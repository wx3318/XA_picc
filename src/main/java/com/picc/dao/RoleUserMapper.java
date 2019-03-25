package com.picc.dao;

import com.picc.entity.RoleUser;
/**
 * 
 * @author wangXi
 *
 */
public interface RoleUserMapper {
	
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
    int insert(RoleUser record);

    /**
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
    int insertSelective(RoleUser record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return RoleUser 查询的数据 
     */
    RoleUser selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(RoleUser record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(RoleUser record);
}