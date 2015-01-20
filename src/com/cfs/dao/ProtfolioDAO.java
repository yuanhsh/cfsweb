package com.cfs.dao;
import javax.sql.DataSource;

public class ProtfolioDAO {
	private DataSource ds;
	public ProtfolioDAO(DataSource ds) {
		this.ds = ds;
	}
}
