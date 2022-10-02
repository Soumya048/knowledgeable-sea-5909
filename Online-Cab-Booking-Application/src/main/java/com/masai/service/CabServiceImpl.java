package com.masai.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.dto.CabDTO;
import com.masai.exception.CabException;
import com.masai.exception.LoginException;
import com.masai.model.AdminSession;
import com.masai.model.Cab;
import com.masai.model.CabType;
import com.masai.repository.AdminSessionDao;
import com.masai.repository.CabDao;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	private CabDao cabDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Override
	public Cab insertCab(Cab cab) {
		
		Cab saveCab = cabDao.save(cab);
		
		return saveCab;
	}

	@Override
	public Cab updateCab(Cab cab) throws CabException {
		
		Optional<Cab> opt = cabDao.findById(cab.getCabId());
		
		if(opt.isPresent()) {
			
			return cabDao.save(cab);
		}
		
		else 
		  throw new CabException("Invalid Cab Details");
	}

	@Override
	public Cab deleteCab(int cabId) throws CabException {
		
		Optional<Cab> opt = cabDao.findById(cabId);
		
		if(opt.isPresent()) {
			
			Cab existingCab = opt.get();
			 
			cabDao.delete(existingCab);
			
			return existingCab;
		}
		else
			throw new CabException("Cab does not exist with this Id :"+cabId);
	}

	@Override
	public List<Cab> viewCabsOfType(CabType cabType, String key) throws CabException, LoginException {
		
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(!opt.isPresent()) 
			throw new LoginException("Admin is not logged in, Please Log in first");
			
			
		List<Cab> cabList = cabDao.findByCabType(cabType);
		
		if(cabList.size() < 0) {	
			throw new CabException("Cab not found " + cabType);
		}
		return cabList;
					
	}

	
	@Override
	public CabDTO countCabsOfType(CabType cabTpye, String key) throws CabException, LoginException {
		
		Optional<AdminSession> opt = adminSessionDao.findByUuid(key);
		
		if(!opt.isPresent()) 
			throw new LoginException("Admin is not logged in, Please Log in first");
		
		Integer cabCount = cabDao.countCabByType(cabTpye);
		
		if(cabCount == 0) {
			throw new CabException("No Cab found with " + cabTpye);
		}
		
		CabDTO cabDto = new CabDTO();
		cabDto.setCabtype(cabTpye);
		cabDto.setCabTypeCount(cabCount);
		
		return cabDto;
	}
	
	

}
