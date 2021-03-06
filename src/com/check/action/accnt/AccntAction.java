package com.check.action.accnt;
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

import com.check.model.accnt.Accnt;
import com.check.service.accnt.IAccntService;
import com.check.utils.CacheToRedis;
import com.check.utils.RedisUtil;
import com.opensymphony.xwork2.Action;
public class AccntAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IAccntService iAccntService;
	
	private List<Accnt> list;
	private Accnt accnt;
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
	public Accnt getAccnt() {
		return accnt;
	}
	public void setAccnt(Accnt accnt) {
		this.accnt = accnt;
	}
	public List<Accnt> getList() {
		return list;
	}
	public void setList(List<Accnt> list) {
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
	private String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	private String xz_lv;
	public String getXz_lv() {
		return xz_lv;
	}
	public void setXz_lv(String xz_lv) {
		this.xz_lv = xz_lv;
	}
	private String wz_t;
	public String getWz_t() {
		return wz_t;
	}
	public void setWz_t(String wz_t) {
		this.wz_t = wz_t;
	}
	private String hy_lv;
	public String getHy_lv() {
		return hy_lv;
	}
	public void setHy_lv(String hy_lv) {
		this.hy_lv = hy_lv;
	}
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String lv_lv;
	public String getLv_lv() {
		return lv_lv;
	}
	public void setLv_lv(String lv_lv) {
		this.lv_lv = lv_lv;
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
	private String work_p;
	public String getWork_p() {
		return work_p;
	}
	public void setWork_p(String work_p) {
		this.work_p = work_p;
	}
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	private String postal;
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String cont_id;
	public String getCont_id() {
		return cont_id;
	}
	public void setCont_id(String cont_id) {
		this.cont_id = cont_id;
	}
	
	//联系人
	
	private String cont;
	
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	
	//附件
	private String atta;
	public String getAtta() {
		return atta;
	}
	public void setAtta(String atta) {
		this.atta = atta;
	}
	
	//主要联系人
	private String c_nm_t;
	private String c_ph_p;
	
	
	 
	public String getC_nm_t() {
		return c_nm_t;
	}
	public void setC_nm_t(String c_nm_t) {
		this.c_nm_t = c_nm_t;
	}
	public String getC_ph_p() {
		return c_ph_p;
	}
	public void setC_ph_p(String c_ph_p) {
		this.c_ph_p = c_ph_p;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Accnt accnt =new Accnt(); 
		if(id!=null&&!id.equals(""))
		accnt.setId(Long.parseLong(id));
		accnt.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		accnt.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		accnt.setUp_dt(sdf.parse(up_dt));
		accnt.setC_id(c_id);
		accnt.setNm_t(nm_t);
		accnt.setXz_lv(xz_lv);
		accnt.setWz_t(wz_t);
		accnt.setHy_lv(hy_lv);
		accnt.setTy_lv(ty_lv);
		accnt.setLv_lv(lv_lv);
		accnt.setSt_lv(st_lv);
		accnt.setCm_tx(cm_tx);
		accnt.setWork_p(work_p);
		accnt.setEmail(email);
		accnt.setState(state);
		accnt.setCity(city);
		accnt.setCounty(county);
		accnt.setStreet(street);
		accnt.setPostal(postal);
		accnt.setBu_id(bu_id);
		//accnt.setCont_id(cont_id);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = iAccntService.addallaccnt(accnt,cont,atta);
			if(result>0){
				/*if(cont!=null){
					
					JSONArray contJA=JSONArray.fromObject(cont);
					for(int i=0;i<contJA.size();i++){
						JSONObject  contJO = (JSONObject) contJA.get(i);
						
						Cont cont = new Cont();
						//cont.setRow_id(contJO.getString("row_id"));
						if(contJO.containsKey("c_id"))
						cont.setC_id(contJO.getString("c_id"));
						if(contJO.containsKey("nm_t"))
						cont.setNm_t(contJO.getString("nm_t"));
						//cont.setJob(contJO.getString("job"));
						//cont.setSex(contJO.getString("sex"));
						if(contJO.containsKey("ph_p"))
						cont.setPh_p(contJO.getString("ph_p"));
						//cont.setQq(contJO.getString("qq")); 
						if(contJO.containsKey("bu_id"))
						cont.setBu_id(Long.parseLong(contJO.getString("bu_id")));
						cont.setPid(accnt.getId());
						iContService.addcont(cont); 
						//flag   =  Y/N
						//flag   =  Y/N
						if(contJO.getString("flag").equals("Y")){
							Accnt tempAccnt = new Accnt();
							tempAccnt.setCont_id(cont.getId()+"");
							tempAccnt.setId(accnt.getId());
							tempAccnt.setUp_dt(null);
							iAccntService.updateaccnt(tempAccnt);
						}
						
					}
					
					
				}
				
				if(atta!=null){
					
					JSONArray attaJA=JSONArray.fromObject(atta);
					for(int i=0;i<attaJA.size();i++){
						JSONObject  attaJO = (JSONObject) attaJA.get(i);
						Atta atta = new Atta();
						if(attaJO.containsKey("c_id"))
						atta.setC_id(attaJO.getString("c_id"));
						//atta.setCm_tx(attaJO.getString("cm_tx"));
						if(attaJO.containsKey("nm_t"))
						atta.setNm_t(attaJO.getString("nm_t"));
						if(attaJO.containsKey("type"))
						atta.setType(attaJO.getString("type"));
						if(attaJO.containsKey("url"))
						atta.setUrl(attaJO.getString("url"));
						atta.setPid(accnt.getId()+"");
						iAttaService.addatta(atta);
					}
					
				}*/
				
				msg.append("\"success\",\"msg\":\"");
				msg.append(accnt.getId()+"\"");
				logger.info(result+"添加成功！");
				
				//缓存
				List<String> tableList = new ArrayList<String>(); 
		        tableList.add("accnt");
		        CacheToRedis.cache(tableList);
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
	public String appAdd() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Accnt accnt =new Accnt(); 
		if(id!=null&&!id.equals(""))
			accnt.setId(Long.parseLong(id));
		accnt.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
			accnt.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
			accnt.setUp_dt(sdf.parse(up_dt));
		accnt.setC_id(c_id);
		accnt.setNm_t(nm_t);
		accnt.setXz_lv(xz_lv);
		accnt.setWz_t(wz_t);
		accnt.setHy_lv(hy_lv);
		accnt.setTy_lv(ty_lv);
		accnt.setLv_lv(lv_lv);
		accnt.setSt_lv(st_lv);
		accnt.setCm_tx(cm_tx);
		accnt.setWork_p(work_p);
		accnt.setEmail(email);
		accnt.setState(state);
		accnt.setCity(city);
		accnt.setCounty(county);
		accnt.setStreet(street);
		accnt.setPostal(postal);
		accnt.setBu_id(bu_id);
		//accnt.setCont_id(cont_id);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = iAccntService.addallaccnt(accnt,cont,atta);
			if(result>0){
				
				
				msg.append("\"success\",\"msg\":\"");
				msg.append(accnt.getId()+"\"");
				logger.info(result+"添加成功！");
				
				//缓存
				List<String> tableList = new ArrayList<String>(); 
				tableList.add("accnt");
				CacheToRedis.cache(tableList);
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
			paramMap.put("nm_t", nm_t);
			paramMap.put("xz_lv", xz_lv);
			paramMap.put("wz_t", wz_t);
			paramMap.put("hy_lv", hy_lv);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("lv_lv", lv_lv);
			paramMap.put("st_lv", st_lv);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("work_p", work_p);
			paramMap.put("email", email);
			paramMap.put("state", state);
			paramMap.put("city", city);
			paramMap.put("county", county);
			paramMap.put("street", street);
			paramMap.put("postal", postal);
			paramMap.put("bu_id", bu_id);
			paramMap.put("cont_id", cont_id);
			paramMap.put("c_nm_t", c_nm_t);
			paramMap.put("c_ph_p", c_ph_p);

		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iAccntService.selectaccntByParam(paramMap); 
			totalnumber=iAccntService.selectCountaccntByParam(paramMap);
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
		Accnt accnt =new Accnt(); 
		if(id!=null&&!id.equals(""))
		accnt.setId(Long.parseLong(id));
		accnt.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		accnt.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		accnt.setUp_dt(sdf.parse(up_dt));
		accnt.setC_id(c_id);
		accnt.setNm_t(nm_t);
		accnt.setXz_lv(xz_lv);
		accnt.setWz_t(wz_t);
		accnt.setHy_lv(hy_lv);
		accnt.setTy_lv(ty_lv);
		accnt.setLv_lv(lv_lv);
		accnt.setSt_lv(st_lv);
		accnt.setCm_tx(cm_tx);
		accnt.setWork_p(work_p);
		accnt.setEmail(email);
		accnt.setState(state);
		accnt.setCity(city);
		accnt.setCounty(county);
		accnt.setStreet(street);
		accnt.setPostal(postal);
		accnt.setBu_id(bu_id);
		
		//accnt.setCont_id(cont_id);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			//int result = iAccntService.updateallaccnt(accnt,cont,atta);
			iAccntService.updateallaccnt(accnt,cont,atta);
			 
			/*if(cont!=null){
				Map  paramMap = new HashMap ();
				paramMap.put("pid",id); 
				int count= iContService.selectCountcontByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",count); 
				List<Cont> contList= iContService.selectcontByParam(paramMap);
				
				List<Cont>  copyList = new ArrayList<Cont>();
				copyList.addAll(contList);
				JSONArray contJA=JSONArray.fromObject(cont);
				for(int i=0;i<contJA.size();i++){
					JSONObject  contJO = (JSONObject) contJA.get(i);
					//循环 后期优化掉
					for(Cont temp:contList){
						if(temp.getId().toString().equals(contJO.getString("id"))){
							copyList.remove(temp);
						}
					}
					
					Cont cont = new Cont();
					//cont.setRow_id(contJO.getString("row_id"));
					cont.setC_id(contJO.getString("c_id"));
					cont.setNm_t(contJO.getString("nm_t"));
					//cont.setJob(contJO.getString("job"));
					//cont.setSex(contJO.getString("sex"));
					cont.setPh_p(contJO.getString("ph_p"));
					//cont.setQq(contJO.getString("qq")); 
					cont.setBu_id(Long.parseLong(contJO.getString("bu_id")));
					cont.setPid(accnt.getId());
					if(!contJO.getString("id").equals(""))
					cont.setId(Long.parseLong(contJO.getString("id")));
					//id 为空新建 否则修改
					if(cont.getId()==null){
						iContService.addcont(cont); 
					}
					else{
						iContService.updatecont(cont);
					}
					
					//flag   =  Y/N
					if(contJO.getString("flag").equals("Y")){
						Accnt tempAccnt = new Accnt();
						tempAccnt.setCont_id(cont.getId()+"");
						tempAccnt.setId(accnt.getId());
						tempAccnt.setUp_dt(null);
						iAccntService.updateaccnt(tempAccnt);
					}
					
				}
				//没有对应数据删除
				for(Cont temp:copyList){
					temp.setPid(Long.valueOf("-1"));
					temp.setUp_dt(null);
					iContService.updatecont(temp);
				}
				
				
			}
			
			if(atta!=null){
				
				JSONArray attaJA=JSONArray.fromObject(atta);
				for(int i=0;i<attaJA.size();i++){
					JSONObject  attaJO = (JSONObject) attaJA.get(i);
					Atta atta = new Atta();
					atta.setC_id(attaJO.getString("c_id"));
					//atta.setCm_tx(attaJO.getString("cm_tx"));
					atta.setNm_t(attaJO.getString("nm_t"));
					atta.setType(attaJO.getString("type"));
					atta.setUrl(attaJO.getString("url"));
					atta.setPid(accnt.getId()+"");
					iAttaService.addatta(atta);
				}
				
			}*/
			 
			msg.append("\"success\",\"msg\":");
			msg.append("\"更新成功！\"");
			logger.info(id+"更新成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("accnt");
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
			iAccntService.deleteaccnt(id);
			msg.append("\"success\",\"msg\":");
			msg.append("\"删除成功！\"");
			logger.info(id+"删除成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("accnt");
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
			accnt=iAccntService.selectaccntById(id);
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
			msg.append(JSONObject.fromObject(accnt, jsonConfig));
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
		paramMap.put("nm_t", nm_t);
		paramMap.put("xz_lv", xz_lv);
		paramMap.put("wz_t", wz_t);
		paramMap.put("hy_lv", hy_lv);
		paramMap.put("ty_lv", ty_lv);
		paramMap.put("lv_lv", lv_lv);
		paramMap.put("st_lv", st_lv);
		paramMap.put("cm_tx", cm_tx);
		paramMap.put("work_p", work_p);
		paramMap.put("email", email);
		paramMap.put("state", state);
		paramMap.put("city", city);
		paramMap.put("county", county);
		paramMap.put("street", street);
		paramMap.put("postal", postal);
		paramMap.put("bu_id", bu_id);
		paramMap.put("cont_id", cont_id);
		//StringBuffer msg = new StringBuffer("{\"state\":");
		msg.append("{\"state\":");
		try {
			totalnumber=iAccntService.selectCountaccntByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iAccntService.selectaccntByParam(paramMap); 
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
	    	String result= RedisUtil.getObject("JC", "accnt");
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
			String result= RedisUtil.getObject("JC", "accnt");
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
    	
    	/*//缓存
		List<String> tableList = new ArrayList<String>(); 
        tableList.add("accnt");
        CacheToRedis.cache(tableList);*/
    	//缓存
    	JSONArray resultJA=new JSONArray();
    	String result= RedisUtil.getObject("JC", "accnt");
    	JSONArray ja = JSONArray.fromObject(result);
    	for(int i =0;i<ja.size();i++){
    		JSONObject   jo= (JSONObject) ja.get(i);
    		if(sdf.parse(jo.getString("up_dt")).after(sdf.parse(up_dtFrom))){
    			resultJA.add(jo);
    		}
    		 
    	}
    	
    	System.out.println(resultJA);
		return null;
	}
    
    
    private String accntids;
    public String getAccntids() {
		return accntids;
	}
	public void setAccntids(String accntids) {
		this.accntids = accntids;
	}
	 
	
	public String appTree() throws Exception {
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			String result="";
			if(accntids!=null && !accntids.equals("")){
				result=iAccntService.selectAppTreeAccnt(accntids,up_dtFrom);
				
				if(!result.equals("")){
					msg.append("\"success\",\"msg\":");
					msg.append(result);
					logger.info(accntids+"查询成功！");
				}
				else{
					msg.append("\"failure\",\"msg\":");
					msg.append("\"查询失败.\"");
				}
			}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"参数不能为空.\"");
			}
			 
			
			
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
    
    
}
