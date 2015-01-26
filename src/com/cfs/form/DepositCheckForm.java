package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean {
	private int customer_id;
	private String amount;
	private List<String> errors = new ArrayList<String>();

	public int getCustomer_id() {
		return customer_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public long getDepositAmount() {
		long result = 0;
		try {
			double amount1 = Double.parseDouble(amount);
			double total = amount1 * 100;
			result = (long)total;
			if(total != result || result <= 0) {
				throw new Exception("deposit amount precision or value error.");
			}
		} catch (Exception e) {
			errors.add("deposit amount precision or value error.");
			e.printStackTrace();
		}
			
		return result;
	}

	public List<String> getValidationErrors() {
		if (this.customer_id < 1) {
			errors.add("CustomerID is required");
		}
		getDepositAmount();
		return errors;
	}

}
