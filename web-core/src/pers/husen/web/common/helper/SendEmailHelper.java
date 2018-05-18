package pers.husen.web.common.helper;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.mail.util.MailSSLSocketFactory;

import pers.husen.web.common.constants.ResponseConstants;

/**
 * 发送邮件
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
public class SendEmailHelper {
	private final Logger logger = LogManager.getLogger(SendEmailHelper.class.getName());
	
	public static void main(String[] args) {
		SendEmailHelper sendEmail = new SendEmailHelper();
		sendEmail.sendEmail2Admin("何明胜", "123@qwe.com", "", "这网站不错");
	}
	
	public int sendEmail2RetrivePwd(String email, int randomCode) {
		String subject = "【何明胜的个人网站】找回密码邮箱验证";
		String mode = "您正在使用找回密码功能。";
		
		return sendEmail2User(email, randomCode, subject, mode);
	}
	
	public int sendEmail2Register(String email, int randomCode) {
		String subject = "【何明胜的个人网站】新用户注册邮箱验证";
		String mode = "欢迎在【何明胜的个人网站】注册账号。";
		
		return sendEmail2User(email, randomCode, subject, mode);
	}
	
	/**
	 * 修改邮箱验证原邮箱
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail2ModufyEmailAuth(String email, int randomCode) {
		String subject = "【何明胜的个人网站】用户修改邮箱验证原邮箱";
		String mode = "您正在使用修改邮箱功能。第一步，验证码您的原邮箱。";
		
		return sendEmail2User(email, randomCode, subject, mode);
	}
	
	/**
	 * 修改邮箱验证原邮箱
	 * @param email
	 * @param randomCode
	 * @return
	 */
	public int sendEmail2ModufyEmailBind(String email, int randomCode) {
		String subject = "【何明胜的个人网站】用户修改邮箱绑定新邮箱";
		String mode = "您正在使用修改邮箱功能。第二步，绑定您的新邮箱。";
		
		return sendEmail2User(email, randomCode, subject, mode);
	}
	
	/**
	 * 发送验证码通用函数
	 * @param email
	 * @param randomCode
	 * @param subject
	 * @param mode
	 * @return
	 */
	public int sendEmail2User(String email, int randomCode, String subject, String mode) {
		try {
			Session session = setupSession();
			
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress("yige_robot@foxmail.com", "一格网站机器人", "UTF-8"));
			// 收件人电子邮箱 可用数组设置多个
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	        
			// 设置标题
			message.setSubject(subject);
			
			// 设置邮件内容 html文本
			String content = "尊敬的用户："
					+ "<br/>&emsp;&emsp;您好!"
					+ "<br/>&emsp;&emsp;" + mode
					+ "<br/>"
					+ "<br/>&emsp;&emsp;您的邮箱验证码【"+ randomCode +"】，请于10分钟内输入，任何人都不会向您索取，请勿泄露。"
					+ "<br/>&emsp;&emsp;注：如果不是您发出的请求，说明您的邮箱可能被人冒用或者存在风险，请留意。"
					+ "<br/><br/><br/>此致，一格";
			
			message.setContent(content, "text/html;charset=UTF-8");
			
			// 发送信息的工具
			Transport transport = session.getTransport();
			transport.connect();
			// 对方的地址
			transport.sendMessage(message, new Address[] { new InternetAddress(email) });
			// 关闭连接
			transport.close();
			
			logger.info("发送邮件成功! 收件人：" + email);
			
			return ResponseConstants.RESPONSE_OPERATION_SUCCESS;
		} catch (MessagingException | UnsupportedEncodingException | GeneralSecurityException mex) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(mex));
		}
		
		return ResponseConstants.RESPONSE_OPERATION_FAILURE;
	}
	
	/**
	 * 发邮件给站长
	 * @param name
	 * @param email
	 * @param phone
	 * @param content
	 * @return
	 */
	public int sendEmail2Admin(String name,String email, String phone, String content) {
		try {
			Session session = setupSession();
			
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// Set From: 头部头字段
			message.setFrom(new InternetAddress("yige_robot@foxmail.com", "一格机器人", "UTF-8"));
			// 收件人电子邮箱 可用数组设置多个
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("husen@hemingsheng.cn"));
	        
			// 设置标题
			message.setSubject("个人网站联系站长邮件");
			
			// 设置邮件内容 普通文本
			content = "我亲爱的站长：<br/>&emsp;&emsp;你好! 我是您的网站的一格机器人。<br/>&emsp;&emsp;现在有人通过您的网站\"联系站长\"功能给您发邮件，详情如下："
					+ "<br/>"
					+ "<br/>&emsp;&emsp;姓名：" + name 
					+ "<br/>&emsp;&emsp;手机：" + phone
					+ "<br/>&emsp;&emsp;邮箱：" + email
					+ "<br/>&emsp;&emsp;邮件内容：<br/>&emsp;&emsp;&emsp;&emsp;" +content;
			message.setContent(content, "text/html;charset=UTF-8");
			
			// 发送信息的工具
			Transport transport = session.getTransport();
			transport.connect();
			// 对方的地址
			transport.sendMessage(message, new Address[] { new InternetAddress("husen@hemingsheng.cn") });
			// 关闭连接
			transport.close();
			
			logger.info("发送邮件给站长成功! 收件人：" + email);
			
			return 1;
		} catch (MessagingException | UnsupportedEncodingException | GeneralSecurityException mex) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(mex));
		}
		
		return 0;
	}
	
	/**
	 * 获取邮箱机器人登录邮箱session
	 * @return
	 * @throws GeneralSecurityException
	 */
	public Session setupSession() throws GeneralSecurityException {
		// 建立属性对象
		Properties properties = new Properties();
		// 开启SSL
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		// 开启认证
		properties.put("mail.smtp.auth", "true");
		// 开启SSL
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 设置邮件服务器主机名
		properties.setProperty("mail.host", "smtp.qq.com");
		// 设置端口
		properties.setProperty("mail.smtp.port", "465");

		// 根据认证获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("yige_robot@foxmail.com", "xjsvsdyrekodfiah");
			}
		});
		
		return session;
	}
}