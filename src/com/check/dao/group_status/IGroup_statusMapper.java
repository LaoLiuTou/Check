package com.check.dao.group_status;
import java.util.List;
import java.util.Map;
import com.check.model.group_status.Group_status;
	public interface IGroup_statusMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Group_status selectgroup_statusById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Group_status> selectgroup_statusByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountgroup_statusByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updategroup_status(Group_status group_status);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addgroup_status(Group_status group_status);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletegroup_status(String id);

}

