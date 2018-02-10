package pers.husen.web.bean.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author 何明胜
 *
 * 2017年10月17日
 */
public class ReleaseFeatureVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int releaseId;
	private String releaseAuthor;
	private Date releaseDate;
	private String releaseNumber;
	private String releaseContent;
	
	@Override
	public String toString() {
		return "ReleaseFeatureVo [releaseId=" + releaseId + ", releaseAuthor=" + releaseAuthor + ", releaseDate="
				+ releaseDate + ", releaseNumber=" + releaseNumber + ", releaseContent=" + releaseContent + "]";
	}
	/**
	 * @return the releaseId
	 */
	public int getReleaseId() {
		return releaseId;
	}
	/**
	 * @param releaseId the releaseId to set
	 */
	public void setReleaseId(int releaseId) {
		this.releaseId = releaseId;
	}
	/**
	 * @return the releaseAuthor
	 */
	public String getReleaseAuthor() {
		return releaseAuthor;
	}
	/**
	 * @param releaseAuthor the releaseAuthor to set
	 */
	public void setReleaseAuthor(String releaseAuthor) {
		this.releaseAuthor = releaseAuthor;
	}
	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * @return the releaseNumber
	 */
	public String getReleaseNumber() {
		return releaseNumber;
	}
	/**
	 * @param releaseNumber the releaseNumber to set
	 */
	public void setReleaseNumber(String releaseNumber) {
		this.releaseNumber = releaseNumber;
	}
	/**
	 * @return the releaseContent
	 */
	public String getReleaseContent() {
		return releaseContent;
	}
	/**
	 * @param releaseContent the releaseContent to set
	 */
	public void setReleaseContent(String releaseContent) {
		this.releaseContent = releaseContent;
	}
}