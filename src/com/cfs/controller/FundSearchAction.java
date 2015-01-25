package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.FundBean;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.form.SearchFundForm;

public class FundSearchAction extends Action {

	private FundDAO fundDAO;
	private FormBeanFactory<SearchFundForm> formBeanFactory = FormBeanFactory.getInstance(SearchFundForm.class);

	public FundSearchAction(Model model) {
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "search_fund.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		try {
			SearchFundForm form = formBeanFactory.create(request);
			request.setAttribute("fund_key", form.getFund_key());
			String keyword = null;
			errors = form.getValidationErrors();
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
			} else {
				keyword = form.getFund_key();
			}
			FundBean[] fundList = fundDAO.getFunds(keyword);
			request.setAttribute("fundList", fundList);
			return "FundSearch.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
