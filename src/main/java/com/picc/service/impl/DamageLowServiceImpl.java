package com.picc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.dao.DamageLowMapper;
import com.picc.entity.DamageLow;
import com.picc.service.DamageLowService;
/**
 * 低碳修复数据梳理
 * @author wangXi
 * @date 2019/02/27
 */
@Service
public class DamageLowServiceImpl implements DamageLowService {
	
	@Autowired
	private DamageLowMapper damageLowMapper;
	
	@Override
	public int saveDamageLowList(List<DamageLow> damageLowList) {
		return damageLowMapper.saveDamageLowList(damageLowList);
	}

}
