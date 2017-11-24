package com.check.model.pact;
import java.util.Date;
/**
 * @author LT
 */
public class Pact {

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
	/** 客户ID */
	private  String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	/** 合同编号 */
	private  String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/** 合同类型 */
	private  String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	/** 工程名称 */
	private  String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	/** 监理单位ID */
	private  String jl_id;
	public String getJl_id() {
		return jl_id;
	}
	public void setJl_id(String jl_id) {
		this.jl_id = jl_id;
	}
	/** 监理方联系人ID */
	private  String jl_cont_id;
	public String getJl_cont_id() {
		return jl_cont_id;
	}
	public void setJl_cont_id(String jl_cont_id) {
		this.jl_cont_id = jl_cont_id;
	}
	/** 状态 */
	private  String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	/** 签订日期 */
	private  String qd_dt;
	public String getQd_dt() {
		return qd_dt;
	}
	public void setQd_dt(String qd_dt) {
		this.qd_dt = qd_dt;
	}
	/** 实际完成时间 */
	private  String sjwc_dt;
	public String getSjwc_dt() {
		return sjwc_dt;
	}
	public void setSjwc_dt(String sjwc_dt) {
		this.sjwc_dt = sjwc_dt;
	}
	/** 实际开始时间 */
	private  String sjks_dt;
	public String getSjks_dt() {
		return sjks_dt;
	}
	public void setSjks_dt(String sjks_dt) {
		this.sjks_dt = sjks_dt;
	}
	/** 计划完成时间 */
	private  String jhwc_dt;
	public String getJhwc_dt() {
		return jhwc_dt;
	}
	public void setJhwc_dt(String jhwc_dt) {
		this.jhwc_dt = jhwc_dt;
	}
	/** 发放报告时间 */
	private  Date ff_dt;
	 
	public Date getFf_dt() {
		return ff_dt;
	}
	public void setFf_dt(Date ff_dt) {
		this.ff_dt = ff_dt;
	}
	/** 委托费用 */
	private  String wtfy;
	public String getWtfy() {
		return wtfy;
	}
	public void setWtfy(String wtfy) {
		this.wtfy = wtfy;
	}
	/** 代办方ID */
	private  String db_id;
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	/** 负责人ID(客户联系人) */
	private  String cont_id;
	public String getCont_id() {
		return cont_id;
	}
	public void setCont_id(String cont_id) {
		this.cont_id = cont_id;
	}
	/** 组ID */
	private  String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	/** 流水号 */
	private  String ls_t;
	public String getLs_t() {
		return ls_t;
	}
	public void setLs_t(String ls_t) {
		this.ls_t = ls_t;
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
	/** 是否追加（Y/N） */
	private  String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	/** 二维码 */
	private  String ewm;
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	/** 条款 */
	private  String terms;
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	/** 备注 */
	private  String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	/** 提交标识 */
	private  String tj_f;
	public String getTj_f() {
		return tj_f;
	}
	public void setTj_f(String tj_f) {
		this.tj_f = tj_f;
	}

	/** 委托单位 */
	private String a_e;
	/** 监理单位 */
	private String a_sv;
	/** 待办单位 */
	private String a_db;
	public String getA_e() {
		return a_e;
	}
	public void setA_e(String a_e) {
		this.a_e = a_e;
	}
	public String getA_sv() {
		return a_sv;
	}
	public void setA_sv(String a_sv) {
		this.a_sv = a_sv;
	}
	public String getA_db() {
		return a_db;
	}
	public void setA_db(String a_db) {
		this.a_db = a_db;
	}
	
	private String jl_nm_t;
	private String cont_nm_t;
	private String sum_zk_n;
	private String sum_payment;
	public String getJl_nm_t() {
		return jl_nm_t;
	}
	public void setJl_nm_t(String jl_nm_t) {
		this.jl_nm_t = jl_nm_t;
	}
	public String getCont_nm_t() {
		return cont_nm_t;
	}
	public void setCont_nm_t(String cont_nm_t) {
		this.cont_nm_t = cont_nm_t;
	}
	public String getSum_zk_n() {
		return sum_zk_n;
	}
	public void setSum_zk_n(String sum_zk_n) {
		this.sum_zk_n = sum_zk_n;
	}
	public String getSum_payment() {
		return sum_payment;
	}
	public void setSum_payment(String sum_payment) {
		this.sum_payment = sum_payment;
	}
	

	private String a_price;
	public String getA_price() {
		return a_price;
	}
	public void setA_price(String a_price) {
		this.a_price = a_price;
	}
	
	private String wjxy_type;
	public String getWjxy_type() {
		return wjxy_type;
	}
	public void setWjxy_type(String wjxy_type) {
		this.wjxy_type = wjxy_type;
	}

	private String m_name;
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	
	
	
	
}
