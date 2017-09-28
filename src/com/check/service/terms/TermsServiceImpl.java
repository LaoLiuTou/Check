package com.check.service.terms;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.terms.ITermsMapper;
import com.check.model.terms.Terms;
public class TermsServiceImpl  implements ITermsService {

	@Autowired
	private ITermsMapper iTermsMapper;
	/**
 * 通过id选取
 * @return
 */
	public Terms selecttermsById(String id){
		return iTermsMapper.selecttermsById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Terms> selecttermsByParam(Map paramMap){ 
		return iTermsMapper.selecttermsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCounttermsByParam(Map paramMap){ 
		return iTermsMapper.selectCounttermsByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateterms(Terms terms){
		return iTermsMapper.updateterms(terms);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addterms(Terms terms){
		return iTermsMapper.addterms(terms);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteterms(String id){
		return iTermsMapper.deleteterms(id);
	}

}

