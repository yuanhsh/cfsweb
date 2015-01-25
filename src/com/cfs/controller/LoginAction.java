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
				System.out.println(form.getUsername());
				System.out.println(form.getPassword());
				request.setAttribute("customer", customerDAO.getCustomerByUsername(form.getUsername()));
			} else if (loginAs != null && loginAs.equals("emp")) {
				request.setAttribute("employee", employeeDAO.getEmployeeByUsername(form.getUsername()));
			}

			request.setAttribute("form", form);
			if (!form.isPresent()) {
				return "login.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "login.jsp";
			}

			CustomerBean customerBean = null;
			EmployeeBean employeeBean = null;
			if (loginAs.equals("cust"))
				customerBean = customerDAO.getCustomer(form.getUsername(), form.getPassword());
			if (loginAs.equals("emp"))
				employeeBean = employeeDAO.getEmployee(form.getUsername(), form.getPassword());

			if (customerBean == null && employeeBean == null) {
				errors.add("Username or Password is incorrect");
				return "login.jsp";
			}

			if (loginAs.equals("cust")) {
				if (customerBean != null && !customerBean.checkPassword(form.getPassword())) {
					errors.add("Username or Password is incorrect");
					return "login.jsp";
				}
			} else if (loginAs.equals("emp")) {
				if (employeeBean != null && !employeeBean.checkPassword(form.getPassword())) {
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
				return "view_protfolio.do?customer_id=" + customerBean.getCustomer_id();
			} else if (loginAs.equals("emp")) {
				session.setAttribute("employee", employeeBean);
				session.setAttribute("loginAs", "emp");
				session.setAttribute("username", employeeBean.getUsername());
				session.setAttribute("employee_id", employeeBean.getEmployee_id());
				return "search_fund.do";
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