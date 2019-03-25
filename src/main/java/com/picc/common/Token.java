package com.picc.common;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * Token令牌管理
 * @author wangXi
 * @date 2019/01/23 16:04
 *
 */
public class Token {
	
	/***
	* 设置令牌
	* @param request
	*/
	public static void setToken(HttpServletRequest request){
	    request.getSession().setAttribute("SesionToken",UUID.randomUUID().toString() );
	}
	
	public static String getToken(HttpServletRequest request){
		String sessionToken = (String)request.getSession().getAttribute("SesionToken");
		if(null == sessionToken || "".equals(sessionToken)){
		    sessionToken = UUID.randomUUID().toString();
		    request.getSession().setAttribute("SesionToken",sessionToken );
		}
		return sessionToken;
	}

	/***
	* 验证是否为重复提交
	* @param HttpServletRequest request
	* @return String true非重复提交,false重复提交,error非法操作
	*/
	public static Boolean validToken(HttpServletRequest request){
		String sessionToken = (String)request.getSession().getAttribute("SesionToken");
		String requestToken = request.getParameter("SesionToken");
		System.out.println(sessionToken+"sessionToken_---------------");
		System.out.println(requestToken+"requestToken---------------");
		if(null == sessionToken){
		    sessionToken = "";
		}
		if( "null".equals(sessionToken) || "null".equals(requestToken)){
		    sessionToken = "";
		}
	
		if(sessionToken.equals(requestToken)){
			//返回前一定要重置session中的SesionToken
			request.getSession().setAttribute("SesionToken",UUID.randomUUID().toString() );
			//非重复提交
		    return true;
		}else{			
			//返回前一定要重置session中的SesionToken
			request.getSession().setAttribute("SesionToken",UUID.randomUUID().toString() );
			//重复提交
			return false;
		}
	}
}
