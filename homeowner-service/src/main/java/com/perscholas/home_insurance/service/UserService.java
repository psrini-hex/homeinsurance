package com.perscholas.home_insurance.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perscholas.home_insurance.dao.UserDAO;
import com.perscholas.home_insurance.models.Users;

@RestController
public class UserService {

	@Autowired
	private UserDAO dao;

	@PostMapping(value = "/registerUser")
	@Consumes({ "application/xml", "application/json" })
	public ResponseEntity<Integer> registerUser(@RequestBody Users user) throws Exception {
		try {
			return new ResponseEntity<Integer>(dao.registerUser(user), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@PostMapping(value = "/loginUser", produces = "application/json")
	@Consumes({ "application/xml", "application/json" })
	public ResponseEntity<Users> loginUsers(@RequestBody String user) {
		Users response =  dao.loginUsers(user);
		return new ResponseEntity<Users>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/users", produces = "application/json")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> response = new ArrayList<Users>();
		try {
			response = dao.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Users>>(response, HttpStatus.OK);
	}
}
