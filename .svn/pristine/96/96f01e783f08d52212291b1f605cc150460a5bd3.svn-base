package com.check.action.lot;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.check.model.lot.Lot;
import com.check.service.accnt.IAccntService;
import com.check.service.lot.ILotService;
import com.check.utils.CacheToRedis;
import com.check.utils.RedisUtil;
import com.opensymphony.xwork2.Action;
public class LotAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private ILotService iLotService;
	@Autowired
	private IAccntService iAccntService;
	private List<Lot> list;
	private Lot lot;
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
	public Lot getLot() {
		return lot;
	}
	public void setLot(Lot lot) {
		this.lot = lot;
	}
	public List<Lot> getList() {
		return list;
	}
	public void setList(List<Lot> list) {
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
	private String c_id;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
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
	private String modification_num;
	public String getModification_num() {
		return modification_num;
	}
	public void setModification_num(String modification_num) {
		this.modification_num = modification_num;
	}
	private String comments;
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	private String db_last_upd_src;
	public String getDb_last_upd_src() {
		return db_last_upd_src;
	}
	public void setDb_last_upd_src(String db_last_upd_src) {
		this.db_last_upd_src = db_last_upd_src;
	}
	private String group_code;
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	private String sy_dt;
	public String getSy_dt() {
		return sy_dt;
	}
	public void setSy_dt(String sy_dt) {
		this.sy_dt = sy_dt;
	}
	private String wt_id;
	public String getWt_id() {
		return wt_id;
	}
	public void setWt_id(String wt_id) {
		this.wt_id = wt_id;
	}
	private String jc_id;
	public String getJc_id() {
		return jc_id;
	}
	public void setJc_id(String jc_id) {
		this.jc_id = jc_id;
	}
	private String ht_id;
	public String getHt_id() {
		return ht_id;
	}
	public void setHt_id(String ht_id) {
		this.ht_id = ht_id;
	}
	private String wt_dt;
	public String getWt_dt() {
		return wt_dt;
	}
	public void setWt_dt(String wt_dt) {
		this.wt_dt = wt_dt;
	}
	private String lotnumber;
	public String getLotnumber() {
		return lotnumber;
	}
	public void setLotnumber(String lotnumber) {
		this.lotnumber = lotnumber;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String pro_code;
	public String getPro_code() {
		return pro_code;
	}
	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}
	private String pro_num;
	public String getPro_num() {
		return pro_num;
	}
	public void setPro_num(String pro_num) {
		this.pro_num = pro_num;
	}
	private String s_num;
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
	private String year;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Lot lot =new Lot(); 
		if(id!=null&&!id.equals(""))
		lot.setId(Long.parseLong(id));
		if(c_dt!=null&&!c_dt.equals(""))
		lot.setC_dt(sdf.parse(c_dt));
		lot.setC_id(c_id);
		if(up_dt!=null&&!up_dt.equals(""))
		lot.setUp_dt(sdf.parse(up_dt));
		if(modification_num!=null&&!modification_num.equals(""))
		lot.setModification_num(Long.parseLong(modification_num));
		lot.setComments(comments);
		lot.setDb_last_upd_src(db_last_upd_src);
		lot.setGroup_code(group_code);
		lot.setSy_dt(sy_dt);
		lot.setWt_id(wt_id);
		lot.setJc_id(jc_id);
		lot.setHt_id(ht_id);
		lot.setWt_dt(wt_dt);
		lot.setLotnumber(lotnumber);
		lot.setBu_id(bu_id);
		lot.setPro_code(pro_code);
		lot.setPro_num(pro_num);
		lot.setS_num(s_num);
		lot.setYear(year);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iLotService.addlot(lot).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(lot.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("lot");
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
		paramMap.put("toPage",page*size); 
			paramMap.put("id", id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			paramMap.put("c_id", c_id);
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("modification_num", modification_num);
			paramMap.put("comments", comments);
			paramMap.put("db_last_upd_src", db_last_upd_src);
			paramMap.put("group_code", group_code);
			paramMap.put("sy_dt", sy_dt);
			paramMap.put("wt_id", wt_id);
			paramMap.put("jc_id", jc_id);
			paramMap.put("ht_id", ht_id);
			paramMap.put("wt_dt", wt_dt);
			paramMap.put("lotnumber", lotnumber);
			paramMap.put("bu_id", bu_id);
			paramMap.put("pro_code", pro_code);
			paramMap.put("pro_num", pro_num);
			paramMap.put("s_num", s_num);
			paramMap.put("year", year);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iLotService.selectlotByParam(paramMap); 
			totalnumber=iLotService.selectCountlotByParam(paramMap);
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
		Lot lot =new Lot(); 
		if(id!=null&&!id.equals(""))
		lot.setId(Long.parseLong(id));
		if(c_dt!=null&&!c_dt.equals(""))
		lot.setC_dt(sdf.parse(c_dt));
		lot.setC_id(c_id);
		if(up_dt!=null&&!up_dt.equals(""))
		lot.setUp_dt(sdf.parse(up_dt));
		if(modification_num!=null&&!modification_num.equals(""))
		lot.setModification_num(Long.parseLong(modification_num));
		lot.setComments(comments);
		lot.setDb_last_upd_src(db_last_upd_src);
		lot.setGroup_code(group_code);
		lot.setSy_dt(sy_dt);
		lot.setWt_id(wt_id);
		lot.setJc_id(jc_id);
		lot.setHt_id(ht_id);
		lot.setWt_dt(wt_dt);
		lot.setLotnumber(lotnumber);
		lot.setBu_id(bu_id);
		lot.setPro_code(pro_code);
		lot.setPro_num(pro_num);
		lot.setS_num(s_num);
		lot.setYear(year);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iLotService.updatelot(lot);
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("lot");
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
			iLotService.deletelot(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("lot");
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
			lot=iLotService.selectlotById(id);
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
			msg.append(JSONObject.fromObject(lot, jsonConfig));
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
		if(c_dtFrom!=null&&!c_dtFrom.equals(""))
		paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
		if(c_dtTo!=null&&!c_dtTo.equals(""))
		paramMap.put("c_dtTo", sdf.parse(c_dtTo));
		paramMap.put("c_id", c_id);
		if(up_dtFrom!=null&&!up_dtFrom.equals(""))
		paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
		if(up_dtTo!=null&&!up_dtTo.equals(""))
		paramMap.put("up_dtTo", sdf.parse(up_dtTo));
		paramMap.put("modification_num", modification_num);
		paramMap.put("comments", comments);
		paramMap.put("db_last_upd_src", db_last_upd_src);
		paramMap.put("group_code", group_code);
		paramMap.put("sy_dt", sy_dt);
		paramMap.put("wt_id", wt_id);
		paramMap.put("jc_id", jc_id);
		paramMap.put("ht_id", ht_id);
		paramMap.put("wt_dt", wt_dt);
		paramMap.put("lotnumber", lotnumber);
		paramMap.put("bu_id", bu_id);
		paramMap.put("pro_code", pro_code);
		paramMap.put("pro_num", pro_num);
		paramMap.put("s_num", s_num);
		paramMap.put("year", year);
		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iLotService.selectCountlotByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iLotService.selectlotByParam(paramMap); 
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
	public String append() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer();
		if(up_dtFrom!=null&&!up_dtFrom.equals("")){
			JSONArray resultJA=new JSONArray();
	    	String result= RedisUtil.getObject("JC", "lot");
	    	//XD  数据库时间
			String dbtime= iAccntService.selectDbtime();
	    	JSONArray ja = JSONArray.fromObject(result);
	    	for(int i =0;i<ja.size();i++){
	    		JSONObject   jo= (JSONObject) ja.get(i);
	    		if(sdf.parse(jo.getString("up_dt")).after(sdf.parse(up_dtFrom))){
	    			if(bu_id==null||bu_id.equals("")||bu_id.equals(jo.getString("bu_id"))){
	    				resultJA.add(jo);
	    			}
	    		}
	    	}
	    	msg.append("{\"state\":\"success\",\"count\":\""+resultJA.size()+"\",\"msg\":");
			msg.append(resultJA);
			msg.append(",\"dbtime\":\""+dbtime+"\"");
			msg.append("}");
		}
		else{
			String result= RedisUtil.getObject("JC", "lot");
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
    public String execute() throws Exception {
    	List<String> tableList = new ArrayList<String>(); 
        tableList.add("lot");
        CacheToRedis.cache(tableList); 
		return null;
	}
}
