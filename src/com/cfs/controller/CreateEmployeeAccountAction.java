package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.EmployeeBean;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.Model;
import com.cfs.form.CreateEmployeeAccountForm;


public class CreateEmployeeAccountAction {
	private FormBeanFactory<CreateEmployeeAccountForm> formBeanFactory = FormBeanFactory.getInstance(CreateEmployeeAccountForm.class);
	private EmployeeDAO employeedao;
	
	public CreateEmployeeAccountAction(Model model){
		employeedao = model.getEmployeeDAO();
	}
	
	public String getName() { return "createEmployeeAccount.do"; }
	
	public String perform(HttpServletRequest request){
		 List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
	        
	        
	        
	        CreateEmployeeAccountForm form;
			try {
				form = formBeanFactory.create(request);
				request.setAttribute("form",form);
				
				if (!form.isPresent()) {
		            return "CreateEmployeeAccountAction.jsp";
		        }
				
				errors.addAll(form.getValidationErrors());
				
				EmployeeBean employee = employeedao.read(form.getUserName());
				if(employee !=null){
					errors.add("Employee's user name is already exist.");
				}
				
				if (errors.size() == 0) {
					errors.add("You create an employee account success!");
					return "CreateEmployeeAccountAction.jsp";
				}
				
				
				employee = new EmployeeBean();
				employee.setUsername(form.getUserName());
				employee.setFirstname(form.getFirstName());
				employee.setLastname(form.getLastName());
				employee.setPassword(form.getPassword());
				employeedao.create(employee);
	        
				// Attach (this copy of) the user bean to the session
		        HttpSession session = request.getSession(false);
		        session.setAttribute("employee",employee);
		        
				return "createEmployeeAccount.do";
				
				
			} catch (FormBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "CreateEmployeeAccountAction.jsp";
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "CreateEmployeeAccountAction.jsp";
			}			
	}

}
