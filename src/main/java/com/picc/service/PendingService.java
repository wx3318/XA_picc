package com.picc.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.picc.entity.Pending;
/**
 * 未决案件 数据处理接口
 * @author wangXi
 * @date 2018/12/05
 */
public interface PendingService {
	
	/**
	 * 批量新增
	 * @param pendingList
	 * @return 保存的数量
	 */
	public int savePendingList(List<Pending> pendingList);
	
	/**
	 * 根据参数查找未决案件集合
	 * @param pending
	 * @return 未决案件集合
	 */
	public List<Pending> getPendingListParam(Pending pending);
	
	/**
	 * 案件状态的更新（批量）
	 * @param pendingList
	 * @return
	 */
	public int updatePending(@Param("pendingList")List<Pending> pendingList); 
	
	/**
	 * 案件区域和机构划分
	 * @param pendingList
	 * @return
	 */
	public int updatePendingAreaGroupType(@Param("pendingList")List<Pending> pendingList);
	/**
	 * 分中心查询
	 * @param pending
	 * @return
	 */
	public List<Map<String,Object>> getPengdingGroup(Pending pending);
	/**
	 * 分中心分页查询
	 * @param pending
	 * @return
	 */
	public PageInfo<Map<String,Object>> getPagePengdingGroup(Pending pending);
	
	/**
	 * 省内立未理
	 * @param pending
	 * @return
	 */
	public List<Map<String,Object>> getPendingListByAreaType(Pending pending);
	/**
	 * 区域划分
	 * @param pending
	 * @return
	 */
	public PageInfo<Map<String,Object>> getPagePendingListByAreaType(Pending pending);	
	/**
	 * 修改归属人
	 * @param pending
	 * @return
	 */
	public int updatePendingInfoById(Pending pending);
	/**
	 * 实体类（省内立未理）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOnShengEntry(Pending pending);
	
	/**
	 * 实体类（郊县立未理）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOnjiaoEntry(Pending pending);
	/**
	 * 实体类（省间通赔）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOntpEntry(Pending pending);
	
	/**
	 * 实体类（城区立未理）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOncqEntry(Pending pending);
	/**
	 * 实体类（城区与辖内所有）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOnCqXnEntry(Pending pending);
	
	/**
	 * 实体类（通赔未结案）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOntpNoEntry(Pending pending);
	
	/**
	 * 实体类（郊县未结案）
	 * @param pending
	 * @return
	 */
	public List<Pending> getPendingListOnjiaoNoEntry(Pending pending);
	
	/**
	 *  实体类（城区未结案）
	 * @param pending
	 * @return
	 */
	 public List<Pending> getPendingListOnchengNoEntry(Pending pending);
	
	
	 /**
		 * 批量更新案件归属人
		 * @param pendingList
		 * @return
		 */
	public	int updatePendingInfoList(List<Pending> pendingList);
	
	/**
	 * 所有数据为公共数据（人员调动）
	 * @param pending
	 * @return
	 */
	public int updateAllCaseUserInfo(Pending pending);
	/**
	 * 带走自己的案子( 分中心调动)
	 * @param pending
	 * @return
	 */
	public int updateCaseUseredInfo(Pending pending);
	/**
	 * 平均分案子 去 group NULL userid NULL
	 * @param pending
	 * @return
	 */
	public int updateCaseUserGroup(Pending pending);
	/**
	 * 获取所有组别为空  归属人 为空 的 案件
	 * @param pending
	 * @return
	 */
 	public List<Pending> getPendingListGroupUser(Pending pending);
	
}
