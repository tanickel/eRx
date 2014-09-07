package com.walgreens.pharmacy.rules.ut.factory;

import com.walgreens.pharmacy.rules.CalculatedValues;
import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.ProductCategory;

public class PreferredGenericDrugFactory extends DrugFactory{
	
	public PreferredGenericDrugFactory(){
		super();
	}

	public CalculatedValues buildCalculatedValues(){
		CalculatedValues cv = new CalculatedValues();
		cv.setMaxDaysToFill(10);
		
		return cv;
	}
	
	public  Drug buildPreferredGenericProductSelection(){
		Drug d = super.buildPreferredTabletGenericDrug();
		d.setNationalDrugCode(incrementNDC(d.getNationalDrugCode()));
		d.setDrugId(incrementDrugId(d.getDrugId()));
		d.setOrangeBookRating("B"); //Orange book A goes manual
		ProductCategory pc = new ProductCategory();
		
		//pc.setCategory(CategoryType.Controlling);
		//d.addProductCategory(pc);
		d.setStorePreferredDrugId("123");
		return d;
	}
	

}
