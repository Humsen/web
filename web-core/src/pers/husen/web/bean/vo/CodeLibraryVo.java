package pers.husen.web.bean.vo;

import java.util.Date;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class CodeLibraryVo extends ArticleCategoryVo{
	private static final long serialVersionUID = 1L;
	
	private int codeId;
	private String codeTitle;
	private String codeAuthor;
	private Date codeDate;
	private int codeRead;
	private String codeSummary;
	private String codeHtmlContent;
	private String codeMdContent;
	private String codeLabel;
	private int codeDelete;
	private int codeCategory;
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
	 * @return the codeLabel
	 */
	public String getCodeLabel() {
		return codeLabel;
	}
	/**
	 * @param codeLabel the codeLabel to set
	 */
	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}
	/**
	 * @return the codeId
	 */
	public int getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	/**
	 * @return the codeTitle
	 */
	public String getCodeTitle() {
		return codeTitle;
	}
	/**
	 * @param codeTitle the codeTitle to set
	 */
	public void setCodeTitle(String codeTitle) {
		this.codeTitle = codeTitle;
	}
	/**
	 * @return the codeAuthor
	 */
	public String getCodeAuthor() {
		return codeAuthor;
	}
	/**
	 * @param codeAuthor the codeAuthor to set
	 */
	public void setCodeAuthor(String codeAuthor) {
		this.codeAuthor = codeAuthor;
	}
	/**
	 * @return the codeDate
	 */
	public Date getCodeDate() {
		return codeDate;
	}
	/**
	 * @param codeDate the codeDate to set
	 */
	public void setCodeDate(Date codeDate) {
		this.codeDate = codeDate;
	}
	/**
	 * @return the codeRead
	 */
	public int getCodeRead() {
		return codeRead;
	}
	/**
	 * @param codeRead the codeRead to set
	 */
	public void setCodeRead(int codeRead) {
		this.codeRead = codeRead;
	}
	/**
	 * @return the codeSummary
	 */
	public String getCodeSummary() {
		return codeSummary;
	}
	/**
	 * @param codeSummary the codeSummary to set
	 */
	public void setCodeSummary(String codeSummary) {
		this.codeSummary = codeSummary;
	}
	/**
	 * @return the codeHtmlContent
	 */
	public String getCodeHtmlContent() {
		return codeHtmlContent;
	}
	/**
	 * @param codeHtmlContent the codeHtmlContent to set
	 */
	public void setCodeHtmlContent(String codeHtmlContent) {
		this.codeHtmlContent = codeHtmlContent;
	}
	/**
	 * @return the codeMdContent
	 */
	public String getCodeMdContent() {
		return codeMdContent;
	}
	/**
	 * @param codeMdContent the codeMdContent to set
	 */
	public void setCodeMdContent(String codeMdContent) {
		this.codeMdContent = codeMdContent;
	}
	/**
	 * @return the codeDelete
	 */
	public int getCodeDelete() {
		return codeDelete;
	}
	/**
	 * @param codeDelete the codeDelete to set
	 */
	public void setCodeDelete(int codeDelete) {
		this.codeDelete = codeDelete;
	}
	/**
	 * @return the codeCategory
	 */
	public int getCodeCategory() {
		return codeCategory;
	}
	/**
	 * @param codeCategory the codeCategory to set
	 */
	public void setCodeCategory(int codeCategory) {
		this.codeCategory = codeCategory;
	}
}