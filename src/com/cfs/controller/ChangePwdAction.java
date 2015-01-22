package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.EmployeeBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.Model;
import com.cfs.form.ChangePwdForm;

public class ChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> fbFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;

	public ChangePwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "change-pwd.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			ChangePwdForm form = fbFactory.create(request);

			if (!form.isPresent()) {
				return "change-pwd.jsp";
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "change-pwd.jsp";
			}

			CustomerBean customerBean = null;
			EmployeeBean employeeBean = null;
			String loginAs = (String) request.getSession().getAttribute("loginAs");
			if (loginAs.equals("cust")) {
				customerBean = (CustomerBean) request.getSession().getAttribute("user");
				if (customerBean.checkPassword(form.getOldPassword())) {
					customerDAO.setPassword(customerBean.getCustomer_id(), form.getNewPassword());
					request.setAttribute("message", "Password changed for " + customerBean.getUsername());
					return "success.jsp";
				} else {
					errors.add("Old password is incorrect");
					return "change-pwd.jsp";
				}
			}
			
			if (loginAs.equals("emp")) {
				employeeBean = (EmployeeBean) request.getSession().getAttribute("user");
				if (employeeBean.checkPassword(form.getOldPassword())) {
					employeeDAO.setPassword(employeeBean.getUsername(),form.getNewPassword());
					request.setAttribute("message", "Password changed for "+ employeeBean.getUsername());
				} else {
					errors.add("Old password is incorrect");
					return "change-pwd.jsp";
				}
				
				return "success.jsp";
			}
			return "change-pwd.jsp";

		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
