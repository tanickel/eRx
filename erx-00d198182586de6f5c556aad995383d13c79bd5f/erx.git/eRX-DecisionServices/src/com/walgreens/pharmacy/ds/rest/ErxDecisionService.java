package com.walgreens.pharmacy.ds.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.walgreens.pharmacy.ds.ErxDecisionServiceImpl;
import com.walgreens.pharmacy.rules.ErxTransaction;

@Path("/erxtransaction")
public class ErxDecisionService {
	ErxDecisionServiceImpl serv = new ErxDecisionServiceImpl();
	

	@POST
	@Path(value="/drugSelection")
    @Produces(value="application/json")
	@Consumes(value="application/json")
	public ErxTransaction assessDrugSelection(ErxTransaction t) {
		return serv.getDrugSelection(t);
	}
	
	@POST
	@Path(value="/drugQuantity")
    @Produces(value="application/json")
	@Consumes(value="application/json")
	public ErxTransaction getDrugQuantity(ErxTransaction t) {
		return serv.getQuantity(t);
	}
	
	@POST
	@Path(value="/drugDaySupply")
    @Produces(value="application/json")
	@Consumes(value="application/json")
	public ErxTransaction getDaySupply(ErxTransaction t) {
		return serv.getDaySupply(t);
	}
	
	@POST
	@Path(value="/thirdParty")
    @Produces(value="application/json")
	@Consumes(value="application/json")
	public ErxTransaction geThirdParty(ErxTransaction t) {
		return serv.geThirdParty(t);
	}
}
