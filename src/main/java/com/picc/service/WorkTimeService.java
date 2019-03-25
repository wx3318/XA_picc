package com.picc.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.WorkTime;

public interface WorkTimeService {
	/**
	 * 分页打卡详情
	 * @param workTime
	 * @return
	 */
	public PageInfo<Map<String, Object>> getWorkTimeByWorkTime(WorkTime workTime) throws Exception;
	/**
	 * 导出
	 * @param workTime
	 * @return
	 */
	public List<Map<String, Object>>  getWorkTimeListByWorkTime(WorkTime workTime) throws Exception;
	/**
	 * id查详情
	 * @param id
	 * @return
	 */
	public WorkTime getWorkTimeByKey(Integer id);
	/**
	 * 更新打卡
	 * @param workTime
	 * @return
	 */
	public int updateWorkTime(WorkTime workTime);
	
	/**
	 * 月累计考勤
	 * @param workTime
	 * @return
	 */
	public	List<Map<String,Object>> getWorkTimeMonth(WorkTime workTime) throws Exception;
	
	/**
	 * 分页月累计打卡详情
	 * @param workTime
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Map<String, Object>> getPageWorkTimeMonth(WorkTime workTime) throws Exception;
	
	/**
	 * 天统计按组
	 * @param workTime
	 * @return
	 */
	public	List<Map<String,Object>> getWorkTimeDay(WorkTime workTime) throws Exception;
	
	/**
	 * 分页天统计
	 * @param workTime
	 * @return
	 * @throws Exception
	 */
	public PageInfo<Map<String, Object>> getPageWorkTimeDay(WorkTime workTime) throws Exception;
	/**
	 * 统计
	 * @param workTime
	 * @return
	 */
	public Map<String,Object> getCountPerson(WorkTime workTime);
	/**
	 * 自营
	 * @param workTime
	 * @return
	 */
	public Map<String,Object> getCountGroupOne(WorkTime workTime);
	
	/**
	 * 续转保
	 * @param workTime
	 * @return
	 */
	public Map<String,Object> getCountGroupTwo(WorkTime workTime);
	
	/**
	 * 员工分组时
	 * @param workTime
	 * @return
	 */
	public int updateWorkTimeGroup(WorkTime workTime);
	
	
	
	/**
	 * 缺勤的日期
	 * @param workTime
	 * @return
	 */
	public	String getLevaeDate(WorkTime workTime);
	/**
	 * 迟到的日期	
	 * @param workTime
	 * @return
	 */
	public	String getLastDate(WorkTime workTime);
	/**
	 * 上班未打卡的日期	
	 * @param workTime
	 * @return
	 */
	public	String getStartDate(WorkTime workTime);
	/**
	 * 下班未打卡的日期	
	 * @param workTime
	 * @return
	 */
	public	String getEndDate(WorkTime workTime);
	/**
	 * 请假的日期	
	 * @param workTime
	 * @return
	 */
	public	String getAskDate(WorkTime workTime);
	
	/**
	 * 手动更新打卡全天
	 * @param addredd
	 * @param monthday
	 */
	public void updateWorkTimeHeadDay(String addredd,String monthday) throws Exception ;
	/**
	 * 手动更新晚上打卡
	 * @param addredd
	 * @param monthday
	 * @throws Exception
	 */
	public void updateWorkTimeHeadEndTime(String addredd,String month,String station) throws Exception ;
	
}
