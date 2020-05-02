package com.perscholas.home_insurance.models;

public class HomeOwner {

	private int home_id;
	private String first_name;
	private String last_name;
	private String birth;
	private String ssn;
	private String email;
	private String retired;
	private int u_id;
	private String homeUse;

	public HomeOwner() {

	}

	public HomeOwner(String first_name, String last_name, String birth, String ssn, String email, String retired,
			int u_id, String homeUse) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth = birth;
		this.ssn = ssn;
		this.email = email;
		this.retired = retired;
		this.u_id = u_id;
		this.homeUse = homeUse;
	}

	public int getHome_id() {
		return home_id;
	}

	public void setHome_id(int home_id) {
		this.home_id = home_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRetired() {
		return retired;
	}

	public void setRetired(String retired) {
		this.retired = retired;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getHomeUse() {
		return homeUse;
	}

	public void setHomeUse(String homeUse) {
		this.homeUse = homeUse;
	}

}
