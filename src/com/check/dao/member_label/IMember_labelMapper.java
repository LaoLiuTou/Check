package com.check.dao.member_label;
import java.util.List;
import java.util.Map;
import com.check.model.member_label.Member_label;
	public interface IMember_labelMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Member_label selectmember_labelById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Member_label> selectmember_labelByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountmember_labelByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatemember_label(Member_label member_label);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addmember_label(Member_label member_label);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletemember_label(String id);

}

