package com.check.service.prod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
 public  Object addflowprod(Prod prod,String muladd,String termids,String subprod){
	 int result = 0;
	 result=iProdMapper.addprod(prod);
	 
	 if(subprod!=null&&!subprod.equals("")){
		 //////////3333333
		 JSONArray subprodJA=JSONArray.fromObject(subprod);
		 List<Prod> tempprod= new ArrayList<Prod>();
		 for(int i=0;i<subprodJA.size();i++){
			JSONObject  addJO = (JSONObject) subprodJA.get(i);
			Prod prodTemp =new Prod(); 
			
			if(addJO.containsKey("fj_f"))
				prodTemp.setFj_f(addJO.getString("fj_f"));
			if(addJO.containsKey("nm_t"))
				prodTemp.setNm_t(addJO.getString("nm_t"));
			if(addJO.containsKey("ty_lv"))
				prodTemp.setTy_lv(addJO.getString("ty_lv"));
			if(addJO.containsKey("jy_f"))
				prodTemp.setJy_f(addJO.getString("jy_f"));
			if(addJO.containsKey("bu_id"))
				prodTemp.setBu_id(addJO.getString("bu_id"));
			if(addJO.containsKey("c_id"))
				prodTemp.setC_id(addJO.getString("c_id"));
			//if(addJO.containsKey("prod_id"))
			prodTemp.setPid(prod.getId()+"");
			tempprod.add(prodTemp);
		 } 
		 if(tempprod.size()>0)
		 iProdMapper.muladdprod(tempprod);
	 }
	 else{
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
		 if(tempList.size()>0)
		 iSample_templetService.muladdsample_templet(tempList);
			
		 ////////////222222
		 List<Prod_term> ptList = new ArrayList<Prod_term>();
		 if(termids!=null&&!termids.equals("")){
			String[] terms=termids.split("\\|");
			for(int i=0;i<terms.length;i++){
				Prod_term prod_term =new Prod_term(); 
				prod_term.setProd_id(prod.getId()+"");
				prod_term.setTerm_id(terms[i]);
				ptList.add(prod_term);
			 }
		 }
		 iProd_termService.deleteprod_termbyprod(prod.getId()+"",ptList);
		
		 

	 }
	
		
	 
	 return result;
 }
 /**
  * 添加流程 
  * @return
  */ 
 @Transactional
 public  Object updateflowprod(Prod prod,String muladd,String termids,String subprod){
	 int result = 0;
	 result=iProdMapper.updateprod(prod);
	 prod = iProdMapper.selectprodById(prod.getId()+"");
	 if(subprod!=null&&!subprod.equals("")){
		 //////////3333333
		 JSONArray subprodJA=JSONArray.fromObject(subprod);
		 List<Prod> tempprod= new ArrayList<Prod>();
		 List<Prod> addprod= new ArrayList<Prod>();
		 for(int i=0;i<subprodJA.size();i++){
			JSONObject  addJO = (JSONObject) subprodJA.get(i);
			Prod prodTemp =new Prod(); 
			 
			if(addJO.containsKey("fj_f"))
				prodTemp.setFj_f(addJO.getString("fj_f"));
			if(addJO.containsKey("nm_t"))
				prodTemp.setNm_t(addJO.getString("nm_t"));
			if(addJO.containsKey("ty_lv"))
				prodTemp.setTy_lv(addJO.getString("ty_lv"));
			if(addJO.containsKey("jy_f"))
				prodTemp.setJy_f(addJO.getString("jy_f"));
			if(addJO.containsKey("id"))
				prodTemp.setId(Long.parseLong(addJO.getString("id")));
			if(addJO.containsKey("flg"))
				prodTemp.setFlg(addJO.getString("flg"));
			if(addJO.containsKey("bu_id"))
				prodTemp.setBu_id(addJO.getString("bu_id"));
			if(addJO.containsKey("c_id"))
				prodTemp.setC_id(addJO.getString("c_id"));
			//if(addJO.containsKey("prod_id"))
			//prodTemp.setPid(prod.getId()+"");
			if(addJO.containsKey("id")){
				tempprod.add(prodTemp);
			}
			else{
				prodTemp.setPid(prod.getId()+"");
				addprod.add(prodTemp);
			}
			
		 } 
		 if(addprod.size()>0)
		 iProdMapper.muladdprod(addprod);
		 if(tempprod.size()>0)
		 iProdMapper.mulupdateprod(tempprod);
	 }
	 else{
		///1111111111
		 JSONArray addJA=JSONArray.fromObject(muladd);
		 List<Sample_templet> tempList= new ArrayList<Sample_templet>();
		 for(int i=0;i<addJA.size();i++){
			 JSONObject  updateJO = (JSONObject) addJA.get(i);
			 Sample_templet sample_templet =new Sample_templet(); 
			 
			 if(updateJO.containsKey("id"))
				sample_templet.setId(Long.parseLong(updateJO.getString("id")));
			 if(updateJO.containsKey("show_flg"))
				sample_templet.setShow_flg(updateJO.getString("show_flg"));
			 if(updateJO.containsKey("sort"))
				sample_templet.setSort(Long.parseLong(updateJO.getString("sort")));
			 if(updateJO.containsKey("lov_id"))
				sample_templet.setLov_id(Long.parseLong(updateJO.getString("lov_id")));
			 //if(addJO.containsKey("prod_id"))
			 sample_templet.setProd_id(prod.getId());
			 tempList.add(sample_templet);
		 }
		 if(tempList.size()>0)
		 iSample_templetService.mulupdatesample_templet(tempList);
		 ////////////222222
		 List<Prod_term> ptList = new ArrayList<Prod_term>();
		 if(termids!=null&&!termids.equals("")){
			 String[] terms=termids.split("\\|");
			 for(int i=0;i<terms.length;i++){
				 Prod_term prod_term =new Prod_term(); 
				 prod_term.setProd_id(prod.getId()+"");
				 prod_term.setTerm_id(terms[i]);
				 ptList.add(prod_term);
			 }
		 }
		 iProd_termService.deleteprod_termbyprod(prod.getId()+"",ptList);
		 
	 }
	 
	 
	 
	  
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

