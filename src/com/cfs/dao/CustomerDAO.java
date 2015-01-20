package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.bean.CustomerBean;



public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CustomerBean.class, tableName, pool);
	}
	
	public void setPassword(int customer_id, String password) throws RollbackException {
        try {
        	Transaction.begin();
			CustomerBean dbCustomer = read(customer_id);
			
			if (dbCustomer == null) {
				throw new RollbackException("Customer"+dbCustomer+" no longer exists");
			}
			
			dbCustomer.setPassword(password);
			
			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}


