package com.picc.service;

import java.util.List;
import java.util.Map;

import com.picc.entity.Group;

/**
 * 区域 操作方法
 * @author Wang xi
 * @author TripodFan
 * @date 2018/10/23
 */
public interface GroupService {
	public List<Group> getGroupList();
	
	public int saveGroup(Group group);
	
	public int updateGroupByKey(Group group);
	
    /**获取符合查询
     * @param group
     * @return 组别列表
     */
	public List<Map<String, Object>> queryGroupList(Group group);
}
