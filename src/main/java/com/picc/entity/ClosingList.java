package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.picc.common.BaseModel;

/**
 * 结案清单
 * @author lx
 *
 */
public class ClosingList extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private Integer id; //主鍵id
	
	private String reportNumber; //报案号
	
	private String registrationNumber; // 立案号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date riskTime;  // 出险时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date closingTime;  // 结案时间 
	
	private String caseType;  // 案件类型
	
	private String prospectorCode;  // 查勘人代码
	
	private String surveyor; // 查勘人
	
	private String duration; //  时长
	
	private String amountOfMoney;  //  结案金额
	
	private Integer groupId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Date getRiskTime() {
		return riskTime;
	}

	public void setRiskTime(Date riskTime) {
		this.riskTime = riskTime;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getProspectorCode() {
		return prospectorCode;
	}

	public void setProspectorCode(String prospectorCode) {
		this.prospectorCode = prospectorCode;
	}

	public String getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(String amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	@Override
	public String toString() {
		return "ClosingList [id=" + id + ", reportNumber=" + reportNumber + ", registrationNumber=" + registrationNumber
				+ ", riskTime=" + riskTime + ", closingTime=" + closingTime + ", caseType=" + caseType
				+ ", prospectorCode=" + prospectorCode + ", surveyor=" + surveyor + ", duration=" + duration
				+ ", amountOfMoney=" + amountOfMoney + ", groupId=" + groupId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClosingList other = (ClosingList) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}

	
	}
	
	

