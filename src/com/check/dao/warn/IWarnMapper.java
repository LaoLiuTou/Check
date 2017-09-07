package com.check.dao.warn;
import java.util.List;
import java.util.Map;
import com.check.model.warn.Warn;
	public interface IWarnMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Warn selectwarnById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Warn> selectwarnByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountwarnByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatewarn(Warn warn);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addwarn(Warn warn);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletewarn(String id);

}

