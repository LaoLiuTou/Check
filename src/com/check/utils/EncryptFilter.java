package com.check.utils;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class EncryptFilter extends StrutsPrepareAndExecuteFilter {  
	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)  
			 throws IOException, ServletException {  
		 
		 
		 
		 	HttpServletRequest request = (HttpServletRequest) req; 
			HttpServletResponse  response = (HttpServletResponse) res; 
			try {
				   
				 
 
				  System.out.println(request.getRequestURI());
				  System.out.println(request.getServletPath());
				  
				     response.setCharacterEncoding("UTF-8"); 
					response.setContentType("text/html;charset=UTF-8"); 
				  //CORS跨域
				    response.setHeader("Access-Control-Allow-Origin", "*");  
				    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
				    response.setHeader("Access-Control-Max-Age", "3600");  
				    //response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
				    response.setHeader("Access-Control-Allow-Headers","x-requested-with,token,timesamp,sign");
				    //response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type，requesttype");
				    //chain.doFilter(req, res);  
				    //super.doFilter(req, res, chain);// 调用父类的方法  
				   
				    
				    String uri = request.getServletPath();
				    
				    //对请求的uri(即api)进行判断，如果是登录的uri则直接放行，如果是其他api则对sign进行验证操作
				    if( !uri.startsWith("/loginMembers") && !uri.startsWith("/appAdd") &&
				    		!uri.startsWith("/QRImages") && !uri.startsWith("/config")){
				        //从请求的url中取出token、时间戳、sign
				        String token = request.getHeader("token");
				        String timesamp = request.getHeader("timesamp");
				        String sign = request.getHeader("sign");
				        //System.out.println("token is :  " + token);
				        //System.out.println("timesamp is :  " + timesamp);
				        //System.out.println("sign is :  " + sign);
				      
				        //对token、timesamp 进行md5计算
				        String signMd5 = MD5Encryption.getEncryption(token + timesamp+"!QAZXSW@");
				        //System.out.println("signMd5 is :  " + signMd5);
				        
				        
				        if(sign!=null&&sign.toLowerCase().equals(signMd5.toLowerCase())){
				        	long removeTime = 60*60*1000;//失效时间
				        	String tokenTime = TokenUtils.get(token);
				        	
				        	if(tokenTime!=null){
				        		
				        		long timesampLong=Long.parseLong(timesamp);
				        		long tokenTimeLong=Long.parseLong(tokenTime);
				        		if((timesampLong-tokenTimeLong)>removeTime){
				        			//token过期
				        			TokenUtils.remove(token);
				        			StringBuffer msg = new StringBuffer("{\"state\":\"expire\",\"msg\":\"token不存在或已经销毁！\"}");
						        	response.getWriter().write(msg.toString()); 
				        		}
				        		else{
				        			 //签名通过
					                chain.doFilter(request, response);	
					                //更新token时间
					                TokenUtils.add(token, timesamp);
				        		}
				        	}
				        	else{
				        		//token对应时间为空 认为token不存在
				        		StringBuffer msg = new StringBuffer("{\"state\":\"expire\",\"msg\":\"token不存在或已经销毁！\"}");
					        	response.getWriter().write(msg.toString()); 
				        		
				        	}
				        	
				        	 
				        }else{
				            //签名不通过，向app后端发送错误信息，提示重新登录
				        	StringBuffer msg = new StringBuffer("{\"state\":\"mismatch\",\"msg\":\"签名错误！\"}");
				        	response.getWriter().write(msg.toString()); 
				        	
				        }  
				    }else{
				        //登录操作
				        chain.doFilter(request, response);
				    }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				StringBuffer msg = new StringBuffer("{\"state\":\"failure\",\"msg\":\"出现异常请重试！\"}");
	        	response.getWriter().write(msg.toString()); 
				e.printStackTrace();
			}
			
	 }  
  }  