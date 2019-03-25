package com.picc.controller;

import java.io.IOException;
import java.io.OutputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.entity.Group;
import com.picc.entity.GroupCase;
import com.picc.entity.User;
import com.picc.entity.UserCase;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;
import com.picc.service.UserCaseService;
/**
 * 个人月任务设置
 * @author Wx
 * @date 2018/12/28
 */
@Controller
@RequestMapping("/picc/groupusercase")
public class UserCaseController {
	
	@Autowired
	private GroupService groupService;	
	@Autowired
	private UserCaseService userCaseService;
	@Autowired
	private OperationRecordService oRdService;
	/**
	 * 个人任务集合设置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("picc.pending.caseuserlist")
	@RequestMapping(value="/caseuserlist.html")
	public ModelAndView getCaseGroupUserPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		List<Group> groupList = groupService.getGroupList();
		model.addObject("groupList", groupList);
		model.setViewName("case/casegroupuserlist");
		return model;
	}
	/**
	 * 个人任务集合
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/caseuserlist.ajax")
	public ResultObject getUserCaseList(HttpServletRequest request,HttpSession session, HttpServletResponse response,UserCase usercase) {
		ResultObject resultObject = new ResultObject();
		//登录人
		User user = (User) session.getAttribute("user");
		String roleid = user.getRoleId().toString();
		//权限
		if("12".equals(roleid)) {
			usercase.setGroupId(user.getGroupId());
		}
		PageInfo<Map<String, Object>> pageGroupCaseList = userCaseService.getPageUserCaseList(usercase);
		resultObject.setData(pageGroupCaseList.getList());
		resultObject.setRecordsTotal((int) pageGroupCaseList.getTotal());
		resultObject.setRecordsFiltered((int) pageGroupCaseList.getTotal());
		resultObject.setDraw(usercase.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	
	/**
	 * 新增任务页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/caseuser.html")
	public ModelAndView getCaseUserPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		List<Group> groupList = groupService.getGroupList();
		model.addObject("groupList", groupList);
		model.setViewName("case/casegroupuser");
		return model;
	}
	/**
	 * 新增任务
	 * @param request
	 * @param response
	 * @param userCase
	 */
	@ResponseBody
	@RequestMapping(value="/save.ajax")
	public ResultObject saveUserCase(HttpServletRequest request, HttpServletResponse response,UserCase usercase) {
		ResultObject resultObject = new ResultObject();
		try {
			userCaseService.saveUserCase(usercase);
			resultObject.setSuccess(true);
			resultObject.setMsg("保存成功");
		} catch (Exception e) {
			resultObject.setSuccess(false);
			resultObject.setMsg("保存失败");
		}
		return resultObject;
	}
	/**
	 * 个人任务修改页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="update.html")
	public ModelAndView updateUserCasePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		String strId = request.getParameter("Id");
		UserCase usercase = new UserCase();
		usercase.setId(Integer.parseInt(strId));
		UserCase userCase = userCaseService.getUserCaseById(usercase);
		model.addObject("userCase", userCase);
		model.setViewName("case/updatecasegroupuser");
		return model;
	}
	/**
	 * 个人任务保存页面
	 * @param request
	 * @param response
	 * @param usercase
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update.ajax")
	public ResultObject updateUserCase(HttpServletRequest request, HttpServletResponse response,UserCase usercase) {
		ResultObject resultObject = new ResultObject();
		try {
			userCaseService.updateUserCase(usercase);
			resultObject.setSuccess(true);
			resultObject.setMsg("修改成功");
		} catch (Exception e) {
			resultObject.setSuccess(false);
			resultObject.setMsg("修改失败");
		}		
		return resultObject;
	}
	/**
	 * 删除个人任务
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete.ajax")
	public ResultObject deleteUserCase(@RequestParam(value = "id") Integer id,HttpServletRequest request, HttpServletResponse response) {
		ResultObject resultObject = new ResultObject();
		try {
			UserCase usercase = new UserCase();
			usercase.setId(id);
			userCaseService.deleteUserCaseById(usercase);
			resultObject.setSuccess(true);
			resultObject.setMsg("删除成功");
		} catch (Exception e) {
			resultObject.setSuccess(false);
			resultObject.setMsg("删除失败");
		}	
		return resultObject;
	}
	
	/**
	 * 分中心人员案件
	 * @param request
	 * @param response
	 * @param groupCase
	 * @return
	 */
	@RequestMapping("/groupuser.html")
	public ModelAndView groupUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView model  = new ModelAndView();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String groupId = request.getParameter("groupId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		UserCase usercase = new UserCase();
		usercase.setGroupId(Integer.parseInt(groupId));
		User user = (User) session.getAttribute("user");
		String roleid = user.getRoleId().toString();
		//权限
		if("12".equals(roleid)) {
			usercase.setGroupId(user.getGroupId());
		}
		if(request.getParameter("createDate")!=null && !"".equals(request.getParameter("createDate"))) {
			String parameter = request.getParameter("createDate");
			try {
				usercase.setCreateDate(sdf.parse(parameter));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			String format = sdf.format(new Date());
			try {
				usercase.setCreateDate(sdf.parse(format));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(request.getParameter("startDate")!=null && !"".equals(request.getParameter("startDate"))) {
			try {
				usercase.setStartDate(sdf.parse(startDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(request.getParameter("endDate")!=null && !"".equals(request.getParameter("endDate"))) {
			try {
				usercase.setEndDate(sdf.parse(endDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String date = sdf.format(usercase.getCreateDate());
		String[] split = date.split("-");
		List<Map<String, Object>> monthUserCase = userCaseService.getMonthUserCase(usercase);
		model.addObject("userMonthcase", monthUserCase);
		model.addObject("groupId", groupId);
		model.addObject("date",date);
		model.addObject("dateMonth", split[0]+"年"+split[1]+"月");
		model.addObject("dateDay", split[1]+"月"+split[2]+"日");
		model.setViewName("case/groupuser");
		return model;
	}
	/**
	 * 未决个人报表导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value="/caseuserexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
	public void excelUserExport(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String groupId = request.getParameter("groupId");
		UserCase usercase = new UserCase();
		usercase.setGroupId(Integer.parseInt(groupId));
		User user = (User) session.getAttribute("user");
		String roleid = user.getRoleId().toString();
		//权限
		if("12".equals(roleid)) {
			usercase.setGroupId(user.getGroupId());
		}
		if(request.getParameter("createDate")!=null && !"".equals(request.getParameter("createDate"))) {
			String parameter = request.getParameter("createDate");
			try {
				usercase.setCreateDate(sdf.parse(parameter));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			String format = sdf.format(new Date());
			try {
				usercase.setCreateDate(sdf.parse(format));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String date = sdf.format(usercase.getCreateDate());
		String[] split = date.split("-");	
		String fileName =split[0]+"年"+split[1]+"月车险未决个人报表.xlsx";
			try {
				List<Map<String, Object>> monthUserCase = userCaseService.getMonthUserCase(usercase);
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream;charset=utf-8");
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
				response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
				//工作簿
				XSSFWorkbook wbk = new XSSFWorkbook();
				// Sheet页
				XSSFSheet sheet = wbk.createSheet("未决日报表");
				XSSFCellStyle style = wbk.createCellStyle();;
	    		style.setAlignment(HorizontalAlignment.CENTER);   		
	    		XSSFRow row = sheet.createRow(0);
	    		XSSFCell cell = row.createCell(0);
	    		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
	    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
	    	    cell.setCellValue("车险未决个人报表");
	    	    cell.setCellStyle(style);
	    	  //第一列
	    	    row = sheet.createRow(1);
	    	    //单元格
	    		cell = row.createCell(0);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("机构");
	    		cell = row.createCell(1);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("归属人");
	    		cell = row.createCell(2);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("起始处");
	    		cell = row.createCell(3);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("每日新增");
	    		cell = row.createCell(4);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("每日结案");
	    		cell = row.createCell(5);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案总计");
	    		cell = row.createCell(6);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("截止"+split[1]+"月"+split[2]+"日未决");
	    		cell = row.createCell(7);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("起始目标");
	    		cell = row.createCell(8);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("差距");
	    		cell = row.createCell(9);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("预算目标");
	    		cell = row.createCell(10);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("差距");
	    		cell = row.createCell(11);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("挑战目标");
	    		cell = row.createCell(12);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("差距");
	    		cell = row.createCell(13);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("排名");
	    		XSSFRow rows;
	    		XSSFCell cells;
	    		for (int i = 0; i < monthUserCase.size(); i++) { 
	    			// sheet数据行
	    			rows = sheet.createRow(i+2);
	    			// 单元格
	    			// 单元格里设置值
	    			cells = rows.createCell(0);
	    			cells.setCellValue(monthUserCase.get(i).get("group_name").toString());
	    			cells = rows.createCell(1);
	    			cells.setCellValue(monthUserCase.get(i).get("NAME").toString()); 
	    			cells = rows.createCell(2);
	    			cells.setCellValue(monthUserCase.get(i).get("starting_number").toString());
	    			cells = rows.createCell(3);
	    			cells.setCellValue(monthUserCase.get(i).get("dayNewCase").toString());
	    			cells = rows.createCell(4);	    			 
	    			cells.setCellValue(monthUserCase.get(i).get("dayEndCase").toString());
	    			cells = rows.createCell(5);
	    			cells.setCellValue(monthUserCase.get(i).get("endAllCase").toString());
	    			cells = rows.createCell(6);   			
	    			cells.setCellValue(monthUserCase.get(i).get("allNewCase").toString());
	    			cells = rows.createCell(7);
	    			cells.setCellValue(monthUserCase.get(i).get("start_target_number").toString());
	    			cells = rows.createCell(8);
	    			cells.setCellValue(monthUserCase.get(i).get("diffstart").toString());
	    			cells = rows.createCell(9);
	    			cells.setCellValue(monthUserCase.get(i).get("budget_target_number").toString());
	    			cells = rows.createCell(10);
	    			cells.setCellValue(monthUserCase.get(i).get("diffbudget").toString());
	    			cells = rows.createCell(11);
	    			cells.setCellValue(monthUserCase.get(i).get("challeng_number").toString());
	    			cells = rows.createCell(12);
	    			cells.setCellValue(monthUserCase.get(i).get("diffchalleng").toString());
	    			cells = rows.createCell(13);
	    			cells.setCellValue(monthUserCase.get(i).get("relt").toString());	    			
	    		}
				try {
					OutputStream out = response.getOutputStream();
					wbk.write(out);
					out.close();
					String content="车险个人报表";
					oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
				} catch (IOException e) {
					e.printStackTrace();
				}			
		}catch (Exception e) {
			
		}
	}
}
