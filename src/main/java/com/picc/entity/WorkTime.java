package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.picc.common.BaseModel;

public class WorkTime extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5922662511963157964L;
	private Integer id;
	private Integer userName;
	private String uName;
	private Integer groupId;
	private String groupName;
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date endTime;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date workDate;
	private Date createDate;
	private Integer updateId;
	private String updateName;
	private Date updateDate;
	private String message;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startWorkDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endWorkDate;
	//状态
	private Integer station;
	
	private String workTion;
	public Integer getStation() {
		return station;
	}
	public void setStation(Integer station) {
		this.station = station;
	}
	public String getWorkTion() {
		return workTion;
	}
	public void setWorkTion(String workTion) {
		this.workTion = workTion;
	}
	public Date getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public Date getEndWorkDate() {
		return endWorkDate;
	}
	public void setEndWorkDate(Date endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserName() {
		return userName;
	}
	public void setUserName(Integer userName) {
		this.userName = userName;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "WorkTime [id=" + id + ", userName=" + userName + ", uName=" + uName + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", startTime=" + startTime + ", endTime=" + endTime + ", workDate="
				+ workDate + ", createDate=" + createDate + ", updateId=" + updateId + ", updateName=" + updateName
				+ ", updateDate=" + updateDate + "]";
	}
}
