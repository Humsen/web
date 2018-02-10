package pers.husen.web.dao;

import java.util.ArrayList;

import pers.husen.web.bean.vo.ArticleCategoryVo;

/**
 * @desc 文章分类接口
 *
 * @author 何明胜
 *
 * @created 2017年12月12日 上午10:07:03
 */
public interface ArticleCategoryDao {
	/**
	 * 插入新的分类
	 * @param aVo
	 * @return
	 */
	public int insertCategory(ArticleCategoryVo aVo);
	
	/**
	 * 查询当前最大id
	 * @return
	 */
	public int queryMaxId();
	
	/**
	 * 根据文章类别查询分类和相应的数量
	 * @param classification
	 * @return
	 */
	public ArrayList<ArticleCategoryVo> queryCategory3Num(String classification);
	
	/**
	 * 查询所有分类
	 * @return
	 */
	public ArrayList<ArticleCategoryVo> queryAllCategory();
}