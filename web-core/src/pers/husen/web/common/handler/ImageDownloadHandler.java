package pers.husen.web.common.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.config.ProjectDeployConfig;

/**
 * 图片下载
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
public class ImageDownloadHandler {
	private final Logger logger = LogManager.getLogger(ImageUploadHandler.class.getName());
	
	public void imageDownloadHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
	    //得到要下载的url
        String imageUrl = request.getParameter("imageUrl");
        //上传的图片都是保存在工程文件的兄弟级文件images目录下
        String saveFile = ProjectDeployConfig.IMAGE_PATH;
        File fileSaveRootPath = new File(saveFile);
        //得到要下载的文件
        logger.info("下载文件：" + fileSaveRootPath + "/" + imageUrl);
        File file = new File(fileSaveRootPath + "/" + imageUrl);
        //如果文件不存在
        if(!file.exists()){
            return;
        }
        
        //获取文件名
        int index = imageUrl.lastIndexOf("/");
        String imageName = imageUrl.substring(index+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + imageName);
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(fileSaveRootPath + "/" + imageUrl);
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