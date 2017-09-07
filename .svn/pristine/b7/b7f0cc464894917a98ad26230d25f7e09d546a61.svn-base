package com.check.utils;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.check.model.members.Members;
import com.check.service.accnt.IAccntService;
import com.check.service.area.IAreaService;
import com.check.service.auth_group.IAuth_groupService;
import com.check.service.auth_group_access.IAuth_group_accessService;
import com.check.service.auth_rule.IAuth_ruleService;
import com.check.service.bu.IBuService;
import com.check.service.lot.ILotService;
import com.check.service.lov.ILovService;
import com.check.service.members.IMembersService;
import com.check.service.pact.IPactService;
import com.check.service.pay.IPayService;
import com.check.service.prod.IProdService;
 
 



public class MyTask extends TimerTask { 
	 private static boolean isRunning = false;  
     private ServletContext context = null;  
      
 	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	 //总条数
 	 private int totalnumber;
 	 //缓存结果
 	 @SuppressWarnings("rawtypes")
	 private List  list;
 	 
 	 
	 private List<String> tableList;
     public MyTask(ServletContext servletContext,List<String> tableList) { 
         this.context = servletContext; 
         this.tableList = tableList; 
     } 

     @SuppressWarnings({ "rawtypes", "unchecked" })
	 @Override 
     public void run() { 
       if(!isRunning) 
       { 
    	 Logger logger = Logger.getLogger("CheckLogger");
         isRunning = true; 
         
         try {
			ApplicationContext ac = new ClassPathXmlApplicationContext(
			 "applicationContext.xml"); 
			IAccntService iAccntService = (IAccntService)ac.getBean("iAccntService");
			IAreaService iAreaService = (IAreaService)ac.getBean("iAreaService");
			IAuth_groupService iAuth_groupService = (IAuth_groupService)ac.getBean("iAuth_groupService");
			IAuth_group_accessService iAuth_group_accessService = (IAuth_group_accessService)ac.getBean("iAuth_group_accessService");
			IAuth_ruleService iAuth_ruleService = (IAuth_ruleService)ac.getBean("iAuth_ruleService");
			IBuService iBuService = (IBuService)ac.getBean("iBuService");
			ILotService iLotService = (ILotService)ac.getBean("iLotService");
			ILovService iLovService = (ILovService)ac.getBean("iLovService");
			IMembersService iMembersService = (IMembersService)ac.getBean("iMembersService");
			IPactService iPactService = (IPactService)ac.getBean("iPactService");
			IPayService iPayService = (IPayService)ac.getBean("iPayService");
			IProdService iProdService = (IProdService)ac.getBean("iProdService");
			
			//json config
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return value;  
				} 
			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) { 
				if(value instanceof Date){ 
					return sdf.format((Date)value);
				}
				return value; 
			}
			});
			Calendar cal = Calendar.getInstance();
	    	int year = cal.get(Calendar.YEAR);
			
			
			 for(String tableName : tableList){
				 Map  paramMap = new HashMap ();
				  
				 
				 switch (tableName) {
					case "accnt":
						paramMap = new HashMap (); 
						//paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iAccntService.selectCountaccntByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iAccntService.selectaccntByParam(paramMap); 
						logger.info("开始缓存accnt数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "accnt", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存accnt数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;
					case "area":
						paramMap = new HashMap (); 
						//paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iAreaService.selectCountareaByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iAreaService.selectareaByParam(paramMap); 
						logger.info("开始缓存area数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "area", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存area数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;
					case "auth_group":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iAuth_groupService.selectCountauth_groupByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iAuth_groupService.selectauth_groupByParam(paramMap); 
						logger.info("开始缓存auth_group数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "auth_group", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存auth_group数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;
					case "auth_group_access":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iAuth_group_accessService.selectCountauth_group_accessByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iAuth_group_accessService.selectauth_group_accessByParam(paramMap); 
						logger.info("开始缓存auth_group_access数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "auth_group_access", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存auth_group_access数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;
					case "auth_rule":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iAuth_ruleService.selectCountauth_ruleByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iAuth_ruleService.selectauth_ruleByParam(paramMap); 
						logger.info("开始缓存auth_rule数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "auth_rule", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存auth_rule数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;
					case "bu":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iBuService.selectCountbuByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iBuService.selectbuByParam(paramMap); 
						logger.info("开始缓存bu数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "bu", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存bu数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					case "lot":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iLotService.selectCountlotByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iLotService.selectlotByParam(paramMap); 
						logger.info("开始缓存lot数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "lot", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存lot数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					case "lov":
						paramMap = new HashMap (); 
						//paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iLovService.selectCountlovByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iLovService.selectlovByParam(paramMap); 
						logger.info("开始缓存lov数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "lov", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存lov数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					case "members":
						List<Members> memberslist= new ArrayList<Members>();
						paramMap = new HashMap (); 
						//paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iMembersService.selectCountmembersByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						memberslist=iMembersService.selectmembersByParam(paramMap); 
						for(int i=0;i<memberslist.size();i++){
							memberslist.get(i).setUsername("");
							memberslist.get(i).setUserpwd("");
						}
						logger.info("开始缓存members数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "members", JSONArray.fromObject(memberslist, jsonConfig).toString());
						logger.info("结束缓存members数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					case "pact":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iPactService.selectCountpactByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iPactService.selectpactByParam(paramMap); 
						logger.info("开始缓存pact数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "pact", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存pact数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					case "pay":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iPayService.selectCountpayByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iPayService.selectpayByParam(paramMap); 
						logger.info("开始缓存pay数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "pay", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存pay数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					case "prod":
						paramMap = new HashMap (); 
						paramMap.put("c_dtFrom", year+"-01-01 00:00:00");
						totalnumber=iProdService.selectCountprodByParam(paramMap);
						paramMap.put("fromPage",0);
						paramMap.put("toPage",totalnumber);
						list=iProdService.selectprodByParam(paramMap); 
						logger.info("开始缓存prod数据："+sdf.format(new Date()));
						RedisUtil.setObject("JC", "prod", JSONArray.fromObject(list, jsonConfig).toString());
						logger.info("结束缓存prod数据："+sdf.format(new Date())+",数据条数："+totalnumber);
						break;	 
					 

						
					default:
						break;
				}
				 
				 
				 
			 }


			
			
 
			  
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
 		
         
         isRunning = false; 
       } 
       else 
       { 
           context.log("上次的任务还未执行完成"); 
       } 
     } 
      
}
