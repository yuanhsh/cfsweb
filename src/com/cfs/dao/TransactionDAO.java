package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.PositionBean;
import com.cfs.bean.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> { 
	public TransactionDAO(String tableName,ConnectionPool pool ) throws DAOException {
		super (TransactionBean.class,tableName,pool);
	}

	public TransactionBean[] getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	public TransactionBean[] getTransactions(int customer_id) throws RollbackException {
		MatchArg arg = MatchArg.equals("customer_id", customer_id);
		TransactionBean[] trans= match(arg);
		return trans;
	}
	
	// IMPORTANT: need to be synchonized, customer cash should be updated through sql, 
	// not bean object from session.
	public void buyFund(CustomerDAO customerDAO, CustomerBean customer, int fund_id, long amount) throws RollbackException {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_BUY);
		trans.setCustomer_id(customer.getCustomer_id());
		trans.setFund_id(fund_id);
		trans.setAmount(amount);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		
		long originalCash = customer.getCash();
		customer.setCash(customer.getCash()-amount);
		try {
			Transaction.begin();
			this.createAutoIncrement(trans);
			customerDAO.update(customer);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
				customer.setCash(originalCash);
			}
			throw e;
		}
	}
	
	public void sellFund(PositionDAO positionDAO, int customer_id, int fund_id, long shares) throws RollbackException {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_SELL);
		trans.setCustomer_id(customer_id);
		trans.setFund_id(fund_id);
		trans.setShares(shares);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		try {
			Transaction.begin();
			PositionBean position = positionDAO.getCustomerFundPosition(customer_id, fund_id);
			if(shares > position.getShares()) {
				throw new RollbackException("Sell share number exceeds your current share numbers");
			}
			position.setShares(position.getShares()-shares);
			positionDAO.update(position);
			this.createAutoIncrement(trans);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
			throw e;
		}
	}
}