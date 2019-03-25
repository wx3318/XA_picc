package com.picc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.common.Constant;
import com.picc.dao.OperationRecordMapper;
import com.picc.entity.OperationRecord;
import com.picc.entity.User;
import com.picc.service.OperationRecordService;

/**
 *操作记录 方法实现
 * 
 * @author TripodFan
 * @date 2018/9/19
 */
@Service
public class OperationRecordServiceImpl implements OperationRecordService {

	@Autowired
	private OperationRecordMapper recordMapper;
	
	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return recordMapper.deleteByPrimaryKey(id);
	}

	/**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return OperationRecord 查询的数据 
     */
	@Override
	public OperationRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return recordMapper.selectByPrimaryKey(id);
	}

	 /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	@Override
	public int updateByPrimaryKeySelective(OperationRecord record) {
		// TODO Auto-generated method stub
		return recordMapper.updateByPrimaryKeySelective(record);
	}

	/**
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
	@Override
	public int insertSelective(OperationRecord record) {
		// TODO Auto-generated method stub
		return recordMapper.insertSelective(record);
	}

	/**
     * 操作记录 分页查询
     * 
     * @param record 
     * @return PageInfo
     * @throws Exception 格式转换等异常
     */
	@Override
	public PageInfo<Map<String, Object>> queryRecordsByParam(OperationRecord record) throws Exception {

	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			 PageHelper.startPage(record.getPageNum(),record.getLength());

		     List<Map<String, Object>> maplist = recordMapper.queryRecordsList(record);
		     
		  // 处理数据
		        for(Map<String, Object> mapEntity : maplist){ 
		        	  // 格式化日期
		            mapEntity.put("createdDateName", sdf.format(sdf.parse(String.valueOf(mapEntity.get("createdDate")))));
		            String  type = String.valueOf(mapEntity.get("type"));
		            mapEntity.put("typeName", Constant.OPERATION_TYPE.get(type));
		        }
		        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maplist);

				return pageInfo;
	}

	/**
	    *添加数据  会判断数据值是否为空为null
	    * 
	    * @param  content 功能名称
	    * @param  user 操作用户
	    * @return int 
	    */
	@Override
	public int insertRecord(String content, User user, String type) {
		
		OperationRecord addRecord = new OperationRecord();
		addRecord.setContent(content);
		addRecord.setType(type);
		addRecord.setCreatedDate(new Date());
		addRecord.setCreatedId(user.getUser_id());
		addRecord.setCreatedName(user.getName());
		
		return recordMapper.insertSelective(addRecord);
	}

}
