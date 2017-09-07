package com.check.service.sample;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.sample.ISampleMapper;
import com.check.model.sample.Sample;
public class SampleServiceImpl  implements ISampleService {

	@Autowired
	private ISampleMapper iSampleMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Sample selectsampleById(String id){
		return iSampleMapper.selectsampleById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Sample> selectsampleByParam(Map paramMap){ 
		return iSampleMapper.selectsampleByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountsampleByParam(Map paramMap){ 
		return iSampleMapper.selectCountsampleByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatesample(Sample sample){
		return iSampleMapper.updatesample(sample);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addsample(Sample sample){
		return iSampleMapper.addsample(sample);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletesample(String id){
		return iSampleMapper.deletesample(id);
	}

}

