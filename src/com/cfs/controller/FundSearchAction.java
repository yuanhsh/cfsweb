package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.FundBean;
import com.cfs.dao.EmployeeDAO;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;
import com.cfs.form.CreateEmployeeAccountForm;
import com.cfs.form.CreateFundForm;
import com.cfs.form.SearchFundForm;

public class FundSearchAction extends Action {

private FundDAO fundDAO;
private FormBeanFactory<SearchFundForm> formBeanFactory = FormBeanFactory.getInstance(SearchFundForm.class);

	public FundSearchAction(Model model) {
		
		fundDAO= model.getFundDAO();
}
	
	public String getName() {return "search_fund.do";}
	
	public String perform (HttpServletRequest request) {
		
		List<String> errors = new ArrayList<String>();
		
		request.setAttribute("errors",errors);
		
		try{
			SearchFundForm form = formBeanFactory.create(request);
		
			request.setAttribute("form",form);
			
			FundBean[] fundList= fundDAO.getFunds();
			request.setAttribute("fundList",fundList);
			
			FundBean searchfund= new FundBean();
			searchfund.setName(form.getName());
			searchfund.setSymbol(form.getSymbol());
			
			request.setAttribute("searchfund", searchfund);
			
			HttpSession session = request.getSession();
			session.setAttribute("searchname", searchfund.getName());
			return"FundSearch.jsp";
		}catch(RollbackException | FormBeanException e){
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
