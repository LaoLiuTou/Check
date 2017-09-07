package com.check.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
/**
 * @author LT
 */
public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException
	{

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
			int index= items.indexOf(item);
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
				}
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
}
