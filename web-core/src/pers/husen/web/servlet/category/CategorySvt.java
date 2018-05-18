package pers.husen.web.servlet.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.common.constants.RequestConstants;
import pers.husen.web.service.ArticleCategorySvc;

/**
 * @desc 文章目录servlet,查询、插入
 *
 * @author 何明胜
 *
 * @created 2017年12月12日 上午10:45:48
 */
@WebServlet(urlPatterns = "/category.hms")
public class CategorySvt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategorySvt() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		ArticleCategorySvc aSvc = new ArticleCategorySvc();
		String requestType = request.getParameter(RequestConstants.PARAM_TYPE);

		/** 如果是创建新的分类 **/
		if (RequestConstants.REQUEST_TYPE_CREATE.equals(requestType)) {
			ArticleCategoryVo aVo = new ArticleCategoryVo();
			aVo.setCategoryName(request.getParameter("cateName"));
			aVo.setCreateDate(new Date());

			int resultInsert = aSvc.insertCategory(aVo);

			if (resultInsert == 1) {
				int curCateId = aSvc.queryMaxId();
				out.println(curCateId);

				return;
			}
		}

		/** 如果是查询文章分类（数量不为0的） **/
		String queryCategory = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_CATEGORY;
		if (queryCategory.equals(requestType)) {
			String classification = request.getParameter("class");
			ArrayList<ArticleCategoryVo> aVos = aSvc.queryCategory3Num(classification);
			String json = JSONArray.fromObject(aVos).toString();

			out.println(json);

			return;
		}
		
		/** 如果是查询所有分类 **/
		String queryAll = RequestConstants.REQUEST_TYPE_QUERY + RequestConstants.MODE_ALL;
		if(queryAll.equals(requestType)) {
			ArrayList<ArticleCategoryVo> aVos = aSvc.queryAllCategory();
			String json = JSONArray.fromObject(aVos).toString();

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