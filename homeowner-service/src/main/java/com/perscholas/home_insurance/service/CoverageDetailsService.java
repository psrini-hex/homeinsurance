package com.perscholas.home_insurance.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perscholas.home_insurance.dao.CoverageDetailsDAO;
import com.perscholas.home_insurance.models.CoverageDetails;
import com.perscholas.home_insurance.models.Users;

@RestController
public class CoverageDetailsService {

	@Autowired
	private CoverageDetailsDAO dao;

	@PostMapping(value = "/storeCoverageDetails")
	@Consumes({ "application/xml", "application/json" })
	public ResponseEntity<Integer> storeCoverageDetails(@RequestBody CoverageDetails coverage) throws Exception {
		try {
			return new ResponseEntity<Integer>(dao.storeCoverageDetails(coverage), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@GetMapping(value = "/getPolicy/{quoteId}", produces = "application/json")
	@Consumes({ "application/xml", "application/json" })
	public ResponseEntity<CoverageDetails> loginUsers(@PathVariable("quoteId") Integer quoteId) throws SQLException {
		CoverageDetails response =  dao.getPolicy(quoteId);
		return new ResponseEntity<CoverageDetails>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/policies/{userId}", produces = "application/json")
	public ResponseEntity<List<CoverageDetails>> getAllPolicies(@PathVariable("userId") Integer userId) {
		List<CoverageDetails> response = new ArrayList<>();
		try {
			response = dao.getAllPolicies(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<CoverageDetails>>(response, HttpStatus.OK);
	}
}
