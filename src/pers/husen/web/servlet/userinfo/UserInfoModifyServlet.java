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
@WebServlet("/UserInfoModifyServlet")
public class UserInfoModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoModifyServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String formData = request.getParameter("formdata");
		JSONObject jsonObject = JSONObject.fromObject(formData);
		UserInfoVo uVo = (UserInfoVo) JSONObject.toBean(jsonObject, UserInfoVo.class);

		UserInfoSvc uSvc = new UserInfoSvc();
		PrintWriter out = response.getWriter();
		
		int updateResult = uSvc.updateUserInfoById(uVo);
		out.println(updateResult);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}