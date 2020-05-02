package com.perscholas.home_insurance.models;

public class HomeInfo {

	private int homeId;
	private String address;
	private String state;
	private String city;
	private String zip;
	private String residence_type;
	private String use;
	private int u_id;
	private String address_line_2;
	private int quote_id;
	private int propertyId;

	public HomeInfo() {

	}

	public HomeInfo(String address, String state, String city, String zip, String residence_type, String use, int u_id,
			String address_line_2, int quote_id, int property_id) {
		super();
		this.address = address;
		this.state = state;
		this.city = city;
		this.zip = zip;
		this.residence_type = residence_type;
		this.use = use;
		this.u_id = u_id;
		this.address_line_2 = address_line_2;
		this.quote_id = quote_id;
		this.propertyId = property_id;
	}

	public HomeInfo(String address, String state, String city, String zip, String residence_type, String use, int u_id,
			int quote_id) {
		super();
		this.address = address;
		this.state = state;
		this.city = city;
		this.zip = zip;
		this.residence_type = residence_type;
		this.use = use;
		this.u_id = u_id;
		this.quote_id = quote_id;
	}

	public int getHomeId() {
		return homeId;
	}

	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getResidence_type() {
		return residence_type;
	}

	public void setResidence_type(String residence_type) {
		this.residence_type = residence_type;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public int getQuote_id() {
		return quote_id;
	}

	public void setQuote_id(int quote_id) {
		this.quote_id = quote_id;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

}
