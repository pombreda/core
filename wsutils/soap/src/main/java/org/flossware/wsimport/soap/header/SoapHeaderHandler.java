package org.flossware.wsimport.soap.header;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

/**
 *
 * The purpose of this interface is to process SOAP headers.
 *
 * @author sfloess
 *
 */
public interface SoapHeaderHandler {
    /**
     * Setup the header.
     *
     * @param header is the SOAP header to setup.
     *
     * @throws SOAPException if any problems arise setting up the header.
     */
    void setupHeader(SOAPHeader header) throws SOAPException;
}
