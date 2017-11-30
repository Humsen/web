package pers.husen.web.servlet.article;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * 代码查询sevlet,如代码总数量、某一页的代码等
 *
 * @author 何明胜
 *
 *         2017年11月7日
 */
@WebServlet(urlPatterns = "/code/query.hms")
public class CodeQuerySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CodeQuerySvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		String requestType = request.getParameter("type");
		String requestKeywords = request.getParameter(RequestConstants.PARAM_KEYWORDS);
		requestKeywords = (requestKeywords == null ? "" : URLDecoder.decode(requestKeywords,"utf-8"));
		
		CodeLibraryVo cVo = new CodeLibraryVo();
		cVo.setCodeTitle(requestKeywords);
		
		// 如果是请求查询总共数量
		String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
		if (queryTotalCount.equals(requestType)) {
			int count = cSvc.queryCodeTotalCount(cVo);
			out.println(count);

			return;
		}
		// 如果是查询某一页的代码
		String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
		if (queryOnePage.equals(requestType)) {
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));

			ArrayList<CodeLibraryVo> cVos = cSvc.queryCodeLibraryPerPage(cVo, pageSize, pageNo);
			String json = JSONArray.fromObject(cVos).toString();

			out.println(json);

			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}