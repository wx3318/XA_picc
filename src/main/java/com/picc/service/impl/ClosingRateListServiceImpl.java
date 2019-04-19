package com.picc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picc.dao.ClosingRateListMapper;
import com.picc.entity.ClosingListSummarySearchMessage;
import com.picc.entity.ClosingRateList;
import com.picc.entity.ClosingRateListMessage;
import com.picc.entity.ClosingRateListPending;
import com.picc.service.ClosingRateListService;
@Service
public class ClosingRateListServiceImpl implements ClosingRateListService {
	
	@Autowired
	private ClosingRateListMapper closingRateListMapper;
	
	@Override
	public int importClosingRateList(List<ClosingRateList> closingRateList) {
		
		return closingRateListMapper.importClosingRateList(closingRateList);
	}
	@Override
	public int importClosingRateListPending(List<ClosingRateListPending> closingRateListPending) {
		
		return closingRateListMapper.importClosingRateListPending(closingRateListPending);
	}
	@Override
	public List<String> getClosingRateYearList() {
		
		return closingRateListMapper.getYearList();
	}
	@Override
	public List<ClosingRateListMessage> getClosingRateList(
			ClosingListSummarySearchMessage closingListSummarySearchMessage) {
		
		return closingRateListMapper.getClosingRateList(closingListSummarySearchMessage);
	}

}
