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
import com.cfs.dao.CustomerDAO;

import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;

import com.cfs.form.DepositCheckForm;



public class DepositCheck extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(DepositCheckForm.class);
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	public DepositCheck(Model model){
		customerDAO=model.getCustomerDAO();
		fundDAO= model.getFundDAO();
	}
	
	public String getName() { return "depositCheck.do"; }
	
	public String perform(HttpServletRequest request){
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			DepositCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
		
			
			
			
	        if (!form.isPresent()) {
	            return "depositCheck.jsp";
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
			
			return "createFund.do"; 		
			 
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "CreateFund.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "CreateFund.jsp";
		}
		
	}
	
}