package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

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
	
	public void buyFund(int customer_id, int fund_id, long amount) {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_BUY);
		trans.setCustomer_id(customer_id);
		trans.setFund_id(fund_id);
		trans.setAmount(amount);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		try {
			Transaction.begin();
			this.createAutoIncrement(trans);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
	
	public void sellFund(int customer_id, int fund_id, long shares) {
		TransactionBean trans = new TransactionBean();
		trans.setTransaction_type(TransactionBean.TYPE_BUY);
		trans.setCustomer_id(customer_id);
		trans.setFund_id(fund_id);
		trans.setShares(shares);
		trans.setStatus(TransactionBean.STATUS_PENDING);
		try {
			Transaction.begin();
			this.createAutoIncrement(trans);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
		}
	}
}