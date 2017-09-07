package com.check.service.lot;
import java.util.List;
import java.util.Map;
import com.check.model.lot.Lot;
public interface ILotService {
	/**
 * 通过id选取
 * @return
 */
	public Lot selectlotById(String id);
	/**
	 * 通过snum排序
	 * @return
	 */ 
	@SuppressWarnings("rawtypes")
	public List<Lot> selectlotByParamOrder(Map paramMap); 
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Lot> selectlotByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountlotByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatelot(Lot lot);
	/**
 * 添加 
 * @return
 */ 
	public  Object addlot(Lot lot);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletelot(String id);

}

