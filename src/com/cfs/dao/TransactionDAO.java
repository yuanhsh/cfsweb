package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import com.cfs.bean.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> { 
	public TransactionDAO(String tableName,ConnectionPool pool ) throws DAOException {
		super (TransactionBean.class,tableName,pool);
	}

	public TransactionBean[] getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

}