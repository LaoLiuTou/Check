package com.check.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.check.model.accnt.Accnt;
import com.check.model.lot.Lot;
import com.check.model.lov.Lov;
import com.check.model.pact.Pact;
import com.check.model.prod.Prod;
import com.check.model.sample.Sample;
import com.check.service.accnt.IAccntService;
import com.check.service.lot.ILotService;
import com.check.service.lov.ILovService;
import com.check.service.pact.IPactService;
import com.check.service.prod.IProdService;
import com.check.service.sample.ISampleService;
import com.check.utils.CodeUtils;
import com.check.utils.ExcelUtil;
import com.check.utils.MatrixToImageWriter;
/**
 * @author LT
 */
public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ISampleService iSampleService;
	private IProdService iProdService;
	private IAccntService iAccntService;
	private ILovService iLovService;
	private ILotService iLotService;
	private IPactService iPactService;
	private HashMap<String, String> paramMap;
	protected void service(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				 "applicationContext.xml"); 
		iSampleService = (ISampleService) context.getBean("iSampleService");
		iProdService = (IProdService) context.getBean("iProdService");
		iAccntService = (IAccntService) context.getBean("iAccntService");
		iLovService = (ILovService) context.getBean("iLovService");
		iLotService = (ILotService) context.getBean("iLotService");
		iPactService = (IPactService) context.getBean("iPactService");
		
		
		
		//非file类型参数
		paramMap = new HashMap<String, String>();  
		//跨域
		String callback = request.getParameter("callback");
		 
		String fullPath = "";
		JSONObject resultJO=new JSONObject();
		request.setCharacterEncoding("UTF-8"); // 设置处理请求参数的编码格式
		response.setContentType("text/html;charset=UTF-8"); // 设置Content-Type字段值
		PrintWriter out = response.getWriter();
		try
		{

			FileItemFactory factory = new DiskFileItemFactory(); // 建立FileItemFactory对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			String uploadPath=request.getSession().getServletContext().getRealPath("/")+"upload/";
			File file = new File(uploadPath);
			if (!file.exists())
			{
				file.mkdir();
			}
			
			 
			 
			InputStream is = null; // 当前上传文件的InputStream对象
			JSONArray  fileJA = new JSONArray();
			
			// 循环处理上传文件
			for (FileItem item : items)
			{
			boolean hasName=true;
			
				// 处理普通的表单域
				if (item.isFormField())
				{
					if (item.getFieldName().equals("filename"))
					{
						// 如果新文件不为空，将其保存在filename中
						if (!item.getString().equals("")){
							fullPath = item.getString("UTF-8");
						}
						else{
							hasName= false;
						}
					}
					else{
						 
		                String formname=item.getFieldName();//获取form中的名字  
		                String formcontent=item.getString();  
		                formname=new String(formname.getBytes(),"GBK");  
		                formcontent=new String(formcontent.getBytes(),"GBK");  
		                paramMap.put(formname, formcontent);  
		                hasName= false;
					}
				}
				// 处理上传文件
				else 
				{
					if (item.getName() != null && !item.getName().equals("")){
						// 从客户端发送过来的上传文件路径中截取文件名
						fullPath = item.getName().substring(
								item.getName().lastIndexOf("\\") + 1);
						is = item.getInputStream(); // 得到上传文件的InputStream对象
					}
					else{
						hasName= false;
					}
				}
				int index= 0;
				if(hasName){
					
					String filename=UUID.randomUUID()+"."+fullPath.split("\\.")[1];
					// 将路径和上传文件名组合成完整的服务端路径
					fullPath = uploadPath + filename;
					// 如果服务器已经存在和上传文件同名的文件，则输出提示信息
					if (new File(fullPath).exists())
					{
						new File(fullPath).delete();
					}
					// 开始上传文件
					if (!fullPath.equals(""))
					{
						// 用FileOutputStream打开服务端的上传文件
						FileOutputStream fos = new FileOutputStream(fullPath);
						byte[] buffer = new byte[8192]; // 每次读8K字节
						int count = 0;
						// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
						while ((count = is.read(buffer)) > 0)
						{
							fos.write(buffer, 0, count); // 向服务端文件写入字节流
						}
						fos.close(); // 关闭FileOutputStream对象
						is.close(); // InputStream对象
						String fileItem = "{\"file"+(index+1)+"\":\""+filename+"\"}";
						fileJA.add(fileItem);
					}
					index++;
				}
			}
			
			if(paramMap.size()>0){
				String ftype=paramMap.get("ftype");
				//新建样品
				if(ftype!=null &&ftype.equals("cs")){
					for(int i=0;i<fileJA.size();i++){
						JSONObject fileJO= (JSONObject) fileJA.get(i);
						String excelName= fileJO.getString("file"+(i+1));
						createSample(uploadPath,excelName,request);
						
					}
					
					
					
				}
				//System.out.println(ftype+uploadPath+fileJA);
			}
			resultJO.accumulate("state", "success");
			resultJO.accumulate("msg", fileJA);
		}
		catch (Exception e)
		{
			resultJO.accumulate("state", "failure");
			resultJO.accumulate("msg", "上传失败!");
			System.out.println(e);
		}
		//out.print(resultJO.toString());
		
		if(callback==null){
			out.print(resultJO.toString());
		}
		else{
			out.print(callback+"("+resultJO.toString()+")");
		}
	}
	
	
	private String createSample(String uploadPath,String excelName,HttpServletRequest request){
		String result="";
		try {
			String message="";
			//pid fz_id  url c_id bu_id
			ExcelUtil eu =new ExcelUtil();
			eu.setExcelPath(uploadPath+excelName);
			List<Row> inList=eu.readExcel();
			List<Row> outList=new ArrayList<Row>();
			if(inList.size()>0){
				Row row=inList.get(0);
				row.createCell((short) 31).setCellValue("导入结果");  
				outList.add(row);
			}
			for(int index=1;index<inList.size();index++){
				Row row =inList.get(index);
				try {
					if(eu.getCellValue(row.getCell(0)).equals("")){
						message = "样品名称不能为空";
					}
					else if(eu.getCellValue(row.getCell(1)).equals("")){
						message = "检验项目不能为空";
					}
					else if(eu.getCellValue(row.getCell(7)).equals("")){
						message = "送样日期不能为空";
					}
					else if(eu.getCellValue(row.getCell(25)).equals("")){
						message = "追加不能为空";
					}
					else{
						Sample sample = new Sample();
						String dh_lv="",prod_id = "";
						sample.setNm_t(eu.getCellValue(row.getCell(0)).toString());///0
						System.out.println(sample.getNm_t());
						Map paramMaps = new HashMap();
						paramMaps.put("fromPage",0);
						paramMaps.put("toPage",1); 
						paramMaps.put("nm_t", eu.getCellValue(row.getCell(1)).toString());///1
						paramMaps.put("ty_lv", "成品");
						paramMaps.put("bu_id", paramMap.get("bu_id").toString());
						List<Prod> prodList= iProdService.selectprodByParam(paramMaps);
						if(prodList.size()>0){
							sample.setProd_id(prodList.get(0).getId()+"");
							dh_lv=prodList.get(0).getDh_lv();
							prod_id=prodList.get(0).getId()+"";
						}
						sample.setDb_n(eu.getCellValue(row.getCell(2)).toString());///2
						sample.setDw_lv(eu.getCellValue(row.getCell(3)).toString());///3
						paramMaps = new HashMap();
						paramMaps.put("fromPage",0);
						paramMaps.put("toPage",1); 
						paramMaps.put("bu_id", paramMap.get("bu_id").toString());
						paramMaps.put("nm_t", eu.getCellValue(row.getCell(4)).toString());///4
						List<Accnt> accntList= iAccntService.selectaccntByParam(paramMaps);
						if(accntList.size()>0){
							sample.setSc_id(accntList.get(0).getId()+"");
						}
						sample.setCd_t(eu.getCellValue(row.getCell(5)).toString());///5
						sample.setGg_t(eu.getCellValue(row.getCell(6)).toString());///6
						String sydt=eu.getCellValue(row.getCell(7)).toString();
						if(sydt!=null&&!sydt.equals(""))
						sample.setSy_dt(sydt);///7
						String zzdt=eu.getCellValue(row.getCell(8)).toString();
						if(zzdt!=null&&!zzdt.equals(""))
						sample.setZz_dt(zzdt);///8
						String testdt=eu.getCellValue(row.getCell(9)).toString();
						if(testdt!=null&&!testdt.equals(""))
						sample.setTest_dt(testdt);///9
						sample.setBw_t(eu.getCellValue(row.getCell(10)).toString());///10
						sample.setGc_t(eu.getCellValue(row.getCell(11)).toString());///11
						sample.setJl_t(eu.getCellValue(row.getCell(12)).toString());///12
						sample.setLo_t(eu.getCellValue(row.getCell(13)).toString());///13
						sample.setLa_t(eu.getCellValue(row.getCell(14)).toString());///14
						sample.setHi_t(eu.getCellValue(row.getCell(15)).toString());///15
						sample.setSn_t(eu.getCellValue(row.getCell(16)).toString());///16
						sample.setSz_t(eu.getCellValue(row.getCell(17)).toString());///17
						sample.setSt_t(eu.getCellValue(row.getCell(18)).toString());///18
						sample.setPp_t(eu.getCellValue(row.getCell(19)).toString());///19
						sample.setWj_t(eu.getCellValue(row.getCell(20)).toString());///20
						sample.setCh_t(eu.getCellValue(row.getCell(21)).toString());///21
						sample.setSh_t(eu.getCellValue(row.getCell(22)).toString());///22
						sample.setSnl_t(eu.getCellValue(row.getCell(23)).toString());///23
						sample.setWa_t(eu.getCellValue(row.getCell(24)).toString());///24
						sample.setTy_lv(eu.getCellValue(row.getCell(25)).toString());///25
						sample.setSt_lv(eu.getCellValue(row.getCell(26)).toString());///26
						sample.setLy_lv(eu.getCellValue(row.getCell(27)).toString());///27
						sample.setQd_t(eu.getCellValue(row.getCell(28)).toString());///28
						sample.setKd_t(eu.getCellValue(row.getCell(29)).toString());///29
						sample.setCm_tx(eu.getCellValue(row.getCell(30)).toString());///30
						
						sample.setPart(createCode(paramMap.get("bu_id").toString(), dh_lv));
						sample.setLot(createLot(eu.getCellValue(row.getCell(7)).toString(), prod_id));
						sample.setPid(paramMap.get("pid"));
						sample.setFz_id(paramMap.get("fz_id"));
						sample.setC_id(paramMap.get("c_id"));
						sample.setBu_id(paramMap.get("bu_id"));
						
						//pid fz_id  url c_id bu_id
						int resultSample = Integer.parseInt(iSampleService.addsample(sample).toString());
						if(resultSample>0){
							String qrResult = MatrixToImageWriter.createQrImage("PS_"+sample.getId());
							if(qrResult.length()>0){
								//HttpServletRequest request = ServletActionContext.getRequest();
								String path = request.getScheme() + "://"
										+ request.getServerName() + ":" + request.getServerPort()
										+ request.getContextPath();
								Sample upsample = new Sample();
								upsample.setId(sample.getId());
								upsample.setEwm(path+"/QRImages/"+qrResult);
								iSampleService.updatesample(upsample);
							}
							message = "成功";
						}
						else{
							message = "失败";
						}
						
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					message = "失败";
					e.printStackTrace();
				}
				/*HSSFCell cell = row.createCell((short)i);  
			    HSSFCellStyle cStyle = wb.createCellStyle();  
			        cStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);  
			        cell.setCellStyle(cStyle);  
			    cell.setCellStyle(cStyle);  */
				row.createCell((short) 31).setCellValue(message);  
			  
				outList.add(row);
			}
			String[] fileNames=excelName.split("\\.");
			 
			String src_xlsPath=uploadPath+fileNames[0]+"_result."+fileNames[1];
			// 读取文档  
			/*File file = new File(src_xlsPath);  
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file)); */    
			eu.writeExcel(outList, src_xlsPath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}  
		
		
		
		
		return result;
	}
	
	/**
     * 
     * 编号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createCode(String bu_id,String dh_lv){
    	//http://192.168.1.144/Check/Sample?prod_id=14&bu_id=1
		StringBuffer sb = new StringBuffer();
		//Prod prod = iProdService.selectprodById(prod_id);
		sb.append("YP"+dh_lv);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Map paramMap = new HashMap();
		paramMap.put("fromPage",0);
		paramMap.put("toPage",1); 
		paramMap.put("bu_id", bu_id);
		paramMap.put("nm_t", dh_lv);
		paramMap.put("ty_lv", "代号");
		paramMap.put("order", "ORDER BY SORT DESC");
		Lov templov = new Lov();
		String newSort=""; 
		List<Lov> lovList= iLovService.selectlovByParamOrder(paramMap);
		if(lovList.size()>0){
			String lovSort=lovList.get(0).getSort();
			Long lovId=lovList.get(0).getId();
			String cm_tx= lovList.get(0).getCm_tx();
			if(lovSort.equals("")||lovSort.equals("null")||!cm_tx.equals(year+"")){
				newSort = "001";
				templov.setSort(newSort);
				templov.setCm_tx(year+"");
				templov.setId(lovId);
			}
			else{
				//更新
				int sortNum=Integer.valueOf(lovSort).intValue();
				newSort = CodeUtils.autoGenericCode(sortNum+"",3);
				 
				templov.setCm_tx(year+"");
				templov.setSort(newSort);
				templov.setId(lovId);
			}
			iLovService.updatelov(templov);
		}
		else{
			//新建 
			newSort = "001";
			templov.setBu_id(bu_id);
			templov.setC_dt(new Date());
			templov.setCm_tx(year+"");
			templov.setNm_t(dh_lv);
			templov.setSort(newSort);
			templov.setTy_lv("代号");
			iLovService.addlov(templov);
		}
		sb.append(newSort);
    	 
    	return sb.toString();
    }
    /**
     * 
     * 批号
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public String createLot(String sy_dt,String prod_id){
    	//http://192.168.1.144/Check/Sample?prod_id=14&bu_id=1&pid=194&sy_dt=2014-04-30 00:00:00&c_id=11
    	String groupcode= "";
    	//sdf = new SimpleDateFormat("yy-MM-dd");
    	Pact pact = iPactService.selectpactById(paramMap.get("pid").toString());
		StringBuffer sb = new StringBuffer();
		String sy_dtStr="";
		try {
			sy_dtStr = sy_dt.substring(0,10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//送样日期_委托单位ID_检测项目ID
		groupcode=sy_dtStr+"_"+pact.getPid()+"_"+prod_id;
		sb.append(sy_dtStr.substring(2,10).replaceAll("-", ""));
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Map paramMaps = new HashMap();
	  	paramMaps = new HashMap();
    	paramMaps.put("fromPage",0);
    	paramMaps.put("toPage",1); 
    	paramMaps.put("group_code", groupcode);
    	 
    	paramMaps.put("order", "ORDER BY LOTNUMBER DESC");
    	//选最大的
    	List<Lot> lotList= iLotService.selectlotByParamOrder(paramMaps);
    	//String lotS_num="001";
    	Lot templot = new Lot();
    	if(lotList.size()>0){
    		String lotnumberStr=lotList.get(0).getLotnumber();
    		if(lotnumberStr.equals("")||lotnumberStr.equals("null")){
    			
    			//String nextNum = CodeUtils.autoGenericCode(lotnumberStr+"",3);
    			
    			templot.setLotnumber("001");
        		templot.setId(lotList.get(0).getId());
        		sb.append("001");
        		iLotService.updatelot(templot);
    		}
    		else{
    			int sortNum=Integer.valueOf(lotnumberStr).intValue(); 
    			String nextNum = CodeUtils.autoGenericCode(sortNum+"",3);
    			templot.setLotnumber(nextNum);
        		templot.setId(lotList.get(0).getId());
        		sb.append(nextNum);
        		iLotService.updatelot(templot);
    		}
    		
    	}
    	else{
    		
    		paramMaps = new HashMap();
        	paramMaps.put("fromPage",0);
        	paramMaps.put("toPage",1); 
        	paramMaps.put("sy_dt", sy_dtStr);
        	paramMaps.put("bu_id", paramMap.get("bu_id"));
        	paramMaps.put("order", "ORDER BY LOTNUMBER DESC");
        	lotList= iLotService.selectlotByParamOrder(paramMaps);
        	if(lotList.size()>0){
        		String lotnumberStr=lotList.get(0).getLotnumber();
        		if(lotnumberStr.equals("")||lotnumberStr.equals("null")){
        			templot.setLotnumber("001");
            		//templot.setId(lotList.get(0).getId());
            		sb.append("001");
            		//iLotService.updatelot(templot);
        		}
        		else{
        			int sortNum=Integer.valueOf(lotnumberStr).intValue(); 
        			String nextNum = CodeUtils.autoGenericCode(sortNum+"",3);
        			templot.setLotnumber(nextNum);
            		//templot.setId(lotList.get(0).getId());
            		sb.append(nextNum);
            		//iLotService.updatelot(templot);
        		}
        	 
        	}
        	else{
        		templot.setLotnumber("001"); 
        		sb.append("001"); 
        	}
        	//templot.setS_num();
    		templot.setBu_id(paramMap.get("bu_id").toString());
    		templot.setC_dt(new Date());
    		templot.setC_id(paramMap.get("c_id").toString());
    		templot.setComments("");
    		templot.setGroup_code(groupcode);
    		templot.setHt_id(paramMap.get("pid").toString());
    		templot.setJc_id(prod_id);
    		templot.setPro_code("");
    		//templot.setPro_num("1");
    		//templot.setWt_dt(wt_dt);
    		templot.setSy_dt(sy_dtStr);
    		templot.setYear(year+"");
    		templot.setWt_id(pact.getPid());
    		  
    		iLotService.addlot(templot);
    		
    	}
    	 
    	return sb.toString();
    }
	
}
