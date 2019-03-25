package com.picc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.UserMapper;
import com.picc.dao.WorkTimeMapper;
import com.picc.entity.User;
import com.picc.entity.WorkTime;
import com.picc.service.WorkTimeService;
import com.picc.util.WorkTimeUtilSch;
import com.picc.util.ZkemSDKUtil;
@Service
public class WorkTimeServiceImpl implements WorkTimeService {
	
	private final static Logger logger = Logger.getLogger(WorkTimeServiceImpl.class);
	
    @Autowired
	private WorkTimeMapper workTimeMapper;
	
    @Autowired
	private  UserMapper userMapper;
    
	@Override
	public PageInfo<Map<String, Object>> getWorkTimeByWorkTime(WorkTime workTime) throws Exception {
		//开始分页
		 PageHelper.startPage(workTime.getPageNum(),workTime.getLength());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		 if("1".equals(workTime.getWorkTion())) {
			 String time ="08:31:00";
			 Date start = format.parse(time);
			 workTime.setStartTime(start);
		 }
		 List<Map<String, Object>> maplist = workTimeMapper.getWorkTimeByWorkTime(workTime);
		
		 for(Map<String, Object> mapEntity : maplist){			 
			 String workDate = sdf.format(sdf.parse(String.valueOf(mapEntity.get("work_date"))));
       	  // 格式化日期
           mapEntity.put("work_date", workDate);
           if(null!=mapEntity.get("start_time")) {
           		if(!String.valueOf(mapEntity.get("start_time")).equals("") || null!=String.valueOf(mapEntity.get("start_time"))){
           			mapEntity.put("start_time", format.format(sdt.parse(String.valueOf(mapEntity.get("start_time")))));   	 
	            }
           }else {
           		mapEntity.put("start_time", "");
           }
           if(null!=mapEntity.get("end_time")) {
        	   if(null!=String.valueOf(mapEntity.get("end_time")) || !String.valueOf(mapEntity.get("end_time")).equals("")  ) {
        		   mapEntity.put("end_time", format.format(sdt.parse(String.valueOf(mapEntity.get("end_time")))));
		       }
          }else {
        	  mapEntity.put("end_time", "");
          }
           if(null!=mapEntity.get("station")) {
        	   if(Integer.parseInt(mapEntity.get("station").toString())==1) {
        		   mapEntity.put("station", "正常");
        	   }else if(Integer.parseInt(mapEntity.get("station").toString())==2) {
        		   mapEntity.put("station", "请假");
        	   }else if(Integer.parseInt(mapEntity.get("station").toString())==4) {
        		   mapEntity.put("station", "外出");
        	   }else {
        		   mapEntity.put("station", "");
        	   }
           }else {
        	   mapEntity.put("station", "");
           }
		 }
		 PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maplist);
		return pageInfo;
	}
	
	@Override
	public List<Map<String, Object>> getWorkTimeListByWorkTime(WorkTime workTime) throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		 if("1".equals(workTime.getWorkTion())) {
			 String time ="08:31:00";
			 Date start = format.parse(time);
			 workTime.setStartTime(start);
		 }
		 List<Map<String, Object>> maplist = workTimeMapper.getWorkTimeByWorkTime(workTime);
		
		 for(Map<String, Object> mapEntity : maplist){			 
			 String workDate = sdf.format(sdf.parse(String.valueOf(mapEntity.get("work_date"))));
      	  // 格式化日期
          mapEntity.put("work_date", workDate);
          if(null!=mapEntity.get("start_time")) {
          	if(!String.valueOf(mapEntity.get("start_time")).equals("") || null!=String.valueOf(mapEntity.get("start_time"))){
	            	 mapEntity.put("start_time", format.format(sdt.parse(String.valueOf(mapEntity.get("start_time")))));   	 
	            }
          }else {
          	mapEntity.put("start_time", "");
          }
          if(null!=mapEntity.get("end_time")) {
         	 if(null!=String.valueOf(mapEntity.get("end_time")) || !String.valueOf(mapEntity.get("end_time")).equals("")  ) {
		            	mapEntity.put("end_time", format.format(sdt.parse(String.valueOf(mapEntity.get("end_time")))));
		            }
         }else {
         	mapEntity.put("end_time", "");
         }
          if(null!=mapEntity.get("station")) {
       	   if(Integer.parseInt(mapEntity.get("station").toString())==1) {
       		   mapEntity.put("station", "正常");
       	   }else if(Integer.parseInt(mapEntity.get("station").toString())==2) {
       		   mapEntity.put("station", "请假");
       	   }else if(Integer.parseInt(mapEntity.get("station").toString())==3) {
       		   mapEntity.put("station", "休假");
       	   }else if(Integer.parseInt(mapEntity.get("station").toString())==4) {
       		   mapEntity.put("station", "外出");
       	   }else {
       		   mapEntity.put("station", "");
       	   }
          }else {
       	   mapEntity.put("station", "");
          }
          if(null==mapEntity.get("message")) {
        	  mapEntity.put("message", "");
          }
		 }
		return maplist;
	}
	
	
	@Override
	public WorkTime getWorkTimeByKey(Integer id) {		
		return workTimeMapper.getWorkTimeByKey(id);
	}

	@Override
	public int updateWorkTime(WorkTime workTime) {
		return workTimeMapper.updateWorkTime(workTime);
	}

	@Override
	public List<Map<String, Object>> getWorkTimeMonth(WorkTime workTime) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		String start = format+" "+"08:31:00";		
		Date startTime = sdf.parse(start);
		workTime.setStartTime(startTime);
		if(null==workTime.getWorkDate()) {
			Date parse = sdf.parse(format);
			workTime.setWorkDate(parse);
		}
		Date workDate1 = workTime.getWorkDate();
		String format2 = sdf.format(workDate1);
		String[] split2 = format2.split("-");
		String startWorkDate1 = split2[0]+"-"+split2[1]+"-"+1;
		 Calendar cal = Calendar.getInstance();
         //设置年份
         cal.set(Calendar.YEAR,Integer.parseInt(split2[0]));
         //设置月份
         cal.set(Calendar.MONTH, Integer.parseInt(split2[1])-1);
         //获取某月最大天数
         int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
         //设置日历中月份的最大天数
         
         cal.set(Calendar.DAY_OF_MONTH, lastDay);
         //格式化日期
         String lastDayOfMonth = sdf.format(cal.getTime());		
		Date startWorkDate = sdf.parse(startWorkDate1);		
		Date endWorkDate = sdf.parse(lastDayOfMonth);
		workTime.setStartWorkDate(startWorkDate);
		workTime.setEndWorkDate(endWorkDate);
		List<Map<String, Object>> workTimeMonth = workTimeMapper.getWorkTimeMonth(workTime);
		for(Map<String, Object> mapEntity : workTimeMonth){
			if(null!=mapEntity.get("chalfSize")) {
				int parseInt = Integer.parseInt(mapEntity.get("chalfSize").toString());
				float size=(float) parseInt/2.0f;
				if(null!=mapEntity.get("leatSize")) {					 					 				 
					 int parseInt2 = Integer.parseInt(mapEntity.get("leatSize").toString());
					 mapEntity.put("leatSize", parseInt2+size);
				}else {
					mapEntity.put("leatSize",size);
				}
				if(null!=mapEntity.get("workSize")) {
					float parseInt2 = (float)Integer.parseInt(mapEntity.get("workSize").toString());
					 float worksize = parseInt2+size;
					 mapEntity.put("workSize", worksize);
				 }else {
					 mapEntity.put("workSize", size);
				 }
			}else {
				if(null==mapEntity.get("leatSize")) {
					mapEntity.put("leatSize",0);	
		        }
				if(null==mapEntity.get("workSize")) {
					mapEntity.put("workSize", 0);	
		        }
			}		
			if(null!=mapEntity.get("qhalfSize")) {
				int parseInt = Integer.parseInt(mapEntity.get("qhalfSize").toString());
				float size=(float) parseInt/2.0f;
				if(null!=mapEntity.get("askSize")) {					 				 
					 int parseInt2 = Integer.parseInt(mapEntity.get("askSize").toString());
					 mapEntity.put("askSize", parseInt2+size);
				}else {
					mapEntity.put("askSize", size);
				}
				if(null!=mapEntity.get("workSize")) {
					float parseInt2 = (float)Integer.parseInt(mapEntity.get("workSize").toString());
					 float worksize = parseInt2+size;
					 mapEntity.put("workSize", worksize);
				 }else {
					 mapEntity.put("workSize", size);
				 }
				
			}else {
				if(null==mapEntity.get("askSize")) {
					mapEntity.put("askSize", 0);	
		        }
				if(null==mapEntity.get("workSize")) {
					mapEntity.put("workSize",0);	
		        }
			}
			if(null==mapEntity.get("lastSize")) {
				mapEntity.put("lastSize",0);	
	        }
			if(null==mapEntity.get("endSize")) {
				mapEntity.put("endSize",0);	
	        }
			if(null==mapEntity.get("startSize")) {
				mapEntity.put("startSize",0);	
	        }
			if(null==mapEntity.get("workDate")) {
				String date = sdf.format(workTime.getWorkDate());
				String[] split = date.split("-");
				String workDate= split[0]+"-"+split[1];
				mapEntity.put("workDate",workDate);	
	        }
		}
		for(Map<String, Object> mapEntity : workTimeMonth){
			//缺勤
			WorkTime workTime1 = new WorkTime();//userName
			workTime1.setStartWorkDate(startWorkDate);
			workTime1.setEndWorkDate(endWorkDate);
			if(Float.parseFloat(String.valueOf(mapEntity.get("leatSize"))) > 0.0) {			
				workTime1.setUserName(Integer.parseInt(mapEntity.get("userName").toString()));
				workTime1.setWorkTion("2");
				List<Map<String, Object>> workTimeListByWorkTime = getWorkTimeListByWorkTime(workTime1);
				StringBuffer sb = new StringBuffer();
				for(Map<String, Object> s : workTimeListByWorkTime) {
					String date1 = s.get("work_date").toString();
					sb.append(date1+",");
				}
				mapEntity.put("leatDate",sb.toString());
			}else {
				mapEntity.put("leatDate","");
			}
			//下班未打卡
			if(Integer.parseInt(mapEntity.get("endSize").toString())>0 ) {				
				workTime1.setUserName(Integer.parseInt(mapEntity.get("userName").toString()));
				workTime1.setWorkTion("3");
				List<Map<String, Object>> workTimeListByWorkTime = getWorkTimeListByWorkTime(workTime1);
				StringBuffer sb = new StringBuffer();
				for(Map<String, Object> s : workTimeListByWorkTime) {
					String date1 = s.get("work_date").toString();
					sb.append(date1+",");
				}
				mapEntity.put("endDate",sb.toString());
			}else {
				mapEntity.put("endDate","");
			}
			//上班未打卡
			if(Integer.parseInt(mapEntity.get("startSize").toString())>0 ) {				
				workTime1.setUserName(Integer.parseInt(mapEntity.get("userName").toString()));
				workTime1.setWorkTion("4");
				List<Map<String, Object>> workTimeListByWorkTime = getWorkTimeListByWorkTime(workTime1);
				StringBuffer sb = new StringBuffer();
				for(Map<String, Object> s : workTimeListByWorkTime) {
					String date1 = s.get("work_date").toString();
					sb.append(date1+",");
				}
				mapEntity.put("startDate",sb.toString());
			}else {
				mapEntity.put("startDate","");
			}
			//迟到
			if(Integer.parseInt(mapEntity.get("lastSize").toString())>0 ) {				
				workTime1.setUserName(Integer.parseInt(mapEntity.get("userName").toString()));
				workTime1.setWorkTion("1");
				List<Map<String, Object>> workTimeListByWorkTime = getWorkTimeListByWorkTime(workTime1);
				StringBuffer sb = new StringBuffer();
				SimpleDateFormat sdt = new SimpleDateFormat("HH:mm:ss");
				for(Map<String, Object> s : workTimeListByWorkTime) {
					String date1 = s.get("work_date").toString();
					String date2 = sdt.format(sdt.parse( s.get("start_time").toString())) ;
					sb.append(date1+"("+date2+")"+",");
					
				}
				mapEntity.put("lastDate",sb.toString());
			}else {
				mapEntity.put("lastDate","");
			}
			//请假
			if(Integer.parseInt(mapEntity.get("askSize").toString())>0 ) {				
				workTime1.setUserName(Integer.parseInt(mapEntity.get("userName").toString()));
				workTime1.setWorkTion("5");
				List<Map<String, Object>> workTimeListByWorkTime = getWorkTimeListByWorkTime(workTime1);
				StringBuffer sb = new StringBuffer();
				for(Map<String, Object> s : workTimeListByWorkTime) {
					String date1 = s.get("work_date").toString();
					sb.append(date1+",");
				}
				mapEntity.put("askDate",sb.toString());
			}else {
				mapEntity.put("askDate","");
			}
		}	
		return workTimeMonth;
	}

	@Override
	public PageInfo<Map<String, Object>> getPageWorkTimeMonth(WorkTime workTime) throws Exception {
		PageHelper.startPage(workTime.getPageNum(),workTime.getLength());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		String start = format+" "+"08:31:00";		
		Date startTime = sdf.parse(start);
		workTime.setStartTime(startTime);
		if(null==workTime.getWorkDate()) {
			Date parse = sdf.parse(format);
			workTime.setWorkDate(parse);
		}
		String format2 = sdf.format( workTime.getWorkDate());
		String[] split2 = format2.split("-");
		String startdate= split2[0]+"-"+split2[1]+"-01";
		workTime.setStartWorkDate(sdf.parse(startdate));
		 Calendar cal = Calendar.getInstance();
         //设置年份
         cal.set(Calendar.YEAR,Integer.parseInt(split2[0]));
         //设置月份
         cal.set(Calendar.MONTH, Integer.parseInt(split2[1])-1);
         //获取某月最大天数
         int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
         //设置日历中月份的最大天数
         cal.set(Calendar.DAY_OF_MONTH, lastDay);
         //格式化日期
         String lastDayOfMonth = sdf.format(cal.getTime());	
         workTime.setEndWorkDate(sdf.parse(lastDayOfMonth));
		List<Map<String, Object>> workTimeMonth = workTimeMapper.getWorkTimeMonth(workTime);
		for(Map<String, Object> mapEntity : workTimeMonth){
			if(null!=mapEntity.get("chalfSize")) {
				int parseInt = Integer.parseInt(mapEntity.get("chalfSize").toString());
				float size=(float) parseInt/2.0f;
				if(null!=mapEntity.get("leatSize")) {					 					 				 
					 int parseInt2 = Integer.parseInt(mapEntity.get("leatSize").toString());
					 mapEntity.put("leatSize", parseInt2+size);
				}else {
					mapEntity.put("leatSize", size);
				}
				if(null!=mapEntity.get("workSize")) {
					float parseInt2 = (float)Integer.parseInt(mapEntity.get("workSize").toString());
					 float worksize = parseInt2+size;
					 mapEntity.put("workSize", worksize);
				 }else {
					 mapEntity.put("workSize", size);
				 }
			}else {
				if(null==mapEntity.get("leatSize")) {
					mapEntity.put("leatSize", 0);	
		        }
				if(null==mapEntity.get("workSize")) {
					mapEntity.put("workSize", 0);	
		        }
			}		
			if(null!=mapEntity.get("qhalfSize")) {
				int parseInt = Integer.parseInt(mapEntity.get("qhalfSize").toString());
				float size=(float) parseInt/2.0f;
				if(null!=mapEntity.get("askSize")) {					 				 
					 int parseInt2 = Integer.parseInt(mapEntity.get("askSize").toString());
					 mapEntity.put("askSize", parseInt2+size);
				}else {
					mapEntity.put("askSize", size);
				}
				if(null!=mapEntity.get("workSize")) {
					float parseInt2 = (float)Integer.parseInt(mapEntity.get("workSize").toString());
					 float worksize = parseInt2+size;
					 mapEntity.put("workSize", worksize);
				 }else {
					 mapEntity.put("workSize", size);
				 }				
			}else {
				if(null==mapEntity.get("askSize")) {
					mapEntity.put("askSize", 0);	
		        }
				if(null==mapEntity.get("workSize")) {
					mapEntity.put("workSize", 0);	
		        }
			}
			if(null==mapEntity.get("lastSize")) {
				mapEntity.put("lastSize", 0);	
	        }
			if(null==mapEntity.get("endSize")) {
				mapEntity.put("endSize", 0);	
	        }
			if(null==mapEntity.get("startSize")) {
				mapEntity.put("startSize", 0);	
	        }
			if(null==mapEntity.get("workDate")) {
				String date = sdf.format(workTime.getWorkDate());
				String[] split = date.split("-");
				String workDate= split[0]+"-"+split[1];
				mapEntity.put("workDate",workDate);	
	        }
		}		
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(workTimeMonth);	
		return pageInfo;
	}

	@Override
	public List<Map<String, Object>> getWorkTimeDay(WorkTime workTime) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null==workTime.getWorkDate()) {
			workTime.setWorkDate(new Date());
		}
		List<Map<String, Object>> workTimeDay = workTimeMapper.getWorkTimeDay(workTime);
		for(Map<String, Object> mapEntity : workTimeDay){
			mapEntity.put("workDate", sdf.format(workTime.getWorkDate()));
			if(null==mapEntity.get("groupSize")) {
				mapEntity.put("groupSize", 0);	
	        }
			if(null==mapEntity.get("comeSize")) {
				mapEntity.put("comeSize", 0);	
	        }
			if(null==mapEntity.get("lastSize")) {
				mapEntity.put("lastSize", 0);	
	        }
			if(null==mapEntity.get("leaveSize")) {
				mapEntity.put("leaveSize", 0);	
	        }
			if(null==mapEntity.get("askSize")) {
				mapEntity.put("askSize", 0);	
	        }
			if(null==mapEntity.get("name")) {
				mapEntity.put("name", "");	
	        }
		}
		return workTimeDay;
	}

	@Override
	public PageInfo<Map<String, Object>> getPageWorkTimeDay(WorkTime workTime) throws Exception {
		PageHelper.startPage(workTime.getPageNum(),workTime.getLength());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		String start = format+" "+"08:31:00";		
		Date startTime = sdf.parse(start);
		workTime.setStartTime(startTime);
		if(null==workTime.getWorkDate()) {
			Date parse = sdf.parse(format);
			workTime.setWorkDate(parse);
		}
		List<Map<String, Object>> workTimeDay = workTimeMapper.getWorkTimeDay(workTime);
		for(Map<String, Object> mapEntity : workTimeDay){
			mapEntity.put("workDate", sdf.format(workTime.getWorkDate()));
			if(null==mapEntity.get("groupSize")) {
				mapEntity.put("groupSize", 0);	
	        }
			if(null==mapEntity.get("comeSize")) {
				mapEntity.put("comeSize", 0);	
	        }
			if(null==mapEntity.get("lastSize")) {
				mapEntity.put("lastSize", 0);	
	        }
			if(null==mapEntity.get("leaveSize")) {
				mapEntity.put("leaveSize", 0);	
	        }
			if(null==mapEntity.get("askSize")) {
				mapEntity.put("askSize", 0);	
	        }
			if(null==mapEntity.get("name")) {
				mapEntity.put("name", "");	
	        }
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(workTimeDay);	
		return pageInfo;
	}

	@Override
	public Map<String, Object> getCountPerson(WorkTime workTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		if(null==workTime.getWorkDate()) {
			Date parse =null;
			try {
				parse = sdf.parse(format);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			workTime.setWorkDate(parse);
		}
		List<Map<String, Object>> workTimeDay = workTimeMapper.getWorkTimeDay(workTime);
		int countPersonSize=0;
		int countComeSize=0;
		int countLastSize=0;
		int countLeaveSize=0;
		int countAskSize=0;
		for(Map<String, Object> mapEntity : workTimeDay){
			mapEntity.put("workDate", sdf.format(workTime.getWorkDate()));
			if(null==mapEntity.get("groupSize")) {
				mapEntity.put("groupSize", 0);	
	        }
			if(null==mapEntity.get("comeSize")) {
				mapEntity.put("comeSize", 0);	
	        }
			if(null==mapEntity.get("lastSize")) {
				mapEntity.put("lastSize", 0);	
	        }
			if(null==mapEntity.get("leaveSize")) {
				mapEntity.put("leaveSize", 0);	
	        }
			if(null==mapEntity.get("askSize")) {
				mapEntity.put("askSize", 0);	
	        }
			if(null==mapEntity.get("name")) {
				mapEntity.put("name", "");	
	        }
			int groupId = Integer.parseInt(mapEntity.get("groupId").toString()); 
			if(groupId==18 || groupId==19 || groupId==20) {
				continue;
			}
			countPersonSize+=Integer.parseInt(mapEntity.get("groupSize").toString());
			countComeSize+=Integer.parseInt(mapEntity.get("comeSize").toString());
			countLastSize+=Integer.parseInt(mapEntity.get("lastSize").toString());
			countLeaveSize+=Integer.parseInt(mapEntity.get("leaveSize").toString());
			countAskSize+=Integer.parseInt(mapEntity.get("askSize").toString());
		}
		Map<String, Object> countPerson = new LinkedHashMap<String, Object>();
		countPerson.put("应到人数：", countPersonSize);
		countPerson.put("实到人数：", countComeSize);
		countPerson.put("缺勤人数：", countLastSize);
		countPerson.put("迟到人数：", countLeaveSize);
		countPerson.put("请假人数：", countAskSize);
		return countPerson;
	}

	@Override
	public Map<String, Object> getCountGroupOne(WorkTime workTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		if(null==workTime.getWorkDate()) {
			Date parse =null;
			try {
				parse = sdf.parse(format);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			workTime.setWorkDate(parse);
		}
		List<Map<String, Object>> workTimeDay = workTimeMapper.getWorkTimeDay(workTime);
		int countPersonSize=0;
		int countComeSize=0;
		int countLastSize=0;
		int countLeaveSize=0;
		int countAskSize=0;
		for(Map<String, Object> mapEntity : workTimeDay){
			if("1".equals(mapEntity.get("groupId").toString()) || "2".equals(mapEntity.get("groupId").toString())	
				|| "3".equals(mapEntity.get("groupId").toString()) || "4".equals(mapEntity.get("groupId").toString())
				|| "5".equals(mapEntity.get("groupId").toString()) || "6".equals(mapEntity.get("groupId").toString())
				|| "7".equals(mapEntity.get("groupId").toString()) || "8".equals(mapEntity.get("groupId").toString())
				|| "9".equals(mapEntity.get("groupId").toString()) || "10".equals(mapEntity.get("groupId").toString())) 
			{
				mapEntity.put("workDate", sdf.format(workTime.getWorkDate()));
				if(null==mapEntity.get("groupSize")) {
					mapEntity.put("groupSize", 0);	
		        }
				if(null==mapEntity.get("comeSize")) {
					mapEntity.put("comeSize", 0);	
		        }
				if(null==mapEntity.get("lastSize")) {
					mapEntity.put("lastSize", 0);	
		        }
				if(null==mapEntity.get("leaveSize")) {
					mapEntity.put("leaveSize", 0);	
		        }
				if(null==mapEntity.get("askSize")) {
					mapEntity.put("askSize", 0);	
		        }
				if(null==mapEntity.get("name")) {
					mapEntity.put("name", "");	
		        }
				countPersonSize+=Integer.parseInt(mapEntity.get("groupSize").toString());
				countComeSize+=Integer.parseInt(mapEntity.get("comeSize").toString());
				countLastSize+=Integer.parseInt(mapEntity.get("lastSize").toString());
				countLeaveSize+=Integer.parseInt(mapEntity.get("leaveSize").toString());
				countAskSize+=Integer.parseInt(mapEntity.get("askSize").toString());
			}else {
				continue;
			}			
		}
		Map<String, Object> countPerson = new LinkedHashMap<String, Object>();
		countPerson.put("应到人数：", countPersonSize);
		countPerson.put("实到人数：", countComeSize);
		countPerson.put("缺勤人数：", countLastSize);
		countPerson.put("迟到人数：", countLeaveSize);
		countPerson.put("请假人数：", countAskSize);
		return countPerson;
	}

	@Override
	public Map<String, Object> getCountGroupTwo(WorkTime workTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(new Date());
		if(null==workTime.getWorkDate()) {
			Date parse =null;
			try {
				parse = sdf.parse(format);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			workTime.setWorkDate(parse);
		}
		List<Map<String, Object>> workTimeDay = workTimeMapper.getWorkTimeDay(workTime);
		int countPersonSize=0;
		int countComeSize=0;
		int countLastSize=0;
		int countLeaveSize=0;
		int countAskSize=0;
		for(Map<String, Object> mapEntity : workTimeDay){
			if( "11".equals(mapEntity.get("groupId").toString()) ||"12".equals(mapEntity.get("groupId").toString()) || "13".equals(mapEntity.get("groupId").toString())	
					|| "14".equals(mapEntity.get("groupId").toString()) || "15".equals(mapEntity.get("groupId").toString()) ) 
			{
				mapEntity.put("workDate", sdf.format(workTime.getWorkDate()));
				if(null==mapEntity.get("groupSize")) {
					mapEntity.put("groupSize", 0);	
		        }
				if(null==mapEntity.get("comeSize")) {
					mapEntity.put("comeSize", 0);	
		        }
				if(null==mapEntity.get("lastSize")) {
					mapEntity.put("lastSize", 0);	
		        }
				if(null==mapEntity.get("leaveSize")) {
					mapEntity.put("leaveSize", 0);	
		        }
				if(null==mapEntity.get("askSize")) {
					mapEntity.put("askSize", 0);	
		        }
				if(null==mapEntity.get("name")) {
					mapEntity.put("name", "");	
		        }
				countPersonSize+=Integer.parseInt(mapEntity.get("groupSize").toString());
				countComeSize+=Integer.parseInt(mapEntity.get("comeSize").toString());
				countLastSize+=Integer.parseInt(mapEntity.get("lastSize").toString());
				countLeaveSize+=Integer.parseInt(mapEntity.get("leaveSize").toString());
				countAskSize+=Integer.parseInt(mapEntity.get("askSize").toString());
			}else {
				continue;
			}
			
		}
		Map<String, Object> countPerson = new LinkedHashMap<String, Object>();
		countPerson.put("应到人数：", countPersonSize);
		countPerson.put("实到人数：", countComeSize);
		countPerson.put("缺勤人数：", countLastSize);
		countPerson.put("迟到人数：", countLeaveSize);
		countPerson.put("请假人数：", countAskSize);
		return countPerson;
	}

	@Override
	public int updateWorkTimeGroup(WorkTime workTime) {
		return workTimeMapper.updateWorkTimeGroup(workTime);
	}

	@Override
	public String getLevaeDate(WorkTime workTime) {
		List<String> levaeDate = workTimeMapper.getLevaeDate(workTime);
		String leave="";
		if(levaeDate.size()>0) {
			for(String s:levaeDate) {
				leave+=s+",";
			}
		}
		return leave;
	}

	@Override
	public String getLastDate(WorkTime workTime) {
		List<String> lastDate = workTimeMapper.getLastDate(workTime);
		String last="";
		if(lastDate.size()>0) {
			for(String s:lastDate) {
				last+=s+",";
			}
		}
		return last;
	}

	@Override
	public String getStartDate(WorkTime workTime) {
		List<String> startDate = workTimeMapper.getStartDate(workTime);
		String start="";
		if(startDate.size()>0) {
			for(String s:startDate) {
				start+=s+",";
			}
		}
		return start;
	}

	@Override
	public String getEndDate(WorkTime workTime) {
		List<String> endDate = workTimeMapper.getEndDate(workTime);
		String end="";
		if(endDate.size()>0) {
			for(String s:endDate) {
				end+=s+",";
			}
		}
		return end;
	}

	@Override
	public String getAskDate(WorkTime workTime) {
		List<String> askDate = workTimeMapper.getAskDate(workTime);
		String ask="";
		if(askDate.size()>0) {
			for(String s:askDate) {
				ask+=s+",";
			}
		}
		return ask;
	}

	@Override
	public void updateWorkTimeHeadDay(String addredd, String monthday) throws Exception {
		List<Map<String, Object>> workTimeByDate = new ArrayList<Map<String, Object>>();
	
			 ZkemSDKUtil zkm = new ZkemSDKUtil();
				zkm.connect(addredd, 4370);
				boolean readGeneralLogData=false;
				readGeneralLogData = zkm.readGeneralLogData();
				if(readGeneralLogData) {
				workTimeByDate = zkm.getWorkTimeByDateNum("1",monthday);
				}else {
					logger.debug("缓存失败");
				}
			if(workTimeByDate.size()!=0) {
				List<WorkTime> worktimelist = new ArrayList<WorkTime>();
				for(int i=0;i<workTimeByDate.size();i++ ) {
					WorkTime worktime = new WorkTime();
					Map<String, Object> map = workTimeByDate.get(i);
					worktime.setStation(1);
					int parseInt = Integer.parseInt(map.get("EnrollNumber").toString());
					if(parseInt==1) {
						continue;
					}
					User user = userMapper.getUserByuname(parseInt);
					if(user==null) {							
						/*worktime.setUserName(Integer.parseInt(map.get("EnrollNumber").toString()));
						worktime.setuName(map.get("Name").toString());*/
						continue;
					}else {
						worktime.setUserName(Integer.parseInt(user.getUsername()));
						worktime.setuName(user.getName());
						worktime.setGroupId(user.getGroupId());
					}
					String startTime1 = map.get("startTime").toString();
					String endTime1 = map.get("endTime").toString();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if(null!=startTime1 &&!"".equals(startTime1)) {
							worktime.setStartTime(sdf.parse(startTime1));	
						}else {
							worktime.setStartTime(null);
						}
						if(null!=endTime1 &&!"".equals(endTime1)) {
							worktime.setEndTime(sdf.parse(endTime1));	
						}else {
							worktime.setEndTime(null);
						}
						worktime.setCreateDate(new Date());
						String format = map.get("WorkDate").toString();
						worktime.setWorkDate(sd.parse(format));
					} catch (ParseException e) {
						logger.error("数据类型转换异常", e);
						e.printStackTrace();
					}
						WorkTime workTimeByUname = workTimeMapper.getWorkTimeByUname(worktime);
						if(null!=workTimeByUname) {
							if(workTimeByUname.getStartTime()==null && worktime.getStartTime() !=null) {
								worktime.setId(workTimeByUname.getId());
								workTimeMapper.updateWorkTimeStart(worktime);
							}
							continue;
						}
					worktimelist.add(worktime);
				}
				 workTimeMapper.saveWorkTimeList(worktimelist);				
			}else {
				logger.debug("获取信息失败"+new Date());
			}	
			
		
		ZkemSDKUtil.disConnect();
		
	}

	@Override
	public void updateWorkTimeHeadEndTime(String addredd, String month, String station) throws Exception {
		List<Map<String, Object>> workTimeByDate = new ArrayList<Map<String, Object>>();
			 ZkemSDKUtil zkm = new ZkemSDKUtil();
				zkm.connect(addredd, 4370);
				boolean readGeneralLogData=false;
				readGeneralLogData = zkm.readGeneralLogData();
				if(readGeneralLogData) {
				workTimeByDate = zkm.getWorkTimeByDateNum("2",month);
				}				
			if(workTimeByDate.size()!=0) {
				List<WorkTime> worktimelist = new ArrayList<WorkTime>();
				for(int i=0;i<workTimeByDate.size();i++ ) {
					WorkTime worktime = new WorkTime();
					Map<String, Object> map = workTimeByDate.get(i);							
					worktime.setUserName(Integer.parseInt(map.get("EnrollNumber").toString()));
					worktime.setuName(map.get("Name").toString());
					String startTime1 = map.get("startTime").toString();
					String endTime1 = map.get("endTime").toString();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					try {
						if(null!=startTime1 &&!"".equals(startTime1)) {
							worktime.setStartTime(sdf.parse(startTime1));	
						}else {
							worktime.setStartTime(null);
						}
						if(null!=endTime1 &&!"".equals(endTime1)) {
							worktime.setEndTime(sdf.parse(endTime1));	
						}else {
							worktime.setEndTime(null);
						}
						String format = map.get("WorkDate").toString();
						worktime.setCreateDate(new Date());
						worktime.setWorkDate(sd.parse(format));
						WorkTime workTimeByUname = workTimeMapper.getWorkTimeByUname(worktime);
						if(null==workTimeByUname) {
							continue;
						}								
						worktime.setId(workTimeByUname.getId());
						if("12".equals(station)) {
							if(workTimeByUname.getStartTime()!=null && worktime.getStartTime() ==null) {
								continue;
							}
						}
						if("7".equals(station)) {
							if(workTimeByUname.getEndTime()!=null && worktime.getEndTime() ==null) {
								continue;
							}									
						}
					} catch (ParseException e) {
						logger.error("异常", e);
						e.printStackTrace();
					}
					worktimelist.add(worktime);
				}
				if("12".equals(station)) {
					workTimeMapper.updateWorkTimeListStart(worktimelist);
				}else if("7".equals(station)) {
					workTimeMapper.updateWorkTimeListEnd(worktimelist);
				}
				 					
			}else {
				logger.debug("获取信息失败"+new Date());
			}	
		ZkemSDKUtil.disConnect();
		
	}

	

}
