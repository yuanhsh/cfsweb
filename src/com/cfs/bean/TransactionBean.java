package com.cfs.bean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transaction_id")
public class TransactionBean {
	private int transaction_id;
	private int customer_id;
	private int fund_id;
	private Date execute_date;
	private long shares;
	private String transaction_type;
	private long amount;
	private long price;
	private String status;

	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_COMPLETED = "Completed";
	public static final String STATUS_CANCELLED = "Cancelled";

	public static final String TYPE_BUY = "Buy";
	public static final String TYPE_SELL = "Sell";
	public static final String TYPE_DEPOSIT = "Deposit Check";
	public static final String TYPE_REQUEST = "Request Check";

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

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

	public Date getExecute_date() {
		return execute_date;
	}

	public void setExecute_date(Date execute_date) {
		this.execute_date = execute_date;
	}

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
