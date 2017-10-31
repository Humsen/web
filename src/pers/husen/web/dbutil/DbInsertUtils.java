package pers.husen.web.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.dbutil.assist.DbConnectUtils;
import pers.husen.web.dbutil.assist.SetPsParamUtils;

/**
 * 数据库插入工具类
 * 
 * @author : 何明胜
 *
 * 2017年9月21日
 */
public class DbInsertUtils {
	private static final Logger logger = LogManager.getLogger(DbInsertUtils.class.getName());
	
	/**
	 * 插入一篇博客
	 * 
	 * @param sql
	 * @param paramList
	 */
	public static int insertBlogArticle(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps=conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for(int i=0; i<paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i+1, paramList.get(i), ps);
				}
			}
			logger.info(ps);
			int result = ps.executeUpdate();
			logger.info("Success insert, return value : " + result);
			return result;
		}catch (SQLException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		
		return 0;
	}
	
	/**
	 * 插入新的留言
	 * @param sql
	 * @param paramList
	 */
	public static int insertMessageNew(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps=conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for(int i=0; i<paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i+1, paramList.get(i), ps);
				}
			}
			logger.info(ps);
			int result = ps.executeUpdate();
			logger.info("Success insert, return value : " + result);
			//查询当前最大id
			ps=conn.prepareStatement("SELECT MAX(message_id) as maxId FROM message_area");
			logger.info(ps);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("maxId");
			}
			logger.info("SUCCESS query, return value : " + result);
			
			return result;
		}catch (SQLException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		return 0;
	}
	
	/**
	 * 插入新注册的用户信息
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int insertUserInfo(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps=conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for(int i=0; i<paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i+1, paramList.get(i), ps);
				}
			}
			logger.info(ps);
			int result = ps.executeUpdate();
			logger.info("Sucess insert, return value : " + result);
			//查询当前最大id
			ps=conn.prepareStatement("SELECT MAX(user_id) as userId FROM user_info");
			logger.info(ps);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("userId");
			}
			
			logger.info("Success query, return value : " + result);
			
			return result;
		}catch (SQLException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		return 0;
	}
	
	public static int insertNewRecord(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps=conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for(int i=0; i<paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i+1, paramList.get(i), ps);
				}
			}
			logger.info(ps);
			int result = ps.executeUpdate();
			logger.info("Success insert, return value : " + result);
			
			return result;
		}catch (SQLException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		return 0;
	}
}