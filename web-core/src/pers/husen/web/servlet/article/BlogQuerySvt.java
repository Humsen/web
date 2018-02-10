package pers.husen.web.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.service.BlogArticleSvc;

/**
 * 博客查询servlet,如博客总数量、某一页的博客等
 *
 * @author 何明胜
 *
 *         2017年11月7日
 */
@WebServlet(urlPatterns = "/blog/query.hms")
public class BlogQuerySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BlogQuerySvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter out = response.getWriter();
		BlogArticleSvc bSvc = new BlogArticleSvc();
		String requestType = request.getParameter(RequestConstants.PARAM_TYPE);
		String requestKeywords = request.getParameter(RequestConstants.PARAM_KEYWORDS);
		requestKeywords = (requestKeywords == null ? "" : URLDecoder.decode(requestKeywords, "utf-8"));
		String category = request.getParameter(RequestConstants.PARAM_CATEGORY);

		BlogArticleVo bVo = new BlogArticleVo();
		bVo.setBlogTitle(requestKeywords);
		if (category != null && category.trim() != "") {
			bVo.setBlogCategory(Integer.parseInt(category));
		} else {
			bVo.setBlogCategory(-1);
		}

		/** 如果是请求查询总共数量 */
		String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
		if (queryTotalCount.equals(requestType)) {
			int count = bSvc.queryBlogTotalCount(bVo);
			out.println(count);

			return;
		}
		/** 如果是请求查询某一页的博客 */
		String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
		if (queryOnePage.equals(requestType)) {
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));

			ArrayList<BlogArticleVo> bVos = bSvc.queryBlogArticlePerPage(bVo, pageSize, pageNo);
			String json = JSONArray.fromObject(bVos).toString();

			out.println(json);

			return;
		}
		/** 如果是请求查询所有博客 */
		String queryAllBlog = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
		if (queryAllBlog.equals(requestType)) {
			ArrayList<BlogArticleVo> aVos = bSvc.queryBlogArticles();
			String json = JSONArray.fromObject(aVos).toString();

			out.println(json);

			return;
		}
		/** 如果是请求查询上一篇有效博客 */
		String queryPrevious = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_PREVIOUS;
		if (queryPrevious.equals(requestType)) {
			int blogId = Integer.parseInt(request.getParameter("blogId"));
			bVo = bSvc.queryPreviousBlog(blogId);

			int previousBlog = 0;
			if (bVo != null && bVo.getBlogId() != 0) {
				previousBlog = bVo.getBlogId();
			}
			
			out.println(previousBlog);
			
			return;
		}
		/** 如果是请求查询下一篇有效博客 */
		String queryNext = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_NEXT;
		if (queryNext.equals(requestType)) {
			int blogId = Integer.parseInt(request.getParameter("blogId"));
			bVo = bSvc.queryNextBlog(blogId);

			int nextBlog = 0;
			if (bVo != null && bVo.getBlogId() != 0) {
				nextBlog = bVo.getBlogId();
			}
			
			out.println(nextBlog);
			
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}