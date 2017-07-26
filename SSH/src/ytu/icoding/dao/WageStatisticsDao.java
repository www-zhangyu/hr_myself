package ytu.icoding.dao;

import java.util.HashMap;
import java.util.List;

import ytu.icoding.entity.WageStatistics;

public interface WageStatisticsDao {
	
	
	List<WageStatistics> listAllUserWage(HashMap<String,Object> map);
	
	List<WageStatistics> listWageStatisticsByPage(HashMap<String,Object> map);

}
