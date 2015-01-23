


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
import com.cfs.form.DepositCheckForm;
import com.cfs.form.TransactionHistoryForm;

//note from Aditi:::::::
//@ Meiqi: The error is because of making Customer_Id a int instead of a String in the CustomerBean. 
//I changed it because cust_id is suppose to be an int value

public class TransactionHistory extends Action {
	private FormBeanFactory<TransactionHistoryForm> formBeanFactory = FormBeanFactory.getInstance(TransactionHistoryForm.class);
	private TransactionDAO transactionDAO;
public TransactionHistory(Model model) {
		
		transactionDAO= model.getTransactionDAO();
}
	
	public String getName() {  return "TransactionHistory.do"; }
	
	public String perform(HttpServletRequest request){
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			TransactionHistoryForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
		
			
			
			
	        if (!form.isPresent()) {
	            return "depositCheck.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
			
		
			
	        TransactionBean[] transactionList= transactionDAO.getTransaction();
			request.setAttribute("history",transactionList);
			return"TransactionHistory.jsp";	
			 
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
		
	}
	
}