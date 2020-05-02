package com.perscholas.home_insurance.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perscholas.home_insurance.dao.HomeInfoDAO;
import com.perscholas.home_insurance.models.HomeInfo;

@RestController
public class HomeInfoService {

	@Autowired
	private HomeInfoDAO dao;

	@PostMapping(value = "/storeHomeInfo")
	@Consumes({ "application/xml", "application/json" })
	public ResponseEntity<Integer> storeHomeInfo(@RequestBody HomeInfo homeInfo) throws Exception {
		try {
			return new ResponseEntity<Integer>(dao.storeHomeInfo(homeInfo), HttpStatus.OK);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@PutMapping(value = "/updateHomeQuote/{homeId}/{quoteId}", produces = "application/json")
	public void updateQuote(@PathVariable("homeId") Integer homeId, @PathVariable("quoteId") Integer quoteId) throws SQLException {
		dao.updateHomeInfoQuoteId(homeId, quoteId);
	}

	@PutMapping(value = "/updateHomeProperty/{homeId}/{propertyId}", produces = "application/json")
	public void updateProperty(@PathVariable("homeId") Integer homeId, @PathVariable("propertyId") Integer propertyId) throws SQLException {
		dao.updateHomeInfoPropertyId(homeId, propertyId);
	}

	@GetMapping(value = "/getHomeInfo/{userId}", produces = "application/json")
	public ResponseEntity<HomeInfo> getAllInfo(@PathVariable("userId") Integer userId) {
		HomeInfo response = dao.getAllTheData(userId);
		return new ResponseEntity<HomeInfo>(response, HttpStatus.OK);
	}
}
