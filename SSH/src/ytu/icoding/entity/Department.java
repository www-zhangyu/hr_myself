package ytu.icoding.entity;

import java.util.Set;



public class Department {
	private Integer depId;
	private String depName;
	private Set<Position> posSet;///职位  多对多
	private Set<User> userSet;
	
	
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public Set<Position> getPosSet() {
		return posSet;
	}
	public void setPosSet(Set<Position> posSet) {
		this.posSet = posSet;
	}
	public Set<User> getUserSet() {
		return userSet;
	}
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	
	
}
