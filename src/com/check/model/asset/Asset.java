package com.check.model.asset;
import java.util.Date;
/**
 * @author LT
 */
public class Asset {

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
	/** 组ID */
	private  String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	/** 名称 */
	private  String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	/** 编号 */
	private  String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/** 型号 */
	private  String xh_t;
	public String getXh_t() {
		return xh_t;
	}
	public void setXh_t(String xh_t) {
		this.xh_t = xh_t;
	}
	/** 厂家 */
	/*private  String cj_t;
	public String getCj_t() {
		return cj_t;
	}
	public void setCj_t(String cj_t) {
		this.cj_t = cj_t;
	}*/
	
	
	private Long  cj_id;
	public Long getCj_id() {
		return cj_id;
	}
	public void setCj_id(Long cj_id) {
		this.cj_id = cj_id;
	}
	/** 最后修改时间 */
	private  Date e_dt;
	public Date getE_dt() {
		return e_dt;
	}
	public void setE_dt(Date e_dt) {
		this.e_dt = e_dt;
	}
	/** 状态 */
	private  String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	/** 负责人 */
	private  String fz_id;
	public String getFz_id() {
		return fz_id;
	}
	public void setFz_id(String fz_id) {
		this.fz_id = fz_id;
	}
	/** 精度 */
	private  String jd_t;
	public String getJd_t() {
		return jd_t;
	}
	public void setJd_t(String jd_t) {
		this.jd_t = jd_t;
	}
	/** 最后修改时间 */
	private  Date gm_dt;
	public Date getGm_dt() {
		return gm_dt;
	}
	public void setGm_dt(Date gm_dt) {
		this.gm_dt = gm_dt;
	}
	/** 使用日期 */
	private  String s_dt;
	public String getS_dt() {
		return s_dt;
	}
	public void setS_dt(String s_dt) {
		this.s_dt = s_dt;
	}
	/** 生产日期 */
	private  String sc_dt;
	public String getSc_dt() {
		return sc_dt;
	}
	public void setSc_dt(String sc_dt) {
		this.sc_dt = sc_dt;
	}
	/** 养护周期（日） */
	private  Long zq_n;
	public Long getZq_n() {
		return zq_n;
	}
	public void setZq_n(Long zq_n) {
		this.zq_n = zq_n;
	}
	/** 养护日期 */
	private  String yh_dt;
	public String getYh_dt() {
		return yh_dt;
	}
	public void setYh_dt(String yh_dt) {
		this.yh_dt = yh_dt;
	}



}
