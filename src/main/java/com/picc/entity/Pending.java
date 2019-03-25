package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.picc.common.BaseModel;
/**
 * 未决案件
 * @author wangXi
 * @date 2018/12/05
 */
public class Pending extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	//报案号
	private String reportNumber;
	//立案号
	private String registrationNumber;
	//保单号
	private String policyNumber;
	//车牌号
	private String plateNumber;
	//被保险人
	private String assured;
	//出险日期
	private Date riskDate;
	//案件性质
	private String caseCharacter;
	//承保代码（机构代码）
	private String underwritingCode;
	//估损金额
	private String assessmentLoss;
	//查勘员代码
	private String insuranceCode;
	//查勘员姓名
	private String insuranceName;
	//定损员代码
	private String damageCode;
	//定损员姓名
	private String damageName;
	//伤亡状态
	private String loss;
	//案件状态
	private String caseStation;
	//理算员代码
	private String adjusterCode;
	//理算员姓名
	private String adjusterName;
	//机构分中心代码
	private String groupId;
	//机构分中心名字
	private String groupName;
	//区域
	private String areaType;
	private Date updateDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	
	private String userInfo;
	//人员归属组别
	private String groupIded;
	
	public String getGroupIded() {
		return groupIded;
	}

	public void setGroupIded(String groupIded) {
		this.groupIded = groupIded;
	}

	@Override
	public String toString() {
		return "Pending [reportNumber=" + reportNumber + ", registrationNumber=" + registrationNumber
				+ ", policyNumber=" + policyNumber + ", plateNumber=" + plateNumber + ", assured=" + assured
				+ ", riskDate=" + riskDate + ", caseCharacter=" + caseCharacter + ", underwritingCode="
				+ underwritingCode + ", assessmentLoss=" + assessmentLoss + ", insuranceCode=" + insuranceCode
				+ ", insuranceName=" + insuranceName + ", damageCode=" + damageCode + ", damageName=" + damageName
				+ ", caseStation=" + caseStation + ", adjusterCode=" + adjusterCode + ", adjusterName=" + adjusterName
				+ ", groupId=" + groupId + ", groupName=" + groupName + "]";
	}	
	
	public String getLoss() {
		return loss;
	}
	public void setLoss(String loss) {
		this.loss = loss;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getAssured() {
		return assured;
	}
	public void setAssured(String assured) {
		this.assured = assured;
	}
	public Date getRiskDate() {
		return riskDate;
	}
	public void setRiskDate(Date riskDate) {
		this.riskDate = riskDate;
	}
	public String getCaseCharacter() {
		return caseCharacter;
	}
	public void setCaseCharacter(String caseCharacter) {
		this.caseCharacter = caseCharacter;
	}
	public String getUnderwritingCode() {
		return underwritingCode;
	}
	public void setUnderwritingCode(String underwritingCode) {
		this.underwritingCode = underwritingCode;
	}
	public String getAssessmentLoss() {
		return assessmentLoss;
	}
	public void setAssessmentLoss(String assessmentLoss) {
		this.assessmentLoss = assessmentLoss;
	}
	public String getInsuranceCode() {
		return insuranceCode;
	}
	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getDamageCode() {
		return damageCode;
	}
	public void setDamageCode(String damageCode) {
		this.damageCode = damageCode;
	}
	public String getDamageName() {
		return damageName;
	}
	public void setDamageName(String damageName) {
		this.damageName = damageName;
	}
	public String getCaseStation() {
		return caseStation;
	}
	public void setCaseStation(String caseStation) {
		this.caseStation = caseStation;
	}
	public String getAdjusterCode() {
		return adjusterCode;
	}
	public void setAdjusterCode(String adjusterCode) {
		this.adjusterCode = adjusterCode;
	}
	public String getAdjusterName() {
		return adjusterName;
	}
	public void setAdjusterName(String adjusterName) {
		this.adjusterName = adjusterName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseStation == null) ? 0 : caseStation.hashCode());
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}	
		if (obj == null) {
			return false;
		}	
		if (getClass() != obj.getClass()) {
			return false;
		}	
		Pending other = (Pending) obj;
		if (caseStation == null) {
			if (other.caseStation != null) {
				return false;
			}	
		} else if (!caseStation.equals(other.caseStation)) {
			return false;
		}	
		if (registrationNumber == null) {
			if (other.registrationNumber != null) {
				return false;
			}	
		} else if (!registrationNumber.equals(other.registrationNumber)) {
			return false;
		}	
		return true;
	}
	
	
}
