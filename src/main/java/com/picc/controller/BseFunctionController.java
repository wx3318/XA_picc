package com.picc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
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
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.entity.BsePlatFunction;
import com.picc.entity.User;
import com.picc.service.BsePlatFunctionService;

/**
 * 系统功能
 * 
 * @author TripodFan
 * @date 2018/08/23
 */
@Controller
@RequestMapping("/picc/function")
public class BseFunctionController {

	private final static Logger logger = Logger.getLogger(BseFunctionController.class);
	
	@Autowired
	private BsePlatFunctionService functionServiceImpl;
	
	/**
	 * 
	 * 添加 .........
	 */
	@RequiresPermissions("picc.function.add")
	@RequestMapping(value = "/add.html")
	public ModelAndView functionAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BsePlatFunction functionParam = new BsePlatFunction();
		List<Map<String, Object>>  functionList = functionServiceImpl.queryAllFunction(functionParam);
	
		ModelAndView view = new ModelAndView();
		
		view.addObject("functionList", functionList);
		view.setViewName("function/function_add");

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
			User user = (User) session.getAttribute("user");

			Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
			
			BsePlatFunction addfunction = new BsePlatFunction();
			BeanUtils.populate(addfunction, requestParamMap);
			
			addfunction.setCreatedDate(new Date());
			addfunction.setCreatedName(user.getName());
			addfunction.setCreatedId(user.getUser_id());
			
			// 业务保存
			functionServiceImpl.insertSelective(addfunction);
			
			// 保存完成后
			resultObject.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("保存功能出现异常");
			logger.error("保存功能出现异常" + e.getMessage());
		}
		
		return resultObject;
	}
	
	/**
	 * 
	 * 列表 .........
	 */
	@RequiresPermissions("picc.function.list")
	@RequestMapping(value = "/list.html")
	public ModelAndView functionList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		// response.setCharacterEncoding("UTF-8");
		view.setViewName("function/function_list");

		return view;
	}
	
	
	/**
	 * 分页查询功能列表
	 *
	 * @return 功能列表
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryAll.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject queryAll(HttpServletRequest request, HttpServletResponse response, BsePlatFunction function,
			HttpSession session) throws IOException { 
		
		    // 返回对象
			ResultObject resultObject = new ResultObject();
			
			try {
			/*	Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);*/
				
				// 业务请求
				function.setState(Constant.STATE_TYPE_KEY.KEY_1);
				PageInfo<Map<String, Object>> pageInfo = functionServiceImpl.queryFunctionList(function);
				
				// 返回对象处理
				resultObject.setData(pageInfo.getList());
				resultObject.setRecordsTotal((int) pageInfo.getTotal());
				resultObject.setRecordsFiltered((int) pageInfo.getTotal());
				resultObject.setDraw(function.getDraw());
				resultObject.setSuccess(true);
				resultObject.setMsg("查询成功");
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				resultObject.setSuccess(false);
				resultObject.setMsg("查询功能列表出现异常");
				logger.error("查询功能列表出现异常" + e.getMessage());
			}
			
			return resultObject;
	}
	
	
	/**
	 * 
	 * 跳往修改页面 .........
	 */
	@RequiresPermissions("picc.function.update")
	@RequestMapping(value = "/update.html")
	public ModelAndView functionUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
		String id = requestParamMap.get("functionId").toString();
		BsePlatFunction function = functionServiceImpl.selectByPrimaryKey(Integer.parseInt(id));
		
		BsePlatFunction functionParam = new BsePlatFunction();
		List<Map<String, Object>>  functionList = functionServiceImpl.queryAllFunction(functionParam);
		
		ModelAndView view = new ModelAndView();
		view.addObject("function", function);
		view.addObject("functionList", functionList);
		// response.setCharacterEncoding("UTF-8");
		view.setViewName("function/function_update");

		return view;
	}
	
	
	/**
	 * 修改数据
	 */
	@RequestMapping(value = "/update.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject update(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException { 
		
		ResultObject resultObject = new ResultObject();
		
		// 获取登陆人
		try {
			User user = (User) session.getAttribute("user");

			Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
			
			BsePlatFunction updateFunction = new BsePlatFunction();
			BeanUtils.populate(updateFunction, requestParamMap);
			updateFunction.setUpdateTime(new Date());
			updateFunction.setUpdateName(user.getName());
			updateFunction.setUpdateId(user.getUser_id());
			
			functionServiceImpl.updateByPrimaryKeySelective(updateFunction);
			
			resultObject.setMsg("修改系统功能成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("修改系统功能出现异常");
			logger.error("修改系统功能出现异常" + e.getMessage());
		}
		
		return  resultObject;
	}
	
	/**
	 * 删除数据
	 */
	@RequestMapping(value = "/delete.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject delete(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException { 
		
		ResultObject resultObject = new ResultObject();
		
		// 获取登陆人
		try {
			User user = (User) session.getAttribute("user");

			Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
			
			String id = requestParamMap.get("id").toString();
			BsePlatFunction updateFunction = functionServiceImpl.selectByPrimaryKey(Integer.parseInt(id));
			
			if (null != updateFunction && updateFunction.getState().equals(Constant.STATE_TYPE_KEY.KEY_1)) {
				
				updateFunction.setState(Constant.STATE_TYPE_KEY.KEY_0);
				updateFunction.setUpdateTime(new Date());
				updateFunction.setUpdateName(user.getName());
				updateFunction.setUpdateId(user.getUser_id());
				
				functionServiceImpl.updateByPrimaryKeySelective(updateFunction);
				
				resultObject.setMsg("操作成功");
			} else {
				resultObject.setMsg("请重新操作");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("操作出现异常");
			logger.error("删除系统功能出现异常" + e.getMessage());
		}
		
		return  resultObject;
	}
}
