package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransactionHistoryForm extends FormBean {
	private String name;
	private int customerID;
	private String button;
	private long cash;
	private int fundID;
	private String type;
	
	
	
		public void setName(String s) {
		name = s.trim();
	}

	public void setCustomerID(int s) {
		customerID = s;
	}
	
    public void setCash(long s){
    	cash=s;
    }
	public long getCash() {
		// TODO Auto-generated method stub
		return cash;
	}
	  public void setType(String s){
	    	type=s;
	    }
		public String getType() {
			// TODO Auto-generated method stub
			return type;
		}

	public void setFund_id(int s){
		fundID=s;
	}
	public int getFund_id() {
		// TODO Auto-generated method stub
		return fundID;
	}
	

	public int getCustomerID() {
		// TODO Auto-generated method stub
		return customerID;
	}
	public void setButton(String s) {
		button = s;
	}
	public String getButton() {
		return button;
	}
	
  
	String FundID="fundID";
	String cash1="cash";
	String customerid="customerID";

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.length() == 0) {
			errors.add("Fund name is required");
		}
		if (customerid == null || customerid.length() == 0) {
			errors.add("CustomerID is required");
		}
		if (FundID==null||FundID.length()==0) {
			errors.add("FundID is required");
		}
		if (cash1==null||cash1.length()==0) {
			errors.add("cash is required");
		}

		if (name.matches(".*[<>\"].*")) {
			errors.add("Funds name cannot contain angular brackets or quotes");
		}
		if (customerid.matches(".*[<>\"].*")) {
			errors.add("customerID cannot contain angular brackets or quotes");
		}
		if (FundID.matches(".*[<>\"].*")) {
			errors.add("FundID  cannot contain angular brackets or quotes");
		}
		if (cash1.matches(".*[<>\"].*")) {
			errors.add("cash1  cannot contain angular brackets or quotes");
		}
		

		if (button == null) {
			errors.add("Clicking on deposit button is required");
		}

		if (!button.equals("Deposit")) {
			errors.add("Invalid button");
		}

		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}


}
