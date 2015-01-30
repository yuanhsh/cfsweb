package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	private String symbol;

	private String name;

	public void setName(String s) {
		name = s.trim();
	}

	public void setSymbol(String s) {
		symbol = s.trim().toUpperCase();
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getSymbol() {
		// TODO Auto-generated method stub
		return symbol;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.length() == 0) {
			errors.add("fund name is required");
		}
		if (symbol == null || symbol.length() == 0) {
			errors.add("symbol is required");
		}
		if(name.length()>20) {
			errors.add("fund name can't have more than 20 characters.");
		}
		if (name.matches(".*[<>\"@$%!^&*/].*")) {
			errors.add("fund name cannot contain angular brackets or quotes");
		}
		if (!symbol.matches("^[a-zA-Z]{1,5}$")) {
			errors.add("symbol must be one to five characters.");
		}
		return errors;
	}

}
