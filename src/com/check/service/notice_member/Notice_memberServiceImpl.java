package com.check.service.notice_member;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.notice_member.INotice_memberMapper;
import com.check.model.notice_member.Notice_member;
public class Notice_memberServiceImpl  implements INotice_memberService {

	@Autowired
	private INotice_memberMapper iNotice_memberMapper;
	/**
 * 通过id选取
 * @return
 */
	public Notice_member selectnotice_memberById(String id){
		return iNotice_memberMapper.selectnotice_memberById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Notice_member> selectnotice_memberByParam(Map paramMap){ 
		return iNotice_memberMapper.selectnotice_memberByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountnotice_memberByParam(Map paramMap){ 
		return iNotice_memberMapper.selectCountnotice_memberByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatenotice_member(Notice_member notice_member){
		return iNotice_memberMapper.updatenotice_member(notice_member);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addnotice_member(Notice_member notice_member){
		return iNotice_memberMapper.addnotice_member(notice_member);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletenotice_member(String id){
		return iNotice_memberMapper.deletenotice_member(id);
	}

}

