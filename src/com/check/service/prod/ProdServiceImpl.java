package com.check.service.prod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.prod.IProdMapper;
import com.check.model.prod.Prod;
import com.check.model.prod_term.Prod_term;
import com.check.model.sample_templet.Sample_templet;
import com.check.service.prod_term.IProd_termService;
import com.check.service.sample_templet.ISample_templetService;
public class ProdServiceImpl  implements IProdService {

	@Autowired
	private IProdMapper iProdMapper;
	@Autowired
	private ISample_templetService iSample_templetService;
	@Autowired
	private IProd_termService iProd_termService;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Prod selectprodById(String id){
		return iProdMapper.selectprodById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Prod> selectprodByParam(Map paramMap){ 
		return iProdMapper.selectprodByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountprodByParam(Map paramMap){ 
		return iProdMapper.selectCountprodByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateprod(Prod prod){
		return iProdMapper.updateprod(prod);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addprod(Prod prod){
		return iProdMapper.addprod(prod);
	}
 /**
  * 添加流程 
  * @return
  */ 
 @Transactional
 public  Object flowprod(Prod prod,String muladd,String termids){
	 int result = 0;
	 result=iProdMapper.addprod(prod);
	 
	 ///1111111111
     JSONArray addJA=JSONArray.fromObject(muladd);
	 List<Sample_templet> tempList= new ArrayList<Sample_templet>();
	 for(int i=0;i<addJA.size();i++){
		JSONObject  addJO = (JSONObject) addJA.get(i);
		Sample_templet sample_templet =new Sample_templet(); 
		 
		if(addJO.containsKey("show_flg"))
			sample_templet.setShow_flg(addJO.getString("show_flg"));
		if(addJO.containsKey("sort"))
			sample_templet.setSort(Long.parseLong(addJO.getString("sort")));
		if(addJO.containsKey("lov_id"))
			sample_templet.setLov_id(Long.parseLong(addJO.getString("lov_id")));
		//if(addJO.containsKey("prod_id"))
			sample_templet.setProd_id(prod.getId());
		tempList.add(sample_templet);
	 }
	 iSample_templetService.muladdsample_templet(tempList);
		
	 ////////////222222
	 List<Prod_term> ptList = new ArrayList<Prod_term>();
		String[] terms=termids.split("\\|");
		for(int i=0;i<terms.length;i++){
			Prod_term prod_term =new Prod_term(); 
			prod_term.setProd_id(prod.getId()+"");
			prod_term.setTerm_id(terms[i]);
			ptList.add(prod_term);
	 }
	 iProd_termService.deleteprod_termbyprod(prod.getId()+"",ptList);
		
		
	 
	 return result;
 }

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteprod(String id){
		return iProdMapper.deleteprod(id);
	}

}

