package com.cfs.form;

import java.util.Collection;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
private String symbol;
private int fundid;
private String name;
	public List<String> getValidationErrors() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getName() {
		
		// TODO Auto-generated method stub
		return "";
	}

	public String getSymbol() {
		
		// TODO Auto-generated method stub
		return symbol;
	}

	public int getFund_id() {
		
		// TODO Auto-generated method stub
		return fundid;
	}

}
