package com.yhzn.common.util.zncb;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class DateUtil {
	/**
	 * 日期相加减
	 * 
	 * @param time 时间字符串 yyyy-MM-dd HH:mm:ss
	 * @param num  加的数，-num就是减去
	 * @return 减去相应的数量的年的日期
	 * @throws ParseException
	 */
	public static Date yearAddNum(Date time, Integer num) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = format.parse(time);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.YEAR, num);
		Date newTime = calendar.getTime();
		return newTime;
	}

	/**
	 * 
	 * @param time 时间
	 * @param num  加的数，-num就是减去
	 * @return 减去相应的数量的月份的日期
	 * @throws ParseException Date
	 */
	public static Date monthAddNum(Date time, Integer num) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = format.parse(time);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.MONTH, num);
		Date newTime = calendar.getTime();
		return newTime;
	}

	/**
	 * 
	 * @param time 时间
	 * @param num  加的数，-num就是减去
	 * @return 减去相应的数量的天的日期
	 * @throws ParseException Date
	 */
	public static Date dayAddNum(Date time, Integer num) {
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = format.parse(time);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		Date newTime = calendar.getTime();
		return newTime;
	}

	/**
	 * 获取本月第一天时间
	 */
	public static Date getMonthStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取本月最后一天
	 * 
	 */
	public static Date getMonthEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
}
