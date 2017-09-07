package com.check.model.results;
import java.util.Date;
/**
 * @author LT
 */
public class Results {

	/** ID */
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
	/**  */
	private  Long c_id;
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	/**  */
	private  Long pid;
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	/**  */
	private  Long prod_id;
	public Long getProd_id() {
		return prod_id;
	}
	public void setProd_id(Long prod_id) {
		this.prod_id = prod_id;
	}
	/** 检测项目名 */
	private  String prod_name;
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	/** 通过标识 */
	private  String bool;
	public String getBool() {
		return bool;
	}
	public void setBool(String bool) {
		this.bool = bool;
	}
	/** 试验值 */
	private  String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/** 是否检测 */
	private  String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	/** 规则类型 */
	private  String statand_lv;
	public String getStatand_lv() {
		return statand_lv;
	}
	public void setStatand_lv(String statand_lv) {
		this.statand_lv = statand_lv;
	}
	/** 规则 */
	private  String statand;
	public String getStatand() {
		return statand;
	}
	public void setStatand(String statand) {
		this.statand = statand;
	}
	/** 规则值 */
	private  String statand_va;
	public String getStatand_va() {
		return statand_va;
	}
	public void setStatand_va(String statand_va) {
		this.statand_va = statand_va;
	}
	/**  */
	private  Long rel_id;
	public Long getRel_id() {
		return rel_id;
	}
	public void setRel_id(Long rel_id) {
		this.rel_id = rel_id;
	}
	/** 输入的标准值 */
	private  String inbz_t;
	public String getInbz_t() {
		return inbz_t;
	}
	public void setInbz_t(String inbz_t) {
		this.inbz_t = inbz_t;
	}
	/** 实验结论 */
	private  String cm_t;
	public String getCm_t() {
		return cm_t;
	}
	public void setCm_t(String cm_t) {
		this.cm_t = cm_t;
	}
	/** 温度 */
	private  String wd;
	public String getWd() {
		return wd;
	}
	public void setWd(String wd) {
		this.wd = wd;
	}
	/** 湿度 */
	private  String sd;
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	/** BU表ID */
	private  Long bu_id;
	public Long getBu_id() {
		return bu_id;
	}
	public void setBu_id(Long bu_id) {
		this.bu_id = bu_id;
	}



}
