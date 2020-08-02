
package pers.husen.web.service;

import pers.husen.web.dao.VisitTotalDao;
import pers.husen.web.dao.impl.VisitTotalDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public class VisitTotalSvc implements VisitTotalDao{
	private static final VisitTotalDaoImpl visitTotalDaoImpl = new VisitTotalDaoImpl();
	
	@Override
	public int queryVisitTotal() {
		return visitTotalDaoImpl.queryVisitTotal();
	}
	
	@Override
	public int queryVisitToday() {
		return visitTotalDaoImpl.queryVisitToday();
	}
	
	@Override
	public int updateVisitCount() {
		return visitTotalDaoImpl.updateVisitCount();
	}
}
