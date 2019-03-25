package com.picc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.DamageLow;



/**
 * 低碳修复数据接口
 * @author wangXi
 * @date 2019/02/27
 *
 */
public interface DamageLowMapper {
	/**
	 * 批量保存
	 * @param damageLowList
	 * @return
	 */
	int saveDamageLowList(@Param("damageLowList")List<DamageLow> damageLowList);
	
}
