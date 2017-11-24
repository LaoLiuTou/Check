package com.check.service.results;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.results.IResultsMapper;
import com.check.dao.test.ITestMapper;
import com.check.model.results.Results;
import com.check.model.test.Test;
public class ResultsServiceImpl  implements IResultsService {

	@Autowired
	private IResultsMapper iResultsMapper;
	
	@Autowired
	private ITestMapper iTestMapper;
	/**
 * 通过id选取
 * @return
 */
	public Results selectresultsById(String id){
		return iResultsMapper.selectresultsById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Results> selectresultsByParam(Map paramMap){ 
		return iResultsMapper.selectresultsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountresultsByParam(Map paramMap){ 
		return iResultsMapper.selectCountresultsByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateresults(Results results){
		return iResultsMapper.updateresults(results);
	}
	/**
	 * 更新 
	 * @return 
	 */ 
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  int updateRelatedResults(Results results,String jl_t,String dd_t){
		int result=0;
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
			///////////////3
			if(jl_t!=null||dd_t!=null){
				if(cresults.getPid()!=null){
					
					Test tempTest = new Test();
					tempTest.setId(cresults.getPid());
					tempTest.setJl_t(jl_t);
					tempTest.setDd_t(dd_t);
					iTestMapper.updatetest(tempTest);
				}
			}
			
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
	/**
 * 添加 
 * @return
 */ 
	public  Object addresults(Results results){
		return iResultsMapper.addresults(results);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteresults(String id){
		return iResultsMapper.deleteresults(id);
	}

}

