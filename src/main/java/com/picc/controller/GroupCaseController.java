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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.common.ResultObject;
import com.picc.common.Token;
import com.picc.entity.Group;
import com.picc.entity.GroupCase;
import com.picc.entity.User;
import com.picc.service.GroupCaseService;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;

/**
 * 月任务设置（控制层）
 * @author wangXi
 * @date 20/12/18
 * 
 */
@Controller
@RequestMapping("/picc/groupcase")
public class GroupCaseController {
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupCaseService groupCaseService;
	
	@Autowired
	private OperationRecordService oRdService;
	
	/**
	 * 月任务设置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/setcase.html")
	public ModelAndView setcasePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/casegroup");
		List<Group> groupList = groupService.getGroupList();
		model.addObject("groupList", groupList);
		return model;
	}
	
	/**
	 * 月任务设置数据页面
	 * @param request
	 * @param response
	 * @return	
	 */
	@RequiresPermissions("picc.groupcase.list")
	@RequestMapping(value="/list.html")
	public ModelAndView groupcaseListPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/casegrouplist");
		List<Group> groupList = groupService.getGroupList();
		model.addObject("groupList", groupList);
		return model;
	}
	/**
	 * 未决案件日报表
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	@RequiresPermissions("picc.groupcase.monthlist")
	@RequestMapping(value="/monthcase.html")
	public ModelAndView caseMonthPage(HttpServletRequest request, HttpServletResponse response,GroupCase groupCase) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/casemonth");
		List<Map<String, Object>> caseMonthList = groupCaseService.getCaseMonthList(groupCase);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr="";
		if(groupCase.getCreateDate()==null) {
			dateStr = sdf.format(new Date());
		}else {
			dateStr = sdf.format(groupCase.getCreateDate());
		}
		String[] split = dateStr.split("-");
		model.addObject("date",dateStr);
		model.addObject("dateMonth", split[0]+"年"+split[1]+"月");
		model.addObject("dateDay", split[1]+"月"+split[2]+"日");
		model.addObject("monthCase", caseMonthList);
		return model;
	}
	/**
	 * 新增月任务
	 * @param request
	 * @param response
	 * @param groupCase
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save.ajax")
	public ResultObject saveGroupCase(HttpServletRequest request, HttpServletResponse response,GroupCase groupCase) {
		ResultObject resultObject = new ResultObject();	
		//验证是否重复提交
		Boolean validToken = Token.validToken(request);
		if (!validToken) {
			resultObject.setSuccess(false);
			resultObject.setMsg("请不要重复提交!");
			return resultObject;
		}
		try {
			groupCaseService.saveGroupCase(groupCase);
			resultObject.setSuccess(true);
			resultObject.setMsg("保存成功");
		} catch (Exception e) {
			resultObject.setSuccess(false);
			resultObject.setMsg("保存失败");
		}		
		return resultObject;
	}
	/**
	 * ajax任务集合
	 * @param request
	 * @param response
	 * @param groupCase
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list.ajax")
	public ResultObject getGroupCaseList(HttpServletRequest request, HttpServletResponse response,GroupCase groupCase) {
		ResultObject resultObject = new ResultObject();	
		PageInfo<Map<String, Object>> pageGroupCaseList = groupCaseService.getPageGroupCaseList(groupCase);
		resultObject.setData(pageGroupCaseList.getList());
		resultObject.setRecordsTotal((int) pageGroupCaseList.getTotal());
		resultObject.setRecordsFiltered((int) pageGroupCaseList.getTotal());
		resultObject.setDraw(groupCase.getDraw());
		resultObject.setSuccess(true);
		resultObject.setMsg("查询成功");
		return resultObject;
	}
	
	
	/**
	 * 任务修改页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/update.html")
	public ModelAndView getPageUpdateGroupCase(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		String strId = request.getParameter("Id");
		GroupCase groupCase = new GroupCase();
		groupCase.setId(Integer.parseInt(strId));
		GroupCase groupCaseById = groupCaseService.getGroupCaseById(groupCase);
		model.setViewName("case/updatecasegroup");
		model.addObject("groupCase", groupCaseById);
		return model;
	}
	/**
	 * 月任务修改
	 * @param request
	 * @param response
	 * @param groupCase
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update.ajax")
	public ResultObject updateGroupcase(HttpServletRequest request, HttpServletResponse response,GroupCase groupCase) {
		ResultObject resultObject = new ResultObject();	
		groupCaseService.updateGroupCase(groupCase);
		resultObject.setSuccess(true);
		resultObject.setMsg("修改成功");
		return resultObject;
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete.ajax")
	public ResultObject deletGroupCase(HttpServletRequest request, HttpServletResponse response) {
		ResultObject resultObject = new ResultObject();	
		String strId = request.getParameter("Id");
		GroupCase groupCase = new GroupCase();
		groupCase.setId(Integer.parseInt(strId));
		groupCaseService.deletGroupCase(groupCase);
		resultObject.setSuccess(true);
		resultObject.setMsg("删除成功");
		return resultObject;		
	}
	/**
	 * 车险未决日报表导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value="/monthcaseexcel.ajax",method={ RequestMethod.POST, RequestMethod.GET })
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		GroupCase groupCase = new GroupCase();
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		String createDate = requestMap.get("createDate").toString();
		User user = (User) session.getAttribute("user");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			groupCase.setCreateDate(sdf.parse(createDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String[] split = createDate.split("-");
		String fileName =split[0]+"年"+split[1]+"月车险未决日报表.xlsx";
		//工作簿
		try {
			List<Map<String, Object>> caseMonthList = groupCaseService.getCaseMonthList(groupCase);
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
    	    cell.setCellValue(split[0]+"年"+split[1]+"月车险未决日报表");
    	    cell.setCellStyle(style);
    	  //第一列
    	    row = sheet.createRow(1);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("机构");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("起始数");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("每日新增");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("每日结案");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("结案总计");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("截止"+split[1]+"月"+split[2]+"日未决");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("起始目标");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("差距");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("预算目标");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("差距");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("挑战目标");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("差距");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("排名");
    		XSSFRow rows;
    		XSSFCell cells;
    		for (int i = 0; i < caseMonthList.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+2);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue(caseMonthList.get(i).get("group_name").toString());
    			cells = rows.createCell(1);
    			cells.setCellValue(caseMonthList.get(i).get("starting_number").toString());
    			cells = rows.createCell(2);
    			cells.setCellValue(caseMonthList.get(i).get("dayNewCase").toString());
    			cells = rows.createCell(3);
    			cells.setCellValue(caseMonthList.get(i).get("dayEndCase").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(caseMonthList.get(i).get("monthEndCase").toString());
    			cells = rows.createCell(5);   			
    			cells.setCellValue(caseMonthList.get(i).get("allCaseSize").toString());
    			cells = rows.createCell(6);
    			cells.setCellValue(caseMonthList.get(i).get("start_target_number").toString());
    			cells = rows.createCell(7);
    			cells.setCellValue(caseMonthList.get(i).get("startDiff").toString());
    			cells = rows.createCell(8);
    			cells.setCellValue(caseMonthList.get(i).get("budget_target_number").toString());
    			cells = rows.createCell(9);
    			cells.setCellValue(caseMonthList.get(i).get("budgetDiff").toString());
    			cells = rows.createCell(10);
    			cells.setCellValue(caseMonthList.get(i).get("challeng_number").toString());
    			cells = rows.createCell(11);
    			cells.setCellValue(caseMonthList.get(i).get("challengDiff").toString());
    			cells = rows.createCell(12);
    			cells.setCellValue(caseMonthList.get(i).get("rangking").toString());   		
    		}
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="车险日报表";
				oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			
		}
	}
}
