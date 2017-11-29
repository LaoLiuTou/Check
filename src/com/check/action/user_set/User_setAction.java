package com.check.action.user_set;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Action;
import com.check.service.user_set.IUser_setService;
import com.check.model.user_set.User_set;
public class User_setAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IUser_setService iUser_setService;
	private List<User_set> list;
	private User_set user_set;
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
	public User_set getUser_set() {
		return user_set;
	}
	public void setUser_set(User_set user_set) {
		this.user_set = user_set;
	}
	public List<User_set> getList() {
		return list;
	}
	public void setList(List<User_set> list) {
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
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String members_id;
	public String getMembers_id() {
		return members_id;
	}
	public void setMembers_id(String members_id) {
		this.members_id = members_id;
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
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String group_id;
	
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	private String m_name;
	
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	private String members_ids;
	
	public String getMembers_ids() {
		return members_ids;
	}
	public void setMembers_ids(String members_ids) {
		this.members_ids = members_ids;
	}
	
	
	
	public String muladd() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iUser_setService.deleteuser_setBytyle(ty_lv);
			if(members_ids!=null&&!members_ids.equals("")){
				String[] memberids = members_ids.split("\\|");
				List<String> idList = new ArrayList<String>();
				for(String temp:memberids){
					User_set user_set =new User_set(); 
					if(id!=null&&!id.equals(""))
					user_set.setId(Long.parseLong(id));
					user_set.setTy_lv(ty_lv);
					if(temp!=null&&!temp.equals(""))
					user_set.setMembers_id(Long.parseLong(temp));
					if(c_dt!=null&&!c_dt.equals(""))
					user_set.setC_dt(sdf.parse(c_dt));
					if(up_dt!=null&&!up_dt.equals(""))
					user_set.setUp_dt(sdf.parse(up_dt));
					if(c_id!=null&&!c_id.equals(""))
					user_set.setC_id(Long.parseLong(c_id));
					if(bu_id!=null&&!bu_id.equals(""))
					user_set.setBu_id(Long.parseLong(bu_id));
					int result = Integer.parseInt(iUser_setService.adduser_set(user_set).toString());
					idList.add(user_set.getId()+"");
				}
				msg.append("\"success\",\"msg\":\"");
				msg.append(StringUtils.join(idList,"|")+"\"");
				logger.info(StringUtils.join(idList,"|")+"添加成功！");
			}
			else{
				msg.append("\"success\",\"msg\":\"");
				msg.append("添加成功！"+"\"");
				logger.info("添加成功！");
			}
			 
			
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
	
	
	
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		User_set user_set =new User_set(); 
		if(id!=null&&!id.equals(""))
		user_set.setId(Long.parseLong(id));
		user_set.setTy_lv(ty_lv);
		if(members_id!=null&&!members_id.equals(""))
		user_set.setMembers_id(Long.parseLong(members_id));
		if(c_dt!=null&&!c_dt.equals(""))
		user_set.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		user_set.setUp_dt(sdf.parse(up_dt));
		if(c_id!=null&&!c_id.equals(""))
		user_set.setC_id(Long.parseLong(c_id));
		if(bu_id!=null&&!bu_id.equals(""))
		user_set.setBu_id(Long.parseLong(bu_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iUser_setService.adduser_set(user_set).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(user_set.getId()+"\"");
			logger.info(result+"添加成功！");
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
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("members_id", members_id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("c_id", c_id);
			paramMap.put("bu_id", bu_id);
			paramMap.put("group_id", group_id);
			paramMap.put("m_name", m_name);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iUser_setService.selectuser_setByParam(paramMap); 
			totalnumber=iUser_setService.selectCountuser_setByParam(paramMap);
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
		User_set user_set =new User_set(); 
		if(id!=null&&!id.equals(""))
		user_set.setId(Long.parseLong(id));
		user_set.setTy_lv(ty_lv);
		if(members_id!=null&&!members_id.equals(""))
		user_set.setMembers_id(Long.parseLong(members_id));
		if(c_dt!=null&&!c_dt.equals(""))
		user_set.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		user_set.setUp_dt(sdf.parse(up_dt));
		if(c_id!=null&&!c_id.equals(""))
		user_set.setC_id(Long.parseLong(c_id));
		if(bu_id!=null&&!bu_id.equals(""))
		user_set.setBu_id(Long.parseLong(bu_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iUser_setService.updateuser_set(user_set);
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
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
			iUser_setService.deleteuser_set(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
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
			user_set=iUser_setService.selectuser_setById(id);
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
			msg.append(JSONObject.fromObject(user_set, jsonConfig));
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
		Map  paramMap = new HashMap ();
			paramMap.put("id", id);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("members_id", members_id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
			paramMap.put("c_id", c_id);
			paramMap.put("bu_id", bu_id);
			paramMap.put("group_id", group_id);
			paramMap.put("m_name", m_name);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iUser_setService.selectCountuser_setByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iUser_setService.selectuser_setByParam(paramMap); 
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

    public String execute() throws Exception {
		return null;
	}
}
