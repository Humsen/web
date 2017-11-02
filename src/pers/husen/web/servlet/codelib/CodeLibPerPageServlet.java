package pers.husen.web.servlet.codelib;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * 查询一页的代码库目录
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/CodeLibPerPageServlet")
public class CodeLibPerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CodeLibPerPageServlet() {
        super();
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		ArrayList<CodeLibraryVo> cVos = cSvc.queryBlogArticlePerPage(pageSize, pageNo);
		
		String json =JSONArray.fromObject(cVos).toString();
		
		out.println(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}