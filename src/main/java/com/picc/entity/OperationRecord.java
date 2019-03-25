package com.picc.entity;

import java.util.Date;

import com.picc.common.BaseModel;

/**
 * 操作记录
 * 
 * @author TripodFan
 * @date 2018/9/19
 */
public class OperationRecord extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1562669307601021409L;

	private Integer id;

    private String content;

    private String type;

    private Date createdDate;

    private String createdName;

    private Integer createdId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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