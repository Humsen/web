package pers.husen.web.servlet.module;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.husen.web.common.constants.ResponseConstants;
import pers.husen.web.common.helper.ReadH5Helper;

/**
 * @desc 留言区模块
 *
 * @author 何明胜
 *
 * @created 2017年12月20日 下午9:33:48
 */
@WebServlet(urlPatterns = "/module/message.hms")
public class MessageModuleSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageModuleSvt() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ReadH5Helper.writeHtmlByName(ResponseConstants.MESSAGE_MODULE_TEMPLATE_PATH, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
