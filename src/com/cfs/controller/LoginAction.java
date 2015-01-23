package com.cfs.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.EmployeeBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.Model;
import com.cfs.form.LoginForm;



public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);

	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "login.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		String loginAs = "";
		loginAs = request.getParameter("loginAs");

		try {

			LoginForm form = formBeanFactory.create(request);
			if (loginAs != null && loginAs.equals("cust")) {
				// receive the value here by comparing the value of the
				// customerbean

				// CustomerBean customerBean = getCustomerByUserName(form.getUsername();
				System.out.println(form.getUsername());
				System.out.println(form.getPassword());
				request.setAttribute("customer", customerDAO.getCustomerByUsername(form.getUsername()));
			} else if (loginAs != null && loginAs.equals("emp")) {
				request.setAttribute("employee", employeeDAO.getEmployeeByUsername(form.getUsername()));
			}

			// LoginForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				return "login.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "login.jsp";
			}

			// User user = userDAO.read(form.getUsername());
			CustomerBean customerBean = customerDAO.getCustomer(form.getUsername(), form.getPassword());
			EmployeeBean employeeBean = employeeDAO.getEmployee(form.getUsername(), form.getPassword());
			System.out.println(employeeBean + " emp ");
			System.out.println(loginAs + " loginAs ");
			

			/*
			 * Object loggedInUser = null; if (loginAs.equals("cust")) {
			 * loggedInUser = customerDAO.read(form.getPk()); } else if
			 * (loginAs.equals("emp")) { loggedInUser =
			 * employeeDAO.read(form.getPk()); }
			 */

			if (customerBean == null && employeeBean == null) {
				System.out.println("null value printed in if");
				errors.add("Username or Password is incorrect");
				return "login.jsp";
			}

			if (loginAs.equals("cust")) {
				System.out.println("in cust");
				if (customerBean!=null &&!customerBean.checkPassword(form.getPassword())) {
					errors.add("Username or Password is incorrect");
					return "login.jsp";
				}
			} else if (loginAs.equals("emp")) {
				System.out.println("in emp");
				if (employeeBean!=null && !employeeBean.checkPassword(form.getPassword())) {
					errors.add("Username or Password is incorrect");
					return "login.jsp";
				}
			}

			HttpSession session = request.getSession();
			if (loginAs.equals("cust")) {
				session.setAttribute("customer", customerBean);
				session.setAttribute("loginAs", "cust");
				session.setAttribute("username", customerBean.getUsername());
				session.setAttribute("customer_id", customerBean.getCustomer_id());
				return "view_protfolio.do?customer_id="+customerBean.getCustomer_id();
				//return "view-account-details.do?customer_id="+customerBean.getCustomer_id();
			} else if (loginAs.equals("emp")) {
				session.setAttribute("employee", employeeBean);
				session.setAttribute("loginAs", "emp");
				session.setAttribute("username", employeeBean.getUsername());
				return "fund-list.do";
				//////redirecting the employee on which page after login
				//session.setAttribute("customer_id", customerBean.getCustomer_id());
				//return "create-customer-account.do?employee_id="+employeeBean.getEmployee_id();/////////////////////////////////////////////////needs to be decided
				//return "view-account-details.do?customer_id="+employeeBean.getEmployee_id();
			}
			return "login.jsp"; 
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}