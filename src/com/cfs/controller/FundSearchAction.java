package com.cfs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import com.cfs.bean.FundBean;
import com.cfs.dao.FundDAO;
import com.cfs.dao.Model;

public class FundSearchAction extends Action {

private FundDAO fundDAO;
	
	public FundSearchAction(Model model) {
		
		fundDAO= model.getFundDAO();
}
	
	public String getName() {return "search_fund.do";}
	
	public String perform (HttpServletRequest request){
		
		List<String> errors = new ArrayList<String>();
		
		request.setAttribute("errors",errors);
		
		try{
			FundBean[] fundList= fundDAO.getFunds();
			request.setAttribute("fundList",fundList);
			return"FundSearch.jsp";
		}catch(RollbackException e){
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
