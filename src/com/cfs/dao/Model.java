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

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");

			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);
			customerDAO = new CustomerDAO("customers", pool);
			employeeDAO = new EmployeeDAO("employees", pool);
			fundDAO = new FundDAO("funds", pool);

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

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public CustomerDAO getCustomerDAO() {
		return this.customerDAO;
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
