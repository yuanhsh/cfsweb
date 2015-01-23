package com.cfs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cfs.dao.Model;

public class LogoutAction extends Action {

	public LogoutAction(Model model) {
		
	}

	public String getName() {
		return "logout.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "login.do";
	}
}
