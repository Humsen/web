package pers.husen.web.bean.vo;

import java.util.Date;

/**
 * @author 何明胜
 *
 * 2017年9月29日
 */
public class FileDownloadVo {
	@Override
	public String toString() {
		return "FileDownloadVo [fileId=" + fileId + ", fileName=" + fileName + ", fileUrl=" + fileUrl
				+ ", fileUploadDate=" + fileUploadDate + ", fileDownloadCount=" + fileDownloadCount + "]";
	}
	private int fileId;
	private String fileName;
	private String fileUrl;
	private Date fileUploadDate;
	private int fileDownloadCount;
	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileUrl
	 */
	public String getFileUrl() {
		return fileUrl;
	}
	/**
	 * @param fileUrl the fileUrl to set
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	/**
	 * @return the fileUploadDate
	 */
	public Date getFileUploadDate() {
		return fileUploadDate;
	}
	/**
	 * @param fileUploadDate the fileUploadDate to set
	 */
	public void setFileUploadDate(Date fileUploadDate) {
		this.fileUploadDate = fileUploadDate;
	}
	/**
	 * @return the fileDownloadCount
	 */
	public int getFileDownloadCount() {
		return fileDownloadCount;
	}
	/**
	 * @param fileDownloadCount the fileDownloadCount to set
	 */
	public void setFileDownloadCount(int fileDownloadCount) {
		this.fileDownloadCount = fileDownloadCount;
	}
}
