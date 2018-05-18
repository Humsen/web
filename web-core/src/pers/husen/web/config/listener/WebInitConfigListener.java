package pers.husen.web.config.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pers.husen.web.config.ProjectDeployConfig;
import pers.husen.web.config.Log4j2Config;

/**
 * web启动时首先启动监听器，初始化一些配置，如路径
 * 
 * @author 何明胜
 *
 *         2017年9月23日
 */
public class WebInitConfigListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		// 获取当前web部署根目录
		String deployRootDir = servletContext.getRealPath("/");
		//将部署根目录赋值给全局变量
		ProjectDeployConfig.setGlobalVariable(deployRootDir);
		//配置log4j
		new Log4j2Config().startLog4j2Config();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
}