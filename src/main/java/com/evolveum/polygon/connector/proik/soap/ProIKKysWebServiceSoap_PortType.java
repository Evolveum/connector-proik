/**
 * ProIKKysWebServiceSoap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.evolveum.polygon.connector.proik.soap;

public interface ProIKKysWebServiceSoap_PortType extends java.rmi.Remote {

    /**
     * T.C. kimlik no ile kullanici bilgisi getirir
     */
    public com.evolveum.polygon.connector.proik.soap.ProUser getUser(java.lang.String tckn) throws java.rmi.RemoteException;

    /**
     * ProUser nesnesi bilgileri ile PROIK üzerinde kullanici create
     * eder
     */
    public com.evolveum.polygon.connector.proik.soap.ProUserResult createUser(com.evolveum.polygon.connector.proik.soap.ProUser user) throws java.rmi.RemoteException;

    /**
     * ProUser nesnesi bilgileri ile PROIK uzerinde kullanici bilgileri
     * gunceller
     */
    public com.evolveum.polygon.connector.proik.soap.ProUserResult updateUser(com.evolveum.polygon.connector.proik.soap.ProUser user) throws java.rmi.RemoteException;

    /**
     * T.C. kimlik no ile kullanici pasife alinir
     */
    public com.evolveum.polygon.connector.proik.soap.ProUserResult disableUser(java.lang.String tckn) throws java.rmi.RemoteException;

    /**
     * T.C. kimlik no ile kullanici aktife alinir
     */
    public com.evolveum.polygon.connector.proik.soap.ProUserResult enableUser(java.lang.String tckn) throws java.rmi.RemoteException;

    /**
     * Kullanici listesi döner
     */
    public com.evolveum.polygon.connector.proik.soap.ProUserResult listAllUser() throws java.rmi.RemoteException;
}
