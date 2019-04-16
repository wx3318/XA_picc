package com.picc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.DamageTicket;


/**
 * 功效 损核数据接口
 * @author wangXi
 * @date 2019/03/04
 */
public interface DamageTicketMapper {
	/**
	 * 批量保存
	 * @param damageTicketList
	 * @return
	 */
	int saveDamageTicketList(@Param("damageTicketList")List<DamageTicket> damageTicketList);
	/**
	 * 获取统计
	 * @param entryMap
	 * @return
	 */
	List<Map<String,Object>> getUserDamageList(Map<String,Object> entryMap);
	/**
	 * 合计统计
	 * @param entryMap
	 * @return
	 */
	Map<String,Object>   querySumDamage(Map<String,Object> entryMap);
}
