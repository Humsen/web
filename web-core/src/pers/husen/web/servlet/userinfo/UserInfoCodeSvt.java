package pers.husen.web.servlet.userinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.RandomCodeHelper;
import pers.husen.web.common.helper.SendEmailHelper;
import pers.husen.web.service.UserInfoSvc;

/**
 * 用户信息操作, 带有验证码操作
 *
 * @author 何明胜
 *
 *         2017年10月20日
 */
@WebServlet(urlPatterns = "/userInfo/code.hms")
public class UserInfoCodeSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoCodeSvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = LogManager.getLogger(UserInfoCodeSvt.class.getName());
		PrintWriter out = response.getWriter();

		String operationType = request.getParameter("type");
		//如果请求类型为null,说明异常，返回
		if(operationType == null) {
			out.println(-1);
			return;
		}
		
		/** 如果为请求发送验证码  */
		if (operationType.indexOf(RequestConstants.REQUEST_TYPE_SEND_CODE) != -1) {
			String email = request.getParameter("email");
			int randomCode = RandomCodeHelper.producedRandomCode(6);
			SendEmailHelper sendEmail = new SendEmailHelper();

			int result = 0;

			// 如果是找回密码发送验证码
			if (operationType.indexOf(RequestConstants.MODE_RETRIVE_PWD) != -1) {
				result = sendEmail.sendEmail2RetrivePwd(email, randomCode);
				request.getSession().setAttribute("random_code_retrive", randomCode);
				logger.info("验证码暂存：[" + randomCode + "],成功设置找回密码验证码至session属性");
				out.println(result);

				return;
			}

			// 如果是注册发送验证码
			if (operationType.indexOf(RequestConstants.MODE_REGISTER) != -1) {
				result = sendEmail.sendEmail2Register(email, randomCode);
				request.getSession().setAttribute("random_code_register", randomCode);
				logger.info("验证码暂存：[" + randomCode + "],成功设置注册用户验证码至session属性");
				out.println(result);

				return;
			}

			// 如果是修改邮箱验证原邮箱,发送验证码
			if (operationType.indexOf(RequestConstants.MODE_OLD_EMAIL) != -1) {
				result = sendEmail.sendEmail2ModufyEmailAuth(email, randomCode);
				request.getSession().setAttribute("random_code_modify_email_auth", randomCode);
				logger.info("验证码暂存：[" + randomCode + "],成功设置修改邮箱验证旧邮箱验证码至session属性");
				System.out.println(result);
				out.println(result);

				return;
			}

			// 如果是修改邮箱绑定新邮箱,发送验证码
			if (operationType.indexOf(RequestConstants.MODE_BIND_EMAIL) != -1) {
				result = sendEmail.sendEmail2ModufyEmailBind(email, randomCode);
				request.getSession().setAttribute("random_code_modify_email_bind", randomCode);
				logger.info("验证码暂存：[" + randomCode + "],成功设置修改邮箱验证新邮箱验证码至session属性");
				out.println(result);

				return;
			}
		}

		/** 如果为校验验证码  */
		if (operationType.indexOf(RequestConstants.REQUEST_TYPE_AUTH_CODE) != -1) {
			String randomCodeFromUser = request.getParameter("randomCode");
			Object randomCodeFromSession = 0;
			String type = request.getParameter("type");

			// 如果是找回密码校验
			if (operationType.indexOf(RequestConstants.MODE_RETRIVE_PWD) != -1) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_retrive");

				if (randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + type);

					String userName = request.getParameter("userName");
					String email = request.getParameter("email");

					UserInfoVo uVo = new UserInfoVo();
					uVo.setUserName(userName);
					uVo.setUserEmail(email);
					uVo.setUserPassword("123456");

					UserInfoSvc uSvc = new UserInfoSvc();
					int result = uSvc.updateUserPwdByNameAndEmail(uVo);

					out.println(result);
				} else {
					logger.info("验证码校验失败，校验类型：" + type + "，用户验证码：" + randomCodeFromUser + "，session验证码："
							+ randomCodeFromSession);
					out.println(0);
				}

				return;
			}

			// 如果是用户注册校验
			if (operationType.indexOf(RequestConstants.MODE_REGISTER) != -1) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_register");

				if (randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + type);
					out.println(1);
				} else {
					logger.info("验证码校验失败，校验类型：" + type + "，用户验证码：" + randomCodeFromUser + "，session验证码：" + randomCodeFromSession);
					out.println(0);
				}

				return;
			}

			// 如果是修改邮箱认证旧邮箱
			if (operationType.indexOf(RequestConstants.MODE_OLD_EMAIL) != -1) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_modify_email_auth");

				if (randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + type);
					out.println(1);
				} else {
					logger.info("验证码校验失败，校验类型：" + type + "，用户验证码：" + randomCodeFromUser + "，session验证码："
							+ randomCodeFromSession);
					out.println(0);
				}

				return;
			}

			// 如果是修改邮箱绑定新邮箱
			if (operationType.indexOf(RequestConstants.MODE_BIND_EMAIL) != -1) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_modify_email_bind");

				if (randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + type);

					String userName = request.getParameter("username");
					String email = request.getParameter("email");

					UserInfoVo uVo = new UserInfoVo();
					uVo.setUserName(userName);
					uVo.setUserEmail(email);

					UserInfoSvc uSvc = new UserInfoSvc();
					int result = uSvc.updateUserEmailByName(uVo);

					out.println(result);
				} else {
					logger.info("验证码校验失败，校验类型：" + type + "，用户验证码：" + randomCodeFromUser + "，session验证码："
							+ randomCodeFromSession);
					out.println(0);
				}

				return;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}