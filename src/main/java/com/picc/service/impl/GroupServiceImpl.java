package com.picc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picc.dao.GroupMapper;

import com.picc.entity.Group;
import com.picc.service.GroupService;

/**
 * 组别操作方法实现
 * @author wx
 * @author TripodFan
 */
@Transactional(readOnly = true, rollbackFor=Exception.class)
@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired
	private GroupMapper groupMapper;
	
	@Override
	public List<Group> getGroupList() {
		// TODO Auto-generated method stub
		return groupMapper.getGroupList();
	}

	@Transactional(readOnly = false, rollbackFor=Exception.class)
	@Override
	public int saveGroup(Group group) {
		// TODO Auto-generated method stub
		int saveGroup = groupMapper.saveGroup(group);
		if(saveGroup>0) {
			return saveGroup;
		}
		return 0;
	}

	@Transactional(readOnly = false, rollbackFor=Exception.class)
	@Override
	public int updateGroupByKey(Group group) {
		// TODO Auto-generated method stub
		return groupMapper.updateGroupByKey(group);
	}

    /**获取符合查询条件的组别
     * @param group
     * @return 组别列表
     */
	@Override
	public List<Map<String, Object>> queryGroupList(Group group) {
		// TODO Auto-generated method stub
		return groupMapper.queryGroupList(group);
	}

}
