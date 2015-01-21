package com.cfs.dao;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.bean.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	public EmployeeDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(EmployeeBean.class, tableName, pool);
	}
	
	public EmployeeBean[] getEmployees() throws RollbackException {
		EmployeeBean[] employee = match();
		return employee;
	}
	
	public EmployeeBean[] getEmployeeByUsername(String username) throws RollbackException {
		//EmployeeBean[] employee = match();
		//System.out.println(employee[0].getEmployee_id() + " cId1 ");
		EmployeeBean[] employee1 = match(MatchArg.equals("username", username));
		//System.out.println(employee1[0].getEmployee_id() + " cId2 ");
		return employee1;
	}

	public int getEmployee_Id(String username) throws RollbackException {
		EmployeeBean[] employee1 = match(MatchArg.equals("username", username));
		if(employee1!=null && employee1.length>0){
		return employee1[0].getEmployee_id();
		}else{
			return 0;
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