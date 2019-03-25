package com.picc.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.picc.entity.User;
import com.picc.service.OperationRecordService;
import com.picc.service.UserService;
import com.picc.util.ImportExcelUtil;
/**
 * 
 * @author 王希
 * @date 2018/8/23
 */
@Controller
@RequestMapping("/upload")
public class UploadExcelController {

	@Autowired
	private UserService userservice;

	
	@Autowired
	private OperationRecordService recordServiceImpl;

	
	@ResponseBody
	@RequestMapping(value = "/userexcel.ajax", method = { RequestMethod.GET, RequestMethod.POST })
	public void userUploadExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws Exception {
		InputStream in = null;
		List<List<Object>> listob = null;
		if (file.isEmpty()) {
			throw new Exception("工作簿为空");
		}
		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<User> listUser = new ArrayList<User>();
		for (int i = 0; i < listob.size(); i++) {
			List<Object> lo = listob.get(i);
			User tk = new User();
			if (lo.size() <= 0) {
				break;
			}
			if (lo.get(0).equals("") && lo.get(1).equals("")) {
				break;
			}
				
			try {				
				tk.setWork_mes("在职");
				tk.setPassword("123456");
				tk.setSex("");
				tk.setId_card("");
				tk.setPhone(null);
				tk.setJion_work(null);
				tk.setStart_work(null);
				tk.setGroupDate(null);			
				tk.setStation("");
				tk.setContract("");				
				tk.setYx_id(null);
				tk.setPhone_show(null);
				tk.setEducation(null);
				tk.setJ_phone(null);
				tk.setAddress(null);
				tk.setEnd_work(null);
				tk.setMessage("");	
				tk.setName(String.valueOf(lo.get(2)));	
				tk.setGroupId(Integer.parseInt(String.valueOf(lo.get(4))));
				tk.setUsername(String.valueOf(lo.get(3)));
				tk.setRoleId(Integer.parseInt(String.valueOf(lo.get(1))));
						
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(tk);
			listUser.add(tk);		
		}
		userservice.importUser(listUser);
		PrintWriter out = null;
		//ajax中文乱码处理
		response.setCharacterEncoding("utf-8"); 
		out = response.getWriter();
		out.print("导入成功"+listUser.size());
		out.flush();
		out.close();
	}
	/**
	 * 话务报表导入
	 * @param file
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/talkimprot", method = { RequestMethod.GET, RequestMethod.POST })
	public void talkImportExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws Exception {}
	
}
