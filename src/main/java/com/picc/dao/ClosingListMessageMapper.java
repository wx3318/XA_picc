package com.picc.dao;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;

public interface ClosingListMessageMapper {
	//按机构导出
	public List<ClosingListMessage> getClosingCountMessageList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
	
	//按案件类型统计导出
	public List<ClosingListMessage> getClosingListCaseTypeCount(ClosingListSummarySearchMessage closingListSummarySearchMessage);
	//按机构统计导出
	public List<ClosingListMessage> getClosingCountMessageGroupList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
	
	public List<ClosingListSummarySearchMessage> getClosingListCaseType();
	//按年查询
	public  List<String> getClosingListYear();
	//按案件类型导出
	public List<ClosingListMessage> exportClosingList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
}
