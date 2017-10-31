package pers.husen.web.servlet.blog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.service.BlogArticleSvc;


/**
 * 查询所有博客的数量
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/BlogTotalCountServlet")
public class BlogTotalCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BlogTotalCountServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		BlogArticleSvc bSvc = new BlogArticleSvc();
		int count = bSvc.queryBlogTotalCount();
		out.println(count);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}