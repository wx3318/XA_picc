package com.picc.dao;

import java.util.List;

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
}
