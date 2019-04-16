package com.picc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.picc.entity.User;

public interface UserService {
		//登录
		public User doLogin(User user) throws Exception;
		//通过id或得用户各别信息
		public User getUser(int id) throws Exception;
		//查询组
		public String getGroup(int groupid) throws Exception;
		//保存用户组别
		public boolean savePassword(User user )throws Exception;
		//保存密码
		public boolean saveUser(User user) throws NumberFormatException, Exception;
		//新建用户
		public User getUserByuname(int username) throws Exception;
		//根据营销工号查
		public void saveGroup(int group_id,int username) throws Exception;
		//保存用户角色
		public boolean saveRole_user(int user_id,int role_id) throws Exception;
		//用户数量
		public int userCount(String group,String username,String name,String work_mes);
		//更新用户信息
		public int updateUser(User user) throws Exception;
		//营销工号得用户
		public User getUserByYxid(String yx_Id) ;
		/**
		 * 查重idCard
		 * @param idCard
		 * @return
		 */
		public User getUserByIdCard(@Param("idCard")String idCard);
		
		public int importUser(List<User> listUser);
		
		
		public List<Map<String,Object>> getUserListMap(User user) throws Exception;
		
		public PageInfo<Map<String, Object>> getUserListPageMap(User user) throws Exception;
		 
	    /**获取符合查询条件的坐席列表
	     * @param user
	     * @return 坐席列表
	     */
		public List<Map<String, Object>> queryUserList(Map<String, Object> param) throws Exception;
		public List<User> getUserListByGroupId(Integer groupId);
}
