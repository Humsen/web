package pers.husen.web.servlet.userinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.service.UserInfoSvc;

/**
 * 用户登录验证
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/UserLoginValidateServlet")
public class UserLoginValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginValidateServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		UserInfoSvc uSvc = new UserInfoSvc();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String validateType = request.getParameter("type");
			
		//如果为请求修改密码
		if(validateType != null && CommonConstants.REQUEST_MODIFY_PWD.equals(validateType)) {
			UserInfoVo uVo = new UserInfoVo();
			uVo.setUserName(userName);
			uVo.setUserPassword(password);
			
			int modifyResult = uSvc.updateUserPwdByName(uVo);
			out.println(modifyResult);
			
			return;
		}
		
		String queryPwd = uSvc.queryPasswordByUserName(userName);

		if(password.equals(queryPwd)) {
			//如果为登录，设置cookie
			if(validateType == null || !CommonConstants.REQUEST_VALIDATE_OLD_PWD.equals(validateType)) {
				Cookie cookie = new Cookie("username", userName);
				cookie.setPath("/");
				//cookie.setMaxAge(60*60); 不设置，关闭浏览器即失效
				response.addCookie(cookie);
			}
			
			out.println(1);
			out.flush();
			out.close();
		}else {
			out.println(0);
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}