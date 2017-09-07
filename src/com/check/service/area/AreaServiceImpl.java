package com.check.service.area;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.area.IAreaMapper;
import com.check.model.area.Area;
public class AreaServiceImpl  implements IAreaService {

	@Autowired
	private IAreaMapper iAreaMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Area selectareaById(String id){
		return iAreaMapper.selectareaById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Area> selectareaByParam(Map paramMap){ 
		return iAreaMapper.selectareaByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountareaByParam(Map paramMap){ 
		return iAreaMapper.selectCountareaByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatearea(Area area){
		return iAreaMapper.updatearea(area);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addarea(Area area){
		return iAreaMapper.addarea(area);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletearea(String id){
		return iAreaMapper.deletearea(id);
	}

}

