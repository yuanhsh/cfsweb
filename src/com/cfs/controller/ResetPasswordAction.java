package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.form.ResetPasswordForm;

public class ResetPasswordAction extends Action {
	private FormBeanFactory<ResetPasswordForm> formBeanFactory = FormBeanFactory.getInstance(ResetPasswordForm.class);
	private CustomerDAO customerDAO;

	public ResetPasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "emp_reset_password.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ResetPasswordForm form = formBeanFactory.create(request);
			if (request.getMethod().equals("POST")) {
				if (!form.isPresent()) {
					return "reset_password.jsp";
				}
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "reset_password.jsp";
				}
				int c_id = Integer.parseInt((String) request.getSession()
						.getAttribute("customer_id"));
				customerDAO.setPassword(c_id, form.getNewPassword());
				request.setAttribute("message", "Password has been reset for " + c_id);
				return "reset_password.jsp";
			} 
				request.getSession().setAttribute("customer_id", request.getParameter("customer_id"));
				return "reset_password.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
			return "reset_password.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
			return "reset_password.jsp";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "reset_password.jsp";
		}

	}

}
