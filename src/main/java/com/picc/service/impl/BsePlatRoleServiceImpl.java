package com.picc.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.common.Constant;
import com.picc.dao.BsePlatRoleMapper;
import com.picc.entity.BsePlatRole;
import com.picc.service.BsePlatRoleService;

/**
 * 角色功能方法实现
 * 
 * @author Tripod Fan
 * @date 2018/08/24
 */
@Service
public class BsePlatRoleServiceImpl implements BsePlatRoleService {
	
	@Autowired
	private BsePlatRoleMapper roleMapper;

	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.deleteByPrimaryKey(id);
	}

	/**
     *添加数据  会判断数据值是否为空为null
     * 
     * @param  record 数据值
     * @return int 
     */
	@Override
	public int insertSelective(BsePlatRole record) {
		// TODO Auto-generated method stub
		return roleMapper.insertSelective(record);
	}

	 /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	@Override
	public int updateByPrimaryKeySelective(BsePlatRole record) {
		// TODO Auto-generated method stub
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	 /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return BsePlatRole 查询的数据 
     */
	@Override
	public BsePlatRole selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.selectByPrimaryKey(id);
	}

    /**
     * 角色列表 分页查询
     * @param role 角色参数
     * @return 角色列表
     * @throws Exception
     */
	@Override
	public PageInfo<Map<String, Object>> queryRoleList(BsePlatRole role) throws Exception {
		// TODO Auto-generated method stub
	     PageHelper.startPage(role.getPageNum(),role.getLength());
	     
	     List<Map<String, Object>> maplist = roleMapper.queryRoleList(role);
	     
	     SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    // 处理数据
	        for(Map<String, Object> mapEntity : maplist){ 
	        	  // 格式化日期
	        	if(mapEntity.get("created_date")!=null) {
	        		mapEntity.put("created_date", sdt.format(sdt.parse(String.valueOf(mapEntity.get("created_date")))));
	        	}else {
	        		mapEntity.put("created_date","");
	        	}
	            
	        }
	        
	        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maplist);
	        
		return pageInfo;
	}

	/**
     * 角色列表 按条件全部查询
     * @param role 角色参数
     * @return 角色列表
     * @throws Exception
     */
	@Override
	public List<Map<String, Object>> getRoleList(BsePlatRole role) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>> maplist = roleMapper.queryRoleList(role);
		return maplist;
	}

}
