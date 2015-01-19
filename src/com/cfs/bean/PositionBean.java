package com.cfs.bean;

import org.genericdao.PrimaryKey;

@PrimaryKey("position_id")
public class PositionBean {
	private int customer_id;
	private int fund_id;
	private String shares;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public String getShares() {
		return shares;
	}

	public void setShares(String shares) {
		this.shares = shares;
	}
}
