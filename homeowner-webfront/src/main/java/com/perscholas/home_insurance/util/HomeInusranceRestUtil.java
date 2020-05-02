package com.perscholas.home_insurance.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.perscholas.home_insurance.models.CoverageDetails;
import com.perscholas.home_insurance.models.HomeInfo;
import com.perscholas.home_insurance.models.HomeOwner;
import com.perscholas.home_insurance.models.PropertyInfo;
import com.perscholas.home_insurance.models.Users;

@Component
public class HomeInusranceRestUtil {

	@Autowired
	private Environment env;
	
	//String userUri = "http://ec2co-ecsel-1uqrmvab3w1bj-641764585.us-east-2.elb.amazonaws.com:8080/";

	public int registerUser(Users user) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		return restTemplate.postForObject(userUri + "registerUser", user, Integer.class);
	}

	public Users loginUsers(String user) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		return restTemplate.postForObject(userUri + "loginUser", user, Users.class);
	}

	public int storeHomeInfo(HomeInfo home) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		return restTemplate.postForObject(userUri + "storeHomeInfo", home, Integer.class);
	}

	public void updateHomeInfoQuote(Integer homeId, Integer quoteId) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		Map<String, Integer> val = new HashMap<String, Integer>();
		val.put("homeId", homeId);
		val.put("quoteId", quoteId);
		restTemplate.put(userUri + "updateHomeQuote/{homeId}/{quoteId}", null, val);
	}

	public void updateHomeInfoProperty(Integer homeId, Integer propertyId) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		Map<String, Integer> val = new HashMap<String, Integer>();
		val.put("homeId", homeId);
		val.put("propertyId", propertyId);
		restTemplate.put(userUri + "updateHomeProperty/{homeId}/{propertyId}", null, val);
	}

	public int storeHomeOwner(HomeOwner ho) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		int id = restTemplate.postForObject(userUri + "storeHomeOwner", ho, Integer.class);
		return id;
	}

	public int storePropertyInfo(PropertyInfo pi) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		return restTemplate.postForObject(userUri + "storePropertyInfo", pi, Integer.class);
	}

	public int storeCoverageDetails(CoverageDetails nCD) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		return restTemplate.postForObject(userUri + "storeCoverageDetails", nCD, Integer.class);
	}

	public List<CoverageDetails> getAllPolicyDetails(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		return restTemplate.getForObject(userUri + "policies/{userId}", List.class, userId);
	}

	public CoverageDetails getPolicyDetails(int quoteId) {
		RestTemplate restTemplate = new RestTemplate();
		String userUri = env.getProperty("service.url");
		Map<String, Integer> val = new HashMap<String, Integer>();
		val.put("quoteId", quoteId);
		return restTemplate.getForObject(userUri + "getPolicy/{quoteId}", CoverageDetails.class, val);
	}
}
