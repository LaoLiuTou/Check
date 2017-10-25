package com.check.service.cont;
import java.util.List;
import java.util.Map;
import com.check.model.cont.Cont;
public interface IContService {
	/**
 * 通过id选取
 * @return
 */
	public Cont selectcontById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Cont> selectcontByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountcontByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatecont(Cont cont,String username);
	/**
 * 添加 
 * @return
 */ 
	public  Object addcont(Cont cont);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletecont(String id);

}

