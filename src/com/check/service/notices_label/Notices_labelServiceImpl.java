package com.check.service.notices_label;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.notices_label.INotices_labelMapper;
import com.check.model.notices_label.Notices_label;
public class Notices_labelServiceImpl  implements INotices_labelService {

	@Autowired
	private INotices_labelMapper iNotices_labelMapper;
	/**
 * 通过id选取
 * @return
 */
	public Notices_label selectnotices_labelById(String id){
		return iNotices_labelMapper.selectnotices_labelById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Notices_label> selectnotices_labelByParam(Map paramMap){ 
		return iNotices_labelMapper.selectnotices_labelByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountnotices_labelByParam(Map paramMap){ 
		return iNotices_labelMapper.selectCountnotices_labelByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatenotices_label(Notices_label notices_label){
		return iNotices_labelMapper.updatenotices_label(notices_label);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addnotices_label(Notices_label notices_label){
		return iNotices_labelMapper.addnotices_label(notices_label);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletenotices_label(String id){
		return iNotices_labelMapper.deletenotices_label(id);
	}

}

