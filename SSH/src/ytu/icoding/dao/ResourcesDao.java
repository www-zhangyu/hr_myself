package ytu.icoding.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import ytu.icoding.entity.Resources;

public interface ResourcesDao {
	
	List<Resources> findAll();
	
	
	 List<Resources> getUserResources(String id);
	
	void deleteOneResources(int id);
	
	void deleteResourcesRole(int resId);
	
	void updateResources(HashMap<String,Object> map);
	
	Resources getResourceById(int id);
	
	void addResource(Resources resources);
}
