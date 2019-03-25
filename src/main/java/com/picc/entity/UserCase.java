package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.picc.common.BaseModel;

/**
 * 个人月任务
 * @author wangXi
 * @date 2018/12/29
 * 
 */
public class UserCase extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer groupId;
	
	private String groupName;
	
	private Integer userId;
	
	private String userName;
	//起始数
	private Integer startingNumber;
	//起始目标
	private Integer startTargetNumber;
	//预算目标
	private Integer budgetTargetNumber;
	//挑战目标
	private Integer challengNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getStartingNumber() {
		return startingNumber;
	}
	public void setStartingNumber(Integer startingNumber) {
		this.startingNumber = startingNumber;
	}
	public Integer getStartTargetNumber() {
		return startTargetNumber;
	}
	public void setStartTargetNumber(Integer startTargetNumber) {
		this.startTargetNumber = startTargetNumber;
	}
	public Integer getBudgetTargetNumber() {
		return budgetTargetNumber;
	}
	public void setBudgetTargetNumber(Integer budgetTargetNumber) {
		this.budgetTargetNumber = budgetTargetNumber;
	}
	public Integer getChallengNumber() {
		return challengNumber;
	}
	public void setChallengNumber(Integer challengNumber) {
		this.challengNumber = challengNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
