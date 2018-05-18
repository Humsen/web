package pers.husen.web.common.helper;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式助手
 * 
 * @author 何明胜
 *
 *         2017年9月29日
 */
public class DateFormatHelper {
	public static void main(String[] args) {
		// VisitTotalDaoImpl vImpl = new VisitTotalDaoImpl();
		// vImpl.updateVisitCount();
		// System.out.println(formatDateYMD());
		// System.out.println(new Date());
		System.out.println(DateFormatHelper.secondsTodayTotal());
		System.out.println((12 * 60 + 22) * 60);
		System.out.println(DateFormatHelper.dateNumberFormat());
	}

	public static Date formatNowDateHelper() {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String formatDate = dateFormater.format(date);

		try {
			return dateFormater.parse(formatDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date formatDateYMD() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(0);
		Date currentTime2 = formatter.parse(dateString, pos);
		return currentTime2;
	}

	/**
	 * 从当日0时0分0秒到当前时间的毫秒数
	 * 
	 * @return
	 */
	public static Long secondsTodayTotal() {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());
		Long zeroSeconds = calendar.getTime().getTime();

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Long todaySeconds = zeroSeconds - calendar.getTime().getTime();

		return todaySeconds;
	}

	/**
	 * 获取当前日期数字格式，如20171020为2017年10月20日
	 */
	public static String dateNumberFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		return sdf.format(new Date());
	}
}