package com.check.service.group_status;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.group_status.IGroup_statusMapper;
import com.check.model.group_status.Group_status;
public class Group_statusServiceImpl  implements IGroup_statusService {

	@Autowired
	private IGroup_statusMapper iGroup_statusMapper;
	/**
 * 通过id选取
 * @return
 */
	public Group_status selectgroup_statusById(String id){
		return iGroup_statusMapper.selectgroup_statusById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Group_status> selectgroup_statusByParam(Map paramMap){ 
		return iGroup_statusMapper.selectgroup_statusByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountgroup_statusByParam(Map paramMap){ 
		return iGroup_statusMapper.selectCountgroup_statusByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updategroup_status(Group_status group_status){
		return iGroup_statusMapper.updategroup_status(group_status);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addgroup_status(Group_status group_status){
		return iGroup_statusMapper.addgroup_status(group_status);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletegroup_status(String id){
		return iGroup_statusMapper.deletegroup_status(id);
	}

}

