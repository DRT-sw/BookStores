package utils;

import java.util.Map;
import java.util.Scanner;

import org.apache.commons.beanutils.BeanUtils;


public class Utils {
	/**
	 * @param request
	 * @param bean
	 */
	public static <T> T copyParamToBean(Map value,T bean) {
		try {
			System.out.println("注入之前：" + bean);
//			 populate 把map的值注入到User对象中
			/**
			 * Map中的值是请求的参数。<br/>	
			 * 		key=value
			 * 		刚好是
			 * 		name=value
			 * 要求请求的参数名必须和javaBean的属性名一致！！！ <br/>
			 * 	我们前面使用EL表达式取bean对象的属性值的时候，走的是读方法getXxx<br/>
			 * 	今天我们使用BeaUtils工具类给bean对象赋值的时候，走的是setXxx方法
			 */
			BeanUtils.populate(bean, value);
			System.out.println("注入之后：" + bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 把字符串转换成为int值
	 * 
	 * @param intStr
	 *            字符串
	 * @param defaultValue
	 *            如果转换出现异常。可以返回的默认值
	 * @return 转换后的int值<br/>
	 *         转换失败返回defualtValue值
	 */
	public static int parseInt(String intStr, int defaultValue) {
		try {
			return Integer.parseInt(intStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	/**
	 * 把字符串转换成为long 值
	 * 
	 * @param longStr
	 *            字符串
	 * @param defaultValue
	 *            如果转换出现异常。可以返回的默认值
	 * @return 转换后的long值<br/>
	 *         转换失败返回defualtValue值
	 */
	public static long parseLong(String longStr, long defaultValue) {
		long value = 0;
		try {
			value = Long.parseLong(longStr);
		} catch (Exception e) {
//			e.printStackTrace();
			return defaultValue;
		}
		return value;
	}

	public static double parseDouble(String doubleStr, double defaultValue) {
		double value = 0;
		try {
			value = Double.parseDouble(doubleStr);
		} catch (Exception e) {
//			e.printStackTrace();
			return defaultValue;
		}
		return value;
	}


}
