package com.check.service.member_label;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.member_label.IMember_labelMapper;
import com.check.model.member_label.Member_label;
public class Member_labelServiceImpl  implements IMember_labelService {

	@Autowired
	private IMember_labelMapper iMember_labelMapper;
	/**
 * 通过id选取
 * @return
 */
	public Member_label selectmember_labelById(String id){
		return iMember_labelMapper.selectmember_labelById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Member_label> selectmember_labelByParam(Map paramMap){ 
		return iMember_labelMapper.selectmember_labelByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountmember_labelByParam(Map paramMap){ 
		return iMember_labelMapper.selectCountmember_labelByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatemember_label(Member_label member_label){
		return iMember_labelMapper.updatemember_label(member_label);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addmember_label(Member_label member_label){
		return iMember_labelMapper.addmember_label(member_label);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletemember_label(String id){
		return iMember_labelMapper.deletemember_label(id);
	}

}

