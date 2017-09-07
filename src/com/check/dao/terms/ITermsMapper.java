package com.check.dao.terms;
import java.util.List;
import java.util.Map;
import com.check.model.terms.Terms;
	public interface ITermsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Terms selecttermsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Terms> selecttermsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCounttermsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateterms(Terms terms);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addterms(Terms terms);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteterms(String id);

}

