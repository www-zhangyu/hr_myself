package ytu.icoding.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ytu.icoding.dao.DepartmentDao;
import ytu.icoding.entity.Department;
import ytu.icoding.service.DepartmentService;

@Service
public class DepartmentServiceImp implements DepartmentService{
	
	@Resource
	private DepartmentDao departmentDao;
	@Override
	public List<Department> selectAllDepartment() {
		// TODO Auto-generated method stub
		return departmentDao.selectAllDepartment();
	}
	
	
	
	
	
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	
	

}
