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
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.ReleaseFeatureSvc;

/**
 * 上传新的版本特性
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet(urlPatterns="/editRlseFetr.hms")
public class EditRlseFetrSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditRlseFetrSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newArticle = request.getParameter("newArticle");
		JSONObject jsonObject = JSONObject.fromObject(newArticle);
		ReleaseFeatureVo rVo = TypeConvertHelper.jsonObj2ReleaseBean(jsonObject);
		
		ReleaseFeatureSvc rSvc = new ReleaseFeatureSvc();
		PrintWriter out = response.getWriter();
		int result = 0;
		
		String requestType = request.getParameter("type");
		/** 如果是请求创建新版本 */
		if(RequestConstants.REQUEST_TYPE_CREATE.equals(requestType)) {
			result = rSvc.insertReleaseFeature(rVo);
			
			out.println(result);
			
			return;
		}
		/** 如果是请求编辑版本 */
		if(RequestConstants.REQUEST_TYPE_MODIFY.equals(requestType)) {
			String releaseId = request.getParameter("releaseId");
			if(releaseId != null) {
				rVo.setReleaseId(Integer.parseInt(releaseId));
				result = rSvc.updateReleaseContentById(rVo);
			}
			
			out.println(result);
			
			return;
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}