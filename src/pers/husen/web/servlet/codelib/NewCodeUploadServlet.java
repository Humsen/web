package pers.husen.web.servlet.codelib;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * 上传新的代码库
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/NewCodeUploadServlet")
public class NewCodeUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewCodeUploadServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newArticle = request.getParameter("newArticle");
		JSONObject jsonObject = JSONObject.fromObject(newArticle);
		
		CodeLibraryVo cVo = TypeConvertHelper.jsonObj2CodeBean(jsonObject);
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		int insertResult = cSvc.insertCodeLibrary(cVo);
		
		PrintWriter out = response.getWriter();
		out.println(insertResult);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}