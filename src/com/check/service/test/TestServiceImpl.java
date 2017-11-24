package com.check.service.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.check.dao.entrust.IEntrustMapper;
import com.check.dao.entrust_sample.IEntrust_sampleMapper;
import com.check.dao.sample.ISampleMapper;
import com.check.dao.test.ITestMapper;
import com.check.model.entrust.Entrust;
import com.check.model.entrust_sample.Entrust_sample;
import com.check.model.sample.Sample;
import com.check.model.test.Test;
public class TestServiceImpl  implements ITestService {

	@Autowired
	private ITestMapper iTestMapper;
	@Autowired
	private IEntrustMapper iEntrustMapper;
	@Autowired
	private IEntrust_sampleMapper iEntrust_sampleMapper;
	@Autowired
	private ISampleMapper iSampleMapper;
	/**
 * 通过id选取
 * @return
 */
	public Test selecttestById(String id){
		return iTestMapper.selecttestById(id);
	}
	
	 /**
	  * first code
	  * @return
	  */ 
	 @SuppressWarnings("rawtypes")
	 public List<Test> selectFirstTestCode(Map paramMap){ 
		 return iTestMapper.selectFirstTestCode(paramMap);
	 }
	 /**
	  * second code
	  * @return
	  */ 
	 @SuppressWarnings("rawtypes")
	 public List<Test> selectSecondTestCode(Map paramMap){ 
		 return iTestMapper.selectSecondTestCode(paramMap);
	 }
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Test> selecttestByParam(Map paramMap){ 
		return iTestMapper.selecttestByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCounttestByParam(Map paramMap){ 
		return iTestMapper.selectCounttestByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
	public  int updatetest(Test test){
		
		int result=0;
		
	 
		/*if(test.getStatus()!=null&&test.getStatus().equals("待审核")){
			test.setStatus("待审核");
			
			result=iTestMapper.updatetest(test);
			if(result>0){
				test=iTestMapper.selecttestById(test.getId()+"");
				Entrust entrust  = new Entrust();
				entrust.setId(Long.parseLong(test.getPid()));
				entrust.setSt_lv("检验中");
				iEntrustMapper.updateentrust(entrust);
			}
		}
		else*/
		if(test.getStatus()!=null&&test.getStatus().equals("检验中")){
			test.setStatus("检验中");
			
			result=iTestMapper.updatetest(test);
			if(result>0){
				test=iTestMapper.selecttestById(test.getId()+"");
				Entrust entrust  = new Entrust();
				entrust.setId(Long.parseLong(test.getPid()));
				entrust.setSt_lv("检验中");
				iEntrustMapper.updateentrust(entrust);
				
				
				//修改样品   stlv  已检验
				if(test.getSample_id()!=null){
					//Sample tempSample= iSampleMapper.selectsampleById(test.getSample_id());
					Sample tempSample = new Sample();
					tempSample.setId(Long.parseLong(test.getSample_id()));
					tempSample.setJd_lv("已检验");
					
					iSampleMapper.updatesample(tempSample);
				}
				
			}
		}
		else if(test.getStatus()!=null&&test.getStatus().equals("待审核")){
			test.setStatus("待审核");
			
			 
			result=iTestMapper.updatetest(test);
			if(result>0){
				test=iTestMapper.selecttestById(test.getId()+"");
				Map paramMap = new HashMap();
				paramMap.put("pid", test.getPid());
				int totalNum = iTestMapper.selectCounttestByParam(paramMap);
				//paramMap.put("status", "待审核");
				paramMap.put("sum_status", "('待审核','已取消')");
				int subNum= iTestMapper.selectCounttestByParam(paramMap);
				if(totalNum==subNum){
					Entrust entrust  = new Entrust();
					entrust.setId(Long.parseLong(test.getPid()));
					entrust.setSt_lv("检验中");
					iEntrustMapper.updateentrust(entrust);
				}
				
			}
		}
		else if(test.getStatus()!=null&&test.getStatus().equals("待审批")){
			test.setStatus("待审批");
			
			 
			result=iTestMapper.updatetest(test);
			if(result>0){
				test=iTestMapper.selecttestById(test.getId()+"");
				Map paramMap = new HashMap();
				paramMap.put("pid", test.getPid());
				int totalNum = iTestMapper.selectCounttestByParam(paramMap);
				paramMap.put("sum_status", "('待审批','已取消')");
				int subNum= iTestMapper.selectCounttestByParam(paramMap);
				if(totalNum==subNum){
					Entrust entrust  = new Entrust();
					entrust.setId(Long.parseLong(test.getPid()));
					entrust.setSt_lv("待审批");
					iEntrustMapper.updateentrust(entrust);
					 
				}
				//修改样品   stlv  已检验
				if(test.getSample_id()!=null){
					//Sample tempSample= iSampleMapper.selectsampleById(test.getSample_id());
					Sample tempSample = new Sample();
					tempSample.setId(Long.parseLong(test.getSample_id()));
					tempSample.setJd_lv("已检验");
					
					iSampleMapper.updatesample(tempSample);
				}
				
			}
		}
		else if(test.getStatus()!=null&&test.getStatus().equals("已取消")){
			result=iTestMapper.updatetest(test);
			if(result>0){
				test=iTestMapper.selecttestById(test.getId()+"");
				Map paramMap = new HashMap();
				paramMap.put("pid", test.getPid());
				int totalNum = iTestMapper.selectCounttestByParam(paramMap);
				paramMap.put("status", "已取消");
				int subNum= iTestMapper.selectCounttestByParam(paramMap);
				if(totalNum==subNum){
					
					Entrust entrust  = new Entrust();
					entrust.setId(Long.parseLong(test.getPid()));
					entrust.setSt_lv("已取消");
					iEntrustMapper.updateentrust(entrust);
					
					
				}
				else {
					paramMap = new HashMap();
					paramMap.put("pid", test.getPid());
					paramMap.put("sum_status", "('待审批','已取消')");
					subNum= iTestMapper.selectCounttestByParam(paramMap);
					
					if(totalNum==subNum){ 
						Entrust entrust  = new Entrust();
						entrust.setId(Long.parseLong(test.getPid()));
						entrust.setSt_lv("待审批");
						iEntrustMapper.updateentrust(entrust);
					}
					paramMap.put("sum_status", "('待审核','已取消')");
					subNum= iTestMapper.selectCounttestByParam(paramMap);
					if(totalNum==subNum){ 
						Entrust entrust  = new Entrust();
						entrust.setId(Long.parseLong(test.getPid()));
						entrust.setSt_lv("待审核");
						iEntrustMapper.updateentrust(entrust);
					}
				}
				//修改样品   stlv  已检验
				if(test.getSample_id()!=null){
					//Sample tempSample= iSampleMapper.selectsampleById(test.getSample_id());
					Sample tempSample = new Sample();
					tempSample.setId(Long.parseLong(test.getSample_id()));
					tempSample.setJd_lv("已取消");
					
					iSampleMapper.updatesample(tempSample);
				}
				
			}
		} 
		else if(test.getStatus()!=null&&test.getStatus().equals("新建-检验中")){
			test.setStatus("检验中");
			result=iTestMapper.updatetest(test);
			if(result>0){
				test=iTestMapper.selecttestById(test.getId()+"");
				/*Map  paramMap = new HashMap ();
				paramMap.put("fromPage",0);
				paramMap.put("toPage",1); 
				paramMap.put("pid", test.getPid());
				List<Entrust_sample> esList= iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
				if(esList.size()>0){
					Sample sample = iSampleMapper.selectsampleById(esList.get(0).getSample_id()+"");
					if(sample!=null){
						Sample temp= new Sample();
						temp.setId(sample.getId());
						temp.setJd_lv("已检验");
						iSampleMapper.updatesample(temp);
					}
					
				}*/
				Entrust entrust  = new Entrust();
				entrust.setId(Long.parseLong(test.getPid()));
				entrust.setSt_lv("检验中");
				iEntrustMapper.updateentrust(entrust);
				 
				//修改样品   stlv  已检验
				if(test.getSample_id()!=null){
					//Sample tempSample= iSampleMapper.selectsampleById(test.getSample_id());
					Sample tempSample = new Sample();
					tempSample.setId(Long.parseLong(test.getSample_id()));
					tempSample.setJd_lv("已检验");
					
					iSampleMapper.updatesample(tempSample);
				}
			}
			 
			
		}
		else{
			result=iTestMapper.updatetest(test);
		}
		
		 
		
	 
		return result;
	}
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int updatetestbypid(Test test){
		return iTestMapper.updatetestbypid(test);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addtest(Test test){
		return iTestMapper.addtest(test);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deletetest(String id){
		return iTestMapper.deletetest(id);
	}

}

