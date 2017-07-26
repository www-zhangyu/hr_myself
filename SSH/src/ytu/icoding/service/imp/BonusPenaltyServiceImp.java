package ytu.icoding.service.imp;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ytu.icoding.dao.BonusPenaltyDao;
import ytu.icoding.service.BonusPenaltyService;

@Service
public class BonusPenaltyServiceImp implements BonusPenaltyService{
	
	@Resource
	private BonusPenaltyDao bonusPenaltyDao;
	
	@Transactional
	@Override
	public void insertPenalty(int userId, String type, int price) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("type", type);
		map.put("price", price);
		System.out.println("userId"+userId);
		bonusPenaltyDao.insertPenalty(map);
	}
	
	
	
	public BonusPenaltyDao getBonusPenaltyDao() {
		return bonusPenaltyDao;
	}
	public void setBonusPenaltyDao(BonusPenaltyDao bonusPenaltyDao) {
		this.bonusPenaltyDao = bonusPenaltyDao;
	}
	
	

}
