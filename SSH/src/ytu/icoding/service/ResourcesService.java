package ytu.icoding.service;

import java.util.List;

import ytu.icoding.entity.Resources;

public interface ResourcesService {
	
	List<Resources> findAll();
	
	List<Resources> getUserResources(String id);
	
	void deleteOneResources(int id);
	
	void deleteResourcesRole(int resId);
	
	void updateResources(int id, String name, String resKey, String resUrl, String description);
	
	
	Resources getResourceById(int id);
	
	void addResource(Resources resources);

}
