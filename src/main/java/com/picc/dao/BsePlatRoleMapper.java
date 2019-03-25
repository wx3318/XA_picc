package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.BsePlatRole;

/**
 * 角色功能
 * 
 * @author TripodFan
 * @date 2018/8/24
 */
public interface BsePlatRoleMapper {
	
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
    int insert(BsePlatRole record);

    /**
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
    int insertSelective(BsePlatRole record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BsePlatRole 查询的数据 
     */
    BsePlatRole selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(BsePlatRole record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(BsePlatRole record);
    
    /**获取角色列表
     * @param role  请求参数
     * @return List 角色列表
     */
    List<Map<String, Object>> queryRoleList(BsePlatRole role);
}