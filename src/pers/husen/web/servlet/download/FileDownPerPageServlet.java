package pers.husen.web.servlet.download;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.service.FileDownloadSvc;

/**
 * 显示一页的文件下载
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/FileDownPerPageServlet")
public class FileDownPerPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileDownPerPageServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		FileDownloadSvc fSvc = new FileDownloadSvc();
		ArrayList<FileDownloadVo> fVos = fSvc.queryFileDownlaodPerPage(pageSize, pageNo);
		
		String json =JSONArray.fromObject(fVos).toString();
		
		out.println(json);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}