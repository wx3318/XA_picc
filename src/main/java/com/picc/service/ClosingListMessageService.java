package com.picc.service;

import java.util.List;

import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;

public interface ClosingListMessageService {
	
	public List<ClosingListMessage> getClosingCountMessageList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
	public List<ClosingListSummarySearchMessage> getClosingListCaseType();
	public List<String> getClosingListYear();
	public List<ClosingListMessage> exportClosingList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
	public List<ClosingListMessage> getClosingListCaseTypeCount(ClosingListSummarySearchMessage closingListSummarySearchMessage);
	public List<ClosingListMessage> getClosingCountMessageGroupList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
}
