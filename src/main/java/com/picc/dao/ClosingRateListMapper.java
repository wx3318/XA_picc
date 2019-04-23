package com.picc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.ClosingList;
import com.picc.entity.ClosingListSummarySearchMessage;
import com.picc.entity.ClosingRateList;
import com.picc.entity.ClosingRateListMessage;
import com.picc.entity.ClosingRateListPending;

public interface ClosingRateListMapper {
	
 //批量导入结案率结案案件
 public int importClosingRateList(@Param("closingRateList") List<ClosingRateList> clsoingRateList);
 //批量导入结案率未结案件
 public int importClosingRateListPending(@Param("closingRateListPending") List<ClosingRateListPending> closingRateListPending);
 //按年查询
 public List<String> getYearList();
 //按条件查询
 public List<ClosingRateListMessage> getClosingRateList(ClosingListSummarySearchMessage closingListSummarySearchMessage);

 public	List<ClosingRateList> getClosingRateListParam(ClosingRateList closingRateList);
 public	List<ClosingRateListPending> getClosingRateListPendingParam(ClosingRateListPending closingRateListPending);
}
