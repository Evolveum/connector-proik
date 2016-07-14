/**
 * ProUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.evolveum.polygon.connector.proik.soap;

public class ProUser  implements java.io.Serializable {
    private java.lang.String tckn;

    private java.lang.String adi;

    private java.lang.String soyadi;

    private java.lang.String kullaniciAdi;

    private java.lang.String eposta;

    private java.lang.String aktifmi;

    private java.lang.String etkiAlanieh;

    private java.lang.String etkiAlani;

    private java.util.Calendar guncellemeZamani;

    private int sonucKodu;

    private java.lang.String hata;

    public ProUser() {
    }

    public ProUser(
           java.lang.String tckn,
           java.lang.String adi,
           java.lang.String soyadi,
           java.lang.String kullaniciAdi,
           java.lang.String eposta,
           java.lang.String aktifmi,
           java.lang.String etkiAlanieh,
           java.lang.String etkiAlani,
           java.util.Calendar guncellemeZamani,
           int sonucKodu,
           java.lang.String hata) {
           this.tckn = tckn;
           this.adi = adi;
           this.soyadi = soyadi;
           this.kullaniciAdi = kullaniciAdi;
           this.eposta = eposta;
           this.aktifmi = aktifmi;
           this.etkiAlanieh = etkiAlanieh;
           this.etkiAlani = etkiAlani;
           this.guncellemeZamani = guncellemeZamani;
           this.sonucKodu = sonucKodu;
           this.hata = hata;
    }


    /**
     * Gets the tckn value for this ProUser.
     * 
     * @return tckn
     */
    public java.lang.String getTckn() {
        return tckn;
    }


    /**
     * Sets the tckn value for this ProUser.
     * 
     * @param tckn
     */
    public void setTckn(java.lang.String tckn) {
        this.tckn = tckn;
    }


    /**
     * Gets the adi value for this ProUser.
     * 
     * @return adi
     */
    public java.lang.String getAdi() {
        return adi;
    }


    /**
     * Sets the adi value for this ProUser.
     * 
     * @param adi
     */
    public void setAdi(java.lang.String adi) {
        this.adi = adi;
    }


    /**
     * Gets the soyadi value for this ProUser.
     * 
     * @return soyadi
     */
    public java.lang.String getSoyadi() {
        return soyadi;
    }


    /**
     * Sets the soyadi value for this ProUser.
     * 
     * @param soyadi
     */
    public void setSoyadi(java.lang.String soyadi) {
        this.soyadi = soyadi;
    }


    /**
     * Gets the kullaniciAdi value for this ProUser.
     * 
     * @return kullaniciAdi
     */
    public java.lang.String getKullaniciAdi() {
        return kullaniciAdi;
    }


    /**
     * Sets the kullaniciAdi value for this ProUser.
     * 
     * @param kullaniciAdi
     */
    public void setKullaniciAdi(java.lang.String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }


    /**
     * Gets the eposta value for this ProUser.
     * 
     * @return eposta
     */
    public java.lang.String getEposta() {
        return eposta;
    }


    /**
     * Sets the eposta value for this ProUser.
     * 
     * @param eposta
     */
    public void setEposta(java.lang.String eposta) {
        this.eposta = eposta;
    }


    /**
     * Gets the aktifmi value for this ProUser.
     * 
     * @return aktifmi
     */
    public java.lang.String getAktifmi() {
        return aktifmi;
    }


    /**
     * Sets the aktifmi value for this ProUser.
     * 
     * @param aktifmi
     */
    public void setAktifmi(java.lang.String aktifmi) {
        this.aktifmi = aktifmi;
    }


    /**
     * Gets the etkiAlanieh value for this ProUser.
     * 
     * @return etkiAlanieh
     */
    public java.lang.String getEtkiAlanieh() {
        return etkiAlanieh;
    }


    /**
     * Sets the etkiAlanieh value for this ProUser.
     * 
     * @param etkiAlanieh
     */
    public void setEtkiAlanieh(java.lang.String etkiAlanieh) {
        this.etkiAlanieh = etkiAlanieh;
    }


    /**
     * Gets the etkiAlani value for this ProUser.
     * 
     * @return etkiAlani
     */
    public java.lang.String getEtkiAlani() {
        return etkiAlani;
    }


    /**
     * Sets the etkiAlani value for this ProUser.
     * 
     * @param etkiAlani
     */
    public void setEtkiAlani(java.lang.String etkiAlani) {
        this.etkiAlani = etkiAlani;
    }


    /**
     * Gets the guncellemeZamani value for this ProUser.
     * 
     * @return guncellemeZamani
     */
    public java.util.Calendar getGuncellemeZamani() {
        return guncellemeZamani;
    }


    /**
     * Sets the guncellemeZamani value for this ProUser.
     * 
     * @param guncellemeZamani
     */
    public void setGuncellemeZamani(java.util.Calendar guncellemeZamani) {
        this.guncellemeZamani = guncellemeZamani;
    }


    /**
     * Gets the sonucKodu value for this ProUser.
     * 
     * @return sonucKodu
     */
    public int getSonucKodu() {
        return sonucKodu;
    }


    /**
     * Sets the sonucKodu value for this ProUser.
     * 
     * @param sonucKodu
     */
    public void setSonucKodu(int sonucKodu) {
        this.sonucKodu = sonucKodu;
    }


    /**
     * Gets the hata value for this ProUser.
     * 
     * @return hata
     */
    public java.lang.String getHata() {
        return hata;
    }


    /**
     * Sets the hata value for this ProUser.
     * 
     * @param hata
     */
    public void setHata(java.lang.String hata) {
        this.hata = hata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProUser)) return false;
        ProUser other = (ProUser) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tckn==null && other.getTckn()==null) || 
             (this.tckn!=null &&
              this.tckn.equals(other.getTckn()))) &&
            ((this.adi==null && other.getAdi()==null) || 
             (this.adi!=null &&
              this.adi.equals(other.getAdi()))) &&
            ((this.soyadi==null && other.getSoyadi()==null) || 
             (this.soyadi!=null &&
              this.soyadi.equals(other.getSoyadi()))) &&
            ((this.kullaniciAdi==null && other.getKullaniciAdi()==null) || 
             (this.kullaniciAdi!=null &&
              this.kullaniciAdi.equals(other.getKullaniciAdi()))) &&
            ((this.eposta==null && other.getEposta()==null) || 
             (this.eposta!=null &&
              this.eposta.equals(other.getEposta()))) &&
            ((this.aktifmi==null && other.getAktifmi()==null) || 
             (this.aktifmi!=null &&
              this.aktifmi.equals(other.getAktifmi()))) &&
            ((this.etkiAlanieh==null && other.getEtkiAlanieh()==null) || 
             (this.etkiAlanieh!=null &&
              this.etkiAlanieh.equals(other.getEtkiAlanieh()))) &&
            ((this.etkiAlani==null && other.getEtkiAlani()==null) || 
             (this.etkiAlani!=null &&
              this.etkiAlani.equals(other.getEtkiAlani()))) &&
            ((this.guncellemeZamani==null && other.getGuncellemeZamani()==null) || 
             (this.guncellemeZamani!=null &&
              this.guncellemeZamani.equals(other.getGuncellemeZamani()))) &&
            this.sonucKodu == other.getSonucKodu() &&
            ((this.hata==null && other.getHata()==null) || 
             (this.hata!=null &&
              this.hata.equals(other.getHata())));
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
        if (getTckn() != null) {
            _hashCode += getTckn().hashCode();
        }
        if (getAdi() != null) {
            _hashCode += getAdi().hashCode();
        }
        if (getSoyadi() != null) {
            _hashCode += getSoyadi().hashCode();
        }
        if (getKullaniciAdi() != null) {
            _hashCode += getKullaniciAdi().hashCode();
        }
        if (getEposta() != null) {
            _hashCode += getEposta().hashCode();
        }
        if (getAktifmi() != null) {
            _hashCode += getAktifmi().hashCode();
        }
        if (getEtkiAlanieh() != null) {
            _hashCode += getEtkiAlanieh().hashCode();
        }
        if (getEtkiAlani() != null) {
            _hashCode += getEtkiAlani().hashCode();
        }
        if (getGuncellemeZamani() != null) {
            _hashCode += getGuncellemeZamani().hashCode();
        }
        _hashCode += getSonucKodu();
        if (getHata() != null) {
            _hashCode += getHata().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ProUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tckn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Tckn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Adi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soyadi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Soyadi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kullaniciAdi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "KullaniciAdi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eposta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Eposta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aktifmi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Aktifmi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etkiAlanieh");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EtkiAlanieh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("etkiAlani");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EtkiAlani"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("guncellemeZamani");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GuncellemeZamani"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
