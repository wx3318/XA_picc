package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.Group;

/**
 * 区域 操作方法
 * 
 * @author Wang xi
 * @author TripodFan
 * @date 2018/10/24
 */
public interface GroupMapper {
	/**
	 * 组别获取
	 * @return
	 */
	List<Group> getGroupList();
	/**
	 * 新增组别
	 * @param group
	 * @return
	 */
	int saveGroup(Group group);
	 /**
	  * 修改组别
	  * @param group
	  * @return
	  */
	int updateGroupByKey(Group group);
	
	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *添加数据
     * 
     * @param  record 
     * @return int 
     */
    int insert(Group record);


    /**
     *添加数据  会判断数 是否为空为null
     * 
     * @param  record 数据
     * @return int 
     */
    int insertSelective(Group record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return PiccArea 
     */
    Group selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(Group record);
    
    /**获取符合查询条件的组别
     * @param group
     * @return 组别列表
     */
    List<Map<String, Object>> queryGroupList(Group group);
}
