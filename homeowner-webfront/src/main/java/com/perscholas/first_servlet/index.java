package com.perscholas.first_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.perscholas.home_insurance.models.CoverageDetails;
import com.perscholas.home_insurance.models.HomeInfo;
import com.perscholas.home_insurance.models.HomeOwner;
import com.perscholas.home_insurance.models.PropertyInfo;
import com.perscholas.home_insurance.models.Users;
import com.perscholas.home_insurance.util.HomeInusranceRestUtil;

/**
 * Servlet implementation class First_try
 */
@WebServlet(urlPatterns = { "/", "/index" })
public class index extends HttpServlet {

	@Autowired
	private HomeInusranceRestUtil restUtil;

	private static final long serialVersionUID = 1L;
	String message = "";
	List<String> errors = new ArrayList<String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		//action = (action.split("/").length > 1) ? action.split("/")[1] : action;
		System.out.println("Action " + action);
		try {
			if (action.contains("getPolicy")) {
				getPolicy(request, response);
				return;
			}
			switch (action) {
			case "/register":
				register(request, response);
				break;
			case "/registerUser":
				registerUser(request, response);
				break;
			case "/login":
				loginUser(request, response);
				break;
			case "/home":
				home(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			case "/get_quote":
				get_quote(request, response);
				break;
			case "/saveHomeInfo":
				saveHomeInfo(request, response);
				break;
			case "/homeOwnerInfo":
				homeOwnerInfo(request, response);
				break;
			case "/propertyInfo":
				propertyInfo(request, response);
				break;
			case "/saveOwnerInfo":
				saveOwnerInfo(request, response);
				break;
			case "/savePropertyInfo":
				savePropertyInfo(request, response);
				break;
			case "/calculateCoverageDetails":
				calculateCoverageDetails(request, response);
				break;
			case "/saveCoverageDetails":
				saveCoverageDetails(request, response);
				break;
			case "/DetailsPolicy":
				DetailsPolicy(request, response);
				break;
			case "/quoteSummary":
				quoteSummary(request, response);
				break;
			case "/detailsToBuy":
				detailsToBuy(request, response);
				break;
			case "/Date":
				Date(request, response);
				break;
			case "/toTheDate":
				toTheDate(request, response);
				break;
			case "/buyingPolicy":
				buyingPolicy(request, response);
				break;
			case "/retrievePolicy":
				getAllPolicy(request, response);
				break;
			case "/getPolicy":
				getPolicy(request, response);
				break;
			default:
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/*****************************************
	 * going to register page
	 *************************/
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		rd.forward(request, response);
	}

	/*****************************************
	 * saving user info to database
	 * 
	 * @throws ServletException
	 *************************/
	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");
		int userRole = 0;
		System.out.println("Login Page - " + username);
		if (null != request.getParameter("user_role"))
			userRole = Integer.parseInt(request.getParameter("user_role"));
		errors.clear();

		if (username.equals("") || username.length() < 3) {
			errors.add("Name can not be empty and must have more than 2 characters");

		}
		if (!password.equals(password_confirm)) {
			errors.add("passwords do not match.");
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			RequestDispatcher rd = request.getRequestDispatcher("/newUsersform");
			rd.forward(request, response);
		} else {
			Users newuser = new Users(username, password, userRole);
			System.out.println(newuser.getUsername());
			int returnId = restUtil.registerUser(newuser);
			System.out.println(returnId);
			response.sendRedirect("main");
		}
	}

	/*****************************************
	 * Login and validation
	 * 
	 * @throws ServletException
	 *************************/
	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		errors.clear();
		System.out.println("Login Page - " + username);
		// login validation
		if (null == username || ("").equals(username)) {
			errors.add("Please enter a valid username");
		}
		if (null == password || ("").equals(password)) {
			errors.add("please enter a password");
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			RequestDispatcher rd = request.getRequestDispatcher("/main");
			rd.forward(request, response);
		} else {
			Users u = new Users();
			u.setUsername(username);
			System.out.println(u.getUsername());
			Users d = restUtil.loginUsers(u.getUsername());

			if (d.getUsername() == null) {
				message = "User name not found. Please try again or register.";
				session.setAttribute("currentUser", null);
				request.setAttribute("validation", message);
				RequestDispatcher rd = request.getRequestDispatcher("main");
				rd.forward(request, response);
			}
			if (d.getPassword().equals(password)) {
				if (d.getUser_role().equals(1)) {
					session.setAttribute("currentUser", d);
					RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
					rd.forward(request, response);
				} else {
					session.setAttribute("currentUser", d);
					response.sendRedirect("home");
				}

			} else {
				message = "invalid login.";
				session.setAttribute("currentUser", null);
				request.setAttribute("validation", message);
				RequestDispatcher rd = request.getRequestDispatcher("main");
				rd.forward(request, response);
			}
		}

	}

	/*****************************************
	 * home page
	 * 
	 * @throws ServletException
	 *************************/
	private void home(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}
	}

	/*****************************************
	 * Logout form
	 *************************/
	private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("main");
	}

	/****************** get a Quote ********************/
	private void get_quote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("get_quote.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("main");
		}

	}

	/******************** send get quote info ************/
	private void saveHomeInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {

			String address = request.getParameter("address");
			String state = request.getParameter("state");
			String city = request.getParameter("city");
			String zip = request.getParameter("zip");
			String resdience_type = request.getParameter("Residence_type");
			String resdience_use = request.getParameter("use");
			Users u = (Users) session.getAttribute("currentUser");
			int user_id = u.getUser_id();
			String address_line_2 = request.getParameter("address_line_2");
			int quoteId = 0;
			int propertyId = 0;
			if (null != request.getParameter("quote_id"))
				quoteId = Integer.parseInt(request.getParameter("quote_id"));
			if (null != request.getParameter("property_id"))
				propertyId = Integer.parseInt(request.getParameter("property_id"));

			session.setAttribute("address", address);
			session.setAttribute("state", state);
			session.setAttribute("city", city);
			session.setAttribute("zip", zip);
			session.setAttribute("type", resdience_type);
			session.setAttribute("use", resdience_use);

			HomeInfo home = new HomeInfo(address, state, city, zip, resdience_type, resdience_use, user_id,
					address_line_2, quoteId, propertyId);
			System.out.println("After contructor: " + home.getAddress());
			int id = restUtil.storeHomeInfo(home);
			home.setHomeId(id);
			session.setAttribute("currentHomeInfo", home.getHomeId());
			System.out.println("Home ID - " + session.getAttribute("currentHomeInfo"));
			response.sendRedirect("homeOwnerInfo");
		}
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response) home owner page
	 */

	private void homeOwnerInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("home_info.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("main");
		}

	}

	/********************************************
	 * property info page
	 ****************************************************************/
	private void propertyInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("propierty_details.jsp");
			rd.forward(request, response);

		} else {
			response.sendRedirect("main");
		}

	}

	/*********************************************************
	 * save owner info
	 *****************************************************/
	private void saveOwnerInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		session.setAttribute("User", request.getParameter("user_id"));
		if (session.getAttribute("currentUser") != null) {

			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String retiered = request.getParameter("retiered");
			String ssn = request.getParameter("ssn");
			String email = request.getParameter("email");
			String birth = request.getParameter("birth");
			Users u = (Users) session.getAttribute("currentUser");
			int user_id = u.getUser_id();
			String use = request.getParameter("use");
			if (null == use || use.equals("null"))
				use = "personal";

			session.setAttribute("first", first_name);
			session.setAttribute("last", last_name);
			session.setAttribute("retired", retiered);
			session.setAttribute("ssn", ssn);
			session.setAttribute("email", email);
			session.setAttribute("birth", birth);

			HomeOwner ho = new HomeOwner(first_name, last_name, birth, ssn, email, retiered, user_id, use);
			session.setAttribute("currentHomeOwner", ho);
			int onwer_id =restUtil.storeHomeOwner(ho);
			System.out.println("Onwer id - "+onwer_id);
			int h = (int) session.getAttribute("currentHomeInfo");
			System.out.println("Under home owner, home info -  " + h);
			response.sendRedirect("propertyInfo");
		} else {
			response.sendRedirect("main");
		}
	}

	/************************************************
	 * saving property info
	 *******************************************************/
	private void savePropertyInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			String value = request.getParameter("value");
			String built = request.getParameter("built");
			String footage = request.getParameter("footage");
			String dwelling = request.getParameter("dwelling");
			String roof = request.getParameter("roof");
			String halfBaths = request.getParameter("halfBaths");
			String baths = request.getParameter("baths");
			String pool = request.getParameter("pool");
			String garage = request.getParameter("garage");

			session.setAttribute("MV", value);
			session.setAttribute("built", built);
			session.setAttribute("footage", footage);
			session.setAttribute("dwelling", dwelling);
			session.setAttribute("roof", roof);
			session.setAttribute("halfBaths", halfBaths);
			session.setAttribute("baths", baths);
			session.setAttribute("pool", pool);
			session.setAttribute("garage", garage);

			PropertyInfo pi = new PropertyInfo(value, built, footage, dwelling, roof, halfBaths, baths, pool, garage);
			session.setAttribute("currentPropertyInfo", pi);
			int propertyId = restUtil.storePropertyInfo(pi);
			pi.setPropertyId(propertyId);
			int h = (int) session.getAttribute("currentHomeInfo");
			System.out.println("Updating property " + h + " - " + propertyId);
			restUtil.updateHomeInfoProperty(h, propertyId);
			response.sendRedirect("calculateCoverageDetails");
		} else {
			response.sendRedirect("main");
		}
	}

	private void getPolicy(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String action = request.getServletPath();
		if (session.getAttribute("currentUser") != null) {
			String propId = request.getParameter("getPropId");
			System.out.println("Prop - " + propId + " - " + action);
			CoverageDetails coverage = restUtil.getPolicyDetails(Integer.parseInt(propId));
			session.setAttribute("monthlyP", coverage.getMonthlyPremuim());
			//session.setAttribute("premium", coverage.get);
			session.setAttribute("coverage", coverage.getDwellingCoverage());
			session.setAttribute("detacheStructure", coverage.getDetachedStructure());
			session.setAttribute("personalProperty", coverage.getPersonalProperty());
			session.setAttribute("living", coverage.getAdditional());
			session.setAttribute("ALC", coverage.getMedical());
			session.setAttribute("deductable", coverage.getDeductible());
			session.setAttribute("property_id", coverage.getProperty_id());
			RequestDispatcher rd = request.getRequestDispatcher("CoverageDetails.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}
	}

	private void getAllPolicy(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			Users u = (Users) session.getAttribute("currentUser");
			int user_id = u.getUser_id();
			List<CoverageDetails> coverList = restUtil.getAllPolicyDetails(user_id);
			session.setAttribute("coverageList", coverList);
			RequestDispatcher rd = request.getRequestDispatcher("all_policies.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}
	}

	/*********************************************************************
	 * caluculations for policy
	 ********************************************************/

	private void calculateCoverageDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			PropertyInfo currentProperty = (PropertyInfo) session.getAttribute("currentPropertyInfo");
			double value = Double.parseDouble(currentProperty.getValue());
			int year = Integer.parseInt(currentProperty.getYear());
			int footage = Integer.parseInt(currentProperty.getFootage());

			int homeValue = footage * 120;

			int ALC = 5000;
			double deductable = (int) (.01 * homeValue);
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			int difference = currentYear - year;
			double reduction = 0;
			if (difference < 5) {
				reduction = (.1 * homeValue);
			} else if (difference < 10) {
				reduction = (.2 * homeValue);
			} else if (difference < 20) {
				reduction = (.3 * homeValue);
			} else if (difference > 20) {
				reduction = (.5 * homeValue);
			}
			double dweleingCovereage = (double) (.50 * value + homeValue);
			double rate = (double) 0.005;
			double detacheStructure = (double) (.10 * dweleingCovereage);
			double personalProperty = (double) (.60 * dweleingCovereage);
			double living = (double) (.20 * dweleingCovereage);
			double premium1 = (rate * homeValue);
			String message = currentProperty.getDwelling();
			double premium2 = 0;
			switch (message) {
			case "single-family":
				premium2 = (premium1 * .005);
				break;
			case "condo":
			case "duplex":
			case "apartment":
				premium2 = (premium1 * .006);
				break;
			case "townhouse":
			case "rowhouse":
				premium2 = (premium1 * .007);
				break;
			}
			double premium = (premium1 + premium2);
			double mothlyPremium = premium / 12;
			session.setAttribute("monthlyP", mothlyPremium);
			session.setAttribute("premium", premium);
			session.setAttribute("coverage", dweleingCovereage);
			session.setAttribute("detacheStructure", detacheStructure);
			session.setAttribute("personalProperty", personalProperty);
			session.setAttribute("living", living);
			session.setAttribute("ALC", ALC);
			session.setAttribute("deductable", deductable);
			session.setAttribute("property_id", currentProperty.getPropertyId());
			RequestDispatcher rd = request.getRequestDispatcher("CoverageDetails.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}
	}

	/********************************************
	 * saving coverage info
	 *************************************************/
	private void saveCoverageDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			double monthlyP = (double) session.getAttribute("monthlyP");
			double coverage = (double) session.getAttribute("coverage");
			double detachedStructors = (double) session.getAttribute("detacheStructure");
			double personalProperty = (double) session.getAttribute("personalProperty");
			double living = (double) session.getAttribute("living");
			int ALC = (int) session.getAttribute("ALC");
			double deductible = (double) session.getAttribute("deductable");
			int property_id = (int) session.getAttribute("property_id");

			CoverageDetails NCD = new CoverageDetails(monthlyP, coverage, detachedStructors, personalProperty, living,
					ALC, deductible, property_id);
			int id = restUtil.storeCoverageDetails(NCD);
			int h = (int) session.getAttribute("currentHomeInfo");
			System.out.println("Updating quote " + h + " - " + id);
			restUtil.updateHomeInfoQuote(h, id);
			response.sendRedirect("quoteSummary");
		} else {
			response.sendRedirect("main");
		}

	}

	/*********************************
	 * details of policy
	 ************************************************************************/
	private void DetailsPolicy(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {

			RequestDispatcher rd = request.getRequestDispatcher("details.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}
	}

	private void quoteSummary(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("buy.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}
	}

	private void detailsToBuy(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			response.sendRedirect("quoteSummary");
		} else {
			response.sendRedirect("main");
		}

	}

	private void Date(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("buyPolicy.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("main");
		}

	}

	private void toTheDate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {
			response.sendRedirect("Date");
		} else {
			response.sendRedirect("main");
		}
	}

	private void buyingPolicy(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("currentUser") != null) {

		} else {
			response.sendRedirect("main");
		}
	}

}
