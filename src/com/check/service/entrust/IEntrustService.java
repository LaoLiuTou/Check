package com.check.service.entrust;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;

import com.check.model.entrust.Entrust;
import com.check.model.entrust_pin.Entrust_pin;
import com.check.model.entrust_sample.Entrust_sample;
import com.check.model.sample.Sample;
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
	 * 添加 流程
	 * @return
	 */ 
	public  int  addentrustFlow(Entrust entrust,String samples,String entrust_pins,String entrust_assets);
	
	
	/**
	 * 添加 流程
	 * @return
	 */ 
	public  int  addWTEntrustFlow(Entrust entrust,String samples,String entrust_pins,String entrust_assets);
	
	
	
	public  int updateentrustFlow(Entrust entrust,String p_status, String entrust_samples,String entrust_pins,String entrust_assets);
	 
	
	public  int updateWTEntrustFlow(Entrust entrust,String p_status, String entrust_samples,String entrust_pins,String entrust_assets);
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
	 * 审核委托单之后 新建Entrust_sample Test Results Entrust_pin   st_lv entrust_ids  
	 * @return 
	 */ 
	public  void  addRemoteFlowFunction(Entrust entrust,List<Entrust_sample> entrust_samples,List<Entrust_pin> entrust_pins);
	
	/**
	 * 样品 委托单 中间表
	 */
	public  List<String> seFlow(Entrust entrust,JSONObject sampleTree);
	
	
	/**
	 * 更新 
	 * @return 
	 */ 
	public  int mulupdateEntrust(List<Entrust> entrustList);
	
	 /**
	  * 修改远程委托单流程
	  * @return 
	  */ 
	 public  int updateRemoteEntrustFlow(Entrust entrust,String entrust_pins,String entrust_assets);
}

