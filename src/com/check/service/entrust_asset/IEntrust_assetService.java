package com.check.service.entrust_asset;
import java.util.List;
import java.util.Map;
import com.check.model.entrust_asset.Entrust_asset;
public interface IEntrust_assetService {
	/**
 * 通过id选取
 * @return
 */
	public Entrust_asset selectentrust_assetById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Entrust_asset> selectentrust_assetByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountentrust_assetByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateentrust_asset(Entrust_asset entrust_asset);
	/**
 * 添加 
 * @return
 */ 
	public  Object addentrust_asset(Entrust_asset entrust_asset);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteentrust_asset(String id);

}

