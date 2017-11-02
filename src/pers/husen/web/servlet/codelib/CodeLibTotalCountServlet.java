package pers.husen.web.servlet.codelib;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.service.CodeLibrarySvc;

/**
 * 查询代码库所有数量，用于分页
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/CodeLibTotalCountServlet")
public class CodeLibTotalCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CodeLibTotalCountServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		int count = cSvc.queryCodeTotalCount();
		out.println(count);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}