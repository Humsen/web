package pers.husen.web.common.constants;

import pers.husen.web.config.ProjectDeployConfig;

/**
 * 后端返回前端常量
 *
 * @author 何明胜
 *
 * 2017年11月8日
 */
public class ResponseConstants {
	/** 操作成功(如果是请求数量, 直接返回数量. 不为-1都为成功)  */
	public static final int RESPONSE_OPERATION_SUCCESS = 1;
	/** 操作失败 (不用0是因为如果有数量请求, 0不知道是失败还是成功)*/
	public static final int RESPONSE_OPERATION_FAILURE = -1;
	
	/** 返回博客的模板地址 **/
	public static final String BLOG_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/blog/blog_template.html";
	/** 返回代码的模板地址 **/
	public static final String CODE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/code/code_template.html";
	
	/** 返回博客模块的模板地址 **/
	public static final String BLOG_MODULE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/blog/blog.html";
	/** 返回博客模块的模板地址 **/
	public static final String CODE_MODULE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/code/code.html";
	/** 返回博客模块的模板地址 **/
	public static final String CONTACT_MODULE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/contact/contact.html";
	/** 返回博客模块的模板地址 **/
	public static final String DOWNLOAD_MODULE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/download/download.html";
	/** 返回博客模块的模板地址 **/
	public static final String MESSAGE_MODULE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/topic/message/message.html";
	
	/** 返回个人中心的模板地址 **/
	public static final String USER_CENTER_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/personal_center/usercenter.html";
	
	/** 返回编辑文章的模板地址 **/
	public static final String EDITOR_ARTICLE_TEMPLATE_PATH = ProjectDeployConfig.WEB_DEPLOY_PATH + "/module/upload/editor.html";
}