package com.check.service.atta;
import java.util.List;
import java.util.Map;
import com.check.model.atta.Atta;
public interface IAttaService {
	/**
 * 通过id选取
 * @return
 */
	public Atta selectattaById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Atta> selectattaByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountattaByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateatta(Atta atta);
	/**
 * 添加 
 * @return
 */ 
	public  Object addatta(Atta atta);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteatta(String id);

}

