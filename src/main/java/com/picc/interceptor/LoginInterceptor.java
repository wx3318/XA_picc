package com.picc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.picc.entity.User;

public class LoginInterceptor implements HandlerInterceptor {
	
	 public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
     
	    public void setAllowUrls(String[] allowUrls) {  
	        this.allowUrls = allowUrls;  
	    }  
	

	public LoginInterceptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {


	     	String url=request.getRequestURI();
			
			if(url.contains("?")){//去掉问号后的参数
				url=url.substring(0,url.indexOf("?"));
			}
	      // 获得相对URL路径
	 		String path = request.getContextPath();
	 	   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	        request.setAttribute("basePath", basePath);
	        
	        request.setAttribute("pageUrl", ((HttpServletRequest)request).getRequestURI()
					+ (((HttpServletRequest)request).getQueryString() != null ? "?" + ((HttpServletRequest)request).getQueryString() : ""));
	        
	 		url = url.replace(path, "");
	 		
	     	if(null != allowUrls && allowUrls.length>=1){
	            for(String allowurl : allowUrls) {    
	                if(url.contains(allowurl)) {    
	                	//System.out.println("==="+url);
	                    return true;    
	                }    
	            }
			}
	     	
	       /*  if (url.indexOf("/login/login.html") >= 0) {
	        	 return true;
	         }*/
	         	//查看是否登录
	         	HttpSession session = request.getSession();
	         	User user = (User) session.getAttribute("user");
	       
	         if (user != null) {
	        	 return true;
	         }
	         //跳转到登录页面
	         response.sendRedirect(request.getContextPath()+"/login/login.html");
		     return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
