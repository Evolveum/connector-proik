/**
 * ProIKKysWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.evolveum.polygon.connector.proik.soap;

public class ProIKKysWebServiceLocator extends org.apache.axis.client.Service implements com.evolveum.polygon.connector.proik.soap.ProIKKysWebService {

    public ProIKKysWebServiceLocator() {
    }


    public ProIKKysWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProIKKysWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProIKKysWebServiceSoap
    private java.lang.String ProIKKysWebServiceSoap_address = "http://172.17.30.33/PROIKTEST/ProIKServices/ProIKKysWebService.asmx";

    public java.lang.String getProIKKysWebServiceSoapAddress() {
        return ProIKKysWebServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProIKKysWebServiceSoapWSDDServiceName = "ProIKKysWebServiceSoap";

    public java.lang.String getProIKKysWebServiceSoapWSDDServiceName() {
        return ProIKKysWebServiceSoapWSDDServiceName;
    }

    public void setProIKKysWebServiceSoapWSDDServiceName(java.lang.String name) {
        ProIKKysWebServiceSoapWSDDServiceName = name;
    }

    public com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType getProIKKysWebServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProIKKysWebServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProIKKysWebServiceSoap(endpoint);
    }

    public com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType getProIKKysWebServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_BindingStub _stub = new com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_BindingStub(portAddress, this);
            _stub.setPortName(getProIKKysWebServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProIKKysWebServiceSoapEndpointAddress(java.lang.String address) {
        ProIKKysWebServiceSoap_address = address;
    }


    // Use to get a proxy class for ProIKKysWebServiceSoap12
    private java.lang.String ProIKKysWebServiceSoap12_address = "http://172.17.30.33/PROIKTEST/ProIKServices/ProIKKysWebService.asmx";

    public java.lang.String getProIKKysWebServiceSoap12Address() {
        return ProIKKysWebServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProIKKysWebServiceSoap12WSDDServiceName = "ProIKKysWebServiceSoap12";

    public java.lang.String getProIKKysWebServiceSoap12WSDDServiceName() {
        return ProIKKysWebServiceSoap12WSDDServiceName;
    }

    public void setProIKKysWebServiceSoap12WSDDServiceName(java.lang.String name) {
        ProIKKysWebServiceSoap12WSDDServiceName = name;
    }

    public com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType getProIKKysWebServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProIKKysWebServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProIKKysWebServiceSoap12(endpoint);
    }

    public com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType getProIKKysWebServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap12Stub _stub = new com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getProIKKysWebServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProIKKysWebServiceSoap12EndpointAddress(java.lang.String address) {
        ProIKKysWebServiceSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_BindingStub _stub = new com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_BindingStub(new java.net.URL(ProIKKysWebServiceSoap_address), this);
                _stub.setPortName(getProIKKysWebServiceSoapWSDDServiceName());
                return _stub;
            }
            if (com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap12Stub _stub = new com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap12Stub(new java.net.URL(ProIKKysWebServiceSoap12_address), this);
                _stub.setPortName(getProIKKysWebServiceSoap12WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ProIKKysWebServiceSoap".equals(inputPortName)) {
            return getProIKKysWebServiceSoap();
        }
        else if ("ProIKKysWebServiceSoap12".equals(inputPortName)) {
            return getProIKKysWebServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ProIKKysWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ProIKKysWebServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ProIKKysWebServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProIKKysWebServiceSoap".equals(portName)) {
            setProIKKysWebServiceSoapEndpointAddress(address);
        }
        else 
if ("ProIKKysWebServiceSoap12".equals(portName)) {
            setProIKKysWebServiceSoap12EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
