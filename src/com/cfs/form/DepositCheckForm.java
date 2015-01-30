package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean {
	private String customer_id;
	private String amount;
	private List<String> errors = new ArrayList<String>();

	public String getCustomer_id() {
		return customer_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public int getIntCustomerID() {
		int custId = 0;
		try {
			custId = Integer.parseInt(this.customer_id);
			if (custId < 1) {
				throw new Exception();
			}
		} catch (Exception e) {
			errors.add("customer id error.");
			e.printStackTrace();
		}
		return custId;
	}
	
	public long getDepositAmount() {
		long result = 0;
		try {
			double amount1 = Double.parseDouble(amount);
			if(amount1 > 1000000.0) {
				String msg = "You can't deposit amount larger than $1,000,000.00";
				errors.add(msg);
				throw new Exception(msg);
			}
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
		getIntCustomerID();
		getDepositAmount();
		return errors;
	}

}
