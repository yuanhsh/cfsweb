package com.cfs.dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.cfs.bean.PositionBean;

public class PositionDAO extends GenericDAO<PositionBean> { 
	public PositionDAO(String tableName,ConnectionPool pool ) throws DAOException {
		super (PositionBean.class,tableName,pool);
	}

	public PositionBean getCustomerFundPosition(int customer_id, int fund_id) throws RollbackException {
		MatchArg arg1 = MatchArg.equals("customer_id", customer_id);
		MatchArg arg2 = MatchArg.equals("fund_id", fund_id);
		PositionBean[] positions = this.match(arg1, arg2);
		if(positions == null || positions.length == 0) {
			return null;
		}
		return positions[0];
	}

}