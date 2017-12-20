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

		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		int codeId = Integer.parseInt(request.getParameter("codeId"));
		CodeLibraryVo cVo = cSvc.queryPerCodeById(codeId);
		
		//判断是否是返回代码json数据
		String returnType = request.getParameter("type");
		if(returnType != null && RequestConstants.REQUEST_TYPE_JSON.equals(returnType)) {
			PrintWriter out = response.getWriter();
			out.println(JSONObject.fromObject(cVo));
			
			return;
		}

		/** 默认返回整篇文章 */
		ReadH5Helper.writeHtmlByName(ResponseConstants.CODE_TEMPLATE_PATH, response);
		/*HttpSession session = request.getSession();
		// 判断是否已经访问过该页面
		Object counter = session.getAttribute("code_" + codeId);
		if (counter == null) {
			session.setAttribute("code_" + codeId, new Integer(1));
			cSvc.updateCodeReadById(codeId);
		} else {
			int count = ((Integer) counter).intValue();
			count++;
			session.setAttribute("code_" + codeId, new Integer(count));
		}

		// 判断是否是管理员登录
		boolean isSuperAdmin = false;

		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if ("username".equals(cookie.getName()) && "super_admin".equals(cookie.getValue())) {
					isSuperAdmin = true;
				}
			}
		}

		String htmlReturn = GenericTemplate.htmlHeader("代码库", cVo.getCodeSummary(), cVo.getCodeLabel()) + GenericTemplate.jsAndCssPlugins()
				+ CodeTemplate.customizeHeader() + GenericTemplate.headBody() 
				+ CodeTemplate.detailCodeBody(cVo, isSuperAdmin) + GenericTemplate.bodyHtml();

		out.println(htmlReturn);*/
	}

	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}