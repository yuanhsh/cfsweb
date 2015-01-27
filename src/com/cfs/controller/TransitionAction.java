package com.cfs.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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
			DateFormat format = new SimpleDateFormat("MMDDyyyy");
			Date date= (Date) format.parse(d);
			
			
			
			int fundNum=fundDAO.getCount();
		FundPriceHistoryBean fundPriceHistoryBean[]= new FundPriceHistoryBean[fundNum];
			double numPrice=0;
			int id[] = form.getFundId();
			String price[]=form.getFundPrice(); 
			
			
			for(int i=0;i<fundNum;i++)
			{   
			    fundPriceHistoryBean[i]=new FundPriceHistoryBean();
				fundPriceHistoryBean[i].setFund_id(id[i]);
				fundPriceHistoryBean[i].setPrice((new Double(numPrice*100)).longValue());
				fundPriceHistoryBean[i].setPrice_date(date);
				fundHistoryDAO.createAutoIncrement(fundPriceHistoryBean[i]);
			}
			
			
			
			TransactionBean transcBean[] =transcDAO.match(MatchArg.equals("status","Pending"));
			int len=transcBean.length;
			while(len>0){
				String transcType=transcBean[len].getTransaction_type();
				char first=transcType.charAt(0);
				long shares;
				long sell_amount=0;
				double temp;
				long closingPrice=0;
				
				for(int i=0;i<id.length;i++)	//getting the closing price for the current fund, with fund_id
				{if(id[i]==transcBean[len].getFund_id())
					{temp= Double.parseDouble(price[i])*100;
					 closingPrice=new Double(temp).longValue();	
					 closingPrice=closingPrice/100;}
					}
				switch(first)
				{case 'B':									//Buy Shares
					
						shares=(transcBean[len].getAmount()*1000)/closingPrice; // getting the share price, inaccordance to closing price
						if(shares<1){											//validating share if less than 0.01, then fail transaction
							transcBean[len].setExecute_date(transcBean[len].getExecute_date());
							transcBean[len].setStatus("Failed");
							transcDAO.update(transcBean[len]);
						}else{
					custBean=customerDAO.read(transcBean[len].getCustomer_id());
					custBean.setCash(custBean.getCash()-transcBean[len].getAmount());
					custBean.setLast_trading_day(date);
					customerDAO.update(custBean);
					posBean=posDAO.read(transcBean[len].getCustomer_id());
					posBean.setShares(transcBean[len].getShares()+posBean.getShares());
					transcBean[len].setExecute_date(transcBean[len].getExecute_date());
					transcBean[len].setStatus("Completed");
					transcDAO.update(transcBean[len]);
					break;}
					
				case 'S':									//Sell Shares
					sell_amount=transcBean[len].getShares()*closingPrice*100/1000;
					if(sell_amount<1)
					{
						transcBean[len].setExecute_date(transcBean[len].getExecute_date());
						transcBean[len].setStatus("Failed");
						posBean=posDAO.read(transcBean[len].getCustomer_id());
						posBean.setShares(posBean.getShares()+transcBean[len].getShares()); // reverting shares to customer
						posDAO.update(posBean);
						transcDAO.update(transcBean[len]);
						
					}else{
					custBean=customerDAO.read(transcBean[len].getCustomer_id());
					custBean.setCash(custBean.getCash()+transcBean[len].getAmount());
					PositionBean tempBean[]=posDAO.match(MatchArg.equals("customer_id",transcBean[len].getCustomer_id()));
					boolean isPresent=false;
					for(int i=0;i<tempBean.length;i++)
					{
						if(transcBean[len].getFund_id()==tempBean[i].getFund_id())
						{
							posBean=tempBean[i];
							isPresent=true;
						}						
					}
					
					if(isPresent){
					posBean.setShares(transcBean[len].getShares()-posBean.getShares());
					posBean.setPrice(closingPrice);
					posDAO.update(posBean);
					transcBean[len].setExecute_date(transcBean[len].getExecute_date());
					transcBean[len].setStatus("Completed");
					transcDAO.update(transcBean[len]);}
					else{
						posBean.setCustomer_id(transcBean[len].getCustomer_id());
						posBean.setFund_id(transcBean[len].getFund_id());
						posBean.setShares(transcBean[len].getShares());
						posBean.setPrice(closingPrice);
						posDAO.createAutoIncrement(posBean);
						transcBean[len].setExecute_date(transcBean[len].getExecute_date());
						transcBean[len].setStatus("Completed");
						transcDAO.update(transcBean[len]);
						
					}
					}
					
					break;
				 case 'R':									//Request Check
					 
						
						transcBean[len].setExecute_date(transcBean[len].getExecute_date());
						transcBean[len].setStatus("Completed");
						transcDAO.update(transcBean[len]);
						break;
				 case 'D':									//Deposit Check
					 	custBean=customerDAO.read(transcBean[len].getCustomer_id());
						custBean.setCash(custBean.getCash()+transcBean[len].getAmount());
						
						transcBean[len].setExecute_date(transcBean[len].getExecute_date());
						transcBean[len].setStatus("Completed");
						transcDAO.update(transcBean[len]);
						break;
				default :
					break;
				}
				len--;
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
