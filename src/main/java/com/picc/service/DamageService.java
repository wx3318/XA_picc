package com.picc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.Damage;
/**]
 * 定损接口
 * @author wangXi
 * @date 2019/02/25
 */
public interface DamageService {
	/**
	 *批量保存
	 * @param damageList
	 * @return 保存数量
	 */
	public int saveDamageList(@Param("damageList")List<Damage> damageList);
}
