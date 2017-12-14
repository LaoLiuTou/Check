package com.check.model.members;
import java.util.Date;
/**
 * @author LT
 */
public class Members {

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
	/** 账号 */
	private  String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/** 密码 */
	private  String userpwd;
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	/** 用户职责名 */
	private  String resp;
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	/** 用户状态 0 禁用 1 启用 */
	private  Long status;
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	/** 创建 */
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
	/** 账号类型：超级管理员，平台管理员 */
	private  String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/** 组ID */
	private  Long bu_id;
	public Long getBu_id() {
		return bu_id;
	}
	public void setBu_id(Long bu_id) {
		this.bu_id = bu_id;
	}
	/** 头像 */
	private  String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/** 签名 */
	private  String sign;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	/** 姓名 */
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private String group_id;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	private String  sign_flg;
	public String getSign_flg() {
		return sign_flg;
	}
	public void setSign_flg(String sign_flg) {
		this.sign_flg = sign_flg;
	}
	private Long accnt_id;
	public Long getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(Long accnt_id) {
		this.accnt_id = accnt_id;
	}
	
	
	private String  last_name;
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	private Long departments_id;
	

	public Long getDepartments_id() {
		return departments_id;
	}
	public void setDepartments_id(Long departments_id) {
		this.departments_id = departments_id;
	}
	private String c_nm_t;
	private String a_nm_t;
	public String getC_nm_t() {
		return c_nm_t;
	}
	public void setC_nm_t(String c_nm_t) {
		this.c_nm_t = c_nm_t;
	}
	public String getA_nm_t() {
		return a_nm_t;
	}
	public void setA_nm_t(String a_nm_t) {
		this.a_nm_t = a_nm_t;
	}
	
	private String b_nm_t;
	private String d_nm_t;
	public String getB_nm_t() {
		return b_nm_t;
	}
	public void setB_nm_t(String b_nm_t) {
		this.b_nm_t = b_nm_t;
	}
	public String getD_nm_t() {
		return d_nm_t;
	}
	public void setD_nm_t(String d_nm_t) {
		this.d_nm_t = d_nm_t;
	}
	
	private Long key1;
	private Long key2;
	private Long key3;
	private Long key4;
	private Long is_safety;
	public Long getKey1() {
		return key1;
	}
	public void setKey1(Long key1) {
		this.key1 = key1;
	}
	public Long getKey2() {
		return key2;
	}
	public void setKey2(Long key2) {
		this.key2 = key2;
	}
	public Long getKey3() {
		return key3;
	}
	public void setKey3(Long key3) {
		this.key3 = key3;
	}
	public Long getKey4() {
		return key4;
	}
	public void setKey4(Long key4) {
		this.key4 = key4;
	}
	public Long getIs_safety() {
		return is_safety;
	}
	public void setIs_safety(Long is_safety) {
		this.is_safety = is_safety;
	}
	
}
