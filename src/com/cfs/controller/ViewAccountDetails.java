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
			CustomerForm form1 = formBeanFactory.create(request);
			System.out.println("Custommmm");
	        errors.addAll(form1.getValidationErrors(request));
	        if (errors.size() != 0) {
	            return "error.jsp";
	        }
	        	        
			String role = (String)request.getSession().getAttribute("loginAs");
			int customer_id=formid.getIdAsInt();
	        
	        CustomerBean customer= customerDAO.read(customer_id);
			if(role.equals("cust")) {
				
				try {
					customer = customerDAO.getCustomerByCustomerId(customer_id);
					request.setAttribute("customer", customer);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return "view-account-details.jsp";
			}
			
			if(role.equals("emp")){
				CustomerBean customer1 = customerDAO.getCustomerByCustomerId(customer_id);
				//CustomerBean[] customers = customerDAO.getCustomers();
				request.setAttribute("customer", customer1);
			
			}
			
			
		} catch (FormBeanException e) {
			e.printStackTrace();
			return "error.jsp";
		} catch (RollbackException e1) {
			e1.printStackTrace();
		}
		return "view-account-details.jsp";
	}

}
