package com.picc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.GroupCaseMapper;
import com.picc.entity.GroupCase;
import com.picc.service.GroupCaseService;
/**
 * 月任务设置接口实现（未决案件）
 * @author wangXi
 * @date 2018/12/18
 * 
 */
@Service
public class GroupCaseServiceImpl implements GroupCaseService {
	
	@Autowired
	private GroupCaseMapper groupCaseMapper;
	
	@Override
	public int saveGroupCase(GroupCase groupCase) {
		return groupCaseMapper.saveGroupCase(groupCase);
	}

	@Override
	public List<Map<String, Object>> getGroupCaseList(GroupCase groupCase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Map<String, Object>> getPageGroupCaseList(GroupCase groupCase) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		PageHelper.startPage(groupCase.getPageNum(),groupCase.getLength());
		List<Map<String, Object>> groupCaseList = groupCaseMapper.getGroupCaseList(groupCase);
		for (Map<String, Object> mapEntity : groupCaseList) {
			try {
				mapEntity.put("create_date", sdf.format(sdf.parse(mapEntity.get("create_date").toString())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(mapEntity.get("endcase_numbe")==null){
				mapEntity.put("endcase_numbe","");
			}
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(groupCaseList);
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> getCaseMonthList(GroupCase groupCase) {
		if(groupCase.getCreateDate()==null) {
			Date date = new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");			
			try {
				groupCase.setCreateDate(sdf.parse(sdf.format(date)) );
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		List<Map<String, Object>> caseMonthList = groupCaseMapper.getCaseMonthList(groupCase);
		if(caseMonthList.size()<11) {
			List<Map<String, Object>> caseMonthListe = new ArrayList<Map<String, Object>>();
			return caseMonthListe;
		}
		Map<String, Object> sumMap = new HashMap<String, Object>();	
		Integer startingNumber=0;
		Integer dayNewCase =0; 
		Integer dayEndCase=0;
		Integer monthEndCase=0;
		Integer allCaseSize=0;
		Integer startTargetNumber=0;
		Integer startDiff=0;
		Integer budgetTargetNumber=0;
		Integer budgetDiff=0;
		Integer challengNumber=0;
		Integer challengDiff=0;
		for(Map<String, Object> entry:caseMonthList) {
		 	if(entry.get("dayNewCase")==null) {
		 		entry.put("dayNewCase", 0);
		 	}
		 	if(entry.get("dayEndCase")==null) {
		 		entry.put("dayEndCase", 0);
		 	}
		 	if(entry.get("monthEndCase")==null) {
		 		entry.put("monthEndCase", 0);
		 	}
		 	if(entry.get("monthendCase")==null) {
		 		entry.put("monthendCase", 0);
		 	}
		 	if(entry.get("endcase_number")==null) {
		 		entry.put("endcase_number", 0);
		 	}
		 	if(entry.get("allCaseSize")==null) {
		 		entry.put("allCaseSize", 0);
		 	}
		 	if(entry.get("startDiff")==null) {
		 		entry.put("startDiff", 0);
		 	}
		 	if(entry.get("challengDiff")==null) {
		 		entry.put("challengDiff", 0);
		 	}
		 	if(entry.get("budgetDiff")==null) {
		 		entry.put("budgetDiff", 0);
		 	}
		 	entry.put("monthEndCase",Integer.parseInt(entry.get("monthendCase").toString())+Integer.parseInt(entry.get("endcase_number").toString()));
		 	startingNumber+=Integer.parseInt(entry.get("starting_number").toString());
		 	dayNewCase+=Integer.parseInt(entry.get("dayNewCase").toString());
		 	dayEndCase+=Integer.parseInt(entry.get("dayEndCase").toString());
		 	monthEndCase+=Integer.parseInt(entry.get("monthEndCase").toString());
		 	allCaseSize+=Integer.parseInt(entry.get("allCaseSize").toString());
		 	startTargetNumber+=Integer.parseInt(entry.get("start_target_number").toString());
		 	startDiff+=Integer.parseInt(entry.get("startDiff").toString());
		 	budgetTargetNumber+=Integer.parseInt(entry.get("budget_target_number").toString());
		 	budgetDiff+=Integer.parseInt(entry.get("budgetDiff").toString());
		 	challengNumber+=Integer.parseInt(entry.get("challeng_number").toString());
		 	challengDiff+=Integer.parseInt(entry.get("challengDiff").toString());
		}
		if(caseMonthList.size()>0) {
			List<Map<String, Object>> caseRank = new ArrayList<Map<String,Object>>(); 
			for(int i=0;i<4;i++) {
				caseRank.add(caseMonthList.get(i));
			}
			caseMonthList.removeAll(caseRank);
			Collections.sort(caseRank, new Comparator<Map<String, Object>>() {
				@Override
	            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
	                Integer name1 = Integer.valueOf(o1.get("startDiff").toString()) ;//name1是从你list里面拿出来的一个 
	                Integer name2 = Integer.valueOf(o2.get("startDiff").toString()) ; //name1是从你list里面拿出来的第二个name
	                return name1.compareTo(name2);
	            }
	        });			
			for(int i=0;i<caseRank.size();i++) {
				caseRank.get(i).put("rangking", i+1);
			}
			List<Map<String, Object>> caseRank1 = new ArrayList<Map<String,Object>>(); 
			for(int i=0;i<7;i++) {
				caseRank1.add(caseMonthList.get(i));
			}
			caseMonthList.removeAll(caseRank1);
			Collections.sort(caseRank1, new Comparator<Map<String, Object>>() {
				@Override
	            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
	                Integer name1 = Integer.valueOf(o1.get("startDiff").toString()) ;//name1是从你list里面拿出来的一个 
	                Integer name2 = Integer.valueOf(o2.get("startDiff").toString()) ; //name1是从你list里面拿出来的第二个name
	                return name1.compareTo(name2);
	            }
	        });			
			for(int i=0;i<7;i++) {
				caseRank1.get(i).put("rangking", i+1);
			}
			for(Map<String, Object> entry:caseMonthList) {
				entry.put("rangking","");
			}
			caseMonthList.addAll(caseRank);
			caseMonthList.addAll(caseRank1);
		}
		Collections.sort(caseMonthList, new Comparator<Map<String, Object>>() {
			@Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.valueOf(o1.get("group_id").toString()) ;//name1是从你list里面拿出来的一个 
                Integer name2 = Integer.valueOf(o2.get("group_id").toString()) ; //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });
		sumMap.put("group_name", "未决合计");
		sumMap.put("starting_number", startingNumber);
		sumMap.put("dayNewCase", dayNewCase);
		sumMap.put("dayEndCase", dayEndCase);
		sumMap.put("monthEndCase", monthEndCase);
		sumMap.put("allCaseSize", allCaseSize);
		sumMap.put("start_target_number", startTargetNumber);
		sumMap.put("startDiff", startDiff);
		sumMap.put("budget_target_number", budgetTargetNumber);
		sumMap.put("budgetDiff", budgetDiff);
		sumMap.put("challeng_number", challengNumber);
		sumMap.put("challengDiff", challengDiff);
		sumMap.put("rangking", "");
		caseMonthList.add(sumMap);
		
		return caseMonthList;
	}

	@Override
	public GroupCase getGroupCaseById(GroupCase groupCase) {
		return groupCaseMapper.getGroupCaseById(groupCase);
	}

	@Override
	public int updateGroupCase(GroupCase groupCase) {
		return groupCaseMapper.updateGroupCase(groupCase);
	}

	@Override
	public int deletGroupCase(GroupCase groupCase) {
		return groupCaseMapper.deletGroupCase(groupCase);
	}

}
