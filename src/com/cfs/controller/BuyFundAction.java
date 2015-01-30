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
import com.cfs.form.BuyFundForm;
import com.google.gson.Gson;

public class BuyFundAction extends Action {
	private FormBeanFactory<BuyFundForm> fbFactory = FormBeanFactory.getInstance(BuyFundForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public BuyFundAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		return "cust_ajax_buy_fund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return null;
	}

	@Override
	public void performAjax(HttpServletRequest request, HttpServletResponse response) {
		CustomerBean customer = (CustomerBean) request.getSession().getAttribute("customer");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "false");
		try {
			Integer custId = (Integer) request.getSession().getAttribute("customer_id");

			BuyFundForm form = fbFactory.create(request);
			List<String> errors = form.getValidationErrors(request);
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				map.put("error", errors.get(0));
				String json = new Gson().toJson(map);
				System.out.println("json error: " + json);
				response.getWriter().write(json);
				return;
			}

			int fundId = form.getBuyFundId();
			long amount = form.getTotalAmount();
			customer = transactionDAO.buyFund(customerDAO, custId, fundId, amount);
			if(customer != null) {
				request.getSession().setAttribute("customer", customer);
			}
			map.put("success", "true");
			map.put("cash", "USD " + ProtfolioDTO.moneyFomatter.format(customer.getCash() / 100.0));
			map.put("info", "Your order has been scheduled successfully.");
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
