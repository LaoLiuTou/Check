package com.check.service.sample;
import java.util.List;
import java.util.Map;
import com.check.model.sample.Sample;
import com.check.model.sample_templet.Sample_templet;
public interface ISampleService {
	/**
 * 通过id选取
 * @return
 */
	public Sample selectsampleById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Sample> selectsampleByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountsampleByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatesample(Sample sample);
	/**
 * 添加 
 * @return
 */ 
	public  Object addsample(Sample sample);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletesample(String id);
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int mulupdateSample(List<Sample> sampleList);

}

