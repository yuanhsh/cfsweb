package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
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
	
	public String getName() { return "emp_create_emp_account.do"; }
	
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
				Transaction.begin();
				EmployeeBean employee = employeeDAO.getEmployeeByUsername(form.getUserName());
				if(employee !=null){
					errors.add("Username already exists. Use a different username.");
				}
				
				if (errors.size() != 0) {
					Transaction.commit();
					return "create-employee-account.jsp";
				}
				
				employee = new EmployeeBean();
				employee.setUsername(form.getUserName());
				employee.setFirstname(form.getFirstName());
				employee.setLastname(form.getLastName());
				employee.setPassword(form.getPassword());
				employeeDAO.createAutoIncrement(employee);
				Transaction.commit();
		        //HttpSession session = request.getSession(false);
		        //session.setAttribute("employee",employee);
		        request.setAttribute("message", "Account for employee " + employee.getUsername() + " created successfully.");
		        return "create-employee-account.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				if(Transaction.isActive()) {
					Transaction.rollback();
				}
				return "create-employee-account.jsp";
			}	
	}
}
