package com.check.service.notice_member;
import java.util.List;
import java.util.Map;
import com.check.model.notice_member.Notice_member;
public interface INotice_memberService {
	/**
 * 通过id选取
 * @return
 */
	public Notice_member selectnotice_memberById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Notice_member> selectnotice_memberByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountnotice_memberByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatenotice_member(Notice_member notice_member);
	/**
 * 添加 
 * @return
 */ 
	public  Object addnotice_member(Notice_member notice_member);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletenotice_member(String id);

}

