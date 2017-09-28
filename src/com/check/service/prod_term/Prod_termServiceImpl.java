package com.check.service.prod_term;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.prod_term.IProd_termMapper;
import com.check.model.prod_term.Prod_term;
public class Prod_termServiceImpl  implements IProd_termService {

	@Autowired
	private IProd_termMapper iProd_termMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Prod_term selectprod_termById(String id){
		return iProd_termMapper.selectprod_termById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Prod_term> selectprod_termByParam(Map paramMap){ 
		return iProd_termMapper.selectprod_termByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountprod_termByParam(Map paramMap){ 
		return iProd_termMapper.selectCountprod_termByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateprod_term(Prod_term prod_term){
		return iProd_termMapper.updateprod_term(prod_term);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addprod_term(Prod_term prod_term){
		return iProd_termMapper.addprod_term(prod_term);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteprod_term(String id){
		return iProd_termMapper.deleteprod_term(id);
	}
 /**
  * 删除 
  * @return 
  */ 
 @Transactional
 public  int deleteprod_termbyprod(String prod_id,List<Prod_term> list){
	 int result= 0;
	 iProd_termMapper.deleteprod_termbyprod(prod_id);
	 if(list.size()>0){
		 result = iProd_termMapper.muladdprod_term(list);
		 
	 }
	 return result;
	 
 }

}

