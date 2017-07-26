package ytu.icoding.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class User{

	private Integer userId;
	private String name;
	private String password;
	private String roleName;
	
	private String hireDate;//入职日期
	

	private Integer userAge;

	private String userSex;

	private String userPhone;

	private String userMail;

	private String bankName;

	private String bankAccountName;

	private String bankAccount;
	
	private Date changePwdDate;
	
	private String depName;
	
	private String posName;
	
	private int shouldWage;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	

	public Date getChangePwdDate() {
		return changePwdDate;
	}

	public void setChangePwdDate(Date changePwdDate) {
		this.changePwdDate = changePwdDate;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public int getShouldWage() {
		return shouldWage;
	}

	public void setShouldWage(int shouldWage) {
		this.shouldWage = shouldWage;
	}

	
	

	
}
