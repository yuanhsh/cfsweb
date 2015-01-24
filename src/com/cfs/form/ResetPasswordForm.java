package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetPasswordForm extends FormBean{
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
		List<String> errors = new ArrayList<String>();

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

		return errors;
	}
	public int getCustID(){
		int custId = 0;
		try {
			custId = Integer.parseInt(this.customer_id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}		
		return custId;
		
	}
}
