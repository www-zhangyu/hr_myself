package ytu.icoding.dao;

import java.util.List;

import ytu.icoding.entity.Leave;

public interface LeaveDao {
	
	List<Leave> selectLeaveByUser(int userId);

}
