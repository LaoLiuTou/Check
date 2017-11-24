package com.check.service.auth_group;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.auth_group.IAuth_groupMapper;
import com.check.model.auth_group.Auth_group;
public class Auth_groupServiceImpl  implements IAuth_groupService {

	@Autowired
	private IAuth_groupMapper iAuth_groupMapper;
	/**
 * 通过id选取
 * @return
 */
	public Auth_group selectauth_groupById(String id){
		return iAuth_groupMapper.selectauth_groupById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Auth_group> selectauth_groupByParam(Map paramMap){ 
		return iAuth_groupMapper.selectauth_groupByParam(paramMap);
	}
	/**
	 * 通过查询参数获取信息
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Auth_group> selectauth_groupByTreeParam(Map paramMap){ 
		return iAuth_groupMapper.selectauth_groupByTreeParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountauth_groupByParam(Map paramMap){ 
		return iAuth_groupMapper.selectCountauth_groupByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateauth_group(Auth_group auth_group){
		return iAuth_groupMapper.updateauth_group(auth_group);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addauth_group(Auth_group auth_group){
		return iAuth_groupMapper.addauth_group(auth_group);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteauth_group(String id){
		return iAuth_groupMapper.deleteauth_group(id);
	}

}

