package com.check.action.prod;
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

import com.check.model.prod.Prod;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
import com.check.service.accnt.IAccntService;
import com.check.service.prod.IProdService;
import com.check.service.sample.ISampleService;
import com.check.service.test.ITestService;
import com.check.utils.CacheToRedis;
import com.check.utils.RedisUtil;
import com.opensymphony.xwork2.Action;
public class ProdAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IProdService iProdService;
	@Autowired
	private IAccntService iAccntService;
	private List<Prod> list;
	private Prod prod;
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
	public Prod getProd() {
		return prod;
	}
	public void setProd(Prod prod) {
		this.prod = prod;
	}
	public List<Prod> getList() {
		return list;
	}
	public void setList(List<Prod> list) {
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
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String dh_lv;
	public String getDh_lv() {
		return dh_lv;
	}
	public void setDh_lv(String dh_lv) {
		this.dh_lv = dh_lv;
	}
	private String zq_n;
	public String getZq_n() {
		return zq_n;
	}
	public void setZq_n(String zq_n) {
		this.zq_n = zq_n;
	}
	private String qz_n;
	public String getQz_n() {
		return qz_n;
	}
	public void setQz_n(String qz_n) {
		this.qz_n = qz_n;
	}
	private String lb_lv;
	public String getLb_lv() {
		return lb_lv;
	}
	public void setLb_lv(String lb_lv) {
		this.lb_lv = lb_lv;
	}
	private String sy_id;
	public String getSy_id() {
		return sy_id;
	}
	public void setSy_id(String sy_id) {
		this.sy_id = sy_id;
	}
	private String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	private String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	private String fj_f;
	public String getFj_f() {
		return fj_f;
	}
	public void setFj_f(String fj_f) {
		this.fj_f = fj_f;
	}
	private String gzty_lv;
	public String getGzty_lv() {
		return gzty_lv;
	}
	public void setGzty_lv(String gzty_lv) {
		this.gzty_lv = gzty_lv;
	}
	private String rule_lv;
	public String getRule_lv() {
		return rule_lv;
	}
	public void setRule_lv(String rule_lv) {
		this.rule_lv = rule_lv;
	}
	private String bz_t;
	public String getBz_t() {
		return bz_t;
	}
	public void setBz_t(String bz_t) {
		this.bz_t = bz_t;
	}
	private String jy_f;
	public String getJy_f() {
		return jy_f;
	}
	public void setJy_f(String jy_f) {
		this.jy_f = jy_f;
	}
	private String cgj;
	public String getCgj() {
		return cgj;
	}
	public void setCgj(String cgj) {
		this.cgj = cgj;
	}
	private String fjj;
	public String getFjj() {
		return fjj;
	}
	public void setFjj(String fjj) {
		this.fjj = fjj;
	}
	private String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	
	private String mulupdate;
	private String muladd;
	private String subprod;
	public String getMulupdate() {
		return mulupdate;
	}
	public void setMulupdate(String mulupdate) {
		this.mulupdate = mulupdate;
	}
	public String getMuladd() {
		return muladd;
	}
	public void setMuladd(String muladd) {
		this.muladd = muladd;
	}
	private String termids;
	public String getTermids() {
		return termids;
	}
	public void setTermids(String termids) {
		this.termids = termids;
	}
	
	public String getSubprod() {
		return subprod;
	}
	public void setSubprod(String subprod) {
		this.subprod = subprod;
	}
	public String addflow() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Prod prod =new Prod(); 
		if(id!=null&&!id.equals(""))
			prod.setId(Long.parseLong(id));
		prod.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
			prod.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
			prod.setUp_dt(sdf.parse(up_dt));
		prod.setC_id(c_id);
		prod.setPid(pid);
		prod.setBu_id(bu_id);
		prod.setNm_t(nm_t);
		prod.setTy_lv(ty_lv);
		prod.setDh_lv(dh_lv);
		if(zq_n!=null&&!zq_n.equals(""))
			prod.setZq_n(Long.parseLong(zq_n));
		prod.setQz_n(qz_n);
		prod.setLb_lv(lb_lv);
		prod.setSy_id(sy_id);
		prod.setSt_lv(st_lv);
		prod.setCm_tx(cm_tx);
		prod.setFj_f(fj_f);
		prod.setGzty_lv(gzty_lv);
		prod.setRule_lv(rule_lv);
		prod.setBz_t(bz_t);
		prod.setJy_f(jy_f);
		prod.setCgj(cgj);
		prod.setFjj(fjj);
		prod.setFlg(flg);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iProdService.addflowprod(prod,muladd,termids,subprod).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(prod.getId()+"\"");
			logger.info(result+"添加成功！");
			
			//缓存
			List<String> tableList = new ArrayList<String>(); 
			tableList.add("prod");
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
	public String updateflow() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Prod prod =new Prod(); 
		if(id!=null&&!id.equals(""))
			prod.setId(Long.parseLong(id));
		prod.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
			prod.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
			prod.setUp_dt(sdf.parse(up_dt));
		prod.setC_id(c_id);
		prod.setPid(pid);
		prod.setBu_id(bu_id);
		prod.setNm_t(nm_t);
		prod.setTy_lv(ty_lv);
		prod.setDh_lv(dh_lv);
		if(zq_n!=null&&!zq_n.equals(""))
			prod.setZq_n(Long.parseLong(zq_n));
		prod.setQz_n(qz_n);
		prod.setLb_lv(lb_lv);
		prod.setSy_id(sy_id);
		prod.setSt_lv(st_lv);
		prod.setCm_tx(cm_tx);
		prod.setFj_f(fj_f);
		prod.setGzty_lv(gzty_lv);
		prod.setRule_lv(rule_lv);
		prod.setBz_t(bz_t);
		prod.setJy_f(jy_f);
		prod.setCgj(cgj);
		prod.setFjj(fjj);
		prod.setFlg(flg);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iProdService.updateflowprod(prod,mulupdate,termids,subprod).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(prod.getId()+"\"");
			logger.info(result+"添加成功！");
			
			//缓存
			List<String> tableList = new ArrayList<String>(); 
			tableList.add("prod");
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
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Prod prod =new Prod(); 
		if(id!=null&&!id.equals(""))
		prod.setId(Long.parseLong(id));
		prod.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		prod.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		prod.setUp_dt(sdf.parse(up_dt));
		prod.setC_id(c_id);
		prod.setPid(pid);
		prod.setBu_id(bu_id);
		prod.setNm_t(nm_t);
		prod.setTy_lv(ty_lv);
		prod.setDh_lv(dh_lv);
		if(zq_n!=null&&!zq_n.equals(""))
		prod.setZq_n(Long.parseLong(zq_n));
		prod.setQz_n(qz_n);
		prod.setLb_lv(lb_lv);
		prod.setSy_id(sy_id);
		prod.setSt_lv(st_lv);
		prod.setCm_tx(cm_tx);
		prod.setFj_f(fj_f);
		prod.setGzty_lv(gzty_lv);
		prod.setRule_lv(rule_lv);
		prod.setBz_t(bz_t);
		prod.setJy_f(jy_f);
		prod.setCgj(cgj);
		prod.setFjj(fjj);
		prod.setFlg(flg);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iProdService.addprod(prod).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(prod.getId()+"\"");
			logger.info(result+"添加成功！");
			
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("prod");
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
			paramMap.put("bu_id", bu_id);
			paramMap.put("nm_t", nm_t);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("dh_lv", dh_lv);
			paramMap.put("zq_n", zq_n);
			paramMap.put("qz_n", qz_n);
			paramMap.put("lb_lv", lb_lv);
			paramMap.put("sy_id", sy_id);
			paramMap.put("st_lv", st_lv);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("fj_f", fj_f);
			paramMap.put("gzty_lv", gzty_lv);
			paramMap.put("rule_lv", rule_lv);
			paramMap.put("bz_t", bz_t);
			paramMap.put("jy_f", jy_f);
			paramMap.put("cgj", cgj);
			paramMap.put("fjj", fjj);
			paramMap.put("flg", flg);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iProdService.selectprodByParam(paramMap); 
			totalnumber=iProdService.selectCountprodByParam(paramMap);
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
	
	
	private String testid;
	public String getTestid() {
		return testid;
	}
	public void setTestid(String testid) {
		this.testid = testid;
	}
	private String prodids;
	
	public String getProdids() {
		return prodids;
	}
	public void setProdids(String prodids) {
		this.prodids = prodids;
	}
	@Autowired
	private ITestService iTestService;
	@Autowired
	private ISampleService iSampleService;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String appTree() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			
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
			
			if(prodids!=null&&!prodids.equals("")&&testid!=null&&!testid.equals("")){
				msg.append("\"failure\",\"msg\":");
				msg.append("\"查询条件重复.\"");
			}
			else if(testid!=null&&!testid.equals("")){
				Test test = iTestService.selecttestById(testid);
				if(test!=null){
					Sample sample=iSampleService.selectsampleById(test.getSample_id());
					
					if(sample!=null){
						Map  paramMap = new HashMap (); 
						paramMap.put("pid", sample.getProd_id());
						paramMap.put("ty_lv", "检测项目");
						int num = iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",num); 
						List<Prod> prodList=iProdService.selectprodByParam(paramMap);
						if(prodList.size()>0){
							//所有结果
							JSONArray allJA= new JSONArray();
							for(Prod temp:prodList){
								//parent
								JSONObject prodJO= JSONObject.fromObject(temp, jsonConfig);
								paramMap = new HashMap (); 
								paramMap.put("pid", temp.getId());
								paramMap.put("ty_lv", "检验属性");
								/*paramMap.put("jy_f", "Y");*/
								int tempnum = iProdService.selectCountprodByParam(paramMap);
								paramMap.put("fromPage",0);
								paramMap.put("toPage",tempnum); 
								//sub
								List<Prod> sonProdList=iProdService.selectprodByParam(paramMap);
								prodJO.accumulate("sub", JSONArray.fromObject(sonProdList, jsonConfig));
								allJA.add(prodJO);
								
							}
							msg.append("\"success\",\"msg\":");
							msg.append(allJA);
							logger.info("获取列表成功！");
						}
						else{
							msg.append("\"failure\",\"msg\":");
							msg.append("\"检测项目不存在.\"");
						}
						
					}
					else{
						msg.append("\"failure\",\"msg\":");
						msg.append("\"样品不存在.\"");
					}
				}
				else{
					msg.append("\"failure\",\"msg\":");
					msg.append("\"试验单不存在.\"");
				}
				
			}
			else if(prodids!=null&&!prodids.equals("")){
				String[] allProd= prodids.split("\\|"); 
				//所有结果
				JSONArray allJA= new JSONArray();
				for(String prodid :allProd){
					Prod temp = iProdService.selectprodById(prodid);
					//parent
					JSONObject prodJO= JSONObject.fromObject(temp, jsonConfig);
					Map  paramMap = new HashMap (); 
					paramMap.put("pid", temp.getId());
					paramMap.put("ty_lv", "检验属性");
					/*paramMap.put("jy_f", "Y");*/
					int tempnum = iProdService.selectCountprodByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",tempnum); 
					//sub
					List<Prod> sonProdList=iProdService.selectprodByParam(paramMap);
					prodJO.accumulate("sub", JSONArray.fromObject(sonProdList, jsonConfig));
					allJA.add(prodJO);
					 
				}
				msg.append("\"success\",\"msg\":");
				msg.append(allJA);
				logger.info("获取列表成功！");
				
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String tree() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			
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
			
			Map  paramMap = new HashMap (); 
			paramMap.put("pid", pid);
			paramMap.put("ty_lv", "检测项目");
			paramMap.put("flg", "Y");
			int num = iProdService.selectCountprodByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",num); 
			List<Prod> prodList=iProdService.selectprodByParam(paramMap);
			if(prodList.size()>0){
				//所有结果
				JSONArray allJA= new JSONArray();
				for(Prod temp:prodList){
					//parent
					JSONObject prodJO= JSONObject.fromObject(temp, jsonConfig);
					paramMap = new HashMap (); 
					paramMap.put("pid", temp.getId());
					paramMap.put("ty_lv", "检验子项目");
					paramMap.put("flg", "Y");
					int tempnum = iProdService.selectCountprodByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",tempnum); 
					//sub
					List<Prod> sonProdList=iProdService.selectprodByParam(paramMap);
					prodJO.accumulate("sub", JSONArray.fromObject(sonProdList, jsonConfig));
					allJA.add(prodJO);
					
				}
				msg.append("\"success\",\"msg\":");
				msg.append(allJA);
				logger.info("获取列表成功！");
			}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"检测项目不存在.\"");
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String subTree() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			
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
			
			if(prodids!=null&&!prodids.equals("")&&testid!=null&&!testid.equals("")){
				msg.append("\"failure\",\"msg\":");
				msg.append("\"查询条件重复.\"");
			}
			else if(testid!=null&&!testid.equals("")){
				Test test = iTestService.selecttestById(testid);
				if(test!=null){
					Sample sample=iSampleService.selectsampleById(test.getSample_id());
					
					if(sample!=null){
						Map  paramMap = new HashMap (); 
						paramMap.put("pid", sample.getProd_id());
						paramMap.put("ty_lv", "检测项目");
						int num = iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",num); 
						List<Prod> prodList=iProdService.selectprodByParam(paramMap);
						if(prodList.size()>0){
							//所有结果
							JSONArray allJA= new JSONArray();
							for(Prod temp:prodList){
								//parent
								JSONObject prodJO= JSONObject.fromObject(temp, jsonConfig);
								paramMap = new HashMap (); 
								paramMap.put("pid", temp.getId());
								paramMap.put("ty_lv", "检验属性");
								/*paramMap.put("jy_f", "Y");*/
								int tempnum = iProdService.selectCountprodByParam(paramMap);
								paramMap.put("fromPage",0);
								paramMap.put("toPage",tempnum); 
								//sub
								List<Prod> sonProdList=iProdService.selectprodByParam(paramMap);
								prodJO.accumulate("sub", JSONArray.fromObject(sonProdList, jsonConfig));
								allJA.add(prodJO);
								
							}
							msg.append("\"success\",\"msg\":");
							msg.append(allJA);
							logger.info("获取列表成功！");
						}
						else{
							msg.append("\"failure\",\"msg\":");
							msg.append("\"检测项目不存在.\"");
						}
						
					}
					else{
						msg.append("\"failure\",\"msg\":");
						msg.append("\"样品不存在.\"");
					}
				}
				else{
					msg.append("\"failure\",\"msg\":");
					msg.append("\"试验单不存在.\"");
				}
				
			}
			else if(prodids!=null&&!prodids.equals("")){
				String[] allProd= prodids.split("\\|"); 
				//所有结果
				JSONArray allJA= new JSONArray();
				for(String prodid :allProd){
					Prod temp = iProdService.selectprodById(prodid);
					//parent
					JSONObject prodJO= JSONObject.fromObject(temp, jsonConfig);
					Map  paramMap = new HashMap (); 
					paramMap.put("pid", temp.getId());
					paramMap.put("ty_lv", "检验子项目");
					paramMap.put("flg", "Y");
					int tempnum = iProdService.selectCountprodByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",tempnum); 
					//sub
					List<Prod> sonProdList=iProdService.selectprodByParam(paramMap);
					prodJO.accumulate("sub", JSONArray.fromObject(sonProdList, jsonConfig));
					allJA.add(prodJO);
					 
				}
				msg.append("\"success\",\"msg\":");
				msg.append(allJA);
				logger.info("获取列表成功！");
				
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

	public String update() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Prod prod =new Prod(); 
		if(id!=null&&!id.equals(""))
		prod.setId(Long.parseLong(id));
		prod.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		prod.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		prod.setUp_dt(sdf.parse(up_dt));
		prod.setC_id(c_id);
		prod.setPid(pid);
		prod.setBu_id(bu_id);
		prod.setNm_t(nm_t);
		prod.setTy_lv(ty_lv);
		prod.setDh_lv(dh_lv);
		if(zq_n!=null&&!zq_n.equals(""))
		prod.setZq_n(Long.parseLong(zq_n));
		prod.setQz_n(qz_n);
		prod.setLb_lv(lb_lv);
		prod.setSy_id(sy_id);
		prod.setSt_lv(st_lv);
		prod.setCm_tx(cm_tx);
		prod.setFj_f(fj_f);
		prod.setGzty_lv(gzty_lv);
		prod.setRule_lv(rule_lv);
		prod.setBz_t(bz_t);
		prod.setJy_f(jy_f);
		prod.setCgj(cgj);
		prod.setFjj(fjj);
		prod.setFlg(flg);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iProdService.updateprod(prod);
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("prod");
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
			iProdService.deleteprod(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("prod");
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
			prod=iProdService.selectprodById(id);
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
			msg.append(JSONObject.fromObject(prod, jsonConfig));
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
		paramMap.put("bu_id", bu_id);
		paramMap.put("nm_t", nm_t);
		paramMap.put("ty_lv", ty_lv);
		paramMap.put("dh_lv", dh_lv);
		paramMap.put("zq_n", zq_n);
		paramMap.put("qz_n", qz_n);
		paramMap.put("lb_lv", lb_lv);
		paramMap.put("sy_id", sy_id);
		paramMap.put("st_lv", st_lv);
		paramMap.put("cm_tx", cm_tx);
		paramMap.put("fj_f", fj_f);
		paramMap.put("gzty_lv", gzty_lv);
		paramMap.put("rule_lv", rule_lv);
		paramMap.put("bz_t", bz_t);
		paramMap.put("jy_f", jy_f);
		paramMap.put("cgj", cgj);
		paramMap.put("fjj", fjj);
		paramMap.put("flg", flg);
		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iProdService.selectCountprodByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iProdService.selectprodByParam(paramMap); 
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
	    	String result= RedisUtil.getObject("JC", "prod");
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
			String result= RedisUtil.getObject("JC", "prod");
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
