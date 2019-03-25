package com.picc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
/**
 * 
 * @author w
 *
 */
public class ZkemSDKUtil extends Thread{
	private static ActiveXComponent zkem = new ActiveXComponent("zkemkeeper.ZKEM.1");
	private final static Logger logger = Logger.getLogger(ZkemSDKUtil.class);	
	 /**
     * 连接考勤机
     *
     * @param address 考勤机地址
     * @param port    端口号
     * @return
     */
    public boolean connect(String address, int port) {
        boolean result = zkem.invoke("Connect_NET", address, port).getBoolean();
        return result;
    }
    /**
     * 读取考勤记录到pc缓存。配合getGeneralLogData使用
     *
     * @return
     */
    public boolean readGeneralLogData() {
        boolean result = zkem.invoke("ReadAllGLogData", 1).getBoolean();
        return result;
    }
    
    /**
     * 断开考勤机链接
     */
    public static void disConnect() {
        zkem.invoke("Disconnect");
    }

    
    /**
	 * 获取用户信息
	 * @return 返回的Map中，包含以下键值:
	 * 	"EnrollNumber"  人员编号
		"Name"          人员姓名
		"Password"      人员密码
		"Privilege"
		"Enabled"       是否启用
	 */
	public static  List<Map<String,Object>> getUserInfo(){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		//将用户数据读入缓存中。
		boolean result = zkem.invoke("ReadAllUserID",1).getBoolean();
		Variant v0 = new Variant(1);
		Variant sdwEnrollNumber = new Variant("",true);
		Variant sName = new Variant("",true);
		Variant sPassword = new Variant("",true);
		Variant iPrivilege = new Variant(0,true);
		Variant bEnabled = new Variant(false,true);
		
		while(result)
		{	
			//从缓存中读取一条条的用户数据
			result = zkem.invoke("SSR_GetAllUserInfo", v0,sdwEnrollNumber,sName,sPassword,iPrivilege,bEnabled).getBoolean(); 
			//如果没有编号，跳过。
			String enrollNumber = sdwEnrollNumber.getStringRef();
			if(enrollNumber == null || enrollNumber.trim().length() == 0) {
				continue;			
			}	
			//由于名字后面会产生乱码，所以这里采用了截取字符串的办法把后面的乱码去掉了，以后有待考察更好的办法。
			//只支持2位、3位、4位长度的中文名字。
			String name = "";
			if(sName.getStringRef().getBytes().length == 9 || sName.getStringRef().getBytes().length == 8)
			{
				name = sName.getStringRef().substring(0,3);
			}else if(sName.getStringRef().getBytes().length == 7 || sName.getStringRef().getBytes().length == 6)
			{
				name = sName.getStringRef().substring(0,2);
			}else if(sName.getStringRef().getBytes().length == 11 || sName.getStringRef().getBytes().length == 10)
			{
				name = sName.getStringRef().substring(0,4);
			}else if(sName.getStringRef().getBytes().length == 16){
				name = sName.getStringRef().substring(0,3);
			}else {
				name = sName.getStringRef().substring(0,4);
			}
			
			//如果没有名字，跳过。
			if(name.trim().length() == 0) {
				continue;			
			}	
			Map<String,Object> m = new HashMap<String, Object>();
			m.put("EnrollNumber", enrollNumber);
			m.put("Name", name);
			m.put("Password", sPassword.getStringRef());
			m.put("Privilege", iPrivilege.getIntRef());
			m.put("Enabled", bEnabled.getBooleanRef());			
			resultList.add(m);
		}
		return resultList;
	}
    
    
    
	/**
	 * 获取缓存中的考勤数据。配合readGeneralLogData 使用。
	 * @return 返回的map中，包含以下键值：
	 	"EnrollNumber"   人员编号
		"Time"           考勤时间串，格式: yyyy-MM-dd HH:mm:ss
		"VerifyMode"
		"InOutMode"
		"Year"          考勤时间：年
		"Month"         考勤时间：月
		"Day"           考勤时间：日
		"Hour"			考勤时间：时
		"Minute"		考勤时间：分
		"Second"		考勤时间：秒
	 */
	public static List<Map<String,Object>> getGeneralLogData(String monthday){
		Variant v0 = new Variant(1);
		Variant dwEnrollNumber = new Variant("",true);
		Variant dwVerifyMode = new Variant(0,true);
		Variant dwInOutMode = new Variant(0,true);
		Variant dwYear = new Variant(0,true);
		Variant dwMonth = new Variant(0,true);
		Variant dwDay = new Variant(0,true);
		Variant dwHour = new Variant(0,true);
		Variant dwMinute = new Variant(0,true);
		Variant dwSecond = new Variant(0,true);
		Variant dwWorkCode = new Variant(0,true);
		List<Map<String,Object>> strList = new ArrayList<Map<String,Object>>();
		boolean newresult = false;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		do{
			Variant   vResult = Dispatch.call(zkem, "SSR_GetGeneralLogData", v0,dwEnrollNumber,dwVerifyMode,dwInOutMode,dwYear,dwMonth,dwDay,dwHour,
					dwMinute,dwSecond,dwWorkCode);	
			newresult = vResult.getBoolean();
			 String month = dwMonth.getIntRef() + "";
             String day = dwDay.getIntRef() + "";
             if (dwMonth.getIntRef() < 10) {
                 month = "0" + dwMonth.getIntRef();
             }
             if (dwDay.getIntRef() < 10) {
                 day = "0" + dwDay.getIntRef();
             }
             String validDate = dwYear.getIntRef() + "-" + month + "-" + day;
            // System.out.println(validDate);
			if(newresult)
			{
				String enrollNumber = dwEnrollNumber.getStringRef();				
				//如果没有编号，则跳过。
				if(enrollNumber == null || enrollNumber.trim().length() == 0) {
					continue;
				}	
				//日期参数
				if (monthday.equals(validDate) ) {
					Map<String,Object> m = new HashMap<String, Object>();
					/*if(Integer.parseInt(enrollNumber.toString())==10000041 || Integer.parseInt(enrollNumber.toString())==10000042 ) {
						String mt=dwMinute.getIntRef()+"";
						int parseInt = Integer.parseInt(mt);
						String ht=dwHour.getIntRef()+"";
						int minute=parseInt;
						if(Integer.parseInt(ht)<12 ) {
							minute=parseInt-5;
						}else if(Integer.parseInt(ht)>14) {
							minute=parseInt+5;
						}		
						m.put("Minute", minute);
						m.put("Time", dwYear.getIntRef() + "-" + dwMonth.getIntRef() + "-" + dwDay.getIntRef() + " " + dwHour.getIntRef() + ":" + minute + ":" + dwSecond.getIntRef());
					}else {
						m.put("Time", dwYear.getIntRef() + "-" + dwMonth.getIntRef() + "-" + dwDay.getIntRef() + " " + dwHour.getIntRef() + ":" + dwMinute.getIntRef() + ":" + dwSecond.getIntRef());
						m.put("Minute", dwMinute.getIntRef());
					}*/					
					m.put("EnrollNumber", enrollNumber);					
					m.put("VerifyMode", dwVerifyMode.getIntRef());
					m.put("InOutMode", dwInOutMode.getIntRef());
					m.put("Time", dwYear.getIntRef() + "-" + dwMonth.getIntRef() + "-" + dwDay.getIntRef() + " " + dwHour.getIntRef() + ":" + dwMinute.getIntRef() + ":" + dwSecond.getIntRef());
					m.put("Minute", dwMinute.getIntRef());
					m.put("Year", dwYear.getIntRef());
					m.put("Month", dwMonth.getIntRef());
					m.put("Day", dwDay.getIntRef());
					m.put("Hour", dwHour.getIntRef());					
					m.put("Second", dwSecond.getIntRef());
					m.put("WorkDate", validDate);
					strList.add(m);
				}
			}
		}while(newresult == true);
		return strList;
	}
	

	/**
	 * 获取缓存中的考勤数据。配合readGeneralLogDataEnd / readLastestLogData使用。
	 * @return 返回的map中，包含以下键值：
	 	"EnrollNumber"   人员编号
		"Time"           考勤时间串，格式: yyyy-MM-dd HH:mm:ss
		"VerifyMode"
		"InOutMode"
		"Year"          考勤时间：年
		"Month"         考勤时间：月
		"Day"           考勤时间：日
		"Hour"			考勤时间：时
		"Minute"		考勤时间：分
		"Second"		考勤时间：秒
	 */
	public static List<Map<String,Object>> getGeneralLogDataEnd(String monthday){
		Variant v0 = new Variant(1);
		Variant dwEnrollNumber = new Variant("",true);
		Variant dwVerifyMode = new Variant(0,true);
		Variant dwInOutMode = new Variant(0,true);
		Variant dwYear = new Variant(0,true);
		Variant dwMonth = new Variant(0,true);
		Variant dwDay = new Variant(0,true);
		Variant dwHour = new Variant(0,true);
		Variant dwMinute = new Variant(0,true);
		Variant dwSecond = new Variant(0,true);
		Variant dwWorkCode = new Variant(0,true);
		List<Map<String,Object>> strList = new ArrayList<Map<String,Object>>();
		boolean newresult = false;
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		do{
			Variant   vResult = Dispatch.call(zkem, "SSR_GetGeneralLogData", v0,dwEnrollNumber,dwVerifyMode,dwInOutMode,dwYear,dwMonth,dwDay,dwHour,
					dwMinute,dwSecond,dwWorkCode);	
			newresult = vResult.getBoolean();
			 String month = dwMonth.getIntRef() + "";
             String day = dwDay.getIntRef() + "";
             if (dwMonth.getIntRef() < 10) {
                 month = "0" + dwMonth.getIntRef();
             }
             if (dwDay.getIntRef() < 10) {
                 day = "0" + dwDay.getIntRef();
             }
             String validDate = dwYear.getIntRef() + "-" + month + "-" + day;
            // System.out.println(validDate);
			if(newresult)
			{
				String enrollNumber = dwEnrollNumber.getStringRef();				
				//如果没有编号，则跳过。
				if(enrollNumber == null || enrollNumber.trim().length() == 0) {
					continue;
				}	
				//日期参数
				if (monthday.equals(validDate) ) {
					Map<String,Object> m = new HashMap<String, Object>();
					m.put("EnrollNumber", enrollNumber);
					m.put("Time", dwYear.getIntRef() + "-" + dwMonth.getIntRef() + "-" + dwDay.getIntRef() + " " + dwHour.getIntRef() + ":" + dwMinute.getIntRef() + ":" + dwSecond.getIntRef());
					m.put("VerifyMode", dwVerifyMode.getIntRef());
					m.put("InOutMode", dwInOutMode.getIntRef());
					m.put("Year", dwYear.getIntRef());
					m.put("Month", dwMonth.getIntRef());
					m.put("Day", dwDay.getIntRef());
					m.put("Hour", dwHour.getIntRef());
					m.put("Minute", dwMinute.getIntRef());
					m.put("Second", dwSecond.getIntRef());
					m.put("WorkDate", validDate);
					strList.add(m);
				}
			}
		}while(newresult == true);
		return strList;
	}
	
	
	public  List<Map<String, Object>> getWorkTimeByDateNum(String count,String month) {
		/*74.18.15.99*/
	
			List<Map<String, Object>> allWorkTimeList =new ArrayList<Map<String, Object>>();
			 try {
						List<Map<String, Object>> userInfo =getUserInfo();
						List<Map<String,Object>> generalLogData=null;
						if("1".equals(count)) {
							generalLogData = getGeneralLogData(month);
						}else {
							generalLogData = getGeneralLogDataEnd(month);
						}						 
				        Map<String, Object> mapAll = new HashMap<String, Object>();
				        for (int i = 0; i < userInfo.size(); i++) {
				            Map<String, Object> userMap = userInfo.get(i);
				            // 用户真实数据
				            mapAll = new HashMap<String, Object>();
				            mapAll.put("EnrollNumber",userMap.get("EnrollNumber").toString());
				            mapAll.put("Name",userMap.get("Name").toString());
				            mapAll.put("Privilege",userMap.get("Privilege").toString());
				            
				            String enrollNumber1 = userMap.get("EnrollNumber").toString();
				            List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
				            for (int j = 0;  j < generalLogData.size(); j++) {
				                Map<String, Object> strMap = generalLogData.get(j);
				                mapAll.put("WorkDate",strMap.get("WorkDate").toString());
				                String enrollNumber2 =strMap.get("EnrollNumber").toString();
				                if (enrollNumber1.equals(enrollNumber2)){
				                    infoList.add(strMap);//这个人所有的打卡数据
				                }
				                if (infoList.size()==0){
				                    mapAll.put("startTime","");
				                    mapAll.put("endTime","");
				                }
				                if (infoList.size()==1){
				                	Map<String, Object> strMapt = infoList.get(0);
				                	if(Integer.parseInt(strMapt.get("Hour").toString())<=14) {
				                		String startTime = strMapt.get("Time").toString();
				                        mapAll.put("startTime",startTime);
				                        mapAll.put("endTime",""); 
				                	}else {
				                		String endTime = strMapt.get("Time").toString();
				                        mapAll.put("startTime","");
				                        mapAll.put("endTime",endTime); 
				                	}  
				                }
				                if (infoList.size()>1){
				                    Map<String, Object> strMap0 = infoList.get(0);
				                    Map<String, Object> strMap1 = infoList.get(infoList.size()-1);
				                    	if(Integer.parseInt(strMap1.get("Hour").toString())<12) {
				                    		String startTime = strMap0.get("Time").toString();
						                    mapAll.put("startTime",startTime);
						                    mapAll.put("endTime","");						                 
						                }else if(Integer.parseInt(strMap0.get("Hour").toString())>=12&&Integer.parseInt(strMap1.get("Hour").toString())<=15){
						                	String startTime = strMap0.get("Time").toString();
						                    mapAll.put("startTime",startTime);
						                    mapAll.put("endTime","");
						                }else if(Integer.parseInt(strMap0.get("Hour").toString())>=17 && Integer.parseInt(strMap1.get("Hour").toString())>=17) {
						                	String endTime = strMap1.get("Time").toString();
						                    mapAll.put("startTime","");          
						                    mapAll.put("endTime",endTime);
						                }
						                else {
						                	String startTime = strMap0.get("Time").toString();
							                String endTime =strMap1.get("Time").toString();
							                mapAll.put("startTime",startTime);
							                 mapAll.put("endTime",endTime);
						                }  			                    
				                }
				            }
				            allWorkTimeList.add(mapAll);
				        }  
			 }catch (Exception e) {
				 e.printStackTrace();
			}
			return allWorkTimeList;
		}		
	
}
