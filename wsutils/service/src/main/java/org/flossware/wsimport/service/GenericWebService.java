/*
 * Copyright (C) 2014 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.wsimport.service;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.flossware.common.IntegrityUtil;
import org.flossware.wsimport.service.util.WebServiceUtil;

/**
 * A generic web service.
 *
 * @author Scot P. Floess
 */
public class GenericWebService<P> extends Service implements WebService<P> {

    /**
     * The Web service's QName.
     */
    private final QName qname;

    /**
     * The ports web end point name.
     */
    private final String portName;

    /**
     * Our port class.
     */
    private final Class<P> portClass;

    /**
     * Returns the port class for our service.
     *
     * @return the port class for our service.
     */
    private Class<P> getPortClass() {
        return portClass;
    }

    /**
     * Sets the WSDL and QName.
     *
     * @param wsdlLocation the URL of the web service's WSDL.
     * @param serviceClass the class of the service.
     */
    public GenericWebService(final URL wsdlLocation, final Class<? extends Service> serviceClass) {
        super(IntegrityUtil.ensure(wsdlLocation, "Cannot have a null WSDL URL"), WebServiceUtil.getQName(serviceClass));

        this.qname = WebServiceUtil.getQName(serviceClass);
        this.portName = WebServiceUtil.getWebServiceClientAnnotation(serviceClass).name();
        this.portClass = WebServiceUtil.getPortClass(serviceClass);
    }

    /**
     * Assumes the WSDL is found on the classpath and uses that when creating
     * the URL for the web service.
     *
     * @param wsdlResource is the WSDL resource as found on the classpath.
     * @param serviceClass the class of the service.
     */
    public GenericWebService(final String wsdlResource, final Class<? extends Service> serviceClass) {
        this(GenericWebService.class.getClassLoader().getResource(wsdlResource), serviceClass);
    }

    /**
     * Sets the QName and uses the WebServiceUtil to compute the URL for the
     * WSDL of the serviceClass.
     *
     * @param serviceClass the class of the service.
     */
    public GenericWebService(final Class<? extends Service> serviceClass) {
        this(WebServiceUtil.getWsdlResource(serviceClass), serviceClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QName getQName() {
        return qname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPortName() {
        return portName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P getPort() {
        return getPort(getPortClass());
    }
}
