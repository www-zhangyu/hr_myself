package ytu.icoding.service;

import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.WageStatistics;

public interface WageStatisticsService {

	List<WageStatistics> listAllUserWage(String userName,String depName,String date);
	
	List<WageStatistics> listWageStatisticsByPage(int beginIndex, int pageSize, String userName, 
			String depName, String date);

}
