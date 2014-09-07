package com.walgreens.pharmacy.rules.ut.test.productselection;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.Prescription;
import com.walgreens.pharmacy.rules.ut.factory.ControllingDrugFactory;
import com.walgreens.pharmacy.rules.ut.factory.PrescriptionFactory;
import com.walgreens.pharmacy.rules.ut.factory.StorePreferredDrugFactory;
import com.walgreens.pharmacy.rules.ut.factory.TransactionFactory;
import com.walgreens.pharmacy.ut.BaseTest;


public class TestProductSelectionControlling extends BaseTest {

	public TestProductSelectionControlling() {
		// TODO Auto-generated constructor stub
	}

	@Test
	/*
	 * * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public  void testUnitOfUseBrandMultiplePackageSize(){
		System.out.println("testUnitOfUseBrand");
		
		ErxTransaction tx = TransactionFactory.buildBasicERXtx();
		//Populate the Transmitted Drug
		tx.setPrescription(PrescriptionFactory.buildPopulatedTransmittedDrugPrescription());
		
		ControllingDrugFactory df = new ControllingDrugFactory();	
		Drug d = df.buildUnitOfUseBrand();
		d.setTransmittedQuantityIsMultipleOfPackageSize(true);
		d.setPackageSize(20);
		d.setTotalNumberOfPackages(2.0); // = transmitted qty X package size of substituted drug
		
		tx.addControllingDrug(d);

		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(DrugType.BRAND,txo.getSubstitutedDrug().getType());
		Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
		// the following assertion verify transmitted quantity is multiple
		Assert.assertTrue(txo.getSubstitutedDrug().isTransmittedQuantityIsMultipleOfPackageSize());
		// that the substituted drug package size more than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
		// the total package size less than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3);
	
	}
	
	@Test
	/*
	 * * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public  void testUnitOfUseBrandSpecifiedQty(){
		System.out.println("test-UnitOfUse-Brand-SpecifiedQty");
		
		ErxTransaction tx =  TransactionFactory.buildBasicERXtx();
		//Populate the Transmitted Drug
		Prescription p = PrescriptionFactory.buildPopulatedTransmittedDrugPrescription();
		p.setQuantityPrescribed(2);
		tx.setPrescription(p);
		
		ControllingDrugFactory df = new ControllingDrugFactory();	
		Drug d = df.buildUnitOfUseBrand();
		d.setTransmittedQuantityIsMultipleOfPackageSize(true);
		d.setPackageSize(6);
		d.setTotalNumberOfPackages(1.0); // = transmitted qty X package size of substituted drug
		
		tx.addControllingDrug(d);

		
		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		
		Assert.assertEquals(DrugType.BRAND,txo.getSubstitutedDrug().getType());
		Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
		// that the substituted drug package size more than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
		// the total package size less than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3);
	
	}
	
	@Test
	/*
	 * * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public  void testUnitOfUseGenericMultiplePackageSize(){
		System.out.println("test-UnitOfUse-Generic-MultiplePackageSize");
		
		ErxTransaction tx =  TransactionFactory.buildBasicERXtx();
		//Populate the Transmitted Drug
		//Populate the Transmitted Drug
		Prescription p = PrescriptionFactory.buildPopulatedTransmittedDrugPrescription();
		p.setQuantityPrescribed(2);
		tx.setPrescription(p);
			
		ControllingDrugFactory df = new ControllingDrugFactory();	
		Drug d = df.buildUnitOfUseGeneric();
		d.setTransmittedQuantityIsMultipleOfPackageSize(true);
		d.setPackageSize(20);
		d.setTotalNumberOfPackages(2.0); // = transmitted qty X package size of substituted drug
		
		tx.addControllingDrug(d);

		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(DrugType.GENERIC,txo.getSubstitutedDrug().getType());
		Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
		// the following assertion verify transmitted quantity is multiple
		Assert.assertTrue(txo.getSubstitutedDrug().isTransmittedQuantityIsMultipleOfPackageSize());
		// that the substituted drug package size more than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
		// the total package size less than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3);
	
	}
	
	@Test
	/*
	 * * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public  void testUnitOfUseGenericSamePackageSize(){
		System.out.println("test-UnitOfUse-Generic-SamePackageSize");
		
		ErxTransaction tx =  TransactionFactory.buildBasicERXtx();
		//Populate the Transmitted Drug
		//Populate the Transmitted Drug
		Prescription p = PrescriptionFactory.buildPopulatedTransmittedDrugPrescription();
		p.setQuantityPrescribed(10);
		tx.setPrescription(p);
			
		ControllingDrugFactory df = new ControllingDrugFactory();	
		Drug d = df.buildUnitOfUseGeneric();

		d.setPackageSize(10);
		d.setTotalNumberOfPackages(2.0); // = transmitted qty X package size of substituted drug
		
		tx.addControllingDrug(d);

		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		Assert.assertEquals(DrugType.GENERIC,txo.getSubstitutedDrug().getType());
		Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
		// that the substituted drug package size more than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
		// the total package size less than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3);
	
	}
	
	@Test
	/*
	 * * Validate that the initialized parameters at the beginning of the flow are set
	 */
	public  void testUnitOfUseGenericSpecifiedQty(){
		System.out.println("test-UnitOfUse-Generic-SpecifiedQty");
		
		ErxTransaction tx =  TransactionFactory.buildBasicERXtx();
		//Populate the Transmitted Drug
		Prescription p = PrescriptionFactory.buildPopulatedTransmittedDrugPrescription();
		p.setQuantityPrescribed(2);
		tx.setPrescription(p);
		
		StorePreferredDrugFactory spdf = new StorePreferredDrugFactory();	
		Drug d = spdf.buildUnitOfUseGeneric();
		d.setPackageSize(6);
		d.setTotalNumberOfPackages(1.0); // = transmitted qty X package size of substituted drug
		
		tx.addControllingDrug(d);

		
		//saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		
		Assert.assertEquals(DrugType.GENERIC,txo.getSubstitutedDrug().getType());
		Assert.assertEquals(PackageType.UNIT_OF_USE,txo.getSubstitutedDrug().getPackageType());
		// that the substituted drug package size more than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getPackageSize() >= 3);
		// the total package size less than 3
		Assert.assertTrue(txo.getSubstitutedDrug().getTotalNumberOfPackages() <= 3);
	
	}

}
