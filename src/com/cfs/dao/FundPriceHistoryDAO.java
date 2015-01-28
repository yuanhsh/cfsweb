package com.cfs.dao;

import java.sql.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.bean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> { 
	public FundPriceHistoryDAO(String tableName,ConnectionPool pool ) throws DAOException {
		super (FundPriceHistoryBean.class,tableName,pool);
	}
	
	public FundPriceHistoryBean[] getPriceHistory(int fund_id) throws RollbackException {
		MatchArg arg = MatchArg.equals("fund_id", fund_id);
		return match(arg);
	}
	
	public boolean hasPriceDateGE(Date date) throws RollbackException {
		MatchArg arg = MatchArg.greaterThanOrEqualTo("price_date", date);
		FundPriceHistoryBean[] records = match(arg);
		if(records==null || records.length==0) {
			return false;
		}
		return true;
	}

}
	
	
	
