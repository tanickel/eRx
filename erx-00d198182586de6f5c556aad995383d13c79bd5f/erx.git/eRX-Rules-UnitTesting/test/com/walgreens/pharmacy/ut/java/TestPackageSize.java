package com.walgreens.pharmacy.ut.java;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.walgreens.pharmacy.rules.Drug;
import com.walgreens.pharmacy.rules.ErxTransaction;



public class TestPackageSize {

		private Collection<Drug> aList = new ArrayList<Drug>();
		@Before
		public void setup(){
			//List<Drug> dList = new ArrayList<Drug>();
			Drug d1 = new Drug();
			Drug d2 = new Drug();
			Drug d3 = new Drug();
			
			
			d1.setDrugId("1");
			d1.setTotalNumberOfPackages(1.0);
			d2.setDrugId("2");
			d2.setTotalNumberOfPackages(2.0);
			d3.setDrugId("3");
			d3.setTotalNumberOfPackages(4.0);
			//d4.setTotalNumberOfPackages(2.0);
			aList.add(d1);
			aList.add(d2);
			aList.add(d3);
		}
		
		/**
		 * Validate that the initialized parameters at the beginning of the flow are set
		 */
		@Test
		public void testLargestPackageSize(){
			System.out.println("testLargestPackageSize - List " + aList.size());
		
				
			// the following assertion verify that the initialize orange book fired
		
			Assert.assertEquals(4.0,ErxTransaction.retainLargestPackageSizeLessThan(aList,3));

		}
	
		@Test
		public void testLargestPackageSizeTwoOfTheSame(){
			// the following assertion verify that the initialize orange book fired
			Drug d = new Drug();
			d.setDrugId("2a");
			d.setTotalNumberOfPackages(2.0);
			aList.add(d);

			
			System.out.println("/n testLargestPackageSizeTwoOfTheSame - List " + aList.size());
			
			Assert.assertEquals(4,ErxTransaction.retainLargestPackageSizeLessThan(aList,3));

		}
	

		
}
