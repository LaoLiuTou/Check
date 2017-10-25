package com.check.model.pay;
import java.util.Date;
/**
 * @author LT
 */
public class Pay {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private  Long bu_id;
	
	public Long getBu_id() {
		return bu_id;
	}
	public void setBu_id(Long bu_id) {
		this.bu_id = bu_id;
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
	/** 合同ID */
	private  String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	/** 付款金额 */
	private  String payment;
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	/** 折扣金额 */
	private  String zk_n;
	public String getZk_n() {
		return zk_n;
	}
	public void setZk_n(String zk_n) {
		this.zk_n = zk_n;
	}
	/** 状态 */
	private  String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	/** 支付方式 */
	private  String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	/** 到期日期 */
	private  String dq_dt;
	public String getDq_dt() {
		return dq_dt;
	}
	public void setDq_dt(String dq_dt) {
		this.dq_dt = dq_dt;
	}
	/** 收款是由 */
	private  String yy_t;
	public String getYy_t() {
		return yy_t;
	}
	public void setYy_t(String yy_t) {
		this.yy_t = yy_t;
	}
	/** 付款编号 */
	private  String fk_num;
	public String getFk_num() {
		return fk_num;
	}
	public void setFk_num(String fk_num) {
		this.fk_num = fk_num;
	}

	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private  String p_nm_t;
	private  String p_ty_lv;
	private  String p_code;
	
	private  String a_nm_t;
	
	public String getP_nm_t() {
		return p_nm_t;
	}
	public void setP_nm_t(String p_nm_t) {
		this.p_nm_t = p_nm_t;
	}
	public String getP_ty_lv() {
		return p_ty_lv;
	}
	public void setP_ty_lv(String p_ty_lv) {
		this.p_ty_lv = p_ty_lv;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		this.p_code = p_code;
	}
	public String getA_nm_t() {
		return a_nm_t;
	}
	public void setA_nm_t(String a_nm_t) {
		this.a_nm_t = a_nm_t;
	}
	
	private String dx_pay;
	public String getDx_pay() {
		return dx_pay;
	}
	public void setDx_pay(String dx_pay) {
		this.dx_pay = dx_pay;
	}
	
	


}
