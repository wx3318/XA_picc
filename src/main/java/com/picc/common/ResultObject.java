package com.picc.common;

import java.io.Serializable;

/**
 * Package_Name: com.lxbtrip.common.model<br/>
 * Created_With: lxbtrip-model<br/>
 * Author: lijh<br/>
 * Created_Time: 2015-09-15 14:00<br/>
 * Description: 统一返回对象,异步请求时使用<br/>
 */
public class ResultObject implements Serializable {

	private static final long serialVersionUID = 1L;
	// success [true|false] default true
	private boolean success = true;
	// 提示信息
	private String msg;
	// 代码, 后面需要对没一个错误进行编码
	private String code = "200";
	// 返回时携带的数据
	private Object data;
	//返回时判断是否登录
	private boolean login = true;

	private Integer draw;

	private Integer recordsTotal;

	private Integer recordsFiltered;
	
	private Object spare;


	public ResultObject() {
	}

	public ResultObject(String msg) {
		this.msg = msg;
	}

	public ResultObject(Object data) {
		this.data = data;
	}

	public ResultObject(boolean result, String msg) {
		this.success = result;
		this.msg = msg;
	}

	public ResultObject(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Object getSpare() {
		return spare;
	}

	public void setSpare(Object spare) {
		this.spare = spare;
	}

	@Override
	public String toString() {
		return "ResultObj{" + "result=" + success + ", msg=" + msg + ", code=" + code + ", data=" + data + ", spare=" + spare +'}';
	}


}
