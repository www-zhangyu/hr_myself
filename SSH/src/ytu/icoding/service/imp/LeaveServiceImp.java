package ytu.icoding.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ytu.icoding.dao.LeaveDao;
import ytu.icoding.entity.Leave;
import ytu.icoding.service.LeaveService;

@Service
public class LeaveServiceImp implements LeaveService{
	
	@Resource
	private LeaveDao leaveDao;
	
	@Override
	public List<Leave> selectLeaveByUser(int userId) {
		// TODO Auto-generated method stub
		return leaveDao.selectLeaveByUser(userId);
	}
	
	
	
	
	
	
	public LeaveDao getLeaveDao() {
		return leaveDao;
	}
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	
	
	

}
