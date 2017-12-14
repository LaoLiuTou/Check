package com.check.service.sample;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.dao.entrust.IEntrustMapper;
import com.check.dao.entrust_pin.IEntrust_pinMapper;
import com.check.dao.entrust_sample.IEntrust_sampleMapper;
import com.check.dao.sample.ISampleMapper;
import com.check.model.entrust.Entrust;
import com.check.model.entrust_sample.Entrust_sample;
import com.check.model.sample.Sample;
import com.check.model.sample_templet.Sample_templet;
public class SampleServiceImpl  implements ISampleService {

	@Autowired
	private ISampleMapper iSampleMapper;
	@Autowired
	private IEntrustMapper iEntrustMapper;
	@Autowired
	private IEntrust_sampleMapper iEntrust_sampleMapper;
	@Autowired
	private IEntrust_pinMapper iEntrust_pinMapper;
	/**
 * 通过id选取
 * @return
 */
	public Sample selectsampleById(String id){
		return iSampleMapper.selectsampleById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Sample> selectsampleByParam(Map paramMap){ 
		return iSampleMapper.selectsampleByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountsampleByParam(Map paramMap){ 
		return iSampleMapper.selectCountsampleByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatesample(Sample sample){
		return iSampleMapper.updatesample(sample);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addsample(Sample sample){
		return iSampleMapper.addsample(sample);
	}

	/**
 * 删除 
 * @return 
 */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  int deletesample(String id){
		int result =0;
		
		
		result=iSampleMapper.deletesample(id);
		if(result>0){
			Map paramMap = new HashMap();
			paramMap = new HashMap();
			paramMap.put("fromPage", 0);
			paramMap.put("toPage", 1);
			paramMap.put("sample_id", id);
			List<Entrust_sample> esList =iEntrust_sampleMapper.selectentrust_sampleByParam(paramMap);
			if(esList.size()>0){
		
				String eid=esList.get(0).getPid();
				iEntrust_sampleMapper.deleteentrust_sample(esList.get(0).getId()+"");
				paramMap = new HashMap();
				paramMap.put("pid", eid);
				int eNum = iEntrust_sampleMapper.selectCountentrust_sampleByParam(paramMap);
				if(eNum==0){
					int eresult=iEntrustMapper.deleteentrust(eid);
					if(eresult>0){
						iEntrust_pinMapper.deleteEntrust_pinByPid(eid);
					}
				}
		  
			}
			else{
				
			}
	 
		}
		
		
		
		return result;
	}
	 @Transactional
	public int mulupdateSample(List<Sample> sampleList) {
		 return iSampleMapper.mulupdateSample(sampleList);
	}
}

