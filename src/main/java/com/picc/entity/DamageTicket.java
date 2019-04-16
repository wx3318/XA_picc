package com.picc.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功效 损核
 * @author wangXi
 * @date 2019/02/28
 */
public class DamageTicket {
	private Integer id;
	//id
	private String damageTicketId;
	//报案号
	private String reportNumber;
	//换件件数
	private String damageTicketNumber;
	//换件金额
	private BigDecimal damageTicketMoney;
	//工时金额
	private BigDecimal damageTimeMoney;
	//定损员代码
	private String damageCode;
	//定损员姓名
	private String damageName;
	//修理厂代码
	private String repairCode;
	//修理厂名字
	private String repairName;
	//修理厂类别
	private String repairLeve;
	//定损完成时间
	private Date damageTicketDate;
	public BigDecimal getDamageTicketMoney() {
		return damageTicketMoney;
	}
	public void setDamageTicketMoney(BigDecimal damageTicketMoney) {
		this.damageTicketMoney = damageTicketMoney;
	}
	public BigDecimal getDamageTimeMoney() {
		return damageTimeMoney;
	}
	public void setDamageTimeMoney(BigDecimal damageTimeMoney) {
		this.damageTimeMoney = damageTimeMoney;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDamageTicketId() {
		return damageTicketId;
	}
	public void setDamageTicketId(String damageTicketId) {
		this.damageTicketId = damageTicketId;
	}
	public String getReportNumber() {
		return reportNumber;
	}
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
	public String getDamageTicketNumber() {
		return damageTicketNumber;
	}
	public void setDamageTicketNumber(String damageTicketNumber) {
		this.damageTicketNumber = damageTicketNumber;
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
	public Date getDamageTicketDate() {
		return damageTicketDate;
	}
	public void setDamageTicketDate(Date damageTicketDate) {
		this.damageTicketDate = damageTicketDate;
	}
	
}
