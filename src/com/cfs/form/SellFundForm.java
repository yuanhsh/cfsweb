package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBean;

import com.cfs.bean.CustomerBean;

public class SellFundForm extends FormBean {
	private List<String> errors = new ArrayList<String>();
	private String shares;
	private String fund_id;
	
	public String getShares() {
		return shares;
	}
	public String getFund_id() {
		return fund_id;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}
	
	public List<String> getValidationErrors(HttpServletRequest request) {
		CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
		try {
			getBuyFundId();
			long shares = getSellShares();
//			if(amount > customer.getCash()) {
//				errors.add("amount exceeds you cash balance.");
//			}
		} catch (Exception e) {
			errors.add("input error.");
			e.printStackTrace();
		}
		return errors;
	}
	
	public long getSellShares(){
		long result = 0;
		try {
			double shareNo = Double.parseDouble(shares);
			double total = shareNo * 1000;
			result = (long)total;
			if(total != result || result <= 0) {
				throw new Exception("shares input error.");
			}
		} catch (Exception e) {
			errors.add("shares input error.");
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
