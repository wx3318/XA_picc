package com.picc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.picc.entity.WorkTime;

public interface WorkTimeMapper {
	//批量插入打卡机数据
	int saveWorkTimeList(@Param("worktimelist")List<WorkTime> worktimelist);
	
	//单个插入
	int saveWorkTime(WorkTime workTime);
	//批量更新打卡机数据
	int updateWorkTimeListStart(@Param("worktimelist")List<WorkTime> worktimelist);
	//时间和工号获取id
	WorkTime getWorkTimeByUname(@Param("worktime")WorkTime workTime);
	//打卡统计
	List<Map<String,Object>> getWorkTimeByWorkTime(WorkTime workTime);
	//查找打卡byId
	WorkTime getWorkTimeByKey(Integer id);
	//更新天打卡详情
	int updateWorkTime(WorkTime workTime);
	//月累计
	List<Map<String,Object>> getWorkTimeMonth(WorkTime workTime);
	//天统计
	List<Map<String,Object>> getWorkTimeDay(WorkTime workTime);
	
	//员工转组时
	int updateWorkTimeGroup(WorkTime workTime);
	
	//更新下班打卡时间
	int updateWorkTimeListEnd(@Param("worktimelist")List<WorkTime> worktimelist);
	//早上打卡
	int updateWorkTimeStart(WorkTime workTime);	
	//晚上
	int updateWorkTimeEnd(WorkTime workTime);
	//缺勤的日期
	List<String> getLevaeDate(WorkTime workTime);
	//迟到的日期
	List<String> getLastDate(WorkTime workTime);
	//上班未打卡的日期
	List<String> getStartDate(WorkTime workTime);
	//下班未打卡的日期
	List<String> getEndDate(WorkTime workTime);
	//请假的日期
	List<String> getAskDate(WorkTime workTime);
	
}
