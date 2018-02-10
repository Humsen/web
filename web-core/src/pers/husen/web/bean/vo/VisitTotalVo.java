package pers.husen.web.bean.vo;

import java.util.Date;

/**
 * @author 何明胜
 *
 * 2017年9月30日
 */
public class VisitTotalVo {
	private int visitId;
	private int visitCount;
	private Date visitDate;
	/**
	 * @return the visitDate
	 */
	public Date getVisitDate() {
		return visitDate;
	}
	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	/**
	 * @return the visitId
	 */
	public int getVisitId() {
		return visitId;
	}
	/**
	 * @param visitId the visitId to set
	 */
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	/**
	 * @return the visitCount
	 */
	public int getVisitCount() {
		return visitCount;
	}
	/**
	 * @param visitCount the visitCount to set
	 */
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
}
