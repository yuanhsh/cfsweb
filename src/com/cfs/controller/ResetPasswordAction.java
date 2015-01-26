package com.cfs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.form.ResetPasswordForm;
import com.google.gson.Gson;



public class ResetPasswordAction extends Action {
	private FormBeanFactory<ResetPasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(ResetPasswordForm.class);
	private CustomerDAO customerDAO;

	public ResetPasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {

		return "emp_ajax_reset_password.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		return null;
	}
	
	@Override
	public void performAjax(HttpServletRequest request, HttpServletResponse response) {
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "false");
        try {
        	ResetPasswordForm form = formBeanFactory.create(request);
			List<String> errors = form.getValidationErrors();
			if(errors != null && errors.size() != 0) {
				map.put("error", errors.get(0));
			}else {
				int custId=form.getCustId();
				String passWord=form.getNewPassword();
				customerDAO.setPassword(custId, passWord);
				map.put("success", "true");
				map.put("info", "Your reset the password success.");
			}
        
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", "Oops, "+e.getMessage());
		}
    	
    	try {
    		String json = new Gson().toJson(map);
        	System.out.println("json info: "+ json);
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}