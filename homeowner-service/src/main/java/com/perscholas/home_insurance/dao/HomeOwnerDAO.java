package com.perscholas.home_insurance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perscholas.home_insurance.models.HomeOwner;

@Component
public class HomeOwnerDAO {

	@Autowired
	private MySqlConnection mysql;

	public int storeHomeOwner(HomeOwner ho) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		String SAVE = "INSERT INTO HOME_OWNER" + "(EMAIL,FIRST_NAME,LAST_NAME,RETIRED,SSN,BIRTH,USER_ID,HOME_USE)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		int ownerId = -1;
		String[] COL = { "HOME_OWNER_ID" };
		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE, COL);
			stmt.setString(1, ho.getEmail());
			stmt.setString(2, ho.getFirst_name());
			stmt.setString(3, ho.getLast_name());
			stmt.setString(4, ho.getRetired());
			stmt.setString(5, ho.getSsn());
			stmt.setString(6, ho.getBirth());
			stmt.setInt(7, ho.getU_id());
			stmt.setString(8, ho.getHomeUse());
			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			if (result != null && result.next()) {
				ownerId = result.getInt(1);
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
		return ownerId;
	}

	public HomeOwner getHomeOwnerInfo(Integer user_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		HomeOwner ho = new HomeOwner();
		String SAVE = "SELECT * FROM HOME_OWNER where USER_ID =?";
		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE);
			stmt.setInt(1, user_id);
			result = stmt.executeQuery();
			if (result.next()) {
				ho.setHome_id(result.getInt(1));
				ho.setFirst_name(result.getString(2));
				ho.setLast_name(result.getString(3));
				ho.setBirth(result.getString(4));
				ho.setSsn(result.getString(5));
				ho.setEmail(result.getString(6));
				ho.setRetired(result.getString(7));
				ho.setHomeUse(result.getString(8));
				ho.setU_id(result.getInt(9));
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return ho;
	}
}
