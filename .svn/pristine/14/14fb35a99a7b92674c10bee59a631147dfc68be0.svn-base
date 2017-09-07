package com.check.service.lov;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.lov.ILovMapper;
import com.check.model.lov.Lov;
public class LovServiceImpl  implements ILovService {

	@Autowired
	private ILovMapper iLovMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Lov selectlovById(String id){
		return iLovMapper.selectlovById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Lov> selectlovByParam(Map paramMap){ 
		return iLovMapper.selectlovByParam(paramMap);
	}
 /**
  * 排序
  * @return
  */ 
 @SuppressWarnings("rawtypes")
 @Transactional
 public List<Lov> selectlovByParamOrder(Map paramMap){ 
	 return iLovMapper.selectlovByParamOrder(paramMap);
 }

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountlovByParam(Map paramMap){ 
		return iLovMapper.selectCountlovByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatelov(Lov lov){
		return iLovMapper.updatelov(lov);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addlov(Lov lov){
		return iLovMapper.addlov(lov);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletelov(String id){
		return iLovMapper.deletelov(id);
	}

}

