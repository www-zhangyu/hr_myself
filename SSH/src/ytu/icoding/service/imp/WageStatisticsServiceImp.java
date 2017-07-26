package ytu.icoding.service.imp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ytu.icoding.dao.WageStatisticsDao;
import ytu.icoding.entity.WageStatistics;
import ytu.icoding.service.WageStatisticsService;


@Service
public class WageStatisticsServiceImp implements WageStatisticsService{
	
	@Resource
	private WageStatisticsDao wageStatisticsDao;
	
	
	
	@Override
	public List<WageStatistics> listAllUserWage(String userName,
			String depName, String date) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		map.put("depName", userName);
		map.put("date", date);
		return wageStatisticsDao.listAllUserWage(map);
	}
	
	
	@Override
	public List<WageStatistics> listWageStatisticsByPage(int beginIndex, int pageSize, String userName, 
			String depName, String date) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("beginIndex", beginIndex);
		map.put("pageSize", pageSize);
		map.put("userName", userName);
		map.put("depName", depName);
		map.put("date", date);
		return wageStatisticsDao.listWageStatisticsByPage(map);
	}

	public WageStatisticsDao getWageStatisticsDao() {
		return wageStatisticsDao;
	}

	public void setWageStatisticsDao(WageStatisticsDao wageStatisticsDao) {
		this.wageStatisticsDao = wageStatisticsDao;
	}

	
	
	
	

}
