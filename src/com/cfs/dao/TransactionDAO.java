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

	public TransactionBean[] getTransactions(int customer_id) throws RollbackException {
		MatchArg arg = MatchArg.equals("customer_id", customer_id);
		TransactionBean[] trans= match(arg);
		return trans;
	}
	
	public TransactionBean[] getPendingTransactions() throws RollbackException {
		MatchArg arg = MatchArg.equals("status", TransactionBean.STATUS_PENDING);
		TransactionBean[] trans= match(arg);
		return trans;
	}
	
	// IMPORTANT: need to be synchonized, customer cash should be updated through sql, 
	// not bean object from session.
	public CustomerBean buyFund(CustomerDAO customerDAO, int customer_id, int fund_id, long amount) throws RollbackException {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_BUY);
		trans.setCustomer_id(customer_id);
		trans.setFund_id(fund_id);
		trans.setAmount(amount);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		
		CustomerBean customer;
		try {
			Transaction.begin();
			customer = customerDAO.read(customer_id);
			if(amount > customer.getCash()) {
				throw new RollbackException("Amount exceeds your current cash balance.");
			}
			customer.setCash(customer.getCash()-amount);
			this.createAutoIncrement(trans);
			customerDAO.update(customer);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
			throw e;
		}
		return customer;
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
			} else if(shares == position.getShares()) {
				positionDAO.delete(position.getPosition_id());
			} else {
				position.setShares(position.getShares()-shares);
				positionDAO.update(position);
			}
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
	
	public CustomerBean requestCheck(CustomerDAO customerDAO, int customer_id, long requestAmount) throws RollbackException {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_REQUEST);
		trans.setCustomer_id(customer_id);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		trans.setAmount(requestAmount);
		CustomerBean customer;
		try {
			Transaction.begin();
			customer = customerDAO.read(customer_id);
			if(requestAmount > customer.getCash()) {
				throw new RollbackException("Request amount exceeds your current cash balance.");
			}
			customer.setCash(customer.getCash()-requestAmount);
			customerDAO.update(customer);
			this.createAutoIncrement(trans);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
			throw e;
		}
		return customer;
	}
	
	public void depositCheck(int customer_id, long depositAmount) throws RollbackException {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_DEPOSIT);
		trans.setCustomer_id(customer_id);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		trans.setAmount(depositAmount);
		try {
			Transaction.begin();
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