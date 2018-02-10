package pers.husen.web.config;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.config.listener.WebInitConfigListener;

/**
 * @author 何明胜
 *
 * 2017年9月28日
 */
public class ProjectDeployConfig {
	/** 存储当前工程是本地测试还是远程部署的变量  */
	public static Boolean IS_REMOTE_DEPLOY = null;
	
	
	/** 工程部署路径,如 本地为 web\WebContent\, 服务器为/web  **/
	public static File WEB_DEPLOY_PATH;
	
	/** 工程根目录路径 ,web的同级目录 **/
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
		/**
		 * 根据os.name判断当前是本地开发还是远程部署 
		 * 在centos为 Linux,
		 * 在windows为 Windows 10
		 * 通过修改log4j2.xml的日志文件存放位置的属性，来修改日志保存的位置
		 */
		String currOsName = System.getProperty("os.name");
		
		if (currOsName.indexOf(CommonConstants.OS_LINUX) != -1) {
			IS_REMOTE_DEPLOY = true;
		} else if (currOsName.indexOf(CommonConstants.OS_WINDOWS) != -1) {
			IS_REMOTE_DEPLOY = false;
		} else {
			Logger logger = LogManager.getLogger(WebInitConfigListener.class);
			logger.fatal("识别 user.dir -> " + currOsName + " 出错,使用log4j2默认配置");
		}
		
		/*if(IS_REMOTE_DEPLOY) {
			WEB_DEPLOY_PATH = new File(deployPath);
		}else {
			WEB_DEPLOY_PATH = new File("F:/workspace/workspace pers/web/WebContent/");
		}*/
		WEB_DEPLOY_PATH = new File(deployPath);
		WEB_ROOT_PATH = WEB_DEPLOY_PATH.getParent();
		
		LOG4J2_CONFIG_PATH = WEB_ROOT_PATH + CommonConstants.LOG4J2_CONFIG_FILE_RELATIVE_PATH;
		DB_CONNECT_INFO_FILE_PATH = WEB_ROOT_PATH + CommonConstants.DB_CONNECT_INFO_FILE_RELATIVE_PATH;
		
		LOG4J2_OUT_PATH = WEB_ROOT_PATH + CommonConstants.LOG_OUT_FILE_RELATIVE_PATH;
		DOWNLOAD_PATH = WEB_ROOT_PATH + CommonConstants.DOWNLOAD_FILE_RELATIVE_PATH;
		IMAGE_PATH = WEB_ROOT_PATH + CommonConstants.IMAGE_UPLOAD_FILE_PATH;
	}
}