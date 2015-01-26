package com.cfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.FundPriceHistoryBean;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.Model;
import com.cfs.form.ViewPriceHistoryForm;
import com.google.gson.Gson;

public class ViewPriceHistoryAction extends Action {
	private FormBeanFactory<ViewPriceHistoryForm> fbFactory = FormBeanFactory
			.getInstance(ViewPriceHistoryForm.class);
	private FundPriceHistoryDAO priceDAO;

	public ViewPriceHistoryAction(Model model) {
		priceDAO = model.getPriceHistoryDAO();
	}

	@Override
	public String getName() {
		return "ajax_price_history.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return null;
	}

	@Override
	public void performAjax(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			ViewPriceHistoryForm form = fbFactory.create(request);
			List<String> errors = form.getValidationErrors();
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				response.getWriter().write("error:"+errors.get(0));
			} else {
				FundPriceHistoryBean[] history = priceDAO.getPriceHistory(form.getViewFundId());
				double[][] prices = new double[history.length][2];
				for(int i=0; i<history.length; i++) {
					FundPriceHistoryBean fundPrice = history[i];
					prices[i][0] = fundPrice.getPrice_date().getTime();
					prices[i][1] = (double)fundPrice.getPrice()/100.0;
				}
				String json = new Gson().toJson(prices);
				System.out.println("json info: " + json);
				response.getWriter().write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
