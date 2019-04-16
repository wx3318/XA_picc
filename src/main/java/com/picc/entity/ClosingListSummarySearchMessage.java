package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 结案查询统计
 * @author lx
 *
 */
public class ClosingListSummarySearchMessage {
	
//		private Integer userId;//按姓名
		private Integer groupId;//按机构
		private String year;//开始时间
		private String  month;//结束时间
//		private String closingListCaseType;//案件类型
		private String moneyType;//金额范围
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getMoneyType() {
			return moneyType;
		}
		public void setMoneyType(String moneyType) {
			this.moneyType = moneyType;
		}
		public Integer getGroupId() {
			return groupId;
		}
		public void setGroupId(Integer groupId) {
			this.groupId = groupId;
		}
		
		
		
		
		
		
	
		
}
