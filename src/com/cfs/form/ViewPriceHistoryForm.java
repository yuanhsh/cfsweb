package com.cfs.form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewPriceHistoryForm extends FormBean {
	private List<String> errors = new ArrayList<String>();
	private String fund_id;
	
	public String getFund_id() {
		return fund_id;
	}

	public void setFund_id(String fund_id) {
		this.fund_id = fund_id;
	}
	
	public List<String> getValidationErrors() {
		try {
			getViewFundId();
		} catch (Exception e) {
			errors.add("input error.");
			e.printStackTrace();
		}
		return errors;
	}
	
	public int getViewFundId(){
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
