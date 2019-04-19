package com.picc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.ClosingListSummarySearchMessage;
import com.picc.entity.ClosingRateList;
import com.picc.entity.ClosingRateListMessage;
import com.picc.entity.ClosingRateListPending;

public interface ClosingRateListService {

	public int importClosingRateList( List<ClosingRateList> closingRateList);
	public int importClosingRateListPending( List<ClosingRateListPending> closingRateListPending);
	public List<String> getClosingRateYearList();
	public List<ClosingRateListMessage> getClosingRateList(ClosingListSummarySearchMessage closingListSummarySearchMessage);
}
