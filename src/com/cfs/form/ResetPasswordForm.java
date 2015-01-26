package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetPasswordForm extends FormBean{
	List<String> errors = new ArrayList<String>();
	private String customer_id;
	private String confirmPassword;
	private String newPassword;

	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String s) {
		newPassword     = s.trim();
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String s) {
		confirmPassword = s.trim();
	}
	
	public List<String> getValidationErrors() {

		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("New password with confirm password do not match");
		}
		
			getCustId();
		
		return errors;
}
	public int getCustId(){
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
}
