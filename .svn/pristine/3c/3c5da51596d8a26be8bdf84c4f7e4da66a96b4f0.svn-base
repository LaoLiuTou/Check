package com.check.service.members;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.members.IMembersMapper;
import com.check.model.members.Members;
public class MembersServiceImpl  implements IMembersService {

	@Autowired
	private IMembersMapper iMembersMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Members selectmembersById(String id){
		return iMembersMapper.selectmembersById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Members> selectmembersByParam(Map paramMap){ 
		return iMembersMapper.selectmembersByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountmembersByParam(Map paramMap){ 
		return iMembersMapper.selectCountmembersByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatemembers(Members members){
		return iMembersMapper.updatemembers(members);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addmembers(Members members){
		return iMembersMapper.addmembers(members);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletemembers(String id){
		return iMembersMapper.deletemembers(id);
	}

}

