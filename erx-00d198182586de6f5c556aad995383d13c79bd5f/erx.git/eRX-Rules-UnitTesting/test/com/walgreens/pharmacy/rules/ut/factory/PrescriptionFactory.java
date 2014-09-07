package com.walgreens.pharmacy.rules.ut.factory;

import java.util.Date;

import com.walgreens.pharmacy.rules.Prescription;
import com.walgreens.pharmacy.rules.StateType;
import com.walgreens.pharmacy.rules.Store;
import com.walgreens.pharmacy.ut.DateUtilities;

public class PrescriptionFactory {
	
	/**
	 * Define on transmitted drug 
	 * @return
	 */
	public static Prescription buildPopulatedTransmittedDrugPrescription(){
		Prescription p = new Prescription();
		Store s = new Store();
		s.setState(StateType.IL);
		p.setStore(s);
		p.setOriginalDate(DateUtilities.makeDate(2014,8,11));
        p.setTransmittedNdc("00009004516");
        p.setOriginalDate(new Date(8-30-2014));
        // Set the transmitted Drug
        p.setTransmittedDrug(TransmittedDrug.buildTransmittedDrug());
        
        p.setQuantityPrescribed(15);
      		return p;
	}
	
	public static void validateNull(Object o){
		if(o == null)
			System.out.println("Null Object");
		else
			System.out.println("Object is Not Null");	
	}
}
