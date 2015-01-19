package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import com.cfs.bean.FundBean;

public class FundDAO extends GenericDAO<FundBean> { 
	public FundDAO(String tableName,ConnectonPool pool ) throws DAOException {
		super (FundBean.class,tableName,pool);
	}

	public FundBean[] getFunds() throws RollbackException{
		FundBean[] funds= match();
		return funds;
		
	}
}