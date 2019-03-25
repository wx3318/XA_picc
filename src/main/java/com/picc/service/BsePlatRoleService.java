package com.picc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.BsePlatRole;

/**
 * 角色功能
 * 
 * @author 范岩
 * @date 2018/08/24
 */
public interface BsePlatRoleService {

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
    public int insertSelective(BsePlatRole record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BsePlatRole 查询的数据 
     */
    public BsePlatRole selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    public int updateByPrimaryKeySelective(BsePlatRole record);

    
    /**
     * 角色列表 分页查询
     * @param role 角色参数
     * @return 角色列表
     * @throws Exception
     */
    public PageInfo<Map<String, Object>> queryRoleList(BsePlatRole role) throws Exception;
    
    /**
     * 角色列表 按条件全部查询
     * @param role 角色参数
     * @return 角色列表
     * @throws Exception
     */
    public List<Map<String, Object>> getRoleList(BsePlatRole role) throws Exception;
}
