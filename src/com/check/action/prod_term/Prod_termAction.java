package com.check.action.prod_term;
import java.text.SimpleDateFormat;
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

import com.check.model.prod_term.Prod_term;
import com.check.service.accnt.IAccntService;
import com.check.service.prod_term.IProd_termService;
import com.opensymphony.xwork2.Action;
public class Prod_termAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IProd_termService iProd_termService;
	@Autowired
	private IAccntService iAccntService;
	private List<Prod_term> list;
	private Prod_term prod_term;
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
	public Prod_term getProd_term() {
		return prod_term;
	}
	public void setProd_term(Prod_term prod_term) {
		this.prod_term = prod_term;
	}
	public List<Prod_term> getList() {
		return list;
	}
	public void setList(List<Prod_term> list) {
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
	private String prod_id;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	private String term_id;
	public String getTerm_id() {
		return term_id;
	}
	public void setTerm_id(String term_id) {
		this.term_id = term_id;
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
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Prod_term prod_term =new Prod_term(); 
		if(id!=null&&!id.equals(""))
		prod_term.setId(Long.parseLong(id));
		prod_term.setRow_id(row_id);
		prod_term.setProd_id(prod_id);
		prod_term.setTerm_id(term_id);
		if(c_dt!=null&&!c_dt.equals(""))
		prod_term.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		prod_term.setUp_dt(sdf.parse(up_dt));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iProd_termService.addprod_term(prod_term).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(prod_term.getId()+"\"");
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
			paramMap.put("row_id", row_id);
			paramMap.put("prod_id", prod_id);
			paramMap.put("term_id", term_id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iProd_termService.selectprod_termByParam(paramMap); 
			totalnumber=iProd_termService.selectCountprod_termByParam(paramMap);
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
		Prod_term prod_term =new Prod_term(); 
		if(id!=null&&!id.equals(""))
		prod_term.setId(Long.parseLong(id));
		prod_term.setRow_id(row_id);
		prod_term.setProd_id(prod_id);
		prod_term.setTerm_id(term_id);
		if(c_dt!=null&&!c_dt.equals(""))
		prod_term.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		prod_term.setUp_dt(sdf.parse(up_dt));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iProd_termService.updateprod_term(prod_term);
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
			iProd_termService.deleteprod_term(id);
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
			prod_term=iProd_termService.selectprod_termById(id);
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
			msg.append(JSONObject.fromObject(prod_term, jsonConfig));
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
			paramMap.put("row_id", row_id);
			paramMap.put("prod_id", prod_id);
			paramMap.put("term_id", term_id);
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
			paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
			paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			if(up_dtFrom!=null&&!up_dtFrom.equals(""))
			paramMap.put("up_dtFrom", sdf.parse(up_dtFrom));
			if(up_dtTo!=null&&!up_dtTo.equals(""))
			paramMap.put("up_dtTo", sdf.parse(up_dtTo));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iProd_termService.selectCountprod_termByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iProd_termService.selectprod_termByParam(paramMap); 
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
}
