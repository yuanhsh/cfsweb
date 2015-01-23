package com.cfs.controller;


import java.util.ArrayList;

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
		ArrayList<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			TransactionHistoryForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
		
			
			
			
	        if (!form.isPresent()) {
	            return "transactionHistory.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
			
		  // the cash means the check the customer wants to deposit, right?
			
			
			TransactionBean trans=new TransactionBean();
			trans.setFund_id(form.getfund_id());
			
			trans.setCustomer_id(form.getCustomer_id());
			trans.setExecute_date(form.getExecute_date);
			trans.setShares(form.getShares());
			trans.setAmount(form.getAmount());
			trans.setStatus(form.getStatus());
			
			transactionDAO.create(trans);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("transaction", trans);
			
			return "transactionHistory.jsp"; 		
			 
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
		
	}


	
}
