package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchFundForm extends FormBean {

	private String fund_key;

	public String getFund_key() {
		return fund_key;
	}

	public void setFund_key(String fund_key) {
		this.fund_key = fund_key;
	}
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		if(fund_key != null && !fund_key.trim().isEmpty()) {
			if(!fund_key.matches("\\w+")) {
				errors.add("search keyword contains illegal characters.");
			}
		}
		return errors;
	}
	

}
