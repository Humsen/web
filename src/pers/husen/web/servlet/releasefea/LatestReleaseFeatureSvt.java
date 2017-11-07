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
@WebServlet(urlPatterns="/latestReleaseFeature.hms")
public class LatestReleaseFeatureSvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LatestReleaseFeatureSvt() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		ReleaseFeatureSvc rSvc = new ReleaseFeatureSvc();
		ReleaseFeatureVo rVo = rSvc.queryLatestReleaseFeature();
		String json = JSONObject.fromObject(rVo).toString();
		out.println(json);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}