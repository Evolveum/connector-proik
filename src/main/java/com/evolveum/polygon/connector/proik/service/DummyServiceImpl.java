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
package com.evolveum.polygon.connector.proik.service;

import com.evolveum.polygon.connector.proik.ProIKConnector;
import com.evolveum.polygon.connector.proik.soap.ProUser;
import com.evolveum.polygon.connector.proik.soap.ProUserResult;

import java.rmi.RemoteException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Dummy implementation of {@link Service} interface.
 * User's are stored only in memory, after restarted midpoint is cleaned and re-initialized.
 * <p/>
 * Created by gpalos on 8. 7. 2016.
 */
public class DummyServiceImpl implements Service {

    private static final Map<String, ProUser> users = new HashMap<String, ProUser>();

    public static final String SAMPLE_TCKN = "11111";

    static {
        // one sample user in dummy resource.
        String tckn = SAMPLE_TCKN;
        ProUser pu = new ProUser();
        pu.setTckn(tckn);
        pu.setAdi("firstName");
        pu.setSoyadi("lastName");
        pu.setKullaniciAdi("userName");
        pu.setEposta("email@evolveum.com");
        pu.setAktifmi(ProIKConnector.DISABLED);
        pu.setEtkiAlanieh(ProIKConnector.ENABLED);
        pu.setEtkiAlani("domainName");
        pu.setGuncellemeZamani(new GregorianCalendar());

        users.put(tckn, pu);

        // one sample user in dummy resource without tckn (will be filtered in connector).
        String tckn2 = "";
        ProUser pu2 = new ProUser();
        pu2.setTckn(tckn2);
        pu2.setAdi("firstName 2");
        pu2.setSoyadi("lastName 2");
        pu2.setKullaniciAdi("userName2");
        pu2.setEposta("email2@evolveum.com");
        pu2.setAktifmi(ProIKConnector.DISABLED);
        pu2.setEtkiAlanieh(ProIKConnector.ENABLED);
        pu2.setEtkiAlani("domainName");
        pu2.setGuncellemeZamani(new GregorianCalendar());

        users.put(tckn2, pu2);
    }

    @Override
    public ProUser getUser(String tckn) throws RemoteException {
        if (users.containsKey(tckn)) {
            return users.get(tckn);
        }

        // not exists
        ProUser pu = new ProUser();
        pu.setSonucKodu(ProIKConnector.CODE_USER_DOES_NOT_EXIST);
        return pu;
    }

    @Override
    public ProUserResult createUser(ProUser user) throws RemoteException {
        if (users.containsKey(user.getTckn())) {
            ProUserResult pur = new ProUserResult();
            pur.setSonucKodu(ProIKConnector.CODE_USER_ALREADY_EXISTS);
            pur.setHata("User already exists");

            return pur;
        }

        if (user.getGuncellemeZamani()==null) {
            ProUserResult pur = new ProUserResult();
            pur.setSonucKodu(ProIKConnector.CODE_CANNOT_LOGIN_TO_SYSTEM);
            pur.setHata("GuncellemeZamani is mandatory !!!");

            return pur;
        }

        users.put(user.getTckn(), user);

        ProUserResult pur = new ProUserResult();
        pur.setSonucKodu(ProIKConnector.CODE_SUCCESS);
        pur.setHata("SUCCESS");

        return pur;
    }

    @Override
    public ProUserResult updateUser(ProUser user) throws RemoteException {
        ProUserResult notExists = userDoesNotExists(user.getTckn());
        if (notExists != null) {
            return notExists;
        }

        if (user.getGuncellemeZamani()==null) {
            ProUserResult pur = new ProUserResult();
            pur.setSonucKodu(ProIKConnector.CODE_CANNOT_LOGIN_TO_SYSTEM);
            pur.setHata("GuncellemeZamani is mandatory !!!");

            return pur;
        }

        users.put(user.getTckn(), user);

        ProUserResult pur = new ProUserResult();
        pur.setSonucKodu(ProIKConnector.CODE_SUCCESS);
        pur.setHata("SUCCESS");

        return pur;
    }

    private ProUserResult userDoesNotExists(String tckn) {
        if (!users.containsKey(tckn)) {
            ProUserResult pur = new ProUserResult();
            pur.setSonucKodu(ProIKConnector.CODE_USER_DOES_NOT_EXIST);
            pur.setHata("User does not exist");

            return pur;
        }

        return null;
    }

    @Override
    public ProUserResult disableUser(String tckn) throws RemoteException {
        ProUserResult notExists = userDoesNotExists(tckn);
        if (notExists != null) {
            return notExists;
        }

        ProUser user = users.get(tckn);
        if (ProIKConnector.DISABLED.equals(user.getAktifmi())) {
            ProUserResult pur = new ProUserResult();
            pur.setSonucKodu(ProIKConnector.CODE_USER_ALREADY_DISABLED);
            pur.setHata("User is already disabled");
        }

        user.setAktifmi(ProIKConnector.DISABLED);
        user.setGuncellemeZamani(new GregorianCalendar());

        ProUserResult pur = new ProUserResult();
        pur.setSonucKodu(ProIKConnector.CODE_SUCCESS);
        pur.setHata("SUCCESS");

        return pur;
    }

    @Override
    public ProUserResult enableUser(String tckn) throws RemoteException {
        ProUserResult notExists = userDoesNotExists(tckn);
        if (notExists != null) {
            return notExists;
        }

        ProUser user = users.get(tckn);
        if (ProIKConnector.ENABLED.equals(user.getAktifmi())) {
            ProUserResult pur = new ProUserResult();
            pur.setSonucKodu(ProIKConnector.CODE_USER_ALREADY_ENABLED);
            pur.setHata("User is already enabled");
        }

        user.setAktifmi(ProIKConnector.ENABLED);
        user.setGuncellemeZamani(new GregorianCalendar());

        ProUserResult pur = new ProUserResult();
        pur.setSonucKodu(ProIKConnector.CODE_SUCCESS);
        pur.setHata("SUCCESS");

        return pur;
    }

    @Override
    public ProUserResult listAllUser() throws RemoteException {
        ProUserResult pur = new ProUserResult();
        pur.setSonucKodu(ProIKConnector.CODE_SUCCESS);
        pur.setHata("SUCCESS");
        pur.setUsers(users.values().toArray(new ProUser[users.size()]));

        return pur;
    }
}
