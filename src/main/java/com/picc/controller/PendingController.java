 package com.picc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.entity.Group;
import com.picc.entity.Pending;
import com.picc.entity.User;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;
import com.picc.service.PendingService;
import com.picc.util.ImportExcelUtil;
/**
 * 未决案件
 * @author wangXi
 * @date  2018/12/05
 */
@Controller
@RequestMapping("/picc/pending")
public class PendingController {
	private final static Logger logger = Logger.getLogger(PendingController.class);
	@Autowired
	private PendingService pendingService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private OperationRecordService oRdService;
	/**
	 * 未决案件导入页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	@RequiresPermissions("picc.pending.import")
	@RequestMapping(value="/import.html")
	public ModelAndView importPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/import");
		return model;
	}

	/**
	 * 未决案件详情页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("picc.pending.list")
	@RequestMapping(value="/list.html")
	public ModelAndView pendingPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/caselist");
		return model;
	}
	/**
	 *分中心未决 案件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("picc.pending.groupuser")
	@RequestMapping(value="/caseuser.html")
	public ModelAndView groupcasePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		List<Group> groupList = groupService.getGroupList();
		model.addObject("groupList", groupList);
		model.setViewName("case/caseuserinfo");
		return model;
	}
	
	/**
	 * 分中心未决
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/pendinggroup.ajax")
	public ResultObject getPendingGroup(HttpServletRequest request,HttpSession session,HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		//登录人
		User user = (User) session.getAttribute("user");
		String roleid = user.getRoleId().toString();
		//权限
		if("12".equals(roleid)) {
			pending.setGroupId(user.getGroupId().toString());
		}
		 PageInfo<Map<String, Object>> pagePengdingGroup = pendingService.getPagePengdingGroup(pending);
		 	resultObject.setData(pagePengdingGroup.getList());
			resultObject.setRecordsTotal((int) pagePengdingGroup.getTotal());
			resultObject.setRecordsFiltered((int) pagePengdingGroup.getTotal());
			resultObject.setDraw(pending.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 更新归属人
	 * @param request
	 * @param response
	 * @param pending
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value="/updateuserinfo.ajax")
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response,Pending pending) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			int updatePendingInfoById = pendingService.updatePendingInfoById(pending);
			if(updatePendingInfoById>0) {
				out.print("1");
			}else {
				out.print("-1");
			}		
		} catch (Exception e) {
			out.print("-2");
		}	
	}
	
	/**
	 * 省内立未理
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/slwl.ajax")
	public ResultObject getPendingSlwl(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("1");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	
	/**
	 * 郊县立未理
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/jlwl.ajax")
	public ResultObject getPendingJlwl(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("2");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 省间通赔立未理
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/tplwl.ajax")
	public ResultObject getPendingTplwl(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("3");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 城区立未理
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cqlwl.ajax")
	public ResultObject getPendingCqlwl(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("4");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 辖内立未理
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/xnlwl.ajax")
	public ResultObject getPendingXnlwl(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("5");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 通赔未结案
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/tpno.ajax")
	public ResultObject getPendingTpNo(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("6");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 郊县未决案件
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/jxno.ajax")
	public ResultObject getPendingJxNo(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("7");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	/**
	 * 城区未结案
	 * @param request
	 * @param response
	 * @param pending
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cqno.ajax")
	public ResultObject getPendingCqNo(HttpServletRequest request, HttpServletResponse response,Pending pending) {
		ResultObject resultObject = new ResultObject();
		pending.setAreaType("8");
		PageInfo<Map<String, Object>> pagePendingListStation = pendingService.getPagePendingListByAreaType(pending);
		resultObject.setData(pagePendingListStation.getList());
		resultObject.setRecordsTotal((int) pagePendingListStation.getTotal());
		resultObject.setRecordsFiltered((int) pagePendingListStation.getTotal());
		resultObject.setDraw(pending.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	 /**
	  *区域未决案件导出
	  * @param request
	  * @param response
	  * @param session
	  */
	@ResponseBody
	@RequestMapping(value="/pendingexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pending pending = new Pending();
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		String areaType = requestMap.get("areaType").toString();
		pending.setAreaType(areaType);
		String fileName="";
		if("1".equals(areaType)) {
			fileName ="省内.xlsx";
		}else if("2".equals(areaType)) {
			fileName ="郊县.xlsx";
		}else if("3".equals(areaType)) {
			fileName ="通赔.xlsx";
		}else if("4".equals(areaType)) {
			fileName ="城区.xlsx";
		}else if("5".equals(areaType)) {
			fileName ="辖内.xlsx";
		}else if("6".equals(areaType)) {
			fileName ="通赔未结案.xlsx";
		}else if("7".equals(areaType)) {
			fileName ="郊县未结案.xlsx";
		}else if("8".equals(areaType)) {
			fileName ="城区未结案.xlsx";
		}else {
			fileName ="车险未决.xlsx";
		}
		try {
			List<Map<String, Object>> pengdingGroup = pendingService.getPengdingGroup(pending);
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("未决");
			XSSFCellStyle style = wbk.createCellStyle();;
			style.setAlignment(HorizontalAlignment.CENTER);   		
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			//第一列
    	    row = sheet.createRow(0);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("列Id");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("报案号");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("立案号");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("保单号");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("车牌号");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("被保人");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("出险日期");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("案件性质");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("承包代码");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("估损金额");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("查勘员");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("查勘员代码");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("定损员");
    		cell = row.createCell(13);
    		cell.setCellStyle(style);
    		cell.setCellValue("定损员代码");
    		cell = row.createCell(14);
    		cell.setCellStyle(style);
    		cell.setCellValue("理算员");
    		cell = row.createCell(15);
    		cell.setCellStyle(style);
    		cell.setCellValue("案伤类型");
    		cell = row.createCell(16);
    		cell.setCellStyle(style);
    		cell.setCellValue("案件状态");
    		cell = row.createCell(17);
    		cell.setCellStyle(style);
    		cell.setCellValue("机构中心");
    		cell = row.createCell(18);
    		cell.setCellStyle(style);
    		cell.setCellValue("归属人");
    		XSSFRow rows;
    		XSSFCell cells;
    		for (int i = 0; i < pengdingGroup.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+1);
    			// 单元格
    			// 单元格里设置值

    			cells = rows.createCell(0);
    			cells.setCellValue(pengdingGroup.get(i).get("id").toString());
    			cells = rows.createCell(1);
    			cells.setCellValue(pengdingGroup.get(i).get("report_number").toString());
    			cells = rows.createCell(2);
    			cells.setCellValue(pengdingGroup.get(i).get("registration_number").toString());
    			cells = rows.createCell(3);
    			cells.setCellValue(pengdingGroup.get(i).get("policy_number").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(pengdingGroup.get(i).get("plate_number").toString());
    			cells = rows.createCell(5);   			
    			cells.setCellValue(pengdingGroup.get(i).get("assured").toString());
    			cells = rows.createCell(6);
    			cells.setCellValue(pengdingGroup.get(i).get("risk_date").toString());
    			cells = rows.createCell(7);
    			cells.setCellValue(pengdingGroup.get(i).get("case_character").toString());
    			cells = rows.createCell(8);
    			cells.setCellValue(pengdingGroup.get(i).get("underwriting_code").toString());
    			cells = rows.createCell(9);
    			cells.setCellValue(pengdingGroup.get(i).get("assessment_loss").toString());
    			cells = rows.createCell(10);
    			cells.setCellValue(pengdingGroup.get(i).get("insurance_code").toString());
    			cells = rows.createCell(11);
    			cells.setCellValue(pengdingGroup.get(i).get("insurance_name").toString());
    			cells = rows.createCell(12);
    			cells.setCellValue(pengdingGroup.get(i).get("damage_code").toString());   		
    			cells = rows.createCell(13);
    			cells.setCellValue(pengdingGroup.get(i).get("damage_name").toString());
    			cells = rows.createCell(14);
    			cells.setCellValue(pengdingGroup.get(i).get("adjuster_code").toString());
    			cells = rows.createCell(15);
    			cells.setCellValue(pengdingGroup.get(i).get("loss").toString());
    			cells = rows.createCell(16);
    			cells.setCellValue(pengdingGroup.get(i).get("case_station_name").toString());
    			cells = rows.createCell(17);
    			cells.setCellValue(pengdingGroup.get(i).get("group_name").toString());
    			cells = rows.createCell(18);
    			cells.setCellValue(pengdingGroup.get(i).get("user_info").toString());
    		}
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				//String content="员工信息";
				//recordServiceImpl.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 分中心未决导出
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequiresPermissions("picc.pending.excelg")
	@ResponseBody
	@RequestMapping(value="/casegroupexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
	public void exportGourpExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Pending pending = new Pending();
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		String userInfo = requestMap.get("userInfo").toString();
		pending.setUserInfo(userInfo);
		User user = (User) session.getAttribute("user");
		String roleid = user.getRoleId().toString();
		//权限
		if("12".equals(roleid)) {
			pending.setGroupId(user.getGroupId().toString());
		}
		try {
			String fileName="分中心未决.xlsx";
			List<Map<String, Object>> pengdingGroup = pendingService.getPengdingGroup(pending);
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("未决");
			XSSFCellStyle style = wbk.createCellStyle();;
			style.setAlignment(HorizontalAlignment.CENTER);   		
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			//第一列
    	    row = sheet.createRow(0);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("列Id");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("报案号");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("立案号");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("保单号");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("车牌号");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("被保人");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("出险日期");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("案件性质");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("承包代码");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("估损金额");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("查勘员");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("查勘员代码");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("定损员");
    		cell = row.createCell(13);
    		cell.setCellStyle(style);
    		cell.setCellValue("定损员代码");
    		cell = row.createCell(14);
    		cell.setCellStyle(style);
    		cell.setCellValue("理算员");
    		cell = row.createCell(15);
    		cell.setCellStyle(style);
    		cell.setCellValue("案伤类型");
    		cell = row.createCell(16);
    		cell.setCellStyle(style);
    		cell.setCellValue("案件状态");
    		cell = row.createCell(17);
    		cell.setCellStyle(style);
    		cell.setCellValue("机构中心");
    		cell = row.createCell(18);
    		cell.setCellStyle(style);
    		cell.setCellValue("归属人代码");
    		cell = row.createCell(19);
    		cell.setCellStyle(style);
    		cell.setCellValue("归属人");
    		XSSFRow rows;
    		XSSFCell cells;
    		for (int i = 0; i < pengdingGroup.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+1);
    			// 单元格
    			// 单元格里设置值

    			cells = rows.createCell(0);
    			cells.setCellValue(pengdingGroup.get(i).get("id").toString());
    			cells = rows.createCell(1);
    			cells.setCellValue(pengdingGroup.get(i).get("report_number").toString());
    			cells = rows.createCell(2);
    			cells.setCellValue(pengdingGroup.get(i).get("registration_number").toString());
    			cells = rows.createCell(3);
    			cells.setCellValue(pengdingGroup.get(i).get("policy_number").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(pengdingGroup.get(i).get("plate_number").toString());
    			cells = rows.createCell(5);   			
    			cells.setCellValue(pengdingGroup.get(i).get("assured").toString());
    			cells = rows.createCell(6);
    			cells.setCellValue(pengdingGroup.get(i).get("risk_date").toString());
    			cells = rows.createCell(7);
    			cells.setCellValue(pengdingGroup.get(i).get("case_character").toString());
    			cells = rows.createCell(8);
    			cells.setCellValue(pengdingGroup.get(i).get("underwriting_code").toString());
    			cells = rows.createCell(9);
    			cells.setCellValue(pengdingGroup.get(i).get("assessment_loss").toString());
    			cells = rows.createCell(10);
    			cells.setCellValue(pengdingGroup.get(i).get("insurance_code").toString());
    			cells = rows.createCell(11);
    			cells.setCellValue(pengdingGroup.get(i).get("insurance_name").toString());
    			cells = rows.createCell(12);
    			cells.setCellValue(pengdingGroup.get(i).get("damage_code").toString());   		
    			cells = rows.createCell(13);
    			cells.setCellValue(pengdingGroup.get(i).get("damage_name").toString());
    			cells = rows.createCell(14);
    			cells.setCellValue(pengdingGroup.get(i).get("adjuster_code").toString());
    			cells = rows.createCell(15);
    			cells.setCellValue(pengdingGroup.get(i).get("loss").toString());
    			cells = rows.createCell(16);
    			cells.setCellValue(pengdingGroup.get(i).get("case_station_name").toString());
    			cells = rows.createCell(17);
    			cells.setCellValue(pengdingGroup.get(i).get("group_name").toString());
    			cells = rows.createCell(18);
    			cells.setCellValue(pengdingGroup.get(i).get("user_info").toString());
    			cells = rows.createCell(19);
    			cells.setCellValue(pengdingGroup.get(i).get("name").toString());
    		}
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="未决归属出";
				oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 未决案件上传	
	 * @param file
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/importexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void impertPendingExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session)
			throws Exception {
		PrintWriter out = null;
		// ajax中午乱码处理
		response.setCharacterEncoding("utf-8"); 
		User user = (User) session.getAttribute("user");
		out = response.getWriter();
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			throw new Exception("创建工作表失败");
		}
		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Pending> pendingList = new ArrayList<Pending>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			Pending tk = new Pending();
			if("".contains(String.valueOf(lo.get(0))) || null==String.valueOf(lo.get(0))) {
				break;
			}
			tk.setReportNumber(String.valueOf(lo.get(0)).trim());
			tk.setRegistrationNumber(String.valueOf(lo.get(1)).trim());
			tk.setPolicyNumber(String.valueOf(lo.get(2)).trim());
			tk.setPlateNumber(String.valueOf(lo.get(3)).trim());
			tk.setAssured(String.valueOf(lo.get(4)).trim());			
			try {
				if (!lo.get(5).equals("")) {
					tk.setRiskDate(sdf.parse(String.valueOf(lo.get(5))));
				} else {
					tk.setRiskDate(null);
				}		
			} catch (Exception e) {
				e.printStackTrace();
				out.print("日期格式有误");
				return;
			}
			tk.setCaseCharacter(String.valueOf(lo.get(6)).trim());
			tk.setUnderwritingCode(String.valueOf(lo.get(7)).trim());
			tk.setAssessmentLoss(String.valueOf(lo.get(8)).trim());
			tk.setInsuranceCode(String.valueOf(lo.get(9)).trim());
			tk.setInsuranceName(String.valueOf(lo.get(10)).trim());
			if(null!=lo.get(11)  && !"".equals(String.valueOf(lo.get(11)))) {
				tk.setDamageCode(String.valueOf(lo.get(11)).trim());
			}
			if( null!=lo.get(12) && !"".equals(String.valueOf(lo.get(12))) ) {
				tk.setDamageName(String.valueOf(lo.get(12)).trim());	
			}
			if( null!=lo.get(13) && !"".equals(String.valueOf(lo.get(13))) ) {
				tk.setLoss(String.valueOf(lo.get(13)).trim());	
			}
			String str= String.valueOf(lo.get(14)).trim();
			String st1="立未理";  String st2="核未结"; String st3="理未核";
			if(st1.equals(str)) {
				tk.setCaseStation("1");
			}else if(st2.equals(str)) {
				tk.setCaseStation("2");
			}else if(st3.equals(str)) {
				tk.setCaseStation("3");
			}			
			if(null!=lo.get(15) && !"".equals(lo.get(15)) ) {
				tk.setAdjusterCode(String.valueOf(lo.get(15)).trim());
			}
			if(null!=lo.get(16) && !"".equals(lo.get(16)) ) {
				tk.setAdjusterName(String.valueOf(lo.get(16)).trim());
			}	
			//放入集合
			pendingList.add(tk);
		}
		Pending pending = new Pending();
		List<Pending> pendingListParam = pendingService.getPendingListParam(pending);
		List<Pending> endDiffrent = getDiffrentNew(pendingListParam,pendingList);
		List<Pending> newDiffrent = getDiffrentNew(pendingList,pendingListParam);
		pendingListParam.removeAll(endDiffrent);
		pendingList.removeAll(newDiffrent);
		List<Pending> diffrent = getDiffrentUpdate(pendingListParam, pendingList);		
		System.out.println("覆盖数量"+diffrent.size());
		if(diffrent.size()>0) {
			//案件状态改变
			pendingService.updatePending(diffrent);
		}
		if(newDiffrent.size()>0) {
			//新的案件
			for(Pending p:newDiffrent) {
				p.setCreateDate(new Date());
			}
			pendingService.savePendingList(newDiffrent);
		}
		if(endDiffrent.size()>0) {
			//已结案件
			for(Pending p:endDiffrent) {
				p.setCaseStation("4");
				p.setUpdateDate(new Date());
			}
			pendingService.updatePending(endDiffrent);
		}
		List<Pending> allPending = new ArrayList<Pending>();
		List<Pending> pendingListOnchengNoEntry = pendingService.getPendingListOnchengNoEntry(pending);
		allPending.addAll(pendingListOnchengNoEntry);
		List<Pending> pendingListOncqEntry = pendingService.getPendingListOncqEntry(pending);
		allPending.addAll(pendingListOncqEntry);
		List<Pending> pendingListOnCqXnEntry = pendingService.getPendingListOnCqXnEntry(pending);
		allPending.addAll(pendingListOnCqXnEntry);
		List<Pending> pendingListOnjiaoEntry = pendingService.getPendingListOnjiaoEntry(pending);
		allPending.addAll(pendingListOnjiaoEntry);
		List<Pending> pendingListOnjiaoNoEntry = pendingService.getPendingListOnjiaoNoEntry(pending);
		allPending.addAll(pendingListOnjiaoNoEntry);
		List<Pending> pendingListOnShengEntry = pendingService.getPendingListOnShengEntry(pending);
		allPending.addAll(pendingListOnShengEntry);
		List<Pending> pendingListOntpEntry = pendingService.getPendingListOntpEntry(pending);
		allPending.addAll(pendingListOntpEntry);
		List<Pending> pendingListOntpNoEntry = pendingService.getPendingListOntpNoEntry(pending);
		allPending.addAll(pendingListOntpNoEntry);	
		int updatePendingAreaGroupType = pendingService.updatePendingAreaGroupType(allPending);
		out.print("覆盖案件数量"+diffrent.size()+" | "+"已结案件数量"+endDiffrent.size()+" | "+"新案件数量"+newDiffrent.size()+" | 划分案件"+updatePendingAreaGroupType);
		out.flush();
		out.close();
		String content="每日未决案件";
		oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.IMPORT);
	}
	/**
	 * 初次上传未决案件
	 * @param file
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/cimportexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void impertcPendingExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session)
			throws Exception {
		PrintWriter out = null;
		// ajax中午乱码处理
		response.setCharacterEncoding("utf-8"); 
		out = response.getWriter();
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			throw new Exception("创建工作表失败");
		}
		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Pending> pendingList = new ArrayList<Pending>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			Pending tk = new Pending();
			if("".contains(String.valueOf(lo.get(0))) || null==String.valueOf(lo.get(0))) {
				break;
			}
			tk.setReportNumber(String.valueOf(lo.get(0)).trim());
			tk.setRegistrationNumber(String.valueOf(lo.get(1)).trim());
			tk.setPolicyNumber(String.valueOf(lo.get(2)).trim());
			tk.setPlateNumber(String.valueOf(lo.get(3)).trim());
			tk.setAssured(String.valueOf(lo.get(4)).trim());			
			try {
				if (!lo.get(5).equals("")) {
					tk.setRiskDate(sdf.parse(String.valueOf(lo.get(5))));
				} else {
					tk.setRiskDate(null);
				}		
			} catch (Exception e) {
				e.printStackTrace();
				out.print("日期格式有误");
				return;
			}
			tk.setCaseCharacter(String.valueOf(lo.get(6)).trim());
			tk.setUnderwritingCode(String.valueOf(lo.get(7)).trim());
			tk.setAssessmentLoss(String.valueOf(lo.get(8)).trim());
			tk.setInsuranceCode(String.valueOf(lo.get(9)).trim());
			tk.setInsuranceName(String.valueOf(lo.get(10)).trim());
			if(null!=lo.get(11)  && !"".equals(String.valueOf(lo.get(11)))) {
				tk.setDamageCode(String.valueOf(lo.get(11)).trim());
			}
			if( null!=lo.get(12) && !"".equals(String.valueOf(lo.get(12))) ) {
				tk.setDamageName(String.valueOf(lo.get(12)).trim());	
			}
			String str= String.valueOf(lo.get(13)).trim();
			String st1="立未理";  String st2="核未结"; String st3="理未核";
			if(st1.equals(str)) {
				tk.setCaseStation("1");
			}else if(st2.equals(str)) {
				tk.setCaseStation("2");
			}else if(st3.equals(str)) {
				tk.setCaseStation("3");
			}			
			String groupName = String.valueOf(lo.get(14)).trim();

			if("东郊理赔分中心".equals(groupName)) {
				tk.setGroupId("1");								
			}else if("南郊理赔分中心".equals(groupName)) {
				tk.setGroupId("2");	
			}else if("西郊理赔分中心".equals(groupName)) {
				tk.setGroupId("3");				
			}else if("北郊理赔分中心".equals(groupName)) {
				tk.setGroupId("4");
			}else if("周至支公司理赔分部".equals(groupName)) {
				tk.setGroupId("5");
			}else if("长安支公司理赔分部".equals(groupName)) {
				tk.setGroupId("6");
			}else if("阎良支公司理赔分部".equals(groupName)) {
				tk.setGroupId("7");
			}else if("临潼支公司理赔分部".equals(groupName)) {
				tk.setGroupId("8");
			}else if("蓝田支公司理赔分部".equals(groupName)) {
				tk.setGroupId("9");
			}else if("户县支公司理赔分部".equals(groupName)) {
				tk.setGroupId("10");
			}else if("高陵支公司理赔分部".equals(groupName)) {
				tk.setGroupId("11");				
			}else if("车险理赔分部".equals(groupName)) {
				tk.setGroupId("12");
			}else {
				tk.setGroupId("13");
			}		
			if(null==tk.getGroupId()) {
				System.out.println("有null");
			}
			String trim = String.valueOf(lo.get(15)).trim();
			if(!"#N/A".equals(trim)) {
				tk.setUserInfo(trim);
			}
			
			//放入集合
			pendingList.add(tk);
		}
		//		
		//
		int savePendingListSize = pendingService.savePendingList(pendingList);
		List<Pending> allPending = new ArrayList<Pending>();
		Pending pending = new Pending();
		pending.setGroupId("-1");
		List<Pending> pendingListOnchengNoEntry = pendingService.getPendingListOnchengNoEntry(pending);
		allPending.addAll(pendingListOnchengNoEntry);
		List<Pending> pendingListOncqEntry = pendingService.getPendingListOncqEntry(pending);
		allPending.addAll(pendingListOncqEntry);
		List<Pending> pendingListOnCqXnEntry = pendingService.getPendingListOnCqXnEntry(pending);
		allPending.addAll(pendingListOnCqXnEntry);
		List<Pending> pendingListOnjiaoEntry = pendingService.getPendingListOnjiaoEntry(pending);
		allPending.addAll(pendingListOnjiaoEntry);
		List<Pending> pendingListOnjiaoNoEntry = pendingService.getPendingListOnjiaoNoEntry(pending);
		allPending.addAll(pendingListOnjiaoNoEntry);
		List<Pending> pendingListOnShengEntry = pendingService.getPendingListOnShengEntry(pending);
		allPending.addAll(pendingListOnShengEntry);
		List<Pending> pendingListOntpEntry = pendingService.getPendingListOntpEntry(pending);
		allPending.addAll(pendingListOntpEntry);
		List<Pending> pendingListOntpNoEntry = pendingService.getPendingListOntpNoEntry(pending);
		allPending.addAll(pendingListOntpNoEntry);	
		int updatePendingAreaGroupType = pendingService.updatePendingAreaGroupType(allPending);
		out.print("上传数量 "+savePendingListSize+" | 划分数量"+updatePendingAreaGroupType);
		out.flush();
		out.close();
	/*	String content=file.getOriginalFilename()+" | 鏁伴噺:"+tklist.size();
		recordServiceImpl.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.IMPORT); */
	}
	/**
	 * 归属人上传
	 * @param file
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value = "/userexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void importtPendingExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session){
		PrintWriter out = null;
		User user = (User) session.getAttribute("user");
		// ajax中午乱码处理
		response.setCharacterEncoding("utf-8"); 
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			try {
				throw new Exception("创建工作表失败");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			in = file.getInputStream();
			listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		} catch (Exception e) {	
			e.printStackTrace();
		}
		List<Pending> pendingList = new ArrayList<Pending>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			Pending tk = new Pending();
			if("".contains(String.valueOf(lo.get(0))) || null==String.valueOf(lo.get(0))) {
				break;
			}
			tk.setId(Integer.parseInt(String.valueOf(lo.get(0))));
			tk.setUserInfo(String.valueOf(lo.get(18)).trim());		
			pendingList.add(tk);
		}
		int updatePendingInfoList = pendingService.updatePendingInfoList(pendingList);
		String content="案件归属人";
		oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.IMPORT);
		out.print("处理数量："+updatePendingInfoList);
		out.flush();
		out.close();
	}
	
	
	
	/**
	 * 新增案件/已结案件
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static List<Pending> getDiffrentNew(List<Pending> list1, List<Pending> list2) {
		//List1 中有 List2 中没有的
         List<Pending> diff = new ArrayList<Pending>();
         Map<String,Integer> map = new HashMap<String,Integer>(list1.size());
         for (Pending string : list1) {
             map.put(string.getRegistrationNumber(), 1);
         }
         for (Pending string : list2) {
             if(map.get(string.getRegistrationNumber())!=null)
             {
                 map.put(string.getRegistrationNumber(), 2);
                 continue;
             }
         }
         for(Entry<String, Integer> entry : map.entrySet()) {
        	 if(entry.getValue()==1) {
        		 for(Pending string : list1) {
                	 if(string.getRegistrationNumber().equals(entry.getKey()) ) {
                		 diff.add(string);
                		 break;
                	 }
                 }        		 
        	 }
         }     
        return diff;       
    }
	/**
	 * 数据覆盖查询
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static List<Pending> getDiffrentUpdate(List<Pending> list1, List<Pending> list2) {
		//List1 中新的数据        List2 中旧数据
         List<Pending> diff = new ArrayList<Pending>();
         Map<Object,Integer> map = new HashMap<Object,Integer>(list1.size());
         for (Pending string : list2) {
             map.put(string, 1);
         }
         for (Pending string : list1) {
             if(map.get(string)!=null)
             {
                 map.put(string, 2);
                 continue;
             }
         }
         for(Entry<Object, Integer> entry : map.entrySet()) {
        	 if(entry.getValue()==1) {
        		 diff.add((Pending)entry.getKey());      		 
        	 }
         }     
        return diff;        
    }
}
