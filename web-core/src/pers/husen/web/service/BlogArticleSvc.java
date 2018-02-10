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
	private BlogArticleDaoImpl bImpl = new BlogArticleDaoImpl();
	
	public ArrayList<BlogArticleVo> queryBlogArticles(){
		return bImpl.queryAllBlogArticles();
	}
	
	@Override
	public int queryBlogTotalCount(BlogArticleVo bVo) {
		return bImpl.queryBlogTotalCount(bVo);
	}
	
	@Override
	public ArrayList<BlogArticleVo> queryBlogArticlePerPage(BlogArticleVo bVo, int pageSize, int pageNo){
		return bImpl.queryBlogArticlePerPage(bVo, pageSize, pageNo);
	}
	
	@Override
	public BlogArticleVo queryPerBlogById(int blogId) {
		return bImpl.queryPerBlogById(blogId);
	}
	
	@Override
	public int insertBlogArticle(BlogArticleVo bVo) {
		return bImpl.insertBlogArticle(bVo);
	}
	
	@Override
	public int updateBlogReadById(int blogId) {
		return bImpl.updateBlogReadById(blogId);
	}
	
	@Override
	public int updateBlogById(BlogArticleVo bVo) {
		return bImpl.updateBlogById(bVo);
	}
	
	@Override
	public int logicDeleteBlogById(int blogId) {
		return bImpl.logicDeleteBlogById(blogId);
	}

	@Override
	public ArrayList<BlogArticleVo> queryAllBlogArticles() {
		return bImpl.queryAllBlogArticles();
	}

	@Override
	public BlogArticleVo queryPreviousBlog(int blogId) {
		return bImpl.queryPreviousBlog(blogId);
	}

	@Override
	public BlogArticleVo queryNextBlog(int blogId) {
		return bImpl.queryNextBlog(blogId);
	}
}