package ytu.icoding.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.Attendance;

public interface AttendanceService {
	
	void insertCheckIn(int userId,String checkInTime,int checkInStatus);
	
	Attendance selectAttendanceByUser(int userId);
	
	void updateCheckOut(int id, String checkOutTime, int status);
	
	List<Attendance> selectAttendance(String userName, String depName, String date);
	
	List<Attendance> selectAttendanceByPage(int beginIndex, int pageSize, String userName, String depName, String date);

	List<Attendance> selectAttendanceOrderByDep();
}
