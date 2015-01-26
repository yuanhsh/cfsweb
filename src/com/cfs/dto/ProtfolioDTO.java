package com.cfs.dto;

import java.text.DecimalFormat;

public class ProtfolioDTO {
	private int customer_id;
	private int fund_id;
	private String fund_name;
	private String fund_symbol;
	private long price;
	private long shares;

	private String disp_price;
	private String disp_shares;
	private String disp_amount;

	public static final DecimalFormat moneyFomatter = new DecimalFormat( "###,###,###,##0.00" );
	public static final DecimalFormat shareFomatter = new DecimalFormat( "###,###,###,##0.000" );

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}

	public String getFund_name() {
		return fund_name;
	}

	public String getFund_symbol() {
		return fund_symbol;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
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
		disp_price = "$" + moneyFomatter.format((double) this.price / 100);
		return disp_price;
	}

	public String getDisp_shares() {
		disp_shares = shareFomatter.format((double) this.shares / 1000);
		return disp_shares;
	}

	public String getDisp_amount() {
		disp_amount = "$" + moneyFomatter.format((double) this.price * (double) this.shares / 100000);
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

}
