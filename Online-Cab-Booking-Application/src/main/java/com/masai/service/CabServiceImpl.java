package com.masai.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exception.CabException;
import com.masai.model.Cab;
import com.masai.repository.CabDao;
import ch.qos.logback.core.joran.conditional.ElseAction;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	private CabDao cabDao;
	
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
	public List<Cab> viewCabsOfType(String cabType) throws CabException {
		
		List<Cab> cabList = cabDao.findByCabType(cabType);
		
		if(cabList.size() < 0) {	
			throw new CabException("Cab not found " + cabType);
		}
		
		return cabList;
					
	}
	

	@Override
	public Integer countCabsOfType(String cabTpye) throws CabException {
		
		Integer cabCount = cabDao.countCabByType(cabTpye);
		
		if(cabCount == 0) {
			throw new CabException("No Cab found with " + cabTpye);
		}
		return cabCount;
	}
	
	

}
