package com.check.utils;
 
import java.util.ArrayList;
import java.util.List;

public class CacheToRedis {

	
	 public static void  cache(List<String> tableList){
	    	//缓存
			
	        new MyTask(null,tableList).run();
	    	
	    }
	 
	 
	 public static void main(String[] args){
		 
		 List<String> tableList = new ArrayList<String>(); 
	        tableList.add("members");
	        CacheToRedis.cache(tableList);
	 }
}
