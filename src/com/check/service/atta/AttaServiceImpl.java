package com.check.service.atta;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.atta.IAttaMapper;
import com.check.model.atta.Atta;
public class AttaServiceImpl  implements IAttaService {

	@Autowired
	private IAttaMapper iAttaMapper;
	/**
 * 通过id选取
 * @return
 */
	public Atta selectattaById(String id){
		return iAttaMapper.selectattaById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Atta> selectattaByParam(Map paramMap){ 
		return iAttaMapper.selectattaByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountattaByParam(Map paramMap){ 
		return iAttaMapper.selectCountattaByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateatta(Atta atta){
		return iAttaMapper.updateatta(atta);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addatta(Atta atta){
		return iAttaMapper.addatta(atta);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteatta(String id){
		return iAttaMapper.deleteatta(id);
	}

}

