package com.picc.entity;

import java.util.Date;

/**
 * 定损合集
 * @author wangXi
 * @date 2019/02/25
 *
 */
public class Damage {
	private Integer id;
	//报案号
	private String reportNumber;
	//定损完成时间
	private Date damageDate;
	//车牌号
	private String plateNumber;
	//车架号
	private String plateCodeNumber;
	//定损金额
	private String damageMoney;
	//损失级别
	private String damageLeve;
	//定损员代码
	private String damageCode;
	//定损员姓名
	private String damageName;
	//修理厂代码
	private String repairCode;
	//修理厂名称
	private String repairName;
	//修理厂类别
	private String repairLeve;
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
	public Date getDamageDate() {
		return damageDate;
	}
	public void setDamageDate(Date damageDate) {
		this.damageDate = damageDate;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getPlateCodeNumber() {
		return plateCodeNumber;
	}
	public void setPlateCodeNumber(String plateCodeNumber) {
		this.plateCodeNumber = plateCodeNumber;
	}
	public String getDamageMoney() {
		return damageMoney;
	}
	public void setDamageMoney(String damageMoney) {
		this.damageMoney = damageMoney;
	}
	public String getDamageLeve() {
		return damageLeve;
	}
	public void setDamageLeve(String damageLeve) {
		this.damageLeve = damageLeve;
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
	public String getRepairCode() {
		return repairCode;
	}
	public void setRepairCode(String repairCode) {
		this.repairCode = repairCode;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public String getRepairLeve() {
		return repairLeve;
	}
	public void setRepairLeve(String repairLeve) {
		this.repairLeve = repairLeve;
	}
	@Override
	public String toString() {
		return "Damage [id=" + id + ", reportNumber=" + reportNumber + ", damageDate=" + damageDate + ", plateNumber="
				+ plateNumber + ", plateCodeNumber=" + plateCodeNumber + ", damageMoney=" + damageMoney
				+ ", damageLeve=" + damageLeve + ", damageCode=" + damageCode + ", damageName=" + damageName
				+ ", repairCode=" + repairCode + ", repairName=" + repairName + ", repairLeve=" + repairLeve + "]";
	}
	
	
}
