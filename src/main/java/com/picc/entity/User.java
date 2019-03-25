package com.picc.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.picc.common.BaseModel;


public class User  extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;
	//营销工号
	private String username;
	private String password;
	private String name;
	private String role;
	private Integer groupId;
	private String group;
	
	private String sex;
	private String phone;
	private String id_card;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date jion_work;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start_work;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date end_work;
	//在职状态
	private String work_mes;
	//岗位
	private String station;
	//合同
	private String contract;
	//营销号
	private String yx_id;
	//外部显示
	private String phone_show;
	private String education;
	private String j_phone;
	private String address;
	private String message;
	
	private String isDelete;

    private Integer createdId;
    
    private String createdName;

    private Date createdDate;

    private Integer updateId;

    private String updateName;

    private Date updateTime;
	
    private Integer roleId;
    
    private Date groupDate;
    
    private Date mesDate;
    
    private Date mesDateEnd;
    
    private Date startDate;
    
    private Date startDateEnd;
    
    private Date endDate;
    
    private Date endDateEnd;
	
	public Date getMesDateEnd() {
		return mesDateEnd;
	}
	public void setMesDateEnd(Date mesDateEnd) {
		this.mesDateEnd = mesDateEnd;
	}
	public Date getStartDateEnd() {
		return startDateEnd;
	}
	public void setStartDateEnd(Date startDateEnd) {
		this.startDateEnd = startDateEnd;
	}
	public Date getEndDateEnd() {
		return endDateEnd;
	}
	public void setEndDateEnd(Date endDateEnd) {
		this.endDateEnd = endDateEnd;
	}
	public Date getMesDate() {
		return mesDate;
	}
	public void setMesDate(Date mesDate) {
		this.mesDate = mesDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getGroupDate() {
		return groupDate;
	}
	public void setGroupDate(Date groupDate) {
		this.groupDate = groupDate;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getCreatedId() {
		return createdId;
	}
	public void setCreatedId(Integer createdId) {
		this.createdId = createdId;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public Date getJion_work() {
		return jion_work;
	}
	public void setJion_work(Date jion_work) {
		this.jion_work = jion_work;
	}
	public Date getStart_work() {
		return start_work;
	}
	public void setStart_work(Date start_work) {
		this.start_work = start_work;
	}
	public Date getEnd_work() {
		return end_work;
	}
	public void setEnd_work(Date end_work) {
		this.end_work = end_work;
	}
	public String getWork_mes() {
		return work_mes;
	}
	public void setWork_mes(String work_mes) {
		this.work_mes = work_mes;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getYx_id() {
		return yx_id;
	}
	public void setYx_id(String yx_id) {
		this.yx_id = yx_id;
	}
	public String getPhone_show() {
		return phone_show;
	}
	public void setPhone_show(String phone_show) {
		this.phone_show = phone_show;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getJ_phone() {
		return j_phone;
	}
	public void setJ_phone(String j_phone) {
		this.j_phone = j_phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public User(String name,String role, String group, String sex,String id_card,String phone,
			 String work_mes, String station, String contract,String username ,String  yx_id,
			String phone_show, String education, String j_phone, String address, String message) {
		super();
		this.username = username;
		this.name = name;
		this.role = role;
		this.group = group;
		this.sex = sex;
		this.phone = phone;
		this.id_card = id_card;
	/*	this.jion_word = jion_word;
		this.start_word = start_word;*/
		this.work_mes = work_mes;
		this.station = station;
		this.contract = contract;
		this.yx_id = yx_id;
		this.phone_show = phone_show;
		this.education = education;
		this.j_phone = j_phone;
		this.address = address;
		this.message = message;
	}
	public User(int user_id, String username, String password, String name, String role, String group, String sex, String phone,
			String id_card, Date jion_work, Date start_work, Date end_work, String work_mes, String station,
			String contract, String yx_id, String phone_show, String education, String j_phone, String address,
			String message) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.group = group;
		this.sex = sex;
		this.phone = phone;
		this.id_card = id_card;
		this.jion_work = jion_work;
		this.start_work = start_work;
		this.end_work = end_work;
		this.work_mes = work_mes;
		this.station = station;
		this.contract = contract;
		this.yx_id = yx_id;
		this.phone_show = phone_show;
		this.education = education;
		this.j_phone = j_phone;
		this.address = address;
		this.message = message;
	}
	public User(int user_id, String username, String password, String name) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	public User(int user_id, String username, String password, String role, String group) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.group = group;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", name=" + name + ", role="
				+ role + ", group=" + group + ", sex=" + sex + ", phone=" + phone + ", id_card=" + id_card
				+ ", jion_word=" + jion_work + ", start_word=" + start_work + ", end_word=" + end_work + ", work_mes="
				+ work_mes + ", station=" + station + ", contract=" + contract + ", yx_id=" + yx_id + ", phone_show="
				+ phone_show + ", education=" + education + ", j_phone=" + j_phone + ", address=" + address
				+ ", message=" + message + "]";
	}
	
}
