package com.tzmb2c.SAPService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "SAPServicePortType", targetNamespace = "http://ws.sap.emn.com")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface SAPServicePortType {

  @WebMethod(operationName = "service", action = "")
  @WebResult(name = "out", targetNamespace = "http://ws.sap.emn.com")
  public String service(
      @WebParam(name = "in0", targetNamespace = "http://ws.sap.emn.com") String in0);

}
