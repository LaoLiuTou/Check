package com.check.service.bu;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.bu.IBuMapper;
import com.check.model.bu.Bu;
public class BuServiceImpl  implements IBuService {

	@Autowired
	private IBuMapper iBuMapper;
	/**
 * 通过id选取
 * @return
 */
	public Bu selectbuById(String id){
		return iBuMapper.selectbuById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Bu> selectbuByParam(Map paramMap){ 
		return iBuMapper.selectbuByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountbuByParam(Map paramMap){ 
		return iBuMapper.selectCountbuByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatebu(Bu bu){
		return iBuMapper.updatebu(bu);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addbu(Bu bu){
		return iBuMapper.addbu(bu);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deletebu(String id){
		return iBuMapper.deletebu(id);
	}

}

