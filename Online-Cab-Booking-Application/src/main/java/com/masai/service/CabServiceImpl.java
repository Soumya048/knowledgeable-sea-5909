package com.masai.service;
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CabException;
import com.masai.model.Cab;
import com.masai.repository.CabDao;

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
			throw  new CabException("Invalid Cab Details");
		
	}

	@Override
	public Cab deleteCab(int cabId) throws CabException {
		
		
				return null;
	}

	@Override
	public List<Cab> viewCabsOfType(String cabType) throws CabException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countCabsOfType(String cabTpye) throws CabException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
