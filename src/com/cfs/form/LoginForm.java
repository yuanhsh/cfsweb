package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String username;
	private String password;
	private String button;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getButton() {
		return button;
	}

	public void setUsername(String s) {
		username = s.trim();
	}

	public void setPassword(String s) {
		password = s.trim();
	}

	public void setButton(String s) {
		button = s;
	}

	public boolean isPresent() {
		return button != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Username is required");
		}

		if (username.matches(".*[<>\"].*")) {
			errors.add("Whoops! Username cannot contain angular brackets or quotes");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (button == null) {
			errors.add("Clicking on login button is required");
		}

		if (!button.equals("Login")) {
			errors.add("Invalid button");
		}

		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}
}