package com.picc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.DamageTicket;

/**
 * 功效 损核接口
 * @author wangXi
 * @date 2019/03/04
 * 
 */
public interface DamageTicketService {
	/**
	 * 批量保存
	 * @param damageTicketList
	 * @return
	 */
	public int saveDamageTicketList(@Param("damageTicketList")List<DamageTicket> damageTicketList);
	/**
	 * 获取统计 个人
	 * @param entryMap
	 * @return
	 */
	public List<Map<String,Object>> getUserDamageMonthOrYearList(Map<String,Object> entryMap);
	/**
	 * 获取统计 组别
	 * @param entryMap
	 * @return
	 */
	public  List<Map<String,Object>> getGroupDamageMonthOrYearList(Map<String,Object> entryMap);
}
