package com.check.service.results;
import java.util.List;
import java.util.Map;
import com.check.model.results.Results;
public interface IResultsService {
	/**
 * 通过id选取
 * @return
 */
	public Results selectresultsById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Results> selectresultsByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountresultsByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateresults(Results results);
	/**
 * 添加 
 * @return
 */ 
	public  Object addresults(Results results);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteresults(String id);

}

