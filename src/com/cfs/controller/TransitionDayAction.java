package com.cfs.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
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
import com.cfs.form.TransitionDayForm;
import com.cfs.form.TransitionForm;
import com.cfs.form.ViewPriceHistoryForm;
import com.google.gson.Gson;

public class TransitionDayAction extends Action {
	private FormBeanFactory<TransitionDayForm> fbFactory = FormBeanFactory.getInstance(TransitionDayForm.class);
	private CustomerDAO customerDAO;
	private TransactionDAO transDAO;
	private PositionDAO posDAO;
	private FundPriceHistoryDAO priceDAO;
	private FundDAO fundDAO;

	public TransitionDayAction(Model model) {
		priceDAO = model.getPriceHistoryDAO();
		customerDAO = model.getCustomerDAO();
		transDAO = model.getTransactionDAO();
		posDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "emp_ajax_transition_day.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		return null;
	}

	@Override
	public void performAjax(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "false");
		try {
			TransitionDayForm form = fbFactory.create(request);
			List<String> errors = form.getValidationErrors();
			if (errors != null && errors.size() != 0) {
				request.setAttribute("errors", errors);
				map.put("error", errors.get(0));
			} else {
				Map<Integer, Long> priceTable = form.getFundPriceTable();
				Date date = form.getExecuteDate();
//				if(this.priceDAO.hasPriceDateGE(date)) {
//					map.put("success", "false");
//					map.put("error", "Transition date must be a larger value.");
//				} else {
//					this.transitionDay(priceTable, date, map);
//					map.put("success", "true");
//					map.put("info", "Transition day operation has been executed. Reloading page...");
//				}
				this.transitionDay(priceTable, date, map);
			}
		} catch (Exception e) {
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
			e.printStackTrace();
			map.put("success", "false");
			map.put("error", e.getMessage());
		}
		
		try {
			String json = new Gson().toJson(map);
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void transitionDay(Map<Integer, Long> priceTable, Date date, Map<String, String> msg) throws RollbackException {
		try {
			Transaction.begin();
			if(this.priceDAO.hasPriceDateGE(date)) {
				msg.put("success", "false");
				msg.put("error", "Transition date must be a larger value.");
				Transaction.commit();
				return;
			}
			TransactionBean[] trans = this.transDAO.getPendingTransactions();
			for(TransactionBean tran:trans) {
				String action = tran.getTransaction_type();
				tran.setExecute_date(date);
				tran.setStatus(TransactionBean.STATUS_COMPLETED);
				if(action.equals(TransactionBean.TYPE_REQUEST)) {
					this.requestCheck(tran);
				} else if(action.equals(TransactionBean.TYPE_DEPOSIT)) {
					this.depositCheck(tran);
				} else if(action.equals(TransactionBean.TYPE_BUY)) {
					this.buyFund(tran, priceTable, date);
				} else if(action.equals(TransactionBean.TYPE_SELL)) {
					this.sellFund(tran, priceTable, date);
				}
			}
			this.updateFundPrices(priceTable, date);
			Transaction.commit();
		} catch (RollbackException e) {
			if(Transaction.isActive()) {
				Transaction.rollback();
			}
			e.printStackTrace();
			throw e;
		}
		msg.put("success", "true");
		msg.put("info", "Transition day operation has been executed. Reloading page...");
	}
	
	private void requestCheck(TransactionBean tran) throws RollbackException {
		this.transDAO.update(tran);
	}
	
	private void depositCheck(TransactionBean tran) throws RollbackException {
		this.transDAO.update(tran);
		CustomerBean cus = this.customerDAO.read(tran.getCustomer_id());
		cus.setCash(cus.getCash()+tran.getAmount());
		customerDAO.update(cus);
	}
	
	private void buyFund(TransactionBean tran, Map<Integer, Long> priceTable, Date date) throws RollbackException {
		long shares = tran.getAmount() * 1000 / priceTable.get(tran.getFund_id());
		int customer_id = tran.getCustomer_id();
		int fund_id = tran.getFund_id();
		long price = priceTable.get(tran.getFund_id());
		tran.setPrice(price);
		tran.setShares(shares);
		CustomerBean cust = this.customerDAO.read(customer_id);
		if(shares < 1) {
			tran.setStatus(TransactionBean.STATUS_CANCELLED);
			cust.setCash(cust.getCash()+tran.getAmount());
		} else {
			PositionBean position = this.posDAO.getCustomerFundPosition(customer_id, fund_id);
			if(position != null) {
				position.setShares(position.getShares()+shares);
				this.posDAO.update(position);
			} else {
				position = new PositionBean();
				position.setCustomer_id(customer_id);
				position.setFund_id(fund_id);
				position.setShares(shares);
				position.setPrice(priceTable.get(tran.getFund_id()));
				this.posDAO.createAutoIncrement(position);
			}
		}
		cust.setLast_trading_day(date);
		this.customerDAO.update(cust);
		this.transDAO.update(tran);
	}
	
	private void sellFund(TransactionBean tran, Map<Integer, Long> priceTable, Date date) throws RollbackException {
		long closingPrice = priceTable.get(tran.getFund_id());
		long sellAmount = tran.getShares() * closingPrice / 1000;
		int customer_id = tran.getCustomer_id();
		int fund_id = tran.getFund_id();
		tran.setPrice(closingPrice);
		tran.setAmount(sellAmount);
		CustomerBean cust = this.customerDAO.read(customer_id);
		if(sellAmount < 1) {
			tran.setStatus(TransactionBean.STATUS_CANCELLED);
			PositionBean position = this.posDAO.getCustomerFundPosition(customer_id, fund_id);
			if(position != null) {
				position.setShares(position.getShares()+tran.getShares());
				this.posDAO.update(position);
			} else {
				position = new PositionBean();
				position.setCustomer_id(customer_id);
				position.setFund_id(fund_id);
				position.setShares(tran.getShares());
				position.setPrice(priceTable.get(tran.getFund_id()));
				this.posDAO.createAutoIncrement(position);
			}
		} else {
			cust.setCash(cust.getCash()+sellAmount);
		}
		cust.setLast_trading_day(date);
		this.customerDAO.update(cust);
		this.transDAO.update(tran);
	}
	
	private void updateFundPrices(Map<Integer, Long> priceTable, Date date) throws RollbackException {
		FundBean[] funds = this.fundDAO.match();
		for(FundBean fund:funds) {
			long closingPrice = priceTable.get(fund.getFund_id());
			fund.setPrice(closingPrice);
			this.fundDAO.update(fund);
			
			FundPriceHistoryBean priceHistory = new FundPriceHistoryBean();
			priceHistory.setFund_id(fund.getFund_id());
			priceHistory.setPrice(closingPrice);
			priceHistory.setPrice_date(date);
			this.priceDAO.createAutoIncrement(priceHistory);
		}
	}

}
