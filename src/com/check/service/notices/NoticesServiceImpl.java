package com.check.service.notices;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.notices.INoticesMapper;
import com.check.model.notices.Notices;
public class NoticesServiceImpl  implements INoticesService {

	@Autowired
	private INoticesMapper iNoticesMapper;
	/**
 * 通过id选取
 * @return
 */
	public Notices selectnoticesById(String id){
		return iNoticesMapper.selectnoticesById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Notices> selectnoticesByParam(Map paramMap){ 
		return iNoticesMapper.selectnoticesByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountnoticesByParam(Map paramMap){ 
		return iNoticesMapper.selectCountnoticesByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatenotices(Notices notices){
		return iNoticesMapper.updatenotices(notices);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addnotices(Notices notices){
		return iNoticesMapper.addnotices(notices);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deletenotices(String id){
		return iNoticesMapper.deletenotices(id);
	}

}

