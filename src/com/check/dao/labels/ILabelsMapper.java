package com.check.dao.labels;
import java.util.List;
import java.util.Map;
import com.check.model.labels.Labels;
	public interface ILabelsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Labels selectlabelsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Labels> selectlabelsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountlabelsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatelabels(Labels labels);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addlabels(Labels labels);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletelabels(String id);

}

