package com.check.service.warn;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.warn.IWarnMapper;
import com.check.model.warn.Warn;
public class WarnServiceImpl  implements IWarnService {

	@Autowired
	private IWarnMapper iWarnMapper;
	/**
 * 通过id选取
 * @return
 */
	public Warn selectwarnById(String id){
		return iWarnMapper.selectwarnById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Warn> selectwarnByParam(Map paramMap){ 
		return iWarnMapper.selectwarnByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountwarnByParam(Map paramMap){ 
		return iWarnMapper.selectCountwarnByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatewarn(Warn warn){
		return iWarnMapper.updatewarn(warn);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addwarn(Warn warn){
		return iWarnMapper.addwarn(warn);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deletewarn(String id){
		return iWarnMapper.deletewarn(id);
	}

}

