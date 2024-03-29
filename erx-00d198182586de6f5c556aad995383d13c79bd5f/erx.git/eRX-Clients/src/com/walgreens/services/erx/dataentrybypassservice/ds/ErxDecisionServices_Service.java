//
// Generated By:JAX-WS RI IBM 2.2.1-11/25/2013 11:48 AM(foreman)- (JAXB RI IBM 2.2.3-11/25/2013 12:35 PM(foreman)-)
//


package com.walgreens.services.erx.dataentrybypassservice.ds;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "ErxDecisionServices", targetNamespace = "http://services.walgreens.com/erx/DataEntryByPassService/ds", wsdlLocation = "http://localhost:9083/erxds/serv/ErxDecisionServices.wsdl")
public class ErxDecisionServices_Service
    extends Service
{

    private final static URL ERXDECISIONSERVICES_WSDL_LOCATION;
    private final static WebServiceException ERXDECISIONSERVICES_EXCEPTION;
    private final static QName ERXDECISIONSERVICES_QNAME = new QName("http://services.walgreens.com/erx/DataEntryByPassService/ds", "ErxDecisionServices");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9083/erxds/serv/ErxDecisionServices.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ERXDECISIONSERVICES_WSDL_LOCATION = url;
        ERXDECISIONSERVICES_EXCEPTION = e;
    }

    public ErxDecisionServices_Service() {
        super(__getWsdlLocation(), ERXDECISIONSERVICES_QNAME);
    }

    public ErxDecisionServices_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), ERXDECISIONSERVICES_QNAME, features);
    }

    public ErxDecisionServices_Service(URL wsdlLocation) {
        super(wsdlLocation, ERXDECISIONSERVICES_QNAME);
    }

    public ErxDecisionServices_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ERXDECISIONSERVICES_QNAME, features);
    }

    public ErxDecisionServices_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ErxDecisionServices_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ErxDecisionServices
     */
    @WebEndpoint(name = "ErxDecisionServicePort")
    public ErxDecisionServices getErxDecisionServicePort() {
        return super.getPort(new QName("http://services.walgreens.com/erx/DataEntryByPassService/ds", "ErxDecisionServicePort"), ErxDecisionServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ErxDecisionServices
     */
    @WebEndpoint(name = "ErxDecisionServicePort")
    public ErxDecisionServices getErxDecisionServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.walgreens.com/erx/DataEntryByPassService/ds", "ErxDecisionServicePort"), ErxDecisionServices.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ERXDECISIONSERVICES_EXCEPTION!= null) {
            throw ERXDECISIONSERVICES_EXCEPTION;
        }
        return ERXDECISIONSERVICES_WSDL_LOCATION;
    }

}
