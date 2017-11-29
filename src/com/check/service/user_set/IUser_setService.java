package com.check.service.user_set;
import java.util.List;
import java.util.Map;
import com.check.model.user_set.User_set;
public interface IUser_setService {
	/**
 * 通过id选取
 * @return
 */
	public User_set selectuser_setById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<User_set> selectuser_setByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountuser_setByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateuser_set(User_set user_set);
	/**
 * 添加 
 * @return
 */ 
	public  Object adduser_set(User_set user_set);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteuser_set(String id);
	/**
	 * 删除 
	 * @return 
	 */ 
	public  int deleteuser_setBytyle(String tyle);

}

