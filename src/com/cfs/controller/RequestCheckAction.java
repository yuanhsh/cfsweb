package com.cfs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.dao.TransactionDAO;
import com.cfs.dto.ProtfolioDTO;
import com.cfs.form.RequestCheckForm;
import com.google.gson.Gson;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory.getInstance(RequestCheckForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public RequestCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "cust_ajax_request_check.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return null;
	}

	public void performAjax(HttpServletRequest request, HttpServletResponse response) {
		CustomerBean customer = (CustomerBean) request.getSession().getAttribute("customer");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "false");
		try {
			RequestCheckForm form = formBeanFactory.create(request);
			List<String> errors = form.getValidationErrors();
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				map.put("error", errors.get(0));
				String json = new Gson().toJson(map);
				System.out.println("json error: " + json);
				response.getWriter().write(json);
				return;
			}

			long requestAmount = form.getRequestCashAmount();
			customer = transactionDAO.requestCheck(customerDAO, customer.getCustomer_id(), requestAmount);
			request.getSession().setAttribute("customer", customer);
			map.put("success", "true");
			map.put("cash", "USD " + ProtfolioDTO.moneyFomatter.format(customer.getCash() / 100.0));
			map.put("info", "Your order has been scheduled.");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", "Oops, " + e.getMessage());
		}

		try {
			String json = new Gson().toJson(map);
			System.out.println("json info: " + json);
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}