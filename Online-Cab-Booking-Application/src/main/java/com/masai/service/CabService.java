package com.masai.service;
import java.util.List;

import com.masai.dto.CabDTO;
import com.masai.exception.CabException;
import com.masai.exception.LoginException;
import com.masai.model.Cab;
import com.masai.model.CabType;

public interface CabService {
	
	public Cab insertCab(Cab cab);
	public Cab updateCab(Cab cab)throws CabException;
	public Cab deleteCab(int cabId)throws CabException;
	public List<Cab> viewCabsOfType(CabType cabType, String key)throws CabException, LoginException;
	public CabDTO countCabsOfType(CabType cabTpye,  String key)throws CabException, LoginException;

}
