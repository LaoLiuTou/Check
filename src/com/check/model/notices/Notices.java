package com.check.model.notices;
import java.util.Date;
/**
 * @author LT
 */
public class Notices {

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
	/** 组ID */
	private  Long bu_id;
	public Long getBu_id() {
		return bu_id;
	}
	public void setBu_id(Long bu_id) {
		this.bu_id = bu_id;
	}
	/** 标题 */
	private  String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/** 内容 */
	private  String contents;
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}


	private String choose_id;
	private String show_flag;
	public String getChoose_id() {
		return choose_id;
	}
	public void setChoose_id(String choose_id) {
		this.choose_id = choose_id;
	}
	public String getShow_flag() {
		return show_flag;
	}
	public void setShow_flag(String show_flag) {
		this.show_flag = show_flag;
	}
	
	
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private String read_flag;
	public String getRead_flag() {
		return read_flag;
	}
	public void setRead_flag(String read_flag) {
		this.read_flag = read_flag;
	}
	
	private String m_name;
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	private String nm_id;
	public String getNm_id() {
		return nm_id;
	}
	public void setNm_id(String nm_id) {
		this.nm_id = nm_id;
	}
	

	private String choose_name;
	public String getChoose_name() {
		return choose_name;
	}
	public void setChoose_name(String choose_name) {
		this.choose_name = choose_name;
	}
	
}
