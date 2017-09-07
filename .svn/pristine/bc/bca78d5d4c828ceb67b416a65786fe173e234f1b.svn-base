package com.check.service.entrust_pin;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.entrust_pin.IEntrust_pinMapper;
import com.check.model.entrust_pin.Entrust_pin;
public class Entrust_pinServiceImpl  implements IEntrust_pinService {

	@Autowired
	private IEntrust_pinMapper iEntrust_pinMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Entrust_pin selectentrust_pinById(String id){
		return iEntrust_pinMapper.selectentrust_pinById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Entrust_pin> selectentrust_pinByParam(Map paramMap){ 
		return iEntrust_pinMapper.selectentrust_pinByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountentrust_pinByParam(Map paramMap){ 
		return iEntrust_pinMapper.selectCountentrust_pinByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateentrust_pin(Entrust_pin entrust_pin){
		return iEntrust_pinMapper.updateentrust_pin(entrust_pin);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addentrust_pin(Entrust_pin entrust_pin){
		return iEntrust_pinMapper.addentrust_pin(entrust_pin);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteentrust_pin(String id){
		return iEntrust_pinMapper.deleteentrust_pin(id);
	}

}

