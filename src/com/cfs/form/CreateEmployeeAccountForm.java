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
	public void setUserName(String userName) {
		userName  = trimAndConvert(userName,"<>\""); 
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		firstName  = trimAndConvert(firstName,"<>\"");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		lastName  = trimAndConvert(lastName,"<>\"");
	}
	public String getPassword() {
		return password; 
	}
	public void setPassWord(String password) {
		password  = password.trim(); 
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		confirm   = confirm.trim();
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