package com.check.service.asset;
import java.util.List;
import java.util.Map;
import com.check.model.asset.Asset;
public interface IAssetService {
	/**
 * 通过id选取
 * @return
 */
	public Asset selectassetById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Asset> selectassetByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountassetByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateasset(Asset asset);
	/**
 * 添加 
 * @return
 */ 
	public  Object addasset(Asset asset);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteasset(String id);

}

