package com.picc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.UserMapper;
import com.picc.entity.User;
import com.picc.service.UserService;
/**
 * 
 * @author wangXi
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;
	@Override
	public User doLogin(User user) throws Exception {
		return usermapper.doLogin(user);
	}
	@Override
	public User getUser(int id) throws Exception {	
		return usermapper.getUser(id);
	}
	@Override
	public String getGroup(int groupid) throws Exception {
		return usermapper.getGroup(groupid);
	}
	@Override
	public boolean savePassword(User user) throws Exception {
		Integer savePassword = usermapper.savePassword(user);
		if( savePassword> 0){
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean saveUser(User user) throws NumberFormatException, Exception {
			//密码初始化
			user.setPassword("123456");
			Integer saveUser = usermapper.saveUser(user);
			if(saveUser>0) {
				
				return true;
			}else {
				return false;
			}
	}
	@Override
	public User getUserByuname(int username) throws Exception {
		 return usermapper.getUserByuname(username);
		
	}
	@Override
	public void saveGroup(int group_id, int username) throws Exception {
		usermapper.saveGroup(group_id, username);
		
	}
	@Override
	public boolean saveRole_user(int user_id, int role_id) throws Exception {
		if(usermapper.saveRole_user(user_id, role_id)>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int userCount(String group,String username_,String name,String work_mes) {
		int group_id=0;
		int username=0;
		if(group!=null && group!="") {
			 group_id =Integer.parseInt(group);
		}
		if(username_!=null && username_!="") {
		 username = Integer.parseInt(username_);
		}
		return usermapper.userCount(group_id, username,name,work_mes);
	}
	@Override
	public int updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		int updateUser = usermapper.updateUser(user);
		if(updateUser>0) {
			return 1;
		}
		return 0;
	}
	@Override
	public User getUserByYxid(String yx_Id) {
		// TODO Auto-generated method stub
		return usermapper.getUserByYxid(yx_Id);
	}
	@Override
	public User getUserByIdCard(String idCard) {
		return usermapper.getUserByIdCard(idCard);
	}
	@Override
	public List<Map<String, Object>> getUserListMap(User user) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=user.getMesDate()) {
			Date mesDate = user.getMesDate();
			String format = sdf.format(mesDate);
			String[] split = format.split("-");
            String mseDate = split[0]+"-"+split[1]+"-01";
            user.setMesDate(sdf.parse(mseDate));
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,Integer.parseInt(split[0]));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(split[1])-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            String lastDayOfMonth = sdf.format(cal.getTime());
            user.setMesDateEnd(sdf.parse(lastDayOfMonth));
		}
		if(null!=user.getStartDate()) {
			Date startsDate = user.getStartDate();
			String format = sdf.format(startsDate);
			String[] split = format.split("-");
            String startDate = split[0]+"-"+split[1]+"-01";
            user.setStartDate(sdf.parse(startDate));
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,Integer.parseInt(split[0]));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(split[1])-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            String lastDayOfMonth = sdf.format(cal.getTime());
            user.setStartDateEnd(sdf.parse(lastDayOfMonth));
		}
		if(null!=user.getEndDate()) {
			Date endsDate = user.getEndDate();
			String format = sdf.format(endsDate);
			String[] split = format.split("-");
            String endDate = split[0]+"-"+split[1]+"-01";
            user.setEndDate(sdf.parse(endDate));
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,Integer.parseInt(split[0]));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(split[1])-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            String lastDayOfMonth = sdf.format(cal.getTime());
            user.setEndDateEnd(sdf.parse(lastDayOfMonth));
		}
		List<Map<String, Object>> userListMap = usermapper.getUserListMap(user);
		
		 for(Map<String, Object> mapEntity : userListMap){
				 if(null==mapEntity.get("name")) {
					 mapEntity.put("name", "");  
			     }
				 if(null==mapEntity.get("role_name")) {
					 mapEntity.put("role_name", ""); 
			     }
				 if(null==mapEntity.get("group_name")) {
					 mapEntity.put("group_name", "");  
			     }
				 if(null==mapEntity.get("sex")) {
					 mapEntity.put("sex", ""); 
			     }
				 if(null==mapEntity.get("phone")) {
					 mapEntity.put("phone", "");  
			     }
				 if(null!=mapEntity.get("jion_work")) {
					 if(!String.valueOf(mapEntity.get("jion_work")).equals("") || null!=String.valueOf(mapEntity.get("jion_work"))){
		            	 mapEntity.put("jion_work",sdf.format(sdf.parse(String.valueOf(mapEntity.get("jion_work")))));   	 
		            }  
			     }else {
			        mapEntity.put("jion_work", "");
			     }
				 if(null!=mapEntity.get("start_work")) {
					 if(!String.valueOf(mapEntity.get("start_work")).equals("") || null!=String.valueOf(mapEntity.get("start_work"))){
		            	 mapEntity.put("start_work",sdf.format(sdf.parse(String.valueOf(mapEntity.get("start_work")))));   	 
		            }  
			     }else {
			        mapEntity.put("start_work", "");
			     }
				 if(null==mapEntity.get("work_mes")) {
					 mapEntity.put("work_mes", "");  
			     }
				 if(null==mapEntity.get("station")) {
					 mapEntity.put("station", "");
			     }
				 if(null==mapEntity.get("phone_show")) {
					 mapEntity.put("phone_show", "");  
			     }
				 if(null==mapEntity.get("username")) {
					 mapEntity.put("username", "");  
			     }
				 if(null==mapEntity.get("yx_id")) {
					 mapEntity.put("yx_id", "");  
			     }
				 if(null==mapEntity.get("message")) {
					 mapEntity.put("message", "");  
			     }
				 if(null==mapEntity.get("id_card")) {
					 mapEntity.put("id_card", "");  
			     }
				 if(null==mapEntity.get("contract")) {
					 mapEntity.put("contract", "");  
			     }
				 if(null==mapEntity.get("education")) {
					 mapEntity.put("education", "");  
			     }
				 if(null==mapEntity.get("j_phone")) {
					 mapEntity.put("j_phone", "");  
			     }
				 if(null==mapEntity.get("address")) {
					 mapEntity.put("address", "");  
			     }				 
		 }
		return userListMap;
	}
	@Override
	public PageInfo<Map<String, Object>> getUserListPageMap(User user) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PageHelper.startPage(user.getPageNum(),user.getLength());
		if(null!=user.getMesDate()) {
			Date mesDate = user.getMesDate();
			String format = sdf.format(mesDate);
			String[] split = format.split("-");
            String mseDate = split[0]+"-"+split[1]+"-01";
            user.setMesDate(sdf.parse(mseDate));                      
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,Integer.parseInt(split[0]));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(split[1])-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            String lastDayOfMonth = sdf.format(cal.getTime());
            user.setMesDateEnd(sdf.parse(lastDayOfMonth));
		}
		if(null!=user.getStartDate()) {
			Date startsDate = user.getStartDate();
			String format = sdf.format(startsDate);
			String[] split = format.split("-");
            String startDate = split[0]+"-"+split[1]+"-01";
            user.setStartDate(sdf.parse(startDate));
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,Integer.parseInt(split[0]));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(split[1])-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            String lastDayOfMonth = sdf.format(cal.getTime());
            user.setStartDateEnd(sdf.parse(lastDayOfMonth));
		}
		if(null!=user.getEndDate()) {
			Date endsDate = user.getEndDate();
			String format = sdf.format(endsDate);
			String[] split = format.split("-");
            String endDate = split[0]+"-"+split[1]+"-01";
            user.setEndDate(sdf.parse(endDate));
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,Integer.parseInt(split[0]));
            //设置月份
            cal.set(Calendar.MONTH, Integer.parseInt(split[1])-1);
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            String lastDayOfMonth = sdf.format(cal.getTime());
            user.setEndDateEnd(sdf.parse(lastDayOfMonth));
		}
		List<Map<String, Object>> userListMap = usermapper.getUserListMap(user);
		
		 for(Map<String, Object> mapEntity : userListMap){
				 if(null==mapEntity.get("name")) {
					 mapEntity.put("name", "");  
			     }
				 if(null==mapEntity.get("role_name")) {
					 mapEntity.put("role_name", ""); 
			     }
				 if(null==mapEntity.get("group_name")) {
					 if(null==mapEntity.get("message")) {
						 mapEntity.put("group_name", "");   
				     }else {
				    	 mapEntity.put("group_name", mapEntity.get("message"));   
				     }
			     }
				 if(null==mapEntity.get("sex")) {
					 mapEntity.put("sex", ""); 
			     }
				 if(null==mapEntity.get("phone")) {
					 mapEntity.put("phone", "");  
			     }
				 if(null!=mapEntity.get("jion_work")) {
					 if(!String.valueOf(mapEntity.get("jion_work")).equals("") || null!=String.valueOf(mapEntity.get("jion_work"))){
		            	 mapEntity.put("jion_work",sdf.format(sdf.parse(String.valueOf(mapEntity.get("jion_work")))));   	 
		            }  
			     }else {
			        mapEntity.put("jion_work", "");
			     }
				 if(null!=mapEntity.get("start_work")) {
					 if(!String.valueOf(mapEntity.get("start_work")).equals("") || null!=String.valueOf(mapEntity.get("start_work"))){
		            	 mapEntity.put("start_work",sdf.format(sdf.parse(String.valueOf(mapEntity.get("start_work")))));   	 
		            }  
			     }else {
			        mapEntity.put("start_work", "");
			     }
				 if(null==mapEntity.get("work_mes")) {
					 mapEntity.put("work_mes", "");  
			     }
				 if(null==mapEntity.get("station")) {
					 mapEntity.put("station", "");
			     }
				 if(null==mapEntity.get("phone_show")) {
					 mapEntity.put("phone_show", "");  
			     }
				 if(null==mapEntity.get("username")) {
					 mapEntity.put("username", "");  
			     }
				 if(null==mapEntity.get("yx_id")) {
					 mapEntity.put("yx_id", "");  
			     }
				 if(null==mapEntity.get("message")) {
					 mapEntity.put("message", "");  
			     }
		 }
		 PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(userListMap);
		return pageInfo;
	}
	@Override
	public int importUser(List<User> listUser) {
		
		return usermapper.importUser(listUser);
	}
	
	/**获取符合查询条件的坐席列表
     * @param user
     * @return 坐席列表
     */
	@Override
	public List<Map<String, Object>> queryUserList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return usermapper.queryUserList(param);
	}
}
