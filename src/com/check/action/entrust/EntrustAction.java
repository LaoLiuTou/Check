package com.check.action.entrust;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.model.accnt.Accnt;
import com.check.model.entrust.Entrust;
import com.check.model.entrust_sample.Entrust_sample;
import com.check.model.lot.Lot;
import com.check.model.lov.Lov;
import com.check.model.pact.Pact;
import com.check.model.prod.Prod;
import com.check.model.sample.Sample;
import com.check.service.accnt.IAccntService;
import com.check.service.entrust.IEntrustService;
import com.check.service.entrust_sample.IEntrust_sampleService;
import com.check.service.lot.ILotService;
import com.check.service.lov.ILovService;
import com.check.service.pact.IPactService;
import com.check.service.prod.IProdService;
import com.check.service.sample.ISampleService;
import com.check.utils.CacheToRedis;
import com.check.utils.CodeUtils;
import com.check.utils.MatrixToImageWriter;
import com.opensymphony.xwork2.Action;
public class EntrustAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private IEntrustService iEntrustService;
	@Autowired
	private IAccntService iAccntService;
	@Autowired
	private ILovService iLovService;
	@Autowired
	private ILotService iLotService;
	@Autowired
	private IPactService iPactService;
	@Autowired
	private IProdService iProdService;
	@Autowired
	private ISampleService iSampleService;
	@Autowired
	private IEntrust_sampleService iEntrust_sampleService;
	
	private List<Entrust> list;
	private Entrust entrust;
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
	public Entrust getEntrust() {
		return entrust;
	}
	public void setEntrust(Entrust entrust) {
		this.entrust = entrust;
	}
	public List<Entrust> getList() {
		return list;
	}
	public void setList(List<Entrust> list) {
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
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String prod_id;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String qy_lv;
	public String getQy_lv() {
		return qy_lv;
	}
	public void setQy_lv(String qy_lv) {
		this.qy_lv = qy_lv;
	}
	private String jl_id;
	public String getJl_id() {
		return jl_id;
	}
	public void setJl_id(String jl_id) {
		this.jl_id = jl_id;
	}
	private String sp_id;
	public String getSp_id() {
		return sp_id;
	}
	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}
	private String lq_id;
	public String getLq_id() {
		return lq_id;
	}
	public void setLq_id(String lq_id) {
		this.lq_id = lq_id;
	}
	private String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	private String pay_lv;
	public String getPay_lv() {
		return pay_lv;
	}
	public void setPay_lv(String pay_lv) {
		this.pay_lv = pay_lv;
	}
	private String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	private String cs_n;
	public String getCs_n() {
		return cs_n;
	}
	public void setCs_n(String cs_n) {
		this.cs_n = cs_n;
	}
	private String syr_id;
	public String getSyr_id() {
		return syr_id;
	}
	public void setSyr_id(String syr_id) {
		this.syr_id = syr_id;
	}
	private String jh_dt;
	public String getJh_dt() {
		return jh_dt;
	}
	public void setJh_dt(String jh_dt) {
		this.jh_dt = jh_dt;
	}
	private String cg_f;
	public String getCg_f() {
		return cg_f;
	}
	public void setCg_f(String cg_f) {
		this.cg_f = cg_f;
	}
	private String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	private String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	private String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	private String ls_n;
	public String getLs_n() {
		return ls_n;
	}
	public void setLs_n(String ls_n) {
		this.ls_n = ls_n;
	}
	private String gg_code;
	public String getGg_code() {
		return gg_code;
	}
	public void setGg_code(String gg_code) {
		this.gg_code = gg_code;
	}
	private String sy_code;
	public String getSy_code() {
		return sy_code;
	}
	public void setSy_code(String sy_code) {
		this.sy_code = sy_code;
	}
	private String ewm;
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String wt_dt;
	public String getWt_dt() {
		return wt_dt;
	}
	public void setWt_dt(String wt_dt) {
		this.wt_dt = wt_dt;
	}
	private String wt_dtFrom;
	private String wt_dtTo;
	
	public String getWt_dtFrom() {
		return wt_dtFrom;
	}
	public void setWt_dtFrom(String wt_dtFrom) {
		this.wt_dtFrom = wt_dtFrom;
	}
	public String getWt_dtTo() {
		return wt_dtTo;
	}
	public void setWt_dtTo(String wt_dtTo) {
		this.wt_dtTo = wt_dtTo;
	}
	private String submit;
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	private String sjwc_dt;
	public String getSjwc_dt() {
		return sjwc_dt;
	}
	public void setSjwc_dt(String sjwc_dt) {
		this.sjwc_dt = sjwc_dt;
	}
	private String ff_dt;
	public String getFf_dt() {
		return ff_dt;
	}
	public void setFf_dt(String ff_dt) {
		this.ff_dt = ff_dt;
	}
	private String fq_flg;
	public String getFq_flg() {
		return fq_flg;
	}
	public void setFq_flg(String fq_flg) {
		this.fq_flg = fq_flg;
	}
	private String spyj_t;
	public String getSpyj_t() {
		return spyj_t;
	}
	public void setSpyj_t(String spyj_t) {
		this.spyj_t = spyj_t;
	}
	private String copy_id;
	public String getCopy_id() {
		return copy_id;
	}
	public void setCopy_id(String copy_id) {
		this.copy_id = copy_id;
	}
	
	private String pact_nm_t;
	private String a_nm_t;
	private String prod_nm_t;
	public String getPact_nm_t() {
		return pact_nm_t;
	}
	public void setPact_nm_t(String pact_nm_t) {
		this.pact_nm_t = pact_nm_t;
	}
	public String getA_nm_t() {
		return a_nm_t;
	}
	public void setA_nm_t(String a_nm_t) {
		this.a_nm_t = a_nm_t;
	}
	public String getProd_nm_t() {
		return prod_nm_t;
	}
	public void setProd_nm_t(String prod_nm_t) {
		this.prod_nm_t = prod_nm_t;
	}

	
	private String sampleids;
	 
	public String getSampleids() {
		return sampleids;
	}
	public void setSampleids(String sampleids) {
		this.sampleids = sampleids;
	}
	
	
	private String entrust_samples;
	private String entrust_pins;
	
	 
	public String getEntrust_samples() {
		return entrust_samples;
	}
	public void setEntrust_samples(String entrust_samples) {
		this.entrust_samples = entrust_samples;
	}
	public String getEntrust_pins() {
		return entrust_pins;
	}
	public void setEntrust_pins(String entrust_pins) {
		this.entrust_pins = entrust_pins;
	}
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Entrust entrust =new Entrust(); 
		if(id!=null&&!id.equals(""))
		entrust.setId(Long.parseLong(id));
		entrust.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		entrust.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		entrust.setUp_dt(sdf.parse(up_dt));
		entrust.setC_id(c_id);
		//编号
		if(flg.equals("Y")){
			entrust.setCode(createAppend());
		}
		else{
			
			entrust.setCode(createCode());
		}
		
		
		entrust.setProd_id(prod_id);
		entrust.setTy_lv(ty_lv);
		entrust.setQy_lv(qy_lv);
		entrust.setJl_id(jl_id);
		entrust.setSp_id(sp_id);
		entrust.setLq_id(lq_id);
		entrust.setSt_lv(st_lv);
		entrust.setPay_lv(pay_lv);
		entrust.setPrice(price);
		if(cs_n!=null&&!cs_n.equals(""))
		entrust.setCs_n(Long.parseLong(cs_n));
		entrust.setSyr_id(syr_id);
		entrust.setJh_dt(jh_dt);
		entrust.setCg_f(cg_f);
		entrust.setCm_tx(cm_tx);
		entrust.setFlg(flg);
		entrust.setPid(pid);
		
		//流水号
		entrust.setLs_n(createLs_t());
		entrust.setGg_code(gg_code);
		entrust.setSy_code(sy_code);
		entrust.setEwm(ewm);
		entrust.setBu_id(bu_id);
		if(wt_dt!=null&&!wt_dt.equals(""))
		entrust.setWt_dt(sdf.parse(wt_dt));
		entrust.setSubmit(submit);
		entrust.setSjwc_dt(sjwc_dt);
		entrust.setFf_dt(ff_dt);
		entrust.setFq_flg(fq_flg);
		entrust.setSpyj_t(spyj_t);
		if(copy_id!=null&&!copy_id.equals(""))
		entrust.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iEntrustService.addentrust(entrust).toString());
			
			//修改  Entrust_sample   可删除   同时修改样品jd_lv--已接收 ；
								//删除 test   results
			
			 // 和新增  jd_lv 修改成  已委托  
								//新建  test  results
			
			
			if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("TG_"+entrust.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Entrust upentrust = new Entrust();
					upentrust.setId(entrust.getId());
					upentrust.setEwm(path+"/QRImages/"+qrResult);
					iEntrustService.updateentrust(upentrust);
				}
			}
			msg.append("\"success\",\"msg\":\"");
			msg.append(entrust.getId()+"\"");
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
	public String update() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Entrust entrust =new Entrust(); 
		if(id!=null&&!id.equals(""))
		entrust.setId(Long.parseLong(id));
		entrust.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		entrust.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		entrust.setUp_dt(sdf.parse(up_dt));
		entrust.setC_id(c_id);
		entrust.setCode(code);
		entrust.setProd_id(prod_id);
		entrust.setTy_lv(ty_lv);
		entrust.setQy_lv(qy_lv);
		entrust.setJl_id(jl_id);
		entrust.setSp_id(sp_id);
		entrust.setLq_id(lq_id);
		entrust.setSt_lv(st_lv);
		entrust.setPay_lv(pay_lv);
		entrust.setPrice(price);
		if(cs_n!=null&&!cs_n.equals(""))
		entrust.setCs_n(Long.parseLong(cs_n));
		entrust.setSyr_id(syr_id);
		entrust.setJh_dt(jh_dt);
		entrust.setCg_f(cg_f);
		entrust.setCm_tx(cm_tx);
		entrust.setFlg(flg);
		entrust.setPid(pid);
		entrust.setLs_n(ls_n);
		entrust.setGg_code(gg_code);
		entrust.setSy_code(sy_code);
		entrust.setEwm(ewm);
		entrust.setBu_id(bu_id);
		if(wt_dt!=null&&!wt_dt.equals(""))
		entrust.setWt_dt(sdf.parse(wt_dt));
		entrust.setSubmit(submit);
		entrust.setSjwc_dt(sjwc_dt);
		entrust.setFf_dt(ff_dt);
		entrust.setFq_flg(fq_flg);
		entrust.setSpyj_t(spyj_t);
		if(copy_id!=null&&!copy_id.equals(""))
		entrust.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iEntrustService.updateentrust(entrust);
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
	public String addFlow() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Entrust entrust =new Entrust(); 
		if(id!=null&&!id.equals(""))
		entrust.setId(Long.parseLong(id));
		entrust.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		entrust.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		entrust.setUp_dt(sdf.parse(up_dt));
		entrust.setC_id(c_id);
		//编号
		if(flg.equals("Y")){
			entrust.setCode(createAppend());
		}
		else{
			
			entrust.setCode(createCode());
		}
		
		
		entrust.setProd_id(prod_id);
		entrust.setTy_lv(ty_lv);
		entrust.setQy_lv(qy_lv);
		entrust.setJl_id(jl_id);
		entrust.setSp_id(sp_id);
		entrust.setLq_id(lq_id);
		entrust.setSt_lv(st_lv);
		entrust.setPay_lv(pay_lv);
		entrust.setPrice(price);
		if(cs_n!=null&&!cs_n.equals(""))
		entrust.setCs_n(Long.parseLong(cs_n));
		entrust.setSyr_id(syr_id);
		entrust.setJh_dt(jh_dt);
		entrust.setCg_f(cg_f);
		entrust.setCm_tx(cm_tx);
		entrust.setFlg(flg);
		entrust.setPid(pid);
		
		//流水号
		entrust.setLs_n(createLs_t());
		entrust.setGg_code(gg_code);
		entrust.setSy_code(sy_code);
		entrust.setEwm(ewm);
		entrust.setBu_id(bu_id);
		if(wt_dt!=null&&!wt_dt.equals(""))
		entrust.setWt_dt(sdf.parse(wt_dt));
		entrust.setSubmit(submit);
		entrust.setSjwc_dt(sjwc_dt);
		entrust.setFf_dt(ff_dt);
		entrust.setFq_flg(fq_flg);
		entrust.setSpyj_t(spyj_t);
		if(copy_id!=null&&!copy_id.equals(""))
		entrust.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			//int result = Integer.parseInt(iEntrustService.addentrust(entrust).toString());
			int result = iEntrustService.addentrustFlow(entrust,entrust_samples,entrust_pins);
			
			
			
			
			
			
			/*//修改  Entrust_sample   可删除   同时修改样品jd_lv--已接收 ；
								//删除 test   results
								//  修改  根据pid删除e_pin  然后在新建
             */			
			 // 新增 Entrust_sample 关系
			 //   修改  样品表jd_lv 修改成  已委托  
			 //新建  test  
			 //新建 results
			 // 新建 e_pin
			
			/*if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("TG_"+entrust.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Entrust upentrust = new Entrust();
					upentrust.setId(entrust.getId());
					upentrust.setEwm(path+"/QRImages/"+qrResult);
					iEntrustService.updateentrust(upentrust);
				}
			}*/
			msg.append("\"success\",\"msg\":\"");
			msg.append(entrust.getId()+"\"");
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
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String appAdd() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		
		 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			//if(entrustnum!=null&&!entrustnum.equals("")){
				
				//保存检测项目id不重复创建委托单
				List<Sample> sampleList= new ArrayList<Sample>();
				//String[] sampleId= sampleIds.split("\\|");
				JSONObject sampleidJO = JSONObject.fromObject(sampleids);
				//JSONObject entrustnumJO = JSONObject.fromObject(entrustnums);
				
		        Iterator iterator = sampleidJO.keys();
		        while(iterator.hasNext()){
		           String key = (String) iterator.next();
		           Sample sample = iSampleService.selectsampleById(key);
				   sampleList.add(sample);
		        }
				 
				//分类
				TreeMap sampleMap=(TreeMap) sort(sampleList);  
				Iterator it = sampleMap.keySet().iterator();  
		        while(it.hasNext()){  
			         String key = (String)it.next();  
			         List<Sample> templist = (List<Sample>) sampleMap.get(key);  
			         System.out.print("\n"+key+":"); 
			         Prod prod= iProdService.selectprodById(key);
			         Pact pact =iPactService.selectpactById(templist.get(0).getPid());
			         
			         
			         Entrust entrust =new Entrust(); 
			         entrust.setBu_id(pact.getBu_id());
			         entrust.setProd_id(key);
			         entrust.setJl_id(pact.getJl_id());
			         //entrust.setSp_id(key)
			         entrust.setSyr_id(prod.getSy_id());
			         entrust.setPid(pact.getId()+"");
			         if(templist.get(0).getSy_dt()!=null){
			        	 entrust.setWt_dt(sdf.parse(templist.get(0).getSy_dt()));
			         }
			         entrust.setC_id(pact.getC_id());
			 		 wt_dt = templist.get(0).getSy_dt();
			 		 bu_id = pact.getBu_id();
			 		 c_id = pact.getC_id();
			 	     pid=	 pact.getId()+"";
			         //编号
			 		 if(templist.get(0).getFlg().equals("Y")){
			 			entrust.setCode(createAppend());
			 		 }
			 		 else{
			 			entrust.setCode(createCode());
			 		 }
			 		 
			 		 //流水号
					 entrust.setLs_n(createLs_t());
					 int result = Integer.parseInt(iEntrustService.addentrust(entrust).toString());
					 if(result>0){
						String qrResult = MatrixToImageWriter.createQrImage("TG_"+entrust.getId());
						if(qrResult.length()>0){
							HttpServletRequest request = ServletActionContext.getRequest();
							String path = request.getScheme() + "://"
									+ request.getServerName() + ":" + request.getServerPort()
									+ request.getContextPath();
							Entrust upentrust = new Entrust();
							upentrust.setId(entrust.getId());
							upentrust.setEwm(path+"/QRImages/"+qrResult);
							iEntrustService.updateentrust(upentrust);
						}
					  }
					
					 
					 
			         for(int i=0; i<templist.size(); i++){  
			        	Sample sample= templist.get(i);
			        	int ennum= Integer.parseInt(sampleidJO.getString(sample.getId()+""));
			            for(int j=0;j<ennum-1;j++){
			            	sample.setId(null);
			            	//编号
							sample.setPart(createSampleCode(sample.getBu_id(),prod.getId()+""));
							//批号
							sample.setLot(createSampleLot(sample.getBu_id(),sample.getSy_dt(),pact.getId()+"",
									prod.getId()+"",sample.getC_id()));
						 
							
							int sresult = Integer.parseInt(iSampleService.addsample(sample).toString());
							if(sresult>0){
								String qrResult = MatrixToImageWriter.createQrImage("PS_"+sample.getId());
								if(qrResult.length()>0){
									HttpServletRequest request = ServletActionContext.getRequest();
									String path = request.getScheme() + "://"
											+ request.getServerName() + ":" + request.getServerPort()
											+ request.getContextPath();
									Sample upsample = new Sample();
									upsample.setId(sample.getId());
									upsample.setEwm(path+"/QRImages/"+qrResult);
									iSampleService.updatesample(upsample);
								}
								logger.info(sample.getId()+"添加成功！");
								Entrust_sample entrust_sample= new Entrust_sample();
								entrust_sample.setC_id(entrust.getC_id());
								entrust_sample.setPid(entrust.getId()+"");
								entrust_sample.setSample_id(sample.getId()+"");
								iEntrust_sampleService.addentrust_sample(entrust_sample);	
							}
						}
			         }  
		           
		        }  
		       
				msg.append("\"success\",\"msg\":\"添加成功。\"");
				//msg.append(StringUtils.join(idList,"|")+"\"");
				/*}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"委托组数不能为空\"");
			}*/
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
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,ArrayList>  sort(List<Sample> sampleList){  
	        TreeMap tm=new TreeMap();  
	        for(int i=0;i<sampleList.size();i++){  
	         Sample s=sampleList.get(i);  
	            if(tm.containsKey(s.getProd_id())){//  
	             ArrayList l11=(ArrayList)tm.get(s.getProd_id());  
	             l11.add(s);  
	            }else{  
	             ArrayList tem=new ArrayList();  
	                tem.add(s);  
	                tm.put(s.getProd_id(), tem);  
	            }  
	              
	        }  
	        return tm;  
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
			paramMap.put("code", code);
			paramMap.put("prod_id", prod_id);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("qy_lv", qy_lv);
			paramMap.put("jl_id", jl_id);
			paramMap.put("sp_id", sp_id);
			paramMap.put("lq_id", lq_id);
			paramMap.put("st_lv", st_lv);
			paramMap.put("pay_lv", pay_lv);
			paramMap.put("price", price);
			paramMap.put("cs_n", cs_n);
			paramMap.put("syr_id", syr_id);
			paramMap.put("jh_dt", jh_dt);
			paramMap.put("cg_f", cg_f);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("flg", flg);
			paramMap.put("pid", pid);
			paramMap.put("ls_n", ls_n);
			paramMap.put("gg_code", gg_code);
			paramMap.put("sy_code", sy_code);
			paramMap.put("ewm", ewm);
			paramMap.put("bu_id", bu_id);
			paramMap.put("wt_dt", wt_dt);
			if(wt_dtFrom!=null&&!wt_dtFrom.equals(""))
			paramMap.put("wt_dtFrom", sdf.parse(wt_dtFrom));
			if(wt_dtTo!=null&&!wt_dtTo.equals(""))
			paramMap.put("wt_dtTo", sdf.parse(wt_dtTo));
			paramMap.put("submit", submit);
			paramMap.put("sjwc_dt", sjwc_dt);
			paramMap.put("ff_dt", ff_dt);
			paramMap.put("fq_flg", fq_flg);
			paramMap.put("spyj_t", spyj_t);
			paramMap.put("copy_id", copy_id);
			paramMap.put("pact_nm_t", pact_nm_t);
			paramMap.put("a_nm_t", a_nm_t);
			paramMap.put("prod_nm_t", prod_nm_t);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iEntrustService.selectentrustByParam(paramMap); 
			totalnumber=iEntrustService.selectCountentrustByParam(paramMap);
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

	private String p_status;
	
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public String updateFlow() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Entrust entrust =new Entrust(); 
		if(id!=null&&!id.equals(""))
		entrust.setId(Long.parseLong(id));
		entrust.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		entrust.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		entrust.setUp_dt(sdf.parse(up_dt));
		entrust.setC_id(c_id);
		entrust.setCode(code);
		entrust.setProd_id(prod_id);
		entrust.setTy_lv(ty_lv);
		entrust.setQy_lv(qy_lv);
		entrust.setJl_id(jl_id);
		entrust.setSp_id(sp_id);
		entrust.setLq_id(lq_id);
		entrust.setSt_lv(st_lv);
		entrust.setPay_lv(pay_lv);
		entrust.setPrice(price);
		if(cs_n!=null&&!cs_n.equals(""))
		entrust.setCs_n(Long.parseLong(cs_n));
		entrust.setSyr_id(syr_id);
		entrust.setJh_dt(jh_dt);
		entrust.setCg_f(cg_f);
		entrust.setCm_tx(cm_tx);
		entrust.setFlg(flg);
		entrust.setPid(pid);
		entrust.setLs_n(ls_n);
		entrust.setGg_code(gg_code);
		entrust.setSy_code(sy_code);
		entrust.setEwm(ewm);
		entrust.setBu_id(bu_id);
		if(wt_dt!=null&&!wt_dt.equals(""))
		entrust.setWt_dt(sdf.parse(wt_dt));
		entrust.setSubmit(submit);
		entrust.setSjwc_dt(sjwc_dt);
		entrust.setFf_dt(ff_dt);
		entrust.setFq_flg(fq_flg);
		entrust.setSpyj_t(spyj_t);
		if(copy_id!=null&&!copy_id.equals(""))
		entrust.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iEntrustService.updateentrustFlow(entrust,p_status,entrust_samples,entrust_pins);
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
			iEntrustService.deleteentrust(id);
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
			entrust=iEntrustService.selectentrustById(id);
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
			
			JSONObject jo=JSONObject.fromObject(entrust, jsonConfig);
			if(entrust!=null){
				Pact tempPact=new Pact();
				 tempPact= iPactService.selectpactById(entrust.getPid());
				Accnt tempAccnt = new Accnt();
				if(tempPact!=null)
				 tempAccnt = iAccntService.selectaccntById(tempPact.getPid());
				Prod tempProd =new Prod();
				 tempProd =iProdService.selectprodById(entrust.getProd_id());
				
			
			  
				if(tempAccnt.getNm_t()!=null){
					jo.accumulate("wt_name", tempAccnt.getNm_t());
				}
				else{
					jo.accumulate("wt_name", "");
				}
				if(tempPact!=null){
					jo.accumulate("pact_name", tempPact.getNm_t());
				}
				else{
					jo.accumulate("pact_name", "");
				}
				if(tempProd!=null){
					jo.accumulate("prod_name", tempProd.getNm_t());
				}
				else{
					jo.accumulate("prod_name", "");
				}
				
				
			} 
			msg.append("\"success\",\"msg\":");
			msg.append(jo);
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
			paramMap.put("code", code);
			paramMap.put("prod_id", prod_id);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("qy_lv", qy_lv);
			paramMap.put("jl_id", jl_id);
			paramMap.put("sp_id", sp_id);
			paramMap.put("lq_id", lq_id);
			paramMap.put("st_lv", st_lv);
			paramMap.put("pay_lv", pay_lv);
			paramMap.put("price", price);
			paramMap.put("cs_n", cs_n);
			paramMap.put("syr_id", syr_id);
			paramMap.put("jh_dt", jh_dt);
			paramMap.put("cg_f", cg_f);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("flg", flg);
			paramMap.put("pid", pid);
			paramMap.put("ls_n", ls_n);
			paramMap.put("gg_code", gg_code);
			paramMap.put("sy_code", sy_code);
			paramMap.put("ewm", ewm);
			paramMap.put("bu_id", bu_id);
			paramMap.put("wt_dt", wt_dt);
			if(wt_dtFrom!=null&&!wt_dtFrom.equals(""))
			paramMap.put("wt_dtFrom", sdf.parse(wt_dtFrom));
			if(wt_dtTo!=null&&!wt_dtTo.equals(""))
			paramMap.put("wt_dtTo", sdf.parse(wt_dtTo));
			paramMap.put("submit", submit);
			paramMap.put("sjwc_dt", sjwc_dt);
			paramMap.put("ff_dt", ff_dt);
			paramMap.put("fq_flg", fq_flg);
			paramMap.put("spyj_t", spyj_t);
			paramMap.put("copy_id", copy_id);
			paramMap.put("pact_nm_t", pact_nm_t);
			paramMap.put("a_nm_t", a_nm_t);
			paramMap.put("prod_nm_t", prod_nm_t);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iEntrustService.selectCountentrustByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iEntrustService.selectentrustByParam(paramMap); 
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
    	
    	Pact pact = new Pact();
    	System.out.println(pact.getPid());
    	//System.out.println(createAppend());
    	//System.out.println(createLs_t());
    	
		return null;
	}
    
    
    /**
     * 
     * 编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createCode(){
    	//http://192.168.1.144/Check/Entrust?pid=2613&wt_dt=2017-01-20&bu_id=1&c_id=10
	 
    	String groupCode = pid+"_"+wt_dt;
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
		sb.append("-");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		sb.append(year+"-");
		paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("bu_id", bu_id);
		paramMap.put("year", year+"");
		paramMap.put("order", "ORDER BY S_NUM DESC");
		//选最大的
		List<Lot> lotList= iLotService.selectlotByParamOrder(paramMap);
		String lotS_num="001";
		if(lotList.size()>0){
			lotS_num=lotList.get(0).getS_num();
		}
		 
		paramMap.put("ht_id",pid); 
		paramMap.put("wt_dt",wt_dt);
		lotList= iLotService.selectlotByParam(paramMap);
		 
		Lot templot = new Lot();
		 
		if(lotList.size()>0){
			
			
			String lotPro_num=lotList.get(0).getPro_num();
			Long lotId=lotList.get(0).getId();
			 
			//同一个合同同一个委托日期
			templot.setPro_num((Integer.parseInt(lotPro_num)+1)+"");
			templot.setId(lotId);
			sb.append(lotS_num+"-");
			sb.append((Integer.parseInt(lotPro_num)+1)+"");
			iLotService.updatelot(templot);
			 
		}
		else{
			 
			//同一个合同下不同委托日期
			int sortNum=Integer.valueOf(lotS_num).intValue(); 
			String nextNum = CodeUtils.autoGenericCode(sortNum+"",3);
			templot.setS_num(nextNum);
			templot.setBu_id(bu_id);
			templot.setC_dt(new Date());
			templot.setC_id(c_id);
			templot.setComments("");
			templot.setGroup_code(groupCode);
			templot.setHt_id(pid);
			//templot.setJc_id("");
			templot.setLotnumber("0");
			templot.setPro_code("");
			templot.setPro_num("1");
			templot.setWt_dt(wt_dt);
			templot.setSy_dt("");
			templot.setYear(year+"");
			sb.append(nextNum+"-");
			sb.append("1");
			iLotService.addlot(templot);
		}
		  
    	return sb.toString();
    }
    /**
     * 
     * 追加编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createAppend(){
    	//http://192.168.1.144/Check/Entrust?pid=2613&wt_dt=2017-01-20&bu_id=1&c_id=10
    	String groupCode = pid+"_"+wt_dt;
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
    	sb.append("-");
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	sb.append(year+"-");
    	paramMap = new HashMap();
    	paramMap.put("fromPage",0);
    	paramMap.put("toPage",1); 
    	paramMap.put("bu_id", bu_id);
    	paramMap.put("year", year+"");
    	paramMap.put("order", "ORDER BY S_NUM DESC");
    	//选最大的
    	List<Lot> lotList= iLotService.selectlotByParamOrder(paramMap);
    	String lotS_num="001";
    	if(lotList.size()>0){
    		lotS_num=lotList.get(0).getS_num();
    	}
    	
    	//paramMap.put("ht_id",pid); 
    	paramMap.put("wt_dt",wt_dt);
    	paramMap.put("order", "pro_num");
    	lotList= iLotService.selectlotByParam(paramMap);
    	
    	Lot templot = new Lot();
    	
    	if(lotList.size()>0){
    		
    		
    		String lotPro_num=lotList.get(0).getPro_num();
    		Long lotId=lotList.get(0).getId();
    		
    		//同一天存在记录
    		templot.setPro_num((Integer.parseInt(lotPro_num)+1)+"");
    		templot.setId(lotId);
    		sb.append(lotS_num+"-");
    		sb.append((Integer.parseInt(lotPro_num)+1)+"");
    		iLotService.updatelot(templot);
    		
    	}
    	else{
    		
    		//同一不存在记录
    		int sortNum=Integer.valueOf(lotS_num).intValue(); 
    		String nextNum = CodeUtils.autoGenericCode(sortNum+"",3);
    		templot.setS_num(nextNum);
    		templot.setBu_id(bu_id);
    		templot.setC_dt(new Date());
    		templot.setC_id(c_id);
    		templot.setComments("");
    		templot.setGroup_code(groupCode);
    		templot.setHt_id(pid);
    		//templot.setJc_id("");
    		templot.setLotnumber("0");
    		templot.setPro_code("");
    		templot.setPro_num("1");
    		templot.setWt_dt(wt_dt);
    		templot.setSy_dt("");
    		templot.setYear(year+"");
    		sb.append(nextNum+"-");
    		sb.append("1");
    		iLotService.addlot(templot);
    	}
    	
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
    	 
    	paramMap.put("bu_id", bu_id);
    	List<Lov> lovList= iLovService.selectlovByParam(paramMap);
    	 
    	 
    	Calendar cal = Calendar.getInstance();
    	int year = cal.get(Calendar.YEAR);
    	sb.append(year);
    	paramMap.put("ty_lv", "委托单流水");
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
    		templov.setTy_lv("委托单流水");
    		iLovService.addlov(templov);
    	}
    	sb.append(newSort);
    	
    	
    	return sb.toString();
    }
    
    
    
    private String accnt;
    private String pact;
    private String sample;
    //private String entrust;
    private String entrust_sample;
    private String test;
    private String results;
    
    public String getAccnt() {
		return accnt;
	}
	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}
	public String getPact() {
		return pact;
	}
	public void setPact(String pact) {
		this.pact = pact;
	}
	public String getSample() {
		return sample;
	}
	public void setSample(String sample) {
		this.sample = sample;
	}
	public String getEntrust_sample() {
		return entrust_sample;
	}
	public void setEntrust_sample(String entrust_sample) {
		this.entrust_sample = entrust_sample;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	 
	
	/**
	 *   委托单流程（全部）
	 * @return
	 * @throws Exception
	 */
	public String flow() throws Exception {
		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		
		try {
			 
	    	
	    	//委托单entrust
			Entrust entrust =new Entrust(); 
			if(id!=null&&!id.equals(""))
			entrust.setId(Long.parseLong(id));
			entrust.setRow_id(row_id);
			if(c_dt!=null&&!c_dt.equals(""))
			entrust.setC_dt(sdf.parse(c_dt));
			if(up_dt!=null&&!up_dt.equals(""))
			entrust.setUp_dt(sdf.parse(up_dt));
			entrust.setC_id(c_id);
			//编号
			if(flg.equals("Y")){
				entrust.setCode(createCode());
			}
			else{
				entrust.setCode(createAppend());
			}
			
			
			entrust.setProd_id(prod_id);
			entrust.setTy_lv(ty_lv);
			entrust.setQy_lv(qy_lv);
			entrust.setJl_id(jl_id);
			entrust.setSp_id(sp_id);
			entrust.setLq_id(lq_id);
			entrust.setSt_lv(st_lv);
			entrust.setPay_lv(pay_lv);
			entrust.setPrice(price);
			if(cs_n!=null&&!cs_n.equals(""))
			entrust.setCs_n(Long.parseLong(cs_n));
			entrust.setSyr_id(syr_id);
			entrust.setJh_dt(jh_dt);
			entrust.setCg_f(cg_f);
			entrust.setCm_tx(cm_tx);
			entrust.setFlg(flg);
			entrust.setPid(pid);
			
			//流水号
			entrust.setLs_n(createLs_t());
			entrust.setGg_code(gg_code);
			entrust.setSy_code(sy_code);
			entrust.setEwm(ewm);
			entrust.setBu_id(bu_id);
			if(wt_dt!=null&&!wt_dt.equals(""))
			entrust.setWt_dt(sdf.parse(wt_dt));
			entrust.setSubmit(submit);
			entrust.setSjwc_dt(sjwc_dt);
			entrust.setFf_dt(ff_dt);
			entrust.setFq_flg(fq_flg);
			entrust.setSpyj_t(spyj_t);
			if(copy_id!=null&&!copy_id.equals(""))
			entrust.setCopy_id(Long.parseLong(copy_id));
			
			 
			int result = iEntrustService.entrustFlow(entrust,accnt,pact,sample,entrust_sample,
					test,results);
			if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("TG_"+entrust.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Entrust upentrust = new Entrust();
					upentrust.setId(entrust.getId());
					upentrust.setEwm(path+"/QRImages/"+qrResult);
					iEntrustService.updateentrust(upentrust);
				}
			 
				
			}
			msg.append("\"success\",\"msg\":\"");
			msg.append(entrust.getId()+"\"");
			logger.info(result+"添加成功！");
			//缓存
			List<String> tableList = new ArrayList<String>(); 
	        tableList.add("accnt");
	        tableList.add("lov");
	        tableList.add("lot");
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
	
	
	/**
	 * 委托单流程
	 * @return
	 */
	public String seFlow() throws Exception {

		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		
		try {
			 
	    	
	    	//委托单entrust
			Entrust entrust =new Entrust(); 
			if(id!=null&&!id.equals(""))
			entrust.setId(Long.parseLong(id));
			entrust.setRow_id(row_id);
			if(c_dt!=null&&!c_dt.equals(""))
			entrust.setC_dt(sdf.parse(c_dt));
			if(up_dt!=null&&!up_dt.equals(""))
			entrust.setUp_dt(sdf.parse(up_dt));
			entrust.setC_id(c_id);
			//编号
			/*if(flg.equals("Y")){
				entrust.setCode(createCode());
			}
			else{
				entrust.setCode(createAppend());
			}*/
			
			
			entrust.setProd_id(prod_id);
			entrust.setTy_lv(ty_lv);
			entrust.setQy_lv(qy_lv);
			entrust.setJl_id(jl_id);
			entrust.setSp_id(sp_id);
			entrust.setLq_id(lq_id);
			entrust.setSt_lv(st_lv);
			entrust.setPay_lv(pay_lv);
			entrust.setPrice(price);
			if(cs_n!=null&&!cs_n.equals(""))
			entrust.setCs_n(Long.parseLong(cs_n));
			entrust.setSyr_id(syr_id);
			entrust.setJh_dt(jh_dt);
			entrust.setCg_f(cg_f);
			entrust.setCm_tx(cm_tx);
			entrust.setFlg(flg);
			entrust.setPid(pid);
			
			//流水号
			//entrust.setLs_n(createLs_t());
			
			entrust.setGg_code(gg_code);
			entrust.setSy_code(sy_code);
			entrust.setEwm(ewm);
			entrust.setBu_id(bu_id);
			if(wt_dt!=null&&!wt_dt.equals(""))
			entrust.setWt_dt(sdf.parse(wt_dt));
			entrust.setSubmit(submit);
			entrust.setSjwc_dt(sjwc_dt);
			entrust.setFf_dt(ff_dt);
			entrust.setFq_flg(fq_flg);
			entrust.setSpyj_t(spyj_t);
			if(copy_id!=null&&!copy_id.equals(""))
			entrust.setCopy_id(Long.parseLong(copy_id));
			
			 
			/*int result = iEntrustService.entrustFlow(entrust,accnt,pact,sample,entrust_sample,
					test,results);
			if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("TG_"+entrust.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Entrust upentrust = new Entrust();
					upentrust.setId(entrust.getId());
					upentrust.setEwm(path+"/QRImages/"+qrResult);
					iEntrustService.updateentrust(upentrust);
				}
			 
				
			}*/
			 
			if(sample!=null&&!sample.equals("")){
				JSONObject sampleTree =JSONObject.fromObject(sample);
				List<String> resultList= iEntrustService.seFlow(entrust, sampleTree);
				if(resultList.size()>0){
					String resultStr=JSONArray.fromObject(resultList).toString();
					msg.append("\"success\",\"msg\":");
					msg.append(resultStr);
					logger.info(resultStr+"添加成功！");
				}
				else{
					msg.append("\"failure\",\"msg\":");
					msg.append("\"添加失败！\"");
					logger.info("添加失败！。");
				}
				
				
			}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"添加失败！\"");
				logger.info("添加失败！。");
			}
				 
			 
			
			
			//缓存
			/*List<String> tableList = new ArrayList<String>(); 
	        tableList.add("accnt");
	        tableList.add("lov");
	        tableList.add("lot");
	        tableList.add("pact");
	        CacheToRedis.cache(tableList);*/
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

	
	/**
     * 
     * 样品编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createSampleCode(String bu_id,String prod_id){
    	//http://192.168.1.144/Check/Sample?prod_id=14&bu_id=1
		StringBuffer sb = new StringBuffer();
		Prod prod = iProdService.selectprodById(prod_id);
		sb.append("YP"+prod.getDh_lv());
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("bu_id", bu_id);
		paramMap.put("nm_t", prod.getDh_lv());
		paramMap.put("ty_lv", "代号");
		paramMap.put("order", "ORDER BY SORT DESC");
		Lov templov = new Lov();
		String newSort=""; 
		List<Lov> lovList= iLovService.selectlovByParamOrder(paramMap);
		if(lovList.size()>0){
			String lovSort=lovList.get(0).getSort();
			Long lovId=lovList.get(0).getId();
			String cm_tx= lovList.get(0).getCm_tx();
			if(lovSort.equals("")||lovSort.equals("null")||!cm_tx.equals(year+"")){
				newSort = "001";
				templov.setSort(newSort);
				templov.setCm_tx(year+"");
				templov.setId(lovId);
			}
			else{
				//更新
				int sortNum=Integer.valueOf(lovSort).intValue();
				newSort = CodeUtils.autoGenericCode(sortNum+"",3);
				 
				templov.setCm_tx(year+"");
				templov.setSort(newSort);
				templov.setId(lovId);
			}
			iLovService.updatelov(templov);
		}
		else{
			//新建 
			newSort = "001";
			templov.setBu_id(bu_id);
			templov.setC_dt(new Date());
			templov.setCm_tx(year+"");
			templov.setNm_t(prod.getDh_lv());
			templov.setSort(newSort);
			templov.setTy_lv("代号");
			iLovService.addlov(templov);
		}
		sb.append(newSort);
    	 
    	return sb.toString();
    }
    
    
    
    private String mulupdate;
	 
   	public String getMulupdate() {
   		return mulupdate;
   	}
   	public void setMulupdate(String mulupdate) {
   		this.mulupdate = mulupdate;
   	}
   	public String mulUpdate() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		StringBuffer msg = new StringBuffer("{\"state\":");
		
		JSONArray updateJA=JSONArray.fromObject(mulupdate);
		if(updateJA!=null){
			List<Entrust> tempList= new ArrayList<Entrust>();
			for(int i=0;i<updateJA.size();i++){
				JSONObject  updateJO = (JSONObject) updateJA.get(i);
				Entrust entrust =new Entrust(); 
				if(updateJO.containsKey("id"))
					entrust.setId(Long.parseLong(updateJO.getString("id")));
				if(updateJO.containsKey("fq_flg"))
					entrust.setFq_flg(updateJO.getString("fq_flg"));
				tempList.add(entrust);
			}
			
			try {
				iEntrustService.mulupdateEntrust(tempList);
				msg.append("\"success\",\"msg\":");
				msg.append("\"更新成功！\"");
				logger.info("批量成功！");
			} catch (Exception e) {
				logger.info("更新失败！"+e);
				msg.append("\"failure\",\"msg\":");
				msg.append("\"批量失败！\"");
				e.printStackTrace();
			}
		}
		else{
			msg.append("\"failure\",\"msg\":");
			msg.append("\"参数不能为空！\"");
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
   	
    /**
     * 
     * 样品批号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createSampleLot(String bu_id,String sy_dt,String pact_id,String prod_id,String c_id){
    	//http://192.168.1.144/Check/Sample?prod_id=14&bu_id=1&pid=194&sy_dt=2014-04-30 00:00:00&c_id=11
    	String groupcode= "";
    	//sdf = new SimpleDateFormat("yy-MM-dd");
    	Pact pact = iPactService.selectpactById(pact_id);
		StringBuffer sb = new StringBuffer();
		String sy_dtStr="";
		 
		sy_dtStr = sy_dt.substring(0,10);
	 
		//送样日期_委托单位ID_检测项目ID
		groupcode=sy_dtStr+"_"+pact.getPid()+"_"+prod_id;
		sb.append(sy_dtStr.substring(2,10).replaceAll("-", ""));
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Map paramMap = new HashMap();
	  	paramMap = new HashMap();
    	paramMap.put("fromPage",0);
    	paramMap.put("toPage",1); 
    	paramMap.put("group_code", groupcode);
    	 
    	paramMap.put("order", "ORDER BY LOTNUMBER DESC");
    	//选最大的
    	List<Lot> lotList= iLotService.selectlotByParamOrder(paramMap);
    	//String lotS_num="001";
    	Lot templot = new Lot();
    	if(lotList.size()>0){
    		String lotnumberStr=lotList.get(0).getLotnumber();
    		if(lotnumberStr.equals("")||lotnumberStr.equals("null")){
    			
    			//String nextNum = CodeUtils.autoGenericCode(lotnumberStr+"",3);
    			
    			templot.setLotnumber("001");
        		templot.setId(lotList.get(0).getId());
        		sb.append("001");
        		iLotService.updatelot(templot);
    		}
    		else{
    			int sortNum=Integer.valueOf(lotnumberStr).intValue(); 
    			String nextNum = CodeUtils.autoGenericCode(sortNum+"",3);
    			templot.setLotnumber(nextNum);
        		templot.setId(lotList.get(0).getId());
        		sb.append(nextNum);
        		iLotService.updatelot(templot);
    		}
    		
    	}
    	else{
    		
    		paramMap = new HashMap();
    	  	paramMap = new HashMap();
        	paramMap.put("fromPage",0);
        	paramMap.put("toPage",1); 
        	paramMap.put("sy_dt", sy_dtStr);
        	paramMap.put("bu_id", bu_id);
        	paramMap.put("order", "ORDER BY LOTNUMBER DESC");
        	lotList= iLotService.selectlotByParamOrder(paramMap);
        	if(lotList.size()>0){
        		String lotnumberStr=lotList.get(0).getLotnumber();
        		if(lotnumberStr.equals("")||lotnumberStr.equals("null")){
        			templot.setLotnumber("001");
            		//templot.setId(lotList.get(0).getId());
            		sb.append("001");
            		//iLotService.updatelot(templot);
        		}
        		else{
        			int sortNum=Integer.valueOf(lotnumberStr).intValue(); 
        			String nextNum = CodeUtils.autoGenericCode(sortNum+"",3);
        			templot.setLotnumber(nextNum);
            		//templot.setId(lotList.get(0).getId());
            		sb.append(nextNum);
            		//iLotService.updatelot(templot);
        		}
        	 
        	}
        	else{
        		templot.setLotnumber("001"); 
        		sb.append("001"); 
        	}
        	//templot.setS_num();
    		templot.setBu_id(bu_id);
    		templot.setC_dt(new Date());
    		templot.setC_id(c_id);
    		templot.setComments("");
    		templot.setGroup_code(groupcode);
    		templot.setHt_id(pact_id);
    		templot.setJc_id(prod_id);
    		templot.setPro_code("");
    		//templot.setPro_num("1");
    		//templot.setWt_dt(wt_dt);
    		templot.setSy_dt(sy_dtStr);
    		templot.setYear(year+"");
    		templot.setWt_id(pact.getPid());
    		  
    		iLotService.addlot(templot);
    		
    	}
    	 
    	return sb.toString();
    }
	
	
}
