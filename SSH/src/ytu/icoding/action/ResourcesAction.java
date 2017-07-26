package ytu.icoding.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import oracle.net.aso.r;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;

import ytu.icoding.entity.Resources;
import ytu.icoding.entity.User;
import ytu.icoding.service.ResourcesService;
import ytu.icoding.util.TreeObject;
import ytu.icoding.util.TreeUtil;

@Controller("resourcesAction")
public class ResourcesAction extends StandardAction{
	
	
	@Resource
	private ResourcesService resourcesService;
	
	private String result;
	
	
	/**
	 * ���ص�¼���û�ӵ�е�Ȩ��
	 * @throws Exception
	 */
	public void resources() throws Exception{
		
		PrintWriter out = response.getWriter();
		List<Resources> resources;
		User user = (User)request.getSession().getAttribute("CurrentUser");
		if ("��������Ա".equals(user.getRoleName())) {//��ǰ�û�Ϊ��������Ա
			resources = resourcesService.findAll();
		}else{
			resources = resourcesService.getUserResources(String.valueOf(user.getUserId()));
		}
		List<TreeObject> treeObjects = new ArrayList<TreeObject>();
		for (Resources res : resources) {//ת��Ϊ������
			TreeObject tree = new TreeObject();
			PropertyUtils.copyProperties(tree,res );
			treeObjects.add(tree);
		}
		List<TreeObject> ns = TreeUtil.getChildResourcess(treeObjects, 0);
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(ns);
		result = jsonArray.toString();
		System.out.println("--resources--"+result);
		out.write(result);
	}
	
	
	public String listResources(){
		
		return "success";
	}
	
	/**
	 * �޸�Ȩ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String updateResources() throws Exception{
		request.setCharacterEncoding("utf-8");
		int id =Integer.valueOf(request.getParameter("edit_resId"));
		String name = request.getParameter("edit_resName");
		String key = request.getParameter("edit_resKey");
		String url = request.getParameter("edit_resUrl");
		String des = request.getParameter("edit_des");
		//System.out.println(name);
		resourcesService.updateResources(id,name, key, url, des);
		addReturnValue(1,119902 );
		return "success";
	}
	
	/**
	 * ���Ȩ��
	 * @return
	 */
	public String addResources(){
		
		
		int id = Integer.valueOf(request.getParameter("add_resId"));
		
		
		/////�ж����ݿ����Ƿ��Ѿ����ڸ�id��Ӧ����Դ
		Resources resourceById = resourcesService.getResourceById(id);
		if (resourceById != null) {
			addReturnValue(-1, 119907);
			return "success";
		}
		
		String name = request.getParameter("add_resName");
		int parentId = Integer.valueOf(request.getParameter("add_parentId"));
		String url = request.getParameter("add_resUrl");
		String key = request.getParameter("add_resKey");
		
		///resKey��Ψһ��
		List<Resources> lst = resourcesService.findAll();
		for (Resources resource : lst) {
			if(resource.getResKey().equals(key)){
				addReturnValue(-1, 119908);
				return "success";
			}
		}
		
		String type = request.getParameter("add_resType");
		String des = "";
		
		if (!"".equals(request.getParameter("add_des")) && request.getParameter("add_des") != null ) {
			des = request.getParameter("add_des");
		}
		int level = 0;
		if (request.getParameter("add_level") != null && !"".equals(request.getParameter("add_level"))){
			level = Integer.valueOf(request.getParameter("add_level"));
		}

		
		Resources resources = new Resources(id, name, parentId, key, url, level, type, des);
		
		resourcesService.addResource(resources);
		
		addReturnValue(1, 119901);
		
		return "success";
	}
	
	/**
	 * ɾ��Ȩ��  ͬʱҪ���м�������ɾ��
	 * @throws Exception
	 */
	public void deleteOneResources() throws Exception{
		PrintWriter out = response.getWriter();
		int resId = Integer.valueOf(request.getParameter("resId"));
		System.out.println(resId);
		resourcesService.deleteOneResources(resId);
		resourcesService.deleteResourcesRole(resId);
		out.write("ɾ���ɹ�");
	}
	
	/**
	 * ����ɾ��
	 * @throws IOException
	 */
	public void deleteSelectedResources() throws IOException{
		PrintWriter out = response.getWriter();
		String ids = request.getParameter("ids");
		String[] split = ids.split(",");
		for (String i : split) {
			int id = Integer.valueOf(i);
			resourcesService.deleteOneResources(id);
			resourcesService.deleteResourcesRole(id);
		}
		
		out.write("��ѡ��Դ��ɾ��");
		
	}
	
	
	

	
	public ResourcesService getResourcesService() {
		return resourcesService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	
}
