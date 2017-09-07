package com.check.dao.notices;
import java.util.List;
import java.util.Map;
import com.check.model.notices.Notices;
	public interface INoticesMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Notices selectnoticesById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Notices> selectnoticesByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountnoticesByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatenotices(Notices notices);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addnotices(Notices notices);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletenotices(String id);

}

