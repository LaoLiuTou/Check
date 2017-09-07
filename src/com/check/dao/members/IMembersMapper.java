package com.check.dao.members;
import java.util.List;
import java.util.Map;
import com.check.model.members.Members;
	public interface IMembersMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Members selectmembersById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Members> selectmembersByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountmembersByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatemembers(Members members);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addmembers(Members members);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletemembers(String id);

}

