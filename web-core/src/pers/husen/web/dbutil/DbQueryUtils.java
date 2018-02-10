package pers.husen.web.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pers.husen.web.common.helper.StackTrace2Str;
import pers.husen.web.dbutil.assist.DbConnectUtils;
import pers.husen.web.dbutil.assist.SetPsParamUtils;
import pers.husen.web.dbutil.assist.TypeTransformUtils;

/**
 * 数据库查询工具类 DQL
 * 
 * @author 何明胜
 *
 *         2017年9月21日
 */
public class DbQueryUtils {
	private static final Logger logger = LogManager.getLogger(DbQueryUtils.class);

	/**
	 * 根据条件查询 beanVoList
	 * 
	 * @param sql
	 * @param paramList
	 * @param classType
	 *            Vo类 类型
	 * @return
	 */
	public static <T> ArrayList<T> queryBeanListByParam(String sql, ArrayList<Object> paramList, Class<T> classType) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<T> tVos = null;

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			tVos = TypeTransformUtils.resultSet2BeanList(rs, classType);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return tVos;
	}

	/**
	 * 根据条件查询bean
	 * 
	 * @param <T>
	 * 
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public static <T> T queryBeanByParam(String sql, ArrayList<Object> paramList, Class<T> classType) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		T tVo = null;

		try {
			ps = conn.prepareStatement(sql);
			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}

			logger.info(ps);
			rs = ps.executeQuery();
			tVo = TypeTransformUtils.resultSet2Bean(rs, classType);
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return tVo;
	}

	public static int queryIntByParam(String sql, ArrayList<Object> paramList) {
		return ((Number) queryResultByParam(sql, paramList)).intValue();
	}

	public static String queryStringByParam(String sql, ArrayList<Object> paramList) {
		return (String) queryResultByParam(sql, paramList);
	}

	public static Date queryDateByParam(String sql, ArrayList<Object> paramList) {
		java.sql.Date sqlDate = (java.sql.Date) queryResultByParam(sql, paramList);
		Date date = new Date(sqlDate.getTime());
		return date;
	}

	public static boolean queryBooleanByParam(String sql, ArrayList<Object> paramList) {
		return (boolean) queryResultByParam(sql, paramList);
	}

	/**
	 * 根据条件查询某个字段或者数量 String int Date etc.
	 * 
	 * @param sql
	 * @param paramList
	 * @param classType
	 * @return
	 */
	private static Object queryResultByParam(String sql, ArrayList<Object> paramList) {
		Connection conn = DbConnectUtils.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object result = null;

		try {
			ps = conn.prepareStatement(sql);

			if (paramList != null && paramList.size() != 0) {
				for (int i = 0; i < paramList.size(); i++) {
					SetPsParamUtils.setParamInit(i + 1, paramList.get(i), ps);
				}
			}
			logger.info(ps);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getObject(1);
			}
		} catch (Exception e) {
			logger.error(StackTrace2Str.exceptionStackTrace2Str(e));
		} finally {
			DbConnectUtils.closeResouce(rs, ps, conn);
		}

		return result;
	}
}