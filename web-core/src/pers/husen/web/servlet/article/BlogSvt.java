package pers.husen.web.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.constants.ResponseConstants;
import pers.husen.web.common.helper.ReadH5Helper;
import pers.husen.web.service.BlogArticleSvc;

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
		if (returnType != null && RequestConstants.REQUEST_TYPE_JSON.equals(returnType)) {
			out.println(JSONObject.fromObject(bVo));

			return;
		}
		
		/** 默认返回整篇文章 */
		response.setContentType("text/html");  
		String resultHtml = ReadH5Helper.modifyHtmlKeywords(ResponseConstants.BLOG_TEMPLATE_PATH, bVo.getBlogLabel());
		out.println(resultHtml);
		//增加访问次数
		bSvc.updateBlogReadById(blogId);
		
		/*HttpSession session = request.getSession();
		//判断是否已经访问过该页面，修改浏览次数 
		Object counter = session.getAttribute("blog_" + blogId);
		if (counter == null) {
			session.setAttribute("blog_" + blogId, new Integer(1));
			
		} else {
			int count = ((Integer) counter).intValue();
			count++;
			session.setAttribute("blog_" + blogId, new Integer(count));
		}

		// 判断是否是管理员登录
		boolean isSuperAdmin = false;

		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName()) && "husen".equals(cookie.getValue())) {
					isSuperAdmin = true;
				}
			}
		}

		String htmlReturn = GenericTemplate.htmlHeader("博客", bVo.getBlogSummary(), bVo.getBlogLabel())
				+ GenericTemplate.jsAndCssPlugins() + BlogTemplate.customizeHeader() + GenericTemplate.headBody()
				+ BlogTemplate.detailBlogBody(bVo, isSuperAdmin) + GenericTemplate.bodyHtml();

		out.println(htmlReturn);*/
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}