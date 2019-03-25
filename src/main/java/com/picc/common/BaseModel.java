package com.picc.common;


import javax.persistence.Transient;
import java.io.Serializable;


public class BaseModel implements Serializable{

	@Transient
	private String order;//排序关键字
	@Transient
	private String sort; //升序  降序
	@Transient
	private Integer length; //每页显示条数
	@Transient
	private Integer start; //起始条数
	@Transient
	private Integer draw; //请求次数
	@Transient
	private Integer pageNum; //当前页数



	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		if (null != this.start){
			this.pageNum = start/length+1;
		}
		this.length = length;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		if (null != this.length){
			this.pageNum = start/this.length+1;
		}
		this.start = start;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

}
