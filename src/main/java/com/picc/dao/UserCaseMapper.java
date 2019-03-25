package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.UserCase;

/**
 * 个人未决任务
 * @author wangXi
 * @date 2019/1/2
 * 
 */
public interface UserCaseMapper {
	/**
	 * 任务设置
	 * @param usercase
	 * @return
	 */
	int saveUserCase(UserCase usercase);
	/**
	 *  未决个人月任务集合
	 * @param usercase
	 * @return
	 */
	List<Map<String,Object>> getUserCaseListByParam(UserCase usercase);
	/**
	 * 个人任务修改
	 * @param usercase
	 * @return
	 */
	int updateUserCase(UserCase usercase);
	/**
	 * 根据id查询个人任务
	 * @param usercase
	 * @return
	 */
	UserCase getUserCaseById(UserCase usercase);
	/**
	 * 通过id删除
	 * @param usercase
	 * @return
	 */
	int deleteUserCaseById(UserCase usercase);
	/**
	 *  个人月任务考核
	 * @param usercase
	 * @return
	 */
	List<Map<String,Object>> getMonthUserCase(UserCase usercase);
}
