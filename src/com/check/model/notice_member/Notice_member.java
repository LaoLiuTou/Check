package com.check.model.notice_member;
import java.util.Date;
/**
 * @author LT
 */
public class Notice_member {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	/** 公告D */
	private  Long notices_id;
	public Long getNotices_id() {
		return notices_id;
	}
	public void setNotices_id(Long notices_id) {
		this.notices_id = notices_id;
	}
	/** 标签ID */
	private  Long members_id;
	public Long getMembers_id() {
		return members_id;
	}
	public void setMembers_id(Long members_id) {
		this.members_id = members_id;
	}
	/** 是否阅读 */
	private  String read_flag;
	public String getRead_flag() {
		return read_flag;
	}
	public void setRead_flag(String read_flag) {
		this.read_flag = read_flag;
	}



}
