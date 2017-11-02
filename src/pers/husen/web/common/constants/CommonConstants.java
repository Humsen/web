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
	public static final String DB_CONNECT_INFO_FILE_RELATIVE_PATH = CONFIG_FILE_RELATIVE_PATH + "/db_connect_info.properties";

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
	public static final char LINUX_ROOT_PATH = '/';

	/** windows 默认系统盘盘符 **/
	public static final char WINDOWS_SYSTEM_DISK_LETTER = 'C';
	
	/******************************************************************************
	 * 前端请求类型常量
	 ******************************************************************************/
	
	/** 类型：客户端要求返回json数据格式的数据  */
	public static final String RETURN_JSON_DATA = "json";
	
	/** 类型：前端请求为修改文章*/
	public static final String REQUEST_MODIFY_ARTICLE = "modify";
	
	/** 类型：前端请求，验证旧密码 */
	public static final String REQUEST_VALIDATE_OLD_PWD = "validate";
	
	/** 类型：前端请求，修改旧密码 */
	public static final String REQUEST_MODIFY_PWD = "modify";
	
	/** 类型：前端请求，发送邮箱验证码 */
	public static final String REQUEST_SEND_EMIAL_CODE = "sendcode";
	
	/** 类型：前端请求，校验验证码 */
	public static final String REQUEST_VALIDATE_RANDOM_CODE = "validate";
	
	/** 模式：找回密码 */
	public static final String REQUEST_RETRIVE_PWD_MODE = "retrivepwd";

	/** 模式：注册新用户 */
	public static final String REQUEST_USER_REGISTER_MODE = "register";
	
	/** 模式：修改邮箱，验证原邮箱 */
	public static final String REQUEST_MODIFY_EMAIL_AUTH_MODE = "modify_email_auth";
	
	/** 模式：修改邮箱，绑定新邮箱 */
	public static final String REQUEST_MODIFY_EMAIL_BIND_MODE = "modify_email_bind";
}