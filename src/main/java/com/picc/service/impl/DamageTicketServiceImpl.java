package com.picc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.dao.DamageTicketMapper;
import com.picc.entity.DamageTicket;
import com.picc.service.DamageTicketService;
/**
 * 功效 损核 数据处理
 * @author wangXi
 * @date 2019/03/04
 */
@Service
public class DamageTicketServiceImpl implements DamageTicketService {
	
	@Autowired
	private DamageTicketMapper damageTicketMapper;
	
	@Override
	public int saveDamageTicketList(List<DamageTicket> damageTicketList) {
		return damageTicketMapper.saveDamageTicketList(damageTicketList);
	}

}
