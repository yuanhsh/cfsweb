package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String requestAmount;
	List<String> errors = new ArrayList<String>();

	public String getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(String requestAmount) {
		this.requestAmount = requestAmount;
	}

	public long getRequestCashAmount(){
		long result = 0;
		try {
			double amount = Double.parseDouble(requestAmount);
			double total = amount * 100;
			result = (long)total;
			if(total != result) {
				throw new Exception("amount input error.");
			}
		} catch (Exception e) {
			errors.add("request amount input error.");
			e.printStackTrace();
		}
			
		return result;
	}

	public List<String> getValidationErrors() {
		try {
			getRequestCashAmount();
		} catch (Exception e) {
			errors.add("request amount input error.");
			e.printStackTrace();
		}
		return errors;
	}





}
