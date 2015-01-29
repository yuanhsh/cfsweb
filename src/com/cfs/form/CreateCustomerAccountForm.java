package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerAccountForm extends FormBean{
	private String userName;
	private String password;
	private String confirm;
	private String firstName;
	private String lastName;
	private String addressl1;
	private String addressl2;
	private String city;
	private String zip;
	private String state;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String s) {
		userName = trimAndConvert(s,"<>\"");
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String s) {
		password = s.trim();
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String s) {
		confirm = s.trim();;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String s) {
		firstName = trimAndConvert(s,"<>\"");
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String s) {
		lastName = trimAndConvert(s,"<>\"");
	}
	public String getAddressl1() {
		return addressl1;
	}
	public void setAddressl1(String s) {
		addressl1 = trimAndConvert(s,"<>\"");
	}
	public String getAddressl2() {
		return addressl2;
	}
	public void setAddressl2(String s) {
		addressl2 = trimAndConvert(s,"<>\"");
	}
	public String getCity() {
		return city;
	}
	public void setCity(String s) {
		city = trimAndConvert(s,"<>\"");
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String s) {
		zip = trimAndConvert(s,"<>\"");
	}
	public String getState() {
		return state;
	}
	public void setState(String s) {
		state = s.trim();
	}
	
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		if (!userName.matches("\\w+")) {
			errors.add("User name Should use numbers, letters or underscore.User name cannot contain angular brackets or quotes");
		}

		if (firstName == null || firstName.length() == 0) {
			errors.add("First Name is required");
		}
		if (!firstName.matches("\\w+")) {
			errors.add("First name Should use numbers, letters or underscore.First name cannot contain angular brackets or quotes");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Last Name is required");
		}
		if (!lastName.matches("\\w+")) {
			errors.add("Last name Should use numbers, letters or underscore.Last name cannot contain angular brackets or quotes");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
		if (addressl1 == null || addressl1.length() == 0) {
			errors.add("Address is required");
		}
		if (city == null || city.length() == 0) {
			errors.add("City is required");
		}
		if (zip == null || zip.length() == 0) {
			errors.add("Zip is required");
		}
		if (!zip.matches("[0-9]{5}")) {
			errors.add("Zip must be five-digit number");
		}
		if (state == null || state.length() == 0) {
			errors.add("State is required");
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
