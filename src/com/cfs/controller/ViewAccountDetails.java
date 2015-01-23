package com.cfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.dao.ProtfolioDAO;
import com.cfs.dto.ProtfolioDTO;
import com.cfs.form.CustomerForm;
import com.cfs.form.ProtfolioForm;
import com.cfs.form.SearchCustomerForm;

public class ViewAccountDetails extends Action {
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory.getInstance(CustomerForm.class);
	private CustomerDAO customerDAO;
	public ViewAccountDetails(Model model) {
		 customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		return "view-account-details.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		try {
			CustomerForm form = formBeanFactory.create(request);
			/*List<String> errors = form.getValidationErrors(request);
			if(errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "error.jsp";
			}*/
			//List<ProtfolioDTO> funds = this.protfolioDAO.getProtfolio(form.getCustomerIdNumber());
			//request.setAttribute("funds", funds);
			String role = (String)request.getSession().getAttribute("loginAs");
			if(role.equals("cust")) {
				int customer_id = (int)request.getSession().getAttribute("customer_id");
				//CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
				CustomerBean customer;
				try {
					customer = customerDAO.getCustomerByCustomerId(customer_id);
					request.setAttribute("customer", customer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			return "view-account-details.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
			return "error.jsp";
		}
	}

}
