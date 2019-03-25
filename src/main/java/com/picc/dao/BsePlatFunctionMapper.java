package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.BsePlatFunction;

/**
 * 系统功能
 * 
 * @author TripodFan
 * @date 2018/8/23
 */
public interface BsePlatFunctionMapper {
	
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
     * @param  record 数据�?
     * @return int 
     */
    int insert(BsePlatFunction record);

    /**
     *添加数据  会判断数据�?�是否为空为null
     * 
     * @param  record 数据�?
     * @return int 
     */
    int insertSelective(BsePlatFunction record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BsePlatFunction 查询的数�? 
     */
    BsePlatFunction selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(BsePlatFunction record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(BsePlatFunction record);
    
    /**获取功能列表
     * @param function 请求参数
     * @return 功能列表
     */
    List<Map<String, Object>> queryFunctionList(BsePlatFunction function);
    
    
    /**获取功能列表 通过角色ID
     * @param roleId 角色ID
     * @return 功能列表
     */
    List<Map<String, Object>> queryFunctionByRoleId(Integer roleId);
}

