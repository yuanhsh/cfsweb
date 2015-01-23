package com.cfs.dao;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.cfs.bean.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	public EmployeeDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(EmployeeBean.class, tableName, pool);
	}
	
	public EmployeeBean[] getEmployees() throws RollbackException {
		EmployeeBean[] employee = match();
		return employee;
	}
	
	public EmployeeBean getEmployeeByUsername(String username) throws RollbackException {
		//EmployeeBean[] employee = match();
		//System.out.println(employee[0].getEmployee_id() + " cId1 ");
		EmployeeBean[] employee1 = match(MatchArg.equals("username", username));
		if (employee1 ==null || employee1.length<1) {
			return null;
		}
		//System.out.println(employee1[0].getEmployee_id() + " cId2 ");
		return employee1[0];
	}

	public int getEmployee_Id(String username) throws RollbackException {
		EmployeeBean[] employee1 = match(MatchArg.equals("username", username));
		if(employee1!=null && employee1.length>0){
		return employee1[0].getEmployee_id();
		}else{
			return 0;
		}
	}
	

	public EmployeeBean getEmployee(String username, String password) throws RollbackException {
		EmployeeBean[] employee1 = match(MatchArg.equals("username", username));
		if(employee1!=null && employee1.length>0){
			EmployeeBean emp = employee1[0];
			if(emp.checkPassword(password)) return emp;
			return null;
		}else{
			return null;
		}
	}
	
	public void create(EmployeeBean employee) throws RollbackException {

		try {
			Transaction.begin();
			createAutoIncrement(employee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}
	

	public void setPassword(String username, String password) throws RollbackException {
		try {
			Transaction.begin();
			EmployeeBean dbEmployee = read(username);
			if (dbEmployee == null) {
				throw new RollbackException("User " + username + " no longer exists");
			}

			dbEmployee.setPassword(password);

			update(dbEmployee);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}

/*
import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import com.cfs.bean.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	public EmployeeDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(EmployeeBean.class, tableName, pool);
	}
	
}*/