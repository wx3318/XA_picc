package com.picc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picc.dao.ClosingListMessageMapper;

import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;
import com.picc.service.ClosingListMessageService;
@Service
public class ClosingListMessageImpl implements ClosingListMessageService {

	@Autowired
	private ClosingListMessageMapper closingListMessageMapper;

	@Override
	public List<ClosingListMessage> getClosingCountMessageList(ClosingListSummarySearchMessage closingListSummarySearchMessage) {
		
		return closingListMessageMapper.getClosingCountMessageList(closingListSummarySearchMessage);
	}

	@Override
	public List<ClosingListSummarySearchMessage> getClosingListCaseType() {
		
		return closingListMessageMapper.getClosingListCaseType();
	}

	@Override
	public List<String> getClosingListYear() {
		
		return closingListMessageMapper.getClosingListYear();
	}

	@Override
	public List<ClosingListMessage> exportClosingList(ClosingListSummarySearchMessage closingListSummarySearchMessage) {
		
		return closingListMessageMapper.exportClosingList(closingListSummarySearchMessage);
	}

	@Override
	public List<ClosingListMessage> getClosingListCaseTypeCount(
			ClosingListSummarySearchMessage closingListSummarySearchMessage) {
		return closingListMessageMapper.getClosingListCaseTypeCount(closingListSummarySearchMessage);
	}

	@Override
	public List<ClosingListMessage> getClosingCountMessageGroupList(
			ClosingListSummarySearchMessage closingListSummarySearchMessage) {
	
		return closingListMessageMapper.getClosingCountMessageGroupList(closingListSummarySearchMessage);
	}
	

}
