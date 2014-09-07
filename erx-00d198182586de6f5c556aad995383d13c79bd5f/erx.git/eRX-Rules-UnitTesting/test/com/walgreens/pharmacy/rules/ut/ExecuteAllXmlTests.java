package com.walgreens.pharmacy.rules.ut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.walgreens.pharmacy.ds.ErxRuleProcessing;
import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.ut.BaseTest;

/**
 * This class executes all the xml documents as input and report on the test
 * @author boyerje
 *
 */
public class ExecuteAllXmlTests extends BaseTest{

	private static List<String> testNames;
	private XStream xstream = new XStream();    
	private Properties props;
	private String testSrcFolder;
	  
	public ExecuteAllXmlTests(String containerName){
		testSrcFolder=datasets+"/"+containerName;
		xstream.alias("eRxTransaction", ErxTransaction.class);
		props = new Properties();
		try {
		    props.load(new FileInputStream(testSrcFolder+"/executor.properties"));
		    testNames=buildTestNameList(testSrcFolder);
		} catch (FileNotFoundException e){
	         e.printStackTrace();
		} catch (IOException e) {
		        e.printStackTrace();
		}
	}
	
	/**
	 * Return a list of string build from the the xml file names
	 * @param path where the xml files are
	 * @return
	 */
	private List<String> buildTestNameList(String path) {
		ArrayList<String> testNames = new ArrayList<String>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
		for (int i = 0; i < listOfFiles.length; i++) {
		   if (listOfFiles[i].isFile()) {
			   String files = listOfFiles[i].getName();
			   if (files.contains(".xml")) {
				   int end=files.indexOf('.');
				   String name=files.substring(0, end);
				   testNames.add(name);
			   }
		    }
		  }
		  return testNames;
	}
	
	public ErxTransaction loadDataSet(String filename){
		File f = new File(getTestSrcFolder()+"/"+filename+".xml");
		ErxTransaction t=(ErxTransaction)xstream.fromXML(f);
        return t;
	}
	
	/**
	 * First step helps to get the ruleset size
	 */
	public void executeFirstTest(String name){
		System.out.println("Execute "+name);
		ErxTransaction tx=loadDataSet(name);
		ErxTransaction txo=rp.processDrugSelection(tx);
		printRulesFired();
		
	}

	public void fireTests() {
		// first execute one test so we can get ruleset metrics
		Iterator<String> test = getTestNames().iterator();
		if (! test.hasNext()) return;
		executeFirstTest(test.next());
	}
	public void executeTest(List<String> tests) {
    	executeFirstTest(tests.get(0));
//		for (String t : tests.subList() {
//			ErxTransaction tx=loadDataSet(t);
//			
//		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage executor containerName");
			System.err.println("container name is the name of the folder containing the test data");
			System.exit(1);
		}
		
		ExecuteAllXmlTests executor= new ExecuteAllXmlTests(args[0]);
		executor.fireTests();
	} // main



	public static List<String> getTestNames() {
		return testNames;
	}

	public Properties getProps() {
		return props;
	}

	public String getTestSrcFolder() {
		return testSrcFolder;
	}

}
