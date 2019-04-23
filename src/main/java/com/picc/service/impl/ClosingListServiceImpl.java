package com.picc.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picc.dao.ClosingListMapper;
import com.picc.entity.ClosingList;
import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;
import com.picc.service.ClosingListService;
@Service
public class ClosingListServiceImpl implements ClosingListService {
	
	@Autowired
	private ClosingListMapper closingListMapper;
	
	@Override
	public int importClosingList(List<ClosingList> closingList) {
		
		return closingListMapper.importClosingList(closingList);
	}
	@Override
	public List<ClosingListSummarySearchMessage> serachClosingListSummaryByGroupId(
			Integer groupId) {
		
		return closingListMapper.serachClosingListSummaryByGroupId(groupId);
	}
	@Override
	public List<ClosingList> getClosingListParam(ClosingList closingList) {
		// TODO Auto-generated method stub
		return closingListMapper.getClosingListParam(closingList);
	}
	

}
