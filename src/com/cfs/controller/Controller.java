package com.cfs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.EmployeeBean;
import com.cfs.dao.Model;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public void init() throws ServletException {

		Model model = new Model(getServletConfig());

		Action.add(new LoginAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new ChangePwdAction(model));
		Action.add(new FundSearchAction(model));
		Action.add(new CreateEmployeeAccountAction(model));
		Action.add(new CreateCustomerAccountAction(model));
		Action.add(new ResetPasswordAction(model));
		Action.add(new CreateFund(model));
		Action.add(new DepositCheck(model));
		Action.add(new SearchCustomerAction(model));
		Action.add(new TransactionHistory(model));
		Action.add(new ViewProtfolioAction(model));
		Action.add(new BuyFundAction(model));
		Action.add(new SellFundAction(model));
		Action.add(new RequestCheckAction(model));
		Action.add(new ViewAccountDetails(model));
		Action.add(new ViewPriceHistoryAction(model));
		Action.add(new TransitionAction(model));
		Action.add(new TransitionDayAction(model));

		createAutomaticCustomers(model);

	}

	public void createAutomaticCustomers(Model model) {
		try {
			if (model.getCustomerDAO().match().length <= 1) {

				CustomerBean cus1 = new CustomerBean();
				cus1.setCustomer_id(11);
				cus1.setUsername("cus1");

				cus1.setSalt(10);
				cus1.setPassword("cus1");
				cus1.setFirstname("customer");
				cus1.setLastname("one");
				cus1.setAddr_line1("CMU");
				cus1.setAddr_line2("cus1 add");
				cus1.setCity("Pittsburgh");
				cus1.setState("AA");
				cus1.setZip("15213");
				cus1.setCash(99999);
				model.getCustomerDAO().create(cus1);

				CustomerBean cus2 = new CustomerBean();
				cus2.setCustomer_id(12);
				cus2.setUsername("cus2");
				cus2.setSalt(11);
				cus2.setPassword("cus2");
				cus2.setFirstname("customer");
				cus2.setLastname("two");
				cus2.setAddr_line1("Pitthbsjhbdh");
				cus2.setAddr_line2("cus2 add");
				cus2.setCity("Pitts");
				cus2.setState("AB");
				cus2.setZip("15238");
				cus2.setCash(88888);
				model.getCustomerDAO().create(cus2);

				CustomerBean cus3 = new CustomerBean();
				cus3.setCustomer_id(13);
				cus3.setUsername("cus3");
				cus3.setSalt(12);
				cus3.setPassword("cus3");
				cus3.setFirstname("customer");
				cus3.setLastname("three");
				cus3.setAddr_line1("CmuPitts");
				cus3.setAddr_line2("cus3 add");
				cus3.setCity("Burgh");
				cus3.setState("AC");
				cus3.setZip("15864");
				cus3.setCash(77777);
				model.getCustomerDAO().create(cus3);
			}
		} catch (Exception e) {
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = performTheAction(request, response);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
		String action = getActionName(servletPath);

		System.out.println("servletPath=" + servletPath + " requestURI = " + request.getRequestURI());

		if (action.equals("login.do")) {
			// Allow these actions without logging in
			return Action.perform(action, request);
		}
		
		String role = (String) request.getSession().getAttribute("loginAs");
		boolean illegal = false;
		
		if (role == null) {
			illegal = true;
		} else if (role.equals("cust")) {
			if (action.startsWith("emp_")) {
				illegal = true;
			}
		} else if (role.equals("emp")) {
			if (action.startsWith("cust_")) {
				illegal = true;
			}
		} else {
			illegal = true;
		}
		
		// If the use hasn't logged in, direct him to the login page
		if (illegal) {
			return Action.perform("login.do", request);
		}

		if (action.indexOf("ajax_") != -1) {
			return Action.perform(action, request, response);
		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}

		if (nextPage.isEmpty())
			return;

		if (nextPage.endsWith(".do") || nextPage.indexOf(".do?") != -1) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp") || nextPage.indexOf(".jsp?") != -1) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
			d.forward(request, response);
			return;
		}
		
		if (nextPage.startsWith("http://")) {
			response.sendRedirect(nextPage);
			return;
		} else {
			response.sendRedirect("http://" + nextPage);
			return;
		}

	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
