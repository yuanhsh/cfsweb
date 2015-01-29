package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.form.CreateCustomerAccountForm;

public class CreateCustomerAccountAction extends Action {
	private FormBeanFactory<CreateCustomerAccountForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerAccountForm.class);
	private CustomerDAO customerDAO;

	public CreateCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();

	}

	public String getName() {
		return "emp_create_cust_account.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			CreateCustomerAccountForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				return "create-customer-account.jsp";
			}
			Transaction.begin();
			errors.addAll(form.getValidationErrors());
			CustomerBean customer = customerDAO.getCustomerByUsername(form.getUserName());
			if (customer != null) {
				errors.add("Username already exists. Use a different username.");
			}
			if (form.getState().equals("null")) {
				errors.add("State is required");
			}
			if (errors.size() != 0) {
				Transaction.commit();
				return "create-customer-account.jsp";
			}

			customer = new CustomerBean();
			customer.setUsername(form.getUserName());
			customer.setPassword(form.getPassword());
			customer.setFirstname(form.getFirstName());
			customer.setLastname(form.getLastName());
			customer.setAddr_line1(form.getAddressl1());
			customer.setAddr_line2(form.getAddressl2());
			customer.setCity(form.getCity());
			customer.setZip(form.getZip());
			customer.setState(form.getState());
			customerDAO.createAutoIncrement(customer);
			Transaction.commit();

			request.setAttribute("message", "Account for customer " + customer.getUsername() + " created successfully.");
			return "create-customer-account.jsp";

		} catch (Exception e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
			return "create-customer-account.jsp";
		}

	}

}
