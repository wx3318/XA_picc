package com.picc.controller;
/**
 * 定损控制层
 * @author wangXi	
 * @date 2019/02/25
 *
 */


import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.picc.entity.Damage;
import com.picc.entity.DamageLow;
import com.picc.entity.User;
import com.picc.service.DamageLowService;
import com.picc.service.DamageService;
import com.picc.service.DamageTicketService;
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
	
	/**
	 * 定损导入页面
	 * @param request
	 * @param response
	 * @return 页面
	 */
	//@RequiresPermissions("picc.damage.import")
	@RequestMapping(value="/import.html")
	public ModelAndView importPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model  = new ModelAndView();
		//页面设置
		model.setViewName("damage/import");
		return model;
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
			if("1".equals(String.valueOf(lo.get(11)))) {
				tk.setDamageId(String.valueOf(lo.get(0)).trim());
				tk.setReportNumber(String.valueOf(lo.get(1)).trim());
				tk.setUnderwritingCode(String.valueOf(lo.get(2)).trim());
				tk.setPlateNumber(String.valueOf(lo.get(3)).trim());
				tk.setDamageCode(String.valueOf(lo.get(4)).trim());
				tk.setDamageName(String.valueOf(lo.get(5)).trim());
				tk.setDamageMoney(String.valueOf(lo.get(6)).trim());
				tk.setDamageNuclear(String.valueOf(lo.get(7)).trim());
				tk.setDamageNumberMoney(String.valueOf(lo.get(8)).trim());
				tk.setRepairLowCode(String.valueOf(lo.get(9)).trim());
				tk.setRepairLowName(String.valueOf(lo.get(10)).trim());
				tk.setIsLow(String.valueOf(lo.get(11)).trim());
				tk.setDamageLoseMoney(String.valueOf(lo.get(12)).trim());
				tk.setDamageLowMoney(String.valueOf(lo.get(13)).trim());
				tk.setDamageLowNumber(String.valueOf(lo.get(14)).trim());
				if(null!=String.valueOf(lo.get(15)) && !"".equals(String.valueOf(lo.get(15)))) {
					if(null!=String.valueOf(lo.get(16)) && !"".equals(String.valueOf(lo.get(16)))) {
						String dateStr= String.valueOf(lo.get(15))+" "+String.valueOf(lo.get(16));
						tk.setDamageLowDate(sdf.parse(dateStr));		
					}else {
						String dateStr = String.valueOf(lo.get(15))+" 00:00:00";
						tk.setDamageLowDate(sdf.parse(dateStr));
					}
				}else {
					tk.setDamageLowDate(new Date());
				}
				//第 5 位 7、9是修理厂    repairLeve 1=4s店  其他=修理厂
				char charRepair = tk.getRepairLowCode().charAt(4);
				String repairLeve = String.valueOf(charRepair);
				if("7".equals(repairLeve) || "9".equals(repairLeve)) {
					tk.setRepairLeve("2");
				}else {
					tk.setRepairLeve("1");
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
	}
}
