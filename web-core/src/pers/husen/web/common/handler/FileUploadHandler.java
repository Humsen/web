package pers.husen.web.common.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.config.ProjectDeployConfig;

/**
 * @author 何明胜
 *
 * 2017年9月29日
 */
public class FileUploadHandler {
	private final Logger logger = LogManager.getLogger(FileUploadHandler.class.getName());
	
	public String fileUploadHandler(HttpServletRequest request) {
        //得到上传文件的保存目录，将上传的文件存放于工程文件的兄弟级文件download目录下
        String saveFile = ProjectDeployConfig.DOWNLOAD_PATH;
        File file = new File(saveFile);
        
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            logger.info(saveFile+"目录不存在，已经创建");
            //创建目录
            file.mkdir();
        }
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return null;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    logger.info("普通表单（暂不处理）：" + name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //判断文件是否已经存在
                    while(true) {
                    	File tempFile = new File(saveFile + "/" + filename);
                    	if(tempFile.exists()) {
                    		//获取扩展名
                    		int index = filename.lastIndexOf(".");
                    			String fileHeadName = filename.substring(0, index);
                        		String fileBackName = filename.substring(index+1);
                        		//重命名文件名
                        		filename = fileHeadName + "1." + fileBackName; 
                    	}else {
                    		break;
                    	}
                    }
                    
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(saveFile + "/" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    logger.info("上传文件名称："+filename);
                    return filename;
                }
            }
        }catch (Exception e) {
            logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
        }
        
        return null;
	}
}