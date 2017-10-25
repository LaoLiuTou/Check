package com.check.action.sample;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.check.model.sample_templet.Sample_templet;
import com.check.service.accnt.IAccntService;
import com.check.service.entrust.IEntrustService;
import com.check.service.entrust_sample.IEntrust_sampleService;
import com.check.service.lot.ILotService;
import com.check.service.lov.ILovService;
import com.check.service.pact.IPactService;
import com.check.service.prod.IProdService;
import com.check.service.sample.ISampleService;
import com.check.utils.CodeUtils;
import com.check.utils.MatrixToImageWriter;
import com.opensymphony.xwork2.Action;
public class SampleAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private ISampleService iSampleService;
	@Autowired
	private ILotService iLotService;
	@Autowired
	private ILovService iLovService; 
	@Autowired
	private IProdService iProdService; 
	@Autowired
	private IPactService iPactService; 
	@Autowired
	private IAccntService iAccntService; 
	@Autowired
	private IEntrustService iEntrustService; 
	@Autowired
	private IEntrust_sampleService iEntrust_sampleService; 
	
	private List<Sample> list;
	private Sample sample;
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
	public Sample getSample() {
		return sample;
	}
	public void setSample(Sample sample) {
		this.sample = sample;
	}
	public List<Sample> getList() {
		return list;
	}
	public void setList(List<Sample> list) {
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
	private String nm_t;
	public String getNm_t() {
		return nm_t;
	}
	public void setNm_t(String nm_t) {
		this.nm_t = nm_t;
	}
	private String part;
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	private String ty_lv;
	public String getTy_lv() {
		return ty_lv;
	}
	public void setTy_lv(String ty_lv) {
		this.ty_lv = ty_lv;
	}
	private String lot;
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	private String st_lv;
	public String getSt_lv() {
		return st_lv;
	}
	public void setSt_lv(String st_lv) {
		this.st_lv = st_lv;
	}
	private String jd_lv;
	public String getJd_lv() {
		return jd_lv;
	}
	public void setJd_lv(String jd_lv) {
		this.jd_lv = jd_lv;
	}
	private String prod_id;
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	private String cd_t;
	public String getCd_t() {
		return cd_t;
	}
	public void setCd_t(String cd_t) {
		this.cd_t = cd_t;
	}
	private String sy_dt;
	public String getSy_dt() {
		return sy_dt;
	}
	public void setSy_dt(String sy_dt) {
		this.sy_dt = sy_dt;
	}
	private String gg_t;
	public String getGg_t() {
		return gg_t;
	}
	public void setGg_t(String gg_t) {
		this.gg_t = gg_t;
	}
	private String fz_id;
	public String getFz_id() {
		return fz_id;
	}
	public void setFz_id(String fz_id) {
		this.fz_id = fz_id;
	}
	private String bw_t;
	public String getBw_t() {
		return bw_t;
	}
	public void setBw_t(String bw_t) {
		this.bw_t = bw_t;
	}
	private String ly_lv;
	public String getLy_lv() {
		return ly_lv;
	}
	public void setLy_lv(String ly_lv) {
		this.ly_lv = ly_lv;
	}
	private String db_n;
	public String getDb_n() {
		return db_n;
	}
	public void setDb_n(String db_n) {
		this.db_n = db_n;
	}
	private String dw_lv;
	public String getDw_lv() {
		return dw_lv;
	}
	public void setDw_lv(String dw_lv) {
		this.dw_lv = dw_lv;
	}
	private String sc_id;
	public String getSc_id() {
		return sc_id;
	}
	public void setSc_id(String sc_id) {
		this.sc_id = sc_id;
	}
	private String qd_t;
	public String getQd_t() {
		return qd_t;
	}
	public void setQd_t(String qd_t) {
		this.qd_t = qd_t;
	}
	private String gc_t;
	public String getGc_t() {
		return gc_t;
	}
	public void setGc_t(String gc_t) {
		this.gc_t = gc_t;
	}
	private String jl_t;
	public String getJl_t() {
		return jl_t;
	}
	public void setJl_t(String jl_t) {
		this.jl_t = jl_t;
	}
	private String zz_dt;
	public String getZz_dt() {
		return zz_dt;
	}
	public void setZz_dt(String zz_dt) {
		this.zz_dt = zz_dt;
	}
	private String test_dt;
	public String getTest_dt() {
		return test_dt;
	}
	public void setTest_dt(String test_dt) {
		this.test_dt = test_dt;
	}
	private String kd_t;
	public String getKd_t() {
		return kd_t;
	}
	public void setKd_t(String kd_t) {
		this.kd_t = kd_t;
	}
	private String lo_t;
	public String getLo_t() {
		return lo_t;
	}
	public void setLo_t(String lo_t) {
		this.lo_t = lo_t;
	}
	private String la_t;
	public String getLa_t() {
		return la_t;
	}
	public void setLa_t(String la_t) {
		this.la_t = la_t;
	}
	private String hi_t;
	public String getHi_t() {
		return hi_t;
	}
	public void setHi_t(String hi_t) {
		this.hi_t = hi_t;
	}
	private String jc_f;
	public String getJc_f() {
		return jc_f;
	}
	public void setJc_f(String jc_f) {
		this.jc_f = jc_f;
	}
	private String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	private String bu_id;
	public String getBu_id() {
		return bu_id;
	}
	public void setBu_id(String bu_id) {
		this.bu_id = bu_id;
	}
	private String cj_lv;
	public String getCj_lv() {
		return cj_lv;
	}
	public void setCj_lv(String cj_lv) {
		this.cj_lv = cj_lv;
	}
	private String sn_t;
	public String getSn_t() {
		return sn_t;
	}
	public void setSn_t(String sn_t) {
		this.sn_t = sn_t;
	}
	private String sz_t;
	public String getSz_t() {
		return sz_t;
	}
	public void setSz_t(String sz_t) {
		this.sz_t = sz_t;
	}
	private String st_t;
	public String getSt_t() {
		return st_t;
	}
	public void setSt_t(String st_t) {
		this.st_t = st_t;
	}
	private String pp_t;
	public String getPp_t() {
		return pp_t;
	}
	public void setPp_t(String pp_t) {
		this.pp_t = pp_t;
	}
	private String wj_t;
	public String getWj_t() {
		return wj_t;
	}
	public void setWj_t(String wj_t) {
		this.wj_t = wj_t;
	}
	private String ch_t;
	public String getCh_t() {
		return ch_t;
	}
	public void setCh_t(String ch_t) {
		this.ch_t = ch_t;
	}
	private String sh_t;
	public String getSh_t() {
		return sh_t;
	}
	public void setSh_t(String sh_t) {
		this.sh_t = sh_t;
	}
	private String snl_t;
	public String getSnl_t() {
		return snl_t;
	}
	public void setSnl_t(String snl_t) {
		this.snl_t = snl_t;
	}
	private String wa_t;
	public String getWa_t() {
		return wa_t;
	}
	public void setWa_t(String wa_t) {
		this.wa_t = wa_t;
	}
	private String ewm;
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
	private String flg;
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	private String txm;
	public String getTxm() {
		return txm;
	}
	public void setTxm(String txm) {
		this.txm = txm;
	}
	private String fq_flg;
	public String getFq_flg() {
		return fq_flg;
	}
	public void setFq_flg(String fq_flg) {
		this.fq_flg = fq_flg;
	}
	private String copy_id;
	public String getCopy_id() {
		return copy_id;
	}
	public void setCopy_id(String copy_id) {
		this.copy_id = copy_id;
	}
	private String pid_no;
	
	public String getPid_no() {
		return pid_no;
	}
	public void setPid_no(String pid_no) {
		this.pid_no = pid_no;
	}
	private String jd_lv_state;
	public String getJd_lv_state() {
		return jd_lv_state;
	}
	public void setJd_lv_state(String jd_lv_state) {
		this.jd_lv_state = jd_lv_state;
	}

	
	
	//委托组数
	private String entrustnum;
	 
	public String getEntrustnum() {
		return entrustnum;
	}
	public void setEntrustnum(String entrustnum) {
		this.entrustnum = entrustnum;
	}
	
	//
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
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Sample sample =new Sample(); 
		if(id!=null&&!id.equals(""))
		sample.setId(Long.parseLong(id));
		sample.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		sample.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		sample.setUp_dt(sdf.parse(up_dt));
		sample.setC_id(c_id);
		sample.setPid(pid);
		sample.setNm_t(nm_t);
		//编号
		//sample.setPart(createCode());
		sample.setTy_lv(ty_lv);
		
		//批号
		//sample.setLot(createLot());
		sample.setSt_lv(st_lv);
		sample.setJd_lv(jd_lv);
		sample.setProd_id(prod_id);
		sample.setCd_t(cd_t);
		sample.setSy_dt(sy_dt);
		sample.setGg_t(gg_t);
		sample.setFz_id(fz_id);
		sample.setBw_t(bw_t);
		sample.setLy_lv(ly_lv);
		sample.setDb_n(db_n);
		sample.setDw_lv(dw_lv);
		sample.setSc_id(sc_id);
		sample.setQd_t(qd_t);
		sample.setGc_t(gc_t);
		sample.setJl_t(jl_t);
		sample.setZz_dt(zz_dt);
		sample.setTest_dt(test_dt);
		sample.setKd_t(kd_t);
		sample.setLo_t(lo_t);
		sample.setLa_t(la_t);
		sample.setHi_t(hi_t);
		sample.setJc_f(jc_f);
		sample.setCm_tx(cm_tx);
		sample.setBu_id(bu_id);
		sample.setCj_lv(cj_lv);
		sample.setSn_t(sn_t);
		sample.setSz_t(sz_t);
		sample.setSt_t(st_t);
		sample.setPp_t(pp_t);
		sample.setWj_t(wj_t);
		sample.setCh_t(ch_t);
		sample.setSh_t(sh_t);
		sample.setSnl_t(snl_t);
		sample.setWa_t(wa_t);
		//sample.setEwm(ewm);
		sample.setFlg(flg);
		sample.setTxm(txm);
		sample.setFq_flg(fq_flg);
		if(copy_id!=null&&!copy_id.equals(""))
		sample.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			
			
			//委托组数
			if(entrustnum!=null&&!entrustnum.equals("")&& StringUtils.isNumeric(entrustnum)){
				int ennum= Integer.parseInt(entrustnum);
				List<String> idList= new ArrayList<String>();
				for(int i=0;i<ennum;i++){
					 
					sample.setId(null);
					sample.setPart(createCode());
					sample.setLot(createLot());
					
					int result = Integer.parseInt(iSampleService.addsample(sample).toString());
					if(result>0){
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
						idList.add(sample.getId()+"");
						logger.info(sample.getId()+"添加成功！");
					}
					
					
				}
				msg.append("\"success\",\"msg\":\"");
				msg.append(StringUtils.join(idList,"|")+"\"");
			}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"委托组数不能为空且必需是数字\"");
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
		Sample sample =new Sample(); 
		if(id!=null&&!id.equals(""))
			sample.setId(Long.parseLong(id));
		sample.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
			sample.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
			sample.setUp_dt(sdf.parse(up_dt));
		sample.setC_id(c_id);
		sample.setPid(pid);
		sample.setNm_t(nm_t);
		//编号
		//sample.setPart(createCode());
		sample.setTy_lv(ty_lv);
		//192.168.1.144/Check/appAddSample?entrustnum=1&prod_id=14&bu_id=1&pid=194&sy_dt=2014-04-30 00:00:00&c_id=11
		//批号
		//sample.setLot(createLot());
		sample.setSt_lv(st_lv);
		sample.setJd_lv(jd_lv);
		sample.setProd_id(prod_id);
		sample.setCd_t(cd_t);
		sample.setSy_dt(sy_dt);
		sample.setGg_t(gg_t);
		sample.setFz_id(fz_id);
		sample.setBw_t(bw_t);
		sample.setLy_lv(ly_lv);
		sample.setDb_n(db_n);
		sample.setDw_lv(dw_lv);
		sample.setSc_id(sc_id);
		sample.setQd_t(qd_t);
		sample.setGc_t(gc_t);
		sample.setJl_t(jl_t);
		sample.setZz_dt(zz_dt);
		sample.setTest_dt(test_dt);
		sample.setKd_t(kd_t);
		sample.setLo_t(lo_t);
		sample.setLa_t(la_t);
		sample.setHi_t(hi_t);
		sample.setJc_f(jc_f);
		sample.setCm_tx(cm_tx);
		sample.setBu_id(bu_id);
		sample.setCj_lv(cj_lv);
		sample.setSn_t(sn_t);
		sample.setSz_t(sz_t);
		sample.setSt_t(st_t);
		sample.setPp_t(pp_t);
		sample.setWj_t(wj_t);
		sample.setCh_t(ch_t);
		sample.setSh_t(sh_t);
		sample.setSnl_t(snl_t);
		sample.setWa_t(wa_t);
		//sample.setEwm(ewm);
		sample.setFlg(flg);
		sample.setTxm(txm);
		sample.setFq_flg(fq_flg);
		if(copy_id!=null&&!copy_id.equals(""))
			sample.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			
			
			
			//委托组数
			if(entrustnum!=null&&!entrustnum.equals("")){
				int ennum= Integer.parseInt(entrustnum);
				List<String> idList= new ArrayList<String>();
				for(int i=0;i<ennum;i++){
					
					sample.setId(null);
					sample.setPart(createCode());
					sample.setLot(createLot());
					
					int result = Integer.parseInt(iSampleService.addsample(sample).toString());
					if(result>0){
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
						idList.add(sample.getId()+"");
						logger.info(sample.getId()+"添加成功！");
					}
					
					
				}
				msg.append("\"success\",\"msg\":\"");
				msg.append(StringUtils.join(idList,"|")+"\"");
			}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"委托组数不能为空\"");
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
			paramMap.put("pid", pid);
			paramMap.put("nm_t", nm_t);
			paramMap.put("part", part);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("lot", lot);
			paramMap.put("st_lv", st_lv);
			paramMap.put("jd_lv", jd_lv);
			paramMap.put("prod_id", prod_id);
			paramMap.put("cd_t", cd_t);
			paramMap.put("sy_dt", sy_dt);
			paramMap.put("gg_t", gg_t);
			paramMap.put("fz_id", fz_id);
			paramMap.put("bw_t", bw_t);
			paramMap.put("ly_lv", ly_lv);
			paramMap.put("db_n", db_n);
			paramMap.put("dw_lv", dw_lv);
			paramMap.put("sc_id", sc_id);
			paramMap.put("qd_t", qd_t);
			paramMap.put("gc_t", gc_t);
			paramMap.put("jl_t", jl_t);
			paramMap.put("zz_dt", zz_dt);
			paramMap.put("test_dt", test_dt);
			paramMap.put("kd_t", kd_t);
			paramMap.put("lo_t", lo_t);
			paramMap.put("la_t", la_t);
			paramMap.put("hi_t", hi_t);
			paramMap.put("jc_f", jc_f);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("bu_id", bu_id);
			paramMap.put("cj_lv", cj_lv);
			paramMap.put("sn_t", sn_t);
			paramMap.put("sz_t", sz_t);
			paramMap.put("st_t", st_t);
			paramMap.put("pp_t", pp_t);
			paramMap.put("wj_t", wj_t);
			paramMap.put("ch_t", ch_t);
			paramMap.put("sh_t", sh_t);
			paramMap.put("snl_t", snl_t);
			paramMap.put("wa_t", wa_t);
			paramMap.put("ewm", ewm);
			paramMap.put("flg", flg);
			paramMap.put("txm", txm);
			paramMap.put("fq_flg", fq_flg);
			paramMap.put("copy_id", copy_id);
			
			paramMap.put("pact_nm_t", pact_nm_t);
			paramMap.put("a_nm_t", a_nm_t);
			paramMap.put("prod_nm_t", prod_nm_t);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iSampleService.selectsampleByParam(paramMap); 
			totalnumber=iSampleService.selectCountsampleByParam(paramMap);
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
		Sample sample =new Sample(); 
		if(id!=null&&!id.equals(""))
		sample.setId(Long.parseLong(id));
		sample.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		sample.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		sample.setUp_dt(sdf.parse(up_dt));
		sample.setC_id(c_id);
		sample.setPid(pid);
		sample.setNm_t(nm_t);
		sample.setPart(part);
		sample.setTy_lv(ty_lv);
		sample.setLot(lot);
		sample.setSt_lv(st_lv);
		sample.setJd_lv(jd_lv);
		sample.setProd_id(prod_id);
		sample.setCd_t(cd_t);
		sample.setSy_dt(sy_dt);
		sample.setGg_t(gg_t);
		sample.setFz_id(fz_id);
		sample.setBw_t(bw_t);
		sample.setLy_lv(ly_lv);
		sample.setDb_n(db_n);
		sample.setDw_lv(dw_lv);
		sample.setSc_id(sc_id);
		sample.setQd_t(qd_t);
		sample.setGc_t(gc_t);
		sample.setJl_t(jl_t);
		sample.setZz_dt(zz_dt);
		sample.setTest_dt(test_dt);
		sample.setKd_t(kd_t);
		sample.setLo_t(lo_t);
		sample.setLa_t(la_t);
		sample.setHi_t(hi_t);
		sample.setJc_f(jc_f);
		sample.setCm_tx(cm_tx);
		sample.setBu_id(bu_id);
		sample.setCj_lv(cj_lv);
		sample.setSn_t(sn_t);
		sample.setSz_t(sz_t);
		sample.setSt_t(st_t);
		sample.setPp_t(pp_t);
		sample.setWj_t(wj_t);
		sample.setCh_t(ch_t);
		sample.setSh_t(sh_t);
		sample.setSnl_t(snl_t);
		sample.setWa_t(wa_t);
		sample.setEwm(ewm);
		sample.setFlg(flg);
		sample.setTxm(txm);
		sample.setFq_flg(fq_flg);
		if(copy_id!=null&&!copy_id.equals(""))
		sample.setCopy_id(Long.parseLong(copy_id));
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iSampleService.updatesample(sample);
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
			iSampleService.deletesample(id);
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
			sample=iSampleService.selectsampleById(id);
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
			Accnt accnt= iAccntService.selectaccntById(sample.getFz_id());
			JSONObject jo=JSONObject.fromObject(sample, jsonConfig);
			if(accnt==null){
				jo.accumulate("fz_nm_t", "");
			}
			else{
				jo.accumulate("fz_nm_t", accnt.getNm_t());
			}
			
			accnt= iAccntService.selectaccntById(sample.getSc_id());
			if(accnt==null){
				jo.accumulate("sc_nm_t", "");
			}
			else{
				jo.accumulate("sc_nm_t", accnt.getNm_t());
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
			paramMap.put("pid", pid);
			paramMap.put("pid_no", pid_no);
			paramMap.put("nm_t", nm_t);
			paramMap.put("part", part);
			paramMap.put("ty_lv", ty_lv);
			paramMap.put("lot", lot);
			paramMap.put("st_lv", st_lv);
			paramMap.put("jd_lv", jd_lv);
			paramMap.put("prod_id", prod_id);
			paramMap.put("cd_t", cd_t);
			paramMap.put("sy_dt", sy_dt);
			paramMap.put("gg_t", gg_t);
			paramMap.put("fz_id", fz_id);
			paramMap.put("bw_t", bw_t);
			paramMap.put("ly_lv", ly_lv);
			paramMap.put("db_n", db_n);
			paramMap.put("dw_lv", dw_lv);
			paramMap.put("sc_id", sc_id);
			paramMap.put("qd_t", qd_t);
			paramMap.put("gc_t", gc_t);
			paramMap.put("jl_t", jl_t);
			paramMap.put("zz_dt", zz_dt);
			paramMap.put("test_dt", test_dt);
			paramMap.put("kd_t", kd_t);
			paramMap.put("lo_t", lo_t);
			paramMap.put("la_t", la_t);
			paramMap.put("hi_t", hi_t);
			paramMap.put("jc_f", jc_f);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("bu_id", bu_id);
			paramMap.put("cj_lv", cj_lv);
			paramMap.put("sn_t", sn_t);
			paramMap.put("sz_t", sz_t);
			paramMap.put("st_t", st_t);
			paramMap.put("pp_t", pp_t);
			paramMap.put("wj_t", wj_t);
			paramMap.put("ch_t", ch_t);
			paramMap.put("sh_t", sh_t);
			paramMap.put("snl_t", snl_t);
			paramMap.put("wa_t", wa_t);
			paramMap.put("ewm", ewm);
			paramMap.put("flg", flg);
			paramMap.put("txm", txm);
			paramMap.put("fq_flg", fq_flg);
			paramMap.put("copy_id", copy_id);
			paramMap.put("jd_lv_state", jd_lv_state);
			
			paramMap.put("pact_nm_t", pact_nm_t);
			paramMap.put("a_nm_t", a_nm_t);
			paramMap.put("prod_nm_t", prod_nm_t);
			
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iSampleService.selectCountsampleByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iSampleService.selectsampleByParam(paramMap);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String exist() throws Exception {
		
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
		paramMap.put("pid_no", pid_no);
		paramMap.put("nm_t", nm_t);
		paramMap.put("part", part);
		paramMap.put("ty_lv", ty_lv);
		paramMap.put("lot", lot);
		paramMap.put("st_lv", st_lv);
		paramMap.put("jd_lv", jd_lv);
		paramMap.put("prod_id", prod_id);
		paramMap.put("cd_t", cd_t);
		paramMap.put("sy_dt", sy_dt);
		paramMap.put("gg_t", gg_t);
		paramMap.put("fz_id", fz_id);
		paramMap.put("bw_t", bw_t);
		paramMap.put("ly_lv", ly_lv);
		paramMap.put("db_n", db_n);
		paramMap.put("dw_lv", dw_lv);
		paramMap.put("sc_id", sc_id);
		paramMap.put("qd_t", qd_t);
		paramMap.put("gc_t", gc_t);
		paramMap.put("jl_t", jl_t);
		paramMap.put("zz_dt", zz_dt);
		paramMap.put("test_dt", test_dt);
		paramMap.put("kd_t", kd_t);
		paramMap.put("lo_t", lo_t);
		paramMap.put("la_t", la_t);
		paramMap.put("hi_t", hi_t);
		paramMap.put("jc_f", jc_f);
		paramMap.put("cm_tx", cm_tx);
		paramMap.put("bu_id", bu_id);
		paramMap.put("cj_lv", cj_lv);
		paramMap.put("sn_t", sn_t);
		paramMap.put("sz_t", sz_t);
		paramMap.put("st_t", st_t);
		paramMap.put("pp_t", pp_t);
		paramMap.put("wj_t", wj_t);
		paramMap.put("ch_t", ch_t);
		paramMap.put("sh_t", sh_t);
		paramMap.put("snl_t", snl_t);
		paramMap.put("wa_t", wa_t);
		paramMap.put("ewm", ewm);
		paramMap.put("flg", flg);
		paramMap.put("txm", txm);
		paramMap.put("fq_flg", fq_flg);
		paramMap.put("copy_id", copy_id);
		paramMap.put("pact_nm_t", pact_nm_t);
		paramMap.put("a_nm_t", a_nm_t);
		paramMap.put("prod_nm_t", prod_nm_t);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iSampleService.selectCountsampleByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iSampleService.selectsampleByParam(paramMap);
			
			
			
			paramMap = new HashMap();
			paramMap.put("pid", pid);
			paramMap.put("prod_id", prod_id);
			totalnumber=iEntrustService.selectCountentrustByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			//根据条件查询entrust多有记录
			List<Sample> listExist = new ArrayList<Sample>();
			List<Sample> listExist_no = new ArrayList<Sample>();
			//List<String> sampleIdList = new ArrayList<String>();
			Set<String> sampleIdSet = new HashSet<String>();
			
			List<Entrust> entrustList= iEntrustService.selectentrustByParam(paramMap);
			
			for(Entrust entrust:entrustList){
				
				paramMap = new HashMap();
				paramMap.put("pid", entrust.getId());
				totalnumber=iEntrust_sampleService.selectCountentrust_sampleByParam(paramMap);
				
				paramMap.put("fromPage",0);
				paramMap.put("toPage",totalnumber);
				List<Entrust_sample> esList= iEntrust_sampleService.selectentrust_sampleByParam(paramMap);
				for(Entrust_sample  es:esList){
					sampleIdSet.add(es.getSample_id());
				}
				
			}
			
			//循环结果判断是否存在
			for(Sample sample:list){
				if(sampleIdSet.contains(sample.getId().toString())){
					listExist.add(sample);
				}
				else{
					listExist_no.add(sample);
				}
				
			}
			 
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
			msg.append("\"success\",\"count\":\""+totalnumber+"\",\"exist\":");
			msg.append(JSONArray.fromObject(listExist, jsonConfig));
			msg.append(",\"exist_no\":"+JSONArray.fromObject(listExist_no, jsonConfig));
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
    	
    	System.out.println(createLot());
		return null;
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
			List<Sample> tempList= new ArrayList<Sample>();
			for(int i=0;i<updateJA.size();i++){
				JSONObject  updateJO = (JSONObject) updateJA.get(i);
				Sample sample =new Sample(); 
				if(updateJO.containsKey("id"))
					sample.setId(Long.parseLong(updateJO.getString("id")));
				if(updateJO.containsKey("fq_flg"))
					sample.setFq_flg(updateJO.getString("fq_flg"));
				tempList.add(sample);
			}
			
			try {
				iSampleService.mulupdateSample(tempList);
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
     * 编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createCode(){
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
    /**
     * 
     * 批号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createLot(){
    	//http://192.168.1.144/Check/Sample?prod_id=14&bu_id=1&pid=194&sy_dt=2014-04-30 00:00:00&c_id=11
    	String groupcode= "";
    	//sdf = new SimpleDateFormat("yy-MM-dd");
    	Pact pact = iPactService.selectpactById(pid);
		StringBuffer sb = new StringBuffer();
		String sy_dtStr="";
		try {
			sy_dtStr = sy_dt.substring(0,10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    		templot.setHt_id(pid);
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
