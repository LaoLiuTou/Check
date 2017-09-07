package com.check.model.area;
import java.util.Date;
/**
 * @author LT
 */
public class Area {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**  */
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**  */
	private  String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**  */
	private  String fpage;
	public String getFpage() {
		return fpage;
	}
	public void setFpage(String fpage) {
		this.fpage = fpage;
	}
	/** 创建时间 */
	private  Date c_dt;
	public Date getC_dt() {
		return c_dt;
	}
	public void setC_dt(Date c_dt) {
		this.c_dt = c_dt;
	}
	/** 最后修改时间 */
	private  Date up_dt;
	public Date getUp_dt() {
		return up_dt;
	}
	public void setUp_dt(Date up_dt) {
		this.up_dt = up_dt;
	}



}
