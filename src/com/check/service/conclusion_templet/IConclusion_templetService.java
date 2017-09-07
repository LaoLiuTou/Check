package com.check.service.conclusion_templet;
import java.util.List;
import java.util.Map;
import com.check.model.conclusion_templet.Conclusion_templet;
public interface IConclusion_templetService {
	/**
 * 通过id选取
 * @return
 */
	public Conclusion_templet selectconclusion_templetById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Conclusion_templet> selectconclusion_templetByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountconclusion_templetByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateconclusion_templet(Conclusion_templet conclusion_templet);
	/**
 * 添加 
 * @return
 */ 
	public  Object addconclusion_templet(Conclusion_templet conclusion_templet);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteconclusion_templet(String id);

}

