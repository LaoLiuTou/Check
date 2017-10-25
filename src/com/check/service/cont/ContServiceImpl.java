package com.check.service.cont;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.dao.bu.IBuMapper;
import com.check.dao.cont.IContMapper;
import com.check.dao.members.IMembersMapper;
import com.check.model.bu.Bu;
import com.check.model.cont.Cont;
import com.check.model.members.Members;
import com.check.utils.MD5Encryption;
public class ContServiceImpl  implements IContService {

	@Autowired
	private IContMapper iContMapper;
	@Autowired
	private IMembersMapper iMembersMapper;
	@Autowired
	private IBuMapper iBuMapper;
	/**
 * 通过id选取
 * @return
 */
	public Cont selectcontById(String id){
		return iContMapper.selectcontById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Cont> selectcontByParam(Map paramMap){ 
		return iContMapper.selectcontByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountcontByParam(Map paramMap){ 
		return iContMapper.selectCountcontByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
	public  int updatecont(Cont cont,String username){
		if(username!=null&&!username.equals("")){
			if(cont.getSt_lv()!=null&&cont.getSt_lv().equals("通过")){
				//新建members 
				/*username/password
				cont.bu_id
				resp  bu表+委托方负责人
				type  委托方
				cont.setUser_id(members.id);*/
				Bu bu = iBuMapper.selectbuById(cont.getBu_id()+"");
				Members members =new Members(); 
				members.setUsername(username);
				members.setUserpwd(MD5Encryption.getEncryption(username));
				members.setResp(bu.getLoc()+"_委托方负责人");
				members.setType("委托方");
				members.setBu_id(cont.getBu_id());
			    int resultM = iMembersMapper.addmembers(members);
			    if(resultM>0){
			    	cont.setUser_id(members.getId());
			    }
			}
		}
		
			
		
		
		return iContMapper.updatecont(cont);
	}

	/**
 * 添加 
 * @return
 */ 
	public  Object addcont(Cont cont){
		return iContMapper.addcont(cont);
	}

	/**
 * 删除 
 * @return 
 */ 
	public  int deletecont(String id){
		return iContMapper.deletecont(id);
	}

}

