package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import com.cfs.bean.FundBean;
import com.cfs.bean.TransactionBean;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.dao.TransactionDAO;

public class TransactionHistory extends Action {

private TransactionDAO transactionDAO;
	
	public TransactionHistory(Model model) {
		
		transactionDAO= model.getTransactionDAO();
}
	
	public String getName() {return "search_transaction.do";}
	
	public String perform (HttpServletRequest request){
		
		List<String> errors = new ArrayList<String>();
		
		request.setAttribute("errors",errors);
		
		TransactionBean[] transactionList= transactionDAO.getTransaction();
		request.setAttribute("history",transactionList);
		return"TransactionHistory.jsp";
	}
}