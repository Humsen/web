package pers.husen.web.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.dbutil.assist.DbConnectUtils;
import pers.husen.web.dbutil.assist.SetPsParamUtils;

/**
 * 数据库操纵工具类 DML
 * 
 * @author 何明胜
 *
 * 2017年9月29日
 */
public class DbManipulationUtils {
	private static final Logger logger = LogManager.getLogger(DbManipulationUtils.class.getName());
	
	/**
	 * 插入新纪录
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int insertNewRecord(String sql, ArrayList<Object> paramList) {
		return dbManipulation(sql, paramList);
	}
	
	/**
	 * 执行更新
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int updateRecordByParam(String sql, ArrayList<Object> paramList) {
		return dbManipulation(sql, paramList);
	}
	
	/**
	 * 逻辑删除
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int deleteRecordByParamLogic (String sql, ArrayList<Object> paramList) {
		return dbManipulation(sql, paramList);
	}
	
	/**
	 * 物理删除
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int delteleRecordByParamPhysical (String sql, ArrayList<Object> paramList) {
		return dbManipulation(sql, paramList);
	}
	
	/**
	 * 数据库更新通用
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int dbManipulation(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		int result = -1;
		
		try {
			ps=conn.prepareStatement(sql);
			
			if (paramList != null && paramList.size() != 0) {
				for(int i=0; i<paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i+1, paramList.get(i), ps);
				}
			
			logger.info(ps);
			result = ps.executeUpdate();
			}
		}catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			ResultSet rs = null;
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		
		return result;
	}
}