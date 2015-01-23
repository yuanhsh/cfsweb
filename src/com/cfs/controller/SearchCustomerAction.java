
package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.FundBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;

public class SearchCustomerAction extends Action {

private CustomerDAO customerDAO;
	
	public SearchCustomerAction(Model model) {
		
		customerDAO= model.getCustomerDAO();
}
	
	public String getName() {return "searchCustomer.do";}
	
	public String perform (HttpServletRequest request){
		
		List<String> errors = new ArrayList<String>();
		
		request.setAttribute("errors",errors);
		
		try{
			CustomerBean[] searchCustomer= customerDAO.getCustomers();
			request.setAttribute("searchCustomer",searchCustomer);
			return"searchCustomer.jsp";
		}catch(RollbackException e){
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}