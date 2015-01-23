package com.cfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.Model;
import com.cfs.dao.ProtfolioDAO;
import com.cfs.dto.ProtfolioDTO;
import com.cfs.form.ProtfolioForm;

public class ViewProtfolioAction extends Action {
	private FormBeanFactory<ProtfolioForm> formBeanFactory = FormBeanFactory.getInstance(ProtfolioForm.class);
	private ProtfolioDAO protfolioDAO;
	public ViewProtfolioAction(Model model) {
		protfolioDAO = model.getProtfolioDAO();
	}
	
	@Override
	public String getName() {
		return "view_protfolio.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		try {
			ProtfolioForm form = formBeanFactory.create(request);
			List<String> errors = form.getValidationErrors(request);
			if(errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "error.jsp";
			}
			List<ProtfolioDTO> funds = this.protfolioDAO.getProtfolio(form.getCustomerIdNumber());
			request.setAttribute("funds", funds);
			String role = (String)request.getSession().getAttribute("loginAs");
			if(role.equals("cust")) {
				CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
				double balance = ((double)customer.getCash())/100.0;
				request.setAttribute("balance", ProtfolioDTO.moneyFomatter.format(balance));
			}
			return "protfolio.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
			return "error.jsp";
		}
	}

}
