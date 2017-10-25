import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;


public class LT {

	/**
	 * @param args
	 * @throws ScriptException 
	 */
	public static void main(String[] args) throws ScriptException {
		// TODO Auto-generated method stub
		//int i = 0;
		 
		//System.out.println((i+"").equals(""));
		 
		 /*try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    GregorianCalendar gc=new GregorianCalendar();
			 gc.setTime(sdf.parse("2017-09-28 09:02:50"));
			 gc.add(5, Integer.parseInt(3+""));
			 System.out.println(sdf.format(gc.getTime()));
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
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);	
		System.out.println((year+"").substring(2,4));
		String currentNum = "12345678".substring(4,8);
		int newNum = Integer.parseInt(currentNum)+1;
		 
		System.out.println("FK"+17+newNum);
	}

}
