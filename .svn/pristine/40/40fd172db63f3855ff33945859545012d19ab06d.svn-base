package com.check.service.entrust;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.check.model.entrust.Entrust;
public interface IEntrustService {
	/**
 * 通过id选取
 * @return
 */
	public Entrust selectentrustById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Entrust> selectentrustByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountentrustByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updateentrust(Entrust entrust);
	/**
 * 添加 
 * @return
 */ 
	public  Object addentrust(Entrust entrust);
	/**
 * 删除 
 * @return 
 */ 
	public  int deleteentrust(String id);

	/**
	 * Flow
	 * @param entrust
	 * @param accnt
	 * @param pact
	 * @param sample
	 * @param entrust_sample
	 * @param test
	 * @param results
	 * @return
	 */
	public  int entrustFlow(Entrust entrust,String accnt,String pact,String sample,String entrust_sample,
			String test,String results);
	/**
	 * 样品 委托单 中间表
	 */
	public  List<String> seFlow(Entrust entrust,JSONObject sampleTree);
}

