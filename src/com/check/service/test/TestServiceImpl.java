package com.check.service.test;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.test.ITestMapper;
import com.check.model.test.Test;
public class TestServiceImpl  implements ITestService {

	@Autowired
	private ITestMapper iTestMapper;
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
	public  int updatetest(Test test){
		return iTestMapper.updatetest(test);
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

