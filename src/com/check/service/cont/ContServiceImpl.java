package com.check.service.cont;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.cont.IContMapper;
import com.check.model.cont.Cont;
public class ContServiceImpl  implements IContService {

	@Autowired
	private IContMapper iContMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Cont selectcontById(String id){
		return iContMapper.selectcontById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Cont> selectcontByParam(Map paramMap){ 
		return iContMapper.selectcontByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountcontByParam(Map paramMap){ 
		return iContMapper.selectCountcontByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatecont(Cont cont){
		return iContMapper.updatecont(cont);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addcont(Cont cont){
		return iContMapper.addcont(cont);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletecont(String id){
		return iContMapper.deletecont(id);
	}

}

