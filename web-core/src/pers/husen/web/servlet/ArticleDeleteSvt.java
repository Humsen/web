package pers.husen.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.service.BlogArticleSvc;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * 删除文章
 *
 * @author 何明胜
 *
 *         2017年11月8日
 */
@WebServlet(urlPatterns = "/article/delete.hms")
public class ArticleDeleteSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ArticleDeleteSvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String requestType = request.getParameter("type");
		/** 如果是删除博客 */
		String logicDeleteBlog = RequestConstants.REQUEST_TYPE_LOGIC_DELETE + RequestConstants.MODE_BLOG;
		if(logicDeleteBlog.equals(requestType)) {
			int blogId = Integer.parseInt(request.getParameter("blogId"));
			BlogArticleSvc bSvc = new BlogArticleSvc();
			int result = bSvc.logicDeleteBlogById(blogId);
			
			out.println(result);
			
			return;
		}
		/** 如果是删除代码 */
		String logicDeleteCode = RequestConstants.REQUEST_TYPE_LOGIC_DELETE + RequestConstants.MODE_CODE;
		if(logicDeleteCode.equals(requestType)) {
			int codeId = Integer.parseInt(request.getParameter("codeId"));
			CodeLibrarySvc cSvc = new CodeLibrarySvc();
			int result = cSvc.logicDeleteCodeById(codeId);
			
			out.println(result);
			
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}