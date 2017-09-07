package com.check.model.warn;
import java.util.Date;
/**
 * @author LT
 */
public class Warn {

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
	/** 设备ID */
	private  String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	/** 提醒日期 */
	private  String tx_dt;
	public String getTx_dt() {
		return tx_dt;
	}
	public void setTx_dt(String tx_dt) {
		this.tx_dt = tx_dt;
	}
	/** 状态(未处理、已处理) */
	private  String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	/** 处理日期 */
	private  String cl_dt;
	public String getCl_dt() {
		return cl_dt;
	}
	public void setCl_dt(String cl_dt) {
		this.cl_dt = cl_dt;
	}



}
