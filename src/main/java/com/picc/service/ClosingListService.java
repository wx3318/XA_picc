package com.picc.service;

import java.util.List;

import com.picc.entity.ClosingList;
import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;

import com.picc.entity.User;

public interface ClosingListService {
	
		public	List<ClosingList> getClosingListParam(ClosingList closingList);
		
		public int importClosingList(List<ClosingList> closingList);
		
		public List<ClosingListSummarySearchMessage> serachClosingListSummaryByGroupId(Integer groupId);
		
}
