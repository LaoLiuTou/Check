package com.check.dao.prod;
import java.util.List;
import java.util.Map;
import com.check.model.prod.Prod;
import com.check.model.sample_templet.Sample_templet;
	public interface IProdMapper {
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
	public  int addprod(Prod prod);
	/**
 	* 删除 
 	* @return 
	 */ 
	public  int deleteprod(String id);
	
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int mulupdateprod(List<Prod> list);
	/**
	 * 添加 
	 * @return
	 */ 
	public  int muladdprod(List<Prod> list);

}

