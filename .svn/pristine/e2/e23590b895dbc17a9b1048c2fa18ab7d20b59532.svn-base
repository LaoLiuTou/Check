package com.check.service.price;
import java.util.List;
import java.util.Map;
import com.check.model.price.Price;
public interface IPriceService {
	/**
 * 通过id选取
 * @return
 */
	public Price selectpriceById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Price> selectpriceByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountpriceByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateprice(Price price);
	/**
 * 添加 
 * @return
 */ 
	public  Object addprice(Price price);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteprice(String id);

}

