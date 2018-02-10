package pers.husen.web.servlet.image;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.common.handler.ImageDownloadHandler;

/**
 * 图片下载
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet(urlPatterns= {"/imageDownload.hms", "/imageDownload"})
public class ImageDownloadSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageDownloadSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ImageDownloadHandler iHandler = new ImageDownloadHandler();
		iHandler.imageDownloadHandler(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}