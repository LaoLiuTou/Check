package com.check.service.sample_templet;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.sample_templet.ISample_templetMapper;
import com.check.model.sample_templet.Sample_templet;
public class Sample_templetServiceImpl  implements ISample_templetService {

	@Autowired
	private ISample_templetMapper iSample_templetMapper;
	/**
 * 通过id选取
 * @return
 */
	public Sample_templet selectsample_templetById(String id){
		return iSample_templetMapper.selectsample_templetById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Sample_templet> selectsample_templetByParam(Map paramMap){ 
		return iSample_templetMapper.selectsample_templetByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountsample_templetByParam(Map paramMap){ 
		return iSample_templetMapper.selectCountsample_templetByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatesample_templet(Sample_templet sample_templet){
		return iSample_templetMapper.updatesample_templet(sample_templet);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addsample_templet(Sample_templet sample_templet){
		return iSample_templetMapper.addsample_templet(sample_templet);
	}

	/**
	 * 删除 
	 * @return 
	 */ 
	public  int deletesample_templet(String id){
		return iSample_templetMapper.deletesample_templet(id);
	}

	 @Transactional
	public int mulupdatesample_templet(List<Sample_templet> sample_templetList) {
		 return iSample_templetMapper.mulupdatesample_templet(sample_templetList);
	}

	 @Transactional
	public int muladdsample_templet(List<Sample_templet> sample_templetList) {
		 return iSample_templetMapper.muladdsample_templet(sample_templetList);
	}

}

