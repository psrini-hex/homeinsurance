package com.perscholas.home_insurance.models;

public class CoverageDetails {

	private double monthlyPremuim;
	private double dwellingCoverage;
	private double detachedStructure;
	private double personalProperty;
	private double additional;
	private int medical;
	private double deductible;
	private int quote_id;
	private int property_id;

	public CoverageDetails() {

	}

	public CoverageDetails(double monthlyPremuim, double dwellingCoverage, double detachedStructure,
			double personalProperty, double additional, int medical, double deductible, int property_id) {
		super();
		this.monthlyPremuim = monthlyPremuim;
		this.dwellingCoverage = dwellingCoverage;
		this.detachedStructure = detachedStructure;
		this.personalProperty = personalProperty;
		this.additional = additional;
		this.medical = medical;
		this.deductible = deductible;
		this.property_id = property_id;
	}

	public double getMonthlyPremuim() {
		return monthlyPremuim;
	}

	public void setMonthlyPremuim(double monthlyPremuim) {
		this.monthlyPremuim = monthlyPremuim;
	}

	public double getDwellingCoverage() {
		return dwellingCoverage;
	}

	public void setDwellingCoverage(double dwellingCoverage) {
		this.dwellingCoverage = dwellingCoverage;
	}

	public double getDetachedStructure() {
		return detachedStructure;
	}

	public void setDetachedStructure(double detachedStructure) {
		this.detachedStructure = detachedStructure;
	}

	public double getPersonalProperty() {
		return personalProperty;
	}

	public void setPersonalProperty(double personalProperty) {
		this.personalProperty = personalProperty;
	}

	public double getAdditional() {
		return additional;
	}

	public void setAdditional(double additional) {
		this.additional = additional;
	}

	public int getMedical() {
		return medical;
	}

	public void setMedical(int medical) {
		this.medical = medical;
	}

	public double getDeductible() {
		return deductible;
	}

	public void setDeductible(double deductible) {
		this.deductible = deductible;
	}

	public int getQuote_id() {
		return quote_id;
	}

	public void setQuote_id(int quote_id) {
		this.quote_id = quote_id;
	}

	public int getProperty_id() {
		return property_id;
	}

	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}

//	public CoverageDetails(String monthlyPremuim, String dwellingCoverage, String detachedStructorsl,
//			String personalProperty, String additional, String medical, String deductible, String quote_id) {
//		super();
//		MonthlyPremuim = monthlyPremuim;
//		DwellingCoverage = dwellingCoverage;
//		DetachedStructorsl = detachedStructorsl;
//		PersonalProperty = personalProperty;
//		Additional = additional;
//		Medical = medical;
//		this.deductible = deductible;
//		this.quote_id = quote_id;
//	}
}
