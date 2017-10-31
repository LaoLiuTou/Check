package com.check.service.status;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.status.IStatusMapper;
import com.check.model.status.Status;
public class StatusServiceImpl  implements IStatusService {

	@Autowired
	private IStatusMapper iStatusMapper;
	/**
 * 通过id选取
 * @return
 */
	public Status selectstatusById(String id){
		return iStatusMapper.selectstatusById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Status> selectstatusByParam(Map paramMap){ 
		return iStatusMapper.selectstatusByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountstatusByParam(Map paramMap){ 
		return iStatusMapper.selectCountstatusByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatestatus(Status status){
		return iStatusMapper.updatestatus(status);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addstatus(Status status){
		return iStatusMapper.addstatus(status);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletestatus(String id){
		return iStatusMapper.deletestatus(id);
	}

}

