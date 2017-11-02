package pers.husen.web.service;

import pers.husen.web.dao.impl.VisitTotalDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public class VisitTotalSvc {
	private VisitTotalDaoImpl vImpl = new VisitTotalDaoImpl();
	
	public int queryVisitTotal() {
		return vImpl.queryVisitTotal();
	}
	
	public int queryVisitToday() {
		return vImpl.queryVisitToday();
	}
	
	public int updateVisitCount() {
		return vImpl.updateVisitCount();
	}
}
