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
import pers.husen.web.service.ReleaseFeatureSvc;

/**
 * 查询最新的版本特性
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet(urlPatterns="/latestRlseFetr.hms")
public class LatestRlseFetrSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LatestRlseFetrSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		ReleaseFeatureSvc rSvc = new ReleaseFeatureSvc();
		ReleaseFeatureVo rVo; 
		
		String releaseId = request.getParameter("releaseId");
		/** 如果是请求最新版本 id为 null或者0 */
		if(releaseId == null || Integer.parseInt(releaseId) == 0) {
			rVo = rSvc.queryLatestReleaseFeature();
			String json = JSONObject.fromObject(rVo).toString();
			out.println(json);
			
			return;
		}
		/** 如果是请求其他版本 */
		if(releaseId != null && Integer.parseInt(releaseId) != 0) {
			rVo = rSvc.queryReleaseById(Integer.parseInt(releaseId));
			String json = JSONObject.fromObject(rVo).toString();
			out.println(json);
			
			return;
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}