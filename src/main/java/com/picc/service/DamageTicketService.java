package com.picc.service;

import java.util.List;

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
}
