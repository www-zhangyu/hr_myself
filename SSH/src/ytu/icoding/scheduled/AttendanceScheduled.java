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
 * ��ʱ����
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
	
	//@Scheduled(cron = "0 31 14 ? * MON-FRI")///��һ�������賿ִ��
    void doSomethingWith(){
        System.out.println("��ʱ����ʼ......");

    
        //��ѯ���ڱ���ÿ��û��ǩ����Ա������Ϣ����ѯ��Ա���Ƿ�����٣����û����٣�����Ϣ���浽���ͱ���
        List<User> users = userService.selectNotCheckInUser();
        for (User user : users) {
        	bonusPenaltyService.insertPenalty(user.getUserId(), "����", -300);
		}
        
        //��ѯ���ڱ��гٵ����û���״̬Ϊ2������50
        List<User> lateUsers = userService.selectLateUser();
        for (User user : lateUsers) {
			bonusPenaltyService.insertPenalty(user.getUserId(), "�ٵ�", -50);
		}
        
        ///��ѯ���ڱ���״̬Ϊ3���ٵ�ʱ�䳬��1Сʱ������������ٱ���û�и��û������Ϣ���û�
        List<User> absentUsers = userService.selectAbsentUser();
        for (User user : absentUsers) {
			////
        	bonusPenaltyService.insertPenalty(user.getUserId(), "�ٵ�����1Сʱ", -200);
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
