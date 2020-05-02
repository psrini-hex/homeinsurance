package com.perscholas.home_insurance.models;

public class PropertyInfo {

	private int propertyId;
	private String value;
	private String year;
	private String footage;
	private String dwelling;
	private String roof;
	private String baths;
	private String half_baths;
	private String pool;
	private String garage;

	public PropertyInfo() {

	}

	public PropertyInfo(String value, String year, String footage, String dwelling, String roof, String baths,
			String half_baths, String pool, String garage) {
		super();
		this.value = value;
		this.year = year;
		this.footage = footage;
		this.dwelling = dwelling;
		this.roof = roof;
		this.baths = baths;
		this.half_baths = half_baths;
		this.pool = pool;
		this.garage = garage;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFootage() {
		return footage;
	}

	public void setFootage(String footage) {
		this.footage = footage;
	}

	public String getDwelling() {
		return dwelling;
	}

	public void setDwelling(String dwelling) {
		this.dwelling = dwelling;
	}

	public String getRoof() {
		return roof;
	}

	public void setRoof(String roof) {
		this.roof = roof;
	}

	public String getBaths() {
		return baths;
	}

	public void setBaths(String baths) {
		this.baths = baths;
	}

	public String getHalf_baths() {
		return half_baths;
	}

	public void setHalf_baths(String half_baths) {
		this.half_baths = half_baths;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public String getGarage() {
		return garage;
	}

	public void setGarage(String garage) {
		this.garage = garage;
	}

}
