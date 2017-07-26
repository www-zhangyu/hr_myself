package ytu.icoding.dao;

import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.Attendance;

public interface AttendanceDao {
	
	void insertCheckIn(HashMap<String, Object> map);
	
	Attendance selectAttendanceByUser(int userId);
	
	void updateCheckOut(HashMap<String, Object> map);
	
	List<Attendance> selectAttendance(HashMap<String, Object> map);
	
	List<Attendance> selectAttendanceByPage(HashMap<String, Object> map);

	List<Attendance> selectAttendanceOrderByDep();
}
