package com.check.service.departments;
import java.util.List;
import java.util.Map;
import com.check.model.departments.Departments;
public interface IDepartmentsService {
	/**
 * 通过id选取
 * @return
 */
	public Departments selectdepartmentsById(String id);
	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Departments> selectdepartmentsByParam(Map paramMap); 
	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountdepartmentsByParam(Map paramMap); 
	/**
 * 更新 
 * @return 
 */ 
	public  int updatedepartments(Departments departments);
	/**
 * 添加 
 * @return
 */ 
	public  Object adddepartments(Departments departments);
	/**
 * 删除 
 * @return 
 */ 
	public  int deletedepartments(String id);

}

