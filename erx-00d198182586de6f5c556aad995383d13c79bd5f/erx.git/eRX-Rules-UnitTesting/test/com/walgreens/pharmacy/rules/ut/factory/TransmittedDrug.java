package com.walgreens.pharmacy.rules.ut.factory;

import com.walgreens.pharmacy.rules.CategoryType;
import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugClassType;
import com.walgreens.pharmacy.rules.DrugConstraint;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ProductCategory;
import com.walgreens.pharmacy.rules.StatusType;
import com.walgreens.pharmacy.rules.TranslatedDosageFormType;

public class TransmittedDrug extends DrugFactory {
	/**
	 * A basic drug to unit test. Each UT will override value to trigger rule conditions
	 * @return
	 */
	public static Drug buildTransmittedDrug(){
		Drug d=new Drug();
		d.setDataSource("IC+");
		d.setPackageSize(30);
		d.setDrugId("646070");
		d.setNationalDrugCode("00009004516");
		d.setDosageFormCode("TABS");
		d.setGenericProductIdentifier("5816007000305");
		d.setTranslatedDosageForm(TranslatedDosageFormType.TABLET);
		d.setType(DrugType.UNSPECIFIED);
		
		d.setPackageQuantity(1);
		d.setPackageSize(40);
		d.setTotalNumberOfPackages(2.0);
			
		DrugConstraint sr = new DrugConstraint();
		sr.setDrugClass(DrugClassType.RX);
		sr.setMaxDaysToFill(20);
		d.setStatus(StatusType.ACTIVE);
		sr.setTotalFillsAllowed("200");
		d.setStateDrugConstraint(sr);
		ProductCategory pc = new ProductCategory();
		pc.setCategory(CategoryType.Controlling);
		pc.setDrugId(d.getDrugId());
		d.addProductCategory(pc);
		return d;
	}
	

}
