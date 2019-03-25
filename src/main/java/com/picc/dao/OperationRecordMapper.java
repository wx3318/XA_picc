package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.OperationRecord;

/**
 *操作记录
 * 
 * @author TripodFan
 * @date 2018/9/19
 */
public interface OperationRecordMapper {
	
	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return OperationRecord 查询的数�? 
     */
	OperationRecord selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	int updateByPrimaryKeySelective(OperationRecord record);

    /**
     *通过主键修改
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	int updateByPrimaryKey(OperationRecord record);

	  /**
     *添加数据
     * 
     * @param  record 数据
     * @return int 
     */
	int insert(OperationRecord record);

	/**
     *添加数据  会判断数据是否为空为null
     * 
     * @param  record 数据
     * @return int 
     */
    int insertSelective(OperationRecord record);
    
    /**获取符合查询条件的操作记录
     * @param record 请求参数
     * @return 操作记录列表
     */
    List<Map<String, Object>> queryRecordsList(OperationRecord record);
}