package pers.husen.web.servlet.blog;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.service.BlogArticleSvc;

/**
 * 查询博客一页的目录
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/BlogArticlePerPage")
public class BlogArticlePerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BlogArticlePerPageServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		BlogArticleSvc bSvc = new BlogArticleSvc();
		ArrayList<BlogArticleVo> articleVos = bSvc.queryBlogArticlePerPage(pageSize, pageNo);
        
		String json =JSONArray.fromObject(articleVos).toString();
		
		out.println(json);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}