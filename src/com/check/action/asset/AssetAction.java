package com.check.action.asset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.check.service.asset.IAssetService;
import com.check.model.asset.Asset;
public class AssetAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IAssetService iAssetService;
	@Autowired
	private IAccntService iAccntService;
	private List<Asset> list;
	private Asset asset;
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
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public List<Asset> getList() {
		return list;
	}
	public void setList(List<Asset> list) {
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
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String xh_t;
	public String getXh_t() {
		return xh_t;
	}
	public void setXh_t(String xh_t) {
		this.xh_t = xh_t;
	}
	private String  cj_id;
	 
	public String getCj_id() {
		return cj_id;
	}
	public void setCj_id(String cj_id) {
		this.cj_id = cj_id;
	}
	private String e_dtFrom;
	public String getE_dtFrom() {
		return e_dtFrom;
	}
	public void setE_dtFrom(String e_dtFrom) {
		this.e_dtFrom = e_dtFrom;
	}
	private String e_dtTo;
	public String getE_dtTo() {
		return e_dtTo;
	}
	public void setE_dtTo(String e_dtTo) {
		this.e_dtTo = e_dtTo;
	}
	private String e_dt;
	public String getE_dt() {
		return e_dt;
	}
	public void setE_dt(String e_dt) {
		this.e_dt = e_dt;
	}
	private String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	private String fz_id;
	public String getFz_id() {
		return fz_id;
	}
	public void setFz_id(String fz_id) {
		this.fz_id = fz_id;
	}
	private String jd_t;
	public String getJd_t() {
		return jd_t;
	}
	public void setJd_t(String jd_t) {
		this.jd_t = jd_t;
	}
	private String gm_dtFrom;
	public String getGm_dtFrom() {
		return gm_dtFrom;
	}
	public void setGm_dtFrom(String gm_dtFrom) {
		this.gm_dtFrom = gm_dtFrom;
	}
	private String gm_dtTo;
	public String getGm_dtTo() {
		return gm_dtTo;
	}
	public void setGm_dtTo(String gm_dtTo) {
		this.gm_dtTo = gm_dtTo;
	}
	private String gm_dt;
	public String getGm_dt() {
		return gm_dt;
	}
	public void setGm_dt(String gm_dt) {
		this.gm_dt = gm_dt;
	}
	private String s_dt;
	public String getS_dt() {
		return s_dt;
	}
	public void setS_dt(String s_dt) {
		this.s_dt = s_dt;
	}
	private String sc_dt;
	public String getSc_dt() {
		return sc_dt;
	}
	public void setSc_dt(String sc_dt) {
		this.sc_dt = sc_dt;
	}
	private String zq_n;
	public String getZq_n() {
		return zq_n;
	}
	public void setZq_n(String zq_n) {
		this.zq_n = zq_n;
	}
	private String yh_dt;
	public String getYh_dt() {
		return yh_dt;
	}
	public void setYh_dt(String yh_dt) {
		this.yh_dt = yh_dt;
	}
	private String flag; 
	private String bxjz_dt;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getBxjz_dt() {
		return bxjz_dt;
	}
	public void setBxjz_dt(String bxjz_dt) {
		this.bxjz_dt = bxjz_dt;
	}
	private String m_name;
	private String a_nm_t; 
	private String next_yh_dt;
	 
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getA_nm_t() {
		return a_nm_t;
	}
	public void setA_nm_t(String a_nm_t) {
		this.a_nm_t = a_nm_t;
	}
	public String getNext_yh_dt() {
		return next_yh_dt;
	}
	public void setNext_yh_dt(String next_yh_dt) {
		this.next_yh_dt = next_yh_dt;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Asset asset =new Asset(); 
		if(id!=null&&!id.equals(""))
		asset.setId(Long.parseLong(id));
		asset.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		asset.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		asset.setUp_dt(sdf.parse(up_dt));
		asset.setC_id(c_id);
		asset.setBu_id(bu_id);
		asset.setNm_t(nm_t);
		asset.setCode(code);
		asset.setXh_t(xh_t);
		if(cj_id!=null&&!cj_id.equals(""))
		asset.setCj_id(Long.parseLong(cj_id));
		if(e_dt!=null&&!e_dt.equals(""))
		asset.setE_dt(sdf.parse(e_dt));
		asset.setSt_lv(st_lv);
		asset.setFz_id(fz_id);
		asset.setJd_t(jd_t);
		if(gm_dt!=null&&!gm_dt.equals(""))
		asset.setGm_dt(sdf.parse(gm_dt));
		asset.setS_dt(s_dt);
		asset.setSc_dt(sc_dt);
		if(zq_n!=null&&!zq_n.equals(""))
		asset.setZq_n(Long.parseLong(zq_n));
		if(yh_dt!=null&&!yh_dt.equals(""))
		asset.setYh_dt(sdf.parse(yh_dt));
		asset.setFlag(flag);
		asset.setBxjz_dt(bxjz_dt);
		
	
		
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iAssetService.addasset(asset).toString());
			msg.append("\"success\",\"msg\":\"");
			msg.append(asset.getId()+"\"");
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
			paramMap.put("bu_id", bu_id);
			paramMap.put("nm_t", nm_t);
			paramMap.put("code", code);
			paramMap.put("xh_t", xh_t);
			paramMap.put("cj_id", cj_id);
			if(e_dtFrom!=null&&!e_dtFrom.equals(""))
			paramMap.put("e_dtFrom", sdf.parse(e_dtFrom));
			if(e_dtTo!=null&&!e_dtTo.equals(""))
			paramMap.put("e_dtTo", sdf.parse(e_dtTo));
			paramMap.put("st_lv", st_lv);
			paramMap.put("fz_id", fz_id);
			paramMap.put("jd_t", jd_t);
			if(gm_dtFrom!=null&&!gm_dtFrom.equals(""))
			paramMap.put("gm_dtFrom", sdf.parse(gm_dtFrom));
			if(gm_dtTo!=null&&!gm_dtTo.equals(""))
			paramMap.put("gm_dtTo", sdf.parse(gm_dtTo));
			paramMap.put("s_dt", s_dt);
			paramMap.put("sc_dt", sc_dt);
			paramMap.put("zq_n", zq_n);
			paramMap.put("yh_dt", yh_dt);
			paramMap.put("flag", flag);
			paramMap.put("bxjz_dt", bxjz_dt);
			paramMap.put("m_name", m_name);
			paramMap.put("a_nm_t", a_nm_t);
			
			 
			  
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iAssetService.selectassetByParam(paramMap); 
			List<Asset> tempList= new ArrayList<Asset>();
			for(Asset temp:list){
				//下次养护日期
				if(temp.getYh_dt()!=null&&temp.getZq_n()!=null){
					Date n_yh_dt=temp.getYh_dt();
					do{
						GregorianCalendar gc=new GregorianCalendar();
			            gc.setTime(n_yh_dt);
			            gc.add(5, Integer.parseInt(temp.getZq_n()+"")); 
			            n_yh_dt=gc.getTime();
					}
					while(n_yh_dt.before(new Date()));
					temp.setNext_yh_dt(sdf.format(n_yh_dt));
				}
				tempList.add(temp);
				
			}
			totalnumber=iAssetService.selectCountassetByParam(paramMap);
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
			msg.append(JSONArray.fromObject(tempList, jsonConfig));
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
		Asset asset =new Asset(); 
		if(id!=null&&!id.equals(""))
		asset.setId(Long.parseLong(id));
		asset.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		asset.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		asset.setUp_dt(sdf.parse(up_dt));
		asset.setC_id(c_id);
		asset.setBu_id(bu_id);
		asset.setNm_t(nm_t);
		asset.setCode(code);
		asset.setXh_t(xh_t);
		if(cj_id!=null&&!cj_id.equals(""))
		asset.setCj_id(Long.parseLong(cj_id));
		if(e_dt!=null&&!e_dt.equals(""))
		asset.setE_dt(sdf.parse(e_dt));
		asset.setSt_lv(st_lv);
		asset.setFz_id(fz_id);
		asset.setJd_t(jd_t);
		if(gm_dt!=null&&!gm_dt.equals(""))
		asset.setGm_dt(sdf.parse(gm_dt));
		asset.setS_dt(s_dt);
		asset.setSc_dt(sc_dt);
		if(zq_n!=null&&!zq_n.equals(""))
		asset.setZq_n(Long.parseLong(zq_n));
		if(yh_dt!=null&&!yh_dt.equals(""))
			asset.setYh_dt(sdf.parse(yh_dt));
		asset.setFlag(flag);
		asset.setBxjz_dt(bxjz_dt);
		
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iAssetService.updateasset(asset);
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
			iAssetService.deleteasset(id);
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
			asset=iAssetService.selectassetById(id);
			//下次养护日期
			if(asset.getYh_dt()!=null&&asset.getZq_n()!=null){
				Date n_yh_dt=asset.getYh_dt();
				do{
					GregorianCalendar gc=new GregorianCalendar();
		            gc.setTime(n_yh_dt);
		            gc.add(5, Integer.parseInt(asset.getZq_n()+"")); 
		            n_yh_dt=gc.getTime();
		            //entrust.setJh_dt(sdf.format(gc.getTime()));
				}
				while(n_yh_dt.before(new Date()));
				asset.setNext_yh_dt(sdf.format(n_yh_dt));
			}
			
	            
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
			msg.append(JSONObject.fromObject(asset, jsonConfig));
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
			paramMap.put("bu_id", bu_id);
			paramMap.put("nm_t", nm_t);
			paramMap.put("code", code);
			paramMap.put("xh_t", xh_t);
			paramMap.put("cj_id", cj_id);
			if(e_dtFrom!=null&&!e_dtFrom.equals(""))
			paramMap.put("e_dtFrom", sdf.parse(e_dtFrom));
			if(e_dtTo!=null&&!e_dtTo.equals(""))
			paramMap.put("e_dtTo", sdf.parse(e_dtTo));
			paramMap.put("st_lv", st_lv);
			paramMap.put("fz_id", fz_id);
			paramMap.put("jd_t", jd_t);
			if(gm_dtFrom!=null&&!gm_dtFrom.equals(""))
			paramMap.put("gm_dtFrom", sdf.parse(gm_dtFrom));
			if(gm_dtTo!=null&&!gm_dtTo.equals(""))
			paramMap.put("gm_dtTo", sdf.parse(gm_dtTo));
			paramMap.put("s_dt", s_dt);
			paramMap.put("sc_dt", sc_dt);
			paramMap.put("zq_n", zq_n);
			paramMap.put("yh_dt", yh_dt);
			paramMap.put("flag", flag);
			paramMap.put("bxjz_dt", bxjz_dt);
			paramMap.put("m_name", m_name);
			paramMap.put("a_nm_t", a_nm_t);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iAssetService.selectCountassetByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iAssetService.selectassetByParam(paramMap); 
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
