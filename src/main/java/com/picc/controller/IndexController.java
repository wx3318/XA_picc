package com.picc.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.picc.entity.HomeConfig;
import com.picc.service.HomeConfigService;
/**
 * 
 * @author wangXi
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private HomeConfigService homeConfigServiceImpl;
	/** 
	* 
	* 去首页
	* ......... 
	*/ 
	@RequiresPermissions("picc.index")
	@RequestMapping(value = "")
	public ModelAndView demo (HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception { 
		
		ModelAndView view = new ModelAndView();

		HomeConfig homeConfig = homeConfigServiceImpl.selectByNewest();
		view.addObject("homeConfig", homeConfig);
		view.setViewName("home/main");
		return view;
	}
	
}
