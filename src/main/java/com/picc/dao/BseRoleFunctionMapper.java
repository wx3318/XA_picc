package com.picc.dao;

import java.util.List;

import com.picc.entity.BseRoleFunction;


/**
 * 角色功能关联表
 * 
 * @author TripodFan
 * @date 2018/8/28
 */
public interface BseRoleFunctionMapper {
	
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
     * @param  record 数据值
     * @return int 
     */
    int insert(BseRoleFunction record);

    /**
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
    int insertSelective(BseRoleFunction record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BseRoleFunction 查询的数据 
     */
    BseRoleFunction selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(BseRoleFunction record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(BseRoleFunction record);
    
	  /**
     * 通过角色ID删除
     * 
     * @param roleId 
     * @return int
     */
    int deleteByRoleId(Integer roleId);
    
    /**
     * 批量新增
     * 
     * @param roleFunctionList 
     * @return int
     */
    int insertRoleFunctionList(List<BseRoleFunction> roleFunctionList);
    
    /**
     * 通过角色ID批量查询
     * 
     * @param roleId 
     * @return roleFunctionList
     */
    List<BseRoleFunction> selectByRoleId(Integer roleId); 
}