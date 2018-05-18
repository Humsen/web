package pers.husen.web.bean.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 文章分类
 *
 * @author 何明胜
 *
 * @created 2017年12月12日 上午9:55:10
 */
public class ArticleCategoryVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private int categoryId;
	private String categoryName;
	private Date createDate;
	private String categoryDelete;
	private int categoryNum;
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the categoryDelete
	 */
	public String getCategoryDelete() {
		return categoryDelete;
	}
	/**
	 * @param categoryDelete the categoryDelete to set
	 */
	public void setCategoryDelete(String categoryDelete) {
		this.categoryDelete = categoryDelete;
	}
	/**
	 * @return the categoryNum
	 */
	public int getCategoryNum() {
		return categoryNum;
	}
	/**
	 * @param categoryNum the categoryNum to set
	 */
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
}
