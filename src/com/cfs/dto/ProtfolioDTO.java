package com.cfs.dto;

public class ProtfolioDTO {

	private int fund_id;
	private String fund_name;
	private String fund_symbol;
	private long initial_price;
	private long current_price;
	private long shares;
	
	public int getFund_id() {
		return fund_id;
	}
	public String getFund_name() {
		return fund_name;
	}
	public String getFund_symbol() {
		return fund_symbol;
	}
	public long getInitial_price() {
		return initial_price;
	}
	public long getCurrent_price() {
		return current_price;
	}
	public long getShares() {
		return shares;
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
	public void setInitial_price(long initial_price) {
		this.initial_price = initial_price;
	}
	public void setCurrent_price(long current_price) {
		this.current_price = current_price;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	
}
