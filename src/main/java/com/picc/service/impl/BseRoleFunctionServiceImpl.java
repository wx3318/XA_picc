package com.picc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.picc.dao.BseRoleFunctionMapper;
import com.picc.entity.BseRoleFunction;
import com.picc.entity.User;
import com.picc.service.BseRoleFunctionService;

/**
 * 角色功能关联方法实现
 * 
 * @author TripodFan
 * @date 2018/8/28
 */
@Service
public class BseRoleFunctionServiceImpl implements BseRoleFunctionService {
	
	@Autowired
	private BseRoleFunctionMapper functionMapper;

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
	public int insertSelective(BseRoleFunction record) {
		// TODO Auto-generated method stub
		return functionMapper.insertSelective(record);
	}

	   /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BseRoleFunction 查询的数据 
     */
	@Override
	public BseRoleFunction selectByPrimaryKey(Integer id) {
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
	public int updateByPrimaryKeySelective(BseRoleFunction record) {
		// TODO Auto-generated method stub
		return functionMapper.updateByPrimaryKeySelective(record);
	}

	  /**
     * 修改角色所拥有的系统功能
     * 
     * @param requestMap 
     * @return int
     */
	@Transactional
	@Override
	public int updateFunctionByRoleId(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		User user = (User) requestMap.get("user");
		String roleId = requestMap.get("roleId").toString();
		//每次修改之前，先把该角色之前的功能清空
		functionMapper.deleteByRoleId(Integer.parseInt(roleId));
		//遍历获得的功能ID
		List<BseRoleFunction> roleFunctionList = new ArrayList<>();
		String classpurview = requestMap.get("classpurview").toString();
		String[] functionList = classpurview.split(",");
		
		Date createdDate = new Date();
		for (int i = 0; i < functionList.length; i++) {
			
			BseRoleFunction bseRoleFunction = new BseRoleFunction();
			int functionId = Integer.parseInt(functionList[i]);
			bseRoleFunction.setRoleId(Integer.parseInt(roleId));
			bseRoleFunction.setFunctionId(functionId);
			bseRoleFunction.setCreatedDate(createdDate);
			bseRoleFunction.setCreatedId(user.getUser_id());
			bseRoleFunction.setCreatedName(user.getName());
			
			roleFunctionList.add(bseRoleFunction);
			
		}
		
		int result = functionMapper.insertRoleFunctionList(roleFunctionList);
		
		return result;
	}

	/**
     * 通过角色ID批量查询
     * 
     * @param roleId 
     * @return roleFunctionList
     */
	@Override
	public List<BseRoleFunction> selectByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return functionMapper.selectByRoleId(roleId);
	}

}
