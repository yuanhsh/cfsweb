package com.cfs.form;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransactionHistoryForm extends FormBean {
     public int fund_id;
	public Date getExecute_date;
	public int customer_id;
	public long shares;
	public long amounts;
	public String status;
	
	public void setfund_id(int s) {
		fund_id = s;
	}
	
	public int getfund_id() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCustomer_id() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setCustomer_id(int s) {
		customer_id = s;
	}
	public long getShares() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setShares(long s) {
		shares = s;
	}
	
	public long getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setAmount(long s) {
		amounts = s;
	}
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setStatus(String s) {
		status = s;
	}

}
