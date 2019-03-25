package com.picc.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.picc.common.BasicUtil;
import com.picc.common.ResultObject;
import com.picc.entity.OperationRecord;
import com.picc.service.OperationRecordService;

/**
 * 操作记录
 * 
 * @author TripodFan
 * @date 2018/09/19
 */
@Controller
@RequestMapping("/picc/operationRecord")
public class OperationRecordController {

	private final static Logger logger = Logger.getLogger(OperationRecordController.class);
	
	@Autowired
	private OperationRecordService recordService;
	
	/**
	 * 
	 * 操作记录列表
	 */
	@RequiresPermissions("picc.operation.record.list")
	@RequestMapping(value = "/list.html")
	public ModelAndView goodsAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView view = new ModelAndView();
		// response.setCharacterEncoding("UTF-8");
		view.setViewName("operation/record_list");

		return view;
	}
	
	/**
	 * 分页查询 操作记录
	 *
	 * @return 操作记录列表信息
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryAll.ajax", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResultObject queryAll(HttpServletRequest request, HttpServletResponse response, OperationRecord record,
			HttpSession session) throws IOException { 
		
		// 返回对象
		ResultObject resultObject = new ResultObject();
		
		try {
			Map<String, Object> requestMap = BasicUtil.requestParamExtract(request);
			
			// 业务请求
			PageInfo<Map<String, Object>> pageInfo = recordService.queryRecordsByParam(record);
			
			// 返回对象处理
			resultObject.setData(pageInfo.getList());
			resultObject.setRecordsTotal((int) pageInfo.getTotal());
			resultObject.setRecordsFiltered((int) pageInfo.getTotal());
			resultObject.setDraw(record.getDraw());
			resultObject.setSuccess(true);
			resultObject.setMsg("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.setSuccess(false);
			resultObject.setMsg("查询操作记录出现异常");
			logger.error("查询操作记录出现异常" + e.getMessage());
		}
		
		return resultObject;
	}

}
