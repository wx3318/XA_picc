package com.picc.common;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * shiro 工具类
 * 
 * @author TripodFan
 * @date 2018/08/31
 */
public class ShiroUtils {
	private Logger logger = Logger.getLogger(getClass());
	

	/**
	 * 把值放入到当前登录用户的Session里
	 * @param key
	 * @param value
	 */
	public static void setSessionByKey(Object key ,Object value){
		getSession().setAttribute(key, value);
	}
	
	
	/**
	 * 获取当前用户的Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
}
