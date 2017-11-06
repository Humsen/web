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
import pers.husen.web.common.constants.CommonConstants;
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
    	//获取文章细节
    	String newArticle = request.getParameter("newArticle");
    	//转化为json
		JSONObject jsonObject = JSONObject.fromObject(newArticle);
		//转化为bean
		CodeLibraryVo cVo = TypeConvertHelper.jsonObj2CodeBean(jsonObject);
		//如果不是以逗号分隔的，关键字之间的多个空格都处理为一个
		String codeLabel = cVo.getCodeLabel();
		if(codeLabel.indexOf(",") == -1 && codeLabel.indexOf("，") == -1) {
			cVo.setCodeLabel(codeLabel.replaceAll("\\s+", " "));
		}
		if(codeLabel.indexOf("，") != -1) {
			cVo.setCodeLabel(codeLabel.replace("，", ","));
		}
				
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		PrintWriter out = response.getWriter();

		String uploadType = request.getParameter("type");
		//如果是修改代码
		if(uploadType != null && CommonConstants.REQUEST_MODIFY_ARTICLE.equals(uploadType)) {
			//获取id
	    	int codeId = Integer.parseInt(request.getParameter("articleId"));
	    	//设置id
	    	cVo.setCodeId(codeId);
	    	
	    	int resultInsert = cSvc.updateCodeById(cVo);
	    	out.println(resultInsert);
	    	
	    	return;
		}
		
		int insertResult = cSvc.insertCodeLibrary(cVo);
		out.println(insertResult);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}