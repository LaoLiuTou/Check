package com.check.service.notices_label;
import java.util.List;
import java.util.Map;
import com.check.model.notices_label.Notices_label;
public interface INotices_labelService {
	/**
 * 通过id选取
 * @return
 */
	public Notices_label selectnotices_labelById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Notices_label> selectnotices_labelByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountnotices_labelByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatenotices_label(Notices_label notices_label);
	/**
 * 添加 
 * @return
 */ 
	public  Object addnotices_label(Notices_label notices_label);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletenotices_label(String id);

}

