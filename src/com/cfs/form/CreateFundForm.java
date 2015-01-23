package com.cfs.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
private String symbol;//、

private String name;//、


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
    String FundID="fundid";
	if (name == null || name.length() == 0) {
		errors.add("fund name is required");
	}
	if (symbol == null || symbol.length() == 0) {
		errors.add("symbol is required");
	}

	if (FundID == null || FundID.length() == 0) {
		errors.add("fundID is required");
	}

	if (name.matches(".*[<>\"].*")) {
		errors.add("Customer's name cannot contain angular brackets or quotes");
	}
	if (symbol.matches(".*[<>\"].*")) {
		errors.add("symbol cannot contain angular brackets or quotes");
	}
	if (FundID.matches(".*[<>\"].*")) {
		errors.add("FundID cannot contain angular brackets or quotes");
	}

	


	if (errors.size() > 0) {
		return errors;
	}

	return errors;
}








}







