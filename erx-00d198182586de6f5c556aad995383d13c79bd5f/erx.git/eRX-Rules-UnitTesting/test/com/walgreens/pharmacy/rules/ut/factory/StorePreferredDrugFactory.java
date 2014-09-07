package com.walgreens.pharmacy.rules.ut.factory;

import java.util.HashMap;
import java.util.Map;

import com.walgreens.pharmacy.rules.CalculatedValues;
import com.walgreens.pharmacy.rules.CategoryType;
import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.ProductCategory;

public class StorePreferredDrugFactory extends DrugFactory {

	
	public StorePreferredDrugFactory(){
		super();
	}

	public Map<String,Drug> buildPreferredGenericProductMap(){
		Map<String,Drug> listOfDrugs = new HashMap<String,Drug>();
		listOfDrugs.put(buildPreferredGenericProductSelection().getNationalDrugCode(), buildPreferredGenericProductSelection());
		
		return listOfDrugs;
	}

	public CalculatedValues buildCalculatedValues(){
		CalculatedValues cv = new CalculatedValues();
		cv.setMaxDaysToFill(10);
		
		return cv;
	}
	
	public  Drug buildPreferredGenericProductSelection(){
		Drug d = super.buildActiveTabletDrug();
		d.setNationalDrugCode(incrementNDC(d.getNationalDrugCode()));
		d.setDrugId(incrementDrugId(d.getDrugId()));
		d.setOrangeBookRating("B"); //Orange book A goes manual
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.Controlling);
		d.addProductCategory(pc);
		d.setStorePreferredDrugId("123");
		return d;
	}
	
	public Drug buildUnitOfUseBrand(){
		Drug d = super.buildActiveTabletDrug();
		d.setNationalDrugCode(incrementNDC(d.getNationalDrugCode()));
		d.setDrugId(incrementDrugId(d.getDrugId()));
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.StorePreferred);
		d.addProductCategory(pc);
		d.setPackageType(PackageType.UNIT_OF_USE);
		d.setType(DrugType.BRAND);
		

		return d;
	}
	
	public Drug buildUnitOfUseGeneric(){
		Drug d = super.buildActiveTabletDrug();
		//d.setNationalDrugCode(incrementNDC(d.getNationalDrugCode()));
		//d.setDrugId(incrementDrugId(d.getDrugId()));
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.Controlling);
		d.addProductCategory(pc);
		d.setPackageType(PackageType.UNIT_OF_USE);
		d.setType(DrugType.GENERIC);
		return d;
	}

	
}
