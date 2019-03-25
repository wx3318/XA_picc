package com.picc.entity;

import java.util.Date;

import com.picc.common.BaseModel;

/**
 * 角色功能关联表
 * 
 * @author TripodFan
 * @date 2018/8/28
 */
public class BseRoleFunction extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2482415144541213199L;

	private Integer id;

    private Integer roleId;

    private Integer functionId;

    private Date createdDate;

    private String createdName;

    private Integer createdId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public Integer getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }

  
}