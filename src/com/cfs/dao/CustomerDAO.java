package com.cfs.dao;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.bean.CustomerBean;


public class CustomerDAO extends GenericDAO<CustomerBean> {
	public CustomerDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(CustomerBean.class, tableName, pool);
	}

	public CustomerBean[] getCustomers() throws RollbackException {
		CustomerBean[] customer = match();
		return customer;
	}

	public CustomerBean[] getCustomerByUsername(String username) throws RollbackException {
		CustomerBean[] customer1 = match(MatchArg.equals("username", username));
		return customer1;
	}

	public CustomerBean getCustomer(String username, String password) throws RollbackException {
		CustomerBean[] customer1 = match(MatchArg.equals("username", username));
		if(customer1!=null && customer1.length>0){
			CustomerBean cust = customer1[0];
			if(cust.checkPassword(password)) return cust;
			return null;
		}else{
			return null;
		}
	}
	

	public void setPassword(int customer_id, String password) throws RollbackException {
		try {
			Transaction.begin();
			CustomerBean dbCustomer = read(customer_id);

			if (dbCustomer == null) {
				throw new RollbackException("User " + customer_id + " no longer exists");
			}

			dbCustomer.setPassword(password);

			update(dbCustomer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
	
}


/*
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
*/