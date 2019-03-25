package com.picc.entity;

import java.util.Date;

import com.picc.common.BaseModel;

/**
 * 组别信息
 * 
 * @author TripodFan
 * @date 2018/10/24
 */
public class Group extends BaseModel {

	    /**
	 * 
	 */
	private static final long serialVersionUID = -1472907345023747538L;

		private Integer id;

	    private String groupName;

	    private Integer areaId;

	    private String state;

	    private String createdName;

	    private Date createdDate;

	    private Integer createdId;

	    private Integer updateId;

	    private String updateName;

	    private Date updateTime;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getGroupName() {
	        return groupName;
	    }

	    public void setGroupName(String groupName) {
	        this.groupName = groupName;
	    }

	    public Integer getAreaId() {
	        return areaId;
	    }

	    public void setAreaId(Integer areaId) {
	        this.areaId = areaId;
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state;
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

	    public Integer getCreatedId() {
	        return createdId;
	    }

	    public void setCreatedId(Integer createdId) {
	        this.createdId = createdId;
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
