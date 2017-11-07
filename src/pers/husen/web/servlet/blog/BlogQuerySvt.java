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
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.service.BlogArticleSvc;

/**
 * 博客查询sevlet,如博客总数量、某一页的博客等
 *
 * @author 何明胜
 *
 * 2017年11月7日
 */
@WebServlet(urlPatterns="/blog/query.hms")
public class BlogQuerySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlogQuerySvt() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		BlogArticleSvc bSvc = new BlogArticleSvc();
		String requestType = request.getParameter("type");
		
		//如果是请求查询总共数量
		String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
		if(queryTotalCount.equals(requestType)) {
			int count = bSvc.queryBlogTotalCount();
			out.println(count);
			
			return;
		}
		//如果是请求查询某一页的博客
		String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
		if(queryOnePage.equals(requestType)) {
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));

			ArrayList<BlogArticleVo> bVos = bSvc.queryBlogArticlePerPage(pageSize, pageNo);
			String json =JSONArray.fromObject(bVos).toString();
			
			out.println(json);
			
			return;
		}
		//如果是请求查询所有博客
		String queryAllBlog = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
		if(queryAllBlog.equals(requestType)) {
			ArrayList<BlogArticleVo> aVos = bSvc.queryBlogArticles();
			String json =JSONArray.fromObject(aVos).toString();
			
			out.println(json);
			
			return;
		}
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}