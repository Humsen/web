package pers.husen.web.servlet.download;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.bean.vo.FileDownloadVo;
import pers.husen.web.common.handler.FileUploadHandler;
import pers.husen.web.service.FileDownloadSvc;

/**
 * 上传文件
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet(urlPatterns="/fileUpload.hms")
public class FileUploadSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileUploadSvt() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUploadHandler fileUploadHandler = new FileUploadHandler();
		String fileName = fileUploadHandler.fileUploadHandler(request);
		//int insertResult = 0;
		
		//不为null则上传成功
		if(fileName != null) {
			FileDownloadVo fVo = new FileDownloadVo();
			fVo.setFileName(fileName);
			fVo.setFileUrl(fileName);
			fVo.setFileUploadDate(new Date());
			fVo.setFileDownloadCount(0);
			
			FileDownloadSvc fSvc = new FileDownloadSvc();
			fSvc.insertFileDownload(fVo);
		}
		
		response.sendRedirect("/personal_center/mycenter.jsp");
		/*PrintWriter out = response.getWriter();
		out.println(insertResult);*/
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}