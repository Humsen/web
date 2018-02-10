package pers.husen.web.common.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.config.ProjectDeployConfig;
import pers.husen.web.service.FileDownloadSvc;

/**
 * @author 何明胜
 *
 * 2017年9月29日
 */
public class FileDownloadHandler {
	private final Logger logger = LogManager.getLogger(FileUploadHandler.class.getName());
	
	public void fileDownloadHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8"); 
	    //得到要下载的文件名
        String fileName = request.getParameter("filename");
        //上传的文件都是保存在工程文件的兄弟级文件download目录
        String saveFile = ProjectDeployConfig.DOWNLOAD_PATH;
        File fileSaveRootPath = new File(saveFile);
        //得到要下载的文件
        logger.info("下载文件：" + fileSaveRootPath + "/" + fileName);
        File file = new File(fileSaveRootPath + "/" + fileName);
        //如果文件不存在
        if(!file.exists()){
            return;
        }
        
        HttpSession session = request.getSession();
        FileDownloadSvc fSvc = new FileDownloadSvc();
		// 判断是否已经访问过该页面
		Object counter = session.getAttribute("file_" + fileName);
		if (counter == null) {
			session.setAttribute("file_" + fileName, new Integer(1));
		} else {
			int count = ((Integer) counter).intValue();
			count++;
			session.setAttribute("file_" + fileName, new Integer(count));
		}
		//更新下载次数
		fSvc.updateFileDownCountByUrl(fileName);
		
		//解决空格问题变加号问题
		String fileNameShow = URLEncoder.encode(fileName, "UTF-8");
		fileNameShow = StringUtils.replace(fileNameShow, "+", "%20");  
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + fileNameShow);
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(fileSaveRootPath + "/" + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
	}
}