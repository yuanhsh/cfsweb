package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.form.SearchCustomerForm;

public class SearchCustomerAction extends Action {
	private FormBeanFactory<SearchCustomerForm> formBeanFactory = FormBeanFactory.getInstance(SearchCustomerForm.class);
	private CustomerDAO customerDAO;

	public SearchCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "emp_search_customer.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			SearchCustomerForm form = formBeanFactory.create(request);
			errors.addAll(form.getValidationErrors());
			request.setAttribute("keyword", form.getKeyword());
			if(errors.size()>0) {
				return "searchCustomer.jsp";
			}
			CustomerBean[] customers = customerDAO.getCustomers(form.getKeyword());
			request.setAttribute("customers", customers);
			return "searchCustomer.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}