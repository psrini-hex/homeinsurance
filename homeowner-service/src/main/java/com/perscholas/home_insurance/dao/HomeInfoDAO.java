package com.perscholas.home_insurance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perscholas.home_insurance.models.HomeInfo;

@Component
public class HomeInfoDAO {

	@Autowired
	private MySqlConnection mysql;

	public int storeHomeInfo(HomeInfo h) throws SQLException {

		System.out.println(h.getAddress());
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		String SAVE = "INSERT INTO HOME_INFO "
				+ "(ADDRESS,STATE,CITY,ZIP,HOME_USE,RESIDENCE_TYPE,USER_ID,ADDRESS_LINE_2) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		@SuppressWarnings("unused")
		int quote_id = -1;
		String[] COL = { "quote_id" };

		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE, COL);
			stmt.setString(1, h.getAddress());
			stmt.setString(2, h.getState());
			stmt.setString(3, h.getCity());
			stmt.setString(4, h.getZip());
			stmt.setString(5, h.getUse());
			stmt.setString(6, h.getResidence_type());
			stmt.setInt(7, h.getU_id());
			stmt.setString(8, h.getAddress_line_2());

			System.out.println("stmt.executeUpdate:  " + stmt.executeUpdate());
			result = stmt.getGeneratedKeys();
			if (result != null && result.next()) {
				quote_id = result.getInt(1);
			}

//			result = stmt.getGeneratedKeys();
//			if(result != null && result.next()) {
//				ID=result.getInt(1);
//			}

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

	public void updateHomeInfoQuoteId(Integer homeId, Integer quoteId) throws SQLException {
		Connection conn = null;
		PreparedStatement getStmt = null;
		ResultSet result = null;
		String GET_HOME_INFO = "UPDATE HOME_INFO SET quote_id = ? WHERE home_id =?";
		try {
			conn = mysql.getConnection();
			getStmt = conn.prepareStatement(GET_HOME_INFO);
			getStmt.setInt(2, homeId);
			getStmt.setInt(1, quoteId);
			//result = getStmt.executeQuery();

			System.out.println("stmt.executeUpdate:  " + getStmt.executeUpdate());

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (getStmt != null) {
				getStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void updateHomeInfoPropertyId(Integer homeId, Integer propertyId) throws SQLException {
		Connection conn = null;
		PreparedStatement getStmt = null;
		ResultSet result = null;
		String GET_HOME_INFO = "UPDATE HOME_INFO SET property_id = ? WHERE home_id =?";
		try {
			conn = mysql.getConnection();
			getStmt = conn.prepareStatement(GET_HOME_INFO);
			getStmt.setInt(2, homeId);
			getStmt.setInt(1, propertyId);
			//result = getStmt.executeQuery();

			System.out.println("stmt.executeUpdate:  " + getStmt.executeUpdate());

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (getStmt != null) {
				getStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public HomeInfo getAllTheData(Integer user_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		HomeInfo hi = new HomeInfo();

		String SAVE = "SELECT * FROM HOME_INFO WHERE user_id =?";
		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE);
			stmt.setInt(1, user_id);
			result = stmt.executeQuery();
			if (result.next()) {
//				hi = new HomeInfo(result.getString(7), result.getString(2), result.getString(3), result.getString(4),
//						result.getString(5), result.getString(6), result.getInt(1), result.getInt(8));
				hi.setHomeId(result.getInt(1));
				hi.setAddress(result.getString(2));
				hi.setAddress_line_2(result.getString(3));
				hi.setState(result.getString(4));
				hi.setCity(result.getString(5));
				hi.setZip(result.getString(6));
				hi.setResidence_type(result.getString(7));
				hi.setUse(result.getString(8));
				hi.setU_id(result.getInt(9));
				hi.setQuote_id(result.getInt(10));
				hi.setPropertyId(result.getInt(11));
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hi;

	}

}
