package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.FundPriceHistoryBean;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.Model;
import com.cfs.form.FundForm;

public class FundHistoryAction extends Action {
	private FormBeanFactory<FundForm> formBeanFactory = FormBeanFactory.getInstance(FundForm.class);

	private FundPriceHistoryDAO fundHistDAO;

	public FundHistoryAction(Model model) {
		fundHistDAO = model.getPriceHistoryDAO();
	}

	public String getName() {
		return "view_fund_info.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();

		request.setAttribute("errors", errors);

		try {
			FundForm form = formBeanFactory.create(request);
			int id = form.getIdAsInt();
			FundPriceHistoryBean f = fundHistDAO.read(id);
			request.setAttribute("fundInfoList", f);
			return "fundHistory.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
