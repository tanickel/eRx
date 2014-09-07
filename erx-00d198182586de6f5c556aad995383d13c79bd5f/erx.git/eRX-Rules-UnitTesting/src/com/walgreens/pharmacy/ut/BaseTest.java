package com.walgreens.pharmacy.ut;

import ilog.rules.res.session.ruleset.IlrExecutionEvent;
import ilog.rules.res.session.ruleset.IlrExecutionTrace;
import ilog.rules.res.session.ruleset.IlrRuleEvent;
import ilog.rules.res.session.ruleset.IlrRuleInformation;
import ilog.rules.res.session.ruleset.IlrTaskEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.walgreens.pharmacy.ds.ErxRuleProcessing;
import com.walgreens.pharmacy.rules.ErxTransaction;

public class BaseTest {
    protected ErxRuleProcessing rp = new ErxRuleProcessing();
    protected static XStream xstream = new XStream();    
    protected static final String datasets = "dataset";
	protected Map<String, IlrRuleInformation> allRules = null;
	
    public BaseTest(){
    	xstream.alias("eRxTransaction", ErxTransaction.class);
    }
    
    public String txToXML(ErxTransaction tx){
    	return xstream.toXML(tx);
    }
   
    public void saveTxToXMLFile(ErxTransaction tx,String filename) {
    	FileWriter f;
		try {
			f = new FileWriter("dataset/"+filename);
			xstream.toXML(tx, f);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public ErxTransaction loadDataSet(String filename){
    	File f = new File("dataset/"+filename);
        return (ErxTransaction)xstream.fromXML(f);
    }
    
	public  void printRulesFired(){
		System.out.println(" Printing execution trace ");
		printRulesFired(rp.getLastExecutionTrace());
		float c=(rp.getLastExecutionTrace().getTotalRulesFired()/totalNumberOfRules())*100;
		System.out.println(" Number of rules :"+totalNumberOfRules()+" executed:"+rp.getLastExecutionTrace().getTotalRulesFired()
				+ " coverage:"+c);
	}
	
	
	protected   void printRulesFired(IlrExecutionTrace rsExecTrace) {
		if (rsExecTrace == null) {
			System.out.println(" No rule execution trace ");
			return;
		}
		printRulesFired(rsExecTrace, rsExecTrace.getExecutionEvents(),0);
	}

	public void buildRuleSetRuleList(IlrExecutionTrace rsExecTrace){
		if (allRules == null) {
			allRules = rsExecTrace.getRules();	
		}
	}
	
	public int totalNumberOfRules(){
		if (allRules != null) {
			// TODO should we group a DT as one rule? as of now one row is a rule
			return allRules.size();
		}
		return 0;
	}
	
	protected  void printRulesFired(IlrExecutionTrace rsExecTrace, List<IlrExecutionEvent> executionEvents, int indent) {
		buildRuleSetRuleList(rsExecTrace);
		if (executionEvents != null && allRules != null) {
			for (IlrExecutionEvent event : executionEvents) {
				for (int i=0;i<indent;i++) System.out.print("  ");
				if (event instanceof IlrRuleEvent) {
					IlrRuleInformation r=allRules.get(event.getName());
					System.out.println("-- " + r.getBusinessName() +"\tStatus:"+r.getProperties().get("status"));
		       } else {
		    	   System.out.println("-- " + event.getName());
		    	   List<IlrExecutionEvent> subEvents = ((IlrTaskEvent) event).getSubExecutionEvents();
		    	   printRulesFired(rsExecTrace, subEvents,  indent+1);
		       }
			}
		}
	}
	
	
	
} // class
