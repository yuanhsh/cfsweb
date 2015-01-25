package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.bean.FundBean;

public class FundDAO extends GenericDAO<FundBean> { 
	public FundDAO(String tableName,ConnectionPool pool ) throws DAOException {
		super (FundBean.class,tableName,pool);
	}

	public FundBean[] getFunds(String keyword) throws RollbackException{
		FundBean[] funds = null;
		if(keyword == null || keyword.trim().isEmpty()) {
			funds= match();
		} else {
			keyword = keyword.trim();
			MatchArg fundNameMatch = MatchArg.containsIgnoreCase("name", keyword);
			MatchArg fundSymbolMatch = MatchArg.containsIgnoreCase("symbol", keyword);
			MatchArg matches = MatchArg.or(fundNameMatch, fundSymbolMatch);
			if(keyword.matches("^\\d+$")) {
				MatchArg idMatch = MatchArg.equals("fund_id", Integer.valueOf(keyword));
				matches = idMatch; //MatchArg.or(matches, idMatch);
			}
			funds= match(matches);
		}
		return funds;
	}
}