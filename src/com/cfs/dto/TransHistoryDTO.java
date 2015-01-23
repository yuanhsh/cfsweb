package com.cfs.dto;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class TransHistoryDTO {
	private int transaction_id;
	private Date execute_date;
	private String fund_name;
	private String fund_symbol;
	private String transaction_type;
	private String status;
	private long price;
	private long shares;
	
	private String disp_price;
	private String disp_shares;
	private String disp_amount;
	private String disp_date;
	
	public static final DecimalFormat moneyFomatter = new DecimalFormat( "###,###,###,##0.00" );
	public static final DecimalFormat shareFomatter = new DecimalFormat( "###,###,###,##0.000" );
	public static final DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public Date getExecute_date() {
		return execute_date;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public String getStatus() {
		return status;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public void setExecute_date(Date execute_date) {
		this.execute_date = execute_date;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFund_name() {
		return fund_name;
	}
	public String getFund_symbol() {
		return fund_symbol;
	}
	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}
	public void setFund_symbol(String fund_symbol) {
		this.fund_symbol = fund_symbol;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	public long getShares() {
		return shares;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getPrice() {
		return price;
	}
	
	public String getDisp_price() {
		disp_price = "USD "+moneyFomatter.format((double)this.price/100);
		if(this.price == 0) {
			disp_price = "-";
		}
		return disp_price;
	}
	public String getDisp_shares() {
		disp_shares = shareFomatter.format((double)this.shares/1000);
		if(this.shares == 0) {
			disp_shares = "-";
		}
		return disp_shares;
	}
	public String getDisp_amount() {
		disp_amount = "USD "+moneyFomatter.format((double)this.price*(double)this.shares/100000);
		if(shares == 0 || price == 0) {
			disp_amount = "-";
		}
		return disp_amount;
	}
	public void setDisp_price(String disp_price) {
		this.disp_price = disp_price;
	}
	public void setDisp_shares(String disp_shares) {
		this.disp_shares = disp_shares;
	}
	public void setDisp_amount(String disp_amount) {
		this.disp_amount = disp_amount;
	}
	public String getDisp_date() {
		if(this.execute_date == null) return "-";
		try {
			disp_date = dateFormatter.format(this.execute_date);
		} catch (Exception e) {
			disp_date = "-";
			e.printStackTrace();
		}
		return disp_date;
	}
	public void setDisp_date(String disp_date) {
		this.disp_date = disp_date;
	}
	

}
