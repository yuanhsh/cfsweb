package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchCustomerForm extends FormBean{
	private String customers;
	private String customerID;
	private String button;
	
		public void setCustomers(String s) {
		customers = s.trim();
	}

	public void setCustomerID(String s) {
		customerID = s.trim();
	}
	
	public String getCustomers() {
		// TODO Auto-generated method stub
		return customers;
	}

	public String getCustomerID() {
		// TODO Auto-generated method stub
		return customerID;
	}
	public void setButton(String s) {
		button = s;
	}
	public String getButton() {
		return button;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (customers == null || customers.length() == 0) {
			errors.add("Customer's name is required");
		}

		if (customers.matches(".*[<>\"].*")) {
			errors.add("Customer's name cannot contain angular brackets or quotes");
		}

		if (customerID == null || customerID.length() == 0) {
			errors.add("customerID is required");
		}

		if (button == null) {
			errors.add("Clicking on search button is required");
		}

		if (!button.equals("Search")) {
			errors.add("Invalid button");
		}

		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}

}
