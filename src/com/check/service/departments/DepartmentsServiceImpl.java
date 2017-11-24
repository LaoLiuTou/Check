package com.check.service.departments;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.check.dao.departments.IDepartmentsMapper;
import com.check.model.departments.Departments;
public class DepartmentsServiceImpl  implements IDepartmentsService {

	@Autowired
	private IDepartmentsMapper iDepartmentsMapper;
	/**
 * 通过id选取
 * @return
 */
	public Departments selectdepartmentsById(String id){
		return iDepartmentsMapper.selectdepartmentsById(id);
	}

	/**
 * 通过查询参数获取信息
 * @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Departments> selectdepartmentsByParam(Map paramMap){ 
		return iDepartmentsMapper.selectdepartmentsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	 * @return
	 */ 
 @SuppressWarnings("rawtypes")
	public int selectCountdepartmentsByParam(Map paramMap){ 
		return iDepartmentsMapper.selectCountdepartmentsByParam(paramMap);
	}

	/**
 * 更新 
 * @return 
 */ 
 @Transactional
	public  int updatedepartments(Departments departments){
		return iDepartmentsMapper.updatedepartments(departments);
	}

	/**
 * 添加 
 * @return
 */ 
 @Transactional
	public  Object adddepartments(Departments departments){
		return iDepartmentsMapper.adddepartments(departments);
	}

	/**
 * 删除 
 * @return 
 */ 
 @Transactional
	public  int deletedepartments(String id){
		return iDepartmentsMapper.deletedepartments(id);
	}

}

