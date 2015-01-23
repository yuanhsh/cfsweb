package com.cfs.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.FundBean;
import com.cfs.bean.TransactionBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.dao.TransactionDAO;
import com.cfs.form.TransactionHistoryForm;

public class TransactionHistory extends Action {
	private FormBeanFactory<TransactionHistoryForm> formBeanFactory = FormBeanFactory.getInstance(TransactionHistoryForm.class);
	private TransactionDAO transactionDAO;
	
	public TransactionHistory(Model model){
		transactionDAO=model.getTransactionDAO();
	}
	
	public String getName() { return "transactionHistory.do"; }
	
	public String perform(HttpServletRequest request){
        try {
			TransactionHistoryForm form = formBeanFactory.create(request);
	        if (!form.isPresent()) {
	            return "TransactionHistory.jsp";
	        }
	        List<String> errors = form.getValidationErrors();
	        if (errors != null && errors.size() != 0) {
	        	request.setAttribute("errors", errors);
	            return "error.jsp";
	        }
			TransactionBean[] trans = transactionDAO.getTransactions(form.getCustID());
			request.setAttribute("transactionList", trans);
			return "TransactionHistory.jsp"; 	
		} catch (Exception e) {
			e.printStackTrace();
			return "error.jsp";
		}
		
	}

}
