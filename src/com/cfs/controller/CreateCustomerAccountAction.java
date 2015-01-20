package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.Model;
import com.cfs.form.CreateCustomerAccountForm;
import com.cfs.form.CreateEmployeeAccountForm;

public class CreateCustomerAccountAction extends Action {
	private FormBeanFactory<CreateCustomerAccountForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerAccountForm.class);
	private CustomerDAO customerDAO;
	
	public CreateCustomerAccountAction(Model model){
		customerDAO=model.getCustomerDAO();
		
	}
	
	public String getName() { return "createCustomerAccount.do"; }
	
	public String perform(HttpServletRequest request){
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
			CreateCustomerAccountForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			if (!form.isPresent()) {
	            return "CreateCustomerAccount.jsp";
	        }
			errors.addAll(form.getValidationErrors());
			CustomerBean customer= customerDAO.read(form.getUserName());
			if(customer !=null){
				errors.add("this user name is already exist.");
			}
			if (errors.size() == 0) {
				errors.add("You create an employee account success!");
			}
			
			customer=new CustomerBean();
			customer.setUsername(form.getUserName());
			customer.setPassword(form.getPassword());
			customer.setFirstname(form.getFirstName());
			customer.setLastname(form.getLastName());
			customer.setAddr_line1(form.getAddressl1());
			customer.setAddr_line2(form.getAddressl2());
			customer.setCity(form.getCity());
			customer.setZip(form.getZip());
			customer.setState(form.getState());
			customerDAO.create(customer);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("customer", customer);
			
			return "createCustomerAccount.do"; 		
			 
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "CreateCustomerAccount.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "CreateCustomerAccount.jsp";
		}
		
	}
	
}
