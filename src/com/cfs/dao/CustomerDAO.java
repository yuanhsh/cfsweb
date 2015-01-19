package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import com.cfs.bean.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CustomerBean.class, tableName, pool);
	}

	public static CustomerBean[] getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}
	public static Object getCustomerID() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getCustomerName() {
		// TODO Auto-generated method stub
		return null;
	}	
}


