package org.flossware.wsimport.soap.header;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import org.flossware.wsimport.soap.header.SoapHeaderHandler;

/**
 *
 * Represents a collection of SoapHeaderHandlers.
 *
 * @author sfloess
 *
 */
public class SoapHeaderHandlerComposite implements SoapHeaderHandler {
    /**
     * The collection of SOAP header handlers.
     */
    private final Collection<SoapHeaderHandler> soapHeaderHandlers;

    /**
     * Return the collection of SOAP header handlers.
     * @return
     */
    protected Collection<SoapHeaderHandler> getSoapHeaderHandlers() {
        return soapHeaderHandlers;
    }

    /**
     * Set the SOAP header handlers to use.
     *
     * @param soapHeaderHandlers the SOAP header handlers to delegate to.
     */
    public SoapHeaderHandlerComposite(final Collection<SoapHeaderHandler> soapHeaderHandlers) {
        this.soapHeaderHandlers = Collections.unmodifiableCollection(soapHeaderHandlers);
    }
    
    /**
     * Set the SOAP header handlers to use.
     *
     * @param soapHeaderHandlers the SOAP header handlers to delegate to.
     */
    public SoapHeaderHandlerComposite(final SoapHeaderHandler[] soapHeaderHandlers) {
        this.soapHeaderHandlers = Arrays.asList(soapHeaderHandlers);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public void setupHeader(final SOAPHeader header) throws SOAPException {
        for(final SoapHeaderHandler soapHeaderHandler : getSoapHeaderHandlers()) {
            soapHeaderHandler.setupHeader(header);
        }
    }
}
