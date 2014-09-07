package com.walgreens.pharmacy.ds;

import java.util.HashSet;
import java.util.Set;

import com.walgreens.pharmacy.ds.rest.ErxDecisionService;

public class ErxDSApplication extends javax.ws.rs.core.Application {

	public ErxDSApplication() {
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ErxDecisionService.class);
		return classes;
	}

}
