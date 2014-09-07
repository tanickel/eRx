/**
 *   Licensed Materials - Property of IBM
  5724X98 5724Y15 5655V82 5724X99 5724Y16 5655V89 5725B69 5655W88 5725C52 5655W90 5655Y31
  Copyright IBM Corp. 1987, 2013 All Rights Reserved
  US Government Users Restricted Rights - Use, duplication or
  disclosure restricted by GSA ADP Schedule Contract with
  IBM Corp.
*/
package com.walgreens.pharmacy.ds;

import ilog.rules.res.session.IlrJ2SESessionFactory;
import ilog.rules.res.session.IlrPOJOSessionFactory;
import ilog.rules.res.session.IlrSessionFactory;
import ilog.rules.res.session.ruleset.IlrExecutionEvent;
import ilog.rules.res.session.ruleset.IlrExecutionTrace;
import ilog.rules.res.session.ruleset.IlrRuleEvent;
import ilog.rules.res.session.ruleset.IlrRuleInformation;
import ilog.rules.res.session.ruleset.IlrTaskEvent;

import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


/**
 * Group together common attributes and methods needed to use JRules RES
 * @author boyerje
 */
public class BaseJRulesService {
	 Logger logger = Logger.getLogger(this.getClass().getName());
	// JRules API.  Rule session factory is the entry point for connections to the Rule Execution Server, it is used to create stateless session
	protected static  IlrSessionFactory sessionFactory;
	// when unit testing we can use the trace to get the list of rules fired
	protected boolean traceEnabled=false;
	// when trace is one, lets keep it	
	protected IlrExecutionTrace lastExecutionTrace = null;
	// to keep the properties as read from ruleprocessing.properties
	protected Properties internalProperties;
	protected String[] rulesetNames=new String[10];
	protected int numberOfRulesets=1;
	// POJO or J2SE
	protected RuleEngineInvocationType mode;
	
	public BaseJRulesService(){
		try {
			init("ruleprocessing.properties");
		} catch (Exception e) {		
			e.printStackTrace();
			mode=RuleEngineInvocationType.J2SE;
			setTraceEnabled(true);
		}
	}
   
	protected void init(String propFileName) throws Exception {
		internalProperties = getAsProperties("ruleprocessing.properties");
		setTraceEnabled(Boolean.valueOf((String)getInternalProperties().get("TraceEnabled")));
		numberOfRulesets=Integer.parseInt((String)getInternalProperties().get("NumberOfRulesets"));
		for (int i=1; i<=numberOfRulesets;i++){
			rulesetNames[i-1]=(String)getInternalProperties().get("RulesetName"+i);
		}
	
		mode=RuleEngineInvocationType.valueOf((String)getInternalProperties().get("RuleEngineInvocationType"));
		logger.info("Decision Service processing init(). Trace:"+isTraceEnabled()+" "+rulesetNames);
    }
   
   
	public static Properties getAsProperties(String name) throws Exception {
		Properties props = new Properties();
		URL url = getAsUrl(name);
		if (url != null) {
			props.load(url.openStream());
		}
		return props;
	}


	
	public static URL getAsUrl(String name) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResource(name);
	}
	
	 public static synchronized 
	     IlrSessionFactory getOrCreateFactory(RuleEngineInvocationType ruleEngineInvocationType) {
	     if (sessionFactory == null) {
	          if (ruleEngineInvocationType == RuleEngineInvocationType.J2SE) {
	        	 sessionFactory = createJ2SESessionFactory();
	         } else if (ruleEngineInvocationType == RuleEngineInvocationType.POJO) {
	        	 sessionFactory = new IlrPOJOSessionFactory();
	         }
	     }
	
	     if (sessionFactory == null) {
	         String errorMsg = "Could not create an IlrSessionFactory";
	         throw new RuntimeException(errorMsg);
	     }
	     return sessionFactory;
	 }
	 


    private static IlrSessionFactory createJ2SESessionFactory() {
        PrintWriter writer = new PrintWriter(System.out);
        IlrJ2SESessionFactory sessionFactory = new IlrJ2SESessionFactory();
        sessionFactory.setOutput(writer);
        return sessionFactory;
    }
	    
    public void manageTrace(IlrExecutionTrace ruleTrace){
  		lastExecutionTrace =ruleTrace;
  		logger.info("@@ Time to process rules:"+ruleTrace.getExecutionDuration());
  		// get the rule definition of the ruleset executed
  		processRuleExecutionEvent(ruleTrace.getRules(),ruleTrace.getExecutionEvents());
  	}
  	    
      private void processRuleExecutionEvent(Map<String, IlrRuleInformation> allRules,List<IlrExecutionEvent> executionEvents){
  		for (IlrExecutionEvent event : executionEvents) {
  			if (event instanceof IlrRuleEvent) {
  				IlrRuleInformation r=allRules.get(event.getName());
  				logger.info(r.getBusinessName());
  			} else {
  				List<IlrExecutionEvent> subEvents = ((IlrTaskEvent) event).getSubExecutionEvents();
  				processRuleExecutionEvent(allRules,subEvents);
  			}
  		}  
  	}
	    
	public Properties getInternalProperties() {
		return internalProperties;
	} 

	public boolean isTraceEnabled() {
		return traceEnabled;
	}

	public void setTraceEnabled(boolean traceEnabled) {
		this.traceEnabled = traceEnabled;
	}

	public IlrExecutionTrace getLastExecutionTrace() {
		return lastExecutionTrace;
	}

	public String[] getRulesetNames() {
		return rulesetNames;
	}

	public void setRulesetNames(String[] rulesetName) {
		this.rulesetNames = rulesetName;
	}

	public RuleEngineInvocationType getMode() {
		return mode;
	}
	
}
