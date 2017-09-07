package com.check.service.conclusion_templet;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.conclusion_templet.IConclusion_templetMapper;
import com.check.model.conclusion_templet.Conclusion_templet;
public class Conclusion_templetServiceImpl  implements IConclusion_templetService {

	@Autowired
	private IConclusion_templetMapper iConclusion_templetMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Conclusion_templet selectconclusion_templetById(String id){
		return iConclusion_templetMapper.selectconclusion_templetById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Conclusion_templet> selectconclusion_templetByParam(Map paramMap){ 
		return iConclusion_templetMapper.selectconclusion_templetByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountconclusion_templetByParam(Map paramMap){ 
		return iConclusion_templetMapper.selectCountconclusion_templetByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateconclusion_templet(Conclusion_templet conclusion_templet){
		return iConclusion_templetMapper.updateconclusion_templet(conclusion_templet);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addconclusion_templet(Conclusion_templet conclusion_templet){
		return iConclusion_templetMapper.addconclusion_templet(conclusion_templet);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteconclusion_templet(String id){
		return iConclusion_templetMapper.deleteconclusion_templet(id);
	}

}

