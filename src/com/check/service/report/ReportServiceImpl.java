package com.check.service.report;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.report.IReportMapper;
import com.check.dao.results.IResultsMapper;
import com.check.model.results.Results;
import com.check.model.test.Test;
public class ReportServiceImpl  implements IReportService {

	@Autowired
	private IReportMapper iReportMapper;
	@Autowired
	private IResultsMapper iResultsMapper;
	/**
	* 更新 
 	* @return 
 	*/ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
 	@Transactional
	public  int updatereport(String param){
	 Logger logger = Logger.getLogger("CheckLogger");
	 /////
	 String sql="";
	 int result=0;
	 
	 try {
		 JSONArray paramJA=JSONArray.fromObject(param);
		 if(paramJA.size()>0){
			 for(int i=0;i<paramJA.size();i++){
				 JSONObject paramJO=(JSONObject) paramJA.get(i);
				 Iterator iterator = paramJO.keys();
				 while(iterator.hasNext()){
				      String tblName=   (String) iterator.next();
				      JSONObject subJO= paramJO.getJSONObject(tblName) ;
				      if(subJO.containsKey("id")){
				    	  // UPDATE table_name SET column1=value1,column2=value2 WHERE some_column=some_value;
				    	 
				    	  if(tblName.equals("results")&&subJO.containsKey("value")){
				    		  Results results=new Results();
				    		  results.setId(Long.parseLong(subJO.getString("id")));
				    		  results.setValue(subJO.getString("value"));
				    		  Results cresults=iResultsMapper.selectresultsById(results.getId()+"");
				    		  Results presults = iResultsMapper.selectresultsById(cresults.getRel_id()+"");
				    		  //子记录
				    		  if(presults!=null){
			    				 //修改标准值
			    				 if(results.getValue()!=null&&!results.getValue().equals("")){
			    					//更新value值
			    				 }
			    				 else{
			    					if(cresults.getValue()!=null&&!cresults.getValue().equals("")){
			    						results.setValue(cresults.getValue());
			    					}
			    				 }
			    				
			    				 if(results.getValue()!=null&&!results.getValue().equals("")){
			    					String inbz_t_p =null;
			    					if(presults!=null){
			    						inbz_t_p =presults.getInbz_t();
			    					}
			    					//运算符
			    					String valueStr = results.getValue();
			    					String statand_lvStr = presults.getStatand_lv();
			    					String statandStr = presults.getStatand();
			    					String statand_vaStr = presults.getStatand_va();
			    					if(inbz_t_p!=null&&!inbz_t_p.equals("")){
			    						statand_vaStr=inbz_t_p;
			    					}
			    					
			    					if(valueStr!=null&&statand_lvStr!=null&&statandStr!=null&&statand_vaStr!=null){
			    						String bool = calculate(valueStr,statand_lvStr,
			    					    		statandStr,statand_vaStr);
			    						results.setBool(bool);
			    					}
			    				 }
			    				 else{
			    					//value无值
			    				 }
				    		   }
				    			//父记录  更新所有子记录
				    		   else{
				    				if(results.getInbz_t()!=null||results.getStatand_id()!=null){
				    					Map paramMap= new HashMap();
				    					paramMap.put("rel_id", results.getId()+"");
				    					int sum= iResultsMapper.selectCountresultsByParam(paramMap);
				    					paramMap.put("fromPage",0);
				    					paramMap.put("toPage",sum); 
				    					List<Results> subList= iResultsMapper.selectresultsByParam(paramMap);
				    					for(Results tempResults:subList){
				    						
				    						//运算符
				    						String valueStr = tempResults.getValue();
				    						String statand_lvStr = results.getStatand_lv();
				    						String statandStr = results.getStatand();
				    						String statand_vaStr = results.getStatand_va();
				    						if(results.getInbz_t()!=null&&!results.getInbz_t().equals("")){
				    							statand_vaStr=results.getInbz_t();
				    						}
				    						
				    						if(valueStr!=null&&statand_lvStr!=null&&statandStr!=null&&statand_vaStr!=null){
				    							String bool = calculate(valueStr,statand_lvStr,
				    						    		statandStr,statand_vaStr);
				    							tempResults.setBool(bool); 
				    							//
				    							iResultsMapper.updateresults(tempResults); 
				    						}
				    						//修改父记录
				    						if(tempResults.getFlg()!=null&&tempResults.getFlg().equals("Y")){
				    							results.setBool(tempResults.getBool());
				    							results.setValue(tempResults.getValue());
				    						}
				    						
				    						
				    					}
				    				}
				    				  
				    			}
				    			 /////////1
				    			result= iResultsMapper.updateresults(results);
				    			if(result>0){
				    				cresults=iResultsMapper.selectresultsById(results.getId()+"");
				    				if(presults!=null&&cresults.getFlg()!=null&&cresults.getFlg().equals("Y")){
				    					presults.setBool(cresults.getBool());
				    					presults.setValue(cresults.getValue());
				    					///////////////////2
				    					//iResultsService.updateresults(presults);
				    				}
				    				if(presults!=null){
				    					//presults.setBool("Y");
				    					///////////////////2
				    					iResultsMapper.updateresults(presults);
				    				}
				    			}
				    	  }
				    	  else{
				    		  String updateSql="UPDATE "+tblName+" SET " ;
					    	  List<String> value= new ArrayList<String>();
					    	  Iterator subIterator = subJO.keys();
							  while(subIterator.hasNext()){
								 String colName=   (String) subIterator.next();
								 if(!colName.equals("id")){
									 value.add(colName+"='"+subJO.getString(colName)+"'");
								 }
							  }
							  if(value.size()>0){
								  updateSql+= StringUtils.join(value,",");
								  String idKey=subJO.getString("id");
								  String[] ids=idKey.split(",");
								  for(String id : ids){
									  sql+=updateSql+" WHERE id='"+id+"';";
								  }
								  
							  }
							  else{
								  logger.info(tblName+":没有传递要修改的参数！");
							  }
				    		  
				    	  }
				    	  
				    	  
				      }
				      else{
				    	  logger.info(tblName+":没有传递id参数！");
				      }
				 }
				 
			 }
			 
			 if(!sql.equals("")){
				 Map paramMap = new HashMap();
				 paramMap.put("sql", sql);
				 result=iReportMapper.updatereport(paramMap);
			 }
			 else{
				 result=0;
			 }
			 
		   }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		result=-1;
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	 
	
	 return result;
	}

	private String  calculate(String valueStr,String statand_lvStr,
    		String statandStr,String statand_vaStr){
    	String  flag="N";
    	try {
    		String boolStr = null;
			//
			if(statand_lvStr.equals("其他")){
				boolStr = "("+valueStr+"=="+statand_vaStr+")";
			}
			else if(statand_lvStr.equals("数字")){
				 if(statandStr.equals("区间")){
					 String[] statand_vaStrs = statand_vaStr.split(",");
					 boolStr = "("+statand_vaStrs[0]+"<="+valueStr+"&&"+valueStr+"<="+statand_vaStrs[1]+")"; 
				 }
				 else{
					 boolStr = "("+valueStr+statandStr+statand_vaStr+")";  
				 }
			}
			else{
				//公式 
				
			}
			ScriptEngineManager manager = new ScriptEngineManager();  
			ScriptEngine engine = manager.getEngineByName("js");  
			//engine.put("a", 4);  
			Object flagOB = engine.eval(boolStr);  
			if(flagOB.toString().equals("true")){
				flag="Y";
			}
			else{
				flag="N";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag="N";
			throw new RuntimeException(e);
		}
    	
    	return flag;
    	
    }
 
}

