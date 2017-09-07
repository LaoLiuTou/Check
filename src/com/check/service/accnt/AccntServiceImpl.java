package com.check.service.accnt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.accnt.IAccntMapper;
import com.check.model.accnt.Accnt;
import com.check.model.atta.Atta;
import com.check.model.cont.Cont;
import com.check.service.atta.IAttaService;
import com.check.service.cont.IContService;
public class AccntServiceImpl  implements IAccntService {

	@Autowired
	private IAccntMapper iAccntMapper;
	//@Autowired
	//private IAccntService iAccntService;
	@Autowired
	private IContService iContService;
	@Autowired
	private IAttaService iAttaService;
	/**
 * 通过id选取
 * @return
 */
 @Transactional
	public Accnt selectaccntById(String id){
		return iAccntMapper.selectaccntById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public List<Accnt> selectaccntByParam(Map paramMap){ 
		return iAccntMapper.selectaccntByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
 @Transactional
	public int selectCountaccntByParam(Map paramMap){ 
		return iAccntMapper.selectCountaccntByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updateaccnt(Accnt accnt){
		return iAccntMapper.updateaccnt(accnt);
	}
 /**
  * 更新 all
  * @return 
  */ 
 @SuppressWarnings({ "rawtypes", "unchecked" })
@Transactional
 public  int updateallaccnt(Accnt accnt,String contstr,String attastr){
	 int result = 0;
	 result= iAccntMapper.updateaccnt(accnt);
	 	if(contstr!=null){
			Map  paramMap = new HashMap ();
			paramMap.put("pid",accnt.getId()); 
			int count= iContService.selectCountcontByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",count); 
			List<Cont> contList= iContService.selectcontByParam(paramMap);
			
			List<Cont>  copyList = new ArrayList<Cont>();
			copyList.addAll(contList);
			JSONArray contJA=JSONArray.fromObject(contstr);
			for(int i=0;i<contJA.size();i++){
				JSONObject  contJO = (JSONObject) contJA.get(i);
				//循环 后期优化掉
				for(Cont temp:contList){
					if(temp.getId().toString().equals(contJO.getString("id"))){
						copyList.remove(temp);
					}
				}
				
				Cont cont = new Cont();
				//cont.setRow_id(contJO.getString("row_id"));
				if(contJO.containsKey("c_id"))
				cont.setC_id(contJO.getString("c_id"));
				if(contJO.containsKey("nm_t"))
				cont.setNm_t(contJO.getString("nm_t"));
				//cont.setJob(contJO.getString("job"));
				//cont.setSex(contJO.getString("sex"));
				if(contJO.containsKey("ph_p"))
				cont.setPh_p(contJO.getString("ph_p"));
				//cont.setQq(contJO.getString("qq")); 
				if(contJO.containsKey("bu_id"))
				cont.setBu_id(Long.parseLong(contJO.getString("bu_id")));
				cont.setPid(accnt.getId());
				if(!contJO.getString("id").equals(""))
				cont.setId(Long.parseLong(contJO.getString("id")));
				//id 为空新建 否则修改
				if(cont.getId()==null){
					iContService.addcont(cont); 
				}
				else{
					iContService.updatecont(cont);
				}
				
				//flag   =  Y/N
				if(contJO.getString("flag").equals("Y")){
					Accnt tempAccnt = new Accnt();
					tempAccnt.setCont_id(cont.getId()+"");
					tempAccnt.setId(accnt.getId());
					tempAccnt.setUp_dt(null);
					iAccntMapper.updateaccnt(tempAccnt);
				}
				
			}
			//没有对应数据删除
			for(Cont temp:copyList){
				temp.setPid(Long.valueOf("-1"));
				temp.setUp_dt(null);
				iContService.updatecont(temp);
			}
			
			
		}
		
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
				atta.setPid(accnt.getId()+"");
				iAttaService.addatta(atta);
			}
			
		}
		
		
		
	 return result;
 }

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object addaccnt(Accnt accnt){
		return iAccntMapper.addaccnt(accnt);
	}

 
 /**
  * 添加 all
  * @return
  */ 
 @Transactional
 	public  int addallaccnt(Accnt accnt,String contstr,String attastr){
 	 	int result=0;
 	 	try {
			result=iAccntMapper.addaccnt(accnt);
			if(result>0){
				if(contstr!=null){
					
					JSONArray contJA=JSONArray.fromObject(contstr);
					for(int i=0;i<contJA.size();i++){
						JSONObject  contJO = (JSONObject) contJA.get(i);
						
						Cont cont = new Cont();
						//cont.setRow_id(contJO.getString("row_id"));
						if(accnt.getC_id()!=null)
						cont.setC_id(accnt.getC_id());
						if(contJO.containsKey("nm_t"))
						cont.setNm_t(contJO.getString("nm_t"));
						//cont.setJob(contJO.getString("job"));
						//cont.setSex(contJO.getString("sex"));
						if(contJO.containsKey("ph_p"))
						cont.setPh_p(contJO.getString("ph_p"));
						//cont.setQq(contJO.getString("qq")); 
						if(accnt.getBu_id()!=null)
						cont.setBu_id(Long.parseLong(accnt.getBu_id()));
						cont.setPid(accnt.getId());
						iContService.addcont(cont); 
						//flag   =  Y/N
						//flag   =  Y/N
						if(contJO.getString("flag").equals("Y")){
							Accnt tempAccnt = new Accnt();
							tempAccnt.setCont_id(cont.getId()+"");
							tempAccnt.setId(accnt.getId());
							tempAccnt.setUp_dt(null);
							iAccntMapper.updateaccnt(tempAccnt);
						}
						
					}
					
					
				}
				
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
						atta.setPid(accnt.getId()+"");
						iAttaService.addatta(atta);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e); 
		}
 		return result;
 	}
  
  
	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deleteaccnt(String id){
		return iAccntMapper.deleteaccnt(id);
	}
 /**
  * 当前时间
  * @return 
  */ 
 @Transactional
 public  String selectDbtime(){
	 return iAccntMapper.selectDbtime();
 }

}

