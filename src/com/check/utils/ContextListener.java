package com.check.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 

public class ContextListener implements ServletContextListener {

	 Timer timer = new Timer() ; 
	 

    public void contextInitialized(ServletContextEvent event) { 
        timer = new java.util.Timer(true);  
      
        
        event.getServletContext().log("定时器已启动"); 
        System.out.println("定时器已启动");
        List<String> tableList = new ArrayList<String>();
      
        tableList.add("prod");
        tableList.add("pay");
        tableList.add("pact");
        tableList.add("members");
        tableList.add("lov");
        tableList.add("lot");
        tableList.add("bu");
        tableList.add("auth_rule");
        tableList.add("auth_group_access");
        tableList.add("auth_group");
        tableList.add("area");
        tableList.add("accnt"); 
        timer.schedule(new MyTask(event.getServletContext(),tableList), 1000);  
        //new MyTask(event.getServletContext(),tableList).run();
       // event.getServletContext().log("已经添加任务调度表");  

    } 
    public void contextDestroyed(ServletContextEvent event) { 
        timer.cancel(); 
        event.getServletContext().log("定时器以销毁"); 
    } 
}
