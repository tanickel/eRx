package com.walgreens.pharmacy.rules.ut.test.prodselection.storepreferred;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.Prescription;
import com.walgreens.pharmacy.rules.ResultType;
import com.walgreens.pharmacy.rules.ut.factory.PrescriptionFactory;
import com.walgreens.pharmacy.rules.ut.factory.StorePreferredDrugFactory;
import com.walgreens.pharmacy.rules.ut.factory.TransactionFactory;
import com.walgreens.pharmacy.ut.BaseTest;

public class Test_SP_UnitOfUse_Generic_SpecifiedQty extends BaseTest{
	@Test
	/*
	 * * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public  void testUnitOfUseGenericSpecifiedQty(){
		System.out.println("test-UnitOfUse-Generic-SpecifiedQty-StorePreferred");
		
		ErxTransaction tx = TransactionFactory.buildBasicERXtx();
		//Populate the Transmitted Drug
		Prescription p = PrescriptionFactory.buildPopulatedTransmittedDrugPrescription();
		p.setQuantityPrescribed(2);
		tx.setPrescription(p);
		
		PrescriptionFactory.validateNull(p.getTransmittedDrug());
		
		StorePreferredDrugFactory spdf = new StorePreferredDrugFactory();	
		Drug d = spdf.buildUnitOfUseGeneric();
		d.setPackageSize(6);
		d.setTotalNumberOfPackages(1.0); // = transmitted qty X package size of substituted drug
		
		//Add Store Preferred Drug
		tx.addStorePreferredDrug(d);

		
		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		System.out.println("Print Substituted Drug " +txo.getSubstitutedDrug().toString());
		
		
		if (tx.getSubstitutedDrug()!=null){
			Assert.assertEquals(DrugType.GENERIC,txo.getSubstitutedDrug().getType());
			Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
			// that the substituted drug package size more than 3
			Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
			// the total package size less than 3
			Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3);
			
			System.out.println(txo.getSubstitutedDrug().toString());
			System.out.println("Completion Status " + txo.getCompletionStatus());
			Assert.assertEquals(ResultType.COMPLETE,txo.getCompletionStatus());
			
		}else{
			System.out.println("#####  No substituted Drug Selected  #####");
		}
		System.out.println("Completion Status " + txo.getCompletionStatus());
	}

}
