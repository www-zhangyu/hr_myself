package ytu.icoding.entity;

import java.util.Set;

public class Position {


	private Long posId;
	private String posName;
	private Set<User> userSet;
	private Set<Department> depSet;
	public Long getPosId() {
		return posId;
	}
	public void setPosId(Long posId) {
		this.posId = posId;
	}
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public Set<User> getUserSet() {
		return userSet;
	}
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	public Set<Department> getDepSet() {
		return depSet;
	}
	public void setDepSet(Set<Department> depSet) {
		this.depSet = depSet;
	}
	
	
	
	
}
