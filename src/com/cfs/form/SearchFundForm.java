package com.cfs.form;

import org.mybeans.form.FormBean;

public class SearchFundForm extends FormBean {

	public String symbol;
	public String name;
	
    public void setName(String s){
    	name=s;
    }
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	 public void setSymbol(String s){
	    	symbol=s;
	    }
	public String getSymbol() {
		// TODO Auto-generated method stub
		return symbol;
	}
	

}
