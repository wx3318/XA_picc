package com.picc.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.OperationRecord;
import com.picc.entity.User;

/**
 *操作记录 方法
 * 
 * @author TripodFan
 * @date 2018/9/19
 */
public interface OperationRecordService {

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
     * @return OperationRecord 查询的数据 
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
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
    int insertSelective(OperationRecord record);
    
	/**
     * 操作记录 分页查询
     * 
     * @param record 
     * @return PageInfo
     * @throws Exception 格式转换等异常
     */
   public PageInfo<Map<String, Object>> queryRecordsByParam(OperationRecord record) throws Exception;
   
   
	/**
    *添加数据  会判断数据值是否为空为null
    * 
    * @param  content 功能名称
    * @param  user 操作用户
    * @return int 
    */
   int insertRecord(String content, User user, String type);
}
