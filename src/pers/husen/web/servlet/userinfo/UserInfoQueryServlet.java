package pers.husen.web.servlet.userinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.service.UserInfoSvc;

/**
 * @author 何明胜
 *
 * 2017年10月26日
 */
@WebServlet("/UserInfoQueryServlet")
public class UserInfoQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfoQueryServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		UserInfoSvc uSvc = new UserInfoSvc();

		String userName = request.getParameter("userName");
		UserInfoVo uVo = uSvc.queryUserInfoByName(userName);

		out.println(JSONObject.fromObject(uVo));
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}