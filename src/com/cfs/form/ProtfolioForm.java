package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

import com.cfs.bean.CustomerBean;

public class ProtfolioForm extends FormBean {
	private String customer_id;

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getCustomerIdNumber() {
		return Integer.parseInt(customer_id);
	}
	
	public List<String> getValidationErrors(HttpServletRequest request) {
		int custId = -1;
		List<String> errors = new ArrayList<String>();
		try {
			custId = Integer.parseInt(customer_id);
			CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
			if(customer != null && customer.getCustomer_id() != custId) {
				errors.add("you do not have permission to view other's account.");
			}
		} catch (NumberFormatException e) {
			errors.add("customer id error.");
			e.printStackTrace();
		}
		return errors;
	}

}
