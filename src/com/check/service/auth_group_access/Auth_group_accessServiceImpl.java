package com.check.service.auth_group_access;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.auth_group_access.IAuth_group_accessMapper;
import com.check.model.auth_group_access.Auth_group_access;
public class Auth_group_accessServiceImpl  implements IAuth_group_accessService {

	@Autowired
	private IAuth_group_accessMapper iAuth_group_accessMapper;
	/**
 * 通过id选取
 * @return
 */
	public Auth_group_access selectauth_group_accessById(String id){
		return iAuth_group_accessMapper.selectauth_group_accessById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Auth_group_access> selectauth_group_accessByParam(Map paramMap){ 
		return iAuth_group_accessMapper.selectauth_group_accessByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountauth_group_accessByParam(Map paramMap){ 
		return iAuth_group_accessMapper.selectCountauth_group_accessByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateauth_group_access(Auth_group_access auth_group_access){
		return iAuth_group_accessMapper.updateauth_group_access(auth_group_access);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addauth_group_access(Auth_group_access auth_group_access){
		return iAuth_group_accessMapper.addauth_group_access(auth_group_access);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteauth_group_access(String id){
		return iAuth_group_accessMapper.deleteauth_group_access(id);
	}

}

