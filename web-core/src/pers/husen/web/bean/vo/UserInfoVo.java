package pers.husen.web.bean.vo;

/**
 * @author 何明胜
 *
 * 2017年9月17日
 */

public class UserInfoVo {
	private int userId;
	private String userName;
	private String userNickName;
	private String userPassword;
	private String userEmail;
	private String userPhone;
	private int userAge;
	private int userSex;
	private String userAddress;
	private String userHeadUrl;

	@Override
	public String toString() {
		return "UserInfoVo [userId=" + userId + ", userName=" + userName + ", userNickName=" + userNickName
				+ ", userPassword=" + userPassword + ", userEmail=" + userEmail + ", userPhone=" + userPhone
				+ ", userAge=" + userAge + ", userSex=" + userSex + ", userAddress=" + userAddress + ", userHeadUrl="
				+ userHeadUrl + "]";
	}
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
	 * @return the userHeadUrl
	 */
	public String getUserHeadUrl() {
		return userHeadUrl;
	}
	/**
	 * @param userHeadUrl the userHeadUrl to set
	 */
	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * @return the userAge
	 */
	public int getUserAge() {
		return userAge;
	}
	/**
	 * @param userAge the userAge to set
	 */
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	/**
	 * @return the userSex
	 */
	public int getUserSex() {
		return userSex;
	}
	/**
	 * @param userSex the userSex to set
	 */
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	/**
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}
	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}
