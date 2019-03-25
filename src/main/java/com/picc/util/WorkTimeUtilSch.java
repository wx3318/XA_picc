package com.picc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.picc.dao.UserMapper;
import com.picc.dao.WorkTimeMapper;
import com.picc.entity.User;
import com.picc.entity.WorkTime;
import com.picc.service.WorkDayService;
/**
 * 
 * @author wx
 *
 */
@Component
public class WorkTimeUtilSch{
	private final static Logger logger = Logger.getLogger(WorkTimeUtilSch.class);
	@Autowired
	private  WorkTimeMapper workTimeMapper;
	
	@Autowired
	private  UserMapper userMapper;
	
	@Autowired
	private WorkDayService workDayService;
	/**
	 * 
	 * @param addredd
	 */
	public  void  saveWorkTimeNumStart(String addredd,String monthday) {				
		List<Map<String, Object>> workTimeByDate = new ArrayList<Map<String, Object>>();
			try {
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
				
			} catch (Exception e) {
				logger.error("连接异常", e);
			}
			ZkemSDKUtil.disConnect();

	}
	/**
	 * 
	 * @param addredd
	 */
	public  void saveWorkTimeNumEnd(String addredd,String month,String station) {
		List<Map<String, Object>> workTimeByDate = new ArrayList<Map<String, Object>>();
				try {
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
					
				} catch (Exception e) {
					logger.error("连接异常", e);
				}
				ZkemSDKUtil.disConnect();
				
	}

	/**
	 * 
	 * 续保1-5 早上九点10 ip15.98
	 */
	public void saveAddressOneStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumStart("74.18.15.98",workDayMonth);
		}
	}
	
	/**
	 * 中午
	 */
	public void updateAddressOneAfter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumEnd("74.18.15.98",workDayMonth,"12");
		}
	}
	/**
	 * 
	 * 
	 *  早上七点5分昨晚数据 ip15.98
	 */
	public void updateAddressOneEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			String dayMonthLast = workDayService.getWorkDayMonthLast(workDayMonth);
			saveWorkTimeNumEnd("74.18.15.98",dayMonthLast,"7");
		}
	}
	/**
	 * 续保6-10 早九点13 ip 15.99
	 */
	public void saveAddressTwoStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumStart("74.18.15.99",workDayMonth);
		}
	}
	
	/**
	 * 中午
	 */
	public void updateAddressTwoAfter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumEnd("74.18.15.99",workDayMonth,"12");
		}
	}
	/**
	 * 早上七点7分昨晚数据 ip 15.99
	 */
	public void updateAddressTwoEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			String dayMonthLast = workDayService.getWorkDayMonthLast(workDayMonth);
			saveWorkTimeNumEnd("74.18.15.99",dayMonthLast,"7");
		}
		
		
	}
	/**
	 * 新续转保 早九点15 ip 10.137
	 */
	public void saveAddressThreeStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumStart("74.18.15.101",workDayMonth);
		}	
	}
	
	/**
	 * 中午
	 */
	public void updateAddressThreeAfter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumEnd("74.18.15.101",workDayMonth,"12");
		}
	}
	/**
	 *  七点10分零3 ip 15.101
	 */
	public void updateAddressThreeEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			String dayMonthLast = workDayService.getWorkDayMonthLast(workDayMonth);
			saveWorkTimeNumEnd("74.18.15.101",dayMonthLast,"7");
		}
	}
	
	/**
	 * 新兵营 早九点零17 ip 1.198
	 */
	public void saveAddressFourStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumStart("74.18.15.102",workDayMonth);
		}
	}
	/**
	 * 中午
	 */
	public void updateAddressFourAfter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumEnd("74.18.15.102",workDayMonth,"12");
		}
	}
	
	/**
	 *  早上七13点零17 ip 1.198
	 */
	public void updateAddressFourEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			String dayMonthLast = workDayService.getWorkDayMonthLast(workDayMonth);
			saveWorkTimeNumEnd("74.18.15.102",dayMonthLast,"7");
		}
	}	
	/**
	 * 内勤 早九点20 ip 1.198
	 */
	public void saveAddressFiveStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumStart("74.18.1.198",workDayMonth);
		}
	}
	/**
	 * 中午
	 */
	public void updateAddressFiveAfter() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			saveWorkTimeNumEnd("74.18.1.198",workDayMonth,"12");
		}	
	}
	
	/**
	 * 早上七15点零17 ip 1.198
	 */
	public void updateAddressFiveEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String month = sdf.format(new Date());
		String workDayMonth = workDayService.getWorkDayMonth(month);
		if(null!=workDayMonth) {
			String dayMonthLast = workDayService.getWorkDayMonthLast(workDayMonth);
			saveWorkTimeNumEnd("74.18.1.198",dayMonthLast,"7");
		}	
	}
}
