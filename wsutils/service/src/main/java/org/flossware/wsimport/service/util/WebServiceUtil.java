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
package org.flossware.wsimport.service.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.annotation.mgr.AnnotationMgr;
import org.flossware.reflect.annotation.mgr.MethodAnnotationMgr;
import org.flossware.util.NetUtil;

/**
 * A Web Service utility class.
 *
 * @author Scot P. Floess
 */
public class WebServiceUtil {

    /**
     * Default WSDL resource directory.
     */
    public static final String DEFAULT_WSDL_DIR = "/wsdl/";

    /**
     * Used to find the port class on a service.
     */
    private static final AnnotationMgr<Method> DEFAULT_METHOD_ANNOTATION_MGR = new MethodAnnotationMgr();

    /**
     * Return the method annotation mgr.
     *
     * @return the method annotation mgr.
     */
    private static AnnotationMgr<Method> getDefaultMethodAnnotationMgr() {
        return DEFAULT_METHOD_ANNOTATION_MGR;
    }

    /**
     * Returns a port class from methodList - the port class is the return type
     * of any of the methods found in methodList.
     *
     * @param <S>          the type of service that was searched.
     *
     * @param serviceClass the class searched for port methods.
     * @param methodList   the list of methods that return the service's port.
     *
     * @return the class of the port type - this is the return type of the
     *         method that the service returns.
     *
     * @throws IllegalArgumentException if no port methods were found on the
     *                                  service.
     */
    private static <S extends Service> Class getPortClassFromMethodList(final Class<S> serviceClass, final List<Method> methodList) {
        IntegrityUtil.ensure(methodList, 1, "No port methods found on service class [" + serviceClass.getName() + "!");

        return methodList.get(0).getReturnType();
    }

    /**
     * Return the WebServiceClient annotation for serviceClass.
     *
     * @param <S>                 the type of service.
     * @param <P>                 the port type.
     *
     * @param serviceClass        the web service class.
     * @param methodAnnotationMgr manages finding annotations for methods.r
     *
     * @return the WebServiceClient annotation for serviceClass.
     */
    public static <S extends Service, P> Class<P> getPortClass(final Class<S> serviceClass, final AnnotationMgr<Method> methodAnnotationMgr) {
        IntegrityUtil.ensure(serviceClass, "Service class cannot be null!");
        IntegrityUtil.ensure(methodAnnotationMgr, "Cannot use a null method annotation manager!");

        return getPortClassFromMethodList(serviceClass, methodAnnotationMgr.find(serviceClass, WebEndpoint.class));
    }

    /**
     * Return the WebServiceClient annotation for serviceClass.
     *
     * @param <S>          the type of service.
     * @param <P>          the port type.
     *
     * @param serviceClass the web service class.
     *
     * @return the WebServiceClient annotation for serviceClass.
     */
    public static <S extends Service, P> Class<P> getPortClass(final Class<S> serviceClass) {
        IntegrityUtil.ensure(serviceClass, "Service class cannot be null!");

        return getPortClass(serviceClass, getDefaultMethodAnnotationMgr());
    }

    /**
     * Return the WebServiceClient annotation for serviceClass.
     *
     * @param <S>          the type of service.
     * @param serviceClass the web service class.
     *
     * @return the WebServiceClient annotation for serviceClass.
     */
    public static <S extends Service> WebServiceClient getWebServiceClientAnnotation(final Class<S> serviceClass) {
        return IntegrityUtil.ensure(serviceClass, "Service class cannot be null!").getAnnotation(WebServiceClient.class);
    }

    /**
     * Return the WebEndpoint annotation for serviceClass.
     *
     * @param webEndpointMethodList a list of methods that should have the web
     *                              end point annotation.
     *
     * @return the WebEndpoint annotation for serviceClass.
     */
    public static WebEndpoint getWebEndpointAnnotation(final List<Method> webEndpointMethodList) {
        IntegrityUtil.ensure(webEndpointMethodList, 1, "Must be at least one end point method!");

        return webEndpointMethodList.get(0).getAnnotation(WebEndpoint.class);
    }

    /**
     * Return the WebEndpoint annotation for serviceClass.
     *
     * @param <S>                 the type of service.
     *
     * @param serviceClass        the web service class.
     * @param methodAnnotationMgr manages finding annotations for methods.
     *
     * @return the WebEndpoint annotation for serviceClass.
     */
    public static <S extends Service> WebEndpoint getWebEndpointAnnotation(final Class<S> serviceClass, final AnnotationMgr<Method> methodAnnotationMgr) {
        IntegrityUtil.ensure(serviceClass, "Service class cannot be null!");
        IntegrityUtil.ensure(methodAnnotationMgr, "Cannot use a null method annotation manager!");

        return getWebEndpointAnnotation(methodAnnotationMgr.find(serviceClass, WebEndpoint.class));
    }

    /**
     * Return the WebEndpoint annotation for serviceClass.
     *
     * @param <S>          the type of service.
     * @param serviceClass the web service class.
     *
     * @return the WebEndpoint annotation for serviceClass.
     */
    public static <S extends Service> WebEndpoint getWebEndpointAnnotation(final Class<S> serviceClass) {
        return getWebEndpointAnnotation(serviceClass, getDefaultMethodAnnotationMgr());
    }

    /**
     * Find the WSDL file name - this is the annotation on the service class
     * denoted as
     *
     * @WebServiceClient whose attribute is wsdlLocation.
     *
     * @param <S>          the type of service class.
     * @param serviceClass the service class itself.
     *
     * @return the wsdlLocation attribute within the @WebServiceClient
     *         annotation.
     */
    public static <S extends Service> String getWsdlFileName(final Class<S> serviceClass) {
        return getWebServiceClientAnnotation(serviceClass).wsdlLocation();
    }

    /**
     * Return the WSDL file itself. This is denoted in the
     *
     * @WebServiceClient annotation in the wsdlLocation attribute. Simply
     * getting that and create a File from it.
     *
     * @param <S>          the type of service class.
     * @param serviceClass the service class itself.
     *
     * @return the file denoted as the wsdlLocation attribute within the
     *
     * @WebServiceClient annotation.
     */
    public static <S extends Service> File getWsdlFile(final Class<S> serviceClass) {
        return new File(NetUtil.createUrl(getWsdlFileName(serviceClass)).getFile());
    }

    /**
     * Return the WSDL resource name. This is the name of the wsdlLocation
     * attribute as denoted in the @WebServiceClient annotation.
     *
     * @param <S>          the type of web service.
     * @param serviceClass the class of the web service.
     *
     * @return the resource name minus the full path of the file denoted in the
     *         sdlLocation attribute of the @WebServiceClient annotation.
     */
    public static <S extends Service> String getWsdlResourceName(final Class<S> serviceClass) {
        return DEFAULT_WSDL_DIR + getWsdlFile(serviceClass).getName();
    }

    /**
     * Return a URL for the web service WSDL.
     *
     * @param <S>          the type of web service.
     * @param serviceClass the class of the web service.
     *
     * @return the default URL for the web service's WSDL.
     */
    public static <S extends Service> URL getWsdlResource(final Class<S> serviceClass) {
        return WebServiceUtil.class.getClassLoader().getResource(getWsdlResourceName(serviceClass));
    }

    /**
     * Return a QName for the WebServiceClient annotation.
     *
     * @param annotation the annotation to use.
     *
     * @return a QName for the WebServiceClient annotation.
     *
     * @throws IllegalArgumentException if annotation is null.
     */
    public static QName getQName(final WebServiceClient annotation) {
        IntegrityUtil.ensure(annotation, "Cannot have a null annotation!");

        return new QName(annotation.targetNamespace(), annotation.name());
    }

    /**
     * Return the QName for serviceClass.
     *
     * @param <S>          the type of service.
     * @param serviceClass the actual web service class.
     *
     * @return the QName for the serviceClass.
     *
     * @throws IllegalArgumentException if serviceClass is null.
     */
    public static <S extends Service> QName getQName(final Class<S> serviceClass) {
        IntegrityUtil.ensure(serviceClass, "Cannot have a null service class");

        return WebServiceUtil.getQName(getWebServiceClientAnnotation(serviceClass));
    }

    /**
     * Default constructor not allowed.
     */
    private WebServiceUtil() {
    }
}
