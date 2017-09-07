package com.check.dao.sample_templet;
import java.util.List;
import java.util.Map;
import com.check.model.sample_templet.Sample_templet;
	public interface ISample_templetMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Sample_templet selectsample_templetById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Sample_templet> selectsample_templetByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountsample_templetByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatesample_templet(Sample_templet sample_templet);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addsample_templet(Sample_templet sample_templet);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletesample_templet(String id);

}

