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
import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.MessageAreaVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.MessageAreaSvc;

/**
 * 获取留言区留言
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet(urlPatterns="/message.hms")
public class MessageSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		String requestType = request.getParameter("type");
		/** 如果是获取所有留言, 目前留言是查询所有，然后到前端分页  */
		String queryAllMsg = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
		if(queryAllMsg.equals(requestType)) {
			//获取id
			int messageId = Integer.parseInt(request.getParameter("messageId"));
			
			MessageAreaSvc mSvc = new MessageAreaSvc();
			ArrayList<MessageAreaVo> mVos = mSvc.queryAllMessageArea(messageId);

			String json =JSONArray.fromObject(mVos).toString();
			out.println(json);
			
			return;
		}
		/** 如果是请求上传新留言 */
		String createOneMsg = RequestConstants.REQUEST_TYPE_CREATE + RequestConstants.MODE_ONE;
		if(createOneMsg.equals(requestType)) {
			String newMessage = request.getParameter("newMessage");
			JSONObject jsonObject = JSONObject.fromObject(newMessage);
			MessageAreaVo mVo  = TypeConvertHelper.jsonObj2MessageBean(jsonObject);
			
			//插入数据
			MessageAreaSvc mSvc = new MessageAreaSvc();
			int messageId = mSvc.insertMessageNew(mVo);
			
			JSONObject jsObject = new JSONObject();
			jsObject.put("result", 1);
			jsObject.put("messageId", messageId);
			
			out.println(jsObject);
			
			return;
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}