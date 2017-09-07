package com.check.service.auth_rule;
import java.util.List;
import java.util.Map;
import com.check.model.auth_rule.Auth_rule;
public interface IAuth_ruleService {
	/**
 * 通过id选取
 * @return
 */
	public Auth_rule selectauth_ruleById(String id);
	/**
	 * 通过userid选取
	 * @return
	 */
	public List<Auth_rule> selectauth_ruleByUserId(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Auth_rule> selectauth_ruleByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountauth_ruleByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateauth_rule(Auth_rule auth_rule);
	/**
 * 添加 
 * @return
 */ 
	public  Object addauth_rule(Auth_rule auth_rule);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteauth_rule(String id);

}

