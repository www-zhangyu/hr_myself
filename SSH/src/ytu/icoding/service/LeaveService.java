package ytu.icoding.service;

import java.util.List;

import ytu.icoding.entity.Leave;

public interface LeaveService {
	
	List<Leave> selectLeaveByUser(int userId);

}
