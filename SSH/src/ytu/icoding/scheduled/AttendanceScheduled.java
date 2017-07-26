package ytu.icoding.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ytu.icoding.entity.Leave;
import ytu.icoding.entity.User;
import ytu.icoding.service.BonusPenaltyService;
import ytu.icoding.service.LeaveService;
import ytu.icoding.service.UserService;

/**
 * 定时任务
 * @author d
 *
 */
@Component
public class AttendanceScheduled {
	
	@Resource
	private UserService userService;
	@Resource
	private BonusPenaltyService bonusPenaltyService;
	@Resource
	private LeaveService leaveService;
	
	//@Scheduled(cron = "0 31 14 ? * MON-FRI")///周一至周五凌晨执行
    void doSomethingWith(){
        System.out.println("定时任务开始......");

    
        //查询考勤表中每天没有签到的员工的信息，查询该员工是否已请假，如果没有请假，将信息保存到奖惩表中
        List<User> users = userService.selectNotCheckInUser();
        for (User user : users) {
        	bonusPenaltyService.insertPenalty(user.getUserId(), "旷工", -300);
		}
        
        //查询考勤表中迟到的用户（状态为2），扣50
        List<User> lateUsers = userService.selectLateUser();
        for (User user : lateUsers) {
			bonusPenaltyService.insertPenalty(user.getUserId(), "迟到", -50);
		}
        
        ///查询考勤表中状态为3（迟到时间超过1小时），而且在请假表中没有该用户请假信息的用户
        List<User> absentUsers = userService.selectAbsentUser();
        for (User user : absentUsers) {
			////
        	bonusPenaltyService.insertPenalty(user.getUserId(), "迟到超过1小时", -200);
		}
    
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BonusPenaltyService getBonusPenaltyService() {
		return bonusPenaltyService;
	}

	public void setBonusPenaltyService(BonusPenaltyService bonusPenaltyService) {
		this.bonusPenaltyService = bonusPenaltyService;
	}

	public LeaveService getLeaveService() {
		return leaveService;
	}

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	
	
	
	
	
}
