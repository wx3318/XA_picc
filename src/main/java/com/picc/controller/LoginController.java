package com.picc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.picc.common.ResultObject;
import com.picc.entity.User;
import com.picc.service.UserService;
/**
 * 
 * @author wangXi
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userservice;
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    

	        dateFormat.setLenient(false);    

	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    

	}
	
	
	/** 
	* 
	* 登录
	* ......... 
	*/ 
	
	@ResponseBody
	@RequestMapping("/dologin.ajax")
	public ResultObject doLoging(User user,  HttpSession session,HttpServletRequest request,HttpServletResponse response ) {
		
		ResultObject resultObject = new ResultObject();
		User sessionuser = (User) session.getAttribute("user");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		// ҳ���ٴ�ˢ��
		if (sessionuser == null) {
			User userEnd;
			try {
				//数据库匹配
				userEnd = userservice.doLogin(user);
				if (userEnd != null) {
					//拿到用户信息
					User userAll = userservice.getUser(userEnd.getUser_id());	
					session.setAttribute("user", userAll);
					session.setMaxInactiveInterval(-1); //session时间
					
					UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(userAll.getUsername()), user.getPassword());
					Subject currentUser = SecurityUtils.getSubject();
					currentUser.login(token);
					

				} else {
					resultObject.setMsg("账号密码错误");
					resultObject.setSuccess(false);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block				
				e.printStackTrace();
				resultObject.setSuccess(false);
				resultObject.setMsg("账号密码错误");
			}			
		} else {
		}
		return resultObject;
	}
	
	/** 
	* 
	* 第一次页面访问
	*  
	*/ 
	@RequestMapping("/login")
	public ModelAndView login(HttpSession session) {
		//登录页面session移除
		session.removeAttribute("user");
		ModelAndView view = new ModelAndView("login/login");
		return view;
	}
	

	@RequestMapping("/newlogin")
	public String newLogin(HttpSession session) {
		session.removeAttribute("user");
		return "login/index";
	}
	
	
}
