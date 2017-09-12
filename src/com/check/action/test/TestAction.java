package com.check.action.test;
import java.text.SimpleDateFormat;
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
import com.check.model.entrust.Entrust;
import com.check.model.lov.Lov;
import com.check.model.prod.Prod;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
import com.check.service.accnt.IAccntService;
import com.check.service.bu.IBuService;
import com.check.service.entrust.IEntrustService;
import com.check.service.lov.ILovService;
import com.check.service.prod.IProdService;
import com.check.service.sample.ISampleService;
import com.check.service.test.ITestService;
import com.check.utils.CodeUtils;
import com.check.utils.MatrixToImageWriter;
import com.opensymphony.xwork2.Action;
public class TestAction implements Action {
	private int page;
	private int size;
	private int totalpage;
	private int totalnumber;
	private String message;
	private String callback;//跨域
	@Autowired
	private ITestService iTestService;
	@Autowired
	private IBuService iBuService;
	@Autowired
	private ILovService iLovService;
	 
	@Autowired
	private ISampleService iSampleService;
	@Autowired
	private IProdService iProdService;
 
	@Autowired
	private IEntrustService iEntrustService;
	@Autowired
	private IAccntService iAccntService;
	private List<Test> list;
	private Test test;
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
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public List<Test> getList() {
		return list;
	}
	public void setList(List<Test> list) {
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
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private String sy_dt;
	public String getSy_dt() {
		return sy_dt;
	}
	public void setSy_dt(String sy_dt) {
		this.sy_dt = sy_dt;
	}
	private String dd_t;
	public String getDd_t() {
		return dd_t;
	}
	public void setDd_t(String dd_t) {
		this.dd_t = dd_t;
	}
	private String sample_id;
	public String getSample_id() {
		return sample_id;
	}
	public void setSample_id(String sample_id) {
		this.sample_id = sample_id;
	}
	private String jl_t;
	public String getJl_t() {
		return jl_t;
	}
	public void setJl_t(String jl_t) {
		this.jl_t = jl_t;
	}
	private String sy_id;
	public String getSy_id() {
		return sy_id;
	}
	public void setSy_id(String sy_id) {
		this.sy_id = sy_id;
	}
	private String syqz_id;
	public String getSyqz_id() {
		return syqz_id;
	}
	public void setSyqz_id(String syqz_id) {
		this.syqz_id = syqz_id;
	}
	private String sh_id;
	public String getSh_id() {
		return sh_id;
	}
	public void setSh_id(String sh_id) {
		this.sh_id = sh_id;
	}
	private String shyj_t;
	public String getShyj_t() {
		return shyj_t;
	}
	public void setShyj_t(String shyj_t) {
		this.shyj_t = shyj_t;
	}
	private String shqz_id;
	public String getShqz_id() {
		return shqz_id;
	}
	public void setShqz_id(String shqz_id) {
		this.shqz_id = shqz_id;
	}
	private String spqz_id;
	public String getSpqz_id() {
		return spqz_id;
	}
	public void setSpqz_id(String spqz_id) {
		this.spqz_id = spqz_id;
	}
	private String bg_f;
	public String getBg_f() {
		return bg_f;
	}
	public void setBg_f(String bg_f) {
		this.bg_f = bg_f;
	}
	private String sh_dt;
	public String getSh_dt() {
		return sh_dt;
	}
	public void setSh_dt(String sh_dt) {
		this.sh_dt = sh_dt;
	}
	private String bg_dt;
	public String getBg_dt() {
		return bg_dt;
	}
	public void setBg_dt(String bg_dt) {
		this.bg_dt = bg_dt;
	}
	private String e_dt;
	public String getE_dt() {
		return e_dt;
	}
	public void setE_dt(String e_dt) {
		this.e_dt = e_dt;
	}
	private String cm_tx;
	public String getCm_tx() {
		return cm_tx;
	}
	public void setCm_tx(String cm_tx) {
		this.cm_tx = cm_tx;
	}
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String fq_flg;
	public String getFq_flg() {
		return fq_flg;
	}
	public void setFq_flg(String fq_flg) {
		this.fq_flg = fq_flg;
	}
	private String ewm;
	public String getEwm() {
		return ewm;
	}
	public void setEwm(String ewm) {
		this.ewm = ewm;
	}
 
	public String add() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		Test test =new Test(); 
		if(id!=null&&!id.equals(""))
		test.setId(Long.parseLong(id));
		test.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		test.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		test.setUp_dt(sdf.parse(up_dt));
		test.setC_id(c_id);
		test.setPid(pid);
		test.setBu_id(bu_id);
		//代码
		Entrust entrust = iEntrustService.selectentrustById(pid);
		if(entrust.getFlg().equals("Y")){
			test.setCode(createCode());
		}
		else{
			//追加
			test.setCode(createAppend());
		}
		
		test.setSy_dt(sy_dt);
		test.setDd_t(dd_t);
		test.setSample_id(sample_id);
		test.setJl_t(jl_t);
		test.setSy_id(sy_id);
		test.setSyqz_id(syqz_id);
		test.setSh_id(sh_id);
		test.setShyj_t(shyj_t);
		test.setShqz_id(shqz_id);
		test.setSpqz_id(spqz_id);
		test.setBg_f(bg_f);
		test.setSh_dt(sh_dt);
		test.setBg_dt(bg_dt);
		test.setE_dt(e_dt);
		test.setCm_tx(cm_tx);
		test.setStatus(status);
		test.setFq_flg(fq_flg);
		test.setEwm(ewm);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = Integer.parseInt(iTestService.addtest(test).toString());
			if(result>0){
				String qrResult = MatrixToImageWriter.createQrImage("EX_"+test.getId());
				if(qrResult.length()>0){
					HttpServletRequest request = ServletActionContext.getRequest();
					String path = request.getScheme() + "://"
							+ request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					Test uptest = new Test();
					uptest.setId(test.getId());
					uptest.setEwm(path+"/QRImages/"+qrResult);
					iTestService.updatetest(uptest);
					
				}
			}
			msg.append("\"success\",\"msg\":\"");
			msg.append(test.getId()+"\"");
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
			paramMap.put("pid", pid);
			paramMap.put("bu_id", bu_id);
			paramMap.put("code", code);
			paramMap.put("sy_dt", sy_dt);
			paramMap.put("dd_t", dd_t);
			paramMap.put("sample_id", sample_id);
			paramMap.put("jl_t", jl_t);
			paramMap.put("sy_id", sy_id);
			paramMap.put("syqz_id", syqz_id);
			paramMap.put("sh_id", sh_id);
			paramMap.put("shyj_t", shyj_t);
			paramMap.put("shqz_id", shqz_id);
			paramMap.put("spqz_id", spqz_id);
			paramMap.put("bg_f", bg_f);
			paramMap.put("sh_dt", sh_dt);
			paramMap.put("bg_dt", bg_dt);
			paramMap.put("e_dt", e_dt);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("status", status);
			paramMap.put("fq_flg", fq_flg);
			paramMap.put("ewm", ewm);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			list=iTestService.selecttestByParam(paramMap); 
			totalnumber=iTestService.selectCounttestByParam(paramMap);
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
		Test test =new Test(); 
		if(id!=null&&!id.equals(""))
		test.setId(Long.parseLong(id));
		test.setRow_id(row_id);
		if(c_dt!=null&&!c_dt.equals(""))
		test.setC_dt(sdf.parse(c_dt));
		if(up_dt!=null&&!up_dt.equals(""))
		test.setUp_dt(sdf.parse(up_dt));
		test.setC_id(c_id);
		test.setPid(pid);
		test.setBu_id(bu_id);
		test.setCode(code);
		test.setSy_dt(sy_dt);
		test.setDd_t(dd_t);
		test.setSample_id(sample_id);
		test.setJl_t(jl_t);
		test.setSy_id(sy_id);
		test.setSyqz_id(syqz_id);
		test.setSh_id(sh_id);
		test.setShyj_t(shyj_t);
		test.setShqz_id(shqz_id);
		test.setSpqz_id(spqz_id);
		test.setBg_f(bg_f);
		test.setSh_dt(sh_dt);
		test.setBg_dt(bg_dt);
		test.setE_dt(e_dt);
		test.setCm_tx(cm_tx);
		test.setStatus(status);
		test.setFq_flg(fq_flg);
		test.setEwm(ewm);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			iTestService.updatetest(test);
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
			iTestService.deletetest(id);
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
			test=iTestService.selecttestById(id);
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
			msg.append(JSONObject.fromObject(test, jsonConfig));
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
			paramMap.put("bu_id", bu_id);
			paramMap.put("code", code);
			paramMap.put("sy_dt", sy_dt);
			paramMap.put("dd_t", dd_t);
			paramMap.put("sample_id", sample_id);
			paramMap.put("jl_t", jl_t);
			paramMap.put("sy_id", sy_id);
			paramMap.put("syqz_id", syqz_id);
			paramMap.put("sh_id", sh_id);
			paramMap.put("shyj_t", shyj_t);
			paramMap.put("shqz_id", shqz_id);
			paramMap.put("spqz_id", spqz_id);
			paramMap.put("bg_f", bg_f);
			paramMap.put("sh_dt", sh_dt);
			paramMap.put("bg_dt", bg_dt);
			paramMap.put("e_dt", e_dt);
			paramMap.put("cm_tx", cm_tx);
			paramMap.put("status", status);
			paramMap.put("fq_flg", fq_flg);
			paramMap.put("ewm", ewm);
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			totalnumber=iTestService.selectCounttestByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber);
			list=iTestService.selecttestByParam(paramMap); 
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
    	System.out.println(createAppend());
		return null;
	}
    
    /**
     * 
     * 编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createCode(){
    	//http://192.168.1.144/Check/Test?sample_id=2868&bu_id=1
		 
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
		
		Sample sample= iSampleService.selectsampleById(sample_id);
		Prod prod = iProdService.selectprodById(sample.getProd_id());
		
		sb.append(prod.getDh_lv());
		
		//查询条件
		String loc = bu.getLoc()+"_"+prod.getDh_lv();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		sb.append((year-2000));
		paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("bu_id", bu_id);
		paramMap.put("nm_t", loc);
		paramMap.put("order", "ORDER BY SORT DESC");
		Lov templov = new Lov();
		String newSort=""; 
		lovList= iLovService.selectlovByParamOrder(paramMap);
		if(lovList.size()>0){
			String lovSort=lovList.get(0).getSort();
			Long lovId=lovList.get(0).getId();
			String cm_tx= lovList.get(0).getCm_tx();
			if(lovSort.equals("")||lovSort.equals("null")||!cm_tx.equals((year-2000)+"")){
				newSort = "001";
				templov.setSort(newSort);
				templov.setCm_tx((year-2000)+"");
				templov.setId(lovId);
			}
			else{
				//更新
				int sortNum=Integer.valueOf(lovSort).intValue();
				newSort = CodeUtils.autoGenericCode(sortNum+"",3);
				 
				templov.setCm_tx((year-2000)+"");
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
			templov.setCm_tx((year-2000)+"");
			templov.setNm_t(loc);
			templov.setSort(newSort);
			templov.setTy_lv("代码");
			iLovService.addlov(templov);
		}
		sb.append(newSort);
    	
    	 
    	return sb.toString();
    }
    /**
     * 
     * 追加编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createAppend(){
    	//http://192.168.1.144/Check/Test?sample_id=2868&bu_id=1
		 
		StringBuffer sb = new StringBuffer();
		//Bu bu= iBuService.selectbuById(bu_id);
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("ty_lv", "组织编码");
		paramMap.put("bu_id", bu_id);
		
		List<Lov> lovList= iLovService.selectlovByParam(paramMap);
		if(lovList.size()>0){
			sb.append(lovList.get(0).getNm_t());
		}
		
		Sample sample= iSampleService.selectsampleById(sample_id);
		Prod prod = iProdService.selectprodById(sample.getProd_id());
		//Pact pact = iPactService.selectpactById(sample.getPid());
		Entrust entrust = iEntrustService.selectentrustById(pid);
		 
		sb.append(prod.getDh_lv());
		
		 
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		sb.append((year-2000));
		paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("bu_id", bu_id);
		paramMap.put("prod_id", prod.getId());
		paramMap.put("flg", "N");
		paramMap.put("wt_dtFrom", year+"-01-01 00:00:00");
		paramMap.put("wt_dtTo", year+"-12-31 23:59:59");
		
		List<Test> tempList = iTestService.selectFirstTestCode(paramMap);
		 
		if(tempList.size()>0){
			String codeStr = tempList.get(0).getCode();
			int sortNum=Integer.valueOf(codeStr.substring(5,codeStr.length())).intValue();
			String newSort = CodeUtils.autoGenericCode(sortNum+"",3);
			sb.append(newSort);
		}
		else{
			paramMap = new HashMap();
			paramMap.put("fromPage",0);
			paramMap.put("toPage",1); 
			paramMap.put("bu_id", bu_id);
			paramMap.put("prod_id", prod.getId());
			paramMap.put("flg", "Y");
			paramMap.put("wt_dtFrom", year+"-01-01 00:00:00");
			paramMap.put("wt_dtTo",  entrust.getWt_dt());
			
			tempList = iTestService.selectFirstTestCode(paramMap);
			if(tempList.size()>0){
				String codeStr = tempList.get(0).getCode();
				int sortNum=Integer.valueOf(codeStr.substring(5,codeStr.length())).intValue();
				String newSort = CodeUtils.autoGenericCode(sortNum+"",3);
				sb.append(newSort);
			}
			else{
				sb.append("001");
			}
			
			
		}
		  
    	return sb.toString();
    }
    
}
