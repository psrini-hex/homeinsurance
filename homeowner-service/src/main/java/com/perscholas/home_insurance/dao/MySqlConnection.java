package com.perscholas.home_insurance.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MySqlConnection {

	@Autowired
	private Environment env;

	public Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		Class.forName(env.getProperty("spring.datasource.driver"));
		final Connection connection = DriverManager.getConnection(env.getProperty("spring.datasource.url"), env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
		return connection;
	}
}
