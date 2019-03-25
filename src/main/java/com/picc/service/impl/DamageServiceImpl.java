package com.picc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.dao.DamageMapper;
import com.picc.entity.Damage;
import com.picc.service.DamageService;
/**
 * 定损数据处理
 * @author wangXi
 * @date 2019/02/25
 *
 */
@Service
public class DamageServiceImpl implements DamageService {
	
	@Autowired
	private DamageMapper damageMapper;
	@Override
	public int saveDamageList(List<Damage> damageList) {
		return damageMapper.saveDamageList(damageList);
	}

}
