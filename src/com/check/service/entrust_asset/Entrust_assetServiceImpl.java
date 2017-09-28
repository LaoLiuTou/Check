package com.check.service.entrust_asset;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.entrust_asset.IEntrust_assetMapper;
import com.check.model.entrust_asset.Entrust_asset;
public class Entrust_assetServiceImpl  implements IEntrust_assetService {

	@Autowired
	private IEntrust_assetMapper iEntrust_assetMapper;
	/**
 * 通过id选取
 * @return
 */
	public Entrust_asset selectentrust_assetById(String id){
		return iEntrust_assetMapper.selectentrust_assetById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Entrust_asset> selectentrust_assetByParam(Map paramMap){ 
		return iEntrust_assetMapper.selectentrust_assetByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountentrust_assetByParam(Map paramMap){ 
		return iEntrust_assetMapper.selectCountentrust_assetByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updateentrust_asset(Entrust_asset entrust_asset){
		return iEntrust_assetMapper.updateentrust_asset(entrust_asset);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addentrust_asset(Entrust_asset entrust_asset){
		return iEntrust_assetMapper.addentrust_asset(entrust_asset);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deleteentrust_asset(String id){
		return iEntrust_assetMapper.deleteentrust_asset(id);
	}

}

