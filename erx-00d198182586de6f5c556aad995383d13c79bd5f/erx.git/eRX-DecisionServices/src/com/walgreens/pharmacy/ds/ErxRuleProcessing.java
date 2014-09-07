package com.walgreens.pharmacy.ds;

import ilog.rules.res.model.IlrFormatException;
import ilog.rules.res.model.IlrPath;
import ilog.rules.res.session.IlrSessionCreationException;
import ilog.rules.res.session.IlrSessionException;
import ilog.rules.res.session.IlrSessionRequest;
import ilog.rules.res.session.IlrSessionResponse;
import ilog.rules.res.session.IlrStatelessSession;

import java.util.HashMap;
import java.util.Map;

import com.walgreens.pharmacy.rules.ErxTransaction;

public class ErxRuleProcessing extends BaseJRulesService{

	
	private IlrSessionRequest prepareSessionRequest(String rsName) throws IlrFormatException {
		
		IlrSessionRequest sessionRequest = getOrCreateFactory(getMode()).createRequest();
		sessionRequest.setRulesetPath(IlrPath.parsePath(rsName));
		// Ensure correct version of the ruleset is taken in account
		sessionRequest.setForceUptodate(true);			
		if (isTraceEnabled()) {
			sessionRequest.setTraceEnabled(true);
			sessionRequest.getTraceFilter().setInfoAllFilters(true);
		}		
		return sessionRequest;
	}
	private ErxTransaction executeRules(IlrSessionRequest sessionRequest,ErxTransaction tx){
		ErxTransaction resp = null;
		try {
			Map<String,Object> inputParameters = new HashMap<String,Object> ();
			inputParameters.put("erxTransaction", tx);
			sessionRequest.setInputParameters(inputParameters);
			IlrStatelessSession session  = getOrCreateFactory(getMode()).createStatelessSession();
			IlrSessionResponse sessionResponse = session.execute(sessionRequest);
			if (sessionResponse != null) {
				if (isTraceEnabled()) {
					manageTrace(sessionResponse.getRulesetExecutionTrace());			
				}
				resp = (ErxTransaction)sessionResponse.getOutputParameters().get("erxTransaction");
			}
		} catch (IlrSessionCreationException e) {
			e.printStackTrace();
		} catch (IlrSessionException e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public ErxTransaction processDrugSelection(ErxTransaction tx) {
		IlrSessionRequest sessionRequest;
		ErxTransaction resp = null;
	
			try {
				sessionRequest = prepareSessionRequest(getRulesetNames()[0]);
				resp=executeRules(sessionRequest,tx);
			} catch (IlrFormatException e) {
				e.printStackTrace();
			} 
			
		return resp;
	}

	public ErxTransaction processDrugQuantity(ErxTransaction tx) {
		IlrSessionRequest sessionRequest;
		ErxTransaction resp = null;
		try {
			sessionRequest = prepareSessionRequest(getRulesetNames()[1]); 
			resp=executeRules(sessionRequest,tx);		
		} catch (IlrFormatException e) {
			e.printStackTrace();
		}
		return resp;
	}

	public ErxTransaction processDaySupply(ErxTransaction tx) {
		IlrSessionRequest sessionRequest;
		ErxTransaction resp = null;
		try {
			sessionRequest = prepareSessionRequest(getRulesetNames()[2]); 
			resp=executeRules(sessionRequest,tx);		
		} catch (IlrFormatException e) {
			e.printStackTrace();
		}
		return resp;
	}
	public ErxTransaction processThirdParty(ErxTransaction tx) {
		IlrSessionRequest sessionRequest;
		ErxTransaction resp = null;
		try {
			sessionRequest = prepareSessionRequest(getRulesetNames()[3]); 
			resp=executeRules(sessionRequest,tx);		
		} catch (IlrFormatException e) {
			e.printStackTrace();
		}
		return resp;
	}
}
