package com.picc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.User;

public interface UserMapper {
	
	//登录
	public User doLogin(User user) throws Exception;
	//通过id或得用户各别信息
	public User getUser(int id) throws Exception;
	//查询组
	public String getGroup(int groupid) throws Exception;
	//保存用户组别
	public void saveGroup(@Param("group_id") int group_id,@Param("username") int username) throws Exception;
	//保存密码
	public Integer savePassword(User user)throws Exception;
	//新建用户
	public Integer saveUser(User user) throws Exception;
	//根据营销工号查
	public User getUserByuname(int username) throws Exception;
	//保存用户角色
	public Integer saveRole_user(@Param("user_id") int user_id,@Param("role_id") int role_id) throws Exception;
	//用户数量
	public int userCount(@Param("group_id")int group_id,@Param("username")int username,
			@Param("name")String name,@Param("work_mes")String work_mes);
	
	/*public List<User> getGroupUser(@Param("group_id")int group_id);*/
	
	public List<Map<String,Object>> getUserListMap(User user);

	//更新用户信息
	public int updateUser(User user);
	//营销工号得用户
	public User getUserByYxid(@Param("yx_id")String yx_Id) ;
	//通过身份证号
	public User getUserByIdCard(@Param("idCard")String idCard);
	
	//批量导入用户
	int importUser(@Param("listUser") List<User> listUser);
	/**获取符合查询条件的坐席列表
     * @param user
     * @return 坐席列表
     */
    List<Map<String, Object>> queryUserList(Map<String, Object> param);
} 
