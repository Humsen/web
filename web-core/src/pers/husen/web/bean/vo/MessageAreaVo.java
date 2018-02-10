package pers.husen.web.bean.vo;

import java.util.Date;

/**
 * @author 何明胜
 *
 * 2017年9月25日
 */
public class MessageAreaVo {
	private int messageId;
	private int messageParent;
	private int messageBelong;
	private String messageContent;
	private Date messageDate;
	private String messageEmail;
	private String messageUsername;
	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}
	/**
	 * @return the messageEmail
	 */
	public String getMessageEmail() {
		return messageEmail;
	}
	/**
	 * @param messageEmail the messageEmail to set
	 */
	public void setMessageEmail(String messageEmail) {
		this.messageEmail = messageEmail;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	/**
	 * @return the messageParent
	 */
	public int getMessageParent() {
		return messageParent;
	}
	/**
	 * @param messageParent the messageParent to set
	 */
	public void setMessageParent(int messageParent) {
		this.messageParent = messageParent;
	}
	/**
	 * @return the messageBelong
	 */
	public int getMessageBelong() {
		return messageBelong;
	}
	/**
	 * @param messageBelong the messageBelong to set
	 */
	public void setMessageBelong(int messageBelong) {
		this.messageBelong = messageBelong;
	}
	/**
	 * @return the messageContent
	 */
	public String getMessageContent() {
		return messageContent;
	}
	/**
	 * @param messageContent the messageContent to set
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	/**
	 * @return the messageDate
	 */
	public Date getMessageDate() {
		return messageDate;
	}
	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	
	@Override
	public String toString() {
		return "MessageAreaVo [messageId=" + messageId + ", messageParent=" + messageParent + ", messageBelong="
				+ messageBelong + ", messageContent=" + messageContent + ", messageDate=" + messageDate
				+ ", mseeageEmail=" + messageEmail + ", messageUsername=" + messageUsername + "]";
	}
	/**
	 * @return the messageUsername
	 */
	public String getMessageUsername() {
		return messageUsername;
	}
	/**
	 * @param messageUsername the messageUsername to set
	 */
	public void setMessageUsername(String messageUsername) {
		this.messageUsername = messageUsername;
	}
}
