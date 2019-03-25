

package com.picc.controller;




import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.common.Token;
import com.picc.dao.UserMapper;
import com.picc.entity.Group;
import com.picc.entity.Pending;
import com.picc.entity.User;
import com.picc.entity.WorkTime;
import com.picc.service.BsePlatRoleService;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;
import com.picc.service.PendingService;
import com.picc.service.UserService;
import com.picc.service.WorkTimeService;
import com.picc.util.WorkTimeUtilSch;
/**
 * 
 * @author 王希
 *	@date 2018/8/23
 */
@Controller
@RequestMapping("/picc/user")
public class UserController {
	private static final String User = null;
	@Autowired
	private UserService userservice;
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private BsePlatRoleService roleService;
	
	@Autowired
	private OperationRecordService recordServiceImpl;
	
	@Autowired
	private WorkTimeService workTimeService;
	
	@Autowired
	private PendingService pendingService;
   /**
    * 
    * @param binder
    */
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    

	        dateFormat.setLenient(false);    

	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    

	}
	@RequestMapping("/index.html")
	public String loginSuccess() {
		return "main";
	}
	

	/**
	 * 原始密码检测
	 * @param password
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkpwd.ajax")
	public void checkPwd(@RequestParam(value = "password") String password, @RequestParam(value = "user_id") int id,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		User user = userservice.getUser(id);
		if (user.getPassword().equals(password)) {
			out.print("n");
		} else {
			out.print("y");
		}
	}
	/**
	 * 融合账号查重
	 * @param username
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkuname")
	public void checkUname(@RequestParam(value = "username") int username,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();	
		User userByuname = userservice.getUserByuname(username);
		if(null!=userByuname) {
			out.print("y");	
		}else {
			out.print("n");
		}
	}
	public String delUser() {
		return "";
	}
	/**
	 * 营销工号查重
	 * @param yxid
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/checkyxid")
	public void checkYxId(@RequestParam(value = "yxid") String yxid,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			User userByYxid = userservice.getUserByYxid(yxid);
			if(null!=userByYxid) {
				out.print("y");
			}else {
				out.print("n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 身份证号查询
	 * @param idCard
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/checkidcard")
	public void checkIdCard(@RequestParam(value = "idCard") String idCard,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			User userByYxid = userservice.getUserByIdCard(idCard);
			if(null!=userByYxid) {
				out.print("y");
			}else {
				out.print("n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 密码修改页面
	 * @return
	 */
	@RequiresPermissions("picc.user.upwd")
	@RequestMapping("/changepwd")
	public ModelAndView changePwd() {
		ModelAndView view = new ModelAndView("user/password");
		return view;
	}
	
	/**
	 * 员工导入
	 * @return
	 */
	//@RequiresPermissions("picc.user.import")
	@RequestMapping("/userimpot.html")
	public ModelAndView userImport() {
		ModelAndView view = new ModelAndView("user/userimport");
		return view;
	}
	
	
	@RequiresPermissions("picc.user.update")
	@RequestMapping("/updateuser.html")
	public ModelAndView updateUser(@RequestParam(value = "userid")String userid) {
		ModelAndView view = new ModelAndView("user/userupdata");
		try {
			User user = userservice.getUser(Integer.parseInt(userid));
			List<Map<String, Object>> roleList = roleService.getRoleList(null);
			List<Group> groupList = groupService.getGroupList();
			view.addObject(user);
			view.addObject("roleList", roleList);
			view.addObject("groupList", groupList);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return view;
		
	}
	/**
	 * 密码修改
	 * @param password
	 * @param agpassword
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/savepwd.html")
	public String savePwd(@RequestParam(value = "newpassword") String password,
			@RequestParam(value = "agpassword") String agpassword, @RequestParam(value = "userid") int id, Model model,
			HttpSession session) throws Exception {
		if (password.equals(agpassword)) {
			User user = new User();
			user.setUser_id(id);
			user.setPassword(password);
			if (userservice.savePassword(user)) {
				session.removeAttribute("user");
				return "redirect:/login/login";
			} else {
				throw new Exception("数据异常");
			}
		} else {
			return "redirect:/login/login";
		}
	}
	/**
	 * 员工新增
	 * @param request
	 * @param response
	 * @return 页面
	 * 
	 */
	@RequiresPermissions("picc.user.add")
	@RequestMapping("/adduser.html")
	public String userAdd(HttpServletRequest request, HttpServletResponse response,Model model){
		try {
			List<Map<String, Object>> roleList = roleService.getRoleList(null);
			List<Group> groupList = groupService.getGroupList();
			 model.addAttribute("groupList", groupList);
			 model.addAttribute("roleList", roleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "user/newuser";
	}
	/**
	 * 新增员工接口
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveuser.ajax")
	public ResultObject saveUser(User user,Model model,HttpSession session,HttpServletRequest request) {
		ResultObject resultObject = new ResultObject();
		//验证是否重复提交
		Boolean validToken = Token.validToken(request);
		if (!validToken) {
			resultObject.setSuccess(false);
			resultObject.setMsg("请不要重复提交!");
			return resultObject;
		}
		if(user!=null) {
			User seUser = (User)session.getAttribute("user");
			user.setCreatedId(seUser.getUser_id());
			user.setCreatedName(seUser.getName());

			user.setCreatedDate(new Date());

			try {
				boolean saveUser = userservice.saveUser(user);
				if(saveUser){
					 resultObject.setSuccess(true);
					 resultObject.setMsg("增加成功");
				 }else {
					 resultObject.setSuccess(false);
					 resultObject.setMsg("增加失败");
				 }
			} catch (NumberFormatException e) {
				e.printStackTrace();
				resultObject.setSuccess(false);
				 resultObject.setMsg("增加失败");
			} catch (Exception e) {
				e.printStackTrace();
				resultObject.setSuccess(false);
				 resultObject.setMsg("增加失败");
			}
			
		}else {
				resultObject.setSuccess(false);
			 resultObject.setMsg("获取信息有误");
		}
		return resultObject;
	}
	/**
	 * 员工修改
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateuser.ajax")
	public ResultObject updateUser(User user,Model model,HttpSession session) {
		User sessuser = (User)session.getAttribute("user");
		user.setUpdateId(sessuser.getUser_id());
		user.setUpdateName(user.getUpdateName());
		user.setUpdateTime(new Date());
		//这里用到createId 用来存储 初始组别groupId
		ResultObject resultObject = new ResultObject();	
		//
			try {			
				//pendingService.updatePendingInfoById(pending)
				int updateUser = userservice.updateUser(user);
				if(updateUser>0) {
					if(null!=user.getGroupId()&&!"".equals(String.valueOf(user.getUsername()))) {
						WorkTime workTime= new WorkTime();
						workTime.setUserName(Integer.parseInt(user.getUsername()));
						workTime.setGroupId(user.getGroupId());
						workTimeService.updateWorkTimeGroup(workTime); 
					}	
					
					//所有的案子留下
					if("2".equals(user.getStation())) {
						Pending pending = new Pending();
						pending.setUserInfo(user.getUsername());
						pending.setGroupIded(user.getCreatedId().toString());
						pendingService.updateAllCaseUserInfo(pending);
					}					
					//带走自己的（查勘） 剩下的留下
					if("1".equals(user.getStation())) {
						Pending pending = new Pending();
						pending.setUserInfo(user.getUsername());
						pending.setGroupIded(user.getCreatedId().toString());
						pending.setGroupId(user.getGroupId().toString());
						// 带走自己的
						pendingService.updateCaseUseredInfo(pending);
						// 剩下的公共
						pendingService.updateAllCaseUserInfo(pending);
					}
					//去生控中心所有的案子平均分
					if("3".equals(user.getStation())) {
						Pending pending = new Pending();
						pending.setUserInfo(user.getUsername());
						pending.setGroupIded(user.getCreatedId().toString());
						//数据变为公共数据		
						pendingService.updateCaseUserGroup(pending);
						//获取该人以前所有的数据
						List<Pending> pendingList = pendingService.getPendingListGroupUser(pending);
						//平均分配
						if(pendingList.size()>0) {
							pendingService.updatePendingAreaGroupType(pendingList);
						}					
					}
				}
				
				resultObject.setSuccess(true);
				resultObject.setMsg("更新成功");
			} catch (Exception e) {
				resultObject.setSuccess(false);
				resultObject.setMsg("更新失败");
				e.printStackTrace();
				
			}
		return resultObject;
	}
	
	
	/**
	 * 所有用户信息
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("picc.user.list")
	@RequestMapping("/listuser.html")
	public String showUser(HttpSession session,Model model) throws Exception {
		User user = (User)session.getAttribute("user");
		String role = user.getRole();
		String roleKey="客服";
		if(roleKey.equals(role)) {
			return "user/usercrud";
		}else {
		List<Group> groupList = groupService.getGroupList();
		    	model.addAttribute("groupList", groupList);
		return "user/userlist";
		}
	}
	/**
	 * 
	 * @param user
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/seachuser.ajax")
	public ResultObject seachUser(User user,HttpSession session,Model model) throws Exception {
		ResultObject resultObject = new ResultObject();	
		PageInfo<Map<String, Object>> userListPageMap = userservice.getUserListPageMap(user);
		resultObject.setData(userListPageMap.getList());
		resultObject.setRecordsTotal((int) userListPageMap.getTotal());
		resultObject.setRecordsFiltered((int) userListPageMap.getTotal());
		resultObject.setDraw(user.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	
	
	@RequiresPermissions("picc.user.export")
	@ResponseBody
	@RequestMapping(value="/userexcel.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	public void userExcelPro(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//参数
		User user =(User) session.getAttribute("user");
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		String name=null;
		String group=null;
		String username=null;
		User users = new User();
		if(null!=requestMap.get("uName")&&!"".equals(requestMap.get("uName"))) {
			name=requestMap.get("uName").toString();
			users.setName(name);
		}
		if(null!=requestMap.get("username")&&!"".equals(requestMap.get("username"))) {
			username=requestMap.get("username").toString();
			users.setUsername(username);
		}		
		if(null!=requestMap.get("uGroup")&&!"".equals(requestMap.get("uGroup"))) {
			group=requestMap.get("uGroup").toString();
			users.setGroupId(Integer.parseInt(group));
		}
		
		String fileName = "员工信息.xlsx";
		//工作簿
		try {
			List<Map<String, Object>> userListMap = userservice.getUserListMap(users);
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("员工信息");
			XSSFCellStyle style = wbk.createCellStyle();;
    		style.setAlignment(HorizontalAlignment.CENTER);   		
    		XSSFRow row = sheet.createRow(0);
    		XSSFCell cell = row.createCell(0);
    		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
    	    cell.setCellValue("员工信息");
    	    cell.setCellStyle(style);
    	    //第一列
    	    row = sheet.createRow(1);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("组别");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("姓名");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("在职情况");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("融合系统工号");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("角色");

    		XSSFRow rows;
    		XSSFCell cells;
    		//日期转换
    		
    		for (int i = 0; i < userListMap.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+2);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue(userListMap.get(i).get("group_name").toString());
    			cells = rows.createCell(1);
    			cells.setCellValue(userListMap.get(i).get("name").toString());
    			cells = rows.createCell(2);

    			cells.setCellValue(userListMap.get(i).get("work_mes").toString());
    			cells = rows.createCell(3);

    			cells.setCellValue(userListMap.get(i).get("username").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(userListMap.get(i).get("role_name").toString());
  		
    		}
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="员工信息";
				recordServiceImpl.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//行高
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
