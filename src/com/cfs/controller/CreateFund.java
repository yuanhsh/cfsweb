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
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.form.CreateCustomerAccountForm;
import com.cfs.form.CreateEmployeeAccountForm;
import com.cfs.form.CreateFundForm;

public class CreateFund extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory.getInstance(CreateFundForm.class);
	private FundDAO fundDAO;
	
	public CreateFund(Model model){
		fundDAO=model.getFundDAO();
		
	}
	
	public String getName() { return "createFund.do"; }
	
	public String perform(HttpServletRequest request){
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			CreateFundForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			if (!form.isPresent()) {
	            return "CreateFund.jsp";
	        }
			errors.addAll(form.getValidationErrors());
			
			FundBean fund= FundDAO.read(form.getName());
			
			if(fund !=null){
				errors.add("this fund is already exist.");
			}
			if (errors.size() == 0) {
				errors.add("You create an employee account success!");
			}
			
			fund=new FundBean();
			fund.setName(form.getName());
			fund.setFund_id(form.getFund_id());
			fund.setSymbol(form.getSymbol());
			
			
			fundDAO.create(fund);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("fund", fund);
			
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