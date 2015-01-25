package com.cfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.form.CustomerForm;

public class ViewAccountDetails extends Action {
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory.getInstance(CustomerForm.class);

	private CustomerDAO customerDAO;

	public ViewAccountDetails(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "view_account_details.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		try {
			CustomerForm form = formBeanFactory.create(request);
			List<String> errors = form.getValidationErrors(request);
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "error.jsp";
			}
			int customer_id = form.getCustomerIdNumber();
			CustomerBean customer = customerDAO.read(customer_id);
			request.setAttribute("customer", customer);
		} catch (Exception e) {
			e.printStackTrace();
			return "error.jsp";
		}
		return "view-account-details.jsp";
	}

}
