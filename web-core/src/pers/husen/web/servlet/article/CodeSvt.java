package pers.husen.web.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.constants.ResponseConstants;
import pers.husen.web.common.helper.ReadH5Helper;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * 根据id查询代码
 *
 * @author 何明胜
 *
 * 2017年11月7日
 */
@WebServlet(urlPatterns="/code.hms")
public class CodeSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CodeSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		
		int codeId = Integer.parseInt(request.getParameter("codeId"));
		CodeLibraryVo cVo = cSvc.queryPerCodeById(codeId);
		
		//判断是否是返回代码json数据
		String returnType = request.getParameter("type");
		if(returnType != null && RequestConstants.REQUEST_TYPE_JSON.equals(returnType)) {
			out.println(JSONObject.fromObject(cVo));
			
			return;
		}

		/** 默认返回整篇文章 */
		response.setContentType("text/html");  
		String resultHtml = ReadH5Helper.modifyHtmlKeywords(ResponseConstants.CODE_TEMPLATE_PATH, cVo.getCodeLabel());
		out.println(resultHtml);
		//增加访问次数
		cSvc.updateCodeReadById(codeId);
	}

	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}