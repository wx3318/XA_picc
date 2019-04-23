package com.picc.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.ClosingList;
import com.picc.entity.ClosingListSummarySearchMessage;

import com.picc.entity.User;

public interface ClosingListMapper {
	//結案集合
	public	List<ClosingList> getClosingListParam(ClosingList closingList);
	
	//批量导入
	public int importClosingList(@Param("closingList") List<ClosingList> closingList);
	
	//根据groupId查询
	public List<ClosingListSummarySearchMessage> serachClosingListSummaryByGroupId(@Param("groupId")Integer groupId);
	
	
}
