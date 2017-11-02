package pers.husen.web.servlet.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.service.MessageAreaSvc;

/**
 * 获取留言区留言
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/MessageGetServlet")
public class MessageGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageGetServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		//获取id
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		
		MessageAreaSvc mSvc = new MessageAreaSvc();
		ArrayList<MessageAreaVo> mVos = mSvc.queryAllMessageArea(messageId);

		String json =JSONArray.fromObject(mVos).toString();
		out.println(json);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}