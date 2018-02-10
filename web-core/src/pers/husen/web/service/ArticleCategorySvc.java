package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.ArticleCategoryVo;
import pers.husen.web.dao.ArticleCategoryDao;
import pers.husen.web.dao.impl.ArticleCategoryDaoImpl;

/**
 * @desc 文章分类服务类
 *
 * @author 何明胜
 *
 * @created 2017年12月12日 上午10:38:46
 */
public class ArticleCategorySvc implements ArticleCategoryDao {
	private ArticleCategoryDaoImpl aImpl = new ArticleCategoryDaoImpl();
	
	@Override
	public int insertCategory(ArticleCategoryVo aVo) {
		return aImpl.insertCategory(aVo);
	}
	@Override
	public int queryMaxId() {
		return aImpl.queryMaxId();
	}

	@Override
	public ArrayList<ArticleCategoryVo> queryCategory3Num(String classification) {
		return aImpl.queryCategory3Num(classification);
	}

	@Override
	public ArrayList<ArticleCategoryVo> queryAllCategory() {
		return aImpl.queryAllCategory();
	}
}