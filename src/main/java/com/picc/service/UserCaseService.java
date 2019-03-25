package com.picc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.UserCase;

/**
 * 个人未决任务接口
 * @author wangXi
 * @date 2019/1/2
 *
 */
public interface UserCaseService {
	/**
	 * 个人未决新增
	 * @param usercase
	 * @return
	 */
	public int saveUserCase(UserCase usercase);
	/**
	 * 个人未决任务集合
	 * @param usercase
	 * @return
	 */
	public List<Map<String,Object>> getUserCaseListByParam(UserCase usercase);
	
	/**
	 * 个人任务分页
	 * @param usercase
	 * @return
	 */
	public PageInfo<Map<String,Object>> getPageUserCaseList(UserCase usercase);
	/**
	 * 个人任务修改
	 * @param usercase
	 * @return
	 */
	public int updateUserCase(UserCase usercase);
	/**
	 * 个人任务通过id获取
	 * @param usercase
	 * @return
	 */
	public UserCase getUserCaseById(UserCase usercase);
	/**
	 * 删除byId
	 * @param usercase
	 * @return
	 */
	public int deleteUserCaseById(UserCase usercase);
	/**
	 * 个人月任务考核
	 * @param usercase
	 * @return
	 */
	public List<Map<String,Object>> getMonthUserCase(UserCase usercase);
}
