package pers.husen.web.servlet.codelib;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此处暂未使用
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/CodeLibraryServlet")
public class CodeLibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CodeLibraryServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}