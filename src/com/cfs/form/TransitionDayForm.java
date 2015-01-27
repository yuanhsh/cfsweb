package com.cfs.form;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybeans.form.FormBean;

public class TransitionDayForm extends FormBean{
	private String date;
	private String[] fundId;
	private String[] fundPrice;
	private Map<Integer, Long> priceTable;
	private List<String> errors = new ArrayList<String>();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String[] getFundPrice() {
		return fundPrice;
	}

	public void setFundPrice(String[] fundPrice) {
		this.fundPrice = fundPrice;
	}
	
	public String[] getFundId() {
		return fundId;
	}

	public void setFundId(String[] fundId) {
		this.fundId = fundId;}
	

	public List<String> getValidationErrors() {
		if (date == null || date.length() == 0) {
			errors.add("Date is required");
		}
		getExecuteDate();
		if(fundId==null || fundId.length==0) {
			errors.add("Fund ID is required");
		}
		if (fundPrice == null || fundPrice.length == 0) {
			errors.add("Fund Price is required");
		}
		getFundPriceTable();
		return errors;
	}
	
	public Map<Integer, Long> getFundPriceTable() {
		if(priceTable != null) return priceTable;
		priceTable = new HashMap<Integer, Long>();
		try {
			for(int i=0; i<fundId.length; i++) {
				double newPrice = Double.valueOf(fundPrice[i]);
				long price = (long)(newPrice * 100);
				priceTable.put(Integer.valueOf(fundId[i]), price);
			}
		} catch (NumberFormatException e) {
			errors.add("Fund Price format is wrong.");
			e.printStackTrace();
		}
		return priceTable;	
	}
	
	public Date getExecuteDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		df.setLenient(false);
		Date parsedDate = null;
		try {
			parsedDate = new Date(df.parse(this.date).getTime());
		} catch (ParseException e) {
			errors.add("Date format is wrong.");
			e.printStackTrace();
		}
		return parsedDate;
	}

}


