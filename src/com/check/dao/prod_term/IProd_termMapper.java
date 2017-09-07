package com.check.dao.prod_term;
import java.util.List;
import java.util.Map;
import com.check.model.prod_term.Prod_term;
	public interface IProd_termMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Prod_term selectprod_termById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Prod_term> selectprod_termByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountprod_termByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateprod_term(Prod_term prod_term);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addprod_term(Prod_term prod_term);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteprod_term(String id);

}

