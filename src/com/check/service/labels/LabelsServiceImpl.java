package com.check.service.labels;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.labels.ILabelsMapper;
import com.check.model.labels.Labels;
public class LabelsServiceImpl  implements ILabelsService {

	@Autowired
	private ILabelsMapper iLabelsMapper;
	/**
 * 通过id选取
 * @return
 */
	public Labels selectlabelsById(String id){
		return iLabelsMapper.selectlabelsById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Labels> selectlabelsByParam(Map paramMap){ 
		return iLabelsMapper.selectlabelsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountlabelsByParam(Map paramMap){ 
		return iLabelsMapper.selectCountlabelsByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatelabels(Labels labels){
		return iLabelsMapper.updatelabels(labels);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addlabels(Labels labels){
		return iLabelsMapper.addlabels(labels);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletelabels(String id){
		return iLabelsMapper.deletelabels(id);
	}

}

