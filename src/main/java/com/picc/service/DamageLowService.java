package com.picc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.DamageLow;

/**
 * 低碳修复接口
 * @author wangXi
 * @date 2019/02/27
 */
public interface DamageLowService {
	/**
	 * 批量保存
	 * @param damageLowList
	 * @return
	 */
	public int saveDamageLowList(@Param("damageLowList")List<DamageLow> damageLowList);
}
