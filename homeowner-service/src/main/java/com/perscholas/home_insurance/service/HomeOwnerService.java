package com.perscholas.home_insurance.service;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perscholas.home_insurance.dao.HomeOwnerDAO;
import com.perscholas.home_insurance.models.HomeOwner;

@RestController
public class HomeOwnerService {

	@Autowired
	private HomeOwnerDAO dao;

	@PostMapping(value = "/storeHomeOwner")
	@Consumes({ "application/xml", "application/json" })
	public ResponseEntity<Integer> storeHomeOwner(@RequestBody HomeOwner owner) throws Exception {
		try {
			return new ResponseEntity<Integer>(dao.storeHomeOwner(owner), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@GetMapping(value = "/getHomeOwner/{userId}", produces = "application/json")
	public ResponseEntity<HomeOwner> getHomeOwnerInfo(@PathVariable("userId") Integer user_id) {
		HomeOwner response = dao.getHomeOwnerInfo(user_id);
		return new ResponseEntity<HomeOwner>(response, HttpStatus.OK);
	}
}
