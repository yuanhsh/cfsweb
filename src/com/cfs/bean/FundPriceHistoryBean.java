package com.cfs.bean;



import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("history_id")
public class FundPriceHistoryBean {
	private int history_id;
	private int fund_id;
	private Date price_date;
	private long price;

	public int getHistory_id() {
		return history_id;
	}

	public void setHistory_id(int history_id) {
		this.history_id = history_id;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public Date getPrice_date() {
		return price_date;
	}

	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
