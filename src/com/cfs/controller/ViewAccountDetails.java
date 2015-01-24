package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.form.CustIdForm;
import com.cfs.form.CustomerForm;


public class ViewAccountDetails extends Action {
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory.getInstance(CustomerForm.class);
	private FormBeanFactory<CustIdForm> custIdBeanFactory=  FormBeanFactory.getInstance(CustIdForm.class);
	private CustomerDAO customerDAO;
	public ViewAccountDetails(Model model) {
		 customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		return "view_account_details.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
		try {
			CustIdForm formid=custIdBeanFactory.create(request);
			CustomerForm form = formBeanFactory.create(request);
			System.out.println("Custommmm");
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
	        
	       /* int id=formid.getIdAsInt();
	        
	        CustomerBean cb= customerDAO.read(id);
	        if(cb==null){
	        	errors.add("No Customer id is"+id);
	        	return "error.jsp";
	        }*/
	        
			/*List<String> errors = form.getValidationErrors(request);
			if(errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "error.jsp";
			}*/
			//List<ProtfolioDTO> funds = this.protfolioDAO.getProtfolio(form.getCustomerIdNumber());
			//request.setAttribute("funds", funds);
	        
	        
			String role = (String)request.getSession().getAttribute("loginAs");
			int customer_id=formid.getIdAsInt();
	        
	        CustomerBean customer= customerDAO.read(customer_id);
			if(role.equals("cust")) {
				//int customer_id = (int)request.getAttribute("customer_id");
				//CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer"); 
		        
		       /* if(custb==null){
		        	errors.add("No Customer id is"+customer_id);
		        	return "error.jsp";*/
		        
				//CustomerBean customer;
				
				try {
					customer = customerDAO.getCustomerByCustomerId(customer_id);
					request.setAttribute("customer", customer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				return "view-account-details.jsp";
			}
			
			if(role.equals("emp")){
				CustomerBean[] customers = customerDAO.getCustomers();
				request.setAttribute("customerList", customers);
			}
			
			
		} catch (FormBeanException e) {
			e.printStackTrace();
			return "error.jsp";
		} catch (RollbackException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "view-account-details.jsp";
	}

}
