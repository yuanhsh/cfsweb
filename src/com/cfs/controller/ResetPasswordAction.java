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
import com.cfs.form.ResetPasswordForm;



public class ResetPasswordAction extends Action {
	private FormBeanFactory<ResetPasswordForm> formBeanFactory = FormBeanFactory.getInstance(ResetPasswordForm.class);
	private CustomerDAO customerDAO;
	
	public ResetPasswordAction(Model model){
		customerDAO = model.getCustomerDAO();
	}
	@Override
	public String getName() {
		
		return "reset_password.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        
		try {
			ResetPasswordForm form = formBeanFactory.create(request);
			if (!form.isPresent()) {
	            return "resetpassword.jsp";
	        }
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "resetpassword.jsp";
	        }
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() == 0) {
	            errors.add("Reset password success!");
	        }
	        
	        CustomerBean customer =(CustomerBean)request.getSession().getAttribute("customer");
	        customerDAO.setPassword(customer.getCustomer_id(), form.getNewPassword());
	        request.setAttribute("message", "Password reset for "+customer.getCustomer_id());
	        return "success.jsp";
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
	}

}
