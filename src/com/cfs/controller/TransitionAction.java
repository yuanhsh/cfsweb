package com.cfs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import com.cfs.bean.CustomerBean;
import com.cfs.bean.FundBean;
import com.cfs.bean.FundPriceHistoryBean;
import com.cfs.bean.PositionBean;
import com.cfs.bean.TransactionBean;
import com.cfs.dao.CustomerDAO;
import com.cfs.dao.FundDAO;
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
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundHistoryDAO;
	private CustomerBean custBean;
	private PositionBean posBean;

	public TransitionAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transcDAO = model.getTransactionDAO();
		posDAO= model.getPositionDAO();
		fundHistoryDAO=model.getPriceHistoryDAO();
		fundDAO=model.getFundDAO();
	}

	public String getName() {

		return "emp_transition_day.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		 List<String> errors = new ArrayList<String>();
	        request.setAttribute("errors",errors);
		try{
			TransitionForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				return "FundSearch.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "FundSearch.jsp";
			}
			
			String d= form.getDate();
			TransactionBean tempTranscBean[]=transcDAO.match(MatchArg.equals("status","Completed"));
			int lastCompletedTransc=tempTranscBean.length-1;
			java.sql.Date lastTradingDay=tempTranscBean[lastCompletedTransc].getExecute_date();
			java.util.Date utilDate = new SimpleDateFormat("MM-dd-yyyy").parse(d);
			java.sql.Date date = new java.sql.Date(utilDate.getTime());	
			if(date.before(lastTradingDay)){
				errors.add("Date entered cannot be from past");
				return "error.jsp";}
			
			
			FundBean fundBean[]=fundDAO.getFunds(null);
			
			int fundNum=fundBean.length;
		FundPriceHistoryBean fundPriceHistoryBean[]= new FundPriceHistoryBean[fundNum];
		
			double numPrice=0;
			String id[] = form.getFundId();
			String price[]=form.getFundPrice(); 
			
			
			for(int i=0;i<fundNum;i++)
			{  numPrice=Double.parseDouble(price[i]);
				if(numPrice<0.01)
				{ errors.add("Fund price entered should be greater than 0.01");	
					return "error.jsp";}
				
				fundBean[i].setPrice(((new Double(numPrice*100)).longValue()));
				fundDAO.update(fundBean[i]);
			    fundPriceHistoryBean[i]=new FundPriceHistoryBean();
				fundPriceHistoryBean[i].setFund_id(Integer.parseInt(id[i]));
				fundPriceHistoryBean[i].setPrice((new Double(numPrice*100)).longValue());
				fundPriceHistoryBean[i].setPrice_date(date);
				fundHistoryDAO.createAutoIncrement(fundPriceHistoryBean[i]);
			}
			
			
			
			TransactionBean transcBean[] =transcDAO.match(MatchArg.equals("status","Pending"));
			int len=transcBean.length;
			for (int k=0;k<len;k++){
				String transcType=transcBean[k].getTransaction_type();
				char first=transcType.charAt(0);
				long shares;
				long sell_amount=0;
				double temp;
				long closingPrice=0;
				
				for(int i=0;i<id.length;i++)	//getting the closing price for the current fund, with fund_id
				{if(Integer.parseInt(id[i])==transcBean[k].getFund_id())
					{temp= Double.parseDouble(price[i])*100;
					 closingPrice=new Double(temp).longValue();	
					 closingPrice=closingPrice/100;
					 break;
					 }
					}
				switch(first)
				{case 'B':									//Buy Shares
					
						shares=(transcBean[k].getAmount()*1000)/closingPrice; // getting the share price, inaccordance to closing price
						if(shares<1){											//validating share if less than 0.01, then fail transaction
							transcBean[k].setExecute_date(transcBean[k].getExecute_date());
							transcBean[k].setStatus("Failed");
							transcDAO.update(transcBean[k]);
						}else{
					custBean=customerDAO.read(transcBean[k].getCustomer_id());
					custBean.setCash(custBean.getCash()-transcBean[k].getAmount());
					custBean.setLast_trading_day(date);
					customerDAO.update(custBean);
					posBean=posDAO.getCustomerFundPosition(transcBean[k].getCustomer_id(), transcBean[k].getFund_id());
					PositionBean tempBean= new PositionBean();
					if(posBean==null)
					{	tempBean.setCustomer_id(transcBean[k].getCustomer_id());
						tempBean.setFund_id(transcBean[k].getFund_id());
						tempBean.setPrice(closingPrice);
						tempBean.setShares(transcBean[k].getShares());
						posDAO.createAutoIncrement(tempBean);
							}
					else{
					posBean.setShares(transcBean[k].getShares()+posBean.getShares());}
					transcBean[k].setExecute_date(transcBean[k].getExecute_date());
					transcBean[k].setStatus("Completed");
					transcDAO.update(transcBean[k]);
					
					
						}break;
					
				case 'S':									//Sell Shares
					sell_amount=transcBean[k].getShares()*closingPrice*100/1000;
					if(sell_amount<1)
					{
						transcBean[k].setExecute_date(transcBean[k].getExecute_date());
						transcBean[k].setStatus("Failed");
						posBean=posDAO.getCustomerFundPosition(transcBean[k].getCustomer_id(), transcBean[k].getFund_id());
						posBean.setShares(posBean.getShares()+transcBean[k].getShares()); // reverting shares to customer
						posDAO.update(posBean);
						transcDAO.update(transcBean[k]);
						
					}else{
					custBean=customerDAO.read(transcBean[k].getCustomer_id());
					custBean.setCash(custBean.getCash()+transcBean[k].getAmount());
					custBean.setLast_trading_day(date);
					customerDAO.update(custBean);
					PositionBean tempBean[]=posDAO.match(MatchArg.equals("customer_id",transcBean[k].getCustomer_id()));
					boolean isPresent=false;
					for(int i=0;i<tempBean.length;i++)
					{
						if(transcBean[k].getFund_id()==tempBean[i].getFund_id())
						{
							posBean=tempBean[i];
							isPresent=true;
						}						
					}
					
					if(isPresent){
					posBean.setShares(transcBean[k].getShares()-posBean.getShares());
					posBean.setPrice(closingPrice);
					posDAO.update(posBean);
					transcBean[k].setExecute_date(transcBean[k].getExecute_date());
					transcBean[k].setStatus("Completed");
					transcDAO.update(transcBean[k]);
					break;}
					else{
						posBean.setCustomer_id(transcBean[k].getCustomer_id());
						posBean.setFund_id(transcBean[k].getFund_id());
						posBean.setShares(transcBean[k].getShares());
						posBean.setPrice(closingPrice);
						posDAO.createAutoIncrement(posBean);
						transcBean[k].setExecute_date(transcBean[k].getExecute_date());
						transcBean[k].setStatus("Completed");
						transcDAO.update(transcBean[k]);
						break;
						
					}
					}
					
					
				 case 'R':									//Request Check
					 
						
						transcBean[k].setExecute_date(transcBean[k].getExecute_date());
						transcBean[k].setStatus("Completed");
						transcDAO.update(transcBean[k]);
						break;
				 case 'D':									//Deposit Check
					 	custBean=customerDAO.read(transcBean[k].getCustomer_id());
						custBean.setCash(custBean.getCash()+transcBean[k].getAmount());
						customerDAO.update(custBean);
						transcBean[k].setExecute_date(transcBean[k].getExecute_date());
						transcBean[k].setStatus("Completed");
						transcDAO.update(transcBean[k]);
						break;
				default :
					break;
				}
				
			}
			
			
		
		
			return "FundSearch.jsp";
		}  catch(RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}catch(FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (ParseException e) {
				return"error.jsp";
		
		}
	}
	}


