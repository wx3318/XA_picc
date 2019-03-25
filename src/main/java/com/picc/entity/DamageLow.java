package com.picc.entity;

import java.util.Date;

/**
 * 核损低碳
 * @author wangXi
 * @date 2019/02/26
 *
 */
public class DamageLow {
	private Integer id;
	//定损Id
	private String damageId;
	//报案号
	private String reportNumber;
	//承包代码
	private String underwritingCode;
	//车牌号
	private String plateNumber;	
	//定损员代码
	private String damageCode;
	//定损员姓名
	private String damageName;
	//定损金额
	private String damageMoney;
	//核损人
	private String damageNuclear;
	//换件金额
	private String damageNumberMoney;
	//低碳修复企业
	private String repairLowCode;
	//低碳修复企业名字
	private String repairLowName;
	//是否低碳
	private String isLow;
	//减损金额
	private String damageLoseMoney;
	//低碳金额
	private String damageLowMoney;
	//低碳件数
	private String damageLowNumber;	
	//修理厂类别
	private String repairLeve;
	//低碳时间
	private Date damageLowDate;
	
	public Date getDamageLowDate() {
		return damageLowDate;
	}
	public void setDamageLowDate(Date damageLowDate) {
		this.damageLowDate = damageLowDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDamageId() {
		return damageId;
	}
	public void setDamageId(String damageId) {
		this.damageId = damageId;
	}
	public String getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	public String getUnderwritingCode() {
		return underwritingCode;
	}
	public void setUnderwritingCode(String underwritingCode) {
		this.underwritingCode = underwritingCode;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
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
	public String getDamageMoney() {
		return damageMoney;
	}
	public void setDamageMoney(String damageMoney) {
		this.damageMoney = damageMoney;
	}
	public String getDamageNuclear() {
		return damageNuclear;
	}
	public void setDamageNuclear(String damageNuclear) {
		this.damageNuclear = damageNuclear;
	}
	public String getDamageNumberMoney() {
		return damageNumberMoney;
	}
	public void setDamageNumberMoney(String damageNumberMoney) {
		this.damageNumberMoney = damageNumberMoney;
	}
	public String getRepairLowCode() {
		return repairLowCode;
	}
	public void setRepairLowCode(String repairLowCode) {
		this.repairLowCode = repairLowCode;
	}
	public String getRepairLowName() {
		return repairLowName;
	}
	public void setRepairLowName(String repairLowName) {
		this.repairLowName = repairLowName;
	}
	public String getIsLow() {
		return isLow;
	}
	public void setIsLow(String isLow) {
		this.isLow = isLow;
	}
	public String getDamageLoseMoney() {
		return damageLoseMoney;
	}
	public void setDamageLoseMoney(String damageLoseMoney) {
		this.damageLoseMoney = damageLoseMoney;
	}
	public String getDamageLowMoney() {
		return damageLowMoney;
	}
	public void setDamageLowMoney(String damageLowMoney) {
		this.damageLowMoney = damageLowMoney;
	}
	public String getDamageLowNumber() {
		return damageLowNumber;
	}
	public void setDamageLowNumber(String damageLowNumber) {
		this.damageLowNumber = damageLowNumber;
	}
	public String getRepairLeve() {
		return repairLeve;
	}
	public void setRepairLeve(String repairLeve) {
		this.repairLeve = repairLeve;
	}
	@Override
	public String toString() {
		return "DamageLow [id=" + id + ", damageId=" + damageId + ", reportNumber=" + reportNumber
				+ ", underwritingCode=" + underwritingCode + ", plateNumber=" + plateNumber + ", damageCode="
				+ damageCode + ", damageName=" + damageName + ", damageMoney=" + damageMoney + ", damageNuclear="
				+ damageNuclear + ", damageNumberMoney=" + damageNumberMoney + ", repairLowCode=" + repairLowCode
				+ ", repairLowName=" + repairLowName + ", isLow=" + isLow + ", damageLoseMoney=" + damageLoseMoney
				+ ", damageLowMoney=" + damageLowMoney + ", damageLowNumber=" + damageLowNumber + ", repairLeve="
				+ repairLeve + ", damageLowDate=" + damageLowDate + "]";
	}
	
}
