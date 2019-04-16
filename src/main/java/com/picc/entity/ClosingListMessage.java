package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 查询信息
 * @author Administrator
 *
 */
public class ClosingListMessage {
	
	private Integer totalCount;//案件个数
	private Double sumMoney;//案件金额
	private Integer totalCountLast;//指定金额案件个数
	private Integer lastTotalCountLast;//同比年的案件个数
	private Double sumMoneyLast;//指定金额案件金额
	private Float nowCount;//当期天数
	private Float countYearDay;//年报天数 countYearDay
	private Float countYearDayLast;//年报同比天数countYearDayLast
	private Float countMonthDay;//月报天数countMonthDay
	private Float countMonthDayLast;//月报同比天数countMonthDayLast
	private Float countYearMonthDay;//年报月结天数countYearMonthDay
	private Float countYearMonthDayLast;//年报月结同比天数countYearMonthDayLast
	private String userName;//查勘员
	private String groupName;//机构
	private String myCaseType;//案件类型
	
	public Integer getLastTotalCountLast() {
		return lastTotalCountLast;
	}
	public void setLastTotalCountLast(Integer lastTotalCountLast) {
		this.lastTotalCountLast = lastTotalCountLast;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}
	public Float getNowCount() {
		return nowCount;
	}
	public void setNowCount(Float nowCount) {
		this.nowCount = nowCount;
	}
	public Float getCountYearDay() {
		return countYearDay;
	}
	public void setCountYearDay(Float countYearDay) {
		this.countYearDay = countYearDay;
	}
	public Float getCountYearDayLast() {
		return countYearDayLast;
	}
	public void setCountYearDayLast(Float countYearDayLast) {
		this.countYearDayLast = countYearDayLast;
	}
	public Float getCountMonthDay() {
		return countMonthDay;
	}
	public void setCountMonthDay(Float countMonthDay) {
		this.countMonthDay = countMonthDay;
	}
	public Float getCountMonthDayLast() {
		return countMonthDayLast;
	}
	public void setCountMonthDayLast(Float countMonthDayLast) {
		this.countMonthDayLast = countMonthDayLast;
	}
	public Float getCountYearMonthDay() {
		return countYearMonthDay;
	}
	public void setCountYearMonthDay(Float countYearMonthDay) {
		this.countYearMonthDay = countYearMonthDay;
	}
	public Float getCountYearMonthDayLast() {
		return countYearMonthDayLast;
	}
	public void setCountYearMonthDayLast(Float countYearMonthDayLast) {
		this.countYearMonthDayLast = countYearMonthDayLast;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getMyCaseType() {
		return myCaseType;
	}
	public void setMyCaseType(String myCaseType) {
		this.myCaseType = myCaseType;
	}
	public Integer getTotalCountLast() {
		return totalCountLast;
	}
	public void setTotalCountLast(Integer totalCountLast) {
		this.totalCountLast = totalCountLast;
	}
	public Double getSumMoneyLast() {
		return sumMoneyLast;
	}
	public void setSumMoneyLast(Double sumMoneyLast) {
		this.sumMoneyLast = sumMoneyLast;
	}
	
}
