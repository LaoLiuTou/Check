package com.check.dao.auth_group;
import java.util.List;
import java.util.Map;
import com.check.model.auth_group.Auth_group;
	public interface IAuth_groupMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Auth_group selectauth_groupById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Auth_group> selectauth_groupByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountauth_groupByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateauth_group(Auth_group auth_group);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addauth_group(Auth_group auth_group);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteauth_group(String id);

}

