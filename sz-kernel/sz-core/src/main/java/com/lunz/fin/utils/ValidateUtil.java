package com.lunz.fin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * @Title:ValidateUtil
 * @Description: 账户相关验证工具类
 * @author chenxiaojun
 */
public class ValidateUtil {
	/**
	 * 正则表达式：验证用户名(匹配大小写字母，下划线_，汉字)
	 */
	public static final String REGEX_USERNAME = "^[a-z0-9A-Z_\\u4e00-\\u9fa5]{2,20}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
	

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((0\\d{2,3}-\\d{7,8})|(1[3|4|5|6|7|8|9][0-9]\\d{8}))$";
	
	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "(.{0})|^([A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4})$";

	/**
	 * 正则表达式：验证空格（两端空格，内容中间空格，匹配一个或者多个空格）
	 */
	public static final String REGEX_BLANK = "^\\s*.*\\s+.*$";

	/**
	 * 正则表达式：不匹配空格
	 */
	public static final String REGEX_NO_BLANK = "^$|^[^\\s]+$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证（非必填）
	 */
	public static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
	
	/**
	 * 银行卡号
	 */
	public static final String REGEX_BANKCARD = "(.{0})|(^\\d{0,32}$)";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 八位日期 20190505
	 */
	public static final String DATE_FORMAT_8 = "yyyyMMdd";

	/**
	 * 十位日期 2019-05-05
	 */
	public static final String DATE_FORMAT_10 = "yyyy-MM-dd";

	/**
	 * 十九位日期 2019-05-05 17:25:18
	 */
	public static final String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 十位日期 ： yyyy-MM-dd
	 */
	public static final String REGEX_DATE_10 = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
	/**
	 * 十九位日期正则：yyyy-MM-dd hh:mm:ss
	 */
	public static final String REGEX_DATE_19 = "(.{0})|((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))";

	
	/**
	 * 正则表达式：验证图片格式：JPG、PNG、BMP、JPEG
	 */
	public static final String REGEX_PICTURE = "^.+(\\.(jpg|jpeg|png|bmp))$";
	
	/**
	 * 逗号
	 */
	public static final String COMMON = ",";

	
	public static void main(String[] args) {
	 	boolean matches = isPicture("0.BMP");
	 	System.out.println(matches);
	}
	
	/**
	 * @Description: 校验用户名
	 */
	public static boolean isUsername(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}
	
	public static boolean isUsernameNoBlank(String username) {
		return Pattern.matches(REGEX_NO_BLANK, username);
	}

	/**
	 * @Description: 校验密码
	 */
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * @Description: 校验手机号
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * @Description: 校验邮箱
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 
	 * @Description: 校验汉字
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}
	/**
	 * @Description: 校验身份证
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * @Description: 校验银行卡号（可为空）
	 */
	public static boolean isBankCard(String bankCard) {
		return Pattern.matches(REGEX_BANKCARD, bankCard);
	}
	
	
	/**
	 * @Description: 校验URL
	 */
	public static boolean isUrl(String url) {
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * @Description: 校验IP地址
	 */
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	/**
	 * @Description: 校验字段最大长度
	 */
	public static boolean isMoreLength(String field, int lenth) {
		return field.length() > lenth ? true : false;
	}

	/**
	 * @Description: 校验字段是否规定长度
	 */
	public static boolean isEqualLength(String field, int lenth) {
		return field.length() == lenth ? true : false;
	}

	/**
	 * @Description: 校验字段最小长度
	 */
	public static boolean isLessLength(String field, int lenth) {
		return field.length() < lenth ? true : false;
	}

	/**
	 * @Description: 校验是否有效日期
	 */
	public static boolean isValidDate(String date, String dateFormate) {

		SimpleDateFormat fmt = new SimpleDateFormat(dateFormate);
		fmt.setLenient(false);
		try {
			fmt.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 * @Description: 字符长度校验
	 */
	public static boolean isValidLength(String field, int... a) {

		String express = "^.{%s,%s}$";
		String regex = "";
		if (a.length == 0) {
			return true;
		} else if (a.length == 1) {
			regex = String.format(express, a[0], "");
		} else {
			regex = String.format(express, a[0], a[1]);
		}
		System.out.println(regex);
		return Pattern.matches(regex, field);
	}
	
	/**
	 *
	 * @Description: 校验图片格式
	 */
	public static boolean isPicture(String url) {
		Pattern pattern = Pattern.compile(REGEX_PICTURE, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(url).matches();
	}

}
