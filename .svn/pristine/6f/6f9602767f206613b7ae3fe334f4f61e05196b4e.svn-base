package com.check.service.results;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.results.IResultsMapper;
import com.check.model.results.Results;
public class ResultsServiceImpl  implements IResultsService {

	@Autowired
	private IResultsMapper iResultsMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Results selectresultsById(String id){
		return iResultsMapper.selectresultsById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Results> selectresultsByParam(Map paramMap){ 
		return iResultsMapper.selectresultsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountresultsByParam(Map paramMap){ 
		return iResultsMapper.selectCountresultsByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateresults(Results results){
		return iResultsMapper.updateresults(results);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addresults(Results results){
		return iResultsMapper.addresults(results);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteresults(String id){
		return iResultsMapper.deleteresults(id);
	}

}

