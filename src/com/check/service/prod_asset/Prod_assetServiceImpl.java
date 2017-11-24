package com.check.service.prod_asset;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.prod_asset.IProd_assetMapper;
import com.check.model.prod_asset.Prod_asset;
public class Prod_assetServiceImpl  implements IProd_assetService {

	@Autowired
	private IProd_assetMapper iProd_assetMapper;
	/**
 * 通过id选取
 * @return
 */
	public Prod_asset selectprod_assetById(String id){
		return iProd_assetMapper.selectprod_assetById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Prod_asset> selectprod_assetByParam(Map paramMap){ 
		return iProd_assetMapper.selectprod_assetByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountprod_assetByParam(Map paramMap){ 
		return iProd_assetMapper.selectCountprod_assetByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateprod_asset(Prod_asset prod_asset){
		return iProd_assetMapper.updateprod_asset(prod_asset);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addprod_asset(Prod_asset prod_asset){
		return iProd_assetMapper.addprod_asset(prod_asset);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteprod_asset(String id){
		return iProd_assetMapper.deleteprod_asset(id);
	}

}

