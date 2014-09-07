package com.walgreens.pharmacy.ds;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.walgreens.pharmacy.rules.ErxTransaction;

@WebService(name="ErxDecisionServices", targetNamespace="http://services.walgreens.com/erx/DataEntryByPassService/ds")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL,
parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ErxDecisionServiceSoap {

	@WebMethod(operationName="getDrugSelection", action="urn:executeDrugSelection")
	public ErxTransaction getDrugSelection(ErxTransaction t);
	
	@WebMethod(operationName="getDrugSelectionAndQuantity", action="urn:executeDrugSelectionAndQuantity")
	public ErxTransaction getDrugSelectionAndQuantity(ErxTransaction t);
	
	@WebMethod(operationName="getQuantity", action="urn:executeQuantity")
	public ErxTransaction getQuantity(ErxTransaction t);
	

	@WebMethod(operationName="getDaySupply", action="urn:executeDaySupply")
	public ErxTransaction getDaySupply(ErxTransaction t);
	

	@WebMethod(operationName="getThirdParty", action="urn:executeThirdParty")
	public ErxTransaction geThirdParty(ErxTransaction t);
	
	
	@WebMethod(operationName="getDaySupplyAndThirdParty", action="urn:executeDaySupplyAndThirdParty")
	public ErxTransaction getDaySupplyAndThirdParty(ErxTransaction t);
}
