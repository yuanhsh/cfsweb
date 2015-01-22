package com.cfs.form;



import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FundForm extends FormBean {
	private String fundId;

	public String getFundId() { return fundId; }
	
	public int getIdAsInt() {
		try {
			return Integer.parseInt(fundId);
		} catch (NumberFormatException e) {
			// call getValidationErrors() to detect this
			return -1;
		}
	}
	public void setId(String id) { this.fundId = id; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundId == null || fundId.length() == 0) {
			errors.add("Id is required");
			return errors;
		}

		try {
			Integer.parseInt(fundId);
		} catch (NumberFormatException e) {
			errors.add("Id is not an integer");
		}
		
		return errors;
	}
}
