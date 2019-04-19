package com.picc.entity;

public class ClosingRateListMessage {

	private String groupName;// 归属机构
	private Integer registrationFiveNum;// 五千以下立案数
	private Integer closedFiveNum;// 五千以下结案数
	private String closedFiveRate;// 五千以下结案率
	private Integer registrationOneDownNum;// 万元以下立案数
	private Integer closedOneDownNum;// 万元以下结案数
	private String closedOneDownRate;// 万元以下结案率
	private Integer registrationOneUpNum;// 万元以上立案数
	private Integer closedOneUpNum;// 万元以上结案数
	private String closedOneUpRate;// 万元以上结案率
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getRegistrationFiveNum() {
		return registrationFiveNum;
	}
	public void setRegistrationFiveNum(Integer registrationFiveNum) {
		this.registrationFiveNum = registrationFiveNum;
	}
	public Integer getClosedFiveNum() {
		return closedFiveNum;
	}
	public void setClosedFiveNum(Integer closedFiveNum) {
		this.closedFiveNum = closedFiveNum;
	}
	public String getClosedFiveRate() {
		return closedFiveRate;
	}
	public void setClosedFiveRate(String closedFiveRate) {
		this.closedFiveRate = closedFiveRate;
	}
	public Integer getRegistrationOneDownNum() {
		return registrationOneDownNum;
	}
	public void setRegistrationOneDownNum(Integer registrationOneDownNum) {
		this.registrationOneDownNum = registrationOneDownNum;
	}
	public Integer getClosedOneDownNum() {
		return closedOneDownNum;
	}
	public void setClosedOneDownNum(Integer closedOneDownNum) {
		this.closedOneDownNum = closedOneDownNum;
	}
	public String getClosedOneDownRate() {
		return closedOneDownRate;
	}
	public void setClosedOneDownRate(String closedOneDownRate) {
		this.closedOneDownRate = closedOneDownRate;
	}
	public Integer getRegistrationOneUpNum() {
		return registrationOneUpNum;
	}
	public void setRegistrationOneUpNum(Integer registrationOneUpNum) {
		this.registrationOneUpNum = registrationOneUpNum;
	}
	public Integer getClosedOneUpNum() {
		return closedOneUpNum;
	}
	public void setClosedOneUpNum(Integer closedOneUpNum) {
		this.closedOneUpNum = closedOneUpNum;
	}
	public String getClosedOneUpRate() {
		return closedOneUpRate;
	}
	public void setClosedOneUpRate(String closedOneUpRate) {
		this.closedOneUpRate = closedOneUpRate;
	}
	
	
}
