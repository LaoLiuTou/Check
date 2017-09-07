package com.check.service.price;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.price.IPriceMapper;
import com.check.model.price.Price;
public class PriceServiceImpl  implements IPriceService {

	@Autowired
	private IPriceMapper iPriceMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Price selectpriceById(String id){
		return iPriceMapper.selectpriceById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Price> selectpriceByParam(Map paramMap){ 
		return iPriceMapper.selectpriceByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountpriceByParam(Map paramMap){ 
		return iPriceMapper.selectCountpriceByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateprice(Price price){
		return iPriceMapper.updateprice(price);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addprice(Price price){
		return iPriceMapper.addprice(price);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteprice(String id){
		return iPriceMapper.deleteprice(id);
	}

}

