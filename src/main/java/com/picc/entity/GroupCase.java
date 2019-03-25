package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.picc.common.BaseModel;

/**
 * 每月任务设置（未决案件）
 * @author wangXi
 * @date 2018/12/17
 */
public class GroupCase  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer groupId;
	
	private String groupName;
	//起始数
	private Integer startingNumber;
	//起始目标
	private Integer startTargetNumber;
	//预算目标
	private Integer budgetTargetNumber;
	//挑战目标
	private Integer challengNumber;
	//初始结案数量
	private Integer endCaseNumber;
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
	public Integer getEndCaseNumber() {
		return endCaseNumber;
	}
	public void setEndCaseNumber(Integer endCaseNumber) {
		this.endCaseNumber = endCaseNumber;
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

	@Override
	public String toString() {
		return "GroupCase [id=" + id + ", groupId=" + groupId + ", groupName=" + groupName + ", startingNumber="
				+ startingNumber + ", startTargetNumber=" + startTargetNumber + ", budgetTargetNumber="
				+ budgetTargetNumber + ", challengNumber=" + challengNumber + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
	
	
}
