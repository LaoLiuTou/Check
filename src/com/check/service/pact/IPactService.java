package com.check.service.pact;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.check.model.pact.Pact;
public interface IPactService {
	/**
 * 通过id选取
 * @return
 */
	public Pact selectpactById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Pact> selectpactByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountpactByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatepact(Pact pact);
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int updatepactAndAtta(Pact pact,String attastr);
	/**
 * 添加 
 * @return
 */ 
	public  Object addpact(Pact pact);
	
	/**
	* 添加 
	* @return
	*/ 
	public  int addPactAndAtta(Pact pact,String attastr);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletepact(String id);

	
	/**
	  * tree
	  * @return 
	  */ 
	 public  String selectAppTreePact(String ids,String test_status);
}

