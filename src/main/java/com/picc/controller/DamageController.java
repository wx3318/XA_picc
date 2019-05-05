 package com.picc.controller;
/**
 * 定损控制层
 * @author wangXi	
 * @date 2019/02/25
 *
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import org.apache.poi.hssf.util.CellRangeAddress;
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

import com.alibaba.fastjson.JSON;
import com.google.inject.internal.BindingBuilder;
import com.picc.common.BasicUtil;
import com.picc.common.Constant;
import com.picc.entity.Damage;
import com.picc.entity.DamageLow;
import com.picc.entity.DamageTicket;
import com.picc.entity.Group;
import com.picc.entity.Pending;
import com.picc.entity.User;
import com.picc.service.DamageLowService;
import com.picc.service.DamageService;
import com.picc.service.DamageTicketService;
import com.picc.service.GroupService;
import com.picc.service.OperationRecordService;
import com.picc.service.UserService;
import com.picc.util.ImportExcelUtil;

@Controller
@RequestMapping("/picc/damage")
public class DamageController {
	
	@Autowired
	private DamageService damageService;
	@Autowired
	private DamageLowService damageLowService;
	@Autowired
	private DamageTicketService damageTicketService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userservice;
	@Autowired
	private OperationRecordService oRdService;
	/**
	 * 定损导入页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	@RequiresPermissions("picc.damage.import")
	@RequestMapping(value="/import.html")
	public ModelAndView importPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		//页面设置
		model.setViewName("damage/import");
		return model;
	}
	/**
	 * 核损月统计页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/monthlist.html")
	public ModelAndView getMonthListPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		//页面设置
		
		model.setViewName("damage/monthlist");
		return model;
	}
	/**
	 * 核损个人统计页面
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	@RequiresPermissions("picc.damage.userinfo")
	@RequestMapping(value="/userlist.html")
	public ModelAndView goUserDamageList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView model  = new ModelAndView();
		//页面设置
		List<Group> groupList = groupService.getGroupList();
		User user = (User) session.getAttribute("user");
		if("12".equals(user.getRoleId().toString())) {
			for(Group g:groupList) {
				if(g.getId().equals(user.getGroupId())) {
					List<Group> newgroup = new ArrayList<Group>();
					Group group = new Group();
					group.setId(user.getGroupId());
					group.setGroupName(user.getGroup());
					newgroup.add(group);
					groupList.clear();
					groupList=newgroup;
				}
			}
		}
		model.addObject("listCategory", groupList);
		model.setViewName("damage/userinfo");
		return model;		
	}
	/**
	 * 个人统计条件查询
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userdamagelist.ajax")
	public List<Map<String, Object>> queryUserDamegeList(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map =  new HashMap<String,Object>();
		Object username = request.getParameter("username");
		Object param = request.getParameter("param");
		Object damageDate = request.getParameter("damageDate");
		map.put("param", param); //统计年月				
		map.put("damageDate",damageDate);//日期
		map.put("userName", username);// 人
		List<Map<String, Object>> userDamageMonthOrYearList = damageTicketService.getUserDamageMonthOrYearList(map); 
		return userDamageMonthOrYearList;
	}
	
	/**
	 * 核损组别统计页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("picc.damage.grouplist")
	@RequestMapping(value="/grouplist.html")
	public ModelAndView goGroupDamageList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ModelAndView model  = new ModelAndView();		
		List<Group> groupList = groupService.getGroupList();
		User user = (User) session.getAttribute("user");
		if("12".equals(user.getRoleId().toString())) {
			for(Group g:groupList) {
				if(g.getId().equals(user.getGroupId())) {
					List<Group> newgroup = new ArrayList<Group>();
					Group group = new Group();
					group.setId(user.getGroupId());
					group.setGroupName(user.getGroup());
					newgroup.add(group);
					groupList.clear();
					groupList=newgroup;
				}
			}
		}
		model.addObject("listCategory", groupList);
		model.setViewName("damage/grouplist");
		return model;
	}
	/**
	 * 核损组别条件查询
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/groupdamagelist.ajax")
	 public List<Map<String, Object>>queryGroupDamegeList(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> map =  new HashMap<String,Object>();
		Object groupId = request.getParameter("groupId");
		Object param = request.getParameter("param");
		Object damageDate = request.getParameter("damageDate");
		map.put("param", param); //统计年月				
		map.put("damageDate",damageDate);//日期
		map.put("groupId", groupId);// 组别
		List<Map<String, Object>> groupDamageMonthOrYearList = damageTicketService.getGroupDamageMonthOrYearList(map);
		 return groupDamageMonthOrYearList;
	 }
	
	/**
	 * 二级菜单
	 * @param cid1
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/userlist.ajax")
	public Map<String,Object> findSecondTypeById(String cid1,HttpServletResponse response ) throws Exception{
		User user =  new User();
		user.setGroupId(Integer.parseInt(cid1));
		List<Map<String, Object>> lTbCategories = userservice.getUserListMap(user);		
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if(null != lTbCategories && lTbCategories.size()>0){
			for(Map<String, Object> s:lTbCategories) {
				Map<String,String> taskMap=new HashMap<String,String>();
				taskMap.put("id",s.get("username").toString());
				taskMap.put("name", s.get("name").toString());
				list.add(taskMap);
			}
			jsonMap.put("tasks", list);
		}
		return jsonMap;
	}
	/**
	 * 定损数据导入
	 * @param file
	 * @param response
	 * @param session
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/importdamage.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void importDamageExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session) 
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
		//poi读取Excel数据
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建集合存储 数据
		List<Damage> damageList = new ArrayList<Damage>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			Damage tk = new Damage();
			if(i > 0){
				if("".contains(String.valueOf(lo.get(0))) || null==String.valueOf(lo.get(0))) {
					break;
				}
			}
			tk.setReportNumber(String.valueOf(lo.get(0)).trim());
			if(null!=String.valueOf(lo.get(1)) && !"".equals(String.valueOf(lo.get(1)))) {
				tk.setDamageDate(sdf.parse(String.valueOf(lo.get(1))));
			}
			tk.setPlateNumber(String.valueOf(lo.get(2)).trim());
			tk.setPlateCodeNumber(String.valueOf(lo.get(3)).trim());
			tk.setDamageMoney(String.valueOf(lo.get(4)).trim());
			tk.setDamageLeve(String.valueOf(lo.get(5)).trim());
			tk.setDamageCode(String.valueOf(lo.get(6)).trim());
			tk.setDamageName(String.valueOf(lo.get(7)).trim());
			tk.setRepairCode(String.valueOf(lo.get(8)).trim());
			tk.setRepairName(String.valueOf(lo.get(9)).trim());
			tk.setRepairLeve(String.valueOf(lo.get(10)).trim());			
			//放入集合
			damageList.add(tk);
		}
		//数据存储 sql
		int saveDamageListSize = damageService.saveDamageList(damageList);
		out.print("上传数量 "+saveDamageListSize);
		out.flush();
		out.close();
	}
	/**
	 * 低碳导入
	 * @param file 文件
	 * @param response 
	 * @param session 
	 * @throws Exception 输入流异常
	 */
	@ResponseBody
	@RequestMapping(value = "/importdamagedt.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void importDamagedtExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session) 
			throws Exception {
		PrintWriter out = null;
		//中文乱码处理
		response.setCharacterEncoding("utf-8"); 
		out = response.getWriter();
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			throw new Exception("创建工作表失败");
		}
		in = file.getInputStream();
		//poi读取Excel数据
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建集合存储 数据
		List<DamageLow> damageLowList = new ArrayList<DamageLow>();
		for(int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			DamageLow tk = new DamageLow();
			if(i > 0){
				if("".contains(String.valueOf(lo.get(0))) || null==String.valueOf(lo.get(0))) {
					break;
				}
			}
			//区域
			if(!"61010000".equals(String.valueOf(lo.get(2)).trim())) {
				continue;
			}
			//是否低碳 0  不是 跳过
			if("1".equals(String.valueOf(lo.get(13)))) {
				tk.setDamageId(String.valueOf(lo.get(0)).trim());
				tk.setReportNumber(String.valueOf(lo.get(1)).trim());
				tk.setUnderwritingCode(String.valueOf(lo.get(2)).trim());
				tk.setPlateNumber(String.valueOf(lo.get(3)).trim());
				tk.setDamageCode(String.valueOf(lo.get(4)).trim());
				tk.setDamageName(String.valueOf(lo.get(5)).trim());
				tk.setDamageMoney(String.valueOf(lo.get(6)).trim());
				tk.setRepairCode(String.valueOf(lo.get(7)).trim());
				tk.setRepairName(String.valueOf(lo.get(8)).trim());
				tk.setRepairLowCode(String.valueOf(lo.get(11)));
				tk.setRepairLowName(String.valueOf(lo.get(12)));
				tk.setIsLow(String.valueOf(lo.get(13)));
				if(!"".equals(String.valueOf(lo.get(14)).trim()) && null!=String.valueOf(lo.get(14)) && !"null".equals(String.valueOf(lo.get(14)))) {				
					tk.setDamageLoseMoney(BigDecimal.valueOf(Double.parseDouble(String.valueOf(lo.get(14)))));
				}
				if(!"".equals(String.valueOf(lo.get(15)).trim()) && null!=String.valueOf(lo.get(15)) && !"null".equals(String.valueOf(lo.get(15)))) {				
					tk.setDamageLowMoney(BigDecimal.valueOf(Double.parseDouble(String.valueOf(lo.get(15)))));
				}
				tk.setDamageLowNumber(String.valueOf(lo.get(16)));
				if(!"".equals(String.valueOf(lo.get(17)).trim()) && null!=String.valueOf(lo.get(17)) && !"null".equals(String.valueOf(lo.get(17)))) {				
					tk.setDamageLowDate(sdf.parse(String.valueOf(lo.get(17))));
				}
				//第 5 位 7、9是修理厂    repairLeve 1=4s店  其他=修理厂
				if(null!=tk.getRepairLowCode() && !"".equals(tk.getRepairLowCode()) && !"null".equals(tk.getRepairLowCode())) {
					char charRepair = tk.getRepairLowCode().charAt(4);
					String repairLeve = String.valueOf(charRepair);
					if("7".equals(repairLeve) || "9".equals(repairLeve)) {
						tk.setRepairLeve("2");
					}else {
						tk.setRepairLeve("1");
					}
				}
				damageLowList.add(tk);
			}			
		}
		if(damageLowList.size()>0) {
			int saveDamageLowList = damageLowService.saveDamageLowList(damageLowList);
			out.print("新增数量 ："+saveDamageLowList);
		}else {
			out.print("新增数量 ： 0 || 没有低碳修复数据");
		}	
		out.flush();
		out.close();
	}
	/**
	 * 清单导入
	 * @param file 文件
	 * @param response 
	 * @param session 
	 * @throws Exception 输入流异常
	 */
	@ResponseBody
	@RequestMapping(value = "/importdamagetk.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void importDamageTicketExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response,HttpSession session) 
			throws Exception {
		PrintWriter out = null;
		//中文乱码处理
		response.setCharacterEncoding("utf-8"); 
		out = response.getWriter();
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			throw new Exception("创建工作表失败");
		}
		in = file.getInputStream();
		//poi读取Excel数据
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//创建集合存储 数据
		List<DamageTicket> damageTicketList = new ArrayList<DamageTicket>();
		for(int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			DamageTicket tk = new DamageTicket();
			if(i > 0){
				if("".contains(String.valueOf(lo.get(0))) || null==String.valueOf(lo.get(0))) {
					break;
				}
			}
			tk.setDamageTicketId(String.valueOf(lo.get(0)).trim());
			tk.setReportNumber(String.valueOf(lo.get(1)).trim());
			tk.setDamageTicketNumber(String.valueOf(lo.get(2)).trim());
			if(!"".equals(String.valueOf(lo.get(3)).trim()) && null!=String.valueOf(lo.get(3)) && !"null".equals(String.valueOf(lo.get(3)))) {				
				tk.setDamageTicketMoney(BigDecimal.valueOf(Double.parseDouble(String.valueOf(lo.get(3)))));
			}
			if(!"".equals(String.valueOf(lo.get(4)).trim()) && null!=String.valueOf(lo.get(4)) && !"null".equals(String.valueOf(lo.get(3)))) {
				tk.setDamageTimeMoney(BigDecimal.valueOf(Double.parseDouble(String.valueOf(lo.get(4)).trim())));
			}	
			tk.setDamageCode(String.valueOf(lo.get(5)).trim());
			tk.setDamageName(String.valueOf(lo.get(6)).trim());
			tk.setDamageTicketDate(sdf.parse(String.valueOf(lo.get(7))) );
			tk.setRepairCode(String.valueOf(lo.get(8)).trim());
			tk.setRepairName(String.valueOf(lo.get(9)).trim());
			tk.setRepairLeve(String.valueOf(lo.get(10)).trim());
			damageTicketList.add(tk);
		}
		
		if(damageTicketList.size()>0) {
			int saveDamageLowList = damageTicketService.saveDamageTicketList(damageTicketList);
			out.print("新增数量 ："+saveDamageLowList);
		}else {
			out.print("新增数量 ： 0 || 没有定损清单数据");
		}	
		out.flush();
		out.close();
	}
	
	/**
	 * 核损个人导出
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequiresPermissions("picc.damage.excel.user")
	@ResponseBody
	@RequestMapping(value="/damageuserexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
	public void exportUserExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String,Object> map =  new HashMap<String,Object>();
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		String param = requestMap.get("param").toString();
		String damageDate = requestMap.get("damageDate").toString();
		String userName = requestMap.get("userName").toString();		
		User user = (User) session.getAttribute("user");
		map.put("param", param); //统计年月				
		map.put("damageDate",damageDate);//日期
		map.put("userName", userName);// 人								
		try {
			User userByuname = userservice.getUserByuname(Integer.parseInt(userName));
			String fileName="个人核损清单.xlsx";
			List<Map<String, Object>> pengdingGroup = damageTicketService.getUserDamageMonthOrYearList(map); 
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("定损员");
			XSSFCellStyle style = wbk.createCellStyle();;
			style.setAlignment(HorizontalAlignment.CENTER);  
			//创建合并单元格  ---begin
			CellRangeAddress region = new CellRangeAddress(0, 0, 5, 16);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
			CellRangeAddress region2 = new CellRangeAddress(1, 1, 7, 10);// 起始行号，终止行号， 起始列号，终止列号
			   //在sheet里增加合并单元格  
			sheet.addMergedRegion(region);
			sheet.addMergedRegion(region2);
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			//第一列
    	    row = sheet.createRow(0);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("部门");
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue("定损员");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		String[] split = damageDate.split("-");
    		if("1".equals(param)) {
    			cell.setCellValue(split[0]+"年"+split[1]+"月——————月统计");
    		}else if("2".equals(param)) {
    			cell.setCellValue(split[0]+"年——————年统计");
    		}		    		
    		//第二列
    	    row = sheet.createRow(1);
    	    //单元格
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("件数");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("金额");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("低碳外修");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("单均金额");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("换件金额");
    		cell = row.createCell(13);
    		cell.setCellStyle(style);
    		cell.setCellValue("换件数量");
    		cell = row.createCell(14);
    		cell.setCellStyle(style);
    		cell.setCellValue("单均换件数量");
    		cell = row.createCell(15);
    		cell.setCellStyle(style);
    		cell.setCellValue("工时金额");
    		cell = row.createCell(16);
    		cell.setCellStyle(style);
    		cell.setCellValue("单均工时");   		
    		//第二列
    	    row = sheet.createRow(2);
    	    //单元格    	    
    	    cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue(userByuname.getGroup());
    		cell = row.createCell(1);
    		cell.setCellStyle(style);
    		cell.setCellValue(userByuname.getName());
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以下件数");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以下金额");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以上件数");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以上金额");
    		XSSFRow rows;
    		XSSFCell cells;
    		for (int i = 0; i < pengdingGroup.size(); i++) { 
    			// sheet数据行第
    			rows = sheet.createRow(i+3);
    			// 单元格
    			// 单元格里设置值从第三列开始
    			cells = rows.createCell(2);
    			if(null!=pengdingGroup.get(i).get("type")) {
    				cells.setCellValue(pengdingGroup.get(i).get("type").toString());
    			}else {
    				cells.setCellValue("");
    			}  			
    			cells = rows.createCell(3);
    			cells.setCellValue(pengdingGroup.get(i).get("repair_name").toString());
    			cells = rows.createCell(4);
    			cells.setCellValue(pengdingGroup.get(i).get("milo").toString());
    			cells = rows.createCell(5);
    			if(null!=pengdingGroup.get(i).get("allsize")) {
    				cells.setCellValue(pengdingGroup.get(i).get("allsize").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(6);
    			if(null!=pengdingGroup.get(i).get("allmoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("allmoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}
    			cells = rows.createCell(7);
    			if(null!=pengdingGroup.get(i).get("lownumber")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lownumber").toString());
    			}else {
    				cells.setCellValue(0);
    			}   			
    			cells = rows.createCell(8); 
    			if(null!=pengdingGroup.get(i).get("lowmoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lowmoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}  			
    			cells = rows.createCell(9);
    			if(null!=pengdingGroup.get(i).get("lownumbers")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lownumbers").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(10);
    			if(null!=pengdingGroup.get(i).get("lowmoneys")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lowmoneys").toString());
    			}else {
    				cells.setCellValue(0);
    			}     			
    			cells = rows.createCell(11);
    			if(null!=pengdingGroup.get(i).get("eqmoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("eqmoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(12);
    			if(null!=pengdingGroup.get(i).get("money")) {
    				cells.setCellValue(pengdingGroup.get(i).get("money").toString());
    			}else {
    				cells.setCellValue(0);
    			}   			
    			cells = rows.createCell(13);
    			if(null!=pengdingGroup.get(i).get("size")) {
    				cells.setCellValue(pengdingGroup.get(i).get("size").toString());
    			}else {
    				cells.setCellValue(0);
    			}   			
    			cells = rows.createCell(14);
    			if(null!=pengdingGroup.get(i).get("eqsize")) {
    				cells.setCellValue(pengdingGroup.get(i).get("eqsize").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(15);
    			if(null!=pengdingGroup.get(i).get("timemoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("timemoney").toString()); 
    			}else {
    				cells.setCellValue(0);
    			}    			  		
    			cells = rows.createCell(16);
    			if(null!=pengdingGroup.get(i).get("eqtimemoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("eqtimemoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    		}
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="个人核损";
			 oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 分中心导出
	 * @param request
	 * @param response picc.damage.excel.user
	 * @param session
	 * @throws UnsupportedEncodingException
	 */
	@RequiresPermissions("picc.damage.excel.group")
	@ResponseBody
	@RequestMapping(value="/damagegroupexcel.ajax",method= { RequestMethod.POST, RequestMethod.GET})
	public void exportGroupExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String,Object> map =  new HashMap<String,Object>();
		Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
		String param = requestMap.get("param").toString();
		String damageDate = requestMap.get("damageDate").toString();
		String groupId = requestMap.get("groupId").toString();	
		User user = (User) session.getAttribute("user");
		map.put("param", param); //统计年月				
		map.put("damageDate",damageDate);//日期
		map.put("groupId", groupId);// 人								
		try {
			String groupName="";
			List<Group> groupList = groupService.getGroupList();
			for(Group g: groupList) {
				if(groupId.equals(g.getId().toString())) {
					groupName=g.getGroupName();
					break;
				}
			}
			String fileName="分中心核损清单.xlsx";
			List<Map<String, Object>> pengdingGroup = damageTicketService.getGroupDamageMonthOrYearList(map);
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			//工作簿
			XSSFWorkbook wbk = new XSSFWorkbook();
			// Sheet页
			XSSFSheet sheet = wbk.createSheet("定损员");
			XSSFCellStyle style = wbk.createCellStyle();;
			style.setAlignment(HorizontalAlignment.CENTER);  
			//创建合并单元格  ---begin
			CellRangeAddress region = new CellRangeAddress(0, 0, 4, 15);// 下标从0开始 起始行号，终止行号， 起始列号，终止列号
			CellRangeAddress region2 = new CellRangeAddress(1, 1, 6, 9);// 起始行号，终止行号， 起始列号，终止列号
			   //在sheet里增加合并单元格  
			sheet.addMergedRegion(region);
			sheet.addMergedRegion(region2);
			XSSFRow row = sheet.createRow(0);
			XSSFCell cell = row.createCell(0);
			//第一列
    	    row = sheet.createRow(0);
    	    //单元格
    		cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue("部门");
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		String[] split = damageDate.split("-");
    		if("1".equals(param)) {
    			cell.setCellValue(split[0]+"年"+split[1]+"月——————月统计");
    		}else if("2".equals(param)) {
    			cell.setCellValue(split[0]+"年——————年统计");
    		}		    		
    		//第二列
    	    row = sheet.createRow(1);
    	    //单元格
    		cell = row.createCell(4);
    		cell.setCellStyle(style);
    		cell.setCellValue("件数");
    		cell = row.createCell(5);
    		cell.setCellStyle(style);
    		cell.setCellValue("金额");
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("低碳外修");
    		cell = row.createCell(10);
    		cell.setCellStyle(style);
    		cell.setCellValue("单均金额");
    		cell = row.createCell(11);
    		cell.setCellStyle(style);
    		cell.setCellValue("换件金额");
    		cell = row.createCell(12);
    		cell.setCellStyle(style);
    		cell.setCellValue("换件数量");
    		cell = row.createCell(13);
    		cell.setCellStyle(style);
    		cell.setCellValue("单均换件数量");
    		cell = row.createCell(14);
    		cell.setCellStyle(style);
    		cell.setCellValue("工时金额");
    		cell = row.createCell(15);
    		cell.setCellStyle(style);
    		cell.setCellValue("单均工时");   		
    		//第二列
    	    row = sheet.createRow(2);
    	    //单元格    	    
    	    cell = row.createCell(0);
    		cell.setCellStyle(style);
    		cell.setCellValue(groupName);
    		cell = row.createCell(6);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以下件数");
    		cell = row.createCell(7);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以下金额");
    		cell = row.createCell(8);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以上件数");
    		cell = row.createCell(9);
    		cell.setCellStyle(style);
    		cell.setCellValue("万元以上金额");
    		XSSFRow rows;
    		XSSFCell cells;
    		for (int i = 0; i < pengdingGroup.size(); i++) { 
    			// sheet数据行第
    			rows = sheet.createRow(i+3);
    			// 单元格
    			// 单元格里设置值从第三列开始
    			cells = rows.createCell(1);
    			if(null!=pengdingGroup.get(i).get("type")) {
    				cells.setCellValue(pengdingGroup.get(i).get("type").toString());
    			}else {
    				cells.setCellValue("");
    			}  			
    			cells = rows.createCell(2);
    			cells.setCellValue(pengdingGroup.get(i).get("repair_name").toString());
    			cells = rows.createCell(3);
    			cells.setCellValue(pengdingGroup.get(i).get("milo").toString());
    			cells = rows.createCell(4);
    			if(null!=pengdingGroup.get(i).get("allsize")) {
    				cells.setCellValue(pengdingGroup.get(i).get("allsize").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(5);
    			if(null!=pengdingGroup.get(i).get("allmoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("allmoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}
    			cells = rows.createCell(6);
    			if(null!=pengdingGroup.get(i).get("lownumber")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lownumber").toString());
    			}else {
    				cells.setCellValue(0);
    			}   			
    			cells = rows.createCell(7); 
    			if(null!=pengdingGroup.get(i).get("lowmoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lowmoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}  			
    			cells = rows.createCell(8);
    			if(null!=pengdingGroup.get(i).get("lownumbers")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lownumbers").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(9);
    			if(null!=pengdingGroup.get(i).get("lowmoneys")) {
    				cells.setCellValue(pengdingGroup.get(i).get("lowmoneys").toString());
    			}else {
    				cells.setCellValue(0);
    			}     			
    			cells = rows.createCell(10);
    			if(null!=pengdingGroup.get(i).get("eqmoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("eqmoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(11);
    			if(null!=pengdingGroup.get(i).get("money")) {
    				cells.setCellValue(pengdingGroup.get(i).get("money").toString());
    			}else {
    				cells.setCellValue(0);
    			}   			
    			cells = rows.createCell(12);
    			if(null!=pengdingGroup.get(i).get("size")) {
    				cells.setCellValue(pengdingGroup.get(i).get("size").toString());
    			}else {
    				cells.setCellValue(0);
    			}   			
    			cells = rows.createCell(13);
    			if(null!=pengdingGroup.get(i).get("eqsize")) {
    				cells.setCellValue(pengdingGroup.get(i).get("eqsize").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    			cells = rows.createCell(14);
    			if(null!=pengdingGroup.get(i).get("timemoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("timemoney").toString()); 
    			}else {
    				cells.setCellValue(0);
    			}    			  		
    			cells = rows.createCell(15);
    			if(null!=pengdingGroup.get(i).get("eqtimemoney")) {
    				cells.setCellValue(pengdingGroup.get(i).get("eqtimemoney").toString());
    			}else {
    				cells.setCellValue(0);
    			}    			
    		}
			try {
				OutputStream out = response.getOutputStream();
				wbk.write(out);
				out.close();
				String content="分中心核损";
			 oRdService.insertRecord(content, user, Constant.OPERATION_TYPE_KEY.EXPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *查重
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
}
