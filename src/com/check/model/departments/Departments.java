package com.check.model.departments;
import java.util.Date;
/**
 * @author LT
 */
public class Departments {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**  */
	private  String row_id;
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String row_id) {
		this.row_id = row_id;
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
	/** 创建者ID */
	private  Long c_id;
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	/** 部门/标签名称 */
	private  String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	/** 省/直辖市 */
	private  String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	/** 市 */
	private  String city;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/** 区/县 */
	private  String county;
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	/** 街道地址 */
	private  String street;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	/** 状态：0删除,1启用 */
	private  Long status;
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	/** 组ID */
	private  Long bu_id;
	public Long getBu_id() {
		return bu_id;
	}
	public void setBu_id(Long bu_id) {
		this.bu_id = bu_id;
	}
	/** 传真 */
	private  String cz_num;
	public String getCz_num() {
		return cz_num;
	}
	public void setCz_num(String cz_num) {
		this.cz_num = cz_num;
	}
	/** 电话 */
	private  String ph_num;
	public String getPh_num() {
		return ph_num;
	}
	public void setPh_num(String ph_num) {
		this.ph_num = ph_num;
	}

	private String b_nm_t;
	public String getB_nm_t() {
		return b_nm_t;
	}
	public void setB_nm_t(String b_nm_t) {
		this.b_nm_t = b_nm_t;
	}
	


}
