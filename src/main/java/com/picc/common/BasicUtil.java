package com.picc.common;



import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package_Name: com.lxbtrip.utils.common <br/>
 * Created_With: lxbtrip-utils <br/>
 * Author: Wang <br/>
 * Created_Time: 2014-11-11 11:11 <br/>
 * Description: 基本操作工具. <br/>
 */
@SuppressWarnings("all")
public class BasicUtil {
	
	/**
	 * 接口名称 : ajaxReturnFunction <br/>
	 * 接口描述 : ajax返回方法  <br/>
	 * @param result 返回的json数据
	 * @param response 浏览器响应对象
	 * @author Wang 
	 */
	public static void ajaxReturnFunction(String result, HttpServletResponse response) {
		
		response.setContentType("application/json");
		
		PrintWriter pw = null;
		
		try {
			
			pw = response.getWriter();
					
			pw.write(result);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			pw.flush();
			
			pw.close();
			
		}
	}
	
	/**
	 * 接口名称 : ajaxReturnFunctionNotClose <br/>
	 * 接口描述 : ajax返回方法  <br/>
	 * @param result 返回的json数据
	 * @param response 浏览器响应对象
	 * @author Wang 
	 */
	public static void ajaxReturnFunctionNotClose(String result, HttpServletResponse response) {
		
		response.setContentType("application/json");
		
		PrintWriter pw = null;
		
		try {
			
			pw = response.getWriter();
					
			pw.write(result);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	
	// 阿拉伯数字转中文小写
	public static String transition(String si) {
		String[] aa = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿" };
		String[] bb = { "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] ch = si.toCharArray();
		int maxindex = ch.length;
		
		String str = "";
		
		// 字符的转换
		// 两位数的特殊转换
		if (maxindex == 2) {
			for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
				if (ch[j] != 48) {
					if (j == 0 && ch[j] == 49) {
						str += aa[i];
					} else {
						str += bb[ch[j] - 49] + aa[i];
					}
				}
			}
		}else if(maxindex == 1){
			for (int i = maxindex - 1, j = 0; i >= 0; i--, j++) {
				if (ch[j] != 48) {
					if (ch[j] == 48 && j == 0) {
						str += aa[i];
					} else {
						str += bb[ch[j] - 49] + aa[i];
					}
				}
			}
		}
		return str;
	}
	
	/**
	 * 获取随机字母数字组合
	 * @param length 字符串长度
	 * @param b 是否包含英文
	 * @return
	 */
	public static String getRandomCharAndNumr(Integer length, boolean b) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			if(b){
				b = random.nextBoolean();
			}
			if (b) { // 字符串
				// int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
				 str += (char) (65 + random.nextInt(26));// 取得大写字母
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}
	
	/**
	 * 生成?位邀请码
	 * length ?
	 * @return
	 */
	public static String getInviteCode(int length) {
		ArrayList list = new ArrayList();
		for (char c = 'a'; c <= 'z'; c++) {
			list.add(c);
		}
		String str = "";
		for (int i = 0; i < length; i++) {
			int num = (int) (Math.random() * 26);
			str = str + list.get(num);
		}
		return str.toUpperCase();
	}
	
	/**
	 * 处理字符串null<br/>
	 * true返回空字符串、false返回原字符串<br/>
	 * @author Wang
	 */
	public static String isNull(String string){
		return string == null ? "" : string;
	}

	/**
	 * 处理对象的null<br/>
	 * @param object 待处理的对象
	 * @param null_ 是否返回null
	 * @author Wang
	 */
	public static String isObjectNull(Object object, boolean null_){
		return object == null ? (null_ ? null : "") : String.valueOf(object);
	}

	/**
	 * 解析请求参数到map中
	 */
	public static TreeMap<String, Object> requestParamExtract(HttpServletRequest request){

		try {

			request.setCharacterEncoding("UTF-8");

			TreeMap<String, Object> queryMap = new TreeMap<String, Object>();

			Map map = request.getParameterMap();

			Set keSet = map.entrySet();

			for (Iterator itr = keSet.iterator(); itr.hasNext();) {
				Map.Entry me = (Map.Entry) itr.next();
				Object ok = me.getKey();
				Object ov = me.getValue();
				String[] value = new String[1];

				if (ov instanceof String[]) {
					value = (String[]) ov;
				} else {
					value[0] = ov.toString();
				}

				String val = "";
				if(value.length > 1){
					for (int k = 0; k < value.length; k++) {
						val += value[k]+",";
					}
					val = val.substring(0, val.length()-1);
				}else{
					val = value[0];
				}

				if(val != null && val.equals("")){
					continue;
				}

				queryMap.put(ok.toString(), val);
			}

			return queryMap;

		}catch (Exception ex){

			return new TreeMap<String, Object>();
		}
	}

	/**
	 * 安全的计算Integer
	 * @param num
	 * @return
	 */
	public static Integer safeParseInteger(Integer num){
		if (num == null)
			return 0;
		return num;
	}

	/**
	 * 安全的计算Integer
	 * @param num
	 * @return
	 */
	public static Short safeParseShort(Short num){
		if (num == null)
			return 0;
		return num;
	}

	/**
	 * 倍数获取
	 * @param a	源值
	 * @param b	除数
	 * @return 返回可以整出的值(如不能整出,则取差值)
	 */
	public static int[] multipleValue (int a, int b){
		int d = a / b;
		int[] result = new int[d + (a % b > 0 ? 1 : 0)];
		for (int i = 0; i < d; i++) {
			result[i] = b;
		}
		if (result.length > d) result[result.length - 1] = a - b * d;
		return result;
	}

	/**
	 * 金钱转换为数据存储
	 * @param money
	 * @return 金钱*100
	 */
	public static int  moneyFormat(String money) {
		
        BigDecimal piccPrice = new BigDecimal(money);
        int price =  piccPrice.multiply(new BigDecimal("100")).intValue();
        
    	return price;
       
	}
	
	/**
	 * 金钱转换为数据存储
	 * @param money
	 * @return 金钱*100
	 */
	public static String  bigMoneyFormat(long money) {
		
		String result;
		BigDecimal price = new BigDecimal(money);
		price = price.divide(new BigDecimal(100));
		return   price.toString();
	}
	
	/**
	 * 转换为金钱格式读取
	 * @param money
	 * @return 判断是否存在小数 
	 */
	public static String  moneyFormat(int money) {
		
		String result;
		double price = 0; 
		price = new BigDecimal(String.valueOf(money)).divide(new BigDecimal(100)).doubleValue();
		int num = 1000;
    	if (((int) price * num) == (int) (price * num)) { 
            //如果是一个整数
    		result = String.valueOf((int) price);
    		return result;
    	} else {
    		result = String.valueOf(price);
    		return result;
    	}
	}
	
	//得到本月的月份
	public static String getMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
		calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat lastDay= new SimpleDateFormat("yyyy-MM");
		return  lastDay.format(calendar.getTime());
	}
		
	//得到本月的第一天
	public static String getMonthFirstDay() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
		calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat firstDay= new SimpleDateFormat("yyyy-MM-dd");
		return  firstDay.format(calendar.getTime());
	}
	
	//得到本月的最后一天
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
		calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat lastDay= new SimpleDateFormat("yyyy-MM-dd");
		return  lastDay.format(calendar.getTime());
	}
	
	//得到今天是几号
	public static int getDayOfMonth() {

		LocalDate localDate = LocalDate.now();
		int day = localDate.getDayOfMonth();
		
		return  day;
	}
	
}
