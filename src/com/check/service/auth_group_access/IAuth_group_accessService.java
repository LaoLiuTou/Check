package com.check.service.auth_group_access;
import java.util.List;
import java.util.Map;
import com.check.model.auth_group_access.Auth_group_access;
public interface IAuth_group_accessService {
	/**
 * 通过id选取
 * @return
 */
	public Auth_group_access selectauth_group_accessById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Auth_group_access> selectauth_group_accessByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountauth_group_accessByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateauth_group_access(Auth_group_access auth_group_access);
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int updateauth_group_accessbyuid(Auth_group_access auth_group_access);
	/**
 * 添加 
 * @return
 */ 
	public  Object addauth_group_access(Auth_group_access auth_group_access);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteauth_group_access(String id);

}

