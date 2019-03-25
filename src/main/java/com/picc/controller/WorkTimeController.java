package com.picc.controller;

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

import org.apache.log4j.Logger;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.entity.Group;
import com.picc.entity.User;
import com.picc.entity.WorkDay;
import com.picc.entity.WorkTime;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;
import com.picc.service.WorkDayService;
import com.picc.service.WorkTimeService;

/**
 * 
 * @author 王希
 *	@date 2018/9/13
 */

@Controller
@RequestMapping("/picc/worktime")
public class WorkTimeController {
	private final static Logger logger = Logger.getLogger(WorkTimeController.class);
	
	@Autowired
	private WorkTimeService workTimeService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private WorkDayService workDayService;
	
	@Autowired
	private OperationRecordService recordServiceImpl;

	/**
	 * 考勤列表
	 * @return
	 */
	@RequiresPermissions("picc.attence.list")
	@RequestMapping("/list.html")
	public ModelAndView goWorkTimeList() {
		ModelAndView view = new ModelAndView("worktime/worklist");
		List<Group> groupList = groupService.getGroupList();
		view.addObject("groupList", groupList);
		return view;
	}
	@RequiresPermissions("picc.attence.daylist")
	@RequestMapping("/daylist.html")
	public ModelAndView goWorkTimeDayList(HttpSession session) {
		WorkTime workTime = new WorkTime();
		Map<String, Object> countPerson = workTimeService.getCountPerson(workTime);
		Map<String, Object> countGroupOne = workTimeService.getCountGroupOne(workTime);
		Map<String, Object> countGroupTwo = workTimeService.getCountGroupTwo(workTime);
		session.setAttribute("countMap", countPerson);
		session.setAttribute("countMapOne", countGroupOne);
		session.setAttribute("countMapTwo", countGroupTwo);
		ModelAndView view = new ModelAndView("worktime/workdaylist");
		return view;
	}
	
	
	/**
	 * 那些天上班
	 * @return
	 */
	@RequestMapping("/saveday.html")
	public ModelAndView setWorkTimeDay() {
		ModelAndView view = new ModelAndView("worktime/workday");
		return view;
	}
	
	@RequiresPermissions("picc.attence.workday")
	@RequestMapping("/savedaylist.html")
	public ModelAndView goWorkDayList() {
		ModelAndView view = new ModelAndView("worktime/work_day");
		return view;
	}
	
	/**
	 * 手动更新页面
	 * @return
	 */
	//@RequiresPermissions("picc.attence.updateworktime")
	@RequestMapping("/updateworktime.html")
	public ModelAndView updateWorkDayList() {
		ModelAndView view = new ModelAndView("worktime/update");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/updateworktimeone.ajax")
	public ResultObject  workTimeUpdateOne(HttpServletRequest request, HttpServletResponse response,HttpSession session,WorkDay workDay) {
		ResultObject resultObject = new ResultObject();
		Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
		String address = requestParamMap.get("workAddressone").toString();
		String workdate = requestParamMap.get("workDateOne").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null==address|| null==workdate ||"".equals(address) || "".equals(workdate)) {
			resultObject.setSuccess(false);
			resultObject.setMsg("更新失败");
		}else {
			if("1".equals(address)) {
				address="74.18.1.198";
			}else if("2".equals(address)) {
				address="74.18.15.102";
			}else if("3".equals(address)) {
				address="74.18.15.101";
			}else if("4".equals(address)) {
				address="74.18.15.98";
			}else{
				address="74.18.15.99";
			}	

			try {
				workTimeService.updateWorkTimeHeadDay(address,sdf.format(sdf.parse(workdate)));
				resultObject.setSuccess(true);
				resultObject.setMsg("更新成功");
			} catch (Exception e) {
				resultObject.setSuccess(false);
				resultObject.setMsg("更新失败");
				e.printStackTrace();
			}	
		}
		return 	resultObject;
	}
	
	@ResponseBody
	@RequestMapping("/updateworktimetwo.ajax")
	public ResultObject  workTimeUpdateTwo(HttpServletRequest request, HttpServletResponse response,HttpSession session,WorkDay workDay) {
		ResultObject resultObject = new ResultObject();
		Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
		String address = requestParamMap.get("workAddresstwo").toString();
		String workdate = requestParamMap.get("workDateTwo").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null==address|| null==workdate ||"".equals(address) || "".equals(workdate)) {
			resultObject.setSuccess(false);
			resultObject.setMsg("更新失败");
		}else {
			if("1".equals(address)) {
				address="74.18.1.198";
			}else if("2".equals(address)) {
				address="74.18.15.102";
			}else if("3".equals(address)) {
				address="74.18.15.101";
			}else if("4".equals(address)) {
				address="74.18.15.98";
			}else{
				address="74.18.15.99";
			}	
			try {
				workTimeService.updateWorkTimeHeadEndTime(address,sdf.format(sdf.parse(workdate)),"7");
				resultObject.setSuccess(true);
				resultObject.setMsg("更新成功");
			} catch (Exception e) {
				resultObject.setSuccess(false);
				resultObject.setMsg("更新失败");
				e.printStackTrace();
			}	
		}
		return 	resultObject;
	}
	
	@ResponseBody
	@RequestMapping("/workday.ajax")
	public ResultObject  workDayList(HttpServletRequest request, HttpServletResponse response,HttpSession session,WorkDay workDay) {
		ResultObject resultObject = new ResultObject();	
		try {
			PageInfo<Map<String, Object>> pageWorkDayList = workDayService.getPageWorkDayList(workDay);
			resultObject.setData(pageWorkDayList.getList());
			resultObject.setRecordsTotal((int) pageWorkDayList.getTotal());
			resultObject.setRecordsFiltered((int) pageWorkDayList.getTotal());
			resultObject.setDraw(workDay.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return 	resultObject;
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addworkday.ajax")
	public ResultObject  setWorkday(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ResultObject resultObject = new ResultObject();	
		Map<String, Object> requestParamMap = BasicUtil.requestParamExtract(request);
		String month=requestParamMap.get("month").toString();
		String workDays = requestParamMap.get("workDays").toString();
		User user = (User) session.getAttribute("user");
		WorkDay workDay = new WorkDay();
		workDay.setDays(workDays);
		workDay.setMonth(month);
		workDay.setCreateId(user.getUser_id());
		workDay.setCreateName(user.getName());
		workDay.setCreateDate(new Date());
		int insertWorkDay = workDayService.insertWorkDay(workDay);
		if(insertWorkDay>0) {
			resultObject.setMsg("保存成功");
			resultObject.setSuccess(true);
		}
		return resultObject;
	}
	
	/**
	 * 天打卡详情页面
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/upworktime.html")
	public ModelAndView updateWorkTimeList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		String workTimeId = request.getParameter("workTimeId");
		int id = Integer.parseInt(workTimeId);
		WorkTime workTime = workTimeService.getWorkTimeByKey(id);
		ModelAndView view = new ModelAndView("worktime/upworktime");
		List<Group> groupList = groupService.getGroupList();
		view.addObject("workTime", workTime);
		view.addObject("groupList", groupList);
		return view;
	}
	/**
	 * 考勤备注修改
	 * @param workTime
	 * @param model
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update.ajax")
	public ResultObject  updateWorktime(WorkTime workTime,Model model,HttpSession session) {
		ResultObject resultObject = new ResultObject();	
		User user = (User) session.getAttribute("user");
		workTime.setUpdateDate(new Date());
		workTime.setUpdateId(user.getUser_id());
		workTime.setUpdateName(user.getName());
		try {
			int updateWorkTime = workTimeService.updateWorkTime(workTime);
			if(updateWorkTime>0) {
				resultObject.setMsg("修改成功");
				resultObject.setSuccess(true);
			}else {
				resultObject.setMsg("修改失败");
				resultObject.setSuccess(false);
			}
		} catch (Exception e) {
			logger.debug("更新数据异常"+e.getMessage());
			resultObject.setMsg("修改失败");
			resultObject.setSuccess(false);
		}
		return resultObject;
	}
	
	
	
	/**
	 * 天打卡详情ajax
	 * @param request
	 * @param response
	 * @param workTime
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/worktimelist.ajax")
	public ResultObject getWorkTimeList(HttpServletRequest request, HttpServletResponse response,WorkTime workTime, 
									HttpSession session) {
		ResultObject resultObject = new ResultObject();	
		User user = (User) session.getAttribute("user");
		if("员工".equals(user.getRole())) {
			workTime.setUserName(Integer.parseInt(user.getUsername()));
		}else if("组长".equals(user.getRole())) {
			workTime.setGroupId(user.getGroupId());
		}
		try {
			PageInfo<Map<String, Object>>	pageInfo = workTimeService.getWorkTimeByWorkTime(workTime);
			resultObject.setData(pageInfo.getList());
			resultObject.setRecordsTotal((int) pageInfo.getTotal());
			resultObject.setRecordsFiltered((int) pageInfo.getTotal());
			resultObject.setDraw(workTime.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultObject;
	}
	/**
	 * 月打卡统计ajax
	 * @param request
	 * @param response
	 * @param workTime
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/workmonthlist.ajax")
	public ResultObject getWorkTimeMonthList(HttpServletRequest request, HttpServletResponse response,WorkTime workTime, 
			HttpSession session) {
		ResultObject resultObject = new ResultObject();	
		User user = (User) session.getAttribute("user");
		if("员工".equals(user.getRole())) {
			workTime.setUserName(Integer.parseInt(user.getUsername()));
		}else if("组长".equals(user.getRole())) {
			workTime.setGroupId(user.getGroupId());
		}
		try {
			PageInfo<Map<String, Object>> pageInfo = workTimeService.getPageWorkTimeMonth(workTime);
			resultObject.setData(pageInfo.getList());
			resultObject.setRecordsTotal((int) pageInfo.getTotal());
			resultObject.setRecordsFiltered((int) pageInfo.getTotal());
			resultObject.setDraw(workTime.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	/**
	 * 天统计按组
	 * @param request
	 * @param response
	 * @param workTime
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/workdaylist.ajax")
	public ResultObject getWorkTimeDayList(HttpServletRequest request, HttpServletResponse response,WorkTime workTime, 
			HttpSession session) {
		ResultObject resultObject = new ResultObject();
		try {
			PageInfo<Map<String, Object>> pageInfo = workTimeService.getPageWorkTimeDay(workTime);
			resultObject.setData(pageInfo.getList());
			resultObject.setRecordsTotal((int) pageInfo.getTotal());
			resultObject.setRecordsFiltered((int) pageInfo.getTotal());
			resultObject.setDraw(workTime.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	@RequestMapping("/worktimegroup.html")
	public String  getWorkTimeGroupList(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String group = request.getParameter("groupId");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				
		List<Group> groupList = groupService.getGroupList();
		for(Group g:groupList) {
			if(g.getId()==Integer.parseInt(group)) {
				group=g.getGroupName();
				break;
			}			
		}
		String date = request.getParameter("workDate");
		if("".equals(date)) {
			date=sdf.format(new Date());
		}
		//缺勤
		WorkTime workTime = new WorkTime();
		workTime.setStartWorkDate(sdf.parse(date));
		workTime.setEndWorkDate(sdf.parse(date));
		workTime.setGroupId(Integer.parseInt(request.getParameter("groupId")));
		workTime.setWorkTion("2");
		List<Map<String, Object>> leatMap = workTimeService.getWorkTimeListByWorkTime(workTime);
		String leat="";
		 for(Map<String, Object> map: leatMap) {
			 String string = map.get("u_name").toString();
			 leat+=string+" , ";
		 }
		 model.addAttribute("leat", leat);	 
		//迟到
		workTime.setWorkTion("1");
		List<Map<String, Object>> leaveMap = workTimeService.getWorkTimeListByWorkTime(workTime);
		String leave="";
		 for(Map<String, Object> map: leaveMap) {
			 String string = map.get("u_name").toString();
			 leave+=string+" , ";
		 }
		 model.addAttribute("leave", leave);
		//请假
		workTime.setWorkTion("5");
		List<Map<String, Object>> askMap = workTimeService.getWorkTimeListByWorkTime(workTime);
		String ask="";
		 for(Map<String, Object> map: askMap) {
			 String string = map.get("u_name").toString();
			 ask+=string+" , ";
		 }
		 model.addAttribute("ask", ask);		
		model.addAttribute("groupmess", group+"("+date+")"+"考勤详情");
		return "worktime/workgroup";
	}
	
	@RequestMapping("/worktimemonth.html")
	public String  getWorkTimeMonth(HttpServletRequest request, HttpServletResponse response,Model model) {
		String username = request.getParameter("username");
		String workDate = request.getParameter("workDate");
		WorkTime workTime = new WorkTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null==workDate || "".equals(workDate)) {
			workDate= sdf.format(new Date());
		}
		try {
			Date parse = sdf.parse(workDate);
			int parseInt = Integer.parseInt(username);
			workTime.setUserName(parseInt);
			workTime.setWorkDate(parse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String levaeDate = workTimeService.getLevaeDate(workTime);
		String lastDate = workTimeService.getLastDate(workTime);
		String startDate = workTimeService.getStartDate(workTime);
		String endDate = workTimeService.getEndDate(workTime);
		String askDate = workTimeService.getAskDate(workTime);
		model.addAttribute("levaeDate",levaeDate );
		model.addAttribute("lastDate", lastDate);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("askDate", askDate);
		return "worktime/worktimemonth";
		
	}
	
	
	/**
	 * 天打卡详情导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequiresPermissions("picc.attence.day.export")
	@ResponseBody
	@RequestMapping(value="/worktimeexport.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	public  void workTimeExcelExport(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		WorkTime workTime = new WorkTime();
		if(null!=requestMap.get("uName")&&!"".equals(requestMap.get("uName")) ) {
			String uName = requestMap.get("uName").toString();
			workTime.setuName(uName);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {	
			if(null!=requestMap.get("startWorkDate")&&!"".equals(requestMap.get("startWorkDate")) ) {
				String startWorkDate = requestMap.get("startWorkDate").toString();			
				workTime.setStartWorkDate(sdf.parse(startWorkDate));	
			}
			if(null!=requestMap.get("endWorkDate")&&!"".equals(requestMap.get("endWorkDate")) ) {
				String endWorkDate = requestMap.get("endWorkDate").toString();
				workTime.setEndWorkDate(sdf.parse(endWorkDate));
			}
			if(null!=requestMap.get("uGroup")&&!"".equals(requestMap.get("uGroup")) ) {
				String uGroup = requestMap.get("uGroup").toString();
				workTime.setGroupId(Integer.parseInt(uGroup));
			}
			if(null!=requestMap.get("workTion")&&!"".equals(requestMap.get("workTion")) ) {
				String workTion = requestMap.get("workTion").toString();
				workTime.setWorkTion(workTion);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
			User user = (User) session.getAttribute("user");
			if("员工".equals(user.getRole())) {
				workTime.setUserName(Integer.parseInt(user.getUsername()));
			}else if("组长".equals(user.getRole())) {
				workTime.setGroupId(user.getGroupId());
			}
			try {
				  List<Map<String, Object>> workTimeList = workTimeService.getWorkTimeListByWorkTime(workTime);
				String fileName = "每日打卡详情.xlsx";
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream;charset=utf-8");
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
				response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
				//工作簿
				XSSFWorkbook wbk = new XSSFWorkbook();
				// Sheet页
				XSSFSheet sheet = wbk.createSheet("每日打卡详情");
				XSSFCellStyle style = wbk.createCellStyle();;
	    		style.setAlignment(HorizontalAlignment.CENTER);	    		
	    		XSSFRow row = sheet.createRow(0);
	    		XSSFCell cell = row.createCell(0);
	    		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
	    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
	    	    cell.setCellValue("每日打卡详情");
	    	    cell.setCellStyle(style);
	    	    //第一列
	    	    row = sheet.createRow(1);
	    	    //单元格
	    		cell = row.createCell(0);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("组别");
	    		cell = row.createCell(1);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("工号");
	    		cell = row.createCell(2);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("姓名");
	    		cell = row.createCell(3);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("打卡日期");
	    		cell = row.createCell(4);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("上班打卡时间");
	    		cell = row.createCell(5);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("下班打卡时间");
	    		cell = row.createCell(6);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("状态");
	    		cell = row.createCell(7);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("备注");
	    		XSSFRow rows;
	    		XSSFCell cells;
	    		//日期转换
	    		for (int i = 0; i < workTimeList.size(); i++) { 
	    			// sheet数据行
	    			rows = sheet.createRow(i+2);
	    			// 单元格
	    			// 单元格里设置值
	    			cells = rows.createCell(0);
	    			cells.setCellValue(workTimeList.get(i).get("group_name").toString());
	    			cells = rows.createCell(1);
	    			cells.setCellValue(workTimeList.get(i).get("username").toString());
	    			cells = rows.createCell(2);
	    			cells.setCellValue(workTimeList.get(i).get("u_name").toString());
	    			cells = rows.createCell(3);
	    			cells.setCellValue(workTimeList.get(i).get("work_date").toString());
	    			cells = rows.createCell(4);
	    			cells.setCellValue(workTimeList.get(i).get("start_time").toString());
	    			cells = rows.createCell(5);
	    			cells.setCellValue(workTimeList.get(i).get("end_time").toString());
	    			cells = rows.createCell(6);
	    			cells.setCellValue(workTimeList.get(i).get("station").toString());
	    			cells = rows.createCell(7);
	    			cells.setCellValue(workTimeList.get(i).get("message").toString());	
	    			
	    		}
	    		OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="每日打卡详情";
				recordServiceImpl.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
	
	
	/**
	 * 月累计考勤导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequiresPermissions("picc.attence.month.export")
	@ResponseBody
	@RequestMapping(value="/workmonthexport.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	public  void workTimeMonnthExcelExport(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		WorkTime workTime = new WorkTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(null!=requestMap.get("uName")&&!"".equals(requestMap.get("uName")) ) {
				String uName = requestMap.get("uName").toString();			
				workTime.setuName(uName);	
			}
			if(null!=requestMap.get("workDate")&&!"".equals(requestMap.get("workDate")) ) {
				String workDate = requestMap.get("workDate").toString();		
					workTime.setWorkDate(sdf.parse(workDate));				
			}
			if(null!=requestMap.get("groupId")&&!"".equals(requestMap.get("groupId")) ) {
				String uGroup = requestMap.get("groupId").toString();
				workTime.setGroupId(Integer.parseInt(uGroup));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		User user = (User) session.getAttribute("user");
		if("员工".equals(user.getRole())) {
			workTime.setUserName(Integer.parseInt(user.getUsername()));
		}else if("组长".equals(user.getRole())) {
			workTime.setGroupId(user.getGroupId());
		}
		try {
			List<Map<String, Object>> workTimeMonth = workTimeService.getWorkTimeMonth(workTime);
			String fileName = "月考勤统计.xlsx";
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("月考勤统计");
			XSSFCellStyle style = wbk.createCellStyle();
    		style.setAlignment(HorizontalAlignment.CENTER);	    		
    		XSSFRow row = sheet.createRow(0);
    		XSSFCell cell = row.createCell(0);
    		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
    	    cell.setCellValue("月考勤统计");
    	    cell.setCellStyle(style);
    	  //第一列
    	    row = sheet.createRow(1);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("组别");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("工号");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("姓名");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("打卡月份");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("上班天数");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("缺勤天数");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("迟到次数");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("下班未打卡次数");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("请假天数");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("缺勤日期");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("迟到日期");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("上班未打卡日期");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("下班未打卡日期");
    		cell = row.createCell(13);
    		cell.setCellStyle(style);
    		cell.setCellValue("请假日期");
    		XSSFRow rows;
    		XSSFCell cells;
    		//日期转换
    		for (int i = 0; i < workTimeMonth.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+2);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue(workTimeMonth.get(i).get("groupName").toString());
    			cells = rows.createCell(1);
    			cells.setCellValue(workTimeMonth.get(i).get("userName").toString());
    			cells = rows.createCell(2);
    			cells.setCellValue(workTimeMonth.get(i).get("name").toString());
    			cells = rows.createCell(3);
    			cells.setCellValue(workTimeMonth.get(i).get("workDate").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(workTimeMonth.get(i).get("workSize").toString());
    			cells = rows.createCell(5);
    			cells.setCellValue(workTimeMonth.get(i).get("leatSize").toString());
    			cells = rows.createCell(6);
    			cells.setCellValue(workTimeMonth.get(i).get("lastSize").toString());
    			cells = rows.createCell(7);
    			cells.setCellValue(workTimeMonth.get(i).get("endSize").toString());
    			cells = rows.createCell(8);
    			cells.setCellValue(workTimeMonth.get(i).get("askSize").toString()); 
    			cells = rows.createCell(9);
    			cells.setCellValue(workTimeMonth.get(i).get("leatDate").toString()); 
    			cells = rows.createCell(10);
    			cells.setCellValue(workTimeMonth.get(i).get("lastDate").toString()); 
    			cells = rows.createCell(11);
    			cells.setCellValue(workTimeMonth.get(i).get("startDate").toString()); 
    			cells = rows.createCell(12);
    			cells.setCellValue(workTimeMonth.get(i).get("endDate").toString());
    			cells = rows.createCell(13);
    			cells.setCellValue(workTimeMonth.get(i).get("askDate").toString()); 
    		}
    	    
    		OutputStream out = response.getOutputStream();
			wbk.write(out);
			out.close();
			String content="月考勤统计";
			recordServiceImpl.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 每天考勤导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value="/workdayexport.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	public  void workTimeDayExcelExport(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		WorkTime workTime = new WorkTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {	
			if(null!=requestMap.get("workDate")&&!"".equals(requestMap.get("workDate")) ) {
				String workDate = requestMap.get("workDate").toString();							
				workTime.setWorkDate(sdf.parse(workDate));					
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			List<Map<String, Object>> workTimeDay = workTimeService.getWorkTimeDay(workTime);
			String fileName = "每日考勤统计.xlsx";
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("每日考勤统计");
			XSSFCellStyle style = wbk.createCellStyle();;
    		style.setAlignment(HorizontalAlignment.CENTER);	    		
    		XSSFRow row = sheet.createRow(0);
    		XSSFCell cell = row.createCell(0);
    		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
    	    cell.setCellValue("每日考勤统计");
    	    cell.setCellStyle(style);
    	    //第一列
    	    row = sheet.createRow(1);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("组别");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("组长姓名");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("考勤日期");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("应到人数");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("实到人数");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("缺勤人数");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("迟到人数");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("请假人数");
    		XSSFRow rows;
    		XSSFCell cells;
    		//日期转换
    		for (int i = 0; i < workTimeDay.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+2);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue(workTimeDay.get(i).get("groupName").toString());
    			cells = rows.createCell(1);
    			cells.setCellValue(workTimeDay.get(i).get("name").toString());
    			cells = rows.createCell(2);
    			cells.setCellValue(workTimeDay.get(i).get("workDate").toString());
    			cells = rows.createCell(3);
    			cells.setCellValue(workTimeDay.get(i).get("groupSize").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(workTimeDay.get(i).get("comeSize").toString());
    			cells = rows.createCell(5);
    			cells.setCellValue(workTimeDay.get(i).get("lastSize").toString());
    			cells = rows.createCell(6);
    			cells.setCellValue(workTimeDay.get(i).get("leaveSize").toString());
    			cells = rows.createCell(7);
    			cells.setCellValue(workTimeDay.get(i).get("askSize").toString()); 			
    		}
    		User user = (User) session.getAttribute("user");
    		OutputStream out = response.getOutputStream();
			wbk.write(out);
			out.close();
			String content="每日考勤统计";
			recordServiceImpl.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
		
}
