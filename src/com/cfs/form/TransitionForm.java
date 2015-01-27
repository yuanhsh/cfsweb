package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransitionForm extends FormBean{
	private String date;
	private String fundId[];
	private String fundPrice[];

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String[] getFundPrice() {
		return fundPrice;
	}

	public void setFundPrice(String fundPrice[]) {
		this.fundPrice = fundPrice;
	}
	
	public String[] getFundId() {
		return fundId;
	}

	public void setFundId(String fundId[]) {
		this.fundId = fundId;}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (date == null || date.length() == 0) {
			errors.add("Date is required");
		}

		  for(int i=0;i<fundPrice.length;i++){
			  if (fundPrice[i] == null || fundPrice[i].length() == 0) {
					errors.add("Fund Price for all Funds is required");
					break;
				}
		  }
		
		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}

}


