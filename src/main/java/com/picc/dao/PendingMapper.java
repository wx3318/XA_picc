package com.picc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.Pending;
/**
 * 未决案件
 * @author wangXi
 * @date 2018/12/05
 */
public interface PendingMapper {
	/**
	 *批量保存
	 * @param pendingList
	 * @return 保存数量
	 */
	int savePendingList(@Param("pendingList")List<Pending> pendingList);
	/**
	 * 根据参数查找未决案件集合
	 * @param pending
	 * @return 未决案件集合
	 */
	List<Pending> getPendingListParam(Pending pending);
	/**
	 * 更新案件状态（结案/核未结/理未核）
	 * @param pendingList
	 * @return
	 */
	int updatePending(@Param("pendingList")List<Pending> pendingList);
	/**
	 * 案件区域和机构划分
	 * @param pendingList
	 * @return
	 */
	int updatePendingAreaGroupType(@Param("pendingList")List<Pending> pendingList);
	
	/**
	 * 区域查询
	 * @param pending
	 * @return
	 */
	List<Map<String,Object>> getPendingListByAreaType(Pending pending);
	/**
	 * 机构分中心查询
	 * @param pending
	 * @return
	 */
	List<Map<String,Object>> getPengdingGroup(Pending pending);
	/**
	 * 更新归属人or分中心
	 * @param pending
	 * @return
	 */
	int updatePendingInfoById(Pending pending);
	/**
	 * 批量更新案件归属人
	 * @param pendingList
	 * @return
	 */
	int updatePendingInfoList(@Param("pendingList") List<Pending> pendingList);
	/**
	 * 实体类（省内立未理）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOnShengEntry(Pending pending);
	/**
	 * 实体类（郊县立未理）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOnjiaoEntry(Pending pending);
	/**
	 * 实体类（省间通赔）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOntpEntry(Pending pending);
	/**
	 * 实体类（城区立未理）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOncqEntry(Pending pending);
	/**
	 * 实体类（城区与辖内所有）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOnCqXnEntry(Pending pending);
	
	/**
	 * 实体类（通赔未结案）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOntpNoEntry(Pending pending);
	
	/**
	 * 实体类（郊县未结案）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOnjiaoNoEntry(Pending pending);
	
	/**
	 *  实体类（城区未结案）
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListOnchengNoEntry(Pending pending);
	/**
	 * 所有数据为原来公共数据（人员调动）
	 * @param pending
	 * @return
	 */
	int updateAllCaseUserInfo(Pending pending);
	/**
	 * 带走自己的案子( 分中心调动)
	 * @param pending
	 * @return
	 */
	int updateCaseUseredInfo(Pending pending);
	/**
	 * 平均分案子 去 group NULL userid NULL
	 * @param pending
	 * @return
	 */
	int updateCaseUserGroup(Pending pending);
	/**
	 * 获取所有组别为空  归属人 为空 的 案件
	 * @param pending
	 * @return
	 */
	List<Pending> getPendingListGroupUser(Pending pending);
	
	
	
}
