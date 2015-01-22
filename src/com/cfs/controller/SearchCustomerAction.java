package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.FundBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.form.CustomerForm;
import com.cfs.form.SearchCustomerForm;
import com.cfs.controller.*;

//@ Meiqi: Changed line 44 and replaced CustomerDAO with customerDAO after ||

public class SearchCustomerAction extends Action {
	private FormBeanFactory<SearchCustomerForm> formBeanFactory = FormBeanFactory.getInstance(SearchCustomerForm.class);
private CustomerDAO customerDAO;
	
	public SearchCustomerAction(Model model) {
		
		customerDAO= model.getCustomerDAO();
}
	
	public String getName() {return "search_customer.do";}
	
	public String perform (HttpServletRequest request){
		
		List<String> errors = new ArrayList<String>();
		
		request.setAttribute("errors",errors);
		
		
	    try {
        	if (customerDAO.getCustomers() == null || customerDAO.getCustomers().length == 0) {
            	return "login.do";
            }
        	request.setAttribute("customerList",customerDAO.getCustomers());
        	
        //	request.setAttribute("customerID", customerDAO.getCustomerID());
        	
        //	request.setAttribute("customerName", customerDAO.getCustomerName());
        	
	        SearchCustomerForm form = formBeanFactory.create(request);
	       // request.setAttribute("form",form);

	      //  if (!form.isPresent()) {
	       //     return "index.jsp";
	      //  }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
	
	        CustomerBean[] array = customerDAO.match(MatchArg.equals("customer_id", form.getCustomerID()));
	        
	     
	       
        	
			CustomerBean customer = null;
			
        
	        //HttpSession session = request.getSession(false);//why add false at here?
	       // session.setAttribute("customer",customer);
	        
		request.setAttribute("searchResult", array);	
        
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "searchResult.jsp";
	   
	}
}
