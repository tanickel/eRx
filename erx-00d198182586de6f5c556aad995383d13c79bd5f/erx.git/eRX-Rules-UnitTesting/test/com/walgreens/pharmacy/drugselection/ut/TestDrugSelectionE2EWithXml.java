package com.walgreens.pharmacy.drugselection.ut;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.walgreens.pharmacy.rules.CategoryType;
import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.DrugType;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.PackageType;
import com.walgreens.pharmacy.rules.ProductCategory;
import com.walgreens.pharmacy.rules.ut.factory.DrugFactory;
import com.walgreens.pharmacy.ut.BaseTest;

/**
 * Use the drug factory to create a model that makes all the rules in drug selection ruleset succeed.
 * @author boyerje
 *
 */
public class TestDrugSelectionE2EWithXml extends BaseTest{

	@Test
	public void createGoodData() throws IOException{
		   ErxTransaction tx= DrugFactory.buildERXtxTabletDrugPrecription();
		   // add a generic drug to get it 
		   Drug d = DrugFactory.buildActiveTabletDrug();
			d.setNationalDrugCode("1234567890");
			d.setDrugId(tx.getSubstitutionDrugList().get(0).getDrugId());
			ProductCategory pc = new ProductCategory();
			pc.setCategory(CategoryType.StorePreferred);
			pc.setDrugId(d.getDrugId());
			d.addProductCategory(pc);
			d.setPackageType(PackageType.UNIT_OF_USE);
			d.setType(DrugType.GENERIC);
			tx.getSubstitutionDrugList().add(d);
		   saveTxToXMLFile(tx, "DrugSelectionE2E.xml");
	}
	
	@Test
	public void testFromXmlDoc() {
		ErxTransaction tx=loadDataSet("DrugSelectionE2E.xml");
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		// that the transmitted 
		Assert.assertNotNull(txo.getPrescription().getTransmittedDrug());
		Assert.assertNotNull(txo.getSubstitutedDrug());
	}

}
