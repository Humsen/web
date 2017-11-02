package pers.husen.web.dbutil.assist;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 辅助工具类
 *
 * @author 何明胜
 *
 *         2017年10月21日
 */
public class AssistUtils {
	/**
	 * 判断查询结果集中是否存在某列
	 * 
	 * @param rs
	 *            查询结果集
	 * @param columnName
	 *            列名
	 * @return true 存在; false 不存在
	 */
	public static boolean isExistColumn(ResultSet rs, String columnName) {
		try {
			if (rs.findColumn(columnName) > 0) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}

		return false;
	}
}