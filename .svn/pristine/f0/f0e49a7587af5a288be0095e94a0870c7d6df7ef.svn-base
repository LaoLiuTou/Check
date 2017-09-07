package com.check.service.data_cache;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.data_cache.IData_cacheMapper;
import com.check.model.data_cache.Data_cache;
public class Data_cacheServiceImpl  implements IData_cacheService {

	@Autowired
	private IData_cacheMapper iData_cacheMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Data_cache selectdata_cacheById(String id){
		return iData_cacheMapper.selectdata_cacheById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Data_cache> selectdata_cacheByParam(Map paramMap){ 
		return iData_cacheMapper.selectdata_cacheByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountdata_cacheByParam(Map paramMap){ 
		return iData_cacheMapper.selectCountdata_cacheByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatedata_cache(Data_cache data_cache){
		return iData_cacheMapper.updatedata_cache(data_cache);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object adddata_cache(Data_cache data_cache){
		return iData_cacheMapper.adddata_cache(data_cache);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletedata_cache(String id){
		return iData_cacheMapper.deletedata_cache(id);
	}

}

