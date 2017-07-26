package ytu.icoding.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ytu.icoding.dao.WageTypeDao;
import ytu.icoding.entity.WageType;
import ytu.icoding.service.WageTypeService;

@Service
public class WageTypeServiceImp implements WageTypeService{
	
	@Resource
	private WageTypeDao wageTypeDao;
	@Override
	public List<WageType> listAllWageType() {
		// TODO Auto-generated method stub
		return wageTypeDao.listAllWageType();
	}
	
	
	
	
	
	
	
	
	public WageTypeDao getWageTypeDao() {
		return wageTypeDao;
	}
	public void setWageTypeDao(WageTypeDao wageTypeDao) {
		this.wageTypeDao = wageTypeDao;
	}
	
	
	
	
	
	

}
