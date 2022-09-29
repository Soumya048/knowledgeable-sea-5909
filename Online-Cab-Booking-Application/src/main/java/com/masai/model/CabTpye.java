package com.masai.model;

import java.security.PublicKey;

public enum CabTpye {

	MINI, MICRO, MICRO_PREMIUM, SEDAN, SUV, SUV_PREMIUM;
	double taxiPrice;
	
	public void setPrice(double taxiPrice) {
		this.taxiPrice = taxiPrice;
		
	}
	
	public int personSettingCapacity() {
		
		switch(this){
		case MINI:
		   return 4;
		 case MICRO:
		    return 4;
         case MICRO_PREMIUM:
            return 4;
         case SEDAN:
            return 5;
         case SUV:
            return 6;
         case SUV_PREMIUM:
            return 7;
         default:
            return 4;
	}
  }
	
	public double getPrice() {
		taxiPrice = 8;
        switch (this) {
            case MINI:
               return taxiPrice *= 0.75;
            case MICRO:
               return taxiPrice *= 1;
            case MICRO_PREMIUM:
               return taxiPrice *= 1.75;
            case SEDAN:
               return taxiPrice *= 2;
            case SUV:
               return taxiPrice *= 3.75;
            case SUV_PREMIUM:
               return taxiPrice *= 6;
            default:
               return taxiPrice;
        }

	}
}