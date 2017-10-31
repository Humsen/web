package pers.husen.web.servlet.message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.MessageAreaSvc;

/**
 * 上传新的留言
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/MessageUploadServlet")
public class MessageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageUploadServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newMessage = request.getParameter("newMessage");
		JSONObject jsonObject = JSONObject.fromObject(newMessage);
		MessageAreaVo mVo  = TypeConvertHelper.jsonObj2MessageBean(jsonObject);
		//System.out.println(mVo);
		
		//插入数据
		MessageAreaSvc mSvc = new MessageAreaSvc();
		int messageId = mSvc.insertMessageNew(mVo);
		
		JSONObject jsObject = new JSONObject();
		jsObject.put("result", 1);
		jsObject.put("messageId", messageId);
		//System.out.println(jsObject.toString());
		
		PrintWriter out = response.getWriter();
		out.println(jsObject);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}