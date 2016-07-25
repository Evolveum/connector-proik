/*
 * Copyright (c) 2016 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evolveum.polygon.connector.proik;

import com.evolveum.polygon.connector.proik.service.DummyServiceImpl;
import com.evolveum.polygon.connector.proik.service.RealServiceImpl;
import com.evolveum.polygon.connector.proik.service.Service;
import com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceLocator;
import com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType;
import com.evolveum.polygon.connector.proik.soap.ProUser;
import com.evolveum.polygon.connector.proik.soap.ProUserResult;
import org.apache.axis.AxisProperties;
import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.AlreadyExistsException;
import org.identityconnectors.framework.common.exceptions.ConnectorIOException;
import org.identityconnectors.framework.common.exceptions.InvalidAttributeValueException;
import org.identityconnectors.framework.common.exceptions.UnknownUidException;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.common.objects.filter.FilterTranslator;
import org.identityconnectors.framework.spi.Configuration;
import org.identityconnectors.framework.spi.ConnectorClass;
import org.identityconnectors.framework.spi.PoolableConnector;
import org.identityconnectors.framework.spi.operations.*;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;

/**
 * ProIK Connector.
 *
 * @author gpalos
 */
@ConnectorClass(displayNameKey = "proik.connector.display", configurationClass = ProIKConfiguration.class)
public class ProIKConnector implements PoolableConnector, TestOp, SchemaOp, CreateOp, DeleteOp, UpdateOp, SearchOp<ProIKFilter> {

    private static final Log LOG = Log.getLog(ProIKConnector.class);

    private ProIKConfiguration configuration;

    private Service service;

    // attributes
//    public static final String ATTR_UNIQUE_ID = "tckn"; // Turkish Citizenship Number
    public static final String ATTR_USER_NAME = "kullaniciAdi";
    public static final String ATTR_FIRST_NAME = "adi"; // turkish
    public static final String ATTR_LAST_NAME = "soyadi";
    public static final String ATTR_EMAIL_ADDRESS = "eposta";
    public static final String ATTR_IS_DOMAIN_NAME = "etkiAlanieh";
    public static final String ATTR_DOMAIN_NAME = "etkiAlani";
    public static final String ATTR_MODIFY_TIMESTAMP = "guncellemeZamani";
    public static final String ATTR_RESULT_CODE = "sonucKodu";
    public static final String ATTR_ERROR_MESSAGE = "hata";

    /*
    aktifmi – administrativeStatus:
        H”for disabling. E=evet(yes) H=hayır(no) in Turkish.
     */
    public static final String ENABLED = "E";
    public static final String DISABLED = "H";

    /*
    ProUserResult codes are:
        0 : SUCCESS (BAŞARILI)
        1 : Cannot login to System (Sisteme girilemiyor)
        2 : User does not exist (Kullanıcı bulunmuyor)
        3 : User is already disabled (Kullanıcı zaten pasif durumda)
        4 : User is already enabled (Kullanıcı zaten aktif durumda)
        5 : User already exists (Varolan kullanıcı)
        6 : User already linked (Kullanıcı hesabı zaten bağlı)
        7 : User already unlinked (Kullanıcı hesabı bağı zaten çözülmüş)
     */
    public static final int CODE_SUCCESS = 0;
    // designed for general connection error, it may be useless.
    public static final int CODE_CANNOT_LOGIN_TO_SYSTEM = 1;
    public static final int CODE_USER_DOES_NOT_EXIST = 2;
    public static final int CODE_USER_ALREADY_DISABLED = 3;
    public static final int CODE_USER_ALREADY_ENABLED = 4;
    public static final int CODE_USER_ALREADY_EXISTS = 5;
//    public static final int CODE_USER_ALREADY_LINKED = 6;
//    public static final int CODE_USER_ALREADY_UNLINKED = 7;

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void init(Configuration configuration) {
        LOG.ok("connector init");
        this.configuration = (ProIKConfiguration) configuration;

        if (this.configuration.getTrustingAllCertificates() != null && this.configuration.getTrustingAllCertificates()) {
            AxisProperties.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");
        }

        if (this.configuration != null && this.configuration.getUseMockup()) {
            service = new DummyServiceImpl();
        } else try {
            ProIKKysWebServiceLocator locator = new ProIKKysWebServiceLocator();
            ProIKKysWebServiceSoap_PortType soap = locator.getProIKKysWebServiceSoap(new URL(this.configuration.getEndpoint()));
            service = new RealServiceImpl(soap);
        } catch (Exception e) {
            LOG.error(e, "Connection failed to: " + this.configuration.getEndpoint());
            throw new ConnectorIOException(e.getMessage(), e);
        }
    }

    @Override
    public void dispose() {
        configuration = null;
        service = null;
    }

    @Override
    public void test() {
        try {
            String ssf = "axis.socketSecureFactory";
            String ssfVal = "org.apache.axis.components.net.SunFakeTrustSocketFactory";
            LOG.info(ssf + " value is: " + AxisProperties.getProperty(ssf));
            if (this.configuration.getTrustingAllCertificates() != null && this.configuration.getTrustingAllCertificates()) {
                // new setup to ignore
                if (!ssfVal.equalsIgnoreCase(AxisProperties.getProperty(ssf))) {
                    synchronized (this) {
                        Configuration configuration = this.configuration;
                        dispose();
                        init(configuration);
                    }
                }
            } else if (AxisProperties.getProperty(ssf) != null) {
                AxisProperties.setProperty("axis.socketSecureFactory", null);
            }
            service.listAllUser();
        } catch (RemoteException e) {
            throw new ConnectorIOException(e.getMessage(), e);
        }
    }

    @Override
    public Schema schema() {
        SchemaBuilder builder = new SchemaBuilder(ProIKConnector.class);

        builder.defineObjectClass(schemaAccount());
        // TODO: create other object classes if needed

        return builder.build();
    }

    private ObjectClassInfo schemaAccount() {
        ObjectClassInfoBuilder objClassBuilder = new ObjectClassInfoBuilder();

        // __UID__ && __NAME__ is the same because we need to find by __NAME__ when AlreadyExistsException occured
        // and are defaults, we don't need to set explicitly

        AttributeInfoBuilder attrUserNameBuilder = new AttributeInfoBuilder(ATTR_USER_NAME);
        attrUserNameBuilder.setRequired(true); // mandatory
        objClassBuilder.addAttributeInfo(attrUserNameBuilder.build());

        AttributeInfoBuilder attrFirstNameBuilder = new AttributeInfoBuilder(ATTR_FIRST_NAME);
        objClassBuilder.addAttributeInfo(attrFirstNameBuilder.build());

        AttributeInfoBuilder attrLastNameBuilder = new AttributeInfoBuilder(ATTR_LAST_NAME);
        objClassBuilder.addAttributeInfo(attrLastNameBuilder.build());

        objClassBuilder.addAttributeInfo(OperationalAttributeInfos.ENABLE); //kullaniciAdi - Administrative Status

        AttributeInfoBuilder attrEmailAddressBuilder = new AttributeInfoBuilder(ATTR_EMAIL_ADDRESS);
        objClassBuilder.addAttributeInfo(attrEmailAddressBuilder.build());

        AttributeInfoBuilder attrIsDomainNameBuilder = new AttributeInfoBuilder(ATTR_IS_DOMAIN_NAME);
        objClassBuilder.addAttributeInfo(attrIsDomainNameBuilder.build());

        AttributeInfoBuilder attrDomainNameBuilder = new AttributeInfoBuilder(ATTR_DOMAIN_NAME);
        objClassBuilder.addAttributeInfo(attrDomainNameBuilder.build());

        // Calendar data type is symulated as Long (timestamp)
        AttributeInfoBuilder attrModifyTimestampBuilder = new AttributeInfoBuilder(ATTR_MODIFY_TIMESTAMP, Long.class);
        objClassBuilder.addAttributeInfo(attrModifyTimestampBuilder.build());

        AttributeInfoBuilder attrResultCodeBuilder = new AttributeInfoBuilder(ATTR_RESULT_CODE, Integer.class);
        attrResultCodeBuilder.setUpdateable(false); // read only
        objClassBuilder.addAttributeInfo(attrResultCodeBuilder.build());

        AttributeInfoBuilder attrErrorMessageBuilder = new AttributeInfoBuilder(ATTR_ERROR_MESSAGE, Integer.class);
        attrErrorMessageBuilder.setUpdateable(false); // read only
        objClassBuilder.addAttributeInfo(attrErrorMessageBuilder.build());


        return objClassBuilder.build();
    }

    @Override
    public Uid create(ObjectClass objectClass, Set<Attribute> attributes, OperationOptions options) {
        if (objectClass.is(ObjectClass.ACCOUNT_NAME)) {    // __ACCOUNT__
            return createUser(attributes);
        } else {
            throw new UnsupportedOperationException("Unsupported object class " + objectClass);
        }
    }

    private Uid createUser(Set<Attribute> attributes) {
        LOG.ok("createUser attributes: {0}", attributes);
        String uid = getStringAttr(attributes, Name.NAME); // NAME & UID is the same and can't be generated in system
        String userName = getStringAttr(attributes, ATTR_USER_NAME);
        if (StringUtil.isBlank(uid)) {
            throw new InvalidAttributeValueException("Missing mandatory attribute " + Name.NAME + " (UID)");
        }
        if (StringUtil.isBlank(userName)) {
            throw new InvalidAttributeValueException("Missing mandatory attribute " + ATTR_USER_NAME + " (userName)");
        }

        String isDomainName = getStringAttr(attributes, ATTR_IS_DOMAIN_NAME);
        String domainName = getStringAttr(attributes, ATTR_DOMAIN_NAME);
        if (ENABLED.equals(isDomainName) && StringUtil.isBlank(domainName)) {
            throw new InvalidAttributeValueException("When " + ATTR_IS_DOMAIN_NAME + " is " + isDomainName + ", " + ATTR_DOMAIN_NAME + " is mandatory attribute and is empty.");
        }

        String firstName = getStringAttr(attributes, ATTR_FIRST_NAME);
        String lastName = getStringAttr(attributes, ATTR_LAST_NAME);
        String emailAddress = getStringAttr(attributes, ATTR_EMAIL_ADDRESS);
        Boolean enable = getAttr(attributes, OperationalAttributes.ENABLE_NAME, Boolean.class);
        Long modifyTimestamp = getAttr(attributes, ATTR_MODIFY_TIMESTAMP, Long.class);


        // check if user already exists
        try {
            ProUser existUser = service.getUser(uid);
            if (existUser == null) {
                throw new InvalidAttributeValueException("getUser must return ProUser with SonucKodu, but returned: "+existUser);
            }
            else if (existUser.getSonucKodu() == CODE_USER_DOES_NOT_EXIST) {
                // do nothing
                LOG.info("user doesn't exists, SonucKodu: " + existUser.getSonucKodu() + ", Hata: " + existUser.getHata());
            } else {
                throw new AlreadyExistsException("User '" + uid + "' already exists, SonucKodu: " + existUser.getSonucKodu() + ", Hata: " + existUser.getHata());
            }
        } catch (RemoteException e) {
            // TODO: its OK if error occured?
            LOG.warn(e, "checking user existence failed: ");
        }

        try {

            ProUser proUser = new ProUser();
            proUser.setTckn(uid);
            proUser.setAdi(firstName);
            proUser.setSoyadi(lastName);
            proUser.setKullaniciAdi(userName);
            proUser.setEposta(emailAddress);
            proUser.setEtkiAlanieh(isDomainName);
            proUser.setEtkiAlani(domainName);

            Calendar modifyTimestampCal = null;
            if (configuration.getGenerateModifyTimestamp()) {
                // generate it automatically
                modifyTimestampCal = GregorianCalendar.getInstance();
            }
            if (modifyTimestamp !=null) {
                // if we get from midPoint, send this value to resource
                modifyTimestampCal = GregorianCalendar.getInstance();
                modifyTimestampCal.setTime(new Date(modifyTimestamp));
            }

            proUser.setGuncellemeZamani(modifyTimestampCal);

            LOG.ok("createUser: tckn:{0}, adi:{1}, soyadi:{2}, kullaniciAdi:{3}, eposta:{4}, enable:{5}, " +
                            "Aktifmi:{6}, etkiAlanieh:{7}, etkiAlani:{8}, modifyTimestamp: {9}",
                    uid, firstName, lastName, userName, emailAddress, enable, proUser.getAktifmi(), isDomainName, domainName, proUser.getGuncellemeZamani());

            ProUserResult result = service.createUser(proUser);
            if (CODE_SUCCESS != result.getSonucKodu()) {
                throw new ConnectorIOException("create user returned sonucKodu: " + result.getSonucKodu() + ", hata: " + result.getHata());
            }

            LOG.ok("user created, result: sonucKodu:{0}, hata:{1}",
                    result.getSonucKodu(), result.getHata());

            if (enable != null) {
                handleAdministrativeStatus(uid, enable);
            }

            return new Uid(uid);
        } catch (java.rmi.RemoteException e) {
            throw new ConnectorIOException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(ObjectClass objectClass, Uid uid, OperationOptions options) {
        if (objectClass.is(ObjectClass.ACCOUNT_NAME)) {
            try {
                LOG.ok("delete user is transformed to disable user, Uid: {0}", uid);
                ProUserResult result = service.disableUser(uid.getUidValue());
                // if already disabled or returned success, is OK, other is wrong answer
                if (CODE_SUCCESS != result.getSonucKodu() && CODE_USER_ALREADY_DISABLED == result.getSonucKodu()) {
                    throw new ConnectorIOException("delete (disable) use returned sonucKodu: " + result.getSonucKodu() + ", hata: " + result.getHata());
                }
            } catch (java.rmi.RemoteException e) {
                throw new ConnectorIOException(e.getMessage(), e);
            }
        } else {
            throw new UnsupportedOperationException("Unsupported object class " + objectClass);
        }
    }

    @Override
    public Uid update(ObjectClass objectClass, Uid uid, Set<Attribute> attributes, OperationOptions options) {
        if (objectClass.is(ObjectClass.ACCOUNT_NAME)) {
            return updateUser(uid, attributes);
        } else {
            throw new UnsupportedOperationException("Unsupported object class " + objectClass);
        }
    }

    private Uid updateUser(Uid uid, Set<Attribute> attributes) {
        LOG.ok("updateUser, Uid: {0}, attributes: {1}", uid, attributes);
        if (attributes == null || attributes.isEmpty()) {
            LOG.ok("update ignored, nothing changed");
            return uid;
        }

        String targetUserId = uid.getUidValue();

        ProUser origUser;
        try {
            // need to read existing user from resource, becouse midPoint send only changed attributes,
            // but when update operation is called on resource, we need all attributes
            origUser = service.getUser(targetUserId);

            if (origUser == null) {
                throw new InvalidAttributeValueException("getUser must return ProUser with SonucKodu, but returned: "+origUser);
            }
            else if (origUser.getSonucKodu() == CODE_USER_DOES_NOT_EXIST) {
                throw new UnknownUidException("User " + targetUserId + " does not exists, SonucKodu: " + origUser.getSonucKodu() + ", Hata: " + origUser.getHata());
            }
        } catch (java.rmi.RemoteException e) {
            throw new ConnectorIOException(e.getMessage(), e);
        }

        // get attribute value from midPoint or fill default value from resource
        String userName = getStringAttr(attributes, ATTR_USER_NAME, origUser.getKullaniciAdi());
        String isDomainName = getStringAttr(attributes, ATTR_IS_DOMAIN_NAME, origUser.getEtkiAlanieh());
        String domainName = getStringAttr(attributes, ATTR_DOMAIN_NAME, origUser.getEtkiAlani());
        String firstName = getStringAttr(attributes, ATTR_FIRST_NAME, origUser.getAdi());
        String lastName = getStringAttr(attributes, ATTR_LAST_NAME, origUser.getSoyadi());
        String emailAddress = getStringAttr(attributes, ATTR_EMAIL_ADDRESS, origUser.getEposta());
        Boolean aktifmiAsBoolean = ENABLED.equals(origUser.getAktifmi()) ? true : false;
        Boolean enable = getAttr(attributes, OperationalAttributes.ENABLE_NAME, Boolean.class, aktifmiAsBoolean);
        Long origModifyTimestamp = origUser.getGuncellemeZamani() == null ? null : origUser.getGuncellemeZamani().getTime().getTime();
        Long modifyTimestamp = getAttr(attributes, ATTR_MODIFY_TIMESTAMP, Long.class, origModifyTimestamp);

        try {

            ProUser proUser = new ProUser();
            proUser.setTckn(targetUserId);
            proUser.setAdi(firstName);
            proUser.setSoyadi(lastName);
            proUser.setKullaniciAdi(userName);
            proUser.setEposta(emailAddress);
            // do this over handleAdministrativeStatus
//            if (enable != null) {
//                proUser.setAktifmi(enable ? ENABLED : DISABLED);
//            }
            proUser.setEtkiAlanieh(isDomainName);
            proUser.setEtkiAlani(domainName);

            Calendar modifyTimestampCal = null;
            if (configuration.getGenerateModifyTimestamp()) {
                modifyTimestampCal = GregorianCalendar.getInstance();
            }
            if (modifyTimestamp !=null) {
                modifyTimestampCal = GregorianCalendar.getInstance();
                modifyTimestampCal.setTime(new Date(modifyTimestamp));
            }
            proUser.setGuncellemeZamani(modifyTimestampCal);


            LOG.ok("updateUser: tckn:{0}, adi:{1}, soyadi:{2}, kullaniciAdi:{3}, eposta:{4}, enable:{5}, " +
                            "Aktifmi:{6}, etkiAlanieh:{7}, etkiAlani:{8}, modifyTimestamp: {9}",
                    uid, firstName, lastName, userName, emailAddress, enable, proUser.getAktifmi(), isDomainName, domainName, proUser.getGuncellemeZamani());

            ProUserResult result = service.updateUser(proUser);
            if (CODE_SUCCESS != result.getSonucKodu()) {
                throw new ConnectorIOException("update user returned sonucKodu: " + result.getSonucKodu() + ", hata: " + result.getHata());
            }

            LOG.ok("user updated, result: sonucKodu:{0}, hata:{1}",
                    result.getSonucKodu(), result.getHata());

            if (enable != null) {
                handleAdministrativeStatus(targetUserId, enable);
            }

            return new Uid(targetUserId);
        } catch (java.rmi.RemoteException e) {
            throw new ConnectorIOException(e.getMessage(), e);
        }
    }

    private void handleAdministrativeStatus(String uid, Boolean enable) throws RemoteException {
        if (enable != null) {
            if (enable) {
                ProUserResult result = service.enableUser(uid);
                if (result.getSonucKodu() != CODE_SUCCESS && result.getSonucKodu() != CODE_USER_ALREADY_ENABLED) {
                    throw new ConnectorIOException("enable user " + uid + " returned sonucKodu: " + result.getSonucKodu() + ", hata: " + result.getHata());
                }
                LOG.ok("user enabled, result: sonucKodu:{0}, hata:{1}",
                        result.getSonucKodu(), result.getHata());
            } else {
                ProUserResult result = service.disableUser(uid);
                if (result.getSonucKodu() != CODE_SUCCESS && result.getSonucKodu() != CODE_USER_ALREADY_DISABLED) {
                    throw new ConnectorIOException("disable user " + uid + " returned sonucKodu: " + result.getSonucKodu() + ", hata: " + result.getHata());
                }
                LOG.ok("user disabled, result: sonucKodu:{0}, hata:{1}",
                        result.getSonucKodu(), result.getHata());
            }
        }
    }

    @Override
    public FilterTranslator<ProIKFilter> createFilterTranslator(ObjectClass objectClass, OperationOptions options) {
        return new ProIKFilterTranslator();
    }

    @Override
    public void executeQuery(ObjectClass objectClass, ProIKFilter query, ResultsHandler handler, OperationOptions options) {

        if (objectClass.is(ObjectClass.ACCOUNT_NAME)) {
            try {
                LOG.ok("executeQuery: {0}, options: {1}", query, options);
                //find by Uid (user Primary Key)
                if (query != null && query.byUid != null) {
                    ProUser user = service.getUser(query.byUid);


                    if (user == null) {
                        throw new InvalidAttributeValueException("getUser must return ProUser with SonucKodu, but returned: "+user);
                    }
                    else if (user.getSonucKodu() == CODE_USER_DOES_NOT_EXIST) {
                        throw new UnknownUidException("User " + user + " does not exists, SonucKodu: " + user.getSonucKodu() + ", Hata: " + user.getHata());
                    }
                    ConnectorObject connectorObject = convertUserToConnectorObject(user);
                    if (connectorObject != null) {
                        handler.handle(connectorObject);
                    }
                    else {
                        throw new UnknownUidException("User " + user + " does not exists after converting, SonucKodu: " + user.getSonucKodu() + ", Hata: " + user.getHata());
                    }

                    // find all
                } else {
                    ProUserResult result = service.listAllUser();

                    int count = 0;
                    for (ProUser user : result.getUsers()) {
                        if (++count % 10 == 0) {
                            LOG.ok("executeQuery: processing {0}. of {1} users", count, result.getUsers().length);
                        }

                        ConnectorObject connectorObject = convertUserToConnectorObject(user);
                        if (connectorObject != null) {
                            boolean finish = !handler.handle(connectorObject);
                            if (finish)
                                break;
                        }
                    }
                }

            } catch (java.rmi.RemoteException e) {
                throw new ConnectorIOException(e.getMessage(), e);
            }

        } else {
            throw new UnsupportedOperationException("Unsupported object class " + objectClass);
        }
    }


    private ConnectorObject convertUserToConnectorObject(ProUser user) throws RemoteException {
        if (StringUtil.isBlank(user.getTckn())) {
            // users on target system without TCKN we don't support (connector framework need UID and is mandatory)
            LOG.warn("User's TCKN is blank, ignoring, UID: {0}, userName {1}",
                    user.getTckn(), user.getKullaniciAdi());
            return null;
        }
        ConnectorObjectBuilder builder = new ConnectorObjectBuilder();
        builder.setUid(user.getTckn());
        builder.setName(user.getTckn());
        addAttr(builder, ATTR_USER_NAME, user.getKullaniciAdi());
        addAttr(builder, ATTR_FIRST_NAME, user.getAdi());
        addAttr(builder, ATTR_LAST_NAME, user.getSoyadi());
        addAttr(builder, ATTR_EMAIL_ADDRESS, user.getEposta());
        addAttr(builder, ATTR_IS_DOMAIN_NAME, user.getEtkiAlanieh());
        addAttr(builder, ATTR_DOMAIN_NAME, user.getEtkiAlani());
        Long modifyTimestamp = user.getGuncellemeZamani() == null ? null : user.getGuncellemeZamani().getTime().getTime();
        addAttr(builder, ATTR_MODIFY_TIMESTAMP, modifyTimestamp);
        addAttr(builder, ATTR_RESULT_CODE, user.getSonucKodu());
        addAttr(builder, ATTR_ERROR_MESSAGE, user.getHata());

        boolean enable = ENABLED == user.getAktifmi() ? true : false;
        addAttr(builder, OperationalAttributes.ENABLE_NAME, enable);

        ConnectorObject connectorObject = builder.build();
        LOG.ok("convertUserToConnectorObject, user: {0}:{1}, enable: {2}, " +
                        "\n\tconnectorObject: {3}, ",
                user.getTckn(), user.getKullaniciAdi(), enable, connectorObject);
        return connectorObject;
    }

    private String getStringAttr(Set<Attribute> attributes, String attrName) throws InvalidAttributeValueException {
        return getAttr(attributes, attrName, String.class);
    }

    private String getStringAttr(Set<Attribute> attributes, String attrName, String defaultVal) throws InvalidAttributeValueException {
        return getAttr(attributes, attrName, String.class, defaultVal);
    }

    private String getStringAttr(Set<Attribute> attributes, String attrName, String defaultVal, String defaultVal2, boolean notNull) throws InvalidAttributeValueException {
        String ret = getAttr(attributes, attrName, String.class, defaultVal);
        if (notNull && ret == null) {
            if (notNull && defaultVal == null)
                return defaultVal2;
            return defaultVal;
        }
        return ret;
    }

    private String getStringAttr(Set<Attribute> attributes, String attrName, String defaultVal, boolean notNull) throws InvalidAttributeValueException {
        String ret = getAttr(attributes, attrName, String.class, defaultVal);
        if (notNull && ret == null)
            return defaultVal;
        return ret;
    }

    private <T> T getAttr(Set<Attribute> attributes, String attrName, Class<T> type) throws InvalidAttributeValueException {
        return getAttr(attributes, attrName, type, null);
    }

    private <T> T getAttr(Set<Attribute> attributes, String attrName, Class<T> type, T defaultVal, boolean notNull) throws InvalidAttributeValueException {
        T ret = getAttr(attributes, attrName, type, defaultVal);
        if (notNull && ret == null)
            return defaultVal;
        return ret;
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttr(Set<Attribute> attributes, String attrName, Class<T> type, T defaultVal) throws InvalidAttributeValueException {
        for (Attribute attr : attributes) {
            if (attrName.equals(attr.getName())) {
                List<Object> vals = attr.getValue();
                if (vals == null || vals.isEmpty()) {
                    // set empty value
                    return null;
                }
                if (vals.size() == 1) {
                    Object val = vals.get(0);
                    if (val == null) {
                        // set empty value
                        return null;
                    }
                    if (type.isAssignableFrom(val.getClass())) {
                        return (T) val;
                    }
                    throw new InvalidAttributeValueException("Unsupported type " + val.getClass() + " for attribute " + attrName);
                }
                throw new InvalidAttributeValueException("More than one value for attribute " + attrName);
            }
        }
        // set default value when attrName not in changed attributes
        return defaultVal;
    }

    private long[] getMultiValAttr(Set<Attribute> attributes, String attrName, long[] defaultVal) {
        for (Attribute attr : attributes) {
            if (attrName.equals(attr.getName())) {
                List<Object> vals = attr.getValue();
                if (vals == null || vals.isEmpty()) {
                    // set empty value
                    return new long[0];
                }
                long[] ret = new long[vals.size()];
                for (int i = 0; i < vals.size(); i++) {
                    Object valAsObject = vals.get(i);
                    if (valAsObject == null)
                        throw new InvalidAttributeValueException("Value " + null + " must be not null for attribute " + attrName);

                    Long val;
                    if (valAsObject instanceof String) {
                        val = Long.parseLong((String) valAsObject);
                    } else {
                        val = (Long) valAsObject;
                    }
                    ret[i] = val;
                }
                return ret;
            }
        }
        // set default value when attrName not in changed attributes
        return defaultVal;
    }


    private <T> void addAttr(ConnectorObjectBuilder builder, String attrName, T attrVal) {
        if (attrVal != null) {
            builder.addAttribute(attrName, attrVal);
        }
    }


    @Override
    public void checkAlive() {
        test();
        // TODO quicker test?
    }
}
