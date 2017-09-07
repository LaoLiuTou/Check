package com.check.service.prod;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.prod.IProdMapper;
import com.check.model.prod.Prod;
public class ProdServiceImpl  implements IProdService {

	@Autowired
	private IProdMapper iProdMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Prod selectprodById(String id){
		return iProdMapper.selectprodById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Prod> selectprodByParam(Map paramMap){ 
		return iProdMapper.selectprodByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountprodByParam(Map paramMap){ 
		return iProdMapper.selectCountprodByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateprod(Prod prod){
		return iProdMapper.updateprod(prod);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addprod(Prod prod){
		return iProdMapper.addprod(prod);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteprod(String id){
		return iProdMapper.deleteprod(id);
	}

}

