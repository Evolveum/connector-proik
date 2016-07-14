/**
 * ProUserResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.evolveum.polygon.connector.proik.soap;

public class ProUserResult  implements java.io.Serializable {
    private int sonucKodu;

    private java.lang.String hata;

    private com.evolveum.polygon.connector.proik.soap.ProUser[] users;

    public ProUserResult() {
    }

    public ProUserResult(
           int sonucKodu,
           java.lang.String hata,
           com.evolveum.polygon.connector.proik.soap.ProUser[] users) {
           this.sonucKodu = sonucKodu;
           this.hata = hata;
           this.users = users;
    }


    /**
     * Gets the sonucKodu value for this ProUserResult.
     * 
     * @return sonucKodu
     */
    public int getSonucKodu() {
        return sonucKodu;
    }


    /**
     * Sets the sonucKodu value for this ProUserResult.
     * 
     * @param sonucKodu
     */
    public void setSonucKodu(int sonucKodu) {
        this.sonucKodu = sonucKodu;
    }


    /**
     * Gets the hata value for this ProUserResult.
     * 
     * @return hata
     */
    public java.lang.String getHata() {
        return hata;
    }


    /**
     * Sets the hata value for this ProUserResult.
     * 
     * @param hata
     */
    public void setHata(java.lang.String hata) {
        this.hata = hata;
    }


    /**
     * Gets the users value for this ProUserResult.
     * 
     * @return users
     */
    public com.evolveum.polygon.connector.proik.soap.ProUser[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this ProUserResult.
     * 
     * @param users
     */
    public void setUsers(com.evolveum.polygon.connector.proik.soap.ProUser[] users) {
        this.users = users;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProUserResult)) return false;
        ProUserResult other = (ProUserResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.sonucKodu == other.getSonucKodu() &&
            ((this.hata==null && other.getHata()==null) || 
             (this.hata!=null &&
              this.hata.equals(other.getHata()))) &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getSonucKodu();
        if (getHata() != null) {
            _hashCode += getHata().hashCode();
        }
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProUserResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ProUserResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sonucKodu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SonucKodu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Hata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Users"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ProUser"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "ProUser"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
