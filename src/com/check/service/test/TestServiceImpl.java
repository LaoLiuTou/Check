package com.check.service.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.check.dao.entrust.IEntrustMapper;
import com.check.dao.test.ITestMapper;
import com.check.model.entrust.Entrust;
import com.check.model.test.Test;
public class TestServiceImpl  implements ITestService {

	@Autowired
	private ITestMapper iTestMapper;
	@Autowired
	private IEntrustMapper iEntrustMapper;
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
		
		if(test.getStatus()!=null&&test.getStatus().equals("审批中-待审核")){
			test.setStatus("待审核");
			Entrust entrust  = new Entrust();
			entrust.setId(Long.parseLong(test.getPid()));
			entrust.setSt_lv("检验中");
			result=iTestMapper.updatetest(test);
			if(result>0){
				iEntrustMapper.updateentrust(entrust);
			}
		}
		else if(test.getStatus()!=null&&test.getStatus().equals("审核中-待检验")){
			test.setStatus("待检验");
			Entrust entrust  = new Entrust();
			entrust.setId(Long.parseLong(test.getPid()));
			entrust.setSt_lv("检验中");
			result=iTestMapper.updatetest(test);
			if(result>0){
				iEntrustMapper.updateentrust(entrust);
			}
		}
		else if(test.getStatus()!=null&&test.getStatus().equals("审核中-待审批")){
			test.setStatus("待审批");
			
			 
			result=iTestMapper.updatetest(test);
			if(result>0){
				Map paramMap = new HashMap();
				paramMap.put("pid", test.getPid());
				int totalNum = iTestMapper.selectCounttestByParam(paramMap);
				paramMap.put("status", "待审批");
				int subNum= iTestMapper.selectCounttestByParam(paramMap);
				if(totalNum==subNum){
					Entrust entrust  = new Entrust();
					entrust.setId(Long.parseLong(test.getPid()));
					entrust.setSt_lv("待审批");
					iEntrustMapper.updateentrust(entrust);
				}
				
			}
		}
		else if(test.getStatus()!=null&&test.getStatus().equals("已取消")){
			result=iTestMapper.updatetest(test);
			if(result>0){
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

