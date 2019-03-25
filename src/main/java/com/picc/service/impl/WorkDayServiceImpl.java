package com.picc.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.WorkDayMapper;
import com.picc.entity.WorkDay;
import com.picc.service.WorkDayService;
@Service
public class WorkDayServiceImpl implements WorkDayService {

	@Autowired
	private WorkDayMapper workDayMapper;

	@Override
	public int insertWorkDay(WorkDay workDay) {
		
		String days = workDay.getDays();
		String[] split = days.split(",");
		int[] arr = new int[split.length];
		for(int i=0; i<split.length; i++){
		arr[i] = Integer.parseInt(split[i]);
		}
	     //冒泡排序
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length-1-i; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
		StringBuffer day = new StringBuffer();
		for(int i = 0; i < arr.length; i++) {			
			if(i==arr.length-1) {
				day.append(arr[i]);
			}else {
				day.append(arr[i]+","); 
			}
		}
		workDay.setDays(day.toString());
		return workDayMapper.insertWorkDay(workDay);
	}

	
	@Override
	public List<Map<String, Object>> getWorkDayList(WorkDay workDay) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageInfo<Map<String, Object>> getPageWorkDayList(WorkDay workDay) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PageHelper.startPage(workDay.getPageNum(),workDay.getLength());
		List<Map<String, Object>> tranckList = workDayMapper.getWorkDayList(workDay);
		
		for(Map<String, Object> t:tranckList) {
			if(null!=t.get("create_date")) {
            	if(!String.valueOf(t.get("create_date")).equals("") || null!=String.valueOf(t.get("create_date"))){
	            	 t.put("create_date", sdf.format(sdf.parse(String.valueOf(t.get("create_date")))));   	 
	            }
            }else {
            	t.put("create_date", "");
            }
		}
		 PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(tranckList);
		return pageInfo;
	}


	@Override
	public String getWorkDayMonth(String month) {
		String[] split = month.split("-");
		String months = split[0]+"-"+split[1];
		int day= Integer.parseInt(split[2]);
		WorkDay workDay = new WorkDay();
		workDay.setMonth(months);
		Map<String, Object> workDayMap = workDayMapper.getWorkDay(workDay);
		String days = workDayMap.get("days").toString();
		String[] days2 = days.split(",");
		int[] arr = new int[days2.length];
		String eqday="";
		for(int i = 0; i<days2.length; i++){
			arr[i] = Integer.parseInt(days2[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			int arrayDays = arr[i];
			
			if (day == arrayDays) {
				if(arrayDays<10) {
					eqday=0+String.valueOf(arr[i]); 
				}else {
					eqday = String.valueOf(arr[i]);
				}
				break;
			} 
		}
		
		String stMonth=months+"-"+eqday;
		if(stMonth.equals(month)) {
			return stMonth;
		}else {
			return null;
		}		
	}


	@Override
	public String getWorkDayMonthLast(String monthday) {
		String[] split = monthday.split("-");
		String months =split[0]+"-"+split[1];
		String month=split[1];
		int day= Integer.parseInt(split[2]);		
		WorkDay workDay = new WorkDay();
		workDay.setMonth(months);
		Map<String, Object> workDayMap = workDayMapper.getWorkDay(workDay);
		String days = workDayMap.get("days").toString();
		String[] days2 = days.split(",");
		int[] arr = new int[days2.length];
		for(int i=0; i<days2.length; i++){
			arr[i] = Integer.parseInt(days2[i]);
		}		
			if(Integer.parseInt(month)>1) {
				if(day <= arr[0]) {
					if(Integer.parseInt(split[1])<=10) {
						months=split[0]+"-0"+(Integer.parseInt(split[1])-1);
					}else {
						months=split[0]+"-"+(Integer.parseInt(split[1])-1);
					}					
					workDay.setMonth(months);
					Map<String, Object> workDayMap2 = workDayMapper.getWorkDay(workDay);
					String[] split2 = (workDayMap2.get("days").toString()).split(",");
					int length = split2.length;
					String lastday=split2[length-1];
					if(Integer.parseInt(lastday)<11) {
						lastday =0+lastday;
					}
					return months+"-"+lastday;
				}else {					
					String lastday="";
					for (int i = 0; i < arr.length; i++) {
						int arrayDays = arr[i];
						if (day == arrayDays) {
							if(arrayDays<11) {
								lastday =0+String.valueOf(arr[i-1]);
							}else {
								lastday = String.valueOf(arr[i-1]);
							}
							
							break;					  
						} 
					}
					return months+"-"+lastday;
				}				
			}else{
				months=(Integer.parseInt(split[0])-1)+"-"+12;
				if(day <= arr[0]) {					
					workDay.setMonth(months);
					Map<String, Object> workDayMap2 = workDayMapper.getWorkDay(workDay);
					String[] split2 = (workDayMap2.get("days").toString()).split(",");
					int length = split2.length;
					String lastday=split2[length-1];
					if(Integer.parseInt(lastday)<10) {
						lastday =0+lastday;
					}
					return months+"-"+lastday;
				}else {		
					Map<String, Object> workDayMap2 = workDayMapper.getWorkDay(workDay);
					String days3 = workDayMap.get("days").toString();
					String[] days4 = days3.split(",");
					int[] arr1 = new int[days4.length];
					for(int i=0; i<days4.length; i++){
						arr1[i] = Integer.parseInt(days4[i]);
					}
					String lastday="";
					for (int i = 0; i < arr1.length; i++) {
						int arrayDays = arr1[i];
						if (day == arrayDays) {
							if(arrayDays<11) {
								lastday =0+String.valueOf(arr[i-1]);
							}else {
								lastday = String.valueOf(arr[i-1]);
							}
							break;					  
						} 
					}
					return months+"-"+lastday;
				}
			}
		

	}

}
