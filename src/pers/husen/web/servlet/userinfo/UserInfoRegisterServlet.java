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
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.common.helper.RandomCodeHelper;
import pers.husen.web.common.helper.SendEmailHelper;
import pers.husen.web.service.UserInfoSvc;

/**
 * 用户注册
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/UserInfoRegisterServlet")
public class UserInfoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfoRegisterServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Logger logger = LogManager.getLogger(UserInfoRegisterServlet.class.getName());
		PrintWriter out = response.getWriter();

		String operationType = request.getParameter("type");
		/** 如果为请求发送验证码 */
		if(CommonConstants.REQUEST_SEND_EMIAL_CODE.equals(operationType)) {
			String email = request.getParameter("email");
			int randomCode = RandomCodeHelper.producedRandomCode(6);
			SendEmailHelper sendEmail = new SendEmailHelper();

			String sendMode = request.getParameter("mode");
			int result = 0;
			
			//如果是找回密码发送验证码
			if(CommonConstants.REQUEST_RETRIVE_PWD_MODE.equals(sendMode)) {
				result = sendEmail.sendEmail2RetrivePwd(email, randomCode);
				request.getSession().setAttribute("random_code_retrive", randomCode);
				logger.info("验证码暂存：" + randomCode + ",成功设置找回密码验证码至session属性");
				out.println(result);
				
				return;
			}
			
			//如果是注册发送验证码
			if(CommonConstants.REQUEST_USER_REGISTER_MODE.equals(sendMode)) {
				result = sendEmail.sendEmail2Register(email, randomCode);
				request.getSession().setAttribute("random_code_register", randomCode);
				logger.info("验证码暂存：" + randomCode + ",成功设置找回注册用户验证码至session属性");
				out.println(result);
				
				return;
			}
			
			//如果是修改邮箱验证原邮箱
			if(CommonConstants.REQUEST_MODIFY_EMAIL_AUTH_MODE.equals(sendMode)) {
				result = sendEmail.sendEmail2ModufyEmailAuth(email, randomCode);
				request.getSession().setAttribute("random_code_modify_email_auth", randomCode);
				logger.info("验证码暂存：" + randomCode + ",成功设置修改邮箱验证旧邮箱验证码至session属性");
				out.println(result);
				
				return;
			}
		
			//如果是修改邮箱验证新邮箱
			if(CommonConstants.REQUEST_MODIFY_EMAIL_BIND_MODE.equals(sendMode)) {
				result = sendEmail.sendEmail2ModufyEmailBind(email, randomCode);
				request.getSession().setAttribute("random_code_modify_email_bind", randomCode);
				logger.info("验证码暂存：" + randomCode + ",成功设置修改邮箱验证新邮箱验证码至session属性");
				out.println(result);
				
				return;
			}
		}
		
		/** 如果为校验验证码 */
		if(CommonConstants.REQUEST_VALIDATE_RANDOM_CODE.equals(operationType)) {
			String randomCodeFromUser = request.getParameter("rangdomcode");
			Object randomCodeFromSession = 0;
			String senddMode = request.getParameter("mode");

			//如果是找回密码校验
			if(CommonConstants.REQUEST_RETRIVE_PWD_MODE.equals(senddMode)) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_retrive");
				
				if(randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + senddMode);
					
					String userName = request.getParameter("username");
					String email = request.getParameter("email");
					
					UserInfoVo uVo = new UserInfoVo();
					uVo.setUserName(userName);
					uVo.setUserEmail(email);
					uVo.setUserPassword("123456");
					
					UserInfoSvc uSvc = new UserInfoSvc();
					int result = uSvc.updateUserPwdByNameAndEmail(uVo);
					
					out.println(result);
				}else {
					logger.info("验证码校验失败，校验类型：" + senddMode + "，用户验证码：" + randomCodeFromUser + "，session验证码：" + randomCodeFromSession);
					out.println(0);
				}
				
				return;
			}
			
			//如果是用户注册校验
			if(CommonConstants.REQUEST_USER_REGISTER_MODE.equals(senddMode)) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_register");
				
				if(randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + senddMode);
					out.println(1);
				}else {
					logger.info("验证码校验失败，校验类型：" + senddMode + "，用户验证码：" + randomCodeFromUser + "，session验证码：" + randomCodeFromSession);
					out.println(0);
				}

				return;
			}
			
			//如果是修改邮箱认证旧邮箱
			if(CommonConstants.REQUEST_MODIFY_EMAIL_AUTH_MODE.equals(senddMode)) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_modify_email_auth");
				
				if(randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + senddMode);
					out.println(1);
				}else {
					logger.info("验证码校验失败，校验类型：" + senddMode + "，用户验证码：" + randomCodeFromUser + "，session验证码：" + randomCodeFromSession);
					out.println(0);
				}

				return;
			}
			
			//如果是修改邮箱绑定新邮箱
			if(CommonConstants.REQUEST_MODIFY_EMAIL_BIND_MODE.equals(senddMode)) {
				randomCodeFromSession = request.getSession().getAttribute("random_code_modify_email_bind");
				
				if(randomCodeFromUser != null && String.valueOf(randomCodeFromSession).equals(randomCodeFromUser)) {
					logger.info("验证码：" + randomCodeFromUser + " 校验成功，校验类型：" + senddMode);
					
					String userName = request.getParameter("username");
					String email = request.getParameter("email");
					
					UserInfoVo uVo = new UserInfoVo();
					uVo.setUserName(userName);
					uVo.setUserEmail(email);
					
					UserInfoSvc uSvc = new UserInfoSvc();
					int result = uSvc.updateUserEmailByName(uVo);
					
					out.println(result);
				}else {
					logger.info("验证码校验失败，校验类型：" + senddMode + "，用户验证码：" + randomCodeFromUser + "，session验证码：" + randomCodeFromSession);
					out.println(0);
				}

				return;
			}
		}
		
		UserInfoVo uVo = new UserInfoVo();
		uVo.setUserName(request.getParameter("username"));
		uVo.setUserPassword(request.getParameter("password"));
		uVo.setUserEmail(request.getParameter("email"));
		
		//System.out.println(uVo);
		UserInfoSvc uSvc = new UserInfoSvc();
		
		out.println(uSvc.insertUserInfo(uVo));
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}