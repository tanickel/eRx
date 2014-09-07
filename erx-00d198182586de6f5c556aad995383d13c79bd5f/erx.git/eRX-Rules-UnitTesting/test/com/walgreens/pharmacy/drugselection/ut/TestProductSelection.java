package com.walgreens.pharmacy.drugselection.ut;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.TranslatedDosageFormType;
import com.walgreens.pharmacy.rules.ut.factory.DrugFactory;
import com.walgreens.pharmacy.rules.ut.factory.StorePreferredDrugFactory;
import com.walgreens.pharmacy.ut.BaseTest;

public class TestProductSelection extends BaseTest {

	public TestProductSelection() {
	}
	
	@Test
	/**
	 * The prescription is about tablet drug, and as part of the same GPI there is one generic product
	 * so rule should have selected it
	 */
	public void verifyGenericStorePreferedSelected() {
		System.out.println("verifyGenericStorePreferedSelected");

		ErxTransaction tx= StorePreferredDrugFactory.buildERXtxTabletDrugPrecription();
		Drug d= DrugFactory.buildPreferredTabletGenericDrug();
		d.setNationalDrugCode("1234567890");
		tx.getSubstitutionDrugList().add(d);

		saveTxToXMLFile(tx,"GenericStorePreferedSelected.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		// the following assertion verify that the initialize orange book fired
		Assert.assertTrue(txo.isEnableOrangeBookCheck());
		// that the transmitted 
		Assert.assertEquals(TranslatedDosageFormType.TABLET,txo.getPrescription().getTransmittedDrug().getTranslatedDosageForm());
		// generic drug should be selected
		Assert.assertNotNull(txo.getSubstitutedDrug());
	    Assert.assertEquals("1234567890",txo.getSubstitutedDrug().getNationalDrugCode());
		// calculate total # of package
	    Assert.assertTrue(1==txo.getSubstitutedDrug().getTotalNumberOfPackages());
	}
}
