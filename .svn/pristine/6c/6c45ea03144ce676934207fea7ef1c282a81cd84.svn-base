package com.check.service.pay;
import java.util.List;
import java.util.Map;
import com.check.model.pay.Pay;
public interface IPayService {
	/**
 * 通过id选取
 * @return
 */
	public Pay selectpayById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Pay> selectpayByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountpayByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatepay(Pay pay);
	/**
 * 添加 
 * @return
 */ 
	public  Object addpay(Pay pay);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletepay(String id);

}

