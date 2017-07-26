package ytu.icoding.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ytu.icoding.entity.Department;
import ytu.icoding.service.DepartmentService;

@Controller
public class DepartmentAction extends StandardAction{

	@Resource
	private DepartmentService departmentService;
	
	
	
	public void selectAllDepartment() throws Exception{
		PrintWriter out = response.getWriter();
		String result;
		////查询所有的部门
		List<Department> deps = departmentService.selectAllDepartment();
		
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(deps);
		
		result = jsonArray.toString();
		
		System.out.println("-------"+result);
		
		out.write(result);
	}
	
	
	
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
}
