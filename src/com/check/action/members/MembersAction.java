package com.check.action.members;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.model.auth_group_access.Auth_group_access;
import com.check.model.auth_rule.Auth_rule;
import com.check.model.members.Members;
import com.check.service.accnt.IAccntService;
import com.check.service.auth_group_access.IAuth_group_accessService;
import com.check.service.auth_rule.IAuth_ruleService;
import com.check.service.members.IMembersService;
import com.check.utils.CacheToRedis;
import com.check.utils.MD5Encryption;
import com.check.utils.RedisUtil;
import com.check.utils.TokenUtils;
import com.check.utils.UsbKeyLock;
import com.opensymphony.xwork2.Action;
public class MembersAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IMembersService iMembersService;
	@Autowired
	private IAuth_ruleService iAuth_ruleService;
	@Autowired
	private IAccntService iAccntService;
	@Autowired
	private IAuth_group_accessService iAuth_group_accessService;
	private List<Members> list;
	private Members members;
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
	public Members getMembers() {
		return members;
	}
	public void setMembers(Members members) {
		this.members = members;
	}
	public List<Members> getList() {
		return list;
	}
	public void setList(List<Members> list) {
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
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String userpwd;
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	private String resp;
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	private String sign;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String  sign_flg;
	public String getSign_flg() {
		return sign_flg;
	}
	public void setSign_flg(String sign_flg) {
		this.sign_flg = sign_flg;
	}
	private String accnt_id;
	
	public String getAccnt_id() {
		return accnt_id;
	}
	public void setAccnt_id(String accnt_id) {
		this.accnt_id = accnt_id;
	}
	private String  last_name;
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	private String accnt_id_null;
	
	public String getAccnt_id_null() {
		return accnt_id_null;
	}
	public void setAccnt_id_null(String accnt_id_null) {
		this.accnt_id_null = accnt_id_null;
	}
	private String departments_id;
	
	public String getDepartments_id() {
		return departments_id;
	}
	public void setDepartments_id(String departments_id) {
		this.departments_id = departments_id;
	}
	
	
	private String group_id;
	
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String is_safety;
 
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	public String getKey4() {
		return key4;
	}
	public void setKey4(String key4) {
		this.key4 = key4;
	}
	public String getIs_safety() {
		return is_safety;
	}
	public void setIs_safety(String is_safety) {
		this.is_safety = is_safety;
	}
	private String rand;
	private String value;
 
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Members members =new Members(); 
		if(id!=null&&!id.equals(""))
		members.setId(Long.parseLong(id));
		members.setRow_id(row_id);
		members.setUsername(username);
		members.setUserpwd(MD5Encryption.getEncryption(userpwd));
		members.setResp(resp);
		if(status!=null&&!status.equals(""))
		members.setStatus(Long.parseLong(status));
		if(c_dt!=null&&!c_dt.equals(""))
		members.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		members.setUp_dt(sdf.parse(up_dt));
		members.setType(type);
		if(bu_id!=null&&!bu_id.equals(""))
		members.setBu_id(Long.parseLong(bu_id));
		members.setImg(img);
		members.setSign(sign);
		members.setName(name);
		members.setSign_flg(sign_flg);
		if(key1!=null&&!key1.equals(""))
			members.setKey1(Long.parseLong(key1));
		if(key2!=null&&!key2.equals(""))
			members.setKey2(Long.parseLong(key2));
		if(key3!=null&&!key3.equals(""))
			members.setKey3(Long.parseLong(key3));
		if(key4!=null&&!key4.equals(""))
			members.setKey4(Long.parseLong(key4));
		if(is_safety!=null&&!is_safety.equals(""))
			members.setIs_safety(Long.parseLong(is_safety));
	 
		
		
		if(accnt_id!=null&&!accnt_id.equals(""))
			members.setAccnt_id(Long.parseLong(accnt_id));
		if(departments_id!=null&&!departments_id.equals(""))
			members.setDepartments_id(Long.parseLong(departments_id));
		members.setLast_name(last_name);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iMembersService.addmembers(members).toString());
			
			if(result>0){
				Auth_group_access auth_group_access = new Auth_group_access();
				if(group_id!=null&&!group_id.equals(""))
				auth_group_access.setGroup_id(Long.parseLong(group_id));
				auth_group_access.setUid(members.getId());
				iAuth_group_accessService.addauth_group_access(auth_group_access);
			}
			msg.append("\"success\",\"msg\":\"");
			msg.append(members.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("members");
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
			paramMap.put("row_id", row_id);
			paramMap.put("username", username);
			if(userpwd!=null&&!userpwd.equals(""))
			paramMap.put("userpwd", MD5Encryption.getEncryption(userpwd));
			paramMap.put("resp", resp);
			paramMap.put("status", status);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("type", type);
			paramMap.put("bu_id", bu_id);
			paramMap.put("img", img);
			paramMap.put("sign", sign);
			paramMap.put("name", name);
			paramMap.put("sign_flg", sign_flg);
			paramMap.put("accnt_id", accnt_id);
			paramMap.put("last_name", last_name);
			paramMap.put("accnt_id_null", accnt_id_null);
			paramMap.put("departments_id", departments_id);
			paramMap.put("group_id", group_id);
			
			
			 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iMembersService.selectmembersByParam(paramMap); 
			totalnumber=iMembersService.selectCountmembersByParam(paramMap);
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
		Members members =new Members(); 
		if(id!=null&&!id.equals(""))
		members.setId(Long.parseLong(id));
		members.setRow_id(row_id);
		members.setUsername(username);
		if(userpwd!=null&&!userpwd.equals(""))
		members.setUserpwd(MD5Encryption.getEncryption(userpwd));
		members.setResp(resp);
		if(status!=null&&!status.equals(""))
		members.setStatus(Long.parseLong(status));
		if(c_dt!=null&&!c_dt.equals(""))
		members.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		members.setUp_dt(sdf.parse(up_dt));
		members.setType(type);
		if(bu_id!=null&&!bu_id.equals(""))
		members.setBu_id(Long.parseLong(bu_id));
		members.setImg(img);
		members.setSign(sign);
		members.setName(name);
		members.setSign_flg(sign_flg);
		
		if(accnt_id!=null&&!accnt_id.equals(""))
			members.setAccnt_id(Long.parseLong(accnt_id));

		if(departments_id!=null&&!departments_id.equals(""))
			members.setDepartments_id(Long.parseLong(departments_id));
		members.setLast_name(last_name);
		
		if(key1!=null&&!key1.equals(""))
			members.setKey1(Long.parseLong(key1));
		if(key2!=null&&!key2.equals(""))
			members.setKey2(Long.parseLong(key2));
		if(key3!=null&&!key3.equals(""))
			members.setKey3(Long.parseLong(key3));
		if(key4!=null&&!key4.equals(""))
			members.setKey4(Long.parseLong(key4));
		if(is_safety!=null&&!is_safety.equals(""))
			members.setIs_safety(Long.parseLong(is_safety));
		
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iMembersService.updatemembers(members);
			if(group_id!=null&&!group_id.equals("")){
				
				Auth_group_access auth_group_access = new Auth_group_access();
				auth_group_access.setGroup_id(Long.parseLong(group_id));
				auth_group_access.setUid(members.getId());
				iAuth_group_accessService.updateauth_group_accessbyuid(auth_group_access);
			}
			
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("members");
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
			iMembersService.deletemembers(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("members");
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
			members=iMembersService.selectmembersById(id);
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
			msg.append(JSONObject.fromObject(members, jsonConfig));
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
		paramMap.put("row_id", row_id);
		paramMap.put("username", username);
		if(userpwd!=null&&!userpwd.equals(""))
		paramMap.put("userpwd", MD5Encryption.getEncryption(userpwd));
		paramMap.put("resp", resp);
		paramMap.put("status", status);
		if(c_dtFrom!=null&&!c_dtFrom.equals(""))
		paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
		if(c_dtTo!=null&&!c_dtTo.equals(""))
		paramMap.put("c_dtTo", sdf.parse(c_dtTo));
		if(up_dtFrom!=null&&!up_dtFrom.equals(""))
		paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
		if(up_dtTo!=null&&!up_dtTo.equals(""))
		paramMap.put("up_dtTo", sdf.parse(up_dtTo));
		paramMap.put("type", type);
		paramMap.put("bu_id", bu_id);
		paramMap.put("img", img);
		paramMap.put("sign", sign);
		paramMap.put("name", name);
		paramMap.put("sign_flg", sign_flg);
		paramMap.put("accnt_id", accnt_id);
		paramMap.put("last_name", last_name);
		paramMap.put("accnt_id_null", accnt_id_null);
		paramMap.put("departments_id", departments_id);
		paramMap.put("group_id", group_id);
		

		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iMembersService.selectCountmembersByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iMembersService.selectmembersByParam(paramMap); 
			for(int i=0;i<list.size();i++){
				list.get(i).setUsername("");
				list.get(i).setUserpwd("");
			}
			//前端需要去掉username和userpwd
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
	    	String result= RedisUtil.getObject("JC", "members");
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
			String result= RedisUtil.getObject("JC", "members");
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
    
  	
  	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
  	public String login() throws Exception {
  		
  		response.setContentType("text/html;charset=UTF-8"); 
  		Map  paramMap = new HashMap ();
  		paramMap.put("fromPage",0);
  		paramMap.put("toPage",1); 
  		
  		paramMap.put("username", username);
  		paramMap.put("status", "1");
  		StringBuffer msg = new StringBuffer("{\"state\":");
  		try {
  			list=iMembersService.selectmembersByParam(paramMap); 
  			if(list.size()>0){
  				members= list.get(0);
  				//System.out.println(members.getUserpwd());
  				//System.out.println(MD5Encryption.getEncryption(userpwd));
  				if(members.getUserpwd().toLowerCase().equals(MD5Encryption.getEncryption(userpwd).toLowerCase())){
  					
  					//private String rond;
  					//private String value;
  					if(members.getIs_safety()!=null&&members.getIs_safety()==0){
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
  						msg.append(JSONObject.fromObject(members, jsonConfig));
  						List<Auth_rule> auList= iAuth_ruleService.selectauth_ruleByUserId(members.getId()+"");
  						String userToken= UUID.randomUUID().toString();
  						msg.append(",\"token\":");
  						msg.append("\""+userToken+"\"");
  						msg.append(",\"auth_rule\":");
  						msg.append(JSONArray.fromObject(auList, jsonConfig));
  						TokenUtils.add(userToken, System.currentTimeMillis()+"");
  					}
  					else{
  						 
  						msg.append("\"success\",\"msg\":");
	  	  				msg.append("\"key\"");
  						 
  						
  					}
  					 
  					
  					//System.out.println(TokenUtils.get(userToken));
  				}
  				else{
  					msg.append("\"failure\",\"msg\":");
  					msg.append("\"密码不正确！\"");
  					
  				}
  				
  			}
  			else{
  				
  				msg.append("\"failure\",\"msg\":");
  				msg.append("\"用户不存在！\"");
  			}
  			
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
  	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
  	public String validate() throws Exception {
  		
  		response.setContentType("text/html;charset=UTF-8"); 
  		Map  paramMap = new HashMap ();
  		paramMap.put("fromPage",0);
  		paramMap.put("toPage",1); 
  		
  		paramMap.put("username", username);
  		paramMap.put("status", "1");
  		StringBuffer msg = new StringBuffer("{\"state\":");
  		try {
  			list=iMembersService.selectmembersByParam(paramMap); 
  			if(list.size()>0){
  				members= list.get(0);
  				//System.out.println(members.getUserpwd());
  				//System.out.println(MD5Encryption.getEncryption(userpwd));
  				if(members.getUserpwd().toLowerCase().equals(MD5Encryption.getEncryption(userpwd).toLowerCase())){
  					
  					//private String rond;
  					//private String value;
  					if(members.getIs_safety()!=null&&members.getIs_safety()==0){
  						
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
  						msg.append(JSONObject.fromObject(members, jsonConfig));
  						List<Auth_rule> auList= iAuth_ruleService.selectauth_ruleByUserId(members.getId()+"");
  						String userToken= UUID.randomUUID().toString();
  						msg.append(",\"token\":");
  						msg.append("\""+userToken+"\"");
  						msg.append(",\"auth_rule\":");
  						msg.append(JSONArray.fromObject(auList, jsonConfig));
  						TokenUtils.add(userToken, System.currentTimeMillis()+"");
  					}
  					else{
  						int param1=Integer.parseInt(rand);
  						int param2=Integer.parseInt(members.getKey1()+"");
  						int param3=Integer.parseInt(members.getKey2()+"");
  						int param4=Integer.parseInt(members.getKey3()+"");
  						int param5=Integer.parseInt(members.getKey4()+"");
  						long sign=new UsbKeyLock().shiled(param1, param2, param3, param4, param5);
  						System.out.println("KEY:"+sign);
  						if(value.equals(sign+"")){
  							
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
  							msg.append(JSONObject.fromObject(members, jsonConfig));
  							List<Auth_rule> auList= iAuth_ruleService.selectauth_ruleByUserId(members.getId()+"");
  							String userToken= UUID.randomUUID().toString();
  							msg.append(",\"token\":");
  							msg.append("\""+userToken+"\"");
  							msg.append(",\"auth_rule\":");
  							msg.append(JSONArray.fromObject(auList, jsonConfig));
  							TokenUtils.add(userToken, System.currentTimeMillis()+"");
  						}
  						else{
  							msg.append("\"failure\",\"msg\":");
  							msg.append("\"U盾与账号不匹配！\"");
  						}
  						
  						
  					}
  					
  					
  					//System.out.println(TokenUtils.get(userToken));
  				}
  				else{
  					msg.append("\"failure\",\"msg\":");
  					msg.append("\"密码不正确！\"");
  					
  				}
  				
  			}
  			else{
  				
  				msg.append("\"failure\",\"msg\":");
  				msg.append("\"用户不存在！\"");
  			}
  			
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

      
  	@SuppressWarnings({ "unchecked", "rawtypes" })
  	public String register() throws Exception {
  		response.setContentType("text/html;charset=UTF-8"); 
  		Map  paramMap = new HashMap ();
  		paramMap.put("fromPage",0);
  		paramMap.put("toPage",1); 
  			 
  		paramMap.put("username", username);
  			 
  		StringBuffer msg = new StringBuffer("{\"state\":");
  		try {
  			if(username.length()<6){
  				msg.append("\"failure\",\"msg\":");
  				msg.append("\"用户名长度必须大于6！\"");
  			}
  			else if(iMembersService.selectmembersByParam(paramMap).size()!=0){
  				msg.append("\"failure\",\"msg\":");
  				msg.append("\"用户名已存在！\"");
  			}
  			else if(userpwd.length()<6){
  				msg.append("\"failure\",\"msg\":");
  				msg.append("\"密码长度必须大于6！\"");
  			}
  			else{
  				Members members =new Members(); 
  				if(id!=null&&!id.equals(""))
  				members.setId(Long.parseLong(id));
  				members.setRow_id(row_id);
  				members.setUsername(username);
  				members.setUserpwd(MD5Encryption.getEncryption(userpwd));
  				members.setResp(resp);
  				if(status!=null&&!status.equals(""))
  				members.setStatus(Long.parseLong(status));
  				if(c_dt!=null&&!c_dt.equals(""))
  				members.setC_dt(sdf.parse(c_dt));
  				if(up_dt!=null&&!up_dt.equals(""))
  				members.setUp_dt(sdf.parse(up_dt));
  				members.setType(type);
  				if(bu_id!=null&&!bu_id.equals(""))
  				members.setBu_id(Long.parseLong(bu_id));
  				members.setImg(img);
  				members.setSign(sign);
  				members.setName(name);
  				members.setSign_flg(sign_flg);
  				members.setLast_name(last_name);

  				if(departments_id!=null&&!departments_id.equals(""))
  					members.setDepartments_id(Long.parseLong(departments_id));
  				if(accnt_id!=null&&!accnt_id.equals(""))
  	  				members.setAccnt_id(Long.parseLong(accnt_id));
  				try {
  					int result = Integer.parseInt(iMembersService.addmembers(members).toString());
  					msg.append("\"success\",\"msg\":\"");
  					msg.append(members.getId()+"\"");
  					logger.info(result+"注册成功！");
  					//缓存
  					List<String> tableList = new ArrayList<String>(); 
  			        tableList.add("members");
  			        CacheToRedis.cache(tableList);
  				} catch (Exception e) {
  					msg.append("\"failure\",\"msg\":");
  					msg.append("\"注册失败！\"");
  					logger.info("注册失败！。");
  					e.printStackTrace();
  				}
  			 
  			}
  			 
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
  	
  	
}
