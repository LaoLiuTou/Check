package com.check.service.entrust;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.check.dao.entrust.IEntrustMapper;
import com.check.model.accnt.Accnt;
import com.check.model.atta.Atta;
import com.check.model.bu.Bu;
import com.check.model.cont.Cont;
import com.check.model.entrust.Entrust;
import com.check.model.entrust_sample.Entrust_sample;
import com.check.model.lot.Lot;
import com.check.model.lov.Lov;
import com.check.model.pact.Pact;
import com.check.model.prod.Prod;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
import com.check.service.accnt.IAccntService;
import com.check.service.atta.IAttaService;
import com.check.service.bu.IBuService;
import com.check.service.cont.IContService;
import com.check.service.entrust_sample.IEntrust_sampleService;
import com.check.service.lot.ILotService;
import com.check.service.lov.ILovService;
import com.check.service.pact.IPactService;
import com.check.service.prod.IProdService;
import com.check.service.sample.ISampleService;
import com.check.service.test.ITestService;
import com.check.utils.CodeUtils;
import com.check.utils.MatrixToImageWriter;
public class EntrustServiceImpl  implements IEntrustService {

	@Autowired
	private IEntrustMapper iEntrustMapper;
	
	
	//@Autowired
	//private IEntrustService iEntrustService;
	@Autowired
	private IAccntService iAccntService;
	@Autowired
	private ILovService iLovService;
	@Autowired
	private ILotService iLotService;
	
	@Autowired
	private IContService iContService;
	@Autowired
	private IAttaService iAttaService;
	@Autowired
	private IPactService iPactService;
	@Autowired
	private ISampleService iSampleService;
	@Autowired
	private IEntrust_sampleService iEntrust_sampleService;
	@Autowired
	private ITestService iTestService;

	@Autowired
	private IBuService iBuService;
	
	@Autowired
	private IProdService iProdService;
	
	Logger logger = Logger.getLogger("CheckLogger");
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Entrust selectentrustById(String id){
		return iEntrustMapper.selectentrustById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Entrust> selectentrustByParam(Map paramMap){ 
		return iEntrustMapper.selectentrustByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountentrustByParam(Map paramMap){ 
		return iEntrustMapper.selectCountentrustByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateentrust(Entrust entrust){
		return iEntrustMapper.updateentrust(entrust);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addentrust(Entrust entrust){
		return iEntrustMapper.addentrust(entrust);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteentrust(String id){
		return iEntrustMapper.deleteentrust(id);
	}

 
 	/**
	 * Flow
	 * @param entrust
	 * @param accnt
	 * @param pact
	 * @param sample
	 * @param entrust_sample
	 * @param test
	 * @param results
	 * @return
	 */
	@SuppressWarnings("unused")
	@Transactional
	public  int entrustFlow(Entrust entrust,String accnt,String pact,String sample,String entrust_sample,
			String test,String results){
		
		int result =0;
		//委托单entrust
		 
		try {
			//客户accnt
			String accntId="";
			if(accnt!=null){
				accntId=createAccnt(accnt);
			}
			
			//合同pact
			String pactId="";
			if(pact!=null){
				pactId=createPact(pact,accntId);
			}
			
			result = iEntrustMapper.addentrust(entrust);
			
			if(result>0){
				
				//样品sample
				List<String> sampleId= new ArrayList<String>();
				if(sample!=null){
					sampleId=createSample(sample,pactId);
				}
				
				 
				
				//样品委托单中间表  entrust_sample
				List<String> entrust_sampleId= new ArrayList<String>();
				if(entrust_sample!=null){
					entrust_sampleId=createEntrust_sample(entrust_sample,entrust,sampleId);
				}
				
				
				
				//试验单 test
				List<String> testId= new ArrayList<String>();
				if(test!=null){
					testId=createTest(test,sampleId,entrust);
				}
				
				
				//结果表results
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	
    	
    	
		return result;
	}
	 
	/**
	 * 样品 委托单 中间表
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public  List<String> seFlow(Entrust entrust,JSONObject sampleTree){
		
		int result =0;
		//委托单entrust
		List<String> entrustId= new ArrayList<String>();
		try {
			
		//JSONObject sampleTree =JSONObject.fromObject(sample);
		     Iterator iterator = sampleTree.keys();
		     while(iterator.hasNext()){
		        String key = (String) iterator.next();
		        JSONArray sampleJA =sampleTree.getJSONArray(key);
		        //编号
		        //String pid,String wt_dt,String bu_id,String c_id
		        if(entrust.getFlg().equals("Y")){
			    	entrust.setCode(createEntrustCode(entrust.getPid(),entrust.getWt_dt(),
								entrust.getBu_id(),entrust.getC_id()));
				}
				else{
					entrust.setCode(createEntrustAppend(entrust.getPid(),entrust.getWt_dt(),
								entrust.getBu_id(),entrust.getC_id()));
				}
		        //流水号
			    entrust.setLs_n(createEntrustLs_t(entrust.getBu_id()));
				//检测项目id
				entrust.setProd_id(key);
				result = iEntrustMapper.addentrust(entrust);
				if(result>0){
					//二维码
					String qrResult = MatrixToImageWriter.createQrImage("TG_"+entrust.getId());
					if(qrResult.length()>0){
						HttpServletRequest request = ServletActionContext.getRequest();
						String path = request.getScheme() + "://"
								+ request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath();
						Entrust upentrust = new Entrust();
						upentrust.setId(entrust.getId());
						upentrust.setEwm(path+"/QRImages/"+qrResult);
						iEntrustMapper.updateentrust(upentrust);
					}
					
					//返回id
					entrustId.add(entrust.getId()+"");
					//样品sample
					List<String> sampleId= new ArrayList<String>();
					if(sampleJA.size()>0){
						sampleId=createSample(sampleJA.toString(),entrust.getPid());
					}
					
					//样品委托单中间表  entrust_sample
					for(int i=0;i<sampleId.size();i++){
						Entrust_sample entrust_sample= new Entrust_sample();
						entrust_sample.setC_id(entrust.getC_id());
						entrust_sample.setPid(entrust.getId()+"");
						entrust_sample.setSample_id(sampleId.get(i));
						iEntrust_sampleService.addentrust_sample(entrust_sample);		 
					 }
				}
		     }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			entrustId.clear();
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return entrustId;
	}

	
	
	/**
	 * 创建客户
	 * @param jsonStr
	 * @return 客户id
	 */
    
    private String  createAccnt(String jsonStr){
    	
    	try {
    		int result =0;
	    	JSONObject JO = JSONObject.fromObject(jsonStr);
	    	Accnt accnt =new Accnt(); 
	    	if(JO.containsKey("web_id")){
	    		
	    		if(JO.containsKey("c_id"))
				accnt.setC_id(JO.getString("c_id"));
	    		if(JO.containsKey("nm_t"))
				accnt.setNm_t(JO.getString("nm_t"));
	    		if(JO.containsKey("xz_lv"))
				accnt.setXz_lv(JO.getString("xz_lv"));
	    		if(JO.containsKey("wz_t"))
				accnt.setWz_t(JO.getString("wz_t"));
	    		if(JO.containsKey("hy_lv"))
				accnt.setHy_lv(JO.getString("hy_lv"));
	    		if(JO.containsKey("ty_lv"))
				accnt.setTy_lv(JO.getString("ty_lv"));
	    		if(JO.containsKey("lv_lv"))
				accnt.setLv_lv(JO.getString("lv_lv"));
	    		if(JO.containsKey("st_lv"))
				accnt.setSt_lv(JO.getString("st_lv"));
	    		if(JO.containsKey("cm_tx"))
				accnt.setCm_tx(JO.getString("cm_tx"));
	    		if(JO.containsKey("work_p"))
				accnt.setWork_p(JO.getString("work_p"));
	    		if(JO.containsKey("state"))
				accnt.setState(JO.getString("state"));
	    		if(JO.containsKey("city"))
				accnt.setCity(JO.getString("city"));
	    		if(JO.containsKey("county"))
				accnt.setCounty(JO.getString("county"));
	    		if(JO.containsKey("street"))
				accnt.setStreet(JO.getString("street"));
	    		if(JO.containsKey("postal"))
				accnt.setPostal(JO.getString("postal"));
	    		if(JO.containsKey("bu_id"))
				accnt.setBu_id(JO.getString("bu_id"));
				//accnt.setCont_id(cont_id);
				result = Integer.parseInt(iAccntService.addaccnt(accnt).toString());
	    	}
	    	else{
	    		result=1;
	    		accnt= iAccntService.selectaccntById(JO.getString("id"));
	    	}
			if(result>0){
				//联系人
		    	JSONArray contJA=JO.getJSONArray("cont");
				if(contJA!=null){
					 
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
						if(contJO.getString("flag").equals("Y")){
							Accnt tempAccnt = new Accnt();
							tempAccnt.setCont_id(cont.getId()+"");
							tempAccnt.setId(accnt.getId());
							tempAccnt.setUp_dt(null);
							iAccntService.updateaccnt(tempAccnt);
						}
					}
				}
				//联系人
		    	JSONArray attaJA=JO.getJSONArray("atta");
				if(attaJA!=null){
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
				}
			}
			return accnt.getId()+"";
    	}
		catch (Exception e) {
			// TODO: handle exception
			logger.info(e);
			return "";
		}
    }
    
	 
	/**
	 * 创建合同
	 * @param jsonStr
	 * @param accntId
	 * @return 合同Id
	 */
   
	private String createPact(String jsonStr,String accntId) {
		// TODO Auto-generated method stub
		String pactId="";
		try {
			//合同
			JSONObject pactJO=JSONObject.fromObject(jsonStr);
			if(pactJO!=null){
				if(pactJO.containsKey("web_id")){ 
					Pact pact = new Pact();
					if(pactJO.containsKey("bu_id"))
					pact.setBu_id(pactJO.getString("bu_id"));
					if(pactJO.containsKey("c_id"))
					pact.setC_id(pactJO.getString("c_id"));
					if(pactJO.containsKey("city"))
					pact.setCity(pactJO.getString("city"));
					if(pactJO.containsKey("cm_tx"))
					pact.setCm_tx(pactJO.getString("cm_tx"));
					//if(pactJO.containsKey("code"))
					if(pactJO.containsKey("bu_id")&&pactJO.containsKey("ty_lv"))
					pact.setCode(createPactCode(pactJO.getString("bu_id"),pactJO.getString("ty_lv")));
					if(pactJO.containsKey("cont_id"))
					pact.setCont_id(pactJO.getString("cont_id"));
					if(pactJO.containsKey("county"))
					pact.setCounty(pactJO.getString("county"));
					if(pactJO.containsKey("db_id"))
					pact.setDb_id(pactJO.getString("db_id"));
					if(pactJO.containsKey("ff_dt"))
					pact.setFf_dt(pactJO.getString("ff_dt"));
					if(pactJO.containsKey("flg"))
					pact.setFlg(pactJO.getString("flg"));
					if(pactJO.containsKey("jhwc_dt"))
					pact.setJhwc_dt(pactJO.getString("jhwc_dt"));
					if(pactJO.containsKey("jl_cont_id"))
					pact.setJl_cont_id(pactJO.getString("jl_cont_id"));
					if(pactJO.containsKey("jl_id"))
					pact.setJl_id(pactJO.getString("jl_id"));
					//if(pactJO.containsKey("ls_t"))
					//流水号
					if(pactJO.containsKey("bu_id"))
					pact.setLs_t(createPactLs_t(pactJO.getString("bu_id")));
					//pact.setLs_t(pactJO.getString("ls_t"));
					if(pactJO.containsKey("nm_t"))
					pact.setNm_t(pactJO.getString("nm_t"));
					pact.setPid(accntId);
					if(pactJO.containsKey("qd_dt"))
					pact.setQd_dt(pactJO.getString("qd_dt"));
					if(pactJO.containsKey("row_id"))
					pact.setRow_id(pactJO.getString("row_id"));
					if(pactJO.containsKey("sjks_dt"))
					pact.setSjks_dt(pactJO.getString("sjks_dt"));
					if(pactJO.containsKey("sjwc_dt"))
					pact.setSjwc_dt(pactJO.getString("sjwc_dt"));
					if(pactJO.containsKey("st_lv"))
					pact.setSt_lv(pactJO.getString("st_lv"));
					if(pactJO.containsKey("state"))
					pact.setState(pactJO.getString("state"));
					if(pactJO.containsKey("street"))
					pact.setStreet(pactJO.getString("street"));
					if(pactJO.containsKey("terms"))
					pact.setTerms(pactJO.getString("terms"));
					if(pactJO.containsKey("tj_f"))
					pact.setTj_f(pactJO.getString("tj_f"));
					if(pactJO.containsKey("ty_lv"))
					pact.setTy_lv(pactJO.getString("ty_lv"));
					if(pactJO.containsKey("wtfy"))
					pact.setWtfy(pactJO.getString("wtfy"));
				 
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
						pactId=pact.getId()+"";
					}
				}
				else{
					pactId=pactJO.getString("id");
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		return pactId;
	}
    
	/**
	 * 创建样品
	 * @param jsonStr
	 * @param pactId
	 * @return 样品ids
	 */
	private List<String> createSample(String jsonStr, String pactId) {
		List<String> sampleIds= new ArrayList<String>();
		try {
			//样品
			JSONArray sampleJA=JSONArray.fromObject(jsonStr);
			if(sampleJA!=null){
				for(int i=0;i<sampleJA.size();i++){
					JSONObject  sampleJO = (JSONObject) sampleJA.get(i);
					
					if(sampleJO.containsKey("web_id")){
						Sample sample = new Sample();
						if(sampleJO.containsKey("bu_id"))
						sample.setBu_id(sampleJO.getString("bu_id"));
						if(sampleJO.containsKey("bw_t"))
						sample.setBw_t(sampleJO.getString("bw_t"));
						if(sampleJO.containsKey("c_id"))
						sample.setC_id(sampleJO.getString("c_id"));
						if(sampleJO.containsKey("cd_t"))
						sample.setCd_t(sampleJO.getString("cd_t"));
						if(sampleJO.containsKey("ch_t"))
						sample.setCh_t(sampleJO.getString("ch_t"));
						if(sampleJO.containsKey("cj_lv"))
						sample.setCj_lv(sampleJO.getString("cj_lv"));
						if(sampleJO.containsKey("cm_tx"))
						sample.setCm_tx(sampleJO.getString("cm_tx"));
						if(sampleJO.containsKey("copy_id"))
						sample.setCopy_id(Long.parseLong(sampleJO.getString("copy_id")));
						if(sampleJO.containsKey("db_n"))
						sample.setDb_n(sampleJO.getString("db_n"));
						if(sampleJO.containsKey("dw_lv"))
						sample.setDw_lv(sampleJO.getString("dw_lv"));
						//sample.setEwm(pactId);
						if(sampleJO.containsKey("flg"))
						sample.setFlg(sampleJO.getString("flg"));
						if(sampleJO.containsKey("fq_flg"))
						sample.setFq_flg(sampleJO.getString("fq_flg"));
						if(sampleJO.containsKey("fz_id"))
						sample.setFz_id(sampleJO.getString("fz_id"));
						if(sampleJO.containsKey("gc_t"))
						sample.setGc_t(sampleJO.getString("gc_t"));
						if(sampleJO.containsKey("gg_t"))
						sample.setGg_t(sampleJO.getString("gg_t"));
						if(sampleJO.containsKey("hi_t"))
						sample.setHi_t(sampleJO.getString("hi_t"));
						if(sampleJO.containsKey("jc_f"))
						sample.setJc_f(sampleJO.getString("jc_f"));
						if(sampleJO.containsKey("jd_lv"))
						sample.setJd_lv(sampleJO.getString("jd_lv"));
						if(sampleJO.containsKey("jl_t"))
						sample.setJl_t(sampleJO.getString("jl_t"));
						if(sampleJO.containsKey("kd_t"))
						sample.setKd_t(sampleJO.getString("kd_t"));
						if(sampleJO.containsKey("la_t"))
						sample.setLa_t(sampleJO.getString("la_t"));
						if(sampleJO.containsKey("lo_t"))
						sample.setLo_t(sampleJO.getString("lo_t"));
						//if(sampleJO.containsKey("lot"))
						//批号
						if(sampleJO.containsKey("bu_id")&&sampleJO.containsKey("sy_dt")&&
								sampleJO.containsKey("prod_id")&&sampleJO.containsKey("c_id")){
							sample.setLot(createSampleLot(sampleJO.getString("bu_id"),sampleJO.getString("sy_dt"),
									pactId,sampleJO.getString("prod_id"),sampleJO.getString("c_id")));
						}
						
						if(sampleJO.containsKey("ly_lv"))
						sample.setLy_lv(sampleJO.getString("ly_lv"));
						if(sampleJO.containsKey("nm_t"))
						sample.setNm_t(sampleJO.getString("nm_t"));
						//if(sampleJO.containsKey("part"))
						//编号
						if(sampleJO.containsKey("bu_id")&&sampleJO.containsKey("prod_id"))
						sample.setPart(createSampleCode(sampleJO.getString("bu_id"),sampleJO.getString("prod_id")));
						 
						sample.setPid(pactId);
						if(sampleJO.containsKey("pp_t"))
						sample.setPp_t(sampleJO.getString("pp_t"));
						if(sampleJO.containsKey("prod_id"))
						sample.setProd_id(sampleJO.getString("prod_id"));
						if(sampleJO.containsKey("qd_t"))
						sample.setQd_t(sampleJO.getString("qd_t"));
						if(sampleJO.containsKey("row_id"))
						sample.setRow_id(sampleJO.getString("row_id"));
						if(sampleJO.containsKey("sc_id"))
						sample.setSc_id(sampleJO.getString("sc_id"));
						if(sampleJO.containsKey("sh_t"))
						sample.setSh_t(sampleJO.getString("sh_t"));
						if(sampleJO.containsKey("sn_t"))
						sample.setSn_t(sampleJO.getString("sn_t"));
						if(sampleJO.containsKey("snl_t"))
						sample.setSnl_t(sampleJO.getString("snl_t"));
						if(sampleJO.containsKey("st_lv"))
						sample.setSt_lv(sampleJO.getString("st_lv"));
						if(sampleJO.containsKey("st_t"))
						sample.setSt_t(sampleJO.getString("st_t"));
						if(sampleJO.containsKey("sy_dt"))
						sample.setSy_dt(sampleJO.getString("sy_dt"));
						if(sampleJO.containsKey("sz_t"))
						sample.setSz_t(sampleJO.getString("sz_t"));
						if(sampleJO.containsKey("test_dt"))
						sample.setTest_dt(sampleJO.getString("test_dt"));
						if(sampleJO.containsKey("txm"))
						sample.setTxm(sampleJO.getString("txm"));
						if(sampleJO.containsKey("ty_lv"))
						sample.setTy_lv(sampleJO.getString("ty_lv"));
						if(sampleJO.containsKey("wa_t"))
						sample.setWa_t(sampleJO.getString("wa_t"));
						if(sampleJO.containsKey("wj_t"))
						sample.setWj_t(sampleJO.getString("wj_t"));
						if(sampleJO.containsKey("zz_dt"))
						sample.setZz_dt(sampleJO.getString("zz_dt"));
						
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
							
						}
						sampleIds.add(sample.getId()+"");
					}
					else{
						sampleIds.add(sampleJO.getString("id"));
					}
					
				 }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		return sampleIds;
	}
	
	
    
	/**
	 * 创建试验单
	 * @param jsonStr
	 * @param pactId
	 * @return 样品ids
	 */
	private List<String> createTest(String jsonStr, List<String> sampleId,Entrust entrust) {
		List<String> testIds= new ArrayList<String>();
		try {
			//试验单
			JSONArray testJA=JSONArray.fromObject(jsonStr);
			if(testJA!=null){
				for(int i=0;i<testJA.size();i++){
					JSONObject  testJO = (JSONObject) testJA.get(i);
					
					if(testJO.containsKey("web_id")){
						 Test test= new Test();
						 if(testJO.containsKey("bg_dt"))
						 test.setBg_dt(testJO.getString("bg_dt"));
						 if(testJO.containsKey("bg_f"))
						 test.setBg_f(testJO.getString("bg_f"));
						 if(testJO.containsKey("c_id"))
						 test.setC_id(testJO.getString("c_id"));
						 if(testJO.containsKey("cm_tx"))
						 test.setCm_tx(testJO.getString("cm_tx"));
						 //if(testJO.containsKey("code"))
						 if(entrust.getFlg().equals("Y")){
							 test.setCode(createTestCode(sampleId.get(i),entrust.getBu_id()));
						 }
						 else{
							//追加
							test.setCode(createTestAppend(entrust,sampleId.get(i)));
						 }
					 
						 if(testJO.containsKey("dd_t"))
						 test.setDd_t(testJO.getString("dd_t"));
						 if(testJO.containsKey("e_dt"))
						 test.setE_dt(testJO.getString("e_dt"));
						 //test.setEwm(jsonStr);
						 if(testJO.containsKey("fq_flg"))
						 test.setFq_flg(testJO.getString("fq_flg"));
						 if(testJO.containsKey("jl_t"))
						 test.setJl_t(testJO.getString("jl_t"));
						 //委托单id
						 test.setPid(entrust.getId()+"");
						 if(testJO.containsKey("row_id"))
						 test.setRow_id(testJO.getString("row_id"));
						 test.setSample_id(sampleId.get(i));
						 if(testJO.containsKey("sh_dt"))
						 test.setSh_dt(testJO.getString("sh_dt"));
						 if(testJO.containsKey("sh_id"))
						 test.setSh_id(testJO.getString("sh_id"));
						 if(testJO.containsKey("shqz_id"))
						 test.setShqz_id(testJO.getString("shqz_id"));
						 if(testJO.containsKey("shyj_t"))
						 test.setShyj_t(testJO.getString("shyj_t"));
						 if(testJO.containsKey("spqz_id"))
						 test.setSpqz_id(testJO.getString("spqz_id"));
						 if(testJO.containsKey("status"))
						 test.setStatus(testJO.getString("status"));
						 if(testJO.containsKey("sy_dt"))
						 test.setSy_dt(testJO.getString("sy_dt"));
						 if(testJO.containsKey("sy_id"))
						 test.setSy_id(testJO.getString("sy_id"));
						 if(testJO.containsKey("syqz_id"))
						 test.setSyqz_id(testJO.getString("syqz_id")); 
						 
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
						
						testIds.add(test.getId()+"");
					}
					else{
						testIds.add(testJO.getString("id"));
					}
					
				 }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		return testIds;
	}
	
	
	/**
	 * 创建样品委托中间表
	 * @param jsonStr
	 * @param pactId
	 * @return 样品ids
	 */
	private List<String> createEntrust_sample(String jsonStr, Entrust entrust,List<String> sampleId) {
		List<String> entrust_sampleIds= new ArrayList<String>();
		try {
			//样品
			JSONObject entrust_sampleJO=JSONObject.fromObject(jsonStr);
			if(entrust_sampleJO!=null){
				 
				String c_id =entrust_sampleJO.getString("c_id");
				for(int i=0;i<sampleId.size();i++){
					Entrust_sample entrust_sample= new Entrust_sample();
					entrust_sample.setC_id(c_id);
					entrust_sample.setPid(entrust.getId()+"");
					entrust_sample.setSample_id(sampleId.get(i));
					iEntrust_sampleService.addentrust_sample(entrust_sample);	
					entrust_sampleIds.add(entrust_sample.getId()+"");
					 
				 }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		return entrust_sampleIds;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////////
	 /**
     * 
     * 合同编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createPactCode(String bu_id,String ty_lv){
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
     * 合同流水号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createPactLs_t(String bu_id){
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
	
    /**
     * 
     * 试验单编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createTestCode(String sample_id,String bu_id){
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
    public String createTestAppend(Entrust entrust,String sample_id){
    	//http://192.168.1.144/Check/Test?sample_id=2868&bu_id=1
		 
		StringBuffer sb = new StringBuffer();
		//Bu bu= iBuService.selectbuById(entrust.getBu_id());
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("ty_lv", "组织编码");
		paramMap.put("bu_id", entrust.getBu_id());
		
		List<Lov> lovList= iLovService.selectlovByParam(paramMap);
		if(lovList.size()>0){
			sb.append(lovList.get(0).getNm_t());
		}
		
		Sample sample= iSampleService.selectsampleById(sample_id);
		Prod prod = iProdService.selectprodById(sample.getProd_id());
		//Pact pact = iPactService.selectpactById(sample.getPid());
		//Entrust entrust = iEntrustService.selectentrustById(pid);
		 
		sb.append(prod.getDh_lv());
		
		 
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		sb.append((year-2000));
		paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("bu_id", entrust.getBu_id());
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
			paramMap.put("bu_id", entrust.getBu_id());
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
	
    
    
    /**
     * 
     * 编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createEntrustCode(String pid,String wt_dt,String bu_id,String c_id){
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
    public String createEntrustAppend(String pid,String wt_dt,String bu_id,String c_id){
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
    public String createEntrustLs_t(String bu_id){
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

}

