package com.check.service.lot;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.lot.ILotMapper;
import com.check.model.lot.Lot;
public class LotServiceImpl  implements ILotService {

	@Autowired
	private ILotMapper iLotMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Lot selectlotById(String id){
		return iLotMapper.selectlotById(id);
	}

 /**
  * 通过snum排序
  * @return
  */ 
 @SuppressWarnings("rawtypes")
 @Transactional
 public List<Lot> selectlotByParamOrder(Map paramMap){ 
	 return iLotMapper.selectlotByParamOrder(paramMap);
 }
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Lot> selectlotByParam(Map paramMap){ 
		return iLotMapper.selectlotByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountlotByParam(Map paramMap){ 
		return iLotMapper.selectCountlotByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatelot(Lot lot){
		return iLotMapper.updatelot(lot);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addlot(Lot lot){
		return iLotMapper.addlot(lot);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletelot(String id){
		return iLotMapper.deletelot(id);
	}

}

