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
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.handler.FileDownloadHandler;
import pers.husen.web.service.FileDownloadSvc;

/**
 * 下载文件
 *
 * @author 何明胜
 *
 *         2017年10月20日
 */
@WebServlet(urlPatterns = "/file/download.hms")
public class FileDownloadSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileDownloadSvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		// 如果是请求下载文件
		String filename = request.getParameter("filename");
		if (filename != null) {
			FileDownloadHandler fileDownloadHandler = new FileDownloadHandler();
			fileDownloadHandler.fileDownloadHandler(request, response);

			return;
		}
		PrintWriter out = response.getWriter();
		String requestType = request.getParameter("type");
		//如果是查询所有文件的数量
		String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
		if(queryTotalCount.equals(requestType)) {
			FileDownloadSvc fSvc = new FileDownloadSvc();
			int count = fSvc.queryFileTotalCount();
			
			out.println(count);
			
			return;
		}
		//如果是查询某一页的文件
		String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
		if(queryOnePage.equals(requestType)) {
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
			
			FileDownloadSvc fSvc = new FileDownloadSvc();
			ArrayList<FileDownloadVo> fVos = fSvc.queryFileDownlaodPerPage(pageSize, pageNo);
			
			String json =JSONArray.fromObject(fVos).toString();
			out.println(json);
			
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}