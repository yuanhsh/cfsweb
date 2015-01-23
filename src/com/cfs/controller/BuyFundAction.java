package com.cfs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.dao.TransactionDAO;
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
    	CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "false");
    	try {
    		customer = customerDAO.read(customer.getCustomer_id());
			if(customer != null) {
				request.getSession().setAttribute("customer", customer);
			}
			
			BuyFundForm form = fbFactory.create(request);
			List<String> errors = form.getValidationErrors(request);
			if(errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				map.put("error", errors.get(0));
				String json = new Gson().toJson(map);
				System.out.println("json error: "+ json);
				response.getWriter().write(json);
				return;
			}
			
			int fundId = form.getBuyFundId();
			long amount = form.getTotalAmount();
			transactionDAO.buyFund(customerDAO, customer, fundId, amount);
			map.put("success", "true");
			map.put("info", "Your order has been scheduled successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", "Oops, something wrong happened.");
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
