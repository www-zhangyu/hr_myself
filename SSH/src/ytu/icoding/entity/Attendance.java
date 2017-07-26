package ytu.icoding.entity;

import java.util.Date;

public class Attendance {
	
	private int id;
	private int userId;
	private String userName;

	private String depName;
	private String checkDate;
	private String checkInTime;
	private String checkOutTime;
	private int checkInStatus;
	private int checkOutStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public String getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public int getCheckInStatus() {
		return checkInStatus;
	}
	public void setCheckInStatus(int checkInStatus) {
		this.checkInStatus = checkInStatus;
	}
	public int getCheckOutStatus() {
		return checkOutStatus;
	}
	public void setCheckOutStatus(int checkOutStatus) {
		this.checkOutStatus = checkOutStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	
	
	

}
