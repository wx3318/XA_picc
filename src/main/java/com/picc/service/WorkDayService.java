package com.picc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.WorkDay;
import com.picc.entity.WorkTime;

public interface WorkDayService {
	
	/**
	 * 设置上班天数
	 * @param workDay
	 * @return
	 */
	public int insertWorkDay(WorkDay workDay);
	/**
	 * 设置记录
	 * @param workDay
	 * @return
	 */
	public List<Map<String,Object>> getWorkDayList(WorkDay workDay);
	
	/**
	 * 分页
	 * @param workDay
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Map<String, Object>> getPageWorkDayList(WorkDay workDay) throws Exception;
	
	
	 /**
	  * 
	  * @param workDay
	  * @return
	  */
	public String getWorkDayMonth(String month);
	
	
	
	public String getWorkDayMonthLast(String month);
}
