package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

import com.cfs.bean.CustomerBean;

public class BuyFundForm extends FormBean {
	private List<String> errors = new ArrayList<String>();
	private String amount;
	private String fund_id;
	
	public String getAmount() {
		return amount;
	}
	public String getFund_id() {
		return fund_id;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}
	
	public List<String> getValidationErrors(HttpServletRequest request) {
		CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
		try {
			getBuyFundId();
			long amount = getTotalAmount();
			if(amount > customer.getCash()) {
				errors.add("amount exceeds you cash balance.");
			}
			if(amount <= 0) {
				errors.add("buy fund amount can not be less than USD 0.01.");
			}
		} catch (Exception e) {
			errors.add("input error.");
			e.printStackTrace();
		}
		return errors;
	}
	
	public long getTotalAmount(){
		long result = 0;
		try {
			double fundAmount = Double.parseDouble(amount);
			double total = fundAmount * 100;
			result = (long)total;
			if(total != result) {
				throw new Exception("amount input error.");
			}
		} catch (Exception e) {
			errors.add("input error.");
			e.printStackTrace();
		}
			
		return result;
	}
	
	public int getBuyFundId(){
		int fundId = 0;
		try {
			fundId = Integer.parseInt(fund_id);
		} catch (Exception e) {
			errors.add("input error.");
			e.printStackTrace();
		}
			
		return fundId;
	}
	
}
