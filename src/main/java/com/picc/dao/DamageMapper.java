package com.picc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.Damage;



/**
 * 定损数据接口
 * @author wangXi
 * @date 2019/02/25
 * 
 */
public interface DamageMapper {
	/**
	 *批量保存
	 * @param damageList
	 * @return 保存数量
	 */
	int saveDamageList(@Param("damageList")List<Damage> damageList);
	
}
