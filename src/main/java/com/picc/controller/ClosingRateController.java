package com.picc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.picc.common.Constant;
import com.picc.entity.ClosingList;
import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;
import com.picc.entity.ClosingRateList;
import com.picc.entity.ClosingRateListMessage;
import com.picc.entity.ClosingRateListPending;
import com.picc.entity.Group;
import com.picc.entity.User;
import com.picc.service.ClosingRateListService;
import com.picc.service.OperationRecordService;
import com.picc.util.ImportExcelUtil;
/**
 * 
 * @author Lx
 *  结案率导入和统计导出
 */
@Controller
@RequestMapping("/picc/closing")
public class ClosingRateController {
	
	@Autowired
	private ClosingRateListService closingRateListService;
	@Autowired
	private OperationRecordService oRdService;
	/**
	 * 结案率案件导入页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	@RequiresPermissions("picc.closing.closingrateimport")
	@RequestMapping(value="/closingrateimport.html")
	public ModelAndView importPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/closingrateimport");
		return model;
	}
	/**
	 * 结案率案件统计页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	@RequiresPermissions("picc.closing.closingratelist")
	@RequestMapping(value="/closingratelist.html")
	public ModelAndView closingListPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		model.setViewName("case/closingratelist");
		List<String> closingRateYearList = closingRateListService.getClosingRateYearList();
		model.addObject("closingRateYearList", closingRateYearList);
		return model;
	}
	/**
	 * 结案报表导入
	 */
	@ResponseBody
	@RequestMapping(value = "/closingrateexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
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
		List<ClosingRateList> closingRateList = new ArrayList<ClosingRateList>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			ClosingRateList cl = new ClosingRateList();
			if (lo.size() <= 0) {
				break;
			}
			if (lo.get(0).equals("") && lo.get(1).equals("")) {
				break;
			}
			try {				
				cl.setReportNumber(String.valueOf(lo.get(0)));
				cl.setRegistrationNumber(String.valueOf(lo.get(1)));
				cl.setRiskTime(Date.valueOf((String) lo.get(2)));
				cl.setClosingTime(Date.valueOf((String) lo.get(3)));
				cl.setProspectorCode(String.valueOf(lo.get(4)));
				cl.setSurveyor(String.valueOf(lo.get(5)));
				cl.setDuration(String.valueOf(lo.get(6)));
				cl.setAmountOfMoney(String.valueOf( lo.get(7)));
				cl.setGroupName(String.valueOf(lo.get(8)));
			}catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println(cl);
			closingRateList.add(cl);	
		}
		ClosingRateList crl = new ClosingRateList();
		List<ClosingRateList> closingListParam = closingRateListService.getClosingRateListParam(crl);

		List<ClosingRateList> newDiffrent = getDiffrentNew(closingRateList,closingListParam);
		//分批导入，防止数据过大导入失败
		int closingListSize = newDiffrent.size();
		int num = closingListSize/1000;
		int num2 = closingListSize%1000;
		List<ClosingRateList> arrayList = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			arrayList.clear();
		
			for (int j = i*1000; j < (1 + i) * 1000; j++) {
				arrayList.add(newDiffrent.get(j));
			}
			closingRateListService.importClosingRateList(arrayList);
		}
		if (num2 != 0)
		{
			arrayList.clear();
			for (int i = num*1000; i < newDiffrent.size(); i++) {
				arrayList.add(newDiffrent.get(i));
			}
			closingRateListService.importClosingRateList(arrayList);
		}
		out = response.getWriter();
		out.print("导入成功"+newDiffrent.size());
		out.flush();
		out.close();
		String content="结案率结案导入";
		oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.IMPORT);
	
	}
	/**
	 * 未结案报表导入
	 */
	@ResponseBody
	@RequestMapping(value = "/closingratependingexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void closingRateListUploadExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session)
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
		List<ClosingRateListPending> closingRateListPending = new ArrayList<ClosingRateListPending>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			ClosingRateListPending cl = new ClosingRateListPending();
			if (lo.size() <= 0) {
				break;
			}
			if (lo.get(0).equals("") && lo.get(1).equals("")) {
				break;
			}
			try {	
				cl.setRegistrationNumber(String.valueOf(lo.get(0)));
				cl.setReportNumber(String.valueOf(lo.get(1)));;
				cl.setReportingTime(Date.valueOf((String) lo.get(2)));
				cl.setRiskTime(Date.valueOf((String) lo.get(3)));
				cl.setFilingTime(Date.valueOf((String) lo.get(4)));
				cl.setEstimatedLossAmount(String.valueOf( lo.get(5)));
				cl.setCaseType(String.valueOf(lo.get(6)));
				cl.setProspectorCode(String.valueOf(lo.get(7)));
				cl.setSurveyor(String.valueOf(lo.get(8)));
				cl.setGroupName(String.valueOf(lo.get(9)));
			}catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println(cl);
			closingRateListPending.add(cl);	
		}
		ClosingRateListPending crlp = new ClosingRateListPending();
		List<ClosingRateListPending> closingRateListPendingParam = closingRateListService.getClosingRateListPendingParam(crlp);
		List<ClosingRateListPending> newDiffrent = getDiffrentNew1(closingRateListPending,closingRateListPendingParam);
		//分批导入，防止数据过大导入失败
		int closingListSize = newDiffrent.size();
		int num = closingListSize/1000;
		int num2 = closingListSize%1000;
		List<ClosingRateListPending> arrayList = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			arrayList.clear();
		
			for (int j = i*1000; j < (1 + i) * 1000; j++) {
				arrayList.add(newDiffrent.get(j));
			}
			closingRateListService.importClosingRateListPending(arrayList);
		}
		if (num2 != 0)
		{
			arrayList.clear();
			for (int i = num*1000; i < newDiffrent.size(); i++) {
				arrayList.add(newDiffrent.get(i));
			}
			closingRateListService.importClosingRateListPending(arrayList);
		}
		
		out = response.getWriter();
		out.print("导入成功"+newDiffrent.size());
		out.flush();
		out.close();
		String content="结案率未结导入";
		oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.IMPORT);
	
	}
	/**
	 * 条件查询结果
	 * @param closingListSummarySearchMessage
	 * @return
	 */
	@PostMapping("/getClosingRateMessage")
	@ResponseBody
	public List<ClosingRateListMessage> getClosingRateMessage(ClosingListSummarySearchMessage closingListSummarySearchMessage){
		if(closingListSummarySearchMessage.getMonth().equals("0")){
			closingListSummarySearchMessage.setMonth(null);
		}
		List<ClosingRateListMessage> closingRateList = closingRateListService.getClosingRateList(closingListSummarySearchMessage);
		
		return closingRateList;
		
	}
	
	/**
	 * 結案率案件报表导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value="/closingratelistexportexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
	public void excelClosingListExport(HttpServletRequest request, HttpServletResponse response, HttpSession session,ClosingListSummarySearchMessage clss ) {
		
		String year = clss.getYear();
		String month = clss.getMonth();
		User user = (User) session.getAttribute("user");
	   
		if(month!=null) {
		clss.setMonth(month);
		}
		
		String fileName=null;
		if(month==null) {
			fileName =year+"年车险结案率报表.xlsx";
		}else {
			 
			 fileName =year+"年"+month+"月车险结案率报表.xlsx";
			
		}
		 
			try {
			
			List<ClosingRateListMessage> closingRateList = closingRateListService.getClosingRateList(clss);//导出方法
			    request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream;charset=utf-8");
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
				response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
				//工作簿
				XSSFWorkbook wbk = new XSSFWorkbook();
				// Sheet页
				XSSFSheet sheet = wbk.createSheet("结案率报表");
				XSSFCellStyle style = wbk.createCellStyle();;
	    		style.setAlignment(HorizontalAlignment.CENTER); 

					for (int i = 0; i < 4; i++) {
					    sheet.setColumnWidth(i, 4500);
					}
					for (int i = 1; i < 10; i++) {
					    sheet.setColumnWidth(i, 2700);
					}
	    		XSSFRow row = sheet.createRow(0);
	    		row.setHeight((short) 500);
	    		XSSFCell cell = row.createCell(0);
	    		 // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
	    		//第一行
	    	    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
	    	    if(month!=null) {
	    	    cell.setCellValue(year+"年"+month+"月车险结案率报表");
	    	    }else {
	    	    	cell.setCellValue(year+"年车险结案率报表");
	    	    }
	    	    cell.setCellStyle(style);
	    	
	    	    //第二行
//	    	    style.setAlignment(HorizontalAlignment.CENTER);
	    	    Row r1 = sheet.createRow(1);
	    	    r1.setHeight((short) 500);
	    	    String[] row_first ={"归属机构","5000元以下", "","","万元以下","","","万元以上","","",};
	    	    for (int i = 0; i < row_first.length; i++) {
	    	        Cell tempCell = r1.createCell(i);
	    	        tempCell.setCellValue(row_first[i]);
	    	        tempCell.setCellStyle(style);
	    	    }
	   
	    	    // 合并
	    	    sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
	    	    sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
	    	    sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 6));
	    	    sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 9));
	    	   
	    	    
	    	   //第三行

	    	   row = sheet.createRow(2);
	    	   row.setHeight((short) 500);
	    	    //单元格
	    		cell = row.createCell(0);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("");
	    		cell = row.createCell(1);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("立案数(件)");
	    		cell = row.createCell(2);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案数(件)");
	    		cell = row.createCell(3);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案率(%)");
	    		cell = row.createCell(4);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("立案数(件)");
	    		cell = row.createCell(5);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案数(件)");
	    		cell = row.createCell(6);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案率(%)");
	    		cell = row.createCell(7);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("立案数(件)");
	    		cell = row.createCell(8);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案数(件)");
	    		cell = row.createCell(9);
	    		cell.setCellStyle(style);
	    		cell.setCellValue("结案率(%)");
	    		
	    		XSSFRow rows;
	    		XSSFCell cells;
	   
	    		for (int i = 0; i < closingRateList.size(); i++) { 
	    			// sheet数据行
	    			rows = sheet.createRow(i+3);
	    			// 单元格
	    			// 单元格里设置值
	    			
	    			cells = rows.createCell(0);
	    			cells.setCellValue(closingRateList.get(i).getGroupName()); 
	    			cells = rows.createCell(1);
	    			cells.setCellValue(closingRateList.get(i).getRegistrationFiveNum());
	    			cells = rows.createCell(2);
	    			cells.setCellValue(closingRateList.get(i).getClosedFiveNum());
	    			cells = rows.createCell(3);	    			 
	    			cells.setCellValue(closingRateList.get(i).getClosedFiveRate());
	    			cells = rows.createCell(4);	    			 
	    			cells.setCellValue(closingRateList.get(i).getRegistrationOneDownNum());
	    			cells = rows.createCell(5);	    			 
	    			cells.setCellValue(closingRateList.get(i).getClosedOneDownNum());
	    			cells = rows.createCell(6);	    			 
	    			cells.setCellValue(closingRateList.get(i).getClosedOneDownRate());
	    			cells = rows.createCell(7);	    			 
	    			cells.setCellValue(closingRateList.get(i).getRegistrationOneUpNum());
	    			cells = rows.createCell(8);	    			 
	    			cells.setCellValue(closingRateList.get(i).getClosedOneUpNum());
	    			cells = rows.createCell(9);	    			 
	    			cells.setCellValue(closingRateList.get(i).getClosedOneUpRate());
	    			
	    		}
				try {
					OutputStream out = response.getOutputStream();
					wbk.write(out);
					out.close();
					String content="结案率报表导出";
					oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
				} catch (IOException e) {
					e.printStackTrace();
				}			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增案件/已结案件
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static List<ClosingRateList> getDiffrentNew(List<ClosingRateList> list1, List<ClosingRateList> list2) {
		//List1 中有 List2 中没有的
	     List<ClosingRateList> diff = new ArrayList<ClosingRateList>();
	     Map<String,Integer> map = new HashMap<String,Integer>(list1.size());
	     for (ClosingRateList string : list1) {
	         map.put(string.getRegistrationNumber(), 1);
	     }
	     for (ClosingRateList string : list2) {
	         if(map.get(string.getRegistrationNumber())!=null)
	         {
	             map.put(string.getRegistrationNumber(), 2);
	             continue;
	         }
	     }
	     for(Entry<String, Integer> entry : map.entrySet()) {
	    	 if(entry.getValue()==1) {
	    		 for(ClosingRateList string : list1) {
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
	 * 新增案件/已结案件
	 * @param list1
	 * @param list2
	 * @return
	 */
	private static List<ClosingRateListPending> getDiffrentNew1(List<ClosingRateListPending> list1, List<ClosingRateListPending> list2) {
		//List1 中有 List2 中没有的
	     List<ClosingRateListPending> diff = new ArrayList<ClosingRateListPending>();
	     Map<String,Integer> map = new HashMap<String,Integer>(list1.size());
	     for (ClosingRateListPending string : list1) {
	         map.put(string.getRegistrationNumber(), 1);
	     }
	     for (ClosingRateListPending string : list2) {
	         if(map.get(string.getRegistrationNumber())!=null)
	         {
	             map.put(string.getRegistrationNumber(), 2);
	             continue;
	         }
	     }
	     for(Entry<String, Integer> entry : map.entrySet()) {
	    	 if(entry.getValue()==1) {
	    		 for(ClosingRateListPending string : list1) {
	            	 if(string.getRegistrationNumber().equals(entry.getKey()) ) {
	            		 diff.add(string);
	            		 break;
	            	 }
	             }        		 
	    	 }
	     }     
	    return diff;       
	}
}
