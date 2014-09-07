package com.walgreens.pharmacy.ds;

import javax.jws.WebService;

import com.walgreens.pharmacy.rules.ErxTransaction;
import com.walgreens.pharmacy.rules.ResultType;

@WebService(serviceName="ErxDecisionServices",
portName = "ErxDecisionServicePort",
endpointInterface = "com.walgreens.pharmacy.ds.ErxDecisionServiceSoap",
targetNamespace = "http://services.walgreens.com/erx/DataEntryByPassService/ds")
public class ErxDecisionServiceImpl implements ErxDecisionServiceSoap {

	private ErxRuleProcessing rp = new ErxRuleProcessing();
	
	private void sendTraceToDecisionWarehouse(){
		
	}
	
	@Override
	public ErxTransaction getDrugSelection(ErxTransaction t) {
	     ErxTransaction to=rp.processDrugSelection(t);	
		//TODO Add call to JMS queue to post rule execution post traces
	     sendTraceToDecisionWarehouse(); 
		return to;
	}


	@Override
	public ErxTransaction getDrugSelectionAndQuantity(ErxTransaction t) {
	    ErxTransaction to=rp.processDrugSelection(t);	
        if (!to.getCompletionStatus().equals(ResultType.MANUAL)) {
        	to=rp.processDrugQuantity(to);
        }
        sendTraceToDecisionWarehouse(); 
		return to;
	}

	@Override
	public ErxTransaction getQuantity(ErxTransaction t) {
		ErxTransaction to=rp.processDrugQuantity(t);
		sendTraceToDecisionWarehouse(); 
		return to;
	}

	@Override
	public ErxTransaction getDaySupply(ErxTransaction t) {
		ErxTransaction to=rp.processDaySupply(t);
		sendTraceToDecisionWarehouse(); 
		return to;
	}

	@Override
	public ErxTransaction geThirdParty(ErxTransaction t) {
		ErxTransaction to=rp.processThirdParty(t);
		sendTraceToDecisionWarehouse(); 
		return to;
	}

	@Override
	public ErxTransaction getDaySupplyAndThirdParty(ErxTransaction t) {
		ErxTransaction to=rp.processDaySupply(t);	
        if (!to.getCompletionStatus().equals(ResultType.MANUAL)) {
        	to=rp.processThirdParty(to);
        }
        sendTraceToDecisionWarehouse(); 
		return to;
	}

}
