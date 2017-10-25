package com.check.model.entrust_pin;
import java.util.Date;
/**
 * @author LT
 */
public class Entrust_pin {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** SIEBEL的ROW_ID */
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
	private  String c_id;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	/** 委托单ID */
	private  String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	/** 检测项ID */
	private  String jcx_id;
	public String getJcx_id() {
		return jcx_id;
	}
	public void setJcx_id(String jcx_id) {
		this.jcx_id = jcx_id;
	}
	/** 检测标识 */
	private  String jc_f;
	public String getJc_f() {
		return jc_f;
	}
	public void setJc_f(String jc_f) {
		this.jc_f = jc_f;
	}
	private String p_nm_t;
	public String getP_nm_t() {
		return p_nm_t;
	}
	public void setP_nm_t(String p_nm_t) {
		this.p_nm_t = p_nm_t;
	}
	


}
