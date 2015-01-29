package com.cfs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.Model;
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.form.SellFundForm;
import com.google.gson.Gson;

public class SellFundAction extends Action {
	private FormBeanFactory<SellFundForm> fbFactory = FormBeanFactory
			.getInstance(SellFundForm.class);
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;

	public SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() {
		return "cust_ajax_sell_fund.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return null;
	}

	@Override
	public void performAjax(HttpServletRequest request, HttpServletResponse response) {
		Integer custId = (Integer) request.getSession().getAttribute("customer_id");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "false");
		try {
			SellFundForm form = fbFactory.create(request);
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
			long shares = form.getSellShares();
			transactionDAO.sellFund(positionDAO, custId, fundId, shares);
			map.put("success", "true");
			map.put("info", "Your order has been scheduled. Reloading page...");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", e.getMessage());
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
