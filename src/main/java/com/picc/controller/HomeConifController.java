package com.picc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.ResultObject;
import com.picc.entity.HomeConfig;
import com.picc.entity.User;
import com.picc.service.HomeConfigService;

/**
 * 首页管理
 * 
 * @author TripodFan
 * @date 2018/11/29
 */
@Controller
@RequestMapping("/picc/home")
public class HomeConifController {

	private final static Logger logger = Logger.getLogger(HomeConifController.class);
	
	@Autowired
	private HomeConfigService homeConfigServiceImpl;
	
	/**
	 * 
	 * 新增区域主管页面 .........
	 */
	@RequiresPermissions("picc.home.add")
	@RequestMapping(value = "/add.html")
	public ModelAndView articleAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		
		view.setViewName("home/home_add");

		return view;
	}
	
	
	/**
	 * 保存数据
	 */
	@RequestMapping(value = "/add.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject add(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException {  
		
		ResultObject resultObject = new ResultObject();
		try {
			// 获取登陆人
			User  user = (User) session.getAttribute("user");
			
			Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
			HomeConfig  addHomeConfig = new HomeConfig();
			BeanUtils.populate(addHomeConfig, requestParamMap);
			
			addHomeConfig.setCreatedDate(new Date());
			addHomeConfig.setCreatedId(user.getUser_id());
			addHomeConfig.setCreatedName(user.getName());
			
			homeConfigServiceImpl.insertSelective(addHomeConfig);
			// 保存完成后
			resultObject.setSuccess(true);
			resultObject.setMsg("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("操作异常");
			logger.error("新增首页内容出现异常" + e.getMessage());
		}
		return resultObject;
	}
	
	
	/**
	 * 
	 * 信息列表页面 .........
	 */
	@RequiresPermissions("picc.home.list")
	@RequestMapping(value = "/list.html")
	public ModelAndView articleList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		ModelAndView view = new ModelAndView();

		view.setViewName("home/home_list");

		return view;
	}
	
	/**
	 * 分页查询文章列表
	 *
	 * @return 文章列表信息
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryAll.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject queryAll(HttpServletRequest request, HttpServletResponse response, HomeConfig homeConfig,
			HttpSession session) throws IOException { 
		
		// 返回对象
		ResultObject resultObject = new ResultObject();
		
		try {
			// 业务请求
			PageInfo<Map<String, Object>> pageInfo = homeConfigServiceImpl.queryHomePageByParam(homeConfig);
			
			// 返回对象处理
			resultObject.setData(pageInfo.getList());
			resultObject.setRecordsTotal((int) pageInfo.getTotal());
			resultObject.setRecordsFiltered((int) pageInfo.getTotal());
			resultObject.setDraw(homeConfig.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("查询首页信息出现异常");
			logger.error("查询首页信息出现异常" + e.getMessage());
		}

		return resultObject;
	}
	
	/**
	 * 
	 * 信息详情页面 .........
	 */
	@RequestMapping(value = "/detail.html")
	public ModelAndView articleDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String detailId = request.getParameter("detailId");
		HomeConfig homeConfig = homeConfigServiceImpl.selectByPrimaryKey(Integer.parseInt(detailId));
		
		ModelAndView view = new ModelAndView();

		view.addObject("homeConfig", homeConfig);
		view.setViewName("home/home_detail");

		return view;
	}
	
}
