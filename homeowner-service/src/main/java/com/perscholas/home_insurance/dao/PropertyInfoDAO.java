package com.perscholas.home_insurance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perscholas.home_insurance.models.PropertyInfo;

@Component
public class PropertyInfoDAO {

	@Autowired
	private MySqlConnection mysql;

	public int storePropertyInfo(PropertyInfo pi) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		String SAVE = "INSERT INTO PROPERTY_INFO"
				+ "(VALUE_OF_HOME, YEAR_WAS_BUILT, FOOTAGE,HALF_BATHS,BATHS,DWELLING,GARAGE,POOL,ROOF)"
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		int propertyId = -1;
		String[] COL = { "PROPERTY_ID" };
		try {
			conn = mysql.getConnection();
			stmt = conn.prepareStatement(SAVE, COL);

			stmt.setString(1, pi.getValue());
			stmt.setString(2, pi.getYear());
			stmt.setString(3, pi.getFootage());
			stmt.setString(4, pi.getHalf_baths());
			stmt.setString(5, pi.getBaths());
			stmt.setString(6, pi.getDwelling());
			stmt.setString(7, pi.getGarage());
			stmt.setString(8, pi.getPool());
			stmt.setString(9, pi.getRoof());

			stmt.executeUpdate();
			result = stmt.getGeneratedKeys();
			if (result != null && result.next()) {
				propertyId = result.getInt(1);
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
		return propertyId;
	}

}
