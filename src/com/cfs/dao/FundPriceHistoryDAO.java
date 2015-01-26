package com.cfs.dao;

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

}
	
	
	
