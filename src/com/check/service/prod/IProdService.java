package com.check.service.prod;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.check.model.prod.Prod;
public interface IProdService {
	/**
 * 通过id选取
 * @return
 */
	public Prod selectprodById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Prod> selectprodByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountprodByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateprod(Prod prod);
	/**
 * 添加 
 * @return
 */ 
	public  Object addprod(Prod prod);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteprod(String id);
	/**
	  * 添加流程 
	  * @return
	  */ 
	 public  Object addflowprod(Prod prod,String muladd,String termids,String subprod,String prod_assets);
	 /**
	  * 更新流程 
	  * @return
	  */ 
	 public  Object updateflowprod(Prod prod,String mulupdate,String termids,String subprod,String prod_assets);
}

