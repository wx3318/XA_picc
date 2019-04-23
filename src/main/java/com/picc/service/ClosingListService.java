package com.picc.service;

import java.util.List;
import java.util.Set;

import com.picc.entity.ClosingList;
import com.picc.entity.ClosingListMessage;
import com.picc.entity.ClosingListSummarySearchMessage;

import com.picc.entity.User;

public interface ClosingListService {
	
		public	List<ClosingList> getClosingListParam(ClosingList cl);
		
		public int importClosingList(List<ClosingList> arrayList);
		
		public List<ClosingListSummarySearchMessage> serachClosingListSummaryByGroupId(Integer groupId);
		
}
