import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.check.model.results.Results;
import com.check.model.test.Test;
import com.check.service.results.IResultsService;
import com.check.service.test.ITestService;


public class LT {

	/**
	 * @param args
	 * @throws ScriptException 
	 */
	public static void main(String[] args) throws ScriptException {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				 "applicationContext.xml"); 
		ITestService iTestService = (ITestService) context.getBean("iTestService");
		IResultsService iResultsService = (IResultsService) context.getBean("iResultsService");
   	 
		String statand_id = null;
		String id="7";
		List<String> templist = new ArrayList<String>();
		if(statand_id!=null&&!statand_id.equals("")){
			Results pResult=iResultsService.selectresultsById(id);
			templist.add("\""+pResult.getId()+"\""+":"+"\""+pResult.getBool()+"\"");
			Map paramMap= new HashMap();
			paramMap.put("rel_id", pResult.getId()+"");
			int sum= iResultsService.selectCountresultsByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",sum); 
			List<Results> subList= iResultsService.selectresultsByParam(paramMap);
			for(Results subResult:subList){
				templist.add("\""+subResult.getId()+"\""+":"+"\""+subResult.getBool()+"\"");
			}
			
		}
		else{
			
			Results cResult=iResultsService.selectresultsById(id);
			if(cResult.getRel_id()!=null){
				Results pResult=iResultsService.selectresultsById(cResult.getRel_id()+"");
				templist.add("\""+pResult.getId()+"\""+":"+"\""+pResult.getBool()+"\"");
				Map paramMap= new HashMap();
				paramMap.put("rel_id", pResult.getId()+"");
				int sum= iResultsService.selectCountresultsByParam(paramMap);
				paramMap.put("fromPage",0);
				paramMap.put("toPage",sum); 
				List<Results> subList= iResultsService.selectresultsByParam(paramMap);
				for(Results subResult:subList){
					templist.add("\""+subResult.getId()+"\""+":"+"\""+subResult.getBool()+"\"");
				}
			}
			
		}
	 
		
		System.out.println(StringUtils.join(templist,","));
		/*Test test = new Test();
		test.setPid(1+"");
		 test.setStatus("已完成");
		 iTestService.updatetestbypid(test);*/
		 
		 /*Map paramMap = new HashMap();
			paramMap.put("pid", 1);
			paramMap.put("sum_status", "('待审批','已取消')");
			 //iTestService.selectCounttestByParam(paramMap);
			 System.out.println(iTestService.selectCounttestByParam(paramMap));*/
		//System.out.println(String.format("%04d",2));
		//System.out.println((i+"").equals(""));
		 
		/*try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    GregorianCalendar gc=new GregorianCalendar();
			 gc.setTime(sdf.parse("2017-09-28 09:02:50"));
			 gc.add(5, Integer.parseInt(3+""));
			 System.out.println(sdf.format(gc.getTime()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date n_yh_dt=sdf.parse("2017-10-28 09:02:50");
				do{
					GregorianCalendar gc=new GregorianCalendar();
		            gc.setTime(n_yh_dt);
		            gc.add(5, Integer.parseInt(3+"")); 
		            n_yh_dt=gc.getTime();
		            //System.out.println(n_yh_dt.before(new Date()));
		            //entrust.setJh_dt(sdf.format(gc.getTime()));
				}
				while(n_yh_dt.before(new Date()));
				System.out.println(sdf.format(n_yh_dt));
				 
			 
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        */
		/*List<String> gg_code=new ArrayList<String>();
		List<String> sy_code=new ArrayList<String>();
		gg_code.add("11111111");
		gg_code.add("22222222");
		gg_code.add("33333333");
		gg_code.add("44444444");
		
		sy_code.add("11111111");
		sy_code.add("22222222");
		sy_code.add("33333333");
		sy_code.add("44444444");
		//更新委托单字段gg_code、sy_code
		 String gg_codeStr= StringUtils.join(gg_code," ");
		 String sy_codeStr= "";
		for(String partStr :sy_code){
			if(sy_code.indexOf(partStr)==0){
				sy_codeStr+=partStr+"、";
				
			}
			else{
				if(partStr.length()>5){
					sy_codeStr+=partStr.substring(5,partStr.length()-1)+"、";
				}
			}
			
		}
		if(sy_codeStr.length()>0){
			sy_codeStr=sy_codeStr.substring(0, sy_codeStr.length()-1);
		}
		System.out.println(gg_codeStr);
		System.out.println(sy_codeStr);*/
		/*String boolStr = "(123==1223)";  
		ScriptEngineManager manager = new ScriptEngineManager();  
		ScriptEngine engine = manager.getEngineByName("js");  
		//engine.put("a", 4);  
		Object flagOB = engine.eval(boolStr);  
		System.out.println(flagOB.toString());*/
		/*
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);	
		System.out.println((year+"").substring(2,4));
		String currentNum = "12345678".substring(4,8);
		int newNum = Integer.parseInt(currentNum)+1;
		 
		System.out.println("FK"+17+newNum);*/
	}

}
