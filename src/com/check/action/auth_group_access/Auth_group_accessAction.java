package com.check.action.auth_group_access;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Action;
import com.check.service.accnt.IAccntService;
import com.check.service.auth_group_access.IAuth_group_accessService;
import com.check.utils.CacheToRedis;
import com.check.utils.RedisUtil;
import com.check.model.auth_group_access.Auth_group_access;
public class Auth_group_accessAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IAuth_group_accessService iAuth_group_accessService;
	@Autowired
	private IAccntService iAccntService;
	private List<Auth_group_access> list;
	private Auth_group_access auth_group_access;
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
	public Auth_group_access getAuth_group_access() {
		return auth_group_access;
	}
	public void setAuth_group_access(Auth_group_access auth_group_access) {
		this.auth_group_access = auth_group_access;
	}
	public List<Auth_group_access> getList() {
		return list;
	}
	public void setList(List<Auth_group_access> list) {
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
	private String uid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	private String group_id;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
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
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Auth_group_access auth_group_access =new Auth_group_access(); 
		if(id!=null&&!id.equals(""))
		auth_group_access.setId(Long.parseLong(id));
		if(uid!=null&&!uid.equals(""))
		auth_group_access.setUid(Long.parseLong(uid));
		if(group_id!=null&&!group_id.equals(""))
		auth_group_access.setGroup_id(Long.parseLong(group_id));
		if(c_dt!=null&&!c_dt.equals(""))
		auth_group_access.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		auth_group_access.setUp_dt(sdf.parse(up_dt));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iAuth_group_accessService.addauth_group_access(auth_group_access).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(auth_group_access.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("auth_group_access");
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
			paramMap.put("uid", uid);
			paramMap.put("group_id", group_id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("status", status);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iAuth_group_accessService.selectauth_group_accessByParam(paramMap); 
			totalnumber=iAuth_group_accessService.selectCountauth_group_accessByParam(paramMap);
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
		Auth_group_access auth_group_access =new Auth_group_access(); 
		if(id!=null&&!id.equals(""))
		auth_group_access.setId(Long.parseLong(id));
		if(uid!=null&&!uid.equals(""))
		auth_group_access.setUid(Long.parseLong(uid));
		if(group_id!=null&&!group_id.equals(""))
		auth_group_access.setGroup_id(Long.parseLong(group_id));
		if(c_dt!=null&&!c_dt.equals(""))
		auth_group_access.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		auth_group_access.setUp_dt(sdf.parse(up_dt));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iAuth_group_accessService.updateauth_group_access(auth_group_access);
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("auth_group_access");
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
			iAuth_group_accessService.deleteauth_group_access(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("auth_group_access");
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
			auth_group_access=iAuth_group_accessService.selectauth_group_accessById(id);
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
			msg.append(JSONObject.fromObject(auth_group_access, jsonConfig));
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
		paramMap.put("uid", uid);
		paramMap.put("group_id", group_id);
		if(c_dtFrom!=null&&!c_dtFrom.equals(""))
		paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
		if(c_dtTo!=null&&!c_dtTo.equals(""))
		paramMap.put("c_dtTo", sdf.parse(c_dtTo));
		if(up_dtFrom!=null&&!up_dtFrom.equals(""))
		paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
		if(up_dtTo!=null&&!up_dtTo.equals(""))
		paramMap.put("up_dtTo", sdf.parse(up_dtTo));
		paramMap.put("status", status);
		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iAuth_group_accessService.selectCountauth_group_accessByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iAuth_group_accessService.selectauth_group_accessByParam(paramMap); 
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
	    	String result= RedisUtil.getObject("JC", "auth_group_access");
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
			String result= RedisUtil.getObject("JC", "auth_group_access");
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
		return null;
	}
}
