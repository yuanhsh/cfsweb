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
import com.cfs.form.CustomerForm;
import com.cfs.controller.*;



public class SearchCustomerAction extends Action {
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory.getInstance(CustomerForm.class);
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
            	return "Action.do";
            }
        	request.setAttribute("customerList",customerDAO.getCustomers());
        	
        	request.setAttribute("customerID", customerDAO.getCustomerID());
        	
        	request.setAttribute("customerName", customerDAO.getCustomerName());
        	
	        CustomerForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "index.jsp";
	        }
	
	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
	
	        CustomerBean customerBean = new CustomerBean();
	        customerBean.setCustomer(form.getCustomers());
	        customerBean.setCustomerID(form.getCustomerID());
	     

        	
			CustomerBean customer = null;
			
        
	        HttpSession session = request.getSession(false);//why add false at here?
	        session.setAttribute("customer",customer);
	        
			return "Action.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}
}

