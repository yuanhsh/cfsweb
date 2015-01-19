package com.cfs.dao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.cfs.bean.EmployeeBean;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO priceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			String username = config.getInitParameter("username");
			String password  = config.getInitParameter("password");

			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL, username, password);
			customerDAO = new CustomerDAO("customer", pool);
			employeeDAO = new EmployeeDAO("employee", pool);
			fundDAO = new FundDAO("fund", pool);
			priceHistoryDAO = new FundPriceHistoryDAO("fund_price_history", pool);
			positionDAO = new PositionDAO("position", pool);
			transactionDAO = new TransactionDAO("transaction", pool);

			if (employeeDAO.getCount() == 0) {
				createDefaultUser();
			}

		} catch (DAOException e) {
			throw new ServletException(e);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public FundPriceHistoryDAO getPriceHistoryDAO() {
		return priceHistoryDAO;
	}

	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void createDefaultUser() throws RollbackException {
		// create the admin login
		EmployeeBean e1 = new EmployeeBean();
		e1.setUsername("admin");
		e1.setPassword("admin");
		e1.setFirstname("admin");
		e1.setLastname("admin");
		employeeDAO.createAutoIncrement(e1);
	}
}
