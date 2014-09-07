package com.walgreens.pharmacy.ft;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.walgreens.services.erx.dataentrybypassservice.ds.ErxDecisionServices;
import com.walgreens.services.erx.dataentrybypassservice.ds.ErxDecisionServices_Service;
import com.walgreens.services.erx.dataentrybypassservice.ds.ErxTransaction;

public class TestDrugSelection {

	public static ErxDecisionServices serv;
	
	@BeforeClass
	public static void init(){
		serv = new ErxDecisionServices_Service().getErxDecisionServicePort();
	}
	
	@Test
	public void test() {
		ErxTransaction t = DrugFactory.buildERXtxTabletDrugPrecription();
		ErxTransaction to=serv.getDrugSelection(t);
		Assert.assertNotNull(to);
		Assert.assertNotNull(to.getPrescription().getTransmittedDrug());
		Assert.assertNotNull(to.getSubstitutedDrug());
	}

}
