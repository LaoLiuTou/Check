package com.check.action.pay;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.model.pay.Pay;
import com.check.service.accnt.IAccntService;
import com.check.service.pay.IPayService;
import com.check.utils.CacheToRedis;
import com.check.utils.RedisUtil;
import com.opensymphony.xwork2.Action;
public class PayAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IPayService iPayService;
	@Autowired
	private IAccntService iAccntService;
	private List<Pay> list;
	private Pay pay;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Pay getPay() {
		return pay;
	}
	public void setPay(Pay pay) {
		this.pay = pay;
	}
	public List<Pay> getList() {
		return list;
	}
	public void setList(List<Pay> list) {
		this.list = list;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	HttpServletResponse response = ServletActionContext.getResponse(); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("CheckLogger");
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String row_id;
	public String getRow_id() {
		return row_id;
	}
	public void setRow_id(String row_id) {
		this.row_id = row_id;
	}
	private String c_dtFrom;
	public String getC_dtFrom() {
		return c_dtFrom;
	}
	public void setC_dtFrom(String c_dtFrom) {
		this.c_dtFrom = c_dtFrom;
	}
	private String c_dtTo;
	public String getC_dtTo() {
		return c_dtTo;
	}
	public void setC_dtTo(String c_dtTo) {
		this.c_dtTo = c_dtTo;
	}
	private String c_dt;
	public String getC_dt() {
		return c_dt;
	}
	public void setC_dt(String c_dt) {
		this.c_dt = c_dt;
	}
	private String up_dtFrom;
	public String getUp_dtFrom() {
		return up_dtFrom;
	}
	public void setUp_dtFrom(String up_dtFrom) {
		this.up_dtFrom = up_dtFrom;
	}
	private String up_dtTo;
	public String getUp_dtTo() {
		return up_dtTo;
	}
	public void setUp_dtTo(String up_dtTo) {
		this.up_dtTo = up_dtTo;
	}
	private String up_dt;
	public String getUp_dt() {
		return up_dt;
	}
	public void setUp_dt(String up_dt) {
		this.up_dt = up_dt;
	}
	private String c_id;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	private String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	private String payment;
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	private String zk_n;
	public String getZk_n() {
		return zk_n;
	}
	public void setZk_n(String zk_n) {
		this.zk_n = zk_n;
	}
	private String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String dq_dt;
	public String getDq_dt() {
		return dq_dt;
	}
	public void setDq_dt(String dq_dt) {
		this.dq_dt = dq_dt;
	}
	private String yy_t;
	public String getYy_t() {
		return yy_t;
	}
	public void setYy_t(String yy_t) {
		this.yy_t = yy_t;
	}
	private String fk_num;
	public String getFk_num() {
		return fk_num;
	}
	public void setFk_num(String fk_num) {
		this.fk_num = fk_num;
	}
	private  String p_nm_t;  
	private  String a_nm_t;
	private  String bu_id;
	
	public String getP_nm_t() {
		return p_nm_t;
	}
	public void setP_nm_t(String p_nm_t) {
		this.p_nm_t = p_nm_t;
	}
	public String getA_nm_t() {
		return a_nm_t;
	}
	public void setA_nm_t(String a_nm_t) {
		this.a_nm_t = a_nm_t;
	}
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private String dx_pay;
	public String getDx_pay() {
		return dx_pay;
	}
	public void setDx_pay(String dx_pay) {
		this.dx_pay = dx_pay;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Pay pay =new Pay(); 
		if(id!=null&&!id.equals(""))
		pay.setId(Long.parseLong(id));
		if(bu_id!=null&&!bu_id.equals(""))
			pay.setBu_id(Long.parseLong(bu_id));
		pay.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		pay.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		pay.setUp_dt(sdf.parse(up_dt));
		pay.setC_id(c_id);
		pay.setPid(pid);
		
		
		
		pay.setPayment(payment);
		pay.setZk_n(zk_n);
		pay.setSt_lv(st_lv);
		pay.setTy_lv(ty_lv);
		pay.setDq_dt(dq_dt);
		pay.setYy_t(yy_t);
		////FK年份0001 
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);	
		String twoYear =(year+"").substring(2,4);
		Map  paramMap = new HashMap ();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("fk_num","FK"+twoYear+"%"); 
		list=iPayService.selectpayByParam(paramMap);
		if(list.size()>0){
			Pay temp =list.get(0);
			String currentNum = temp.getFk_num().substring(4,8);
			int newNum = Integer.parseInt(currentNum)+1;
			pay.setFk_num("FK"+twoYear+newNum);
		}
		else{
			pay.setFk_num("FK"+twoYear+"0001");
		}
		
		
		
		pay.setFlag(flag);
		pay.setDx_pay(dx_pay);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iPayService.addpay(pay).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(pay.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("pay");
	        CacheToRedis.cache(tableList);
		} catch (Exception e) {
			msg.append("\"failure\",\"msg\":");
			msg.append("\"添加失败！\"");
			logger.info("添加失败！。");
			e.printStackTrace();
		}
		msg.append("}");
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String list() throws Exception {

		response.setContentType("text/html;charset=UTF-8"); 
		Map  paramMap = new HashMap ();
		paramMap.put("fromPage",(page-1)*size);
		paramMap.put("toPage",size); 
			paramMap.put("id", id);
			paramMap.put("bu_id", bu_id);
			paramMap.put("row_id", row_id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("c_id", c_id);
			paramMap.put("pid", pid);
			paramMap.put("payment", payment);
			paramMap.put("zk_n", zk_n);
			paramMap.put("st_lv", st_lv);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("dq_dt", dq_dt);
			paramMap.put("yy_t", yy_t);
			paramMap.put("flag", flag);
			paramMap.put("dx_pay", dx_pay);
			paramMap.put("fk_num", fk_num);
			paramMap.put("p_nm_t", p_nm_t);
			paramMap.put("a_nm_t", a_nm_t);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iPayService.selectpayByParam(paramMap); 
			totalnumber=iPayService.selectCountpayByParam(paramMap);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return value;  
				} 
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) { 
				if(value instanceof Date){ 
					return sdf.format((Date)value);
				}
				return value; 
			}
			});
			msg.append("\"success\",\"count\":\""+totalnumber+"\",\"msg\":");
			msg.append(JSONArray.fromObject(list, jsonConfig));
			logger.info("获取列表成功！");
		} catch (Exception e) {
			msg.append("\"failure\",\"msg\":");
			msg.append("\"查询失败.\"");
			logger.info("获取列表失败！"+e);
			e.printStackTrace();
		}
		msg.append("}");
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}

	public String update() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Pay pay =new Pay(); 
		if(id!=null&&!id.equals(""))
		pay.setId(Long.parseLong(id));
		if(bu_id!=null&&!bu_id.equals(""))
			pay.setBu_id(Long.parseLong(bu_id));
		pay.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		pay.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		pay.setUp_dt(sdf.parse(up_dt));
		pay.setC_id(c_id);
		pay.setPid(pid);
		pay.setPayment(payment);
		pay.setZk_n(zk_n);
		pay.setSt_lv(st_lv);
		pay.setTy_lv(ty_lv);
		pay.setDq_dt(dq_dt);
		pay.setYy_t(yy_t);
		pay.setFk_num(fk_num);
		pay.setFlag(flag);
		pay.setDx_pay(dx_pay);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iPayService.updatepay(pay);
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("pay");
	        CacheToRedis.cache(tableList);
		} catch (Exception e) {
			logger.info(id+"更新失败！"+e);
			msg.append("\"failure\",\"msg\":");
			msg.append("\"更新失败！\"");
			e.printStackTrace();
		}
		msg.append("}");
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}
	public String delete() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iPayService.deletepay(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("pay");
	        CacheToRedis.cache(tableList);
		} catch (Exception e) {
			logger.info(id+"删除失败！"+e);
			msg.append("\"failure\",\"msg\":");
			msg.append("\"更新失败！\"");
			e.printStackTrace();
		}
		msg.append("}");
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}
	public String select() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			pay=iPayService.selectpayById(id);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return value;  
				} 
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) { 
				if(value instanceof Date){ 
					return sdf.format((Date)value);
				}
				return value; 
			}
			});
			msg.append("\"success\",\"msg\":");
			msg.append(JSONObject.fromObject(pay, jsonConfig));
			logger.info(id+"查询成功！");
		} catch (Exception e) {
			logger.info(id+"查询失败！"+e);
			msg.append("\"failure\",\"msg\":");
			msg.append("\"查询失败.\"");
			e.printStackTrace();
		}
		msg.append("}");
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String all() throws Exception {

		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer();
		Map  paramMap = new HashMap ();
		paramMap.put("id", id);
		paramMap.put("bu_id", bu_id);
		paramMap.put("row_id", row_id);
		if(c_dtFrom!=null&&!c_dtFrom.equals(""))
		paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
		if(c_dtTo!=null&&!c_dtTo.equals(""))
		paramMap.put("c_dtTo", sdf.parse(c_dtTo));
		if(up_dtFrom!=null&&!up_dtFrom.equals(""))
		paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
		if(up_dtTo!=null&&!up_dtTo.equals(""))
		paramMap.put("up_dtTo", sdf.parse(up_dtTo));
		paramMap.put("c_id", c_id);
		paramMap.put("pid", pid);
		paramMap.put("payment", payment);
		paramMap.put("zk_n", zk_n);
		paramMap.put("st_lv", st_lv);
		paramMap.put("ty_lv", ty_lv);
		paramMap.put("dq_dt", dq_dt);
		paramMap.put("yy_t", yy_t);
		paramMap.put("flag", flag);
		paramMap.put("dx_pay", dx_pay);
		paramMap.put("fk_num", fk_num);
		paramMap.put("p_nm_t", p_nm_t);
		paramMap.put("a_nm_t", a_nm_t);
		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iPayService.selectCountpayByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iPayService.selectpayByParam(paramMap); 
			//XD  数据库时间
			String dbtime= iAccntService.selectDbtime();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return value;  
				} 
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) { 
				if(value instanceof Date){ 
					return sdf.format((Date)value);
				}
				return value; 
			}
			});
			msg.append("\"success\",\"count\":\""+totalnumber+"\",\"msg\":");
			msg.append(JSONArray.fromObject(list, jsonConfig));
			msg.append(",\"dbtime\":\""+dbtime+"\"");
			logger.info("获取列表成功！");
		} catch (Exception e) {
			msg.append("\"failure\",\"msg\":");
			msg.append("\"查询失败.\"");
			logger.info("获取列表失败！"+e);
			e.printStackTrace();
		}
		msg.append("}");
	 
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}

    public String execute() throws Exception {
		return null;
	}
    public String append() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer();
		if(up_dtFrom!=null&&!up_dtFrom.equals("")){
			JSONArray resultJA=new JSONArray();
	    	String result= RedisUtil.getObject("JC", "pay");
	    	//XD  数据库时间
			String dbtime= iAccntService.selectDbtime();
	    	JSONArray ja = JSONArray.fromObject(result);
	    	for(int i =0;i<ja.size();i++){
	    		JSONObject   jo= (JSONObject) ja.get(i);
	    		if(sdf.parse(jo.getString("up_dt")).after(sdf.parse(up_dtFrom))){
	    			resultJA.add(jo);
	    		}
	    	}
	    	msg.append("{\"state\":\"success\",\"count\":\""+resultJA.size()+"\",\"msg\":");
			msg.append(resultJA);
			msg.append(",\"dbtime\":\""+dbtime+"\"");
			msg.append("}");
		}
		else{
			String result= RedisUtil.getObject("JC", "pay");
	    	//XD  数据库时间
			String dbtime= iAccntService.selectDbtime();
			JSONArray  ja = JSONArray.fromObject(result);
			msg.append("{\"state\":\"success\",\"count\":\""+ja.size()+"\",\"msg\":");
			msg.append(ja);
			msg.append(",\"dbtime\":\""+dbtime+"\"");
			msg.append("}");
		}
		
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}
}
