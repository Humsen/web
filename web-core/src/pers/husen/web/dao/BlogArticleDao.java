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
	 * 根据条件询博客数量
	 * @param bVo
	 * @return
	 */
	public int queryBlogTotalCount(BlogArticleVo bVo);
	
	/**
	 * 按照页面大小和页码查询博客
	 * @param bVo
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public ArrayList<BlogArticleVo> queryBlogArticlePerPage(BlogArticleVo bVo, int pageSize, int pageNo);
	
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
	
	/**
	 * 根据id逻辑删除博客
	 * @param blogId
	 * @return
	 */
	public int logicDeleteBlogById(int blogId);
	
	/**
	 * 根据id查找上一篇有效博客
	 * @param blogId
	 * @return
	 */
	public BlogArticleVo queryPreviousBlog(int blogId);
	
	/**
	 * 根据id查找下一篇有效博客
	 * @param blogId
	 * @return
	 */
	public BlogArticleVo queryNextBlog(int blogId);
}