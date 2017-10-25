package com.check.service.accnt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.check.dao.accnt.IAccntMapper;
import com.check.dao.pact.IPactMapper;
import com.check.dao.prod.IProdMapper;
import com.check.dao.results.IResultsMapper;
import com.check.dao.sample.ISampleMapper;
import com.check.dao.test.ITestMapper;
import com.check.model.accnt.Accnt;
import com.check.model.atta.Atta;
import com.check.model.cont.Cont;
import com.check.model.pact.Pact;
import com.check.model.prod.Prod;
import com.check.model.results.Results;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
import com.check.service.atta.IAttaService;
import com.check.service.cont.IContService;
public class AccntServiceImpl  implements IAccntService {

	@Autowired
	private IAccntMapper iAccntMapper;
	//@Autowired
	//private IAccntService iAccntService;
	@Autowired
	private IContService iContService;
	@Autowired
	private IAttaService iAttaService;
	
	
	@Autowired
	private ISampleMapper iSampleMapper;
	@Autowired
	private IPactMapper iPactMapper;
 
	@Autowired
	private IProdMapper iProdMapper;
	@Autowired
	private ITestMapper iTestMapper;
	@Autowired
	private IResultsMapper iResultsMapper;
	/**
 * 通过id选取
 * @return
 */
	public Accnt selectaccntById(String id){
		return iAccntMapper.selectaccntById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Accnt> selectaccntByParam(Map paramMap){ 
		return iAccntMapper.selectaccntByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountaccntByParam(Map paramMap){ 
		return iAccntMapper.selectCountaccntByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateaccnt(Accnt accnt){
		return iAccntMapper.updateaccnt(accnt);
	}
 /**
  * 更新 all
  * @return 
  */ 
 @SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
 public  int updateallaccnt(Accnt accnt,String contstr,String attastr){
	 int result = 0;
	 result= iAccntMapper.updateaccnt(accnt);
	 	if(contstr!=null){
			Map  paramMap = new HashMap ();
			paramMap.put("pid",accnt.getId()); 
			int count= iContService.selectCountcontByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",count); 
			List<Cont> contList= iContService.selectcontByParam(paramMap);
			
			List<Cont>  copyList = new ArrayList<Cont>();
			copyList.addAll(contList);
			JSONArray contJA=JSONArray.fromObject(contstr);
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
				if(!contJO.getString("id").equals(""))
				cont.setId(Long.parseLong(contJO.getString("id")));
				//id 为空新建 否则修改
				if(cont.getId()==null){
					iContService.addcont(cont); 
				}
				else{
					iContService.updatecont(cont,null);
				}
				
				//flag   =  Y/N
				if(contJO.getString("flag").equals("Y")){
					Accnt tempAccnt = new Accnt();
					tempAccnt.setCont_id(cont.getId()+"");
					tempAccnt.setId(accnt.getId());
					tempAccnt.setUp_dt(null);
					iAccntMapper.updateaccnt(tempAccnt);
				}
				
			}
			//没有对应数据删除
			for(Cont temp:copyList){
				temp.setPid(Long.valueOf("-1"));
				temp.setUp_dt(null);
				iContService.updatecont(temp,null);
			}
			
			
		}
		
		if(attastr!=null){
			
			JSONArray attaJA=JSONArray.fromObject(attastr);
			for(int i=0;i<attaJA.size();i++){
				JSONObject  attaJO = (JSONObject) attaJA.get(i);
				if(attaJO.containsKey("id")){
					iAttaService.deleteatta(attaJO.getString("id"));
				}
				else{
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
				
				
			}
			
		}
		
		
		
	 return result;
 }

	/**
 * 添加 
 * @return
 */ 
	public  Object addaccnt(Accnt accnt){
		return iAccntMapper.addaccnt(accnt);
	}

 
 /**
  * 添加 all
  * @return
  */ 
 @Transactional
 	public  int addallaccnt(Accnt accnt,String contstr,String attastr){
 	 	int result=0;
 	 	try {
			result=iAccntMapper.addaccnt(accnt);
			if(result>0){
				if(contstr!=null){
					
					JSONArray contJA=JSONArray.fromObject(contstr);
					for(int i=0;i<contJA.size();i++){
						JSONObject  contJO = (JSONObject) contJA.get(i);
						
						Cont cont = new Cont();
						//cont.setRow_id(contJO.getString("row_id"));
						if(accnt.getC_id()!=null)
						cont.setC_id(accnt.getC_id());
						if(contJO.containsKey("nm_t"))
						cont.setNm_t(contJO.getString("nm_t"));
						//cont.setJob(contJO.getString("job"));
						//cont.setSex(contJO.getString("sex"));
						if(contJO.containsKey("ph_p"))
						cont.setPh_p(contJO.getString("ph_p"));
						//cont.setQq(contJO.getString("qq")); 
						if(accnt.getBu_id()!=null)
						cont.setBu_id(Long.parseLong(accnt.getBu_id()));
						cont.setPid(accnt.getId());
						iContService.addcont(cont); 
						//flag   =  Y/N
						//flag   =  Y/N
						if(contJO.getString("flag").equals("Y")){
							Accnt tempAccnt = new Accnt();
							tempAccnt.setCont_id(cont.getId()+"");
							tempAccnt.setId(accnt.getId());
							tempAccnt.setUp_dt(null);
							iAccntMapper.updateaccnt(tempAccnt);
						}
						
					}
					
					
				}
				
				if(attastr!=null){
					
					JSONArray attaJA=JSONArray.fromObject(attastr);
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
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
 		return result;
 	}
  
  
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteaccnt(String id){
		return iAccntMapper.deleteaccnt(id);
	}
 /**
  * 当前时间
  * @return 
  */ 
 public  String selectDbtime(){
	 return iAccntMapper.selectDbtime();
 }
 
 
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 /**
  * tree
  * @return 
  */ 
 @SuppressWarnings({ "rawtypes", "unchecked" })
 public  String selectAppTreeAccnt(String ids,String updatetime){
	 String result="";
	 try {
		StringBuffer sb = new StringBuffer("{");
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
		 String[] aids = ids.split("\\|");
		 for(int i=0;i<aids.length;i++){
			 sb.append("\""+aids[i]+"\":[");
			 //合同
			 Map  paramMap = new HashMap ();
			 paramMap.put("pid", aids[i]);
			 //paramMap.put("up_dtFrom", sdf.parse(updatetime));
			 int pactnum = iPactMapper.selectCountpactByParam(paramMap);
			 if(pactnum>0){
				paramMap.put("fromPage",0);
				paramMap.put("toPage",pactnum);
				List<Pact> pactList = iPactMapper.selectpactByParam(paramMap); 
				
				for(Pact tempPact :pactList){
					
					//样品
					paramMap = new HashMap ();
					paramMap.put("pid", tempPact.getId());
					//paramMap.put("up_dtFrom", sdf.parse(updatetime));
					int samplenum = iSampleMapper.selectCountsampleByParam(paramMap);
					paramMap.put("fromPage",0);
					paramMap.put("toPage",samplenum);
					List<Sample> sampleList = iSampleMapper.selectsampleByParam(paramMap);
					//sb.append("{\"sample\":"+JSONArray.fromObject(sampleList, jsonConfig)+",");
					
					for(Sample tempSample :sampleList){
						
						sb.append("{\"sample\":"+JSONObject.fromObject(tempSample, jsonConfig)+",");
						//检测项目
						Prod tempProd = iProdMapper.selectprodById(tempSample.getProd_id());
						sb.append("\"prod\":"+JSONObject.fromObject(tempProd, jsonConfig)+",");
						if(tempProd==null){
							sb.append("\"sub_prod\":\"\"}");
						}
						else{
							//试验单
							Test tempTest = new Test();
							paramMap = new HashMap ();
							paramMap.put("sample_id", tempSample.getId());
							//paramMap.put("up_dtFrom", sdf.parse(updatetime)); 
							paramMap.put("fromPage",0);
							paramMap.put("toPage",1);
							List<Test> testList = iTestMapper.selecttestByParam(paramMap);
							if(testList.size()>0){
								tempTest=testList.get(0);
							}
							sb.append("\"test\":"+JSONObject.fromObject(tempTest, jsonConfig)+",");
							
							//子检测项目
							paramMap = new HashMap ();
							paramMap.put("pid", tempProd.getId());
							paramMap.put("ty_lv", "检测项目");
							//paramMap.put("up_dtFrom", sdf.parse(updatetime));
							int subprodlenum = iProdMapper.selectCountprodByParam(paramMap);
							paramMap.put("fromPage",0);
							paramMap.put("toPage",subprodlenum);
							List<Prod> subprodList = iProdMapper.selectprodByParam(paramMap);
							JSONArray prodJA = new JSONArray();
							
							for(Prod tempsubprod : subprodList){
								JSONObject resultsJO=JSONObject.fromObject(tempsubprod, jsonConfig);
								//结果表
								paramMap = new HashMap ();
								paramMap.put("prod_id", tempsubprod.getId());
								paramMap.put("pid", tempTest.getId());
								//paramMap.put("up_dtFrom", sdf.parse(updatetime));
								int resultsnum = iResultsMapper.selectCountresultsByParam(paramMap);
								paramMap.put("fromPage",0);
								paramMap.put("toPage",resultsnum);
								List<Results> resultsList = iResultsMapper.selectresultsByParam(paramMap);
								resultsJO.accumulate("results", JSONArray.fromObject(resultsList, jsonConfig));
								
								//子检测项目
								paramMap = new HashMap ();
								paramMap.put("pid", tempsubprod.getId());
								paramMap.put("ty_lv", "检验属性");
								paramMap.put("jy_f", "Y");
								//paramMap.put("up_dtFrom", sdf.parse(updatetime));
								int gsubprodlenum = iProdMapper.selectCountprodByParam(paramMap);
								paramMap.put("fromPage",0);
								paramMap.put("toPage",gsubprodlenum);
								List<Prod> gsubprodList = iProdMapper.selectprodByParam(paramMap);
								
								JSONArray subprodJA = new JSONArray();
								for(Prod tempgsubprod :gsubprodList){
									JSONObject subresultsJO=JSONObject.fromObject(tempgsubprod, jsonConfig);
									//结果表
									paramMap = new HashMap ();
									paramMap.put("prod_id", tempgsubprod.getId());
									paramMap.put("pid", tempTest.getId());
									//paramMap.put("up_dtFrom", sdf.parse(updatetime));
									int subresultsnum = iResultsMapper.selectCountresultsByParam(paramMap);
									paramMap.put("fromPage",0);
									paramMap.put("toPage",subresultsnum);
									List<Results> subresultsList = iResultsMapper.selectresultsByParam(paramMap);
									subresultsJO.accumulate("results", JSONArray.fromObject(subresultsList, jsonConfig));
									subprodJA.add(subresultsJO);
								}
								resultsJO.accumulate("sub_prod", subprodJA);
								prodJA.add(resultsJO);
							}
							sb.append("\"sub_prod\":"+prodJA+"}");
						}
						
						//sb.append(",");
						//tempPact :pactList
						if(sampleList.indexOf(tempSample)!=(sampleList.size()-1) ||
								pactList.indexOf(tempPact)!=(pactList.size()-1)){
							 sb.append(",");
						 }
					}
					
					
					
					
					
				}
				
				//sb.append(JSONArray.fromObject(pactList, jsonConfig));
				 
			 }
			 
			 if(i!=(aids.length-1)){
				 sb.append(",");
			 }
			 
			 sb.append("]");
			 
		 }
		 
		 //{"1":[{"pact1":{"sample":[],pact}},{"pact2"},{"pact3"}]}
		 
		 
		 sb.append("}");
		 result = sb.toString();
	 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 result="";
		 e.printStackTrace();
		// throw new RuntimeException(e); 
	 }
	 
	 
	 return result;
 }

}

