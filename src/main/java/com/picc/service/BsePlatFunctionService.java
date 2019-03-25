package com.picc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.BsePlatFunction;

/**
 * 系统功能
 * 
 * @author 范岩
 * @date 2018/08/23
 */
public interface BsePlatFunctionService {

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
    public int insertSelective(BsePlatFunction record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BsePlatFunction 查询的数据 
     */
    public BsePlatFunction selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    public int updateByPrimaryKeySelective(BsePlatFunction record);

    /**
     * 功能列表 全部查询
     * 
     * @param goods 
     * @return List<Map<String, Object>>
     * @throws Exception 格式转换等异常
     */
    public List<Map<String, Object>> queryAllFunction(BsePlatFunction function) throws Exception;
    
    /**
     * 功能列表 分页查询
     * 
     * @param function 
     * @return PageInfo
     * @throws Exception 格式转换等异常
     */
    public PageInfo<Map<String, Object>> queryFunctionList(BsePlatFunction function) throws Exception;
    
    /**
     * 获取功能列表
     * 
     * @param roleId  角色ID
     * @return List<Map<String, Object>>
     * @throws Exception 格式转换等异常
     */
    public List<Map<String, Object>> queryFunctionByRoleId(Integer roleId) throws Exception;
}
