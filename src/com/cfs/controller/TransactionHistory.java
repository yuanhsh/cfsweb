package com.cfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.Model;
import com.cfs.dao.TransHistoryDAO;
import com.cfs.dto.TransHistoryDTO;
import com.cfs.form.TransactionHistoryForm;

public class TransactionHistory extends Action {
	private FormBeanFactory<TransactionHistoryForm> formBeanFactory = FormBeanFactory.getInstance(TransactionHistoryForm.class);
	private TransHistoryDAO transHistoryDAO;
	
	public TransactionHistory(Model model){
		transHistoryDAO = model.getTransHistoryDAO();
	}
	
	public String getName() { return "transactionHistory.do"; }
	
	public String perform(HttpServletRequest request){
        try {
			TransactionHistoryForm form = formBeanFactory.create(request);
	        if (!form.isPresent()) {
	            return "TransactionHistory.jsp";
	        }
	        List<String> errors = form.getValidationErrors(request);
	        if (errors != null && errors.size() != 0) {
	        	request.setAttribute("errors", errors);
	            return "error.jsp";
	        }
	        List<TransHistoryDTO> trans = transHistoryDAO.getTransHistory(form.getCustID());
			request.setAttribute("transactionList", trans);
			return "TransactionHistory.jsp"; 	
		} catch (Exception e) {
			e.printStackTrace();
			return "error.jsp";
		}
		
	}

}
