package com.check.service.pact;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.pact.IPactMapper;
import com.check.model.atta.Atta;
import com.check.model.pact.Pact;
import com.check.service.atta.IAttaService;
public class PactServiceImpl  implements IPactService {

	@Autowired
	private IPactMapper iPactMapper;
	@Autowired
	private IAttaService iAttaService;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Pact selectpactById(String id){
		return iPactMapper.selectpactById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Pact> selectpactByParam(Map paramMap){ 
		return iPactMapper.selectpactByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountpactByParam(Map paramMap){ 
		return iPactMapper.selectCountpactByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatepact(Pact pact){
		return iPactMapper.updatepact(pact);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addpact(Pact pact){
		return iPactMapper.addpact(pact);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addPactAndAtta(Pact pact,String attastr){
		 
		int result=0;
		result=iPactMapper.addpact(pact);
		if(attastr!=null){
			
			JSONArray attaJA=JSONArray.fromObject(attastr);
			for(int i=0;i<attaJA.size();i++){
				JSONObject  attaJO = (JSONObject) attaJA.get(i);
				Atta atta = new Atta();
				if(attaJO.containsKey("c_id"))
				atta.setC_id(attaJO.getString("c_id"));
				//atta.setCm_tx(attaJO.getString("cm_tx"));
				if(attaJO.containsKey("nm_t"))
				atta.setNm_t(attaJO.getString("nm_t"));
				if(attaJO.containsKey("type"))
				atta.setType(attaJO.getString("type"));
				if(attaJO.containsKey("url"))
				atta.setUrl(attaJO.getString("url"));
				atta.setPid(pact.getId()+"");
				iAttaService.addatta(atta);
			}
			
		}
		 return result;
	}
	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletepact(String id){
		return iPactMapper.deletepact(id);
	}

}

