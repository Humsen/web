package pers.husen.web.dao;


/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public interface VisitTotalDao {
	/**
	 * 查询所有访问量
	 * @return
	 */
	public int queryVisitTotal();
	
	/**
	 * 查询当日访问量
	 * @return
	 */
	public int queryVisitToday();
	
	/**
	 * 更新当日访问量和总访问量
	 * 
	 * @param visitDate
	 * @return
	 */
	public int updateVisitCount();
}
