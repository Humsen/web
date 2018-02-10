package pers.husen.web.common.constants;

/**
 * 通用常量
 *
 * @author 何明胜
 *
 *         2017年10月20日
 */
public class CommonConstants {
	/******************************************************************************
	 * 路径常量
	 ******************************************************************************/
	
	/** 配置文件相对于工程根目录的路径 */
	public static final String CONFIG_FILE_RELATIVE_PATH = "/config";

	/** 数据库连接信息文件相对工程根目录的路径 **/
	public static final String DB_CONNECT_INFO_FILE_RELATIVE_PATH = CONFIG_FILE_RELATIVE_PATH + "/mysql_connect_info.properties";

	/** log4j2配置文件相对于工程根目录的路径  **/
	public static final String LOG4J2_CONFIG_FILE_RELATIVE_PATH = CONFIG_FILE_RELATIVE_PATH + "/log4j2.xml";

	/** 下载文件相对工程根目录的路径 **/
	public static final String DOWNLOAD_FILE_RELATIVE_PATH = "/download";

	/** 图片文件相对工程根目录的路径 */
	public static final String IMAGE_UPLOAD_FILE_PATH = "/images";

	/** 日志文件相对于工程根目录的路径  **/
	public static final String LOG_OUT_FILE_RELATIVE_PATH = "/logs";
	
	/******************************************************************************
	 * 系统常量
	 ******************************************************************************/
	
	/** linux系统根目录路径 **/
	public static final String OS_LINUX = "Linux";

	/** windows 默认系统盘盘符 **/
	public static final String OS_WINDOWS = "Windows";
	
	/** 中文逗号  */
	public static final char CHINESE_COMMA = '，';
	
	/** 英文逗号  */
	public static final char ENGLISH_COMMA = ',';
}