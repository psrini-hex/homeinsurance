package com.perscholas.home_insurance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perscholas.home_insurance.models.CoverageDetails;
import com.perscholas.home_insurance.models.Users;

@Component
public class CoverageDetailsDAO {

	@Autowired
	private MySqlConnection mysql;

	public int storeCoverageDetails(CoverageDetails CD) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		String SAVE = "INSERT INTO COVERAGE_DETAILS "
				+ "	(QUOTE_ID,MONTHLY_PREMIUM,DWELLING_COVERAGE,DETACHED_STRUCTURES,PERSONAL_PROPERTY,ADDITIONAL,MEDICAL,DEDUCTIBLE,PROPERTY_ID)"
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		int quote_id = -1;
		String[] COL = { "quote_id" };

		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE, COL);
			stmt.setInt(1, CD.getQuote_id());
			stmt.setDouble(2, CD.getMonthlyPremuim());
			stmt.setDouble(3, CD.getDwellingCoverage());
			stmt.setDouble(4, CD.getDetachedStructure());
			stmt.setDouble(5, CD.getPersonalProperty());
			stmt.setDouble(6, CD.getAdditional());
			stmt.setInt(7, CD.getMedical());
			stmt.setDouble(8, CD.getDeductible());
			stmt.setInt(9, CD.getProperty_id());
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			if (result != null && result.next()) {
				quote_id = result.getInt(1);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return quote_id;
	}

	public List<CoverageDetails> getAllPolicies(int userId) throws SQLException {
		List<CoverageDetails> allPolicies = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		CoverageDetails cover = null;

		String SAVE = "SELECT * FROM COVERAGE_DETAILS WHERE PROPERTY_ID IN (SELECT property_id FROM HOME_INFO WHERE user_id = ?)";

		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE);
			stmt.setInt(1, userId);
			result = stmt.executeQuery();

			while (result.next()) {
				cover = new CoverageDetails();
				cover.setQuote_id(result.getInt(1));
				cover.setMonthlyPremuim(result.getDouble(2));
				cover.setDwellingCoverage(result.getDouble(3));
				cover.setDetachedStructure(result.getDouble(4));
				cover.setPersonalProperty(result.getDouble(5));
				cover.setAdditional(result.getDouble(6));
				cover.setMedical(result.getInt(7));
				cover.setDeductible(result.getDouble(8));
				cover.setProperty_id(result.getInt(9));
				allPolicies.add(cover);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return allPolicies;
	}

	public CoverageDetails getPolicy(int quoteId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		CoverageDetails cover = null;

		String SAVE = "SELECT * FROM COVERAGE_DETAILS WHERE PROPERTY_ID=?";

		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE);
			stmt.setInt(1, quoteId);
			result = stmt.executeQuery();
			while (result.next()) {
				cover = new CoverageDetails();
				cover.setQuote_id(result.getInt(1));
				cover.setMonthlyPremuim(result.getDouble(2));
				cover.setDwellingCoverage(result.getDouble(3));
				cover.setDetachedStructure(result.getDouble(4));
				cover.setPersonalProperty(result.getDouble(5));
				cover.setAdditional(result.getDouble(6));
				cover.setMedical(result.getInt(7));
				cover.setDeductible(result.getDouble(8));
				cover.setProperty_id(result.getInt(9));
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return cover;
	}
}