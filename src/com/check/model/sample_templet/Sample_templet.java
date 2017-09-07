package com.check.model.sample_templet;
import java.util.Date;
/**
 * @author LT
 */
public class Sample_templet {

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
	/** 是否显示 */
	private  String show_flg;
	public String getShow_flg() {
		return show_flg;
	}
	public void setShow_flg(String show_flg) {
		this.show_flg = show_flg;
	}
	/** 排序 */
	private  Long sort;
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/** lov表id */
	private  Long lov_id;
	public Long getLov_id() {
		return lov_id;
	}
	public void setLov_id(Long lov_id) {
		this.lov_id = lov_id;
	}
	/** 检测项目id */
	private  Long prod_id;
	public Long getProd_id() {
		return prod_id;
	}
	public void setProd_id(Long prod_id) {
		this.prod_id = prod_id;
	}

	
	
	private String nm_t;
	private String cm_tx;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}


}
