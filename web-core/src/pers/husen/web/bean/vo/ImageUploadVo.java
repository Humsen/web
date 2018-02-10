package pers.husen.web.bean.vo;

import java.util.Date;

/**
 * 图片
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
public class ImageUploadVo {
	private int imageId;
	private String imageName;
	private String imageUrl;
	private Date imageUploadDate;
	private int imageType;
	private int imageDownloadCount;
	/**
	 * @return the imageId
	 */
	public int getImageId() {
		return imageId;
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the imageUploadDate
	 */
	public Date getImageUploadDate() {
		return imageUploadDate;
	}
	/**
	 * @param imageUploadDate the imageUploadDate to set
	 */
	public void setImageUploadDate(Date imageUploadDate) {
		this.imageUploadDate = imageUploadDate;
	}
	/**
	 * @return the imageType
	 */
	public int getImageType() {
		return imageType;
	}
	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(int imageType) {
		this.imageType = imageType;
	}
	/**
	 * @return the imageDownloadCount
	 */
	public int getImageDownloadCount() {
		return imageDownloadCount;
	}
	/**
	 * @param imageDownloadCount the imageDownloadCount to set
	 */
	public void setImageDownloadCount(int imageDownloadCount) {
		this.imageDownloadCount = imageDownloadCount;
	}
}
