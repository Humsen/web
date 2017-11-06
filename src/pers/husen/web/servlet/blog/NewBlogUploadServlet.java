package pers.husen.web.servlet.blog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import pers.husen.web.bean.vo.BlogArticleVo;
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.common.helper.TypeConvertHelper;
import pers.husen.web.service.BlogArticleSvc;


/**
 * 上传新博客
 *
 * @author 何明胜
 *
 * 2017年10月20日
 */
@WebServlet("/NewBlogUploadServlet")
public class NewBlogUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewBlogUploadServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取文章细节
		String newArticle = request.getParameter("newArticle");
		//转化为json
		JSONObject jsonObject = JSONObject.fromObject(newArticle);
		//转化为bean
		BlogArticleVo bVo = TypeConvertHelper.jsonObj2BlogBean(jsonObject);
		//如果不是以逗号分隔的，关键字之间的多个空格都处理为一个
		String blogLabel = bVo.getBlogLabel();
		if(blogLabel.indexOf(",") == -1 && blogLabel.indexOf("，") == -1) {
			bVo.setBlogLabel(blogLabel.replaceAll("\\s+", " "));
		}
		if(blogLabel.indexOf("，") != -1) {
			bVo.setBlogLabel(blogLabel.replace("，", ","));
		}
		
		BlogArticleSvc bSvc = new BlogArticleSvc();
		PrintWriter out = response.getWriter();
		
		String uploadType = request.getParameter("type");
		//如果是修改博客
		if(uploadType != null && CommonConstants.REQUEST_MODIFY_ARTICLE.equals(uploadType)) {
			//获取id
			int blogId = Integer.parseInt(request.getParameter("articleId"));
			//设置id
			bVo.setBlogId(blogId);
			
			int insertResult = bSvc.updateBlogById(bVo);
			out.println(insertResult);
			
			return;
		}
		
		int insertResult = bSvc.insertBlogArticle(bVo);
		out.println(insertResult);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}