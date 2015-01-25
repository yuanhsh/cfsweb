package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SearchCustomerForm extends FormBean{
	
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (keyword != null && !keyword.isEmpty()) {
			if (!keyword.matches("\\w+")) {
				errors.add("Search keyword cannot contain illegal characters.");
			}
		}

		return errors;
	}

}
