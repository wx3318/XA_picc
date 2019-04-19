package com.picc.controller;
/**
 * 结案案件导入和导出
 * @author Lx
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.maven.shared.utils.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.entity.ClosingList;

import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;

import com.picc.entity.Group;
import com.picc.entity.User;
import com.picc.entity.UserCase;
import com.picc.service.ClosingListMessageService;
import com.picc.service.ClosingListService;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;
import com.picc.service.UserService;
import com.picc.util.ImportExcelUtil;

@Controller
@RequestMapping("/picc/closing")
public class ClosingListController {
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ClosingListService closingListService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClosingListMessageService closingListMessageService;
	@Autowired
	private OperationRecordService oRdService;
		/**
	 * 结案案件导入页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	@RequiresPermissions("picc.closing.closingimport")
	@RequestMapping(value="/closingimport.html")
	public ModelAndView importPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/closingimport");
		return model;
	}

	/**
	 * 结案案件统计页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	@RequiresPermissions("picc.closing.closinglist")
	@RequestMapping(value="/closinglist.html")
	public ModelAndView closingListPage(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/closinglist");
		List<Group> groupList = groupService.getGroupList();
		User user = (User) session.getAttribute("user");
		String roleid = user.getRoleId().toString();
		//权限
		if("12".equals(roleid)) {
			groupList.clear();
			Group group =new Group();
			group.setId(user.getGroupId());
			group.setGroupName(user.getGroup());
			groupList.add(group);
			model.addObject("groupList",groupList);
		}else {
			model.addObject("groupList",groupList);//机构
		}
		model.addObject("groupList",groupList);
		List<ClosingListSummarySearchMessage> closingListCaseType = closingListMessageService.getClosingListCaseType();
		model.addObject("closingListCaseType", closingListCaseType);
		List<String> listYear = closingListMessageService.getClosingListYear();
		model.addObject("listYear", listYear);
		return model;
	}
	/**
	 * 通过机构查询对应的查勘员
	 * @param groupId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getUserGroupId")
	@ResponseBody
	public List<User> getUserListByGroupId(@RequestParam(value = "groupId") Integer groupId ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		List<User> userList=userService.getUserListByGroupId(groupId);
		
		return userList;
	}
	/**
	 * 结案报表导入
	 */
	@ResponseBody
	@RequestMapping(value = "/closinglistexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void closingListUploadExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session)
			throws Exception {
		PrintWriter out = null;
		// ajax中午乱码处理
		response.setCharacterEncoding("utf-8"); 
		User user = (User) session.getAttribute("user");
		out = response.getWriter();
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			throw new Exception("工作簿为空");
		}
		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ClosingList> closingList = new ArrayList<ClosingList>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			ClosingList cl = new ClosingList();
			if (lo.size() <= 0) {
				break;
			}
			if (lo.get(0).equals("") && lo.get(1).equals("")) {
				break;
			}
				
			try {				
				cl.setReportNumber(String.valueOf(lo.get(0)));;
				cl.setRegistrationNumber(String.valueOf(lo.get(1)));
				cl.setRiskTime(Date.valueOf((String) lo.get(2)));
				cl.setClosingTime(Date.valueOf((String) lo.get(3)));
				cl.setCaseType(String.valueOf(lo.get(4)));	
				cl.setProspectorCode(String.valueOf(lo.get(5)));
				cl.setSurveyor(String.valueOf(lo.get(6)));
				cl.setDuration(String.valueOf(lo.get(7)));
				cl.setAmountOfMoney(String.valueOf(lo.get(8)));
				cl.setGroupId(Integer.parseInt(String.valueOf(lo.get(9))));
			}catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println(cl);
			closingList.add(cl);	
		}
		//分批导入，防止数据过大导入失败
		int closingListSize = closingList.size();
		int num = closingListSize/1000;
		int num2 = closingListSize%1000;
		List<ClosingList> arrayList = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			arrayList.clear();
		
			for (int j = i*1000; j < (1 + i) * 1000; j++) {
				arrayList.add(closingList.get(j));
			}
			closingListService.importClosingList(arrayList);
		}
		if (num2 != 0)
		{
			arrayList.clear();
			for (int i = num*1000; i < closingList.size(); i++) {
				arrayList.add(closingList.get(i));
			}
			closingListService.importClosingList(arrayList);
		}
		
		out = response.getWriter();
		out.print("导入成功"+closingList.size());
		out.flush();
		out.close();
		String content="结案导入";
		oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.IMPORT);
	
	}
	/**
	 * 结案信息查询结果
	 * @param closingListSummarySerachMessage
	 * @return
	 */
	@PostMapping("/serachClosingListSummary")
	@ResponseBody
	public List<ClosingListSummarySearchMessage> serachClosingListSummary (@RequestParam(value = "groupId") Integer groupId){
		//System.out.println(closingListSummarySerachMessage);
		List<ClosingListSummarySearchMessage> searchList=closingListService.serachClosingListSummaryByGroupId(groupId);
	    return searchList;
		
	}
	/**
	 * 结案信息查询结果
	 * @param closingListSummarySerachMessage
	 * @return
	 */
	@PostMapping("/getClosingListMessage")
	@ResponseBody
	public List<ClosingListMessage> getClosingCountMessageList(ClosingListSummarySearchMessage closingListSummarySearchMessage){
	
		if(closingListSummarySearchMessage.getMonth().equals("0")){
			closingListSummarySearchMessage.setMonth(null);
		}
		List<ClosingListMessage> closingListMessage = closingListMessageService.getClosingCountMessageList(closingListSummarySearchMessage);
		return closingListMessage;
		
	}
	

/**
 * 結案案件报表导出
 * @param request
 * @param response
 * @param session
 */
@ResponseBody
@RequestMapping(value="/closinglistexportexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
public void excelUserExport(HttpServletRequest request, HttpServletResponse response, HttpSession session,ClosingListSummarySearchMessage clss ) {
	
	String year = clss.getYear();
	String month = clss.getMonth();
	String moneyType = clss.getMoneyType();
    Integer groupId = clss.getGroupId();
    User user = (User) session.getAttribute("user");
   /* ClosingList cl=new ClosingList();
    cl.setGroupId(groupId);
	User user = (User) session.getAttribute("user");
	String roleid = user.getRoleId().toString();
	//权限
	if("12".equals(roleid)) {
		cl.setGroupId(user.getGroupId());
	}*/
	if(month!=null) {
	clss.setMonth(month);
	}
	clss.setMoneyType(moneyType);
	String fileName=null;
	if(month==null) {
		fileName =year+"年车险结案报表.xlsx";
	}else {
		 
		 fileName =year+"年"+month+"月车险结案个人报表.xlsx";
		
	}
	 
		try {
		
			List<ClosingListMessage> closingListMessage = closingListMessageService.getClosingCountMessageList(clss);//导出方法
			List<ClosingListMessage> closingListMessage1 = closingListMessageService.getClosingListCaseTypeCount(clss);//导出方法
			List<ClosingListMessage> closingListMessage2 = closingListMessageService.exportClosingList(clss);//导出方法
			List<ClosingListMessage> closingListMessage3 = closingListMessageService.getClosingCountMessageGroupList(clss);//导出方法
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("结案报表");
			XSSFCellStyle style = wbk.createCellStyle();;
    		style.setAlignment(HorizontalAlignment.CENTER); 

				for (int i = 0; i < 7; i++) {
				    sheet.setColumnWidth(i, 3500);
				}
				for (int i = 7; i < 13; i++) {
				    sheet.setColumnWidth(i, 5800);
				}
    		XSSFRow row = sheet.createRow(0);
    		row.setHeight((short) 500);
    		XSSFCell cell = row.createCell(0);
    		 // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
    		//第一行
    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));
    	    if(month!=null) {
    	    cell.setCellValue(year+"年"+month+"月结案报表");
    	    }else {
    	    	cell.setCellValue(year+"年结案报表");
    	    }
    	    cell.setCellStyle(style);
    	
    	    //第二行
//    	    style.setAlignment(HorizontalAlignment.CENTER);
    	    Row r1 = sheet.createRow(1);
    	    r1.setHeight((short) 500);
    	    String[] row_first ={"机构","查勘员", "结案件数(件)","结案金额(元)",moneyType+"元以下","","","","","","",""};
    	    for (int i = 0; i < row_first.length; i++) {
    	        Cell tempCell = r1.createCell(i);
    	        tempCell.setCellValue(row_first[i]);
    	        tempCell.setCellStyle(style);
    	    }
   
    	    // 合并
    	    sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
    	    sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
    	    sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
    	    sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
    	    sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 12));
    	    
    	    /*//第三行
    	    Row r3 = sheet.createRow(2);
    	    r3.setHeight((short) 500);
    	    String[] row_third = {"机构", "查勘员", "结案件数", "结案金额","当期天数","当年报案当年结案天数", "当年报案当年结案同比天数", "当年报案当月结案天数",
    	    		"当年报案当月结案同比天数","当月报案当月结案天数","当月报案当月结案同比天数"};
    	   
    	    for (int i = 0; i < row_third.length; i++) {
    	        Cell tempCell = r3.createCell(i);
    	        tempCell.setCellValue(row_third[i]);
    	        tempCell.setCellStyle(style);
    	    }*/

    	   row = sheet.createRow(2);
    	   row.setHeight((short) 500);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("");
    		cell = row.createCell(2);
    		cell.setCellStyle(style);
    		cell.setCellValue("");
    		cell = row.createCell(3);
    		cell.setCellStyle(style);
    		cell.setCellValue("");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("结案件数(件)");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("结案金额(元)");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("当期天数(天)");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("年报年结案天数(天)");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("年报年结案同比天数(天)");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("月报月结案天数(天)");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("月报月结案同比天数(天)");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("年报月结案天数(天)");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("年报月结案同比天数(天)");
    		
    		XSSFRow rows;
    		XSSFCell cells;
   
    		for (int i = 0; i < closingListMessage.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+3);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue("中心合计");
    			cells = rows.createCell(1);
    			cells.setCellValue(closingListMessage.get(i).getMyCaseType()); 
    			cells = rows.createCell(2);
    			cells.setCellValue(closingListMessage.get(i).getTotalCount());
    			cells = rows.createCell(3);
    			cells.setCellValue(closingListMessage.get(i).getSumMoney());
    			cells = rows.createCell(4);	    			 
    			cells.setCellValue(closingListMessage.get(i).getTotalCountLast());
    			cells = rows.createCell(5);	    			 
    			cells.setCellValue(closingListMessage.get(i).getSumMoneyLast());
    			cells = rows.createCell(6);	    			 
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getNowCount()));
    			cells = rows.createCell(7);
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getCountYearDay()));
    			cells = rows.createCell(8);
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getCountYearDayLast()));
    			cells = rows.createCell(9);
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getCountMonthDay()));
    			cells = rows.createCell(10);
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getCountMonthDayLast()));
    			cells = rows.createCell(11);
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getCountYearMonthDay()));
    			cells = rows.createCell(12);
    			cells.setCellValue(String .format("%.2f",closingListMessage.get(i).getCountYearMonthDayLast()));
    		}
    		for (int i = 0; i < closingListMessage1.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+closingListMessage.size()+3);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue("");
    			cells = rows.createCell(1);
    			cells.setCellValue("总计"); 
    			cells = rows.createCell(2);
    			cells.setCellValue(closingListMessage1.get(i).getTotalCount());
    			cells = rows.createCell(3);
    			cells.setCellValue(closingListMessage1.get(i).getSumMoney());
    			cells = rows.createCell(4);	    			 
    			cells.setCellValue(closingListMessage1.get(i).getTotalCountLast());
    			cells = rows.createCell(5);	    			 
    			cells.setCellValue(closingListMessage1.get(i).getSumMoneyLast());
    			cells = rows.createCell(6);	    			 
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getNowCount()));
    			cells = rows.createCell(7);
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getCountYearDay()));
    			cells = rows.createCell(8);
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getCountYearDayLast()));
    			cells = rows.createCell(9);
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getCountMonthDay()));
    			cells = rows.createCell(10);
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getCountMonthDayLast()));
    			cells = rows.createCell(11);
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getCountYearMonthDay()));
    			cells = rows.createCell(12);
    			cells.setCellValue(String .format("%.2f",closingListMessage1.get(i).getCountYearMonthDayLast()));
    		}
			
    		for (int i = 0; i < closingListMessage2.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+closingListMessage.size()+closingListMessage1.size()+3);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue(closingListMessage2.get(i).getGroupName());
    			cells = rows.createCell(1);
    			cells.setCellValue(closingListMessage2.get(i).getUserName()); 
    			cells = rows.createCell(2);
    			cells.setCellValue(closingListMessage2.get(i).getTotalCount());
    			cells = rows.createCell(3);
    			cells.setCellValue(closingListMessage2.get(i).getSumMoney());
    			cells = rows.createCell(4);	    			 
    			cells.setCellValue(closingListMessage2.get(i).getTotalCountLast());
    			cells = rows.createCell(5);	    			 
    			cells.setCellValue(closingListMessage2.get(i).getSumMoneyLast());
    			cells = rows.createCell(6);	    			 
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getNowCount()));
    			cells = rows.createCell(7);
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getCountYearDay()));
    			cells = rows.createCell(8);
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getCountYearDayLast()));
    			cells = rows.createCell(9);
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getCountMonthDay()));
    			cells = rows.createCell(10);
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getCountMonthDayLast()));
    			cells = rows.createCell(11);
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getCountYearMonthDay()));
    			cells = rows.createCell(12);
    			cells.setCellValue(String .format("%.2f",closingListMessage2.get(i).getCountYearMonthDayLast()));
    		}
    		for (int i = 0; i < closingListMessage3.size(); i++) { 
    			// sheet数据行
    			rows = sheet.createRow(i+closingListMessage.size()+closingListMessage1.size()+closingListMessage2.size()+3);
    			// 单元格
    			// 单元格里设置值
    			cells = rows.createCell(0);
    			cells.setCellValue(closingListMessage3.get(i).getGroupName());
    			cells = rows.createCell(1);
    			cells.setCellValue("总计"); 
    			cells = rows.createCell(2);
    			cells.setCellValue(closingListMessage3.get(i).getTotalCount());
    			cells = rows.createCell(3);
    			cells.setCellValue(closingListMessage3.get(i).getSumMoney());
    			cells = rows.createCell(4);	    			 
    			cells.setCellValue(closingListMessage3.get(i).getTotalCountLast());
    			cells = rows.createCell(5);	    			 
    			cells.setCellValue(closingListMessage3.get(i).getSumMoneyLast());
    			cells = rows.createCell(6);	    			 
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getNowCount()));
    			cells = rows.createCell(7);
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getCountYearDay()));
    			cells = rows.createCell(8);
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getCountYearDayLast()));
    			cells = rows.createCell(9);
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getCountMonthDay()));
    			cells = rows.createCell(10);
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getCountMonthDayLast()));
    			cells = rows.createCell(11);
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getCountYearMonthDay()));
    			cells = rows.createCell(12);
    			cells.setCellValue(String .format("%.2f",closingListMessage3.get(i).getCountYearMonthDayLast()));
    				
    		}
    		
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="结案报表导出";
				oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}			
	}catch (Exception e) {
		e.printStackTrace();
	}
}
}
