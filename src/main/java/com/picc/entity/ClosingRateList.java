package com.picc.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.picc.common.BaseModel;

/**
 * 
 * @author lx
 * 结案案件信息表
 */
public class ClosingRateList extends BaseModel
{
	private static final long serialVersionUID = 1L;
	private int id;
	private String reportNumber;//报案号
	private String registrationNumber;//立案号
	private Date riskTime;//出险时间
	private Date closingTime;//结案时间
	private String surveyor;//查勘人
	private String prospectorCode;//查勘人工号
	private String duration;//结案时长
	private String amountOfMoney;//结案金额
	private String groupName;//归属中心
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getSurveyor() {
		return surveyor;
	}
	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}
	public String getProspectorCode() {
		return prospectorCode;
	}
	public void setProspectorCode(String prospectorCode) {
		this.prospectorCode = prospectorCode;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
		ClosingRateList other = (ClosingRateList) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}

}
