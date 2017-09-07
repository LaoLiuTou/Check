package com.check.service.lov;
import java.util.List;
import java.util.Map;
import com.check.model.lov.Lov;
public interface ILovService {
	/**
 * 通过id选取
 * @return
 */
	public Lov selectlovById(String id);
	/**
	 * 排序
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Lov> selectlovByParamOrder(Map paramMap); 
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Lov> selectlovByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountlovByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatelov(Lov lov);
	/**
 * 添加 
 * @return
 */ 
	public  Object addlov(Lov lov);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletelov(String id);

}

