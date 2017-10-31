package com.check.dao.status;
import java.util.List;
import java.util.Map;
import com.check.model.status.Status;
	public interface IStatusMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Status selectstatusById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Status> selectstatusByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountstatusByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatestatus(Status status);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addstatus(Status status);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletestatus(String id);

}

