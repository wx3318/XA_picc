package com.picc.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.entity.BsePlatFunction;
import com.picc.entity.BsePlatRole;
import com.picc.entity.BseRoleFunction;
import com.picc.entity.User;
import com.picc.service.BsePlatFunctionService;
import com.picc.service.BsePlatRoleService;
import com.picc.service.BseRoleFunctionService;

/**
 * 系统功能
 * 
 * @author TripodFan
 * @date 2018/08/24
 */
@Controller
@RequestMapping("/picc/role")
public class BseRoleController {

	private final static Logger logger = Logger.getLogger(BseRoleController.class);
	
	@Autowired
	private BsePlatRoleService roleService;
	
	@Autowired
	private BsePlatFunctionService functionService;
	
	@Autowired
	private BseRoleFunctionService roleFunctionService;
	
	/**
	 * 
	 * 添加 .........
	 */
	@RequiresPermissions("picc.role.add")
	@RequestMapping(value = "/add.html")
	public ModelAndView roleAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
		ModelAndView view = new ModelAndView();
		
		view.setViewName("role/role_add");

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
			
			BsePlatRole addRole = new BsePlatRole();
			BeanUtils.populate(addRole, requestParamMap);
			
			addRole.setCreatedDate(new Date());
			addRole.setCreatedId(user.getUser_id());
			addRole.setCreatedName(user.getName());
			
			// 业务保存
			roleService.insertSelective(addRole);
			
			// 保存完成后
			resultObject.setMsg("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultObject;
	}
	
	/**
	 * 
	 * 修改 .........
	 */
	@RequiresPermissions("picc.role.update")
	@RequestMapping(value = "/update.html")
	public ModelAndView roleUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
		String id = requestParamMap.get("roleId").toString();
		BsePlatRole role = roleService.selectByPrimaryKey(Integer.parseInt(id));
		
		ModelAndView view = new ModelAndView();
		view.addObject("role", role);
		view.setViewName("role/role_update");

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
	
	
		try {
			
			User user = (User) session.getAttribute("user");

			Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
			BsePlatRole updateRole = new BsePlatRole();
			BeanUtils.populate(updateRole, requestParamMap);
			
			updateRole.setUpdateTime(new Date());
			updateRole.setUpdateId(user.getUser_id());
			updateRole.setUpdateName(user.getName());
			
			roleService.updateByPrimaryKeySelective(updateRole);
			
			resultObject.setMsg("修改角色信息成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("修改角色信息出现异常");
			logger.error("修改角色信息出现异常" + e.getMessage());
		} 
		return  resultObject;
	}
	/**
	 * 
	 * 列表 .........
	 */
	@RequiresPermissions("picc.role.list")
	@RequestMapping(value = "/list.html")
	public ModelAndView roleList(HttpServletRequest request, HttpServletResponse response) throws Exception {

	
		ModelAndView view = new ModelAndView();
		
		view.setViewName("role/role_list");

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
	public ResultObject queryAll(HttpServletRequest request, HttpServletResponse response, BsePlatRole role,
			HttpSession session) throws IOException {  
	
	        // 返回对象
			ResultObject resultObject = new ResultObject();
			
			
			try {
				// 业务请求
				PageInfo<Map<String, Object>> pageInfo = roleService.queryRoleList(role);
				
				
				// 返回对象处理
				resultObject.setData(pageInfo.getList());
				resultObject.setRecordsTotal((int) pageInfo.getTotal());
				resultObject.setRecordsFiltered((int) pageInfo.getTotal());
				resultObject.setDraw(role.getDraw());
				resultObject.setSuccess(true);
				resultObject.setMsg("查询成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  resultObject;
			
	}
	
	
	
	/**
	 * 
	 * 给角色赋予权限页面 .........
	 */
	@RequiresPermissions("picc.role.function")
	@RequestMapping(value = "/function.html")
	public ModelAndView functionHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
		String id = requestParamMap.get("roleId").toString();
	
		// 查询所有的系统功能
		BsePlatFunction function = new BsePlatFunction();
		function.setState(Constant.STATE_TYPE_KEY.KEY_1);
		List<Map<String, Object>> functionList = functionService.queryAllFunction(function);
		
		// 查询该角色已有的功能
		List<BseRoleFunction> roleFunctionList = roleFunctionService.selectByRoleId(Integer.parseInt(id));
		
		//取出该角色已有的功能ID
		List<String> functionStrList = new ArrayList<>();
		if (null != roleFunctionList && roleFunctionList.size() > 0) {
			   for (int i = 0; i < roleFunctionList.size(); i++) {
				   String fuctionId = roleFunctionList.get(i).getFunctionId().toString();
				   functionStrList.add(fuctionId);
			}
		  }
		
		JSONArray json = new JSONArray();
		for (Map<String, Object> map : functionList) {
			 JSONObject jo = new JSONObject();
			  
			    String functionId = map.get("id").toString();
			   jo.put("id", functionId);
			   jo.put("pId", map.get("funPid").toString());
			   jo.put("name", map.get("funName").toString());
			   if ("8".equals( map.get("id").toString())) {
				   jo.put("checked", true);
			   }
			   //判断该角色已有的功能ID 是否有该 功能ID
			   if (functionStrList.size() > 0 && functionStrList.contains(functionId)) {
				   jo.put("checked", true);
			   }
			   
			   json.add(jo);
		}
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("functionList", json);
		view.addObject("roleId", id);
		view.setViewName("role/function_update");

		return view;
	}
	
	
	
	/**
	 * 给角色添加权限
	 *
	 * @return ResultObject列表
	 * @throws IOException
	 */
	@RequestMapping(value = "/addFunction.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject addFunction(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException { 
		
		    // 返回对象
			ResultObject resultObject = new ResultObject();
			
			try {
				Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
				
				// 获取登陆人
				User user = (User) session.getAttribute("user");
				requestMap.put("user", user);
				
				roleFunctionService.updateFunctionByRoleId(requestMap);
			
				// 返回对象处理
				resultObject.setSuccess(true);
				resultObject.setMsg("赋予权限成功");
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				resultObject.setSuccess(false);
				resultObject.setMsg("赋予权限出现异常");
				logger.error("赋予权限出现异常" + e.getMessage());
			}
			
			return resultObject;
	}
	
}
