package pers.husen.web.config;

import java.io.File;

import pers.husen.web.common.constants.CommonConstants;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class DeployPathConfig {
	/** 工程部署路径,如 本地为 web\WebContent\, 服务器为/web  **/
	public static File WEB_DEPLOY_PATH;
	
	/** 工程根目录路径 如 /web **/
	public static String WEB_ROOT_PATH;
	
	/** 日志配置文件路径  */
	public static String LOG4J2_CONFIG_PATH;
	
	/** 数据库连接信息文件存放路径 */
	public static String DB_CONNECT_INFO_FILE_PATH;
	
	/** 日志存放路径  */
	public static String LOG4J2_OUT_PATH;
	
	/** 文件上传和下载路径 **/
	public static String DOWNLOAD_PATH;

	/** 图片上传和下载路径 **/
	public static String IMAGE_PATH;
	
	/**
	 * 设置全局变量
	 * 
	 * @param deployPath
	 */
	public static void setGlobalVariable(String deployPath) {
		WEB_DEPLOY_PATH = new File(deployPath);
		WEB_ROOT_PATH = WEB_DEPLOY_PATH.getParent();
		
		LOG4J2_CONFIG_PATH = WEB_ROOT_PATH + CommonConstants.LOG4J2_CONFIG_FILE_RELATIVE_PATH;
		DB_CONNECT_INFO_FILE_PATH = WEB_ROOT_PATH + CommonConstants.DB_CONNECT_INFO_FILE_RELATIVE_PATH;
		
		LOG4J2_OUT_PATH = WEB_ROOT_PATH + CommonConstants.LOG_OUT_FILE_RELATIVE_PATH;
		DOWNLOAD_PATH = WEB_ROOT_PATH + CommonConstants.DOWNLOAD_FILE_RELATIVE_PATH;
		IMAGE_PATH = WEB_ROOT_PATH + CommonConstants.IMAGE_UPLOAD_FILE_PATH;
	}
}