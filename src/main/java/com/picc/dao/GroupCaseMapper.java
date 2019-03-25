package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.GroupCase;

/**
 * 月任务设置（未决案件）
 * @author wangXi
 * @date 2018/12/18
 * 
 */
public interface GroupCaseMapper {
	/**
	 * 月任务设置（机构）
	 * @param groupCase
	 * @return
	 */
	int saveGroupCase(GroupCase groupCase);
	/**
	 * 参数获取任务集合
	 * @param groupCase
	 * @return
	 */
	List<Map<String,Object>> getGroupCaseList(GroupCase groupCase);
	/**
	 * 车险未决日报表
	 * @param groupCase
	 * @return
	 */
	List<Map<String,Object>> getCaseMonthList(GroupCase groupCase);
	/**
	 * 通过id查询
	 * @param groupCase
	 * @return
	 */
	GroupCase getGroupCaseById(GroupCase groupCase);
	/**
	 * 任务修改
	 * @param groupCase
	 * @return
	 */
	int updateGroupCase(GroupCase groupCase);
	/**
	 * 通过Id删除
	 * @param groupCase
	 * @return
	 */
	int deletGroupCase(GroupCase groupCase);
}
