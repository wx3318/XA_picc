package com.picc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.common.Constant;
import com.picc.dao.BsePlatFunctionMapper;
import com.picc.entity.BsePlatFunction;
import com.picc.service.BsePlatFunctionService;

/**
 * 系统功能
 * 
 * @author 范岩
 * @date 2018/08/23
 */
@Service
public class BsePlatFunctionServiceImpl implements BsePlatFunctionService {

	@Autowired
	private BsePlatFunctionMapper functionMapper;
	
	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return functionMapper.deleteByPrimaryKey(id);
	}

	  /**
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
	@Override
	public int insertSelective(BsePlatFunction record) {
		// TODO Auto-generated method stub
		return functionMapper.insertSelective(record);
	}

	  /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BsePlatFunction 查询的数据 
     */
	@Override
	public BsePlatFunction selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return functionMapper.selectByPrimaryKey(id);
	}

	  /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	@Override
	public int updateByPrimaryKeySelective(BsePlatFunction record) {
		// TODO Auto-generated method stub
		return functionMapper.updateByPrimaryKeySelective(record);
	}

	 /**
     * 功能列表 分页查询
     * 
     * @param function 
     * @return PageInfo
     */
	@Override
	public PageInfo<Map<String, Object>> queryFunctionList(BsePlatFunction function) throws Exception {
		   // TODO Auto-generated method stub
		     PageHelper.startPage(function.getPageNum(),function.getLength());
		 
		    List<Map<String, Object>> maplist = functionMapper.queryFunctionList(function);
		    
		    // 处理数据
	        for(Map<String, Object> mapEntity : maplist){ 
	            String funTypeName =  Constant.FUN_TYPE.get(String.valueOf(mapEntity.get("funType"))).toString();
	            mapEntity.put("funType", funTypeName);
	        }
	        
		    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maplist);
		    
			return pageInfo;
	}

    /**
     * 功能列表 全部查询
     * 
     * @param goods 
     * @return functionList
     */
	@Override
	public List<Map<String, Object>> queryAllFunction(BsePlatFunction function) throws Exception {
		// TODO Auto-generated method stub
		 List<Map<String, Object>> functionList = functionMapper.queryFunctionList(function);
		 
		return functionList;
	}
	
	 /**
     * @param roleId
     * @return 功能列表
     * @desc 获取功能列表
     */
	@Override
	public List<Map<String, Object>> queryFunctionByRoleId(Integer roleId) throws Exception {
		// TODO Auto-generated method stub
		return functionMapper.queryFunctionByRoleId(roleId);
	}


}
