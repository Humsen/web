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

import pers.husen.web.common.helper.DateFormatHelper;
import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.config.ProjectDeployConfig;

/**
 * 图片上传
 *
 * @author 何明胜
 *
 *         2017年10月20日
 */
public class ImageUploadHandler {
	private final Logger logger = LogManager.getLogger(FileUploadHandler.class.getName());

	public String imageUploadHandler(HttpServletRequest request, String imageDatePath) {
		// 得到上传图片的保存目录，将上传的文件存放于工程文件的兄弟级文件images目录下
		String saveFile = ProjectDeployConfig.IMAGE_PATH + "/" + imageDatePath;
		File file = new File(saveFile);

		// 判断上传图片的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			logger.info("目录不存在，创建目录-> " +saveFile);
			// 创建目录
			file.mkdirs();
		}
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return null;
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					logger.info("普通表单：" + name + "=" + value);
				} else {// 如果fileitem中封装的是上传文件
					String filename = Long.toString(DateFormatHelper.secondsTodayTotal());
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					
					// 获取扩展名
					int index = item.getName().lastIndexOf(".");
					String fileBackName = item.getName().substring(index);
					
					// 判断文件是否已经存在
					while (true) {
						File tempFile = new File(saveFile + "/" + filename + fileBackName);
						if (tempFile.exists()) {
							filename = Long.toString(DateFormatHelper.secondsTodayTotal());
							tempFile = new File(saveFile + "/" + filename + fileBackName);
						} else {
							break;
						}
					}

					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// 创建一个文件输出流
					FileOutputStream out = new FileOutputStream(saveFile + "/" + filename + fileBackName);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					logger.info("上传文件名称：" + filename + fileBackName);
					return filename + fileBackName;
				}
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}

		return null;
	}
}