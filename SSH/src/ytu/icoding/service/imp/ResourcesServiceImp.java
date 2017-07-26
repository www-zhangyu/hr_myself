package ytu.icoding.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;






import org.springframework.transaction.annotation.Transactional;

import ytu.icoding.dao.ResourcesDao;
import ytu.icoding.entity.Resources;
import ytu.icoding.service.ResourcesService;

@Service("resourcesService")
public class ResourcesServiceImp implements ResourcesService{
	
	@Resource
	private ResourcesDao resourcesDao;
	
	public ResourcesDao getResourcesDao() {
		return resourcesDao;
	}

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	@Override
	public List<Resources> findAll() {
		// TODO Auto-generated method stub
		return resourcesDao.findAll();
	}
	
	@Override
	public List<Resources> getUserResources(String id) {
		// TODO Auto-generated method stub
		return resourcesDao.getUserResources(id);
	}
	
	@Transactional
	@Override
	public void deleteOneResources(int id) {
		// TODO Auto-generated method stub
		resourcesDao.deleteOneResources(id);
	}
	
	@Transactional
	@Override
	public void deleteResourcesRole(int resId) {
		// TODO Auto-generated method stub
		resourcesDao.deleteResourcesRole(resId);
	}
	
	@Transactional
	@Override
	public void updateResources(int id, String name, String resKey,
			String resUrl, String description) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("name",name);
		map.put("resKey",resKey);
		map.put("resUrl",resUrl);
		map.put("description",description);
		resourcesDao.updateResources(map);
	}
	
	@Transactional
	@Override
	public void addResource(Resources resources) {
		// TODO Auto-generated method stub
		
		resourcesDao.addResource(resources);
		
	}
	
	@Override
	public Resources getResourceById(int id) {
		// TODO Auto-generated method stub
		return resourcesDao.getResourceById(id);
	}
}
