package ytu.icoding.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ytu.icoding.dao.AttendanceDao;
import ytu.icoding.entity.Attendance;
import ytu.icoding.service.AttendanceService;


@Service("attendanceService")
public class AttendanceServiceImp implements AttendanceService{
	
	@Resource
	private AttendanceDao attendanceDao;
	
	

	@Override
	public List<Attendance> selectAttendance(String userName, String depName,
			String date) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("depName", depName);
		map.put("date", date);

		return attendanceDao.selectAttendance(map);
	}
	
	@Override
	public List<Attendance> selectAttendanceByPage(int beginIndex, int pageSize, String userName, 
			String depName, String date) {
		// TODO Auto-generated method stub
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("beginIndex", beginIndex);
		map.put("pageSize", pageSize);
		map.put("userName", userName);
		map.put("depName", depName);
		map.put("date", date);
		return attendanceDao.selectAttendanceByPage(map);
	}

	@Transactional
	@Override
	public void insertCheckIn(int userId, String checkInTime, int checkInStatus) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("checkInTime", checkInTime);
		map.put("checkInStatus", checkInStatus);
		attendanceDao.insertCheckIn(map);
	}
	
	@Override
	public Attendance selectAttendanceByUser(int userId) {
		// TODO Auto-generated method stub
		return attendanceDao.selectAttendanceByUser(userId);
	}
	
	@Override
	public List<Attendance> selectAttendanceOrderByDep() {
		// TODO Auto-generated method stub
		return attendanceDao.selectAttendanceOrderByDep();
	}
	
	
	
	
	@Transactional
	@Override
	public void updateCheckOut(int id, String checkOutTime, int status) {
		// TODO Auto-generated method stub
		System.out.println("111111111111111111111111111");
		// TODO Auto-generated method stub
		HashMap<String, Object> map =new  HashMap<>();		
		map.put("userId", id);
		map.put("checkOutStatus", status);
		map.put("checkOutTime", checkOutTime);
		attendanceDao.updateCheckOut(map);
	}
	

	public AttendanceDao getAttendanceDao() {
		return attendanceDao;
	}


	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}
	

}
