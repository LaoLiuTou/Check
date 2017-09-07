package com.check.action.pact;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.model.bu.Bu;
import com.check.model.lov.Lov;
import com.check.model.pact.Pact;
import com.check.service.accnt.IAccntService;
import com.check.service.bu.IBuService;
import com.check.service.lov.ILovService;
import com.check.service.pact.IPactService;
import com.check.utils.CacheToRedis;
import com.check.utils.CodeUtils;
import com.check.utils.MatrixToImageWriter;
import com.check.utils.RedisUtil;
import com.opensymphony.xwork2.Action;
public class PactAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IPactService iPactService;
	@Autowired
	private IBuService iBuService;
	@Autowired
	private ILovService iLovService;
	@Autowired
	private IAccntService iAccntService;
	private List<Pact> list;
	private Pact pact;
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
	public Pact getPact() {
		return pact;
	}
	public void setPact(Pact pact) {
		this.pact = pact;
	}
	public List<Pact> getList() {
		return list;
	}
	public void setList(List<Pact> list) {
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
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	private String jl_id;
	public String getJl_id() {
		return jl_id;
	}
	public void setJl_id(String jl_id) {
		this.jl_id = jl_id;
	}
	private String jl_cont_id;
	public String getJl_cont_id() {
		return jl_cont_id;
	}
	public void setJl_cont_id(String jl_cont_id) {
		this.jl_cont_id = jl_cont_id;
	}
	private String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	private String qd_dt;
	public String getQd_dt() {
		return qd_dt;
	}
	public void setQd_dt(String qd_dt) {
		this.qd_dt = qd_dt;
	}
	private String sjwc_dt;
	public String getSjwc_dt() {
		return sjwc_dt;
	}
	public void setSjwc_dt(String sjwc_dt) {
		this.sjwc_dt = sjwc_dt;
	}
	private String sjks_dt;
	public String getSjks_dt() {
		return sjks_dt;
	}
	public void setSjks_dt(String sjks_dt) {
		this.sjks_dt = sjks_dt;
	}
	private String jhwc_dt;
	public String getJhwc_dt() {
		return jhwc_dt;
	}
	public void setJhwc_dt(String jhwc_dt) {
		this.jhwc_dt = jhwc_dt;
	}
	private String ff_dt;
	public String getFf_dt() {
		return ff_dt;
	}
	public void setFf_dt(String ff_dt) {
		this.ff_dt = ff_dt;
	}
	private String wtfy;
	public String getWtfy() {
		return wtfy;
	}
	public void setWtfy(String wtfy) {
		this.wtfy = wtfy;
	}
	private String db_id;
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	private String cont_id;
	public String getCont_id() {
		return cont_id;
	}
	public void setCont_id(String cont_id) {
		this.cont_id = cont_id;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String ls_t;
	public String getLs_t() {
		return ls_t;
	}
	public void setLs_t(String ls_t) {
		this.ls_t = ls_t;
	}
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String city;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String county;
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	private String street;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	private String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	private String ewm;
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	private String terms;
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	private String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	private String tj_f;
	public String getTj_f() {
		return tj_f;
	}
	public void setTj_f(String tj_f) {
		this.tj_f = tj_f;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Pact pact =new Pact(); 
		if(id!=null&&!id.equals(""))
		pact.setId(Long.parseLong(id));
		pact.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		pact.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		pact.setUp_dt(sdf.parse(up_dt));
		pact.setC_id(c_id);
		pact.setPid(pid);
		 
		/////////////////////////code
		//是否追加
		if(flg.equals("Y"))
			pact.setCode(createCode());
		else{ 
			pact.setCode(createCode());
		}
		
		
		
		pact.setTy_lv(ty_lv);
		pact.setNm_t(nm_t);
		pact.setJl_id(jl_id);
		pact.setJl_cont_id(jl_cont_id);
		pact.setSt_lv(st_lv);
		pact.setQd_dt(qd_dt);
		pact.setSjwc_dt(sjwc_dt);
		pact.setSjks_dt(sjks_dt);
		pact.setJhwc_dt(jhwc_dt);
		pact.setFf_dt(ff_dt);
		pact.setWtfy(wtfy);
		pact.setDb_id(db_id);
		pact.setCont_id(cont_id);
		pact.setBu_id(bu_id);
		
		//流水号
		pact.setLs_t(createLs_t());
		
		
		pact.setState(state);
		pact.setCity(city);
		pact.setCounty(county);
		pact.setStreet(street);
		pact.setFlg(flg);
		pact.setEwm(ewm);
		pact.setTerms(terms);
		pact.setCm_tx(cm_tx);
		pact.setTj_f(tj_f);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iPactService.addpact(pact).toString());
			if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("AR_"+pact.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Pact uppact = new Pact();
					uppact.setId(pact.getId());
					uppact.setEwm(path+"/QRImages/"+qrResult);
					iPactService.updatepact(uppact);
				}
			}
			msg.append("\"success\",\"msg\":\"");
			msg.append(pact.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("pact");
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
	public String appAdd() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Pact pact =new Pact(); 
		if(id!=null&&!id.equals(""))
			pact.setId(Long.parseLong(id));
		pact.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
			pact.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
			pact.setUp_dt(sdf.parse(up_dt));
		pact.setC_id(c_id);
		pact.setPid(pid);
		
		/////////////////////////code
		//是否追加
		if(flg.equals("Y"))
			pact.setCode(createCode());
		else{ 
			pact.setCode(createCode());
		}
		
		
		
		pact.setTy_lv(ty_lv);
		pact.setNm_t(nm_t);
		pact.setJl_id(jl_id);
		pact.setJl_cont_id(jl_cont_id);
		pact.setSt_lv(st_lv);
		pact.setQd_dt(qd_dt);
		pact.setSjwc_dt(sjwc_dt);
		pact.setSjks_dt(sjks_dt);
		pact.setJhwc_dt(jhwc_dt);
		pact.setFf_dt(ff_dt);
		pact.setWtfy(wtfy);
		pact.setDb_id(db_id);
		pact.setCont_id(cont_id);
		pact.setBu_id(bu_id);
		
		//流水号
		pact.setLs_t(createLs_t());
		
		
		pact.setState(state);
		pact.setCity(city);
		pact.setCounty(county);
		pact.setStreet(street);
		pact.setFlg(flg);
		pact.setEwm(ewm);
		pact.setTerms(terms);
		pact.setCm_tx(cm_tx);
		pact.setTj_f(tj_f);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iPactService.addpact(pact).toString());
			if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("AR_"+pact.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Pact uppact = new Pact();
					uppact.setId(pact.getId());
					uppact.setEwm(path+"/QRImages/"+qrResult);
					iPactService.updatepact(uppact);
				}
			}
			msg.append("\"success\",\"msg\":\"");
			msg.append(pact.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
			tableList.add("pact");
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
			paramMap.put("code", code);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("nm_t", nm_t);
			paramMap.put("jl_id", jl_id);
			paramMap.put("jl_cont_id", jl_cont_id);
			paramMap.put("st_lv", st_lv);
			paramMap.put("qd_dt", qd_dt);
			paramMap.put("sjwc_dt", sjwc_dt);
			paramMap.put("sjks_dt", sjks_dt);
			paramMap.put("jhwc_dt", jhwc_dt);
			paramMap.put("ff_dt", ff_dt);
			paramMap.put("wtfy", wtfy);
			paramMap.put("db_id", db_id);
			paramMap.put("cont_id", cont_id);
			paramMap.put("bu_id", bu_id);
			paramMap.put("ls_t", ls_t);
			paramMap.put("state", state);
			paramMap.put("city", city);
			paramMap.put("county", county);
			paramMap.put("street", street);
			paramMap.put("flg", flg);
			paramMap.put("ewm", ewm);
			paramMap.put("terms", terms);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("tj_f", tj_f);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iPactService.selectpactByParam(paramMap); 
			totalnumber=iPactService.selectCountpactByParam(paramMap);
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
		Pact pact =new Pact(); 
		if(id!=null&&!id.equals(""))
		pact.setId(Long.parseLong(id));
		pact.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		pact.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		pact.setUp_dt(sdf.parse(up_dt));
		pact.setC_id(c_id);
		pact.setPid(pid);
		pact.setCode(code);
		pact.setTy_lv(ty_lv);
		pact.setNm_t(nm_t);
		pact.setJl_id(jl_id);
		pact.setJl_cont_id(jl_cont_id);
		pact.setSt_lv(st_lv);
		pact.setQd_dt(qd_dt);
		pact.setSjwc_dt(sjwc_dt);
		pact.setSjks_dt(sjks_dt);
		pact.setJhwc_dt(jhwc_dt);
		pact.setFf_dt(ff_dt);
		pact.setWtfy(wtfy);
		pact.setDb_id(db_id);
		pact.setCont_id(cont_id);
		pact.setBu_id(bu_id);
		pact.setLs_t(ls_t);
		pact.setState(state);
		pact.setCity(city);
		pact.setCounty(county);
		pact.setStreet(street);
		pact.setFlg(flg);
		pact.setEwm(ewm);
		pact.setTerms(terms);
		pact.setCm_tx(cm_tx);
		pact.setTj_f(tj_f);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iPactService.updatepact(pact);
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("pact");
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
			iPactService.deletepact(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("pact");
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
			pact=iPactService.selectpactById(id);
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
			msg.append(JSONObject.fromObject(pact, jsonConfig));
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
		paramMap.put("code", code);
		paramMap.put("ty_lv", ty_lv);
		paramMap.put("nm_t", nm_t);
		paramMap.put("jl_id", jl_id);
		paramMap.put("jl_cont_id", jl_cont_id);
		paramMap.put("st_lv", st_lv);
		paramMap.put("qd_dt", qd_dt);
		paramMap.put("sjwc_dt", sjwc_dt);
		paramMap.put("sjks_dt", sjks_dt);
		paramMap.put("jhwc_dt", jhwc_dt);
		paramMap.put("ff_dt", ff_dt);
		paramMap.put("wtfy", wtfy);
		paramMap.put("db_id", db_id);
		paramMap.put("cont_id", cont_id);
		paramMap.put("bu_id", bu_id);
		paramMap.put("ls_t", ls_t);
		paramMap.put("state", state);
		paramMap.put("city", city);
		paramMap.put("county", county);
		paramMap.put("street", street);
		paramMap.put("flg", flg);
		paramMap.put("ewm", ewm);
		paramMap.put("terms", terms);
		paramMap.put("cm_tx", cm_tx);
		paramMap.put("tj_f", tj_f);
		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iPactService.selectCountpactByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iPactService.selectpactByParam(paramMap); 
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
    	
    	/*String qrResult = MatrixToImageWriter.createQrImage("AR_"+"1000");
		if(qrResult.length()>0){
			HttpServletRequest request = ServletActionContext.getRequest();
			String path = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			 
			System.out.println(path+"/QRImages/"+qrResult);
		}
		*/
    	System.out.println(createLs_t());
		return null;
	}
    public String append() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer();
		if(up_dtFrom!=null&&!up_dtFrom.equals("")){
			JSONArray resultJA=new JSONArray();
	    	String result= RedisUtil.getObject("JC", "pact");
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
			String result= RedisUtil.getObject("JC", "pact");
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
    
    /**
     * 
     * 合同号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createCode(){
    	//192.168.1.144/Check/Pact?bu_id=1&ty_lv=施工协议
		/////////////////////////code
		StringBuffer sb = new StringBuffer();
		Bu bu= iBuService.selectbuById(bu_id);
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("ty_lv", "组织编码");
		paramMap.put("bu_id", bu_id);
		List<Lov> lovList= iLovService.selectlovByParam(paramMap);
		if(lovList.size()>0){
			sb.append(lovList.get(0).getNm_t());
		}
		sb.append("JC-");
		//查询条件
		String loc = bu.getLoc()+"_"+ty_lv;
		if(ty_lv.equals("施工协议")){
			sb.append("SG-");
		}
		else if(ty_lv.equals("平行协议")){
			sb.append("PX-");
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		sb.append(year+"-");
		paramMap.put("nm_t", loc);
		paramMap.put("ty_lv", "编号");
		paramMap.put("order", "ORDER BY SORT DESC");
		Lov templov = new Lov();
		String newSort=""; 
		lovList= iLovService.selectlovByParamOrder(paramMap);
		if(lovList.size()>0){
			String lovSort=lovList.get(0).getSort();
			Long lovId=lovList.get(0).getId();
			
			if(lovSort.equals("")||lovSort.equals("null")){
				newSort = "001";
				templov.setSort(year+newSort);
				templov.setId(lovId);
			}
			else{
				//更新
				int cyear=Integer.valueOf(lovSort.substring(0,4)).intValue();
				int sortNum=Integer.valueOf(lovSort.substring(4,7)).intValue();
				if(year==cyear){
					newSort = CodeUtils.autoGenericCode(sortNum+"",3);
				}
				else{
					newSort = "001";
				}
				
				templov.setSort(year+newSort);
				templov.setId(lovId);
			}
			iLovService.updatelov(templov);
		}
		else{
			//新建 
			newSort = "001";
			templov.setBu_id(bu_id);
			templov.setC_dt(new Date());
			templov.setCm_tx("");
			templov.setNm_t(loc);
			templov.setSort(year+newSort);
			templov.setTy_lv("编号");
			iLovService.addlov(templov);
		}
		sb.append(newSort);
    	
    	
    	return sb.toString();
    }
    
    /**
     * 
     * 流水号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createLs_t(){
    	/////////////////////////Ls_t
    	StringBuffer sb = new StringBuffer();
    	Map paramMap = new HashMap();
    	paramMap.put("fromPage",0);
    	paramMap.put("toPage",1); 
    	paramMap.put("ty_lv", "组织编码");
    	paramMap.put("bu_id", bu_id);
    	List<Lov> lovList= iLovService.selectlovByParam(paramMap);
    	if(lovList.size()>0){
    		sb.append(lovList.get(0).getNm_t());
    	}
    	 
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	sb.append(year);
    	paramMap.put("ty_lv", "流水号");
    	paramMap.put("cm_tx", year+"");
    	paramMap.put("order", "ORDER BY SORT DESC");
    	Lov templov = new Lov();
    	String newSort=""; 
    	lovList= iLovService.selectlovByParamOrder(paramMap);
    	if(lovList.size()>0){
    		String lovSort=lovList.get(0).getSort();
    		Long lovId=lovList.get(0).getId();
    		
    		if(lovSort.equals("")||lovSort.equals("null")){
    			newSort = "0001";
    			templov.setSort(newSort);
    			templov.setId(lovId);
    			templov.setCm_tx(year+"");
    		}
    		else{
    			//更新
    			int sortNum=Integer.valueOf(lovSort).intValue();
    			newSort = CodeUtils.autoGenericCode(sortNum+"",4);
    			templov.setSort(newSort);
    			templov.setId(lovId);
    			templov.setCm_tx(year+"");
    		}
    		iLovService.updatelov(templov);
    	}
    	else{
    		//新建 
    		newSort = "0001";
    		templov.setBu_id(bu_id);
    		templov.setC_dt(new Date());
    		templov.setCm_tx(year+"");
    		templov.setNm_t("Code");
    		templov.setSort(newSort);
    		templov.setTy_lv("流水号");
    		iLovService.addlov(templov);
    	}
    	sb.append(newSort);
    	
    	
    	return sb.toString();
    }
    
    
    
}
