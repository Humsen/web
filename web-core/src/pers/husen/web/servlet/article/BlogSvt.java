package pers.husen.web.servlet.article;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.constants.ResponseConstants;
import pers.husen.web.common.helper.ReadH5Helper;
import pers.husen.web.service.BlogArticleSvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 根据id查询某一篇博客
 *
 * @author 何明胜
 *
 *         2017年11月7日
 */
@WebServlet(urlPatterns = "/blog.hms")
public class BlogSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BlogSvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		BlogArticleSvc bSvc = new BlogArticleSvc();

		int blogId = Integer.parseInt(request.getParameter("blogId"));
		BlogArticleVo bVo = bSvc.queryPerBlogById(blogId);
		
		/** 判断是否是返回博客json数据 */
		String returnType = request.getParameter("type");
		if (RequestConstants.REQUEST_TYPE_JSON.equals(returnType)) {
			out.println(JSONObject.fromObject(bVo));

			return;
		}
		
		/** 默认返回整篇文章 */
		response.setContentType("text/html");  
		String resultHtml = ReadH5Helper.modifyHtmlKeywords(ResponseConstants.BLOG_TEMPLATE_PATH, bVo.getBlogLabel());
		out.println(resultHtml);
		//增加访问次数
		bSvc.updateBlogReadById(blogId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}