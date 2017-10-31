package pers.husen.web.service;

import java.util.ArrayList;

import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.dao.impl.BlogArticleDaoImpl;

/**
 * @author 何明胜
 *
 * 2017年9月17日
 */
public class BlogArticleSvc {
	private BlogArticleDaoImpl bImpl = new BlogArticleDaoImpl();
	
	public ArrayList<BlogArticleVo> queryBlogArticles(){
		return bImpl.queryAllBlogArticles();
	}
	
	public int queryBlogTotalCount() {
		return bImpl.queryBlogTotalCount();
	}
	
	public ArrayList<BlogArticleVo> queryBlogArticlePerPage(int pageSize, int pageNo){
		return bImpl.queryBlogArticlePerPage(pageSize, pageNo);
	}
	
	public BlogArticleVo queryPerBlogById(int blogId) {
		return bImpl.queryPerBlogById(blogId);
	}
	
	public int insertBlogArticle(BlogArticleVo bVo) {
		return bImpl.insertBlogArticle(bVo);
	}
	
	public int updateBlogReadById(int blogId) {
		return bImpl.updateBlogReadById(blogId);
	}
	
	public int updateBlogById(BlogArticleVo bVo) {
		return bImpl.updateBlogById(bVo);
	}
}