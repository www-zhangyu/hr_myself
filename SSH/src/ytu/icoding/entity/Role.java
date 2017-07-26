package ytu.icoding.entity;

import java.util.HashSet;
import java.util.Set;


public class Role {
	
	private Integer id;
	private String name;
	private String roleKey;
	private String description;
	private Set<Resources> resources = new HashSet<Resources>(0);
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
	

}
