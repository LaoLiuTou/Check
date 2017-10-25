package com.check.service.test;
import java.util.List;
import java.util.Map;
import com.check.model.test.Test;
public interface ITestService {
	/**
 * 通过id选取
 * @return
 */
	public Test selecttestById(String id);
	/**
	 * first code
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Test> selectFirstTestCode(Map paramMap); 
	/**
	 * first code
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Test> selectSecondTestCode(Map paramMap); 
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Test> selecttestByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCounttestByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatetest(Test test);
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int updatetestbypid(Test test);
	/**
 * 添加 
 * @return
 */ 
	public  Object addtest(Test test);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletetest(String id);

}

