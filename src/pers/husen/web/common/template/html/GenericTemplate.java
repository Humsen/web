package pers.husen.web.common.template.html;

import pers.husen.web.common.constants.CommonConstants;

/**
 * 存放通用的html模板
 *
 * @author 何明胜
 *
 *         2017年10月18日
 */
public class GenericTemplate {
	/**
	 * 通用的js和css plugins.jsp的内容
	 * @return
	 */
	public static String jsAndCssPlugins() {
		String plugins = "<!-- 插件统一放  -->\r\n\r\n" + 
				"<!-- Animate.css -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/template/css/animate.css\">\r\n" + 
				"<!-- Icomoon Icon Fonts-->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/template/css/icomoon.css\">\r\n" + 
				"<!-- Bootstrap  -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/bootstrap/css/bootstrap.min.css\">\r\n" + 
				"<!-- Flexslider  -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/template/css/flexslider.css\">\r\n" + 
				"<!-- Theme style  -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/template/css/style.css\">\r\n" + 
				"<!-- validator -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/validator/css/bootstrapValidator.min.css\" />\r\n" + 
				"<!-- jquery confirm -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/plugins/jqueryconfirm/css/jquery-confirm.min.css\" />\r\n" + 
				"<!-- 左侧菜单栏 -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/css/navigation/left-menu-bar.css\">" +
				"\r\n" + 
				"\r\n" + 
				"<!-- jQuery -->\r\n" + 
				"<script src=\"/plugins/jquery/js/jquery-3.2.1.min.js\"></script>\r\n" + 
				"<!-- jQuery -->\r\n" + 
				"<script src=\"/plugins/jquery/js/jquery.form.min.js\"></script>\r\n" + 
				"<!-- 初始化菜单栏 -->\r\n" + 
				"<script src=\"/js/navigation/left-menu-bar.js\"></script>\r\n" + 
				"<!-- 判断访问类型是电脑还是手机 -->\r\n" + 
				"<script src=\"/js/is-pc-or-mobile.js\"></script>\r\n" + 
				"<!-- jQuery Easing -->\r\n" + 
				"<script src=\"/plugins/jquery/js/jquery.easing.1.3.js\"></script>\r\n" + 
				"<!-- Bootstrap -->\r\n" + 
				"<script src=\"/plugins/bootstrap/js/bootstrap.min.js\"></script>\r\n" + 
				"<!-- Waypoints -->\r\n" + 
				"<script src=\"/plugins/jquery/js/jquery.waypoints.min.js\"></script>\r\n" + 
				"<!-- Flexslider -->\r\n" + 
				"<script src=\"/plugins/jquery/js/jquery.flexslider-min.js\"></script>\r\n" + 
				"<!-- MAIN JS -->\r\n" + 
				"<script src=\"/plugins/template/js/main.js\"></script>\r\n" + 
				"<!-- validator -->\r\n" + 
				"<script src=\"/plugins/validator/js/bootstrapValidator.min.js\"></script>\r\n" + 
				"<script src=\"/js/login/formvalidator.js\"></script>\r\n" + 
				"<!-- JQuery cookie -->\r\n" + 
				"<script src=\"/plugins/jquery/js/jquery.cookie.js\"></script>\r\n" + 
				"<!-- Modernizr JS -->\r\n" + 
				"<script src=\"/plugins/template/js/modernizr-2.6.2.min.js\"></script>\r\n" + 
				"<!-- jquery confirm -->\r\n" + 
				"<script src=\"/plugins/jqueryconfirm/js/jquery-confirm.min.js\"></script>\r\n" + 
				"<!-- 自定义开发工具包  -->\r\n" + 
				"<script src=\"/js/customize-sdk.js\"></script>"; 
		
		return plugins;
	}
	
	/**
	 * 导航栏
	 * @return
	 */
	public static String topFixedBar() {
		String topBar = "<!-- 上部导航栏 -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/css/navigation/topbar.css\">\r\n" + 
				"<script type=\"text/javascript\" src=\"/js/navigation/topbar.js\"></script>\r\n" + 
				"\r\n" + 
				"<nav id=\"topBar\" class=\"navbar navbar-default topbar-nav\">\r\n" + 
				"	<div class=\"container-fluid\">\r\n" + 
				"		<div class=\"navbar-header\">\r\n" + 
				"			<a class=\"navbar-brand navbar-brand-a\" href=\"#\"> <span\r\n" + 
				"				id=\"trademark\" class=\"glyphicon glyphicon-header\" aria-hidden=\"true\"></span>\r\n" + 
				"			</a> <label id=\"accessToday\" class=\"access-today\"></label> &nbsp; <label\r\n" + 
				"				id=\"accessTotal\"></label>\r\n" + 
				"		</div>\r\n" + 
				"\r\n" + 
				"		<label id=\"onlineCurrent\" class=\"online-current\"></label> <a\r\n" + 
				"			id=\"loginBtn\" class=\"btn btn-warning btn-sm topbar-btn-login\"\r\n" + 
				"			href=\"#\" role=\"button\" data-toggle=\"modal\" data-target=\"#login\"\r\n" + 
				"			href=\"\"> <span class=\"glyphicon glyphicon-user\"></span>登录\r\n" + 
				"		</a> <a id=\"registerBtn\" class=\"btn btn-info btn-sm topbar-btn-right\"\r\n" + 
				"			href=\"#\" role=\"button\" data-toggle=\"modal\" data-target=\"#register\"\r\n" + 
				"			href=\"\"> <span class=\"glyphicon glyphicon-log-in\"></span>注册\r\n" + 
				"		</a> <a id=\"persCenterBtn\" class=\"btn btn-success btn-sm topbar-btn-pers\"\r\n" + 
				"			href=\"#\" role=\"button\" data-toggle=\"modal\" data-target=\"#\" href=\"\">\r\n" + 
				"			<span class=\"glyphicon glyphicon-header\"></span>个人中心\r\n" + 
				"		</a> <a id=\"quitLoginBtn\" class=\"btn btn-primary btn-sm topbar-btn-right\"\r\n" + 
				"			href=\"#\" role=\"button\" data-toggle=\"modal\" data-target=\"#\" href=\"\">\r\n" + 
				"			<span class=\"glyphicon glyphicon-log-out\"></span>退出\r\n" + 
				"		</a>\r\n" + 
				"	</div>\r\n" + 
				"</nav>";
		
		return topBar;
	}
	
	/**
	 * 登录注册页面
	 * @return
	 */
	public static String loginAndRegister() {
		String login = "<link rel=\"stylesheet\" href=\"/css/login/login.css\">	\r\n" + 
				"	\r\n" + 
				"<!-- 注册窗口 -->\r\n" + 
				"<div id=\"register\" class=\"modal fade\" tabindex=\"-1\"\r\n" + 
				"	data-backdrop=\"static\" data-keyboard=\"false\">\r\n" + 
				"	<div class=\"modal-dialog\">\r\n" + 
				"		<div class=\"modal-content\">\r\n" + 
				"			<div class=\"modal-body\">\r\n" + 
				"				<button class=\"close\" data-dismiss=\"modal\">\r\n" + 
				"					<span>&times;</span>\r\n" + 
				"				</button>\r\n" + 
				"			</div>\r\n" + 
				"			<div class=\"modal-title\">\r\n" + 
				"				<h1 class=\"text-center login-title\">注册</h1>\r\n" + 
				"			</div>\r\n" + 
				"			<div class=\"modal-body\">\r\n" + 
				"				<div id=\"registerForm\" class=\"form-group\">\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<label for=\"\">用户名</label> <input name=\"username\"\r\n" + 
				"							id=\"txt_userNameRegister\" class=\"form-control\" type=\"text\"\r\n" + 
				"							placeholder=\"5-15位字母或数字\">\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<label for=\"\">密码</label> <input name=\"password\"\r\n" + 
				"							id=\"txt_userPwdRegister\" class=\"form-control\" type=\"password\"\r\n" + 
				"							placeholder=\"至少6位字母或数字\">\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<label for=\"\">再次输入密码</label> <input name=\"confirmPassword\"\r\n" + 
				"							class=\"form-control\" type=\"password\" placeholder=\"至少6位字母或数字\">\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<label for=\"\">邮箱</label> <input id=\"txt_userEmailRegister\"\r\n" + 
				"							name=\"email\" class=\"form-control\" type=\"text\"\r\n" + 
				"							placeholder=\"例如:123@123.com\">\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"text-right\">\r\n" + 
				"						<button id=\"btn_submitRegister\" class=\"btn btn-primary\">提交</button>\r\n" + 
				"						<button id=\"btnCancel\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>\r\n" + 
				"					</div>\r\n" + 
				"					<a href=\"\" data-toggle=\"modal\" data-dismiss=\"modal\"\r\n" + 
				"						data-target=\"#login\">已有账号？点我登录</a>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>\r\n" + 
				"\r\n" + 
				"<!-- 登录窗口 -->\r\n" + 
				"<div id=\"login\" class=\"modal fade\" data-backdrop=\"static\"\r\n" + 
				"	data-keyboard=\"false\">\r\n" + 
				"	<div class=\"modal-dialog\">\r\n" + 
				"		<div class=\"modal-content\">\r\n" + 
				"			<div class=\"modal-body\">\r\n" + 
				"				<button class=\"close\" data-dismiss=\"modal\">\r\n" + 
				"					<span>&times;</span>\r\n" + 
				"				</button>\r\n" + 
				"			</div>\r\n" + 
				"			<div class=\"modal-title\">\r\n" + 
				"				<h1 class=\"text-center login-title\">登录</h1>\r\n" + 
				"			</div>\r\n" + 
				"			<div class=\"modal-body\">\r\n" + 
				"				<div id=\"loginForm\" class=\"form-group\">\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<label for=\"\">用户名</label> <input name=\"username\"\r\n" + 
				"							id=\"txt_userNameLogin\" class=\"form-control\" type=\"text\"\r\n" + 
				"							placeholder=\"\">\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"form-group\">\r\n" + 
				"						<label for=\"\">密码</label> <input id=\"txt_userPwdLogin\"\r\n" + 
				"							name=\"password\" class=\"form-control\" type=\"password\"\r\n" + 
				"							placeholder=\"\">\r\n" + 
				"					</div>\r\n" + 
				"					<div class=\"text-right\">\r\n" + 
				"						<input id=\"btnLogin\" type=\"button\" class=\"btn btn-primary\"\r\n" + 
				"							value=\"登录\"> <input id=\"btnLoginCancel\" type=\"button\"\r\n" + 
				"							class=\"btn btn-danger\" data-dismiss=\"modal\" value=\"取消\">\r\n" + 
				"					</div>\r\n" + 
				"					<a href=\"\" data-toggle=\"modal\" data-dismiss=\"modal\"\r\n" + 
				"						data-target=\"#register\">注册账号</a>\r\n" + 
				"					<a id=\"btn_forgetPwd\" href=\"\" class=\"forget-pwd\" data-dismiss=\"modal\">忘记密码？</a>\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>"+
				" <!-- 右侧固定栏  -->"
				+ rightBar() + 
				"<!-- 底部版权信息 -->"
				+ "<nav class=\"navbar-fixed-bottom navbar-bottom\">\r\n" + 
				"	<span class=\"glyphicon glyphicon-copyright-mark\"></span>"
				+ " 2017&nbsp; 何明胜&nbsp;版权所有&nbsp;渝ICP备16013250号"
				+ "</nav>";
		
		return login;
	}
	
	public static String rightBar() {
		String rightBar = "<!-- 右侧固定栏 -->\r\n" + 
				"<link rel=\"stylesheet\" href=\"/css/navigation/rightbar.css\" />\r\n" + 
				"\r\n" + 
				"<div id=\"rightBar\">\r\n" + 
				"	<div>\r\n" + 
				"		<div class=\"sidebar-module\">\r\n" + 
				"			<h4>关于本站</h4>\r\n" + 
				"			<p>&emsp;&emsp;欢迎来到何明胜的个人网站。本站主要用于记录和分享本人的学习心得和编程经验，并分享常见可复用代码、推荐书籍以及分享软件等资源。</p>\r\n" + 
				"			<p>本站源码已托管github。欢迎访问：<br/>\r\n" + 
				"				<a href=\"https://github.com/HelloHusen/web\" target=\"_blank\">https://github.com/HelloHusen/web</a>\r\n" + 
				"			</p>\r\n" + 
				"		</div>\r\n" + 
				"		<div class=\"sidebar-module\">\r\n" + 
				"			<h4>我的其他主页</h4>\r\n" + 
				"			<ol class=\"list-unstyled\">\r\n" + 
				"				<li><a href=\"http://blog.csdn.net/qq_24879495\" target=\"_blank\">CSDN博客&emsp;一格的程序人生</a></li>\r\n" + 
				"				<li><a\r\n" + 
				"					href=\"http://www.jianshu.com/users/933952e4f1d6/timeline\"\r\n" + 
				"					target=\"_blank\">简书&emsp;一格的博客</a></li>\r\n" + 
				"				<li><a href=\"https://github.com/HelloHusen\" target=\"_blank\">GitHub&emsp;何明胜</a></li>\r\n" + 
				"			</ol>\r\n" + 
				"		</div>\r\n" + 
				"		<div class=\"sidebar-module\">\r\n" + 
				"			<h4>友情链接</h4>\r\n" + 
				"			<ol class=\"list-unstyled\">\r\n" + 
				"				<li><a href=\"http://www.jianshu.com/u/062170d22472\"\r\n" + 
				"					target=\"_blank\">简书 &emsp;鲁莽书生</a></li>\r\n" + 
				"				<!-- <li><a\r\n" + 
				"					href=\"http://www.jianshu.com/users/933952e4f1d6/timeline\" target=\"_blank\">简书\r\n" + 
				"						一格的博客</a></li>\r\n" + 
				"				<li><a href=\"https://github.com/HelloHusen\" target=\"_blank\">GitHub 何明胜</a></li> -->\r\n" + 
				"			</ol>\r\n" + 
				"		</div>\r\n" + 
				"	</div>\r\n" + 
				"</div>";
		
		return rightBar;
	}
}