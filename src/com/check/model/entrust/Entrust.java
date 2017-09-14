package com.check.model.entrust;
import java.util.Date;
/**
 * @author LT
 */
public class Entrust {

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
	/** 委托编号 */
	private  String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/** 检测项目ID */
	private  String prod_id;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	/** 委托类型(一般，仲裁，监督) */
	private  String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	/** 取样地点(现场) */
	private  String qy_lv;
	public String getQy_lv() {
		return qy_lv;
	}
	public void setQy_lv(String qy_lv) {
		this.qy_lv = qy_lv;
	}
	/** 监理单位ID */
	private  String jl_id;
	public String getJl_id() {
		return jl_id;
	}
	public void setJl_id(String jl_id) {
		this.jl_id = jl_id;
	}
	/** 审批人ID */
	private  String sp_id;
	public String getSp_id() {
		return sp_id;
	}
	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}
	/** 报告领取人 */
	private  String lq_id;
	public String getLq_id() {
		return lq_id;
	}
	public void setLq_id(String lq_id) {
		this.lq_id = lq_id;
	}
	/** 委托单状态 */
	private  String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	/** 付款状态 */
	private  String pay_lv;
	public String getPay_lv() {
		return pay_lv;
	}
	public void setPay_lv(String pay_lv) {
		this.pay_lv = pay_lv;
	}
	/** 检测价格 */
	private  String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	/** 试验次数 */
	private  Long cs_n;
	public Long getCs_n() {
		return cs_n;
	}
	public void setCs_n(Long cs_n) {
		this.cs_n = cs_n;
	}
	/** 试验人ID */
	private  String syr_id;
	public String getSyr_id() {
		return syr_id;
	}
	public void setSyr_id(String syr_id) {
		this.syr_id = syr_id;
	}
	/** 计划完成时间 */
	private  String jh_dt;
	public String getJh_dt() {
		return jh_dt;
	}
	public void setJh_dt(String jh_dt) {
		this.jh_dt = jh_dt;
	}
	/** 常规 */
	private  String cg_f;
	public String getCg_f() {
		return cg_f;
	}
	public void setCg_f(String cg_f) {
		this.cg_f = cg_f;
	}
	/** 备注 */
	private  String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	/** 是否正常（Y/N） */
	private  String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	/** 合同ID */
	private  String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	/** 流水号 */
	private  String ls_n;
	public String getLs_n() {
		return ls_n;
	}
	public void setLs_n(String ls_n) {
		this.ls_n = ls_n;
	}
	/** 样品规格组合 */
	private  String gg_code;
	public String getGg_code() {
		return gg_code;
	}
	public void setGg_code(String gg_code) {
		this.gg_code = gg_code;
	}
	/** 试验编号组合 */
	private  String sy_code;
	public String getSy_code() {
		return sy_code;
	}
	public void setSy_code(String sy_code) {
		this.sy_code = sy_code;
	}
	/** 二维码 */
	private  String ewm;
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	/** 组ID */
	private  String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	/** 委托日期 */
	private  String wt_dt;
	public String getWt_dt() {
		return wt_dt;
	}
	public void setWt_dt(String wt_dt) {
		this.wt_dt = wt_dt;
	}
	/** 是否提交(Y/N) */
	private  String submit;
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	/** 实际完成时间 */
	private  String sjwc_dt;
	public String getSjwc_dt() {
		return sjwc_dt;
	}
	public void setSjwc_dt(String sjwc_dt) {
		this.sjwc_dt = sjwc_dt;
	}
	/** 发放报告时间 */
	private  String ff_dt;
	public String getFf_dt() {
		return ff_dt;
	}
	public void setFf_dt(String ff_dt) {
		this.ff_dt = ff_dt;
	}
	/** 废弃标识 */
	private  String fq_flg;
	public String getFq_flg() {
		return fq_flg;
	}
	public void setFq_flg(String fq_flg) {
		this.fq_flg = fq_flg;
	}
	/** 审批意见 */
	private  String spyj_t;
	public String getSpyj_t() {
		return spyj_t;
	}
	public void setSpyj_t(String spyj_t) {
		this.spyj_t = spyj_t;
	}
	/** 复制id */
	private  Long copy_id;
	public Long getCopy_id() {
		return copy_id;
	}
	public void setCopy_id(Long copy_id) {
		this.copy_id = copy_id;
	}
	
	private String pact_nm_t;
	private String a_nm_t;
	private String prod_nm_t;
	public String getPact_nm_t() {
		return pact_nm_t;
	}
	public void setPact_nm_t(String pact_nm_t) {
		this.pact_nm_t = pact_nm_t;
	}
	public String getA_nm_t() {
		return a_nm_t;
	}
	public void setA_nm_t(String a_nm_t) {
		this.a_nm_t = a_nm_t;
	}
	public String getProd_nm_t() {
		return prod_nm_t;
	}
	public void setProd_nm_t(String prod_nm_t) {
		this.prod_nm_t = prod_nm_t;
	}


}
