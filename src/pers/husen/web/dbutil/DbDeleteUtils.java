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
 * 删除数据看记录
 *
 * @author 何明胜
 *
 * 2017年10月23日
 */
public class DbDeleteUtils {
	public static final Logger logger = LogManager.getLogger(DbDeleteUtils.class.getName());
	
	/**
	 * 删除数据库记录
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int deleteRecordByParam(String sql, ArrayList<Object> paramList) {
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
			logger.info("Success delete, return value : " + result);
			return result;
		}catch (SQLException e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		
		return 0;
	}
}