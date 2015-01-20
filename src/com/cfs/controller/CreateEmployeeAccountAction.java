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


public class CreateEmployeeAccountAction extends Action {
	private FormBeanFactory<CreateEmployeeAccountForm> formBeanFactory = FormBeanFactory.getInstance(CreateEmployeeAccountForm.class);
	private EmployeeDAO employeeDAO;
	
	public CreateEmployeeAccountAction(Model model){
		employeeDAO = model.getEmployeeDAO();
	}
	
	public String getName() { return "createEmployeeAccount.do"; }
	
	public String perform(HttpServletRequest request){
		 List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
	        
	        
	        
	       
			try {
				CreateEmployeeAccountForm form = formBeanFactory.create(request);
				request.setAttribute("form",form);
			
				
				if (!form.isPresent()) {
		            return "create-employee-account.jsp";
		        }
				
				errors.addAll(form.getValidationErrors());
				
				EmployeeBean employee = employeeDAO.read(form.getUserName());
				if(employee !=null){
					errors.add("This user name is already exist.");
				}
				
				if (errors.size() == 0) {
					errors.add("You create an employee account success!");
				}
				
				
				employee = new EmployeeBean();
				employee.setUsername(form.getUserName());
				employee.setFirstname(form.getFirstName());
				employee.setLastname(form.getLastName());
				employee.setPassword(form.getPassword());
				employeeDAO.create(employee);
	        
				// Attach (this copy of) the user bean to the session
		        HttpSession session = request.getSession(false);
		        session.setAttribute("employee",employee);
		        
				return "createEmployeeAccount.do";
				
				
			} catch (FormBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "create-employee-account.jsp";
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "create-employee-account.jsp";
			}	

	}

}
