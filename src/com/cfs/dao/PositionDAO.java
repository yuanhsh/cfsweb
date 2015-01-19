package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import com.cfs.bean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> { 
	public PositionDAO(String tableName,ConnectionPool pool ) throws DAOException {
		super (PositionBean.class,tableName,pool);
	}


}