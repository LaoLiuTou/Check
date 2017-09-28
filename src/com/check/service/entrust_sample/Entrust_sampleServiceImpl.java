package com.check.service.entrust_sample;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.entrust_sample.IEntrust_sampleMapper;
import com.check.model.entrust_sample.Entrust_sample;
public class Entrust_sampleServiceImpl  implements IEntrust_sampleService {

	@Autowired
	private IEntrust_sampleMapper iEntrust_sampleMapper;
	/**
 * 通过id选取
 * @return
 */
	public Entrust_sample selectentrust_sampleById(String id){
		return iEntrust_sampleMapper.selectentrust_sampleById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Entrust_sample> selectentrust_sampleByParam(Map paramMap){ 
		return iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountentrust_sampleByParam(Map paramMap){ 
		return iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateentrust_sample(Entrust_sample entrust_sample){
		return iEntrust_sampleMapper.updateentrust_sample(entrust_sample);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addentrust_sample(Entrust_sample entrust_sample){
		return iEntrust_sampleMapper.addentrust_sample(entrust_sample);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteentrust_sample(String id){
		return iEntrust_sampleMapper.deleteentrust_sample(id);
	}

}

