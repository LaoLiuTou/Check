package com.check.service.auth_rule;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.auth_rule.IAuth_ruleMapper;
import com.check.model.auth_rule.Auth_rule;
public class Auth_ruleServiceImpl  implements IAuth_ruleService {

	@Autowired
	private IAuth_ruleMapper iAuth_ruleMapper;
	/**
 * 通过id选取
 * @return
 */
	public Auth_rule selectauth_ruleById(String id){
		return iAuth_ruleMapper.selectauth_ruleById(id);
	}
 /**
  * 通过userid选取
  * @return
  */
 public List<Auth_rule> selectauth_ruleByUserId(String id){
	 return iAuth_ruleMapper.selectauth_ruleByUserId(id);
 }

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Auth_rule> selectauth_ruleByParam(Map paramMap){ 
		return iAuth_ruleMapper.selectauth_ruleByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountauth_ruleByParam(Map paramMap){ 
		return iAuth_ruleMapper.selectCountauth_ruleByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateauth_rule(Auth_rule auth_rule){
		return iAuth_ruleMapper.updateauth_rule(auth_rule);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addauth_rule(Auth_rule auth_rule){
		return iAuth_ruleMapper.addauth_rule(auth_rule);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteauth_rule(String id){
		return iAuth_ruleMapper.deleteauth_rule(id);
	}

}

