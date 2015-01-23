package com.cfs.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.FundBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.form.TransactionHistoryForm;

public class TransactionHistory extends Action {
	private FormBeanFactory<TransactionHistoryForm> formBeanFactory = FormBeanFactory.getInstance(TransactionHistoryForm.class);
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	public TransactionHistory(Model model){
		customerDAO=model.getCustomerDAO();
		fundDAO= model.getFundDAO();
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
			
			CustomerBean check=new CustomerBean();
			check.setCustomer_id(form.getCustomerID());
			check.setCash(form.getCash());  // the cash means the check the customer wants to deposit, right?
			
			
			FundBean deposit=new FundBean();
			deposit.setFund_id(form.getFund_id());
			deposit.setMoney(form.getCash());
			
			fundDAO.create(deposit);
			customerDAO.create(check);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("check", check);
			
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
