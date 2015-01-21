package com.cfs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.Model;

public class LogoutAction extends Action {

	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;

	public LogoutAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "logout.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.setAttribute("user", null);
		session.setAttribute("username", null);
		session.setAttribute("loginAs", null);
		request.setAttribute("message", "You are now logged out");
		return "success.jsp";
	}
}
