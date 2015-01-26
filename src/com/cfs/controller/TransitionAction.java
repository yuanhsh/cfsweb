package com.cfs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.FundPriceHistoryBean;
import com.cfs.bean.PositionBean;
import com.cfs.bean.TransactionBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundPriceHistoryDAO;
import com.cfs.dao.Model;
import com.cfs.dao.PositionDAO;
import com.cfs.dao.TransactionDAO;
import com.cfs.form.TransitionForm;




public class TransitionAction extends Action {
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory.getInstance(TransitionForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transcDAO;
	private PositionDAO posDAO;
	private FundPriceHistoryDAO fundHistoryDAO;
	private CustomerBean custBean;
	private PositionBean posBean;

	public TransitionAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transcDAO = model.getTransactionDAO();
		posDAO= model.getPositionDAO();
		fundHistoryDAO=model.getPriceHistoryDAO();
	}

	public String getName() {

		return "transition_day.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		 List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
		try{
			TransitionForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			

			String d= form.getDate();
			DateFormat format = new SimpleDateFormat("MMDDyyyy");
			Date date= format.parse(d);
			
			
			
			int fundNum=fundHistoryDAO.getCount();
		FundPriceHistoryBean fundPriceHistoryBean[]= new FundPriceHistoryBean[fundNum];
			double numPrice=0;
			int id[] = form.getFundId();
			
			for(int i=0;i<fundNum;i++)
			{   
			    fundPriceHistoryBean[i]=new FundPriceHistoryBean();
				fundPriceHistoryBean[i].setFund_id(id[i]);
				fundPriceHistoryBean[i].setPrice((new Double(numPrice*100)).longValue());
				fundPriceHistoryBean[i].setPrice_date(date);
				fundHistoryDAO.createAutoIncrement(fundPriceHistoryBean[i]);
			}
			
			
			
			TransactionBean transcBean[] =transcDAO.match(MatchArg.equals("status","Complete"));
			int len=transcBean.length;
			while(len>0){
				String transcType=transcBean[len].getTransaction_type();
				char first=transcType.charAt(0);
				
				switch(first)
				{case 'B':									//Buy Shares
						if(transcBean[len].getShares()<0.001){
							transcBean[len].setExecute_date(transcBean[len].getExecute_date());
							transcBean[len].setStatus("Failed");
							transcDAO.update(transcBean[len]);
						}else{
					custBean=customerDAO.read(transcBean[len].getCustomer_id());
					custBean.setCash(custBean.getCash()-transcBean[len].getAmount());
					posBean=posDAO.read(transcBean[len].getCustomer_id());
					posBean.setShares(transcBean[len].getShares()+posBean.getShares());
					transcBean[len].setExecute_date(transcBean[len].getExecute_date());
					transcBean[len].setStatus("Complete");
					transcDAO.update(transcBean[len]);
					break;}
					
				case 'S':									//Sell Shares
					custBean=customerDAO.read(transcBean[len].getCustomer_id());
					custBean.setCash(custBean.getCash()+transcBean[len].getAmount());
					posBean=posDAO.read(transcBean[len].getCustomer_id());
					posBean.setShares(transcBean[len].getShares()-posBean.getShares());
					transcBean[len].setExecute_date(transcBean[len].getExecute_date());
					transcBean[len].setStatus("Complete");
					transcDAO.update(transcBean[len]);
					
					break;
				 case 'R':									//Request Check
					 	custBean=customerDAO.read(transcBean[len].getCustomer_id());
						custBean.setCash(custBean.getCash()-transcBean[len].getAmount());
						
						transcBean[len].setExecute_date(transcBean[len].getExecute_date());
						transcBean[len].setStatus("Complete");
						transcDAO.update(transcBean[len]);
						break;
				 case 'D':									//Deposit Check
					 	custBean=customerDAO.read(transcBean[len].getCustomer_id());
						custBean.setCash(custBean.getCash()+transcBean[len].getAmount());
						
						transcBean[len].setExecute_date(transcBean[len].getExecute_date());
						transcBean[len].setStatus("Complete");
						transcDAO.update(transcBean[len]);
						break;
				default :
					break;
				}
				len--;
			}
			
			
		
		
			return "transition_day.do";
		}  catch(RollbackException e) {
			errors.add(e.getMessage());
			return "transitionDay.jsp";
		}catch(FormBeanException e) {
			errors.add(e.getMessage());
			return "transitionDay.jsp";
		} catch (ParseException e) {
				return"transitionDay.jsp";
		}
	}

}
