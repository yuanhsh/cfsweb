package com.cfs.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

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
		return null;
	}

}
