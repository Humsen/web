package pers.husen.web.common.constants;

/**
 * 前端到后端请求常量
 *
 * @author 何明胜
 *
 * 2017年11月6日
 */
public class RequestConstants {
	/******************************************************************************
	 * 前端请求参数名称
	 ******************************************************************************/
	public static final String PARAM_TYPE = "type";
	public static final String PARAM_KEYWORDS = "keywords";
	public static final String PARAM_CATEGORY = "category";
	
	
	/******************************************************************************
	 * 前端请求类型常量, 先用类型, 如果类型不足以表达含义, 再加个模式
	 ******************************************************************************/
	
	/* 没有组合,只用类型就能解决的放上面. 一旦组合就放下面 */
	
	/** 请求类型：json格式数据 -> 博客、代码等  */
	public static final String REQUEST_TYPE_JSON = "json_return";
	
	/* 前端请求为 类型(动词)+模式(名词),两个单词拼接,前端请求直接拼接两个单词. 如 auth_login为登录验证. 以下的已经统一化   */
	
	/** 请求类型：验证  */
	public static final String REQUEST_TYPE_AUTH = "auth";
	/** 请求类型：修改 -> 密码/邮箱/文章等  */
	public static final String REQUEST_TYPE_MODIFY = "modify";
	/** 请求类型：查询 -> 个人资料等  */
	public static final String REQUEST_TYPE_QUERY = "query";
	/** 请求类型：创建(注册) -> 新用户、留言、文章等  */
	public static final String REQUEST_TYPE_CREATE = "create";
	/** 请求类型：发送验证码 -> 认证邮箱、注册等 */
	public static final String REQUEST_TYPE_SEND_CODE = "send_code";
	/** 请求类型：发送验证码 -> 认证邮箱等 */
	public static final String REQUEST_TYPE_AUTH_CODE = "auth_code";
	/** 请求类型：逻辑删除 -> 博客、代码等 */
	public static final String REQUEST_TYPE_LOGIC_DELETE = "logic_delete";
	/** 请求类型：物理删除 -> 博客、代码等 */
	public static final String REQUEST_TYPE_PHYSICALLY_DELETE = "physically_delete";
	
	/* ********************* 我是分割线  ******************************/
	
	/** 请求模式：登录  */
	public static final String MODE_LOGIN = "_login";
	/** 请求模式：注册  */
	public static final String MODE_REGISTER = "_register";
	/** 请求模式：密码 */
	public static final String MODE_PASSWORD = "_pwd";
	/** 请求模式：用户信息 */
	public static final String MODE_USER_INFO = "_user_info";
	/** 请求模式：旧邮箱 -> 修改密码需要验证 */
	public static final String MODE_OLD_EMAIL = "_old_email";
	/** 请求模式：绑定(新)邮箱 -> 注册、修改密码绑定 */
	public static final String MODE_BIND_EMAIL = "_bind_email";
	/** 请求模式：找回密码 -> 找回密码需要验证码 */
	public static final String MODE_RETRIVE_PWD = "_retrive_pwd";
	/** 请求模式：所有 -> 所有留言、博客等  */
	public static final String MODE_ALL = "_all";
	/** 请求模式：一个(含内容) -> 上传留言、查询某篇博客等  */
	public static final String MODE_ONE = "_one";
	/** 请求模式：一页 -> 博客、代码、下载等分页查询  */
	public static final String MODE_ONE_PAGE = "_one_page";
	/** 请求模式：总数量  */
	public static final String MODE_TOTAL_NUM = "_total_num";
	/** 请求模式：目录 */
	public static final String MODE_CATEGORY = "_category";
	
	/** 请求模式：博客 */
	public static final String MODE_BLOG = "_blog";
	/** 请求模式：代码 */
	public static final String MODE_CODE = "_code";
	/** 请求模式：留言 */
	public static final String MODE_MESSAGE = "_message";
	/** 请求模式：文件 */
	public static final String MODE_FILE = "_file";
	/** 请求模式：上一篇 -> 有效文章等 **/
	public static final String MODE_PREVIOUS = "_previous";
	/** 请求模式：下一篇 -> 有效文章等 **/
	public static final String MODE_NEXT = "_next";
}