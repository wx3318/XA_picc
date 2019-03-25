package com.picc.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置多数据源 
 * 
 * @author TripodFan
 * @date 2018/10/15
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

	public static final String  mySqlDataSource= "mySqlDataSource";  
    
    public static final String sqlServerDataSource = "sqlServerDataSource";  
    //本地线程，获取当前正在执行的currentThread  
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();   
      
    public static void setCustomerType(String customerType) {  
  
        contextHolder.set(customerType);  
      
    }  
  
    public static String getCustomerType() {  
        return contextHolder.get();       
    }  
  
    public static void clearCustomerType() {  
        contextHolder.remove();  
    }  
    
	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return getCustomerType();
	}

}
