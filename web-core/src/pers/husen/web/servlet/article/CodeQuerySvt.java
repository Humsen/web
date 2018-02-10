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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		PrintWriter out = response.getWriter();
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		String requestType = request.getParameter(RequestConstants.PARAM_TYPE);
		String requestKeywords = request.getParameter(RequestConstants.PARAM_KEYWORDS);
		requestKeywords = (requestKeywords == null ? "" : URLDecoder.decode(requestKeywords,"utf-8"));
		String category = request.getParameter(RequestConstants.PARAM_CATEGORY);
		
		CodeLibraryVo cVo = new CodeLibraryVo();
		cVo.setCodeTitle(requestKeywords);
		if(category != null && category.trim() != "") {
			cVo.setCodeCategory(Integer.parseInt(category));
		}else {
			cVo.setCodeCategory(-1);
		}
		
		/** 如果是请求查询总共数量 */
		String queryTotalCount = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_TOTAL_NUM;
		if (queryTotalCount.equals(requestType)) {
			int count = cSvc.queryCodeTotalCount(cVo);
			out.println(count);

			return;
		}
		/** 如果是查询某一页的代码 */
		String queryOnePage = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ONE_PAGE;
		if (queryOnePage.equals(requestType)) {
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));

			ArrayList<CodeLibraryVo> cVos = cSvc.queryCodeLibraryPerPage(cVo, pageSize, pageNo);
			String json = JSONArray.fromObject(cVos).toString();

			out.println(json);

			return;
		}
		/** 如果是查询上一篇有效代码 */
		String queryPrevious = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_PREVIOUS;
		if (queryPrevious.equals(requestType)) {
			int codeId = Integer.parseInt(request.getParameter("codeId"));
			cVo = cSvc.queryPreviousCode(codeId);

			int previousCode = 0;
			if (cVo != null && cVo.getCodeId() != 0) {
				previousCode = cVo.getCodeId();
			}
			
			out.println(previousCode);
			
			return;
		}
		/** 如果是查询上一篇有效代码 */
		String queryNext = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_NEXT;
		if (queryNext.equals(requestType)) {
			int codeId = Integer.parseInt(request.getParameter("codeId"));
			cVo = cSvc.queryNextCode(codeId);

			int nextCode = 0;
			if (cVo != null && cVo.getCodeId() != 0) {
				nextCode = cVo.getCodeId();
			}
			
			out.println(nextCode);
			
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}