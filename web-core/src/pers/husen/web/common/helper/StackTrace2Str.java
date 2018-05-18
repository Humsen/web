package pers.husen.web.common.helper;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 返回程序调用的堆栈轨迹
 *
 * @author 何明胜
 *
 * 2017年10月22日
 */
public class StackTrace2Str {
	/**
	 * 返回 e.printStackTrace()的内容
	 * @param e
	 * @return
	 */
	public static String exceptionStackTrace2Str (Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		// 将出错的栈信息输出到printWriter中
		e.printStackTrace(pw);
		pw.flush();
		sw.flush();

		return sw.toString();
	}
}