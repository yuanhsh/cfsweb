package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

public class TransactionHistoryForm extends FormBean {
	private List<String> errors = new ArrayList<String>();
	public String customer_id;

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	public List<String> getValidationErrors(HttpServletRequest request) {
		try {
			int custId = getCustID();
			String role = (String)request.getSession().getAttribute("loginAs");
			if(!"emp".equals(role)) {
				int customerId = (Integer)request.getSession().getAttribute("customer_id");
				if(custId != customerId) {
					errors.add("you do not have permission to view other's account.");
				}
			}
			
		} catch (Exception e) {
			errors.add("customer_id format error.");
			e.printStackTrace();
		}
		return errors;
	}
	
	public int getCustID(){
		int custId = 0;
		try {
			custId = Integer.parseInt(this.customer_id);
		} catch (Exception e) {
			errors.add("customer_id format error.");
			e.printStackTrace();
		}
			
		return custId;
	}

}
