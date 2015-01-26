package com.cfs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.Model;
import com.cfs.dao.TransactionDAO;
import com.cfs.form.DepositCheckForm;
import com.google.gson.Gson;

public class DepositCheck extends Action {
	private FormBeanFactory<DepositCheckForm> fbFactory = FormBeanFactory.getInstance(DepositCheckForm.class);
	private TransactionDAO transactionDAO;
	public DepositCheck(Model model){
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() { return "emp_ajax_deposit_check.do"; }
	
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
			DepositCheckForm form = fbFactory.create(request);
			List<String> errors = form.getValidationErrors();
			if (errors != null && errors.size() != 0) {
				map.put("error", errors.get(0));
			} else {
				int custId = form.getIntCustomerID();
				long amount = form.getDepositAmount();
				transactionDAO.depositCheck(custId, amount);
				map.put("success", "true");
				map.put("info", "Your order has been scheduled.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", e.getMessage());
		}

		try {
			String json = new Gson().toJson(map);
			System.out.println("json result: " + json);
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}