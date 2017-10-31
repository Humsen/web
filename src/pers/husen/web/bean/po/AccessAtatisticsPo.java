package pers.husen.web.bean.po;

import java.io.Serializable;

/**
 *访问统计
 *
 * @author 何明胜
 *
 * 2017年10月18日
 */
public class AccessAtatisticsPo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int accessToday;
	private int accessTotal;
	private int onlineCurrent;
	/**
	 * @return the accessToday
	 */
	public int getAccessToday() {
		return accessToday;
	}
	/**
	 * @param accessToday the accessToday to set
	 */
	public void setAccessToday(int accessToday) {
		this.accessToday = accessToday;
	}
	/**
	 * @return the accessTotal
	 */
	public int getAccessTotal() {
		return accessTotal;
	}
	/**
	 * @param accessTotal the accessTotal to set
	 */
	public void setAccessTotal(int accessTotal) {
		this.accessTotal = accessTotal;
	}
	/**
	 * @return the onlineCurrent
	 */
	public int getOnlineCurrent() {
		return onlineCurrent;
	}
	/**
	 * @param onlineCurrent the onlineCurrent to set
	 */
	public void setOnlineCurrent(int onlineCurrent) {
		this.onlineCurrent = onlineCurrent;
	}
}