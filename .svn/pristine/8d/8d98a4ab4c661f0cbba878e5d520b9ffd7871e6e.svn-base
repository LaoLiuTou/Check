package com.check.service.asset;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.asset.IAssetMapper;
import com.check.model.asset.Asset;
public class AssetServiceImpl  implements IAssetService {

	@Autowired
	private IAssetMapper iAssetMapper;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Asset selectassetById(String id){
		return iAssetMapper.selectassetById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Asset> selectassetByParam(Map paramMap){ 
		return iAssetMapper.selectassetByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountassetByParam(Map paramMap){ 
		return iAssetMapper.selectCountassetByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateasset(Asset asset){
		return iAssetMapper.updateasset(asset);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addasset(Asset asset){
		return iAssetMapper.addasset(asset);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteasset(String id){
		return iAssetMapper.deleteasset(id);
	}

}

