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
	public CustomerBean[] getCustomers(String keyword) throws RollbackException {
		CustomerBean[] customer = null;
		if(keyword == null || keyword.trim().isEmpty()) {
			customer= match();
		} else {
			keyword = keyword.trim();
			MatchArg firstnameMatch = MatchArg.containsIgnoreCase("firstname", keyword);
			MatchArg lastnameMatch = MatchArg.containsIgnoreCase("lastname", keyword);
			MatchArg usernameMatch = MatchArg.containsIgnoreCase("username", keyword);
			MatchArg matches = MatchArg.or(firstnameMatch, lastnameMatch, usernameMatch);
			if(keyword.matches("^\\d+$")) {
				MatchArg idMatch = MatchArg.equals("customer_id", Integer.valueOf(keyword));
				matches = idMatch; //MatchArg.or(matches, idMatch);
			}
			customer = match(matches);
		}
		return customer;
	}

	public CustomerBean getCustomerByUsername(String username) throws RollbackException {
		CustomerBean[] customer1 = match(MatchArg.equals("username", username));
		if (customer1 ==null || customer1.length<1) {
			return null;
		}
		return customer1[0];
	}
	
	public CustomerBean getCustomerByCustomerId(int custId) throws RollbackException {
		CustomerBean[] customer1 = match(MatchArg.equals("customer_id", custId));
		if (customer1 ==null || customer1.length<1) {
			return null;
		}
		return customer1[0];
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
	public void create(CustomerBean customer) throws RollbackException {

		try {
			Transaction.begin();
			createAutoIncrement(customer);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
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
