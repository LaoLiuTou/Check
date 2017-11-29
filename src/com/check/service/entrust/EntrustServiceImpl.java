package com.check.service.entrust;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

 
import com.check.dao.auth_group.IAuth_groupMapper;
import com.check.dao.auth_group_access.IAuth_group_accessMapper;
import com.check.dao.entrust.IEntrustMapper;
import com.check.dao.entrust_asset.IEntrust_assetMapper;
import com.check.dao.entrust_pin.IEntrust_pinMapper;
import com.check.dao.entrust_sample.IEntrust_sampleMapper;
import com.check.dao.prod_asset.IProd_assetMapper;
import com.check.dao.results.IResultsMapper;
import com.check.dao.test.ITestMapper;
import com.check.model.accnt.Accnt;
import com.check.model.atta.Atta;
import com.check.model.bu.Bu;
import com.check.model.cont.Cont;
import com.check.model.entrust.Entrust;
import com.check.model.entrust_asset.Entrust_asset;
import com.check.model.entrust_pin.Entrust_pin;
import com.check.model.entrust_sample.Entrust_sample;
import com.check.model.lot.Lot;
import com.check.model.lov.Lov;
import com.check.model.pact.Pact;
import com.check.model.prod.Prod;
import com.check.model.prod_asset.Prod_asset;
import com.check.model.results.Results;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
import com.check.service.accnt.IAccntService;
import com.check.service.atta.IAttaService;
import com.check.service.bu.IBuService;
import com.check.service.cont.IContService;
import com.check.service.entrust_pin.IEntrust_pinService;
import com.check.service.entrust_sample.IEntrust_sampleService;
import com.check.service.lot.ILotService;
import com.check.service.lov.ILovService;
import com.check.service.pact.IPactService;
import com.check.service.prod.IProdService;
import com.check.service.results.IResultsService;
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
	private IEntrust_pinService iEntrust_pinService;

	@Autowired
	private IBuService iBuService;
	
	@Autowired
	private IProdService iProdService;
	@Autowired
	private IResultsService iResultsService;
	
	@Autowired
	private IResultsMapper iResultsMapper;
	@Autowired
	private ITestMapper iTestMapper;
	@Autowired
	private IEntrust_sampleMapper iEntrust_sampleMapper;
	@Autowired
	private IEntrust_pinMapper iEntrust_pinMapper;
	@Autowired
	private IEntrust_assetMapper iEntrust_assetMapper;
	@Autowired
	private IProd_assetMapper iProd_assetMapper;
	@Autowired
	private IAuth_group_accessMapper iAuth_group_accessMapper;
	@Autowired
	private IAuth_groupMapper iAuth_groupMapper;
	
	
	
	
	Logger logger = Logger.getLogger("CheckLogger");
	/**
 * 通过id选取
 * @return
 */
	public Entrust selectentrustById(String id){
		return iEntrustMapper.selectentrustById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Entrust> selectentrustByParam(Map paramMap){ 
		return iEntrustMapper.selectentrustByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountentrustByParam(Map paramMap){ 
		return iEntrustMapper.selectCountentrustByParam(paramMap);
	}

	/**
	 * 更新 
	 * @return 
	 */ 
	 @Transactional
	public  int updateentrust(Entrust entrust){
		int result=0;
		//st_lv--已完成   test  --STATUS  已完成
		//st_lv--检验中   test  --STATUS  待审核
		result = iEntrustMapper.updateentrust(entrust);
		 

		if(result>0){
			//修改相关表fq_flg
			if(entrust.getFq_flg()!=null&&!entrust.getFq_flg().equals("")){
				Map paramMap = new HashMap();
				 paramMap.put("pid", entrust.getId());
				 int num = iTestMapper.selectCounttestByParam(paramMap);
				 paramMap.put("fromPage",0);
				 paramMap.put("toPage",num);
				 List<Test> tempList= iTestMapper.selecttestByParam(paramMap);
				 for(Test temp:tempList){
					 temp.setFq_flg(entrust.getFq_flg());
					 iTestMapper.updatetest(temp);
					 Sample sample = iSampleService.selectsampleById(temp.getSample_id());
					 if(sample!=null){
						 sample.setFq_flg(entrust.getFq_flg());
						 iSampleService.updatesample(sample);
					 }
				 }
			}
			
			if(entrust.getSt_lv()!=null){
				Test test = new Test();
				test.setPid(entrust.getId()+"");
				if(entrust.getSt_lv().equals("已完成")){
					test.setStatus("已完成");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("检验中")){
					test.setStatus("待审核");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已发放")){
					test.setStatus("已发放");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已存档")){
					test.setStatus("已存档");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("审批中")){
					test.setStatus("审批中");
					iTestService.updatetestbypid(test);
				}
				else{
					//do nothing
				}
			}
			
			if(entrust.getFlg()!=null&&entrust.getFlg().equals("N")){
				 //修改样品
				 Map paramMap = new HashMap ();
				paramMap.put("pid", entrust.getId()+"");
				 
				//paramMap.put("up_dtFrom", sdf.parse(updatetime));
				int esnum = iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",esnum);
				List<Entrust_sample> esList= iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
				
				for(Entrust_sample es:esList){
					if(es.getSample_id()!=null){
						//Sample tempSample= iSampleService.selectsampleById(es.getSample_id());
						Sample tempSample = new Sample();
						tempSample.setId(Long.parseLong(es.getSample_id()));
						tempSample.setTy_lv("追加样品");
						iSampleService.updatesample(tempSample);
						
					}
					 
				}
			 
			 }
			
			if(entrust.getSyr_id()!=null){
				Test test = new Test();
				test.setPid(entrust.getId()+"");
				test.setSy_id(entrust.getSyr_id());
				iTestService.updatetestbypid(test);
			}
			
			 if(entrust.getSjwc_dt()!=null){
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 Test test = new Test();
				 test.setPid(entrust.getId()+"");
				 test.setE_dt(sdf.format(entrust.getSjwc_dt()));
				 iTestService.updatetestbypid(test);
			 }
			
		}
		return result;
	}
 
	 /**
	  * 修改远程委托单流程
	  * @return 
	  */ 
	 @Transactional
	 public  int updateRemoteEntrustFlow(Entrust entrust,String entrust_pins,String entrust_assets){
		 int result=0;
		 //st_lv--已完成   test  --STATUS  已完成
		 //st_lv--检验中   test  --STATUS  待审核
		 
		 Prod pProd = iProdService.selectprodById(entrust.getProd_id());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if(entrust.getJh_dt()==null||entrust.getJh_dt().equals("")){
			 
			 if(entrust.getWt_dt()!=null&&pProd.getZq_n()!=null){
				 
				 GregorianCalendar gc=new GregorianCalendar();
				 gc.setTime(entrust.getWt_dt());
				 gc.add(5, Integer.parseInt(pProd.getZq_n()+""));
				 entrust.setJh_dt(sdf.format(gc.getTime()));
			 }
		 }
		 
		 //常规价、复检价
		 if(entrust.getCg_f().equals("Y")){
			 entrust.setPrice(pProd.getCgj());
		 }
		 else if(entrust.getCg_f().equals("N")){
			 entrust.setPrice(pProd.getFjj());
		 }
		
		 
		 if(entrust.getSt_lv()!=null){
				Test test = new Test();
				test.setPid(entrust.getId()+"");
				if(entrust.getSt_lv().equals("已完成")){
					test.setStatus("已完成");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("检验中")){
					test.setStatus("待审核");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已发放")){
					test.setStatus("已发放");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已存档")){
					test.setStatus("已存档");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("审批中")){
					test.setStatus("审批中");
					iTestService.updatetestbypid(test);
				}
				else{
					//do nothing
				}
			}
		 
		 
		 if(entrust.getFlg()!=null&&entrust.getFlg().equals("N")){
			 //修改样品
			 Map paramMap = new HashMap ();
			paramMap.put("pid", entrust.getId()+"");
			 
			//paramMap.put("up_dtFrom", sdf.parse(updatetime));
			int esnum = iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",esnum);
			List<Entrust_sample> esList= iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
			
			for(Entrust_sample es:esList){
				if(es.getSample_id()!=null){
					//Sample tempSample= iSampleService.selectsampleById(es.getSample_id());
					Sample tempSample = new Sample();
					tempSample.setId(Long.parseLong(es.getSample_id()));
					tempSample.setTy_lv("追加样品");
					iSampleService.updatesample(tempSample);
					
				}
				 
			}
		 
		 }
		 
		 Entrust  tempEntrust = iEntrustMapper.selectentrustById(entrust.getId()+"");
		 if(tempEntrust.getFlg()!=null&&tempEntrust.getFlg().equals("Y")&&entrust.getFlg().equals("N")){
			 entrust.setCode(createEntrustAppend(tempEntrust.getPid(),sdf.format(tempEntrust.getWt_dt()),
					 tempEntrust.getBu_id(),tempEntrust.getC_id()));
		 }
		 
		 
		 result = iEntrustMapper.updateentrust(entrust);
		 if(result>0){
			 
			 if(entrust.getProd_id()!=null){ 
				 //修改样品
				 Map paramMap = new HashMap ();
				paramMap.put("pid", entrust.getId()+"");
				 
				//paramMap.put("up_dtFrom", sdf.parse(updatetime));
				int esnum = iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",esnum);
				List<Entrust_sample> esList= iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
				
				for(Entrust_sample es:esList){
					if(es.getSample_id()!=null){
						Sample tempSample= iSampleService.selectsampleById(es.getSample_id());
						tempSample.setProd_id(entrust.getProd_id());
						iSampleService.updatesample(tempSample);
						
					}
					 
				}
			 }
			 
			 
			 entrust= iEntrustMapper.selectentrustById(entrust.getId()+"");
			 //删除
			//删除  entrust_pin
			 if(entrust_pins!=null&&!entrust_pins.equals("")){
				
				 iEntrust_pinMapper.deleteEntrust_pinByPid(entrust.getId()+"");
				 JSONArray epinJA = JSONArray.fromObject(entrust_pins);
				 for(int index=0;index<epinJA.size();index++){
					JSONObject epinJO = (JSONObject) epinJA.get(index);
					Entrust_pin entrust_pin =new Entrust_pin();
					entrust_pin.setC_id(entrust.getC_id());
					if(epinJO.containsKey("jc_f"))
					entrust_pin.setJc_f(epinJO.getString("jc_f"));
					if(epinJO.containsKey("jcx_id"))
					entrust_pin.setJcx_id(epinJO.getString("jcx_id"));
					entrust_pin.setPid(entrust.getId()+"");
					iEntrust_pinService.addentrust_pin(entrust_pin);
					 
				 }
				 
			 }
			
			 
			iEntrust_assetMapper.deleteentrust_assetbyentrust(entrust.getId()+"");
			if(entrust_assets!=null&&!entrust_assets.equals("")){
				 JSONArray entrust_assetJA=JSONArray.fromObject(entrust_assets);
				 for(int i=0;i<entrust_assetJA.size();i++){
						JSONObject  addJO = (JSONObject) entrust_assetJA.get(i);
						Entrust_asset ps=new Entrust_asset();
						if(addJO.containsKey("ass_id"))
							ps.setAss_id(addJO.getString("ass_id"));
						if(addJO.containsKey("flag"))
							ps.setFlag(addJO.getString("flag"));
						
						ps.setC_id(entrust.getC_id());
						ps.setAgree_id(entrust.getId()+"");
						iEntrust_assetMapper.addentrust_asset(ps); 
				 }
				 
			 }
			 
			 
		 }
		 return result;
	 }
	 /**
	  * 更新 流程
	  * @return 
	  */ 
	 @Transactional
	 public  int updateentrustFlow(Entrust entrust,String p_status, String entrust_samples,String entrust_pins,String entrust_assets){
		 int result=0;
		 //st_lv--已完成   test  --STATUS  已完成
		 //st_lv--检验中   test  --STATUS  待审核
		 
		 Prod pProd = iProdService.selectprodById(entrust.getProd_id());
	 	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	 
	 	if(entrust.getJh_dt()==null||entrust.getJh_dt().equals("")){
	         if(entrust.getWt_dt()!=null&&pProd.getZq_n()!=null){
	        	
		        GregorianCalendar gc=new GregorianCalendar();
	            gc.setTime(entrust.getWt_dt());
	            gc.add(5, Integer.parseInt(pProd.getZq_n()+""));
	            entrust.setJh_dt(sdf.format(gc.getTime()));
	         }
	 	 }
        
	     //常规价、复检价
         if(entrust.getCg_f()!=null){
    		 if(entrust.getCg_f().equals("Y")){
    				entrust.setPrice(pProd.getCgj());
    				//entrust.setSt_lv("已分配");
    			 }
    			 else if(entrust.getCg_f().equals("N")){
    				entrust.setPrice(pProd.getFjj());
    				//entrust.setSt_lv("已分配");
    			 }
         }

		 
		 
		 if(entrust.getSt_lv()!=null){
				Test test = new Test();
				test.setPid(entrust.getId()+"");
				if(entrust.getSt_lv().equals("已完成")){
					test.setStatus("已完成");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("检验中")){
					test.setStatus("待审核");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已发放")){
					test.setStatus("已发放");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已存档")){
					test.setStatus("已存档");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("审批中")){
					test.setStatus("审批中");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("需调整")){
					test.setStatus("需调整");
					iTestService.updatetestbypid(test);
				}
				else if(entrust.getSt_lv().equals("已分配")){
					test.setStatus("新建");
					iTestService.updatetestbypid(test);
				}
				else{
					//do nothing
				}
			}
			

		 Entrust  tempEntrust = iEntrustMapper.selectentrustById(entrust.getId()+"");
		 if(tempEntrust.getFlg()!=null&&tempEntrust.getFlg().equals("Y")&&entrust.getFlg().equals("N")){
			 entrust.setCode(createEntrustAppend(tempEntrust.getPid(),sdf.format(tempEntrust.getWt_dt()),
					 tempEntrust.getBu_id(),tempEntrust.getC_id()));
		 }
		 result = iEntrustMapper.updateentrust(entrust);
		 if(result>0){
			 
			 if(entrust.getSyr_id()!=null){
				Test test = new Test();
				test.setPid(entrust.getId()+"");
				test.setSy_id(entrust.getSyr_id());
				iTestService.updatetestbypid(test);
			 }
			 if(entrust.getSjwc_dt()!=null){
				 Test test = new Test();
				 test.setPid(entrust.getId()+"");
				 test.setE_dt(sdf.format(entrust.getSjwc_dt()));
				 iTestService.updatetestbypid(test);
			 }
			 
			 if(entrust.getSt_lv()!=null&&entrust.getSt_lv().equals("已取消")){
				//修改样品
				 
				Map paramMap = new HashMap ();
				paramMap.put("pid", entrust.getId()+"");
				 
				//paramMap.put("up_dtFrom", sdf.parse(updatetime));
				int esnum = iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",esnum);
				List<Entrust_sample> esList= iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
				
				for(Entrust_sample es:esList){
					if(es.getSample_id()!=null){
						//Sample tempSample= iSampleService.selectsampleById(es.getSample_id());
						Sample tempSample = new Sample();
						tempSample.setId(Long.parseLong(es.getSample_id()));
						tempSample.setJd_lv("已取消");
						iSampleService.updatesample(tempSample);
						
					}
					 
				}
				
				Test test = new Test();
				test.setPid(entrust.getId()+"");
				test.setStatus("已取消");
				iTestService.updatetestbypid(test);
			 
			 }
			 /*Test test = new Test();
			 test.setPid(entrust.getId()+"");
			 if(entrust.getSt_lv().equals("已完成")){
				 test.setStatus("已完成");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("检验中")){
				 test.setStatus("待审核");
				 iTestService.updatetestbypid(test);
			 }
			 else{
				 //do nothing
			 }*/
			 entrust=iEntrustMapper.selectentrustById(entrust.getId()+"");
			 if(p_status!=null&&p_status.equals("N")){
				 //先删除 所有委托单相关信息然后新建
				 //删除
				 deleteFunction(entrust);
				 //新建
				 addFlowFunction(entrust, entrust_samples, entrust_pins);
			 }
			 else if(p_status!=null&&p_status.equals("Y")){
				 if(entrust.getFlg()!=null&&entrust.getFlg().equals("N")){
					 //修改样品
					 Map paramMap = new HashMap ();
					paramMap.put("pid", entrust.getId()+"");
					 
					//paramMap.put("up_dtFrom", sdf.parse(updatetime));
					int esnum = iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",esnum);
					List<Entrust_sample> esList= iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
					
					for(Entrust_sample es:esList){
						if(es.getSample_id()!=null){
							//Sample tempSample= iSampleService.selectsampleById(es.getSample_id());
							Sample tempSample = new Sample();
							tempSample.setId(Long.parseLong(es.getSample_id()));
							tempSample.setTy_lv("追加样品");
							iSampleService.updatesample(tempSample);
							
						}
						 
					}
				 
				 }
				
				
				 //删除
				 //deleteFunction(entrust);
				//新建
				 //addFlowFunction(entrust, entrust_samples, entrust_pins);
			 }
			 else{
				 //do nothing
			 }
			 
			 
			iEntrust_assetMapper.deleteentrust_assetbyentrust(entrust.getId()+"");
			if(entrust_assets!=null&&!entrust_assets.equals("")){
				 JSONArray entrust_assetJA=JSONArray.fromObject(entrust_assets);
				 for(int i=0;i<entrust_assetJA.size();i++){
						JSONObject  addJO = (JSONObject) entrust_assetJA.get(i);
						Entrust_asset ps=new Entrust_asset();
						if(addJO.containsKey("ass_id"))
							ps.setAss_id(addJO.getString("ass_id"));
						if(addJO.containsKey("flag"))
							ps.setFlag(addJO.getString("flag"));
						
						ps.setC_id(entrust.getC_id());
						ps.setAgree_id(entrust.getId()+"");
						iEntrust_assetMapper.addentrust_asset(ps); 
				 }
				 
			 }
			 
		 }
		 return result;
	 }
	 /**
	  * 委托方更新 流程
	  * @return 
	  */ 
	 @Transactional
	 public  int updateWTEntrustFlow(Entrust entrust,String p_status, String entrust_samples,String entrust_pins,String entrust_assets){
		 int result=0;
		 //st_lv--已完成   test  --STATUS  已完成
		 //st_lv--检验中   test  --STATUS  待审核
		 
		 Prod pProd = iProdService.selectprodById(entrust.getProd_id());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 if(entrust.getJh_dt()==null||entrust.getJh_dt().equals("")){
			 if(entrust.getWt_dt()!=null&&pProd.getZq_n()!=null){
				 
				 GregorianCalendar gc=new GregorianCalendar();
				 gc.setTime(entrust.getWt_dt());
				 gc.add(5, Integer.parseInt(pProd.getZq_n()+""));
				 entrust.setJh_dt(sdf.format(gc.getTime()));
			 }
		 }
		 
		 //常规价、复检价
		 if(entrust.getCg_f()!=null){
			 if(entrust.getCg_f().equals("Y")){
				 entrust.setPrice(pProd.getCgj());
				 //entrust.setSt_lv("已分配");
			 }
			 else if(entrust.getCg_f().equals("N")){
				 entrust.setPrice(pProd.getFjj());
				 //entrust.setSt_lv("已分配");
			 }
		 }
		 
		 
		 
		 if(entrust.getSt_lv()!=null){
			 Test test = new Test();
			 test.setPid(entrust.getId()+"");
			 if(entrust.getSt_lv().equals("已完成")){
				 test.setStatus("已完成");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("检验中")){
				 test.setStatus("待审核");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("已发放")){
				 test.setStatus("已发放");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("已存档")){
				 test.setStatus("已存档");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("审批中")){
				 test.setStatus("审批中");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("需调整")){
				 test.setStatus("需调整");
				 iTestService.updatetestbypid(test);
			 }
			 else if(entrust.getSt_lv().equals("已分配")){
				 test.setStatus("新建");
				 iTestService.updatetestbypid(test);
			 }
			 else{
				 //do nothing
			 }
		 }
		 
		 
		 Entrust  tempEntrust = iEntrustMapper.selectentrustById(entrust.getId()+"");
		 if(tempEntrust.getFlg()!=null&&tempEntrust.getFlg().equals("Y")&&entrust.getFlg().equals("N")){
			 entrust.setCode(createEntrustAppend(tempEntrust.getPid(),sdf.format(tempEntrust.getWt_dt()),
					 tempEntrust.getBu_id(),tempEntrust.getC_id()));
		 }
		 result = iEntrustMapper.updateentrust(entrust);
		 if(result>0){
			  
			
			 entrust=iEntrustMapper.selectentrustById(entrust.getId()+"");
			 if(p_status!=null&&p_status.equals("N")){
				 //先删除 所有委托单相关信息然后新建
				 //删除
				 deleteFunction(entrust);
				 //新建 
				 addESEPFunction(entrust, entrust_samples, entrust_pins);
				  
			 }
			 else if(p_status!=null&&p_status.equals("Y")){
				 
				 //删除
				 //deleteFunction(entrust);
				 //新建
				 //addFlowFunction(entrust, entrust_samples, entrust_pins);
			 }
			 else{
				 //do nothing
			 }
			 
			 
			 iEntrust_assetMapper.deleteentrust_assetbyentrust(entrust.getId()+"");
			 if(entrust_assets!=null&&!entrust_assets.equals("")){
				 JSONArray entrust_assetJA=JSONArray.fromObject(entrust_assets);
				 for(int i=0;i<entrust_assetJA.size();i++){
					 JSONObject  addJO = (JSONObject) entrust_assetJA.get(i);
					 Entrust_asset ps=new Entrust_asset();
					 if(addJO.containsKey("ass_id"))
						 ps.setAss_id(addJO.getString("ass_id"));
					 if(addJO.containsKey("flag"))
						 ps.setFlag(addJO.getString("flag"));
					 
					 ps.setC_id(entrust.getC_id());
					 ps.setAgree_id(entrust.getId()+"");
					 iEntrust_assetMapper.addentrust_asset(ps); 
				 }
				 
			 }
			 
		 }
		 return result;
	 }

	 
	 
	 /**
	  * 删除相关
	  * @return
	  */ 
	 public  void deleteFunction(Entrust entrust){
			/*@Autowired
			private IResultsMapper iResultsMapper;
			@Autowired
			private ITestMapper iTestMapper;
			@Autowired
			private IEntrust_sampleMapper iEntrust_sampleMapper;
			@Autowired
			private IEntrust_pinMapper iEntrust_pinMapper;*/
		 //删除  Entrust_sample
		 iEntrust_sampleMapper.deleteEntrust_sampleByPid(entrust.getId()+"");
		
		 //删除  entrust_pin
		 iEntrust_pinMapper.deleteEntrust_pinByPid(entrust.getId()+"");
		 //删除  Results
		 Map paramMap = new HashMap();
		 paramMap.put("pid", entrust.getId());
		 int num = iTestMapper.selectCounttestByParam(paramMap);
		 paramMap.put("fromPage",0);
		 paramMap.put("toPage",num);
		 List<Test> tempList= iTestMapper.selecttestByParam(paramMap);
		 for(Test temp:tempList){
			 iResultsMapper.deleteResultsByPid(temp.getId()+"");
		 }
		 //删除  Test
		 iTestMapper.deleteTestByPid(entrust.getId()+"");
	 }
	/**
 * 添加 
 * @return
 */ 
	public  Object addentrust(Entrust entrust){
		return iEntrustMapper.addentrust(entrust);
	}
	/**
	 * 添加 流程
	 * @return
	 */ 
	@Transactional
	public  int  addentrustFlow(Entrust entrust,String entrust_samples,String entrust_pins,String entrust_assets){
		
		 // 新增 Entrust_sample 关系
		 //   修改  样品表jd_lv 修改成  已委托  
		 //新建  test  
		 //新建 results
		 // 新建 e_pin
		int result=0;
		Prod pProd = iProdService.selectprodById(entrust.getProd_id());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(entrust.getJh_dt()==null||entrust.getJh_dt().equals("")){
	        if(entrust.getWt_dt()!=null&&pProd.getZq_n()!=null){
	        	
		        GregorianCalendar gc=new GregorianCalendar();
	            gc.setTime(entrust.getWt_dt());
	            gc.add(5, Integer.parseInt(pProd.getZq_n()+""));
	            entrust.setJh_dt(sdf.format(gc.getTime()));
	        }
		}
        
	    //常规价、复检价
		if(entrust.getCg_f().equals("Y")){
			entrust.setPrice(pProd.getCgj());
		}
		else if(entrust.getCg_f().equals("N")){
			entrust.setPrice(pProd.getFjj());
		}
		
        /////////////////
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
			entrust=iEntrustMapper.selectentrustById(entrust.getId()+"");
			addFlowFunction(entrust, entrust_samples, entrust_pins);
			
			
			if(entrust_assets!=null&&!entrust_assets.equals("")){
				 JSONArray entrust_assetJA=JSONArray.fromObject(entrust_assets);
				 for(int i=0;i<entrust_assetJA.size();i++){
						JSONObject  addJO = (JSONObject) entrust_assetJA.get(i);
						Entrust_asset ps=new Entrust_asset();
						if(addJO.containsKey("ass_id"))
							ps.setAss_id(addJO.getString("ass_id"));
						if(addJO.containsKey("flag"))
							ps.setFlag(addJO.getString("flag"));
						
						ps.setC_id(entrust.getC_id());
						ps.setAgree_id(entrust.getId()+"");
						iEntrust_assetMapper.addentrust_asset(ps); 
				 }
				 
			 }
			
		}
		 
		
		return result;
	}

	
	/**
	 * 委托方添加 流程
	 * @return
	 */ 
	@Transactional
	public  int  addWTEntrustFlow(Entrust entrust,String entrust_samples,String entrust_pins,String entrust_assets){
 
		int result=0;
		Prod pProd = iProdService.selectprodById(entrust.getProd_id());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(entrust.getJh_dt()==null||entrust.getJh_dt().equals("")){
			if(entrust.getWt_dt()!=null&&pProd.getZq_n()!=null){
				
				GregorianCalendar gc=new GregorianCalendar();
				gc.setTime(entrust.getWt_dt());
				gc.add(5, Integer.parseInt(pProd.getZq_n()+""));
				entrust.setJh_dt(sdf.format(gc.getTime()));
			}
		}
		
		//常规价、复检价
		if(entrust.getCg_f().equals("Y")){
			entrust.setPrice(pProd.getCgj());
		}
		else if(entrust.getCg_f().equals("N")){
			entrust.setPrice(pProd.getFjj());
		}
		
		/////////////////
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
			entrust=iEntrustMapper.selectentrustById(entrust.getId()+"");
			//不建test result
			addESEPFunction(entrust, entrust_samples, entrust_pins);
			
			
			if(entrust_assets!=null&&!entrust_assets.equals("")){
				JSONArray entrust_assetJA=JSONArray.fromObject(entrust_assets);
				for(int i=0;i<entrust_assetJA.size();i++){
					JSONObject  addJO = (JSONObject) entrust_assetJA.get(i);
					Entrust_asset ps=new Entrust_asset();
					if(addJO.containsKey("ass_id"))
						ps.setAss_id(addJO.getString("ass_id"));
					if(addJO.containsKey("flag"))
						ps.setFlag(addJO.getString("flag"));
					
					ps.setC_id(entrust.getC_id());
					ps.setAgree_id(entrust.getId()+"");
					iEntrust_assetMapper.addentrust_asset(ps); 
				}
				
			}
			
		}
		
		
		return result;
	}
	
	
	
	
	/**
	 * 审核委托单之后 新建Entrust_sample Test Results Entrust_pin   st_lv entrust_ids  
	 * @return 
	 */ 
	public  void  addRemoteFlowFunction(Entrust entrust,List<Entrust_sample> entrust_samples,List<Entrust_pin> entrust_pins){
		
		int result =0;
		
		//默认实验人,审批人
		Prod prod = iProdService.selectprodById(entrust.getProd_id()); 
		entrust.setSyr_id(prod.getSy_id());
		//entrust.setSp_id(prod.getSp_id()+"");
		
		result=iEntrustMapper.updateentrust(entrust);
		if(result>0){
			entrust=iEntrustMapper.selectentrustById(entrust.getId()+"");
			List<String> testidList= new ArrayList<String>();
			//样品sampless
			for(int index=0;index<entrust_samples.size();index++){
				Entrust_sample entrust_sample= entrust_samples.get(index);
				//修改样品
				Sample sample = new Sample();
				 
				sample.setId(Long.parseLong(entrust_sample.getSample_id()));
				sample.setJd_lv("已委托");
				sample.setShow_flg("Y");
				iSampleService.updatesample(sample);
			 
				//试验单 test start
				sample = iSampleService.selectsampleById(entrust_sample.getSample_id());
				 
				Test test = new Test();
				test.setBu_id(entrust.getBu_id());
				test.setC_id(entrust.getC_id());
				test.setPid(entrust.getId()+"");
				/*if(entrust.getSt_lv().equals("已完成")){
					test.setStatus("已完成");
				}
				else if(entrust.getSt_lv().equals("检验中")){
					test.setStatus("待审核");
				}*/
				
				if(entrust.getFlg().equals("Y")){
					test.setCode(createTestCode(sample,entrust));
				}
				else{
					//追加
					test.setCode(createTestAppend(sample,entrust));
				}
				test.setSample_id(sample.getId()+"");
				test.setStatus("新建");
				test.setSy_id(entrust.getSyr_id());
				test.setSh_id(prod.getSh_id()+"");
				
				int testresult = Integer.parseInt(iTestService.addtest(test).toString());
				if(testresult>0){
					String testqrResult = MatrixToImageWriter.createQrImage("EX_"+test.getId());
					if(testqrResult.length()>0){
						HttpServletRequest request = ServletActionContext.getRequest();
						String path = request.getScheme() + "://"
								+ request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath();
						Test uptest = new Test();
						uptest.setId(test.getId());
						uptest.setEwm(path+"/QRImages/"+testqrResult);
						iTestService.updatetest(uptest);
						
					}
					testidList.add(test.getId()+"");
				}
				
			}
			
			
			 
			for(int index=0;index<entrust_pins.size();index++){
				Entrust_pin entrust_pin =entrust_pins.get(index);
				 
				//结果表results start
				
				Map paramMap = new HashMap ();
				/*paramMap.put("id", entrust_pin.getJcx_id());
				paramMap.put("ty_lv", "检测项目");
				//paramMap.put("up_dtFrom", sdf.parse(updatetime));
				int prodnum = iProdService.selectCountprodByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",prodnum);
				List<Prod> prodList=iProdService.selectprodByParam(paramMap); */
				Prod temp =iProdService.selectprodById(entrust_pin.getJcx_id());
				//for(Prod temp : prodList){
					
					for(String tempStr :testidList){
						Results presults=new Results();
						int resultnum = 0;
						
						if(entrust_pin.getJc_f()!=null&&entrust_pin.getJc_f().equals("Y")){
							//result
							presults=new Results();
							presults.setPid(Long.parseLong(tempStr));
							presults.setBu_id(Long.parseLong(entrust.getBu_id()));
							presults.setC_id(Long.parseLong(entrust.getC_id()));
							presults.setProd_id(temp.getId());
							presults.setProd_name(temp.getNm_t());
							
							//子检测项目
							paramMap = new HashMap ();
							paramMap.put("pid", entrust_pin.getJcx_id());
							paramMap.put("ty_lv", "检验子项目");
							//paramMap.put("up_dtFrom", sdf.parse(updatetime));
							int prodnumson = iProdService.selectCountprodByParam(paramMap);
							paramMap.put("fromPage",0);
							paramMap.put("toPage",prodnumson);
							List<Prod> prodsonList=iProdService.selectprodByParam(paramMap); 
							if(prodsonList.size()>0){
								Prod tempProd = prodsonList.get(prodsonList.size()-1);
								if(tempProd.getGzty_lv()!=null)
									presults.setStatand_lv(tempProd.getGzty_lv());
								if(tempProd.getRule_lv()!=null)
									presults.setStatand(tempProd.getRule_lv());
								if(tempProd.getBz_t()!=null)
									presults.setStatand_va(tempProd.getBz_t());
								presults.setStatand_id(tempProd.getId());
							}
							  
							resultnum = Integer.parseInt(iResultsService.addresults(presults).toString());
							 
						}
						
						
						if(resultnum>0){
							paramMap = new HashMap ();
							paramMap.put("pid", temp.getId());
							paramMap.put("ty_lv", "检验属性");
							//paramMap.put("up_dtFrom", sdf.parse(updatetime));
							int subprodnum = iProdService.selectCountprodByParam(paramMap);
							paramMap.put("fromPage",0);
							paramMap.put("toPage",subprodnum);
							List<Prod> subprodList=iProdService.selectprodByParam(paramMap); 
							for(Prod subtemp : subprodList){
								Results subresults=new Results();
								subresults.setPid(Long.parseLong(tempStr));
								subresults.setBu_id(Long.parseLong(entrust.getBu_id()));
								subresults.setC_id(Long.parseLong(entrust.getC_id()));
								subresults.setProd_id(subtemp.getId());
								subresults.setProd_name(subtemp.getNm_t());
								subresults.setRel_id(presults.getId());
								subresults.setFlg(subtemp.getJy_f());
								iResultsService.addresults(subresults);
							}
						}
					}
				//}
				//结果表results start
			}
			
		}
	}
	
	
	
 
	/**
	 * 新建Entrust_sample Test Results Entrust_pin
	 * @return 
	 */ 
	public  void  addFlowFunction(Entrust entrust,String entrust_samples,String entrust_pins){
		List<String> testidList= new ArrayList<String>();
		//样品sampless
		JSONArray sampleJA = JSONArray.fromObject(entrust_samples);
		for(int index=0;index<sampleJA.size();index++){
			JSONObject sampleJO = (JSONObject) sampleJA.get(index);
			if(sampleJO.containsKey("id")){
				//
				Entrust_sample entrust_sample=iEntrust_sampleService.selectentrust_sampleById(sampleJO.getString("id"));
				if(entrust_sample!=null){
					Sample sample = new Sample();
					sample.setId(Long.parseLong(entrust_sample.getSample_id()));
					sample.setJd_lv("已接收");
				}
			}
			else{
				Entrust_sample entrust_sample= new Entrust_sample();
				entrust_sample.setC_id(entrust.getC_id());
				entrust_sample.setPid(entrust.getId()+"");
				if(sampleJO.containsKey("sample_id"))
				entrust_sample.setSample_id(sampleJO.getString("sample_id"));
				iEntrust_sampleService.addentrust_sample(entrust_sample);	
				 
				//修改样品
				Sample sample = new Sample();
				sample.setId(Long.parseLong(sampleJO.getString("sample_id")));
				sample.setJd_lv("已委托");
				 if(entrust.getFlg()!=null&&entrust.getFlg().equals("N")){
					 sample.setTy_lv("追加样品");
				 }
				iSampleService.updatesample(sample);
				//试验单 test start
				sample = iSampleService.selectsampleById(sampleJO.getString("sample_id"));
				Test test = new Test();
				test.setBu_id(entrust.getBu_id());
				test.setC_id(entrust.getC_id());
				test.setPid(entrust.getId()+"");
				if(entrust.getSt_lv().equals("已完成")){
					 test.setStatus("已完成");
				}
				else if(entrust.getSt_lv().equals("检验中")){
					 test.setStatus("待审核");
				}
				
				if(entrust.getFlg().equals("Y")){
					test.setCode(createTestCode(sample,entrust));
				}
				else{
					//追加
					test.setCode(createTestAppend(sample,entrust));
				}
				test.setSample_id(sample.getId()+"");
				test.setStatus("新建");
				test.setSy_id(entrust.getSyr_id());
				int testresult = Integer.parseInt(iTestService.addtest(test).toString());
				if(testresult>0){
					String testqrResult = MatrixToImageWriter.createQrImage("EX_"+test.getId());
					if(testqrResult.length()>0){
						HttpServletRequest request = ServletActionContext.getRequest();
						String path = request.getScheme() + "://"
								+ request.getServerName() + ":" + request.getServerPort()
								+ request.getContextPath();
						Test uptest = new Test();
						uptest.setId(test.getId());
						uptest.setEwm(path+"/QRImages/"+testqrResult);
						iTestService.updatetest(uptest);
						
					}
					testidList.add(test.getId()+"");
				}
				//试验单 test  end
			}
			
		}
		
		
		JSONArray epinJA = JSONArray.fromObject(entrust_pins);
		for(int index=0;index<epinJA.size();index++){
			JSONObject epinJO = (JSONObject) epinJA.get(index);
			Entrust_pin entrust_pin =new Entrust_pin();
			entrust_pin.setC_id(entrust.getC_id());
			if(epinJO.containsKey("jc_f"))
			entrust_pin.setJc_f(epinJO.getString("jc_f"));
			if(epinJO.containsKey("jcx_id"))
			entrust_pin.setJcx_id(epinJO.getString("jcx_id"));
			entrust_pin.setPid(entrust.getId()+"");
			iEntrust_pinService.addentrust_pin(entrust_pin);
			
			
			//结果表results start
			 
			
			Map paramMap = new HashMap ();
			/*paramMap.put("pid", epinJO.getString("jcx_id"));
			paramMap.put("ty_lv", "检测项目");
			//paramMap.put("up_dtFrom", sdf.parse(updatetime));
			int prodnum = iProdService.selectCountprodByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",prodnum);
			List<Prod> prodList=iProdService.selectprodByParam(paramMap); */
			Prod temp =iProdService.selectprodById(epinJO.getString("jcx_id"));
			//for(Prod temp : prodList){
				
				for(String tempStr :testidList){
					
					int resultnum = 0;
					Results presults=new Results();
					//if(entrust.getCg_f()!=null&&entrust.getCg_f().equals("Y")){
					if(entrust_pin.getJc_f()!=null&&entrust_pin.getJc_f().equals("Y")){
					 
						//result
						presults=new Results();
						presults.setPid(Long.parseLong(tempStr));
						presults.setBu_id(Long.parseLong(entrust.getBu_id()));
						presults.setC_id(Long.parseLong(entrust.getC_id()));
						presults.setProd_id(temp.getId());
						presults.setProd_name(temp.getNm_t());
						//子检测项目
						paramMap = new HashMap ();
						paramMap.put("pid", entrust_pin.getJcx_id());
						paramMap.put("ty_lv", "检验子项目");
						//paramMap.put("up_dtFrom", sdf.parse(updatetime));
						int prodnumson = iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",prodnumson);
						List<Prod> prodsonList=iProdService.selectprodByParam(paramMap); 
						if(prodsonList.size()>0){
							Prod tempProd = prodsonList.get(prodsonList.size()-1);
							if(tempProd.getGzty_lv()!=null)
								presults.setStatand_lv(tempProd.getGzty_lv());
							if(tempProd.getRule_lv()!=null)
								presults.setStatand(tempProd.getRule_lv());
							if(tempProd.getBz_t()!=null)
								presults.setStatand_va(tempProd.getBz_t());
							presults.setStatand_id(tempProd.getId());
						}
						resultnum = Integer.parseInt(iResultsService.addresults(presults).toString());
					}
					 
					if(resultnum>0){
						paramMap = new HashMap ();
						paramMap.put("pid", temp.getId());
						paramMap.put("ty_lv", "检验属性");
						//paramMap.put("up_dtFrom", sdf.parse(updatetime));
						int subprodnum = iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",subprodnum);
						List<Prod> subprodList=iProdService.selectprodByParam(paramMap); 
						for(Prod subtemp : subprodList){
							Results subresults=new Results();
							subresults.setPid(Long.parseLong(tempStr));
							subresults.setBu_id(Long.parseLong(entrust.getBu_id()));
							subresults.setC_id(Long.parseLong(entrust.getC_id()));
							subresults.setProd_id(subtemp.getId());
							subresults.setProd_name(subtemp.getNm_t());
							subresults.setRel_id(presults.getId());
							subresults.setFlg(subtemp.getJy_f());
							iResultsService.addresults(subresults);
							
						}
					}
					
				}
			 
				
			//}
			//结果表results start
		
		
		}
	}
	/**
	 * 新建Entrust_sample   Entrust_pin
	 * @return 
	 */ 
	public  void  addESEPFunction(Entrust entrust,String entrust_samples,String entrust_pins){
		//样品sampless
		JSONArray sampleJA = JSONArray.fromObject(entrust_samples);
		for(int index=0;index<sampleJA.size();index++){
			JSONObject sampleJO = (JSONObject) sampleJA.get(index);
		 
			Entrust_sample entrust_sample= new Entrust_sample();
			entrust_sample.setC_id(entrust.getC_id());
			entrust_sample.setPid(entrust.getId()+"");
			if(sampleJO.containsKey("sample_id"))
				entrust_sample.setSample_id(sampleJO.getString("sample_id"));
			iEntrust_sampleService.addentrust_sample(entrust_sample);	
		     //试验单 test  end
		}
		
		
		JSONArray epinJA = JSONArray.fromObject(entrust_pins);
		for(int index=0;index<epinJA.size();index++){
			JSONObject epinJO = (JSONObject) epinJA.get(index);
			Entrust_pin entrust_pin =new Entrust_pin();
			entrust_pin.setC_id(entrust.getC_id());
			if(epinJO.containsKey("jc_f"))
				entrust_pin.setJc_f(epinJO.getString("jc_f"));
			if(epinJO.containsKey("jcx_id"))
				entrust_pin.setJcx_id(epinJO.getString("jcx_id"));
			entrust_pin.setPid(entrust.getId()+"");
			iEntrust_pinService.addentrust_pin(entrust_pin);
		}
	}
	/**
 * 删除 
 * @return 
 */ 
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
					sampleId=createSample(sample,pactId,entrust);
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
		        Prod pProd = iProdService.selectprodById(key);
		         
		        //新增默认值
		        Bu bum = iBuService.selectbuById(entrust.getBu_id()+"");
		        Pact pactm = iPactService.selectpactById(entrust.getPid());
		        if(bum!=null){
		        	entrust.setSp_id(bum.getApprove());
		        }
		        if(pactm!=null){
		        	entrust.setJl_id(pactm.getJl_id());
		        	Accnt accntm =iAccntService.selectaccntById(pactm.getPid());
		        	if(accntm!=null){
		        		entrust.setLq_id(accntm.getCont_id());
		        	}
		        }
		        if(pProd!=null){
		        	entrust.setSyr_id(pProd.getSy_id());
		        }
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        //
		        if(entrust.getJh_dt()==null||entrust.getJh_dt().equals("")){
			        if(entrust.getWt_dt()!=null&&pProd.getZq_n()!=null){
			        	
				        GregorianCalendar gc=new GregorianCalendar();
			            gc.setTime(entrust.getWt_dt());
			            gc.add(5, Integer.parseInt(pProd.getZq_n()+""));
			            entrust.setJh_dt(sdf.format(gc.getTime()));
			        }
		        }
		        JSONArray sampleJA =sampleTree.getJSONArray(key);
		        //编号
		        //String pid,String wt_dt,String bu_id,String c_id
		        String wt_dt_str="";
		        if(entrust.getWt_dt()!=null){
		        	wt_dt_str=sdf.format(entrust.getWt_dt());
		        }
		        if(entrust.getFlg().equals("Y")){
		        	
		        	entrust.setCode(createEntrustAppend(entrust.getPid(),wt_dt_str,
							entrust.getBu_id(),entrust.getC_id()));
			    	
				}
				else{
					entrust.setCode(createEntrustCode(entrust.getPid(),wt_dt_str,
							entrust.getBu_id(),entrust.getC_id()));
				}
		        //流水号
			    entrust.setLs_n(createEntrustLs_t(entrust.getBu_id()));
				//检测项目id
				entrust.setProd_id(key);
				//常规价、复检价
				if(entrust.getCg_f().equals("Y")){
					entrust.setPrice(pProd.getCgj());
				}
				else if(entrust.getCg_f().equals("N")){
					entrust.setPrice(pProd.getFjj());
				}
				//实验人id
				entrust.setSyr_id(pProd.getSy_id());
				entrust.setSp_id(pProd.getSp_id()+"");
				
				//试验次数
				int totleNum=0;
				for(int i=0;i<sampleJA.size();i++){
					JSONObject  sampleJO = (JSONObject) sampleJA.get(i);
					if(sampleJO.containsKey("entrustnum")){
						String entrustnum =sampleJO.getString("entrustnum");
						if(entrustnum!=null&&!entrustnum.equals("")&& StringUtils.isNumeric(entrustnum)){
							int ennum= Integer.parseInt(entrustnum);
							totleNum+=ennum;
						}
					}
				}
				entrust.setCs_n(Long.parseLong(totleNum+""));
				
				
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
						sampleId=createSample(sampleJA.toString(),entrust.getPid(),entrust);
					}
					
					//样品委托单中间表  entrust_sample
					for(int i=0;i<sampleId.size();i++){
						Entrust_sample entrust_sample= new Entrust_sample();
						entrust_sample.setC_id(entrust.getC_id());
						entrust_sample.setPid(entrust.getId()+"");
						entrust_sample.setSample_id(sampleId.get(i));
						iEntrust_sampleService.addentrust_sample(entrust_sample);		 
					 }
				 
					
					//
					Map paramMap = new HashMap();
					paramMap.put("prod_id", key); 
					// paramMap.put("up_dtFrom", sdf.parse(updatetime));
					int prodnum = iProd_assetMapper.selectCountprod_assetByParam(paramMap);
					paramMap.put("fromPage", 0);
					paramMap.put("toPage", prodnum);
					List<Prod_asset> tempList=iProd_assetMapper.selectprod_assetByParam(paramMap);
					for(Prod_asset temp :tempList){
						Entrust_asset entrust_asset = new Entrust_asset();
						entrust_asset.setAgree_id(entrust.getId()+"");
						entrust_asset.setAss_id(temp.getAsset_id()+"");
						entrust_asset.setC_id(entrust.getC_id());
						entrust_asset.setFlag(temp.getFlag());
						iEntrust_assetMapper.addentrust_asset(entrust_asset);
					}
					
				}
				
				//
				
				
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
					pact.setFf_dt(sdf.parse(pactJO.getString("ff_dt")));
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<String> createSample(String jsonStr, String pactId,Entrust entrust) {
		List<String> sampleIds= new ArrayList<String>();
		try {
			//样品
			JSONArray sampleJA=JSONArray.fromObject(jsonStr);
			if(sampleJA!=null){
				
				List<String> gg_code=new ArrayList<String>();
				List<String> sy_code=new ArrayList<String>();
				
				for(int i=0;i<sampleJA.size();i++){
					JSONObject  sampleJO = (JSONObject) sampleJA.get(i);
					
					//样品信息
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
					if(sampleJO.containsKey("gg_t")){
						sample.setGg_t(sampleJO.getString("gg_t"));
					}
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
					
					
					if(sampleJO.containsKey("ly_lv"))
					sample.setLy_lv(sampleJO.getString("ly_lv"));
					if(sampleJO.containsKey("nm_t"))
					sample.setNm_t(sampleJO.getString("nm_t"));
					//if(sampleJO.containsKey("part"))
					
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
					if(sampleJO.containsKey("test_dt")){
						if(sampleJO.getString("test_dt")!=null&&!sampleJO.getString("test_dt").equals(""))
						sample.setTest_dt(sampleJO.getString("test_dt"));
					}
					if(sampleJO.containsKey("txm"))
					sample.setTxm(sampleJO.getString("txm"));
					if(sampleJO.containsKey("ty_lv"))
					sample.setTy_lv(sampleJO.getString("ty_lv"));
					if(sampleJO.containsKey("wa_t"))
					sample.setWa_t(sampleJO.getString("wa_t"));
					if(sampleJO.containsKey("wj_t"))
					sample.setWj_t(sampleJO.getString("wj_t"));
					if(sampleJO.containsKey("show_flg"))
						sample.setShow_flg(sampleJO.getString("show_flg"));
					if(sampleJO.containsKey("zz_dt")){
						if(sampleJO.getString("zz_dt")!=null&&!sampleJO.getString("zz_dt").equals(""))
						sample.setZz_dt(sampleJO.getString("zz_dt"));
					}
					
					
					//存在web_id说明为新建字段 否则都要修改（这段不是我本意，无奈）
					if(sampleJO.containsKey("web_id")){
						
						//不包含委托组数字段不会新建记录
						if(sampleJO.containsKey("entrustnum")){
							String entrustnum =sampleJO.getString("entrustnum");
							if(entrustnum!=null&&!entrustnum.equals("")&& StringUtils.isNumeric(entrustnum)){
								int ennum= Integer.parseInt(entrustnum);
								for(int index=0;index<ennum;index++){
									////////////////////////////
									sample.setId(null);
									
									
									if(sample.getGg_t()!=null&&!sample.getGg_t().equals("")){
										gg_code.add(sample.getGg_t());
									}
									
									//批号
									if(sampleJO.containsKey("bu_id")&&sampleJO.containsKey("sy_dt")&&
											sampleJO.containsKey("prod_id")&&sampleJO.containsKey("c_id")){
										sample.setLot(createSampleLot(sampleJO.getString("bu_id"),sampleJO.getString("sy_dt"),
												pactId,sampleJO.getString("prod_id"),sampleJO.getString("c_id")));
									}
									//编号
									if(sampleJO.containsKey("bu_id")&&sampleJO.containsKey("prod_id")){
										String partStr=createSampleCode(sampleJO.getString("bu_id"),sampleJO.getString("prod_id")); 
										sample.setPart(partStr);
										sy_code.add(partStr);
									}
									/////////////////////////////////
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
										
									    //test e_pin and result
										addTestAndResults(entrust, sample);
									
										
									}
									sampleIds.add(sample.getId()+""); 
									
								}
							}
							else{
								
								logger.info("entrustnum不能为空且必须是数字！");
							}
							
						}
						else{
							logger.info("entrustnum为必须字段！");
						}
						 
					}
					else{
						
						//不包含委托组数字段不会新建记录
						if(sampleJO.containsKey("entrustnum")){
							String entrustnum =sampleJO.getString("entrustnum");
							if(entrustnum!=null&&!entrustnum.equals("")&& StringUtils.isNumeric(entrustnum)){
								int ennum= Integer.parseInt(entrustnum);
								for(int index=0;index<ennum;index++){
									
									
									if(sample.getGg_t()!=null&&!sample.getGg_t().equals("")){
										gg_code.add(sample.getGg_t());
									}
									
									
									//第一条修改，其他的新建
									if(index==0){
										if(sampleJO.containsKey("id")){
											sample.setId(Long.parseLong(sampleJO.getString("id")));
										}
										//批号
										if(sampleJO.containsKey("lot")){
											sample.setLot(sampleJO.getString("lot"));
										}
										//编号
										if(sampleJO.containsKey("part")){
											sample.setPart(sampleJO.getString("part"));
											sy_code.add(sampleJO.getString("part"));
										}
										sampleIds.add(sampleJO.getString("id"));
										
										iSampleService.updatesample(sample);
									}
									else{
										////////////////////////////
										sample.setId(null);
										//批号
										if(sampleJO.containsKey("bu_id")&&sampleJO.containsKey("sy_dt")&&
												sampleJO.containsKey("prod_id")&&sampleJO.containsKey("c_id")){
											sample.setLot(createSampleLot(sampleJO.getString("bu_id"),sampleJO.getString("sy_dt"),
													pactId,sampleJO.getString("prod_id"),sampleJO.getString("c_id")));
										}
										//编号
										if(sampleJO.containsKey("bu_id")&&sampleJO.containsKey("prod_id")){
											String partStr=createSampleCode(sampleJO.getString("bu_id"),sampleJO.getString("prod_id"));
											sample.setPart(partStr);
											sy_code.add(partStr);
										}
										/////////////////////////////////
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
											
											//test e_pin and result
											//addTestAndResults(entrust, sample);
										}
										sampleIds.add(sample.getId()+""); 
									}
									addTestAndResults(entrust, sample);
									
								}
							}
							else{
								
								logger.info("entrustnum不能为空且必须是数字！");
							}
							
						}
						else{
							logger.info("entrustnum为必须字段！");
						}
						
						
					}
					
				 }
				
				//更新委托单字段gg_code、sy_code
				 String gg_codeStr= StringUtils.join(gg_code," ");
				 String sy_codeStr= "";
				 for(String partStr :sy_code){
					if(sy_code.indexOf(partStr)==0){
						sy_codeStr+=partStr+"、";
						
					}
					else{
						if(partStr.length()>5){
							sy_codeStr+=partStr.substring(5,partStr.length())+"、";
						}
					}
					
				 }
				 if(sy_codeStr.length()>0){
					sy_codeStr=sy_codeStr.substring(0, sy_codeStr.length()-1);
				 }
				 entrust.setGg_code(gg_codeStr);
				 entrust.setSy_code(sy_codeStr);
				 iEntrustMapper.updateentrust(entrust);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return sampleIds;
	}
	/**
	 * 创建试验单和results
 
	 */
	private void addTestAndResults(Entrust entrust,Sample sample){
		 
		//试验单 test
		Test test = new Test();
		test.setBu_id(entrust.getBu_id());
		test.setC_id(entrust.getC_id());
		test.setPid(entrust.getId()+"");
		if(entrust.getFlg().equals("Y")){
			test.setCode(createTestCode(sample,entrust));
		}
		else{
			//追加
			test.setCode(createTestAppend(sample,entrust));
		}
		test.setSample_id(sample.getId()+"");
		test.setStatus("新建");
		Prod prod = iProdService.selectprodById(entrust.getProd_id()); 
		test.setSy_id(entrust.getSyr_id());
		test.setSh_id(prod.getSh_id()+"");
		
		int testresult = Integer.parseInt(iTestService.addtest(test).toString());
		if(testresult>0){
			String testqrResult = MatrixToImageWriter.createQrImage("EX_"+test.getId());
			if(testqrResult.length()>0){
				HttpServletRequest request = ServletActionContext.getRequest();
				String path = request.getScheme() + "://"
						+ request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				Test uptest = new Test();
				uptest.setId(test.getId());
				uptest.setEwm(path+"/QRImages/"+testqrResult);
				iTestService.updatetest(uptest);
				
			}
			
			
			//结果表results
			Prod tempProd= iProdService.selectprodById(sample.getProd_id()+"");
			if(tempProd!=null){
				
				Map paramMap = new HashMap ();
				paramMap.put("pid", tempProd.getId());
				paramMap.put("ty_lv", "检测项目");
				//paramMap.put("up_dtFrom", sdf.parse(updatetime));
				int prodnum = iProdService.selectCountprodByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",prodnum);
				List<Prod> prodList=iProdService.selectprodByParam(paramMap); 
				for(Prod temp : prodList){
					Results presults=new Results();
					int resultnum = 0;
					if(entrust.getCg_f()!=null&&entrust.getCg_f().equals("Y")){
						//
						Entrust_pin entrust_pin= new Entrust_pin();
						entrust_pin.setC_id(entrust.getC_id());
						entrust_pin.setJc_f("Y");
						entrust_pin.setJcx_id(temp.getId()+"");
						entrust_pin.setPid(entrust.getId()+"");
						iEntrust_pinService.addentrust_pin(entrust_pin);
						//result
						presults=new Results();
						presults.setPid(test.getId());
						presults.setBu_id(Long.parseLong(entrust.getBu_id()));
						presults.setC_id(Long.parseLong(entrust.getC_id()));
						presults.setProd_id(temp.getId());
						presults.setProd_name(temp.getNm_t());
						//子检测项目
						paramMap = new HashMap ();
						paramMap.put("pid", entrust_pin.getJcx_id());
						paramMap.put("ty_lv", "检验子项目");//检验子项目
						//paramMap.put("up_dtFrom", sdf.parse(updatetime));
						int prodnumson = iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",prodnumson);
						List<Prod> prodsonList=iProdService.selectprodByParam(paramMap); 
						if(prodsonList.size()>0){
							Prod tempProdson = prodsonList.get(prodsonList.size()-1);
							if(tempProdson.getGzty_lv()!=null)
								presults.setStatand_lv(tempProdson.getGzty_lv());
							if(tempProdson.getRule_lv()!=null)
								presults.setStatand(tempProdson.getRule_lv());
							if(tempProdson.getBz_t()!=null)
								presults.setStatand_va(tempProdson.getBz_t());
							presults.setStatand_id(tempProdson.getId());
						}
						resultnum = Integer.parseInt(iResultsService.addresults(presults).toString());
					}
					else{
						Entrust_pin entrust_pin= new Entrust_pin();
						entrust_pin.setC_id(entrust.getC_id());
						entrust_pin.setJc_f(temp.getFj_f());
						entrust_pin.setJcx_id(temp.getId()+"");
						entrust_pin.setPid(entrust.getId()+"");
						iEntrust_pinService.addentrust_pin(entrust_pin);
						
						if(temp.getFj_f()!=null&&temp.getFj_f().equals("Y")){
							//result
							presults=new Results();
							presults.setPid(test.getId());
							presults.setBu_id(Long.parseLong(entrust.getBu_id()));
							presults.setC_id(Long.parseLong(entrust.getC_id()));
							presults.setProd_id(temp.getId());
							presults.setProd_name(temp.getNm_t());
							//子检测项目
							paramMap = new HashMap ();
							paramMap.put("pid", entrust_pin.getJcx_id());
							paramMap.put("ty_lv", "检验子项目");//检验子项目
							//paramMap.put("up_dtFrom", sdf.parse(updatetime));
							int prodnumson = iProdService.selectCountprodByParam(paramMap);
							paramMap.put("fromPage",0);
							paramMap.put("toPage",prodnumson);
							List<Prod> prodsonList=iProdService.selectprodByParam(paramMap); 
							if(prodsonList.size()>0){
								Prod tempProdson = prodsonList.get(prodsonList.size()-1);
								if(tempProdson.getGzty_lv()!=null)
									presults.setStatand_lv(tempProdson.getGzty_lv());
								if(tempProdson.getRule_lv()!=null)
									presults.setStatand(tempProdson.getRule_lv());
								if(tempProdson.getBz_t()!=null)
									presults.setStatand_va(tempProdson.getBz_t());
								presults.setStatand_id(tempProdson.getId());
							}
							resultnum = Integer.parseInt(iResultsService.addresults(presults).toString());
						
						}
						
					}
					
					
					if(resultnum>0){
						 
						paramMap = new HashMap ();
						paramMap.put("pid", temp.getId());
						paramMap.put("ty_lv", "检验属性");
						//paramMap.put("up_dtFrom", sdf.parse(updatetime));
						int subprodnum = iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",subprodnum);
						List<Prod> subprodList=iProdService.selectprodByParam(paramMap); 
						for(Prod subtemp : subprodList){
							Results subresults=new Results();
							subresults.setPid(test.getId());
							subresults.setBu_id(Long.parseLong(entrust.getBu_id()));
							subresults.setC_id(Long.parseLong(entrust.getC_id()));
							subresults.setProd_id(subtemp.getId());
							subresults.setProd_name(subtemp.getNm_t());
							subresults.setRel_id(presults.getId());
							subresults.setFlg(subtemp.getJy_f());
							iResultsService.addresults(subresults);
							
						}
					}
				}
			}
		}
		
	}
    
	
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
		paramMap.put("order", "ORDER BY PRO_NUM DESC");
		lotList = iLotService.selectlotByParamOrder(paramMap);
		 
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
    	paramMap.put("order", "ORDER BY PRO_NUM DESC");
		lotList = iLotService.selectlotByParamOrder(paramMap);
    	
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


    /**
     * 
     * 编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String createTestCode(Sample sample,Entrust entrust){
    	//http://192.168.1.144/Check/Test?sample_id=2868&bu_id=1
		 
		StringBuffer sb = new StringBuffer();
		Bu bu= iBuService.selectbuById(entrust.getBu_id());
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("ty_lv", "组织编码");
		paramMap.put("bu_id", entrust.getBu_id());
		
		List<Lov> lovList= iLovService.selectlovByParam(paramMap);
		if(lovList.size()>0){
			sb.append(lovList.get(0).getNm_t());
		}
		
		//Sample sample= iSampleService.selectsampleById(sample_id);
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
		paramMap.put("bu_id", entrust.getBu_id());
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
			templov.setBu_id(entrust.getBu_id());
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
    public String createTestAppend(Sample sample,Entrust entrust){
    	//http://192.168.1.144/Check/Test?sample_id=2868&bu_id=1
		 
		StringBuffer sb = new StringBuffer();
		//Bu bu= iBuService.selectbuById(bu_id);
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("ty_lv", "组织编码");
		paramMap.put("bu_id", entrust.getBu_id());
		
		List<Lov> lovList= iLovService.selectlovByParam(paramMap);
		if(lovList.size()>0){
			sb.append(lovList.get(0).getNm_t());
		}
		
		//Sample sample= iSampleService.selectsampleById(sample_id);
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
    
    @Transactional
   	public int mulupdateEntrust(List<Entrust> entrustList) {
   		 return iEntrustMapper.mulupdateEntrust(entrustList);
   	}

}

