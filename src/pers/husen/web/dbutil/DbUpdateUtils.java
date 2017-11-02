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
 * @author 何明胜
 *
 * 2017年9月29日
 */
public class DbUpdateUtils {
	private static final Logger logger = LogManager.getLogger(DbUpdateUtils.class.getName());
	
	/**
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static int updateRecordByParam(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps=conn.prepareStatement(sql);
			
			if (paramList != null && paramList.size() != 0) {
				for(int i=0; i<paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i+1, paramList.get(i), ps);
				}
			
			logger.info(ps);
			int result = ps.executeUpdate();
			logger.info("Success update, return value : " + result);
			
			return result;
			}
		}catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		}finally {
			ResultSet rs = null;
			DbConnectUtils.closeResouce(rs, ps, conn);
		}
		return 0;
	}
}