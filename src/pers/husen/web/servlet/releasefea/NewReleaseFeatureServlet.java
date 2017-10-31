package pers.husen.web.servlet.releasefea;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.ReleaseFeatureVo;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.ReleaseFeatureSvc;

/**
 * 上传新的版本特性
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/NewReleaseFeatureServlet")
public class NewReleaseFeatureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewReleaseFeatureServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newArticle = request.getParameter("newArticle");
		JSONObject jsonObject = JSONObject.fromObject(newArticle);
		ReleaseFeatureVo rVo = TypeConvertHelper.jsonObj2ReleaseBean(jsonObject);
		ReleaseFeatureSvc rSvc = new ReleaseFeatureSvc();
		int insertResult = rSvc.insertReleaseFeature(rVo);
		
		PrintWriter out = response.getWriter();
		out.println(insertResult);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}