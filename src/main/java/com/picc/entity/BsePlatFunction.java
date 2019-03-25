package com.picc.entity;

import java.util.Date;

import com.picc.common.BaseModel;

/**
 * 菜单功能
 * 
 * @author TripodFan
 * @date 2018/8/23
 */

public class BsePlatFunction extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090179670614911760L;
	
    private Integer id;

	private String funName;

	private String funDescription;

	private String funType;

	private Integer funPid;

	private String funUrl;

	private Integer orderNum;

	private String icon;

	private String funFlag;

	private String state;

	private String isDelete;

	private Integer createdId;

	private String createdName;

	private Date createdDate;

	private Integer updateId;

	private String updateName;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunDescription() {
		return funDescription;
	}

	public void setFunDescription(String funDescription) {
		this.funDescription = funDescription;
	}

	public String getFunType() {
		return funType;
	}

	public void setFunType(String funType) {
		this.funType = funType;
	}

	public Integer getFunPid() {
		return funPid;
	}

	public void setFunPid(Integer funPid) {
		this.funPid = funPid;
	}

	public String getFunUrl() {
		return funUrl;
	}

	public void setFunUrl(String funUrl) {
		this.funUrl = funUrl;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getFunFlag() {
		return funFlag;
	}

	public void setFunFlag(String funFlag) {
		this.funFlag = funFlag;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getCreatedId() {
		return createdId;
	}

	public void setCreatedId(Integer createdId) {
		this.createdId = createdId;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	
}