package com.cfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.Model;
import com.cfs.dao.ProtfolioDAO;
import com.cfs.dto.ProtfolioDTO;
import com.cfs.form.ProtfolioForm;

public class ViewProtfolioAction extends Action {
	private FormBeanFactory<ProtfolioForm> formBeanFactory = FormBeanFactory.getInstance(ProtfolioForm.class);
	private ProtfolioDAO protfolioDAO;
	private CustomerDAO customerDAO;

	public ViewProtfolioAction(Model model) {
		protfolioDAO = model.getProtfolioDAO();
		customerDAO = model.getCustomerDAO();
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
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				return "error.jsp";
			}
			List<ProtfolioDTO> funds = this.protfolioDAO.getProtfolio(form.getCustomerIdNumber());
			request.setAttribute("funds", funds);
			HttpSession session = request.getSession();
			String role = (String) session.getAttribute("loginAs");
			if (role.equals("cust")) {
				Integer customer_id = (Integer) session.getAttribute("customer_id");
				CustomerBean customer = customerDAO.read(customer_id);
				session.setAttribute("customer", customer);
				double balance = ((double) customer.getCash()) / 100.0;
				request.setAttribute("balance", ProtfolioDTO.moneyFomatter.format(balance));
			}
			return "protfolio.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "error.jsp";
		}
	}
}
