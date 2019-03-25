package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.PiccArea;

/**
 * 区域 操作方法
 * 
 * @author TripodFan
 * @date 2018/10/23
 */
public interface PiccAreaMapper {

	/**
     *通过主键删除
     * 
     * @param areaId 主键id
     * @return int 返回删除数据的count
     */
    int deleteByPrimaryKey(Integer areaId);

    /**
     *添加数据
     * 
     * @param  record 
     * @return int 
     */
    int insert(PiccArea record);

    /**
     *添加数据  会判断数�?? 是否为空为null
     * 
     * @param  record 数据
     * @return int 
     */
    int insertSelective(PiccArea record);

    /**
     *通过主键查询
     * 
     * @param  areaId 主键id
     * @return PiccArea 
     */
    PiccArea selectByPrimaryKey(Integer areaId);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(PiccArea record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(PiccArea record);
    
    
    /**获取符合查询条件的区域列�?
     * @param area
     * @return 区域列表
     */
    List<Map<String, Object>> queryAreaList(PiccArea area);
}