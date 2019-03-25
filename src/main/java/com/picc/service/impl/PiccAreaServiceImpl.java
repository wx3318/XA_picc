package com.picc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.picc.dao.GroupMapper;
import com.picc.dao.PiccAreaMapper;
import com.picc.entity.Group;
import com.picc.entity.PiccArea;
import com.picc.service.PiccAreaService;

/**
 * 区域 操作方法
 * 
 * @author TripodFan
 * @date 2018/10/23
 */
@Transactional(readOnly = true)
@Service
public class PiccAreaServiceImpl implements PiccAreaService {

	@Autowired
	private PiccAreaMapper arerMapper;
	
	@Autowired
	private GroupMapper groupMapper;
	
	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
	@Override
	public int deleteByPrimaryKey(Integer areaId) {
		// TODO Auto-generated method stub
		return arerMapper.deleteByPrimaryKey(areaId);
	}

	   /**
     *添加数据  会判断数据 是否为空为null
     * 
     * @param  record 数据
     * @return int 
     */
	@Transactional(readOnly = false)
	@Override
	public int insertSelective(PiccArea record) {
		// TODO Auto-generated method stub
		return arerMapper.insertSelective(record);
	}

	  /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return PiccArea 
     */
	@Override
	public PiccArea selectByPrimaryKey(Integer areaId) {
		// TODO Auto-generated method stub
		return arerMapper.selectByPrimaryKey(areaId);
	}

	   /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	@Transactional(readOnly = false)
	@Override
	public int updateByPrimaryKeySelective(PiccArea record) {
		// TODO Auto-generated method stub
		return arerMapper.updateByPrimaryKeySelective(record);
	}

	  /**
     * 文章列表 分页查询
     * 
     * @param record 
     * @return PageInfo
     * @throws Exception 格式转换等异常
     */
	@Override
	public PageInfo<Map<String, Object>> queryAreaListByPage(PiccArea area) {
		
		 // TODO Auto-generated method stub
		 List<Map<String, Object>> maplist = arerMapper.queryAreaList(area);
		 
		 // 处理数据
	        for(Map<String, Object> mapEntity : maplist){  
	        	
	        	String groupName = "";
	        	
	        	String[]  groupIds = mapEntity.get("groupId").toString().split(",");
	        	for (String groupId : groupIds) {
					Group group = groupMapper.selectByPrimaryKey(Integer.parseInt(groupId));
					groupName +=  group.getGroupName() + " ";
				}
	        	
	        	mapEntity.put("groupName", groupName);
	        }
		 PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maplist);
		    
		return pageInfo;
	}

    /**获取符合查询条件的区域主管列表
     * @param area
     * @return List区域主管列表
     */
	@Override
	public List<Map<String, Object>> queryAreaListByParam(PiccArea area) {
		// TODO Auto-generated method stub
		return arerMapper.queryAreaList(area);
	}

}
