package com.check.service.accnt;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.check.model.accnt.Accnt;
public interface IAccntService {
	/**
 * 通过id选取
 * @return
 */
	public Accnt selectaccntById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Accnt> selectaccntByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountaccntByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateaccnt(Accnt accnt);
	/**
 * 添加 
 * @return
 */ 
	public  Object addaccnt(Accnt accnt);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteaccnt(String id);
	 /**
	  * 当前时间
	  * @return 
	  */ 
 
	 public  String selectDbtime();
	 
	 
		/**
	  * 添加 
	  * @return
	  */ 
	 	public  int addallaccnt(Accnt accnt,String cont,String atta);
	 	
 	/**
 	  * 更新 all
 	  * @return 
 	  */ 
 	 @Transactional
 	 public  int updateallaccnt(Accnt accnt,String contstr,String attastr);
}

