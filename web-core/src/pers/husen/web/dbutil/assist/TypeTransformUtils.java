package pers.husen.web.dbutil.assist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 博客文章工具类
 * 
 * @author 何明胜
 *
 *         2017年9月21日
 */
public class TypeTransformUtils {
	/**
	 * ResultSET -> beanList
	 * 
	 * @param rs
	 * @param classType
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static <T> ArrayList<T> resultSet2BeanList(ResultSet rs, Class<T> classType)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException,
			InstantiationException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		ArrayList<T> arrayList = new ArrayList<T>();
		// 获取列的数量、类型和属性
		ResultSetMetaData metaData = rs.getMetaData();
		// 获取总列数
		int count = metaData.getColumnCount();
		while (rs.next()) {
			T newInstance = classType.newInstance();
			for (int i = 1; i <= count; i++) {
				// 获取字段名
				String fieldName = metaData.getColumnName(i).toLowerCase();
				// 转换为驼峰形式
				fieldName = underline2Camel(fieldName);
				// 获取字段类型
				Class<?> type = getDeclaredField(classType, fieldName);
				// 拼接set函数名称
				String firstChar = fieldName.substring(0, 1);
				String pascalName = fieldName.replaceFirst(firstChar, firstChar.toUpperCase());
				Method method = classType.getMethod("set" + pascalName, type);
				// 判断读取数据的类型
				if (type.equals(String.class)) {
					method.invoke(newInstance, rs.getString(i));
				} else if (type.equals(int.class) || type.equals(Integer.class)) {
					method.invoke(newInstance, rs.getInt(i));
				} else if (type.equals(Date.class)) {
					// java.sql.Date -> java.util.Date
					Date date = new Date(rs.getTimestamp(i).getTime());
					method.invoke(newInstance, date);
				} else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
					method.invoke(newInstance, rs.getBoolean(i));
				}
			}
			arrayList.add(newInstance);
		}

		return arrayList;
	}

	/**
	 * ResultSET -> bean
	 * 
	 * @param rs
	 * @param classType
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	public static <T> T resultSet2Bean(ResultSet rs, Class<T> classType)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException,
			InstantiationException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		T tVo = classType.newInstance();
		// 获取列的数量、类型和属性
		ResultSetMetaData metaData = rs.getMetaData();
		// 获取总列数
		int count = metaData.getColumnCount();
		if (rs.next()) {
			for (int i = 1; i <= count; i++) {
				// 获取字段名
				String fieldName = metaData.getColumnName(i).toLowerCase();
				// 转换为驼峰形式
				fieldName = underline2Camel(fieldName);
				// 获取字段类型
				Class<?> type = getDeclaredField(classType, fieldName);
				// 拼接set函数名称
				String firstChar = fieldName.substring(0, 1);
				String pascalName = fieldName.replaceFirst(firstChar, firstChar.toUpperCase());
				Method method = classType.getMethod("set" + pascalName, type);
				// 判断读取数据的类型
				if (type.equals(String.class)) {
					method.invoke(tVo, rs.getString(i));
				} else if (type.equals(int.class) || type.equals(Integer.class)) {
					method.invoke(tVo, rs.getInt(i));
				} else if (type.equals(Date.class)) {
					// java.sql.Date -> java.util.Date
					Date date = new Date(rs.getTimestamp(i).getTime());
					method.invoke(tVo, date);
				} else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
					method.invoke(tVo, rs.getBoolean(i));
				}
			}
		}

		return tVo;
	}

	/**
	 * 数据库字段下划线格式转javaBean驼峰格式
	 * 
	 * @param underLine
	 * @return
	 */
	public static String underline2Camel(String underLine) {
		String[] words = underLine.split("_");
		StringBuilder builder = new StringBuilder();
		// 拼接第一个字符
		builder.append(words[0]);

		// 如果数组不止一个单词
		if (words.length > 1) {
			for (int i = 1; i < words.length; i++) {
				// 去掉下划线，首字母变为大写
				String string = words[i];
				String substring = string.substring(0, 1);
				words[i] = string.replaceFirst(substring, substring.toUpperCase());
				builder.append(words[i]);
			}
		}

		return builder.toString();
	}

	/**
	 * 根据字段名称获取当前类及其父类中的变量
	 * @param <T>
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static <T> Class<?> getDeclaredField(Class<T> currClass, String methodName) {
		Class<?> fieldClass = null;

		for (Class<?> clazz = currClass; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				fieldClass = clazz.getDeclaredField(methodName).getType();
				return fieldClass;
			} catch (Exception e) {
				// 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				// 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
			}
		}

		return null;
	}
}