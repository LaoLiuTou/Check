package com.check.service.pay;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.pay.IPayMapper;
import com.check.model.pay.Pay;
public class PayServiceImpl  implements IPayService {

	@Autowired
	private IPayMapper iPayMapper;
	/**
 * 通过id选取
 * @return
 */
	public Pay selectpayById(String id){
		return iPayMapper.selectpayById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Pay> selectpayByParam(Map paramMap){ 
		return iPayMapper.selectpayByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountpayByParam(Map paramMap){ 
		return iPayMapper.selectCountpayByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatepay(Pay pay){
		return iPayMapper.updatepay(pay);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addpay(Pay pay){
		return iPayMapper.addpay(pay);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deletepay(String id){
		return iPayMapper.deletepay(id);
	}

}

