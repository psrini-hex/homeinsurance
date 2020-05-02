package com.perscholas.home_insurance.service;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perscholas.home_insurance.dao.PropertyInfoDAO;
import com.perscholas.home_insurance.models.PropertyInfo;

@RestController
public class PropertyInfoService {

	@Autowired
	private PropertyInfoDAO dao;

	@PostMapping(value = "/storePropertyInfo")
	@Consumes({ "application/xml", "application/json" })
	public int storePropertyInfo(@RequestBody PropertyInfo propertyInfo) throws Exception {
		try {
			return dao.storePropertyInfo(propertyInfo);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
