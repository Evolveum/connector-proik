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

import com.evolveum.polygon.connector.proik.soap.ProIKKysWebServiceSoap_PortType;
import com.evolveum.polygon.connector.proik.soap.ProUser;
import com.evolveum.polygon.connector.proik.soap.ProUserResult;

import java.rmi.RemoteException;

/**
 * Implementation of {@link Service} interface for ProIK system.
 * <p/>
 * Created by gpalos on 8. 7. 2016.
 */
public class RealServiceImpl implements Service {
    private ProIKKysWebServiceSoap_PortType soap = null;

    public RealServiceImpl(ProIKKysWebServiceSoap_PortType soap) {
        this.soap = soap;
    }

    @Override
    public ProUser getUser(String tckn) throws RemoteException {
        return soap.getUser(tckn);
    }

    @Override
    public ProUserResult createUser(ProUser user) throws RemoteException {
        return soap.createUser(user);
    }

    @Override
    public ProUserResult updateUser(ProUser user) throws RemoteException {
        return soap.updateUser(user);
    }

    @Override
    public ProUserResult disableUser(String tckn) throws RemoteException {
        return soap.disableUser(tckn);
    }

    @Override
    public ProUserResult enableUser(String tckn) throws RemoteException {
        return soap.enableUser(tckn);
    }

    @Override
    public ProUserResult listAllUser() throws RemoteException {
        return soap.listAllUser();
    }
}
