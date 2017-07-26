package ytu.icoding.entity;

import java.util.HashSet;
import java.util.Set;


public class Resources {
	
	private Integer id;
	private String name;
	private Integer parentId; //父类Id
	private String parentName;
	private String resKey;//这个权限KEY是唯一的，新增时要注意，
	private String resUrl;//URL地址．例如：/videoType/query　　不需要项目名和http://xxx:8080
	private Integer level;
	private String type;//权限类型，0．表示目录　1，表示菜单．2，表示按扭．．在spring security3安全权限中，涉及精确到按扭控制
	private String description;
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Resources> childs = new HashSet<Resources>(0);
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Resources> getChilds() {
		return childs;
	}
	public void setChilds(Set<Resources> childs) {
		this.childs = childs;
	}
	public Resources(Integer id, String name, Integer parentId, String resKey,
			String resUrl, Integer level, String type, String description) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.resKey = resKey;
		this.resUrl = resUrl;
		this.level = level;
		this.type = type;
		this.description = description;
	}
	public Resources() {
		super();
	}
	

}
