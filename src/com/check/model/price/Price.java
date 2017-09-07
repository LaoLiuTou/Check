package com.check.model.price;
/**
 * @author LT
 */
public class Price {

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
	/** 创建 */
	private  String c_dt;
	public String getC_dt() {
		return c_dt;
	}
	public void setC_dt(String c_dt) {
		this.c_dt = c_dt;
	}
	/** 最后修改时间 */
	private  String up_dt;
	public String getUp_dt() {
		return up_dt;
	}
	public void setUp_dt(String up_dt) {
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
	/** 检测项目ID */
	private  String prod_id;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	/** 类型（常规价、复检价） */
	private  String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	/** 价格 */
	private  Long prix;
	public Long getPrix() {
		return prix;
	}
	public void setPrix(Long prix) {
		this.prix = prix;
	}
	/** 开始日期 */
	private  String s_dt;
	public String getS_dt() {
		return s_dt;
	}
	public void setS_dt(String s_dt) {
		this.s_dt = s_dt;
	}
	/** 结束日期 */
	private  String e_dt;
	public String getE_dt() {
		return e_dt;
	}
	public void setE_dt(String e_dt) {
		this.e_dt = e_dt;
	}



}
