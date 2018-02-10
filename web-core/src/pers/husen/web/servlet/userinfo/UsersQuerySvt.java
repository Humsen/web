package pers.husen.web.servlet.userinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.UserInfoVo;
import pers.husen.web.service.UserInfoSvc;

/**
 * @desc 查询所有用户
 *
 * @author 何明胜
 *
 * @created 2017年12月26日 下午2:58:15
 */
@WebServlet(urlPatterns = "/users/query.hms")
public class UsersQuerySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UsersQuerySvt() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		Integer draw = Integer.parseInt(request.getParameter("draw"));
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer length = Integer.parseInt(request.getParameter("length"));
		
		PrintWriter out = response.getWriter();
		UserInfoVo uVo = new UserInfoVo();
		UserInfoSvc uSvc = new UserInfoSvc();
		
		Integer recordsTotal = uSvc.queryUserTotalCount(uVo);
		ArrayList<UserInfoVo> uVos = uSvc.queryUserPerPage(uVo, length, start);
		String json = JSONArray.fromObject(uVos).toString();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("draw", draw);
		jsonObject.put("recordsTotal", recordsTotal);
		jsonObject.put("recordsFiltered", recordsTotal);
		jsonObject.element("data", json);
		
		out.println(jsonObject);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}