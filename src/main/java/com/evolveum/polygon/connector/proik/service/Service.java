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

import com.evolveum.polygon.connector.proik.soap.ProUser;
import com.evolveum.polygon.connector.proik.soap.ProUserResult;

import java.rmi.RemoteException;

/**
 * The Service interface.
 * <p/>
 * Created by gpalos on 8. 7. 2016.
 */
public interface Service {
    ProUser getUser(String tckn) throws RemoteException;

    ProUserResult createUser(ProUser user) throws RemoteException;

    ProUserResult updateUser(ProUser user) throws RemoteException;

    ProUserResult disableUser(String tckn) throws RemoteException;

    ProUserResult enableUser(String tckn) throws RemoteException;

    ProUserResult listAllUser() throws RemoteException;
}
