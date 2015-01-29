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
		symbol = s.trim();
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
		if(name != null && !name.trim().isEmpty()) {
			if(!name.matches(".*[<>\"].*@")) {
				errors.add(" illegal characters.");
			}
		}
		if(symbol != null && !symbol.trim().isEmpty()) {
			if(!symbol.matches(".*[<>\"].*@")) {
				errors.add(" illegal characters.");
			}
		}
		
	
		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}

}
