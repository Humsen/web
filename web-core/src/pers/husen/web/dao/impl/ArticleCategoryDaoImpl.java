package pers.husen.web.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.DbConstans;
import pers.husen.web.dao.ArticleCategoryDao;
import pers.husen.web.dbutil.DbManipulationUtils;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.mappingdb.ArticleCategoryMapping;
import pers.husen.web.dbutil.mappingdb.BlogDetailsMapping;
import pers.husen.web.dbutil.mappingdb.CodeLibraryMapping;
import pers.husen.web.service.BlogArticleSvc;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * @desc 文章目录
 *
 * @author 何明胜
 *
 * @created 2017年12月12日 上午10:13:12
 */
public class ArticleCategoryDaoImpl implements ArticleCategoryDao {
	@Override
	public int insertCategory(ArticleCategoryVo aVo) {
		String sql = "INSERT INTO " + ArticleCategoryMapping.DB_NAME + " (" + ArticleCategoryMapping.CATEGORY_NAME
				+ ", " + ArticleCategoryMapping.CREATE_DATE + ", " + ArticleCategoryMapping.CATEGORY_DELETE
				+ ") VALUES (?, ?, ?)";

		ArrayList<Object> paramList = new ArrayList<Object>();
		Object obj = null;
		paramList.add((obj = aVo.getCategoryName()) != null ? obj : "");
		paramList.add((obj = aVo.getCreateDate()) != null ? obj : new Date());
		paramList.add(DbConstans.FIELD_VALID_FLAG);

		return DbManipulationUtils.insertNewRecord(sql, paramList);
	}

	@Override
	public int queryMaxId() {
		String sql = "SELECT max(" + ArticleCategoryMapping.CATEGORY_ID + ") FROM " + ArticleCategoryMapping.DB_NAME;

		return DbQueryUtils.queryIntByParam(sql, new ArrayList<Object>());
	}

	@Override
	public ArrayList<ArticleCategoryVo> queryCategory3Num(String classification) {
		String dbName = null;
		String cateClass = null;
		String isValid = null;
		int totalNum = 0;

		String classBlog = "blog";
		if (classBlog.equals(classification)) {
			dbName = BlogDetailsMapping.DB_NAME;
			cateClass = BlogDetailsMapping.BLOG_CATEGOTY;
			isValid = BlogDetailsMapping.BLOG_DELETE;
			// 查询总数量
			BlogArticleVo bVo = new BlogArticleVo();
			bVo.setBlogCategory(-1);
			;
			totalNum = new BlogArticleSvc().queryBlogTotalCount(bVo);
		} else {
			dbName = CodeLibraryMapping.DB_NAME;
			cateClass = CodeLibraryMapping.CODE_CATEGORY;
			isValid = CodeLibraryMapping.CODE_DELETE;
			// 查询总数量
			CodeLibraryVo cVo = new CodeLibraryVo();
			cVo.setCodeCategory(-1);
			totalNum = new CodeLibrarySvc().queryCodeTotalCount(cVo);
		}

		String sql = "SELECT " + ArticleCategoryMapping.CATEGORY_ID + ", " + ArticleCategoryMapping.CATEGORY_NAME
				+ ", COUNT(*) AS category_num FROM " + ArticleCategoryMapping.DB_NAME + " LEFT JOIN " + dbName + " ON "
				+ isValid + " = " + DbConstans.FIELD_VALID_FLAG + " AND " + ArticleCategoryMapping.CATEGORY_ID + " = "
				+ cateClass + " WHERE " + cateClass + " IS NOT NULL GROUP BY " + ArticleCategoryMapping.CATEGORY_ID
				+ ", " + ArticleCategoryMapping.CATEGORY_NAME + " ORDER BY " + ArticleCategoryMapping.CATEGORY_ID;

		ArrayList<ArticleCategoryVo> aVos = DbQueryUtils.queryBeanListByParam(sql, new ArrayList<Object>(),
				ArticleCategoryVo.class);

		// 判断是否还存在未分类的
		// 如果存在，设置该数量为总共梳理
		// 如果不存在，在最前面新增一个
		if (aVos.get(0).getCategoryId() == 0) {
			aVos.get(0).setCategoryNum(totalNum);
		} else {
			ArticleCategoryVo aVo = new ArticleCategoryVo();
			aVo.setCategoryId(0);
			aVo.setCategoryName("所有文章");
			aVo.setCategoryNum(totalNum);

			aVos.add(0, aVo);
		}

		return aVos;
	}

	@Override
	public ArrayList<ArticleCategoryVo> queryAllCategory() {
		String sql = "SELECT " + ArticleCategoryMapping.CATEGORY_ID + ", " + ArticleCategoryMapping.CATEGORY_NAME
				+ " FROM " + ArticleCategoryMapping.DB_NAME + " ORDER BY " + ArticleCategoryMapping.CATEGORY_ID;

		return DbQueryUtils.queryBeanListByParam(sql, new ArrayList<Object>(), ArticleCategoryVo.class);
	}
}