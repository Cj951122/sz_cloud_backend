package com.lunz.fin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import org.apache.commons.codec.digest.DigestUtils;


/**
 * 
 * 
 * @Title:CommonUtil
 * @Package com.lunz.uc.users.util
 * @Description: 公共工具类
 * @author chenxiaojun
 * @date 2019/04/24
 */
public class CommonUtil {

	/**
	 * 分割-逗号
	 */
	public static final String COMMON_SPLIT = ",";

	/**
	 * 判断对象是否为null和空
	 * 
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isObjectNull(Object object) {
		if (object == null) {
			return true;
		} else if (object instanceof java.util.Collection) {
			return ((java.util.Collection) object).isEmpty() ? true : false;
		} else if (object instanceof java.util.Map) {
			return ((java.util.Map) object).isEmpty() ? true : false;
		} else if (object instanceof String) {
			return ((String) object).trim().length() == 0 ? true : false;
		}
		return false;
	}
	
	/**
	 * 判断对象是否为null字符串和空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullStr(String str) {
		return str.toLowerCase().trim().equals("null");
	}
	
	
	public static List<String> splitStr(String str, String separator) {
		List<String> list = new ArrayList<String>();
		if (CommonUtil.isObjectNull(str)) {
			return null;
		}
		String[] split = str.split(separator);
		list.addAll(Arrays.asList(split));
		return list;
	}

	/**
	 * 生成UUID
	 * @Description: TODO
	 */
	public static String getUUID() {
		
		return UUID.randomUUID().toString();
	}	

	public static String getDate(Date date,String dateFormate) {
		
		SimpleDateFormat fmtDateFormat = new SimpleDateFormat(dateFormate);
		return fmtDateFormat.format(date);
	}
	
	/**
	 *  将字符串日期转化为date
	 * @param date
	 * @param DatePattern
	 * @return
	 * @throws ParseException 
	 */
	public static Date convertToDate(String inputDate,String datePattern){
		SimpleDateFormat formate = new SimpleDateFormat(datePattern);
		formate.setLenient(false); // 日期错误不进位 
		try {
			return formate.parse(inputDate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * chenxiaojun
	 * 判断一个日期是否超过另一个日期固定天数
	 * @return
	 */
	public static boolean compareDate(String firstDate,String secondDate,int shift) {
		
		Date first = convertToDate(firstDate, ValidateUtil.DATE_FORMAT_10);
		Date second = convertToDate(secondDate, ValidateUtil.DATE_FORMAT_10);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(second);
		calendar.add(Calendar.DATE, shift);
		Date now = calendar.getTime();
		
		String format = DateUtils.format(now, ValidateUtil.DATE_FORMAT_10);
		System.out.println(format);
		
		if (first.after(now)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		boolean compareDate = compareDate("2019-07-21", "2019-06-20", 30);
		
		System.out.println(compareDate);
	}
	
}


