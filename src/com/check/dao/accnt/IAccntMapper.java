package com.check.dao.accnt;
import java.util.List;
import java.util.Map;
import com.check.model.accnt.Accnt;
	public interface IAccntMapper {
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
	public  int addaccnt(Accnt accnt);
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

}

