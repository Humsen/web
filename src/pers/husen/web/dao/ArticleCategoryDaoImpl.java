package pers.husen.web.dao;

import java.util.ArrayList;
import java.util.Date;

import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.common.constants.DbConstans;
import pers.husen.web.dbutil.DbManipulationUtils;
import pers.husen.web.dbutil.DbQueryUtils;
import pers.husen.web.dbutil.mappingdb.ArticleCategoryMapping;
import pers.husen.web.dbutil.mappingdb.BlogDetailsMapping;
import pers.husen.web.dbutil.mappingdb.CodeLibraryMapping;

/**
 * @desc TODO
 *
 * @author 何明胜
 *
 * @created 2017年12月12日 上午10:13:12
 */
public class ArticleCategoryDaoImpl implements ArticleCategoryDao {
	@Override
	public int insertCategory(ArticleCategoryVo aVo) {
		String sql = "INSERT INTO " + ArticleCategoryMapping.DB_NAME + " ("
				+ ArticleCategoryMapping.CATEGORY_NAME + ", "
				+ ArticleCategoryMapping.CREATE_DATE + ", "
				+ ArticleCategoryMapping.CATEGORY_DELETE
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
		String sql = "SELECT max(" + ArticleCategoryMapping.CATEGORY_ID + ") FROM "
				+ ArticleCategoryMapping.DB_NAME;
		
		return DbQueryUtils.queryIntByParam(sql, new ArrayList<Object>());
	}

	@Override
	public ArrayList<ArticleCategoryVo> queryCategory3Num(String classification) {
		String dbName = null;
		String cateClass = null;
		String isValid = null;
		
		if("blog".equals(classification)) {
			dbName = BlogDetailsMapping.DB_NAME;
			cateClass = BlogDetailsMapping.BLOG_CATEGOTY;
			isValid = BlogDetailsMapping.BLOG_DELETE;
		}else {
			dbName = CodeLibraryMapping.DB_NAME;
			cateClass = CodeLibraryMapping.CODE_CATEGORY;
			isValid = CodeLibraryMapping.CODE_DELETE;
		}
		
		String sql = "SELECT " + 
				ArticleCategoryMapping.CATEGORY_ID + ", " +
				ArticleCategoryMapping.CATEGORY_NAME + 
				", COUNT(*) AS category_num FROM " + 
				ArticleCategoryMapping.DB_NAME + 
				" LEFT JOIN " + dbName +
				" ON " +
				isValid + " = " + DbConstans.FIELD_VALID_FLAG + " AND " +
				ArticleCategoryMapping.CATEGORY_ID + " = " + cateClass + 
				" WHERE " + cateClass + " IS NOT NULL GROUP BY " +
				ArticleCategoryMapping.CATEGORY_ID + ", " +
				ArticleCategoryMapping.CATEGORY_NAME +
				" ORDER BY " + 
				ArticleCategoryMapping.CATEGORY_ID;
		
		return DbQueryUtils.queryBeanListByParam(sql, new ArrayList<Object>(), ArticleCategoryVo.class);
	}

	@Override
	public ArrayList<ArticleCategoryVo> queryAllCategory() {
		String sql = "SELECT " + 
				ArticleCategoryMapping.CATEGORY_ID + ", " +
				ArticleCategoryMapping.CATEGORY_NAME + 
				" FROM " + 
				ArticleCategoryMapping.DB_NAME + 
				" ORDER BY " + 
				ArticleCategoryMapping.CATEGORY_ID;
		
		return DbQueryUtils.queryBeanListByParam(sql, new ArrayList<Object>(), ArticleCategoryVo.class);
	}
}