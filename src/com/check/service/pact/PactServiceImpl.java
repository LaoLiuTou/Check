package com.check.service.pact;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.pact.IPactMapper;
import com.check.dao.prod.IProdMapper;
import com.check.dao.results.IResultsMapper;
import com.check.dao.sample.ISampleMapper;
import com.check.dao.test.ITestMapper;
import com.check.model.atta.Atta;
import com.check.model.pact.Pact;
import com.check.model.prod.Prod;
import com.check.model.results.Results;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
import com.check.service.atta.IAttaService;
public class PactServiceImpl  implements IPactService {

	@Autowired
	private IPactMapper iPactMapper;
	@Autowired
	private IAttaService iAttaService;
	
	
	

	@Autowired
	private ISampleMapper iSampleMapper;
 
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
	public Pact selectpactById(String id){
		return iPactMapper.selectpactById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Pact> selectpactByParam(Map paramMap){ 
		return iPactMapper.selectpactByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountpactByParam(Map paramMap){ 
		return iPactMapper.selectCountpactByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatepact(Pact pact){
		return iPactMapper.updatepact(pact);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addpact(Pact pact){
		return iPactMapper.addpact(pact);
	}

	
	/**
	 * 更新 
	 * @return 
	 */ 
	@Transactional
	public  int updatepactAndAtta(Pact pact,String attastr){
		int result=0;
		result=iPactMapper.updatepact(pact);
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
					atta.setPid(pact.getId()+"");
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
	@Transactional
	public  int addPactAndAtta(Pact pact,String attastr){
		 
		int result=0;
		result=iPactMapper.addpact(pact);
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
				atta.setPid(pact.getId()+"");
				iAttaService.addatta(atta);
			}
			
		}
		 return result;
	}
	/**
 * 删除 
 * @return 
 */ 
	public  int deletepact(String id){
		return iPactMapper.deletepact(id);
	}

	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	/**
	  * tree
	  * @return 
	  */ 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public  String selectAppTreePact(String ids,String test_status){
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
				 /*paramMap.put("pid", aids[i]);
				 //paramMap.put("up_dtFrom", sdf.parse(updatetime));
				 int pactnum = iPactMapper.selectCountpactByParam(paramMap);
				 if(pactnum>0){
					paramMap.put("fromPage",0);
					paramMap.put("toPage",pactnum);
					List<Pact> pactList = iPactMapper.selectpactByParam(paramMap); */
					
					//for(Pact tempPact :pactList){
						
						//样品
						paramMap = new HashMap ();
						paramMap.put("pid", aids[i]);  
						if(test_status!=null&&!test_status.equals("")){
							paramMap.put("test_status", test_status);
							String [] ts=test_status.split("\\|");
							paramMap.put("ts1", ts[0]);
							paramMap.put("ts2", ts[1]);
						}
					
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
									//paramMap.put("jy_f", "Y");
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
							if(sampleList.indexOf(tempSample)!=(sampleList.size()-1)){
								 sb.append(",");
							 }
						}
						
						
						
						
						
					//}
					
					//sb.append(JSONArray.fromObject(pactList, jsonConfig));
					 
				 //}
				 sb.append("]");
				 if(i!=(aids.length-1)){
					 sb.append(",");
				 }
				 
				
				 
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

