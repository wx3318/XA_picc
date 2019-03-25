package com.picc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.GroupCase;

/**
 * 月任务设置接口（未决案件）
 * @author wangXi
 * @date 2018/12/18
 * 
 */
public interface GroupCaseService {
	/**
	 * 月任务设置（机构）
	 * @param groupCase
	 * @return
	 */
	public int saveGroupCase(GroupCase groupCase);
	/**
	 * 参数获取任务集合
	 * @param groupCase
	 * @return
	 */
	public List<Map<String,Object>> getGroupCaseList(GroupCase groupCase);
	/**
	 * 分页
	 * @param groupCase
	 * @return
	 */
	public PageInfo<Map<String,Object>> getPageGroupCaseList(GroupCase groupCase);
	
	/**
	 * 车险未决日报表
	 * @param groupCase
	 * @return
	 */
	public List<Map<String,Object>> getCaseMonthList(GroupCase groupCase);
	
	/**
	 * 通过id查询
	 * @param groupCase
	 * @return
	 */
	public GroupCase getGroupCaseById(GroupCase groupCase);
	/**
	 * 任务修改
	 * @param groupCase
	 * @return
	 */
	public int updateGroupCase(GroupCase groupCase);
	/**
	 * 通过Id删除
	 * @param groupCase
	 * @return
	 */
	public int deletGroupCase(GroupCase groupCase);
}
