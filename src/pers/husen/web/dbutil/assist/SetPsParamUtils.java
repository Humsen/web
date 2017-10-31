package pers.husen.web.dbutil.assist;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 设置PreparedStatement参数的工具类
 * 
 * @author : 何明胜
 *
 * 2017年9月21日
 */

public class SetPsParamUtils {
	/**
	 * 判断参数类型并转换
	 * 
	 * @param index
	 * @param obj
	 * @param ps
	 * @throws SQLException
	 */
	public static void setParamInit(int index, Object obj, PreparedStatement ps) throws SQLException {
		if(obj instanceof Integer) {
			setParamInt(index, (Integer) obj, ps);
		}
		if(obj instanceof Long) {
			setParamLong(index, (Long) obj, ps);
		}
		if(obj instanceof Double) {
			setParamDouble(index, (Double) obj, ps);
		}
		if(obj instanceof String) {
			setParamString(index, (String) obj, ps);
		}
		if(obj instanceof Date) {
			setParamDate(index, (Date) obj, ps);
		}
	}
	
	/**
	 * 设置int类型的参数
	 * 
	 * @param index
	 * @param param
	 * @param ps
	 * @throws SQLException
	 */
	public static void setParamInt(int index, Integer param, PreparedStatement ps) throws SQLException {
		ps.setInt(index, param);
	}
	
	/**
	 * 设置Long类型的参数
	 * 
	 * @param index
	 * @param param
	 * @param ps
	 * @throws SQLException
	 */
	public static void setParamLong(int index, Long param, PreparedStatement ps) throws SQLException {
		ps.setLong(index, param);
	}
	
	/**
	 * 设置Double类型的参数
	 * 
	 * @param index
	 * @param param
	 * @param ps
	 * @throws SQLException
	 */
	public static void setParamDouble(int index, Double param, PreparedStatement ps) throws SQLException {
		ps.setDouble(index, param);
	}
	
	/**
	 * 设置String类型的参数
	 * 
	 * @param index
	 * @param param
	 * @param ps
	 * @throws SQLException
	 */
	public static void setParamString(int index, String param, PreparedStatement ps) throws SQLException {
		ps.setString(index, param);
	}
	
	/**
	 * 设置Date->Timestamp类型的参数
	 * 
	 * @param index
	 * @param param
	 * @param ps
	 * @throws SQLException
	 */
	public static void setParamDate(int index, Date param, PreparedStatement ps) throws SQLException {
		Timestamp timestamp = new Timestamp(param.getTime());
		ps.setTimestamp(index, timestamp);
	}
}