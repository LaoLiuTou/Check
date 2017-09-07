package com.check.utils;

public class CodeUtils {

	
	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
    public static String autoGenericCode(String code, int num) {
        String result = "";
        // 保留num的位数
        // 0 代表前面补充0     
        // num 代表长度为4     
        // d 代表参数为正数型 
        result = String.format("%0" + num + "d", Integer.parseInt(code) + 1);

        return result;
    }
    
    
    public static void main(String[] args){
    	String lovSort="2017043";
    	
    	int sortNum=Integer.valueOf(lovSort.substring(4,7)).intValue();
    	System.out.println(sortNum);
		String newSort = CodeUtils.autoGenericCode(sortNum+"",3);
		System.out.println(newSort);
    }
    
    
    
}
