package com.check.service.entrust_pin;
import java.util.List;
import java.util.Map;
import com.check.model.entrust_pin.Entrust_pin;
public interface IEntrust_pinService {
	/**
 * 通过id选取
 * @return
 */
	public Entrust_pin selectentrust_pinById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Entrust_pin> selectentrust_pinByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountentrust_pinByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateentrust_pin(Entrust_pin entrust_pin);
	/**
 * 添加 
 * @return
 */ 
	public  Object addentrust_pin(Entrust_pin entrust_pin);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteentrust_pin(String id);

}

