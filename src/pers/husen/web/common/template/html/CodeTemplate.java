package pers.husen.web.common.template.html;

import java.text.SimpleDateFormat;

import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.BootstrapConstans;
import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.service.CodeLibrarySvc;

/**
 * @author 何明胜
 *
 * 2017年10月18日
 */
public class CodeTemplate {
	/**
	 * 单独的js和css
	 * @return
	 */
	public static String customizeHeader() {
		String cHeader = "" + 
				"	<!-- 自定义CSS -->\r\n" + 
				"	<link rel=\"stylesheet\" href=\"/css/article/article.css\">\r\n"+
				"	<!-- 自定义脚本 -->\r\n" + 
				"	<script src=\"/js/article/article-markdown.js\"></script>" +
				"	<script src=\"/js/article/code-details.js\"></script>";
		
		return cHeader;
	}
	
	/**
	 * 查看代码的html body
	 * @return
	 */
	public static String detailCodeBody(CodeLibraryVo cVo, boolean isSuperAdmin) {
		//关键字处理
		StringBuffer keyWordsStrBuf = new StringBuffer();
		String codeLabel = cVo.getCodeLabel();

		if(codeLabel != null && codeLabel != "") {
			//关键字处理
			String []keyWordsArray = null;
			
			if(codeLabel.indexOf(CommonConstants.ENGLISH_COMMA) != -1) {
				keyWordsArray = codeLabel.split(",");
			}else {
				keyWordsArray = codeLabel.split("\\s+");
			}
			
			for(int index=0; index < keyWordsArray.length; index++){
			    keyWordsStrBuf.append(
			    	"<span class=\"label label-"
			    	+ BootstrapConstans.BOOTSTRAP5COLOR[index] 
			    	+ "\">" + keyWordsArray[index].trim() + "</span> ");
			}
		}
				
		String body = GenericTemplate.topFixedBar() +
				GenericTemplate.loginAndRegister()+
				"	<input id=\"menuBarNo\" type=\"hidden\" value=\"2\" />" +
				"<div id=\"fh5co-page\">\r\n" + 
				"	<!-- 左侧导航  -->\r\n" + 
				GenericTemplate.leftBar()+ 
				
				"	<!-- 中间内容  -->" + 
				"	<div id=\"fh5co-main\">\r\n" + 
				"		<div id=\"list_clode\" class=\"fh5co-post\"> \r\n" + 
				"			<!-- js脚本动态添加内容 -->" +
				"			\r\n" +
				"			<div class=\"fh5co-entry\" id=\"content\">" +
				"				<nav>\r\n" + 
				" 					 <ul class=\"pager\">\r\n" + 
										findPreviousCode(cVo.getCodeId()) +
										findNextCode(cVo.getCodeId()) +
				"  					</ul>\r\n" + 
				"				</nav>"+	
				"				<div>" +
				"					<h2 class=\"text-align-center\"\"><input id=\"hiden_codeId\" type=\"hidden\" value=" + cVo.getCodeId() + " />"+
				"						<a href=#>" + cVo.getCodeTitle() + "</a>"+
				"					</h2>"+
				"					<span class=\"fh5co-post-date\">" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cVo.getCodeDate()) + "</span>"+
				"					<span class=\"fh5co-post-date\">作者:" + cVo.getCodeAuthor() + "</span>"+
				"					<span class=\"fh5co-post-date\">浏览" + cVo.getCodeRead() + "次</span>" +
				"					<span class=\"fh5co-post-date label-lowercase\">关键字：" + keyWordsStrBuf.toString() + "</span>";
		
		if(isSuperAdmin) {
			body += "<a href=\"/module/upload/editor_article.jsp?codeId=" + cVo.getCodeId() + "\" target=\"_blank\" role=\"button\" class=\"btn btn-default btn-sm\">编辑</a> "
					+ "<a id=\"btn_deleteCode\" href=\"javascript:void(0)\" role=\"button\" class=\"btn btn-danger btn-sm\">删除</a>";
		}
		
		body +=
				"					<p>" + cVo.getCodeHtmlContent() +"</p>" +
				"				</div>" +
				"			</div>" +
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				GenericTemplate.rightBar() +
				"</div>";
		
		return body;
	}
	
	/**
	 * 查找上一篇有效代码
	 * @return
	 */
	public static String findPreviousCode(int codeId) {
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		CodeLibraryVo cVo = cSvc.queryPreviousCode(codeId);
		
		String previousCode = null;
		if(cVo != null && cVo.getCodeId() != 0) {
			previousCode = "<li class=\"previous\"><a href=\"/code.hms?codeId=" + cVo.getCodeId() +  "\"><span class=\"glyphicon glyphicon-hand-left\" aria-hidden=\"true\"></span> 上一篇</a></li>\r\n";
		}else {
			previousCode = "<li class=\"previous disabled\"><a href=\"#\"><span class=\"glyphicon glyphicon-hand-left\" aria-hidden=\"true\"></span> 上一篇</a></li>\r\n";
		}
		
		return previousCode;
	}
	
	/**
	 * 查找下一篇有效代码
	 * @return
	 */
	public static String findNextCode(int codeId) {
		CodeLibrarySvc cSvc = new CodeLibrarySvc();
		CodeLibraryVo cVo = cSvc.queryPreviousCode(codeId);
		
		String nextCode = null;
		if(cVo != null && cVo.getCodeId() != 0) {
			nextCode = "<li class=\"next\"><a href=\"/code.hms?codeId=" + cVo.getCodeId() + "\">下一篇 <span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\"></span></a></li>\r\n";
		}else {
			nextCode = "<li class=\"next disabled\"><a href=\"#\">下一篇 <span class=\"glyphicon glyphicon-hand-right\" aria-hidden=\"true\"></span></a></li>\r\n";
		}
		
		return nextCode;
	}
}