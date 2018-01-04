package com.check.action.report;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.check.service.report.IReportService;
import com.check.utils.MatrixToImageWriter;
import com.opensymphony.xwork2.Action;
public class ReportAction implements Action {

	private String callback;//跨域
	@Autowired
	private IReportService iReportService;
	HttpServletResponse response = ServletActionContext.getResponse(); 
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("CheckLogger"); 
	private String param;
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String update() throws Exception {
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		 
		StringBuffer msg = new StringBuffer("{\"state\":");
		try {
			int result = iReportService.updatereport(param);
			if(result>=0){
				msg.append("\"success\",\"msg\":\"");
				msg.append("修改成功！\"");
				logger.info(result+"修改成功！");
			}
			else{
				msg.append("\"failure\",\"msg\":");
				msg.append("\"修改失败！\"");
			}
		} catch (Exception e) {
			msg.append("\"failure\",\"msg\":");
			msg.append("\"修改失败！\"");
			logger.info("修改失败！。");
			e.printStackTrace();
		}
		msg.append("}");
		if(callback==null){
			response.getWriter().write(msg.toString());
		}
		else{
			response.getWriter().write(callback+"("+msg.toString()+")");
		}
		return null;
	}
 
    public String execute() throws Exception {
    	response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8"); 
		 
    	String qrResult = MatrixToImageWriter.createQrImage("AR_1234");
    	response.getWriter().write(qrResult);
    	System.out.println(qrResult);
		return null;
	}

}
