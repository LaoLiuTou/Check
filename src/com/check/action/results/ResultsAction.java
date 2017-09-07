package com.check.action.results;
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

import com.check.model.results.Results;
import com.check.service.accnt.IAccntService;
import com.check.service.results.IResultsService;
import com.opensymphony.xwork2.Action;
public class ResultsAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IResultsService iResultsService;
	@Autowired
	private IAccntService iAccntService;
	private List<Results> list;
	private Results results;
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
	public Results getResults() {
		return results;
	}
	public void setResults(Results results) {
		this.results = results;
	}
	public List<Results> getList() {
		return list;
	}
	public void setList(List<Results> list) {
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
	private String prod_id;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	private String prod_name;
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	private String bool;
	public String getBool() {
		return bool;
	}
	public void setBool(String bool) {
		this.bool = bool;
	}
	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	private String statand_lv;
	public String getStatand_lv() {
		return statand_lv;
	}
	public void setStatand_lv(String statand_lv) {
		this.statand_lv = statand_lv;
	}
	private String statand;
	public String getStatand() {
		return statand;
	}
	public void setStatand(String statand) {
		this.statand = statand;
	}
	private String statand_va;
	public String getStatand_va() {
		return statand_va;
	}
	public void setStatand_va(String statand_va) {
		this.statand_va = statand_va;
	}
	private String rel_id;
	public String getRel_id() {
		return rel_id;
	}
	public void setRel_id(String rel_id) {
		this.rel_id = rel_id;
	}
	private String inbz_t;
	public String getInbz_t() {
		return inbz_t;
	}
	public void setInbz_t(String inbz_t) {
		this.inbz_t = inbz_t;
	}
	private String cm_t;
	public String getCm_t() {
		return cm_t;
	}
	public void setCm_t(String cm_t) {
		this.cm_t = cm_t;
	}
	private String wd;
	public String getWd() {
		return wd;
	}
	public void setWd(String wd) {
		this.wd = wd;
	}
	private String sd;
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Results results =new Results(); 
		if(id!=null&&!id.equals(""))
		results.setId(Long.parseLong(id));
		results.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		results.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		results.setUp_dt(sdf.parse(up_dt));
		if(c_id!=null&&!c_id.equals(""))
		results.setC_id(Long.parseLong(c_id));
		if(pid!=null&&!pid.equals(""))
		results.setPid(Long.parseLong(pid));
		if(prod_id!=null&&!prod_id.equals(""))
		results.setProd_id(Long.parseLong(prod_id));
		results.setProd_name(prod_name);
		results.setBool(bool);
		results.setValue(value);
		results.setFlg(flg);
		results.setStatand_lv(statand_lv);
		results.setStatand(statand);
		results.setStatand_va(statand_va);
		if(rel_id!=null&&!rel_id.equals(""))
		results.setRel_id(Long.parseLong(rel_id));
		results.setInbz_t(inbz_t);
		results.setCm_t(cm_t);
		results.setWd(wd);
		results.setSd(sd);
		if(bu_id!=null&&!bu_id.equals(""))
		results.setBu_id(Long.parseLong(bu_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iResultsService.addresults(results).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(results.getId()+"\"");
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
			paramMap.put("prod_id", prod_id);
			paramMap.put("prod_name", prod_name);
			paramMap.put("bool", bool);
			paramMap.put("value", value);
			paramMap.put("flg", flg);
			paramMap.put("statand_lv", statand_lv);
			paramMap.put("statand", statand);
			paramMap.put("statand_va", statand_va);
			paramMap.put("rel_id", rel_id);
			paramMap.put("inbz_t", inbz_t);
			paramMap.put("cm_t", cm_t);
			paramMap.put("wd", wd);
			paramMap.put("sd", sd);
			paramMap.put("bu_id", bu_id);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iResultsService.selectresultsByParam(paramMap); 
			totalnumber=iResultsService.selectCountresultsByParam(paramMap);
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
		Results results =new Results(); 
		if(id!=null&&!id.equals(""))
		results.setId(Long.parseLong(id));
		results.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		results.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		results.setUp_dt(sdf.parse(up_dt));
		if(c_id!=null&&!c_id.equals(""))
		results.setC_id(Long.parseLong(c_id));
		if(pid!=null&&!pid.equals(""))
		results.setPid(Long.parseLong(pid));
		if(prod_id!=null&&!prod_id.equals(""))
		results.setProd_id(Long.parseLong(prod_id));
		results.setProd_name(prod_name);
		results.setBool(bool);
		results.setValue(value);
		results.setFlg(flg);
		results.setStatand_lv(statand_lv);
		results.setStatand(statand);
		results.setStatand_va(statand_va);
		if(rel_id!=null&&!rel_id.equals(""))
		results.setRel_id(Long.parseLong(rel_id));
		results.setInbz_t(inbz_t);
		results.setCm_t(cm_t);
		results.setWd(wd);
		results.setSd(sd);
		if(bu_id!=null&&!bu_id.equals(""))
		results.setBu_id(Long.parseLong(bu_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iResultsService.updateresults(results);
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
			iResultsService.deleteresults(id);
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
			results=iResultsService.selectresultsById(id);
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
			msg.append(JSONObject.fromObject(results, jsonConfig));
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
			paramMap.put("prod_id", prod_id);
			paramMap.put("prod_name", prod_name);
			paramMap.put("bool", bool);
			paramMap.put("value", value);
			paramMap.put("flg", flg);
			paramMap.put("statand_lv", statand_lv);
			paramMap.put("statand", statand);
			paramMap.put("statand_va", statand_va);
			paramMap.put("rel_id", rel_id);
			paramMap.put("inbz_t", inbz_t);
			paramMap.put("cm_t", cm_t);
			paramMap.put("wd", wd);
			paramMap.put("sd", sd);
			paramMap.put("bu_id", bu_id);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iResultsService.selectCountresultsByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iResultsService.selectresultsByParam(paramMap); 
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
