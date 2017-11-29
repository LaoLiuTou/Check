package com.check.service.user_set;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.user_set.IUser_setMapper;
import com.check.model.user_set.User_set;
public class User_setServiceImpl  implements IUser_setService {

	@Autowired
	private IUser_setMapper iUser_setMapper;
	/**
 * 通过id选取
 * @return
 */
	public User_set selectuser_setById(String id){
		return iUser_setMapper.selectuser_setById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<User_set> selectuser_setByParam(Map paramMap){ 
		return iUser_setMapper.selectuser_setByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountuser_setByParam(Map paramMap){ 
		return iUser_setMapper.selectCountuser_setByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateuser_set(User_set user_set){
		return iUser_setMapper.updateuser_set(user_set);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object adduser_set(User_set user_set){
		return iUser_setMapper.adduser_set(user_set);
	}

	/**
	 * 删除 
	 * @return 
	 */ 
	 @Transactional
		public  int deleteuser_set(String id){
			return iUser_setMapper.deleteuser_set(id);
		}
	 /**
	  * 删除 
	  * @return 
	  */ 
	 @Transactional
	 public  int deleteuser_setBytyle(String tylv){
		 return iUser_setMapper.deleteuser_setBytyle(tylv);
	 }

}

