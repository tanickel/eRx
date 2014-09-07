package com.walgreens.pharmacy.rules.ut.test.prodselection.storepreferred;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.ut.factory.PrescriptionFactory;
import com.walgreens.pharmacy.rules.ut.factory.StorePreferredDrugFactory;
import com.walgreens.pharmacy.rules.ut.factory.TransactionFactory;
import com.walgreens.pharmacy.ut.BaseTest;

public class Test_SP_UnitOfUse_Brand_MultiPkgSize extends BaseTest{
	
	@Test
	public  void testUnitOfUseBrandMultiplePackageSize(){
		System.out.println("test-UnitOfUse-Brand-MultiplePackageSize-StorePreferred");
	
		ErxTransaction tx = TransactionFactory.buildBasicERXtx();
		
		//Populate the Transmitted Drug
		tx.setPrescription(PrescriptionFactory.buildPopulatedTransmittedDrugPrescription());
		
		StorePreferredDrugFactory spdf = new StorePreferredDrugFactory();	
		Drug d = spdf.buildUnitOfUseBrand();
		d.setTransmittedQuantityIsMultipleOfPackageSize(true);
		d.setPackageSize(20);
		d.setTotalNumberOfPackages(3.0); // = transmitted qty X package size of substituted drug

		//Add Store Preferred Drug
		tx.addStorePreferredDrug(d);

		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
	
		
		if (tx.getSubstitutedDrug()!=null){
			Assert.assertEquals(DrugType.BRAND,txo.getSubstitutedDrug().getType());
			Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
			// the following assertion verify transmitted quantity is multiple
			Assert.assertTrue(txo.getSubstitutedDrug().isTransmittedQuantityIsMultipleOfPackageSize());
			// that the substituted drug package size more than 3
			Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
			// the total package size less than 3
			Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3.0);
			
			System.out.println(txo.getSubstitutedDrug().toString());
			System.out.println("Completion Status " + txo.getCompletionStatus());
			
			Assert.assertEquals(ResultType.COMPLETE,txo.getCompletionStatus());
			
		}else{
			System.out.println("#####  No substituted Drug Selected  #####");
		}
		
	}

}
