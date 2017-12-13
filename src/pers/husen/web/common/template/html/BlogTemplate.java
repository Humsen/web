package pers.husen.web.common.template.html;

import java.text.SimpleDateFormat;

import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.BootstrapConstans;
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.service.BlogArticleSvc;

/**
 *
 *
 * @author 何明胜
 *
 * 2017年10月18日
 */
public class BlogTemplate {
	/**
	 * 单独的js和css
	 * @return
	 */
	public static String customizeHeader() {
		String cHeader = ""+
				"<!-- 自定义CSS -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/css/article/article.css\">\r\n" + 
				"<!-- 自定义脚本 -->\r\n" + 
				"<script src=\"/js/article/blog-details.js\"></script>";;
		
		return cHeader;
	}
	
	/**
	 * 查看博客的html body
	 * @return
	 */
	public static String detailBlogBody(BlogArticleVo bVo, boolean isSuperAdmin) {
		//关键字处理
		StringBuffer keyWordsStrBuf = new StringBuffer();
		String blogLabel = bVo.getBlogLabel();
		
		if(blogLabel != null && blogLabel != "") {
			//关键字处理
			String []keyWordsArray = null;
			
			if(blogLabel.indexOf(CommonConstants.ENGLISH_COMMA) != -1) {
				keyWordsArray = blogLabel.split(",");
			}else {
				keyWordsArray = blogLabel.split("\\s+");
			}
			
			for(int index=0; index < keyWordsArray.length; index++){
			    keyWordsStrBuf.append(
			    	"<span class=\"label label-"
			    	+ BootstrapConstans.BOOTSTRAP5COLOR[index] 
			    	+ "\">" + keyWordsArray[index].trim() + "</span> ");
			}
		}
		
		String body = GenericTemplate.topFixedBar()+
				GenericTemplate.loginAndRegister() + 
				"<input id=\"menuBarNo\" type=\"hidden\" value=\"1\" />"+
				"<div id=\"fh5co-page\">\r\n" +
				"	<!-- 左侧导航  -->\r\n" + 
				GenericTemplate.leftBar()+ 
				
				"	<!-- 中间内容  -->" + 
				"	<div id=\"fh5co-main\">\r\n" + 
				"		<div id=\"list_blog\" class=\"fh5co-post\"> \r\n" + 
				"			<!-- js脚本动态添加内容 -->" +
				"			\r\n" +
				"			<div class=\"fh5co-entry\" id=\"content\">" +
				"				<nav>\r\n" + 
				" 					 <ul class=\"pager\">\r\n" + 
										findPreviousBlog(bVo.getBlogId()) +
										findNextBlog(bVo.getBlogId()) +
				"  					</ul>\r\n" + 
				"				</nav>"+	
				"				<div>" +
				"					<h2 class=\"text-align-center\"><input id=\"hiden_blogId\" type=\"hidden\" value="+ bVo.getBlogId() + " />"+
				"						<a href=#>" + bVo.getBlogTitle() + "</a>"+
				"					</h2>"+
				"					<span class=\"fh5co-post-date\">" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(bVo.getBlogDate()) + "</span>"+
				"					<span class=\"fh5co-post-date\">作者:" + bVo.getBlogAuthor() + "</span>"+
				"					<span class=\"fh5co-post-date\">浏览" + bVo.getBlogRead() + "次</span>"+
				"					<span class=\"fh5co-post-date label-lowercase\">关键字：" + keyWordsStrBuf.toString() + "</span>";
		
		if(isSuperAdmin) {
			body += "<a href=\"/module/upload/editor_article.jsp?blogId=" + bVo.getBlogId() + "\" target=\"_blank\" role=\"button\" class=\"btn btn-default btn-sm\">编辑</a> "
				  + "<a id=\"btn_deleteBlog\" href=\"javascript:void(0)\" role=\"button\" class=\"btn btn-danger btn-sm\">删除</a>"; 
		}
		
		body += 
				"					<p>" + bVo.getBlogHtmlContent() +"</p>" +
				"				</div>" +
				/*" 				<nav>\r\n" + 
				"  					<ul class=\"pager\">\r\n" + 
				"    					<li><a href=\"/blog.hms?blogId=" + (bVo.getBlogId()-1) + "\"><span class=\"glyphicon glyphicon-hand-left\" aria-hidden=\"true\"></span> 上一篇</a></li>\r\n" + 
				"    					<li><a href=\"/blog.hms?blogId=" + (bVo.getBlogId()+1) + "\">下一篇 <span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\"></span></a></li>\r\n" + 
				"  					</ul>\r\n" + 
				"				</nav>"+*/
				"			</div>" +
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				GenericTemplate.rightBar() +
				"</div>";
		
		return body;
	}
	
	/**
	 * 查找上一篇博客
	 * @return
	 */
	public static String findPreviousBlog(int blogId) {
		BlogArticleSvc bSvc = new BlogArticleSvc();
		BlogArticleVo bVo = bSvc.queryPreviousBlog(blogId);
		
		String previousBlog = null;
		if(bVo != null && bVo.getBlogId() != 0) {
			previousBlog = "<li class=\"previous\"><a href=\"/blog.hms?blogId=" + bVo.getBlogId() +  "\"><span class=\"glyphicon glyphicon-hand-left\" aria-hidden=\"true\"></span> 上一篇</a></li>\r\n";
		}else {
			previousBlog = "<li class=\"previous disabled\"><a href=\"#\"><span class=\"glyphicon glyphicon-hand-left\" aria-hidden=\"true\"></span> 上一篇</a></li>\r\n";
		}
		
		return previousBlog;
	}
	
	/**
	 * 查找下一篇博客
	 * @return
	 */
	public static String findNextBlog(int blogId) {
		BlogArticleSvc bSvc = new BlogArticleSvc();
		BlogArticleVo bVo = bSvc.queryNextBlog(blogId);
		
		String nextBlog = null;
		if(bVo != null && bVo.getBlogId() != 0) {
			nextBlog = "<li class=\"next\"><a href=\"/blog.hms?blogId=" + bVo.getBlogId() + "\">下一篇 <span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\"></span></a></li>\r\n";
		}else {
			nextBlog = "<li class=\"next disabled\"><a href=\"#\">下一篇 <span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\"></span></a></li>\r\n";
		}
		
		return nextBlog;
	}
}