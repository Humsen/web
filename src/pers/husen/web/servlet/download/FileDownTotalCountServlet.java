package pers.husen.web.servlet.download;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.service.FileDownloadSvc;

/**
 * 查询文件所有数量
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/FileDownTotalCountServlet")
public class FileDownTotalCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDownTotalCountServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		FileDownloadSvc fSvc = new FileDownloadSvc();
		int count = fSvc.queryFileTotalCount();
		out.println(count);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}