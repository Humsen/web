package pers.husen.web.bean.po;

/**
 *
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
public class ImageUploadPo {
	private int success;
	private String message;
	private String url;
	
	@Override
	public String toString() {
		return "ImageUploadPo [success=" + success + ", message=" + message + ", url=" + url + "]";
	}
	/**
	 * @return the success
	 */
	public int getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(int success) {
		this.success = success;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}