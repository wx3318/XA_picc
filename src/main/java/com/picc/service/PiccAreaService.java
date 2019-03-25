package com.picc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.PiccArea;

/**
 * 区域 操作方法
 * 
 * @author TripodFan
 * @date 2018/10/23
 */
public interface PiccAreaService {

	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
    public int deleteByPrimaryKey(Integer areaId);

    /**
     *添加数据  会判断数据 是否为空为null
     * 
     * @param  record 数据
     * @return int 
     */
    public int insertSelective(PiccArea record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return PiccArea 
     */
    public PiccArea selectByPrimaryKey(Integer areaId);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    public int updateByPrimaryKeySelective(PiccArea record);
    
    /**获取符合查询条件的区域列表
     * @param area
     * @return PageInfo 区域列表
     */
    public PageInfo<Map<String, Object>> queryAreaListByPage(PiccArea area);

    /**获取符合查询条件的区域主管列表
     * @param area
     * @return List区域主管列表
     */
    public List<Map<String, Object>> queryAreaListByParam(PiccArea area);
}
