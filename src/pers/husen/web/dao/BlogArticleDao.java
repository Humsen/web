package pers.husen.web.dao;

import java.util.ArrayList;

import pers.husen.web.bean.vo.BlogArticleVo;

/**
 * 博客文章接口
 * 
 * @author 何明胜
 *
 * 2017年9月17日
 */

public interface BlogArticleDao {
	/** 
	 * 查询博客文章
	 * 
	 * @return 博客文章数组
	 */
	public ArrayList<BlogArticleVo> queryAllBlogArticles();
	
	/** 
	 * 查询博客数量
	 * 
	 * @return 博客数量
	 */
	public int queryBlogTotalCount();
	
	/** 
	 * 按照页面大小和页码查询博
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @return 分页后一页的所有博客
	 */
	public ArrayList<BlogArticleVo> queryBlogArticlePerPage(int pageSize, int pageNo);
	
	/** 
	 * 根据id查询单独的一篇博客
	 *  
	 * @param blogId
	 * @return 一篇博客
	 */
	public BlogArticleVo queryPerBlogById(int blogId);
	
	/** 
	 * 插入记录
	 *  
	 * @param bVo
	 * @return 返回插入结果
	 */
	public int insertBlogArticle(BlogArticleVo bVo);
	
	/**
	 * 根据id更新博客阅读次数
	 * @param blogId
	 * @return
	 */
	public int updateBlogReadById(int blogId);
	
	/**
	 * 根据id修改博客内容
	 * @param bVo
	 * @return
	 */
	public int updateBlogById(BlogArticleVo bVo);
}