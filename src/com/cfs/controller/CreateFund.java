package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;


import com.cfs.bean.FundBean;

import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;

import com.cfs.form.CreateFundForm;



public class CreateFund extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	private FundDAO fundDAO;
	
	public CreateFund(Model model){
		fundDAO=model.getFundDAO();
		
	}
	
	public String getName() { return "emp_create_fund.do"; }
	
	public String perform(HttpServletRequest request){
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			CreateFundForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "createFund.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "createFund.jsp";
	        }
			
			FundBean fund=new FundBean();
			fund.setName(form.getName());
			fund.setSymbol(form.getSymbol());
			
			
			fundDAO.createAutoIncrement(fund);
			
			request.setAttribute("message", "Successfully created fund "+fund.getName());
			 return "createFund.jsp";

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