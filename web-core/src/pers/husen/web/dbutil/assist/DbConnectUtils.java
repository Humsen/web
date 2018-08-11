package pers.husen.web.dbutil.assist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.constants.CommonConstants;
import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.config.ProjectDeployConfig;


/**
 * 数据库连接工具类
 * 
 * @author 何明胜
 *
 * 2017年9月17日
 */

public class DbConnectUtils {
	/**
	 * 日志记录器
	 */
	private static final Logger logger = LogManager.getLogger(DbConnectUtils.class.getName());

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			String url = null;
			//正式数据库和测试数据不一样
			String dbName = null;
			String username = null;
			String password = null;
			String driveClass = null;
			
			//读取数据库连接文件
			Properties properties = new Properties();
			
			FileInputStream inputStream = new FileInputStream(ProjectDeployConfig.WEB_ROOT_PATH + CommonConstants.DB_CONNECT_INFO_FILE_RELATIVE_PATH);
			properties.load(inputStream);
			dbName = properties.getProperty("dbname_test");
		
			//如果是在服务器环境,则加载正式数据库
			if(ProjectDeployConfig.IS_REMOTE_DEPLOY) {
				inputStream = new FileInputStream(ProjectDeployConfig.WEB_ROOT_PATH + CommonConstants.DB_CONNECT_INFO_FILE_RELATIVE_PATH);
				properties.load(inputStream);
				dbName = properties.getProperty("dbname_official");
			}
		
			//获取公共属性
			url = properties.getProperty("url_psql");
			username = properties.getProperty("username_webuser");
			password = properties.getProperty("password_webuser");
			driveClass = properties.getProperty("driver_class");
			inputStream.close();
			
			Class.forName(driveClass);
			connection = DriverManager.getConnection(url+dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false", username, password);
			//logger.info("成功获取数据库连接, url->" + (url+dbName) + ", username->" + username + ", password->" + password);
		}catch (ClassNotFoundException e) {
			logger.error(e);
		}catch (SQLException e) {
			logger.error(e);
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return connection;
	}
	
	
	/**
	 * 关闭连接
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void closeResouce(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch (SQLException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}
	}
}