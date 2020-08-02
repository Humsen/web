package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.dao.BlogArticleDao;
import pers.husen.web.dao.impl.BlogArticleDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月17日
 */
public class BlogArticleSvc implements BlogArticleDao{
	private static final BlogArticleDaoImpl blogArticleDaoImpl = new BlogArticleDaoImpl();
	
	public ArrayList<BlogArticleVo> queryBlogArticles(){
		return blogArticleDaoImpl.queryAllBlogArticles();
	}
	
	@Override
	public int queryBlogTotalCount(BlogArticleVo bVo) {
		return blogArticleDaoImpl.queryBlogTotalCount(bVo);
	}
	
	@Override
	public ArrayList<BlogArticleVo> queryBlogArticlePerPage(BlogArticleVo bVo, int pageSize, int pageNo){
		return blogArticleDaoImpl.queryBlogArticlePerPage(bVo, pageSize, pageNo);
	}
	
	@Override
	public BlogArticleVo queryPerBlogById(int blogId) {
		return blogArticleDaoImpl.queryPerBlogById(blogId);
	}
	
	@Override
	public int insertBlogArticle(BlogArticleVo bVo) {
		return blogArticleDaoImpl.insertBlogArticle(bVo);
	}
	
	@Override
	public int updateBlogReadById(int blogId) {
		return blogArticleDaoImpl.updateBlogReadById(blogId);
	}
	
	@Override
	public int updateBlogById(BlogArticleVo bVo) {
		return blogArticleDaoImpl.updateBlogById(bVo);
	}
	
	@Override
	public int logicDeleteBlogById(int blogId) {
		return blogArticleDaoImpl.logicDeleteBlogById(blogId);
	}

	@Override
	public ArrayList<BlogArticleVo> queryAllBlogArticles() {
		return blogArticleDaoImpl.queryAllBlogArticles();
	}

	@Override
	public BlogArticleVo queryPreviousBlog(int blogId) {
		return blogArticleDaoImpl.queryPreviousBlog(blogId);
	}

	@Override
	public BlogArticleVo queryNextBlog(int blogId) {
		return blogArticleDaoImpl.queryNextBlog(blogId);
	}
}