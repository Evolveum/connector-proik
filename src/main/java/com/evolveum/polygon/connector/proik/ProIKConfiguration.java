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

import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.net.MalformedURLException;
import java.net.URL;

import static org.identityconnectors.common.StringUtil.isBlank;

/**
 * ProIK Connector configuration.
 *
 * @author gpalos
 */
public class ProIKConfiguration extends AbstractConfiguration {

    private static final Log LOG = Log.getLog(ProIKConfiguration.class);

    /**
     * The ProIK SOAP endpoint.
     */
    private String endpoint;

    /**
     * If true, accept all HTTPS certificates, default false - only certificates in {midpoint.home}\keystore.jceks, @see https://wiki.evolveum.com/display/midPoint/Keystore+Configuration.
     */
    private Boolean trustingAllCertificates = false;

    /**
     * If true, use symulated ProIK system in memory over @com.evolveum.polygon.connector.proik.service.RealServiceImpl as mockup @see com.evolveum.polygon.connector.proik.service.DummyServiceImpl.
     */
    private Boolean useMockup = false;

    /**
     * If true, set modifyTimestamp automatically to current dateTime in create and update operations.
     */
    private Boolean generateModifyTimestamp = false;

    @Override
    public void validate() {
        if (isBlank(endpoint))
            throw new ConfigurationException("endpoint is empty");

        try {
            new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new ConfigurationException("Malformed endpoint: " + endpoint, e);
        }
    }

    @ConfigurationProperty(displayMessageKey = "proik.config.endpoint",
            helpMessageKey = "proik.config.endpoint.help")
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @ConfigurationProperty(displayMessageKey = "proik.config.trustingAllCertificates",
            helpMessageKey = "proik.config.trustingAllCertificates.help")
    public Boolean getTrustingAllCertificates() {
        return trustingAllCertificates;
    }

    public void setTrustingAllCertificates(Boolean trustingAllCertificates) {
        this.trustingAllCertificates = trustingAllCertificates;
    }

    @ConfigurationProperty(displayMessageKey = "proik.config.useMockup",
            helpMessageKey = "proik.config.useMockup.help")
    public Boolean getUseMockup() {
        return useMockup;
    }

    public void setUseMockup(Boolean useMockup) {
        this.useMockup = useMockup;
    }

    @ConfigurationProperty(displayMessageKey = "proik.config.generateModifyTimestamp",
            helpMessageKey = "proik.config.generateModifyTimestamp.help")
    public Boolean getGenerateModifyTimestamp() {
        return generateModifyTimestamp;
    }

    public void setGenerateModifyTimestamp(Boolean generateModifyTimestamp) {
        this.generateModifyTimestamp = generateModifyTimestamp;
    }
}