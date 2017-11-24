package com.check.service.prod_asset;
import java.util.List;
import java.util.Map;
import com.check.model.prod_asset.Prod_asset;
public interface IProd_assetService {
	/**
 * 通过id选取
 * @return
 */
	public Prod_asset selectprod_assetById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Prod_asset> selectprod_assetByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountprod_assetByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateprod_asset(Prod_asset prod_asset);
	/**
 * 添加 
 * @return
 */ 
	public  Object addprod_asset(Prod_asset prod_asset);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteprod_asset(String id);

}

