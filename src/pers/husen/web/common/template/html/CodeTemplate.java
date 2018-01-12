package pers.husen.web.common.template.html;

import java.text.SimpleDateFormat;

import pers.husen.web.bean.vo.CodeLibraryVo;
import pers.husen.web.common.constants.BootstrapConstans;
import pers.husen.web.common.constants.CommonConstants;

/**
 * @author 何明胜
 *
 * 2017年10月18日
 */
public class CodeTemplate {
	/**
	 * 查看博客的html body
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
				
		String body =
				"					<h2 class=\"text-align-center\"\"><input id=\"hiden_codeId\" type=\"hidden\" value=" + cVo.getCodeId() + " />"+
				"						<a href=#>" + cVo.getCodeTitle() + "</a>"+
				"					</h2>"+
				"					<span class=\"fh5co-post-date\">" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cVo.getCodeDate()) + "</span>"+
				"					<span class=\"fh5co-post-date\">作者:" + cVo.getCodeAuthor() + "</span>"+
				"					<span class=\"fh5co-post-date\">浏览" + cVo.getCodeRead() + "次</span>" +
				"					<span class=\"fh5co-post-date label-lowercase\">关键字：" + keyWordsStrBuf.toString() + "</span>";
		
		if(isSuperAdmin) {
			body += "<a href=\"/upload/editor_article.jsp?codeId=" + cVo.getCodeId() + "\" target=\"_blank\" role=\"button\" class=\"btn btn-default btn-sm\">编辑</a> "
					+ "<a id=\"btn_deleteCode\" href=\"javascript:void(0)\" role=\"button\" class=\"btn btn-danger btn-sm\">删除</a>";
		}
		
		body +=
				"					<p>" + cVo.getCodeHtmlContent() +"</p>" +
				"				</div>" +
				"			</div>" +
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>";
		
		return body;
	}
}