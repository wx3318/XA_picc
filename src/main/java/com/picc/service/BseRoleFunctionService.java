package com.picc.service;

import java.util.List;
import java.util.Map;

import com.picc.entity.BseRoleFunction;


/**
 * 角色功能关联表
 * 
 * @author TripodFan
 * @date 2018/8/28
 */
public interface BseRoleFunctionService {

		/**
	     *通过主键删除
	     * 
	     * @param id 主键id
	     * @return int 返回删除数据的count
	     */
	    public int deleteByPrimaryKey(Integer id);

	    /**
	     *添加数据  会判断数据值是否为空为null
	     * 
	     * @param  record 数据值
	     * @return int 
	     */
	    public int insertSelective(BseRoleFunction record);

	    /**
	     *通过主键查询
	     * 
	     * @param  id 主键id
	     * @return BseRoleFunction 查询的数据 
	     */
	    public BseRoleFunction selectByPrimaryKey(Integer id);

		 /**
	     *通过主键修改 会判断数据是否为空为null
	     * 
	     * @param  record 要修改的数据
	     * @return int 修改结果
	     */
	    public int updateByPrimaryKeySelective(BseRoleFunction record);

	    /**
	     * 修改角色所拥有的系统功能
	     * 
	     * @param requestMap 
	     * @return int
	     */
	    public int updateFunctionByRoleId(Map<String, Object> requestMap);
	    
	    
	    /**
	     * 通过角色ID批量查询
	     * 
	     * @param roleId 
	     * @return roleFunctionList
	     */
	    public List<BseRoleFunction> selectByRoleId(Integer roleId); 
}
