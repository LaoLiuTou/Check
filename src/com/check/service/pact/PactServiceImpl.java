package com.check.service.pact;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.pact.IPactMapper;
import com.check.model.pact.Pact;
public class PactServiceImpl  implements IPactService {

	@Autowired
	private IPactMapper iPactMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Pact selectpactById(String id){
		return iPactMapper.selectpactById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Pact> selectpactByParam(Map paramMap){ 
		return iPactMapper.selectpactByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountpactByParam(Map paramMap){ 
		return iPactMapper.selectCountpactByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatepact(Pact pact){
		return iPactMapper.updatepact(pact);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addpact(Pact pact){
		return iPactMapper.addpact(pact);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletepact(String id){
		return iPactMapper.deletepact(id);
	}

}

