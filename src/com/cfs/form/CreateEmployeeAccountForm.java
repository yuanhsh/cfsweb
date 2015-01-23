package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeAccountForm extends FormBean {
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String confirm;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String s) {
		userName  = trimAndConvert(s,"<>\""); 
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String s) {
		firstName  = trimAndConvert(s,"<>\"");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String s) {
		lastName  = trimAndConvert(s,"<>\"");
	}
	public String getPassword() {
		return password; 
	}
	public void setPassword(String s) {
		password  = s.trim(); 
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String s) {
		confirm   = s.trim();
	}
	
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		
		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}
		
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		
		return errors;
		
	}
	
	
	
}
