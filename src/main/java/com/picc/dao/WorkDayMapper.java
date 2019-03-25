package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.WorkDay;

public interface WorkDayMapper {
	
	int insertWorkDay(WorkDay workDay);
	
	List<Map<String,Object>> getWorkDayList(WorkDay workDay);
	
	Map<String,Object> getWorkDay(WorkDay workDay);
}
