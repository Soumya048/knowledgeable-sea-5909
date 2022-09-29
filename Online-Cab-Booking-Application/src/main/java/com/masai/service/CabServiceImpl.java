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
	private CabDao dao;
	
	@Override
	public Cab insertCab(Cab cab) {
		
		Cab saveCab = dao.save(cab);
		
		return saveCab;
	}

	@Override
	public Cab updateCab(Cab cab) throws CabException {
		
		Optional<Cab> opt = dao.findById(cab.getCabId());
		
		if(opt.isPresent()) {
			
			return dao.save(cab);
		}
		
		else 
			
		  throw new CabException("Invalid Cab Details");
	}

	@Override
	public Cab deleteCab(int cabId) throws CabException {
		
    Optional<Cab> opt = dao.findById(cabId);
		
		if(opt.isPresent()) {
			
			Cab existingCab = opt.get();
			 
			 dao.delete(existingCab);
			
			 return existingCab;
		}else
			
			throw new CabException("Cab does not exist with this Id :"+cabId);
	}

	@Override
	public List<Cab> viewCabsOfType(String cabType) throws CabException {
		
		List<Cab> list = dao.findAllByType(cabType);
		
		if(list.size() < 0) {
			
			throw new CabException("Cab did not find");
		}
		return list;
					
	}
	

	@Override
	public int countCabsOfType(String cabTpye) throws CabException {
		
		return 0;
	}
	
	

}
