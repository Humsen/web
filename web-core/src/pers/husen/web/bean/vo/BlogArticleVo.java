package pers.husen.web.bean.vo;

import java.util.Date;

/**
 * @author 何明胜
 *
 * 2017年9月17日
 */
public class BlogArticleVo extends ArticleCategoryVo{
	private static final long serialVersionUID = 1L;
	
	private int blogId;
	private String blogTitle;
	private String blogAuthor;
	private Date blogDate;
	private String blogSummary;
	private String blogHtmlContent;
	private int blogRead;
	private String blogMdContent;
	private String blogLabel;
	private int blogDelete;
	private int blogCategory;
	private String userNickName;
	
	/**
	 * @return the userNickName
	 */
	public String getUserNickName() {
		return userNickName;
	}
	/**
	 * @param userNickName the userNickName to set
	 */
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	/**
	 * @return the blogDelete
	 */
	public int getBlogDelete() {
		return blogDelete;
	}
	/**
	 * @param blogDelete the blogDelete to set
	 */
	public void setBlogDelete(int blogDelete) {
		this.blogDelete = blogDelete;
	}
	/**
	 * @return the blogLabel
	 */
	public String getBlogLabel() {
		return blogLabel;
	}
	/**
	 * @param blogLabel the blogLabel to set
	 */
	public void setBlogLabel(String blogLabel) {
		this.blogLabel = blogLabel;
	}
	/**
	 * @return the blogHtmlContent
	 */
	public String getBlogHtmlContent() {
		return blogHtmlContent;
	}
	/**
	 * @param blogHtmlContent the blogHtmlContent to set
	 */
	public void setBlogHtmlContent(String blogHtmlContent) {
		this.blogHtmlContent = blogHtmlContent;
	}
	/**
	 * @return the blogMdContent
	 */
	public String getBlogMdContent() {
		return blogMdContent;
	}
	/**
	 * @param blogMdContent the blogMdContent to set
	 */
	public void setBlogMdContent(String blogMdContent) {
		this.blogMdContent = blogMdContent;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the blogId
	 */
	public int getBlogId() {
		return blogId;
	}
	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	/**
	 * @return the blogTitle
	 */
	public String getBlogTitle() {
		return blogTitle;
	}
	/**
	 * @param blogTitle the blogTitle to set
	 */
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	/**
	 * @return the blogAuthor
	 */
	public String getBlogAuthor() {
		return blogAuthor;
	}
	/**
	 * @param blogAuthor the blogAuthor to set
	 */
	public void setBlogAuthor(String blogAuthor) {
		this.blogAuthor = blogAuthor;
	}
	/**
	 * @return the blogDate
	 */
	public Date getBlogDate() {
		return blogDate;
	}
	/**
	 * @param blogDate the blogDate to set
	 */
	public void setBlogDate(Date blogDate) {
		this.blogDate = blogDate;
	}
	/**
	 * @return the blogSummary
	 */
	public String getBlogSummary() {
		return blogSummary;
	}
	/**
	 * @param blogSummary the blogSummary to set
	 */
	public void setBlogSummary(String blogSummary) {
		this.blogSummary = blogSummary;
	}
	/**
	 * @return the blogCategory
	 */
	public int getBlogCategory() {
		return blogCategory;
	}
	/**
	 * @param blogCategory the blogCategory to set
	 */
	public void setBlogCategory(int blogCategory) {
		this.blogCategory = blogCategory;
	}
	/**
	 * @return the blogRead
	 */
	public int getBlogRead() {
		return blogRead;
	}
	/**
	 * @param blogRead the blogRead to set
	 */
	public void setBlogRead(int blogRead) {
		this.blogRead = blogRead;
	}
}