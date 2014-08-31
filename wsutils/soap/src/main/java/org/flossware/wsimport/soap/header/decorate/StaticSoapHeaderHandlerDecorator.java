package org.flossware.wsimport.soap.header.decorate;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

import org.flossware.wsimport.soap.header.SoapHeaderHandler;

/**
 *
 * This class ensures that the SOAP header is static - by static, the contents of the header are
 * removed before setting values.
 *
 * @author sfloess
 *
 */
public class StaticSoapHeaderHandlerDecorator extends AbstractSoapHeaderHandlerDecorator {
    /**
     * Sets the object for whom we embellish.
     *
     * @param decoratee is the object for whom we decorate.
     *
     * @throws IllegalArgumentException if decoratee is null.
     */
    public StaticSoapHeaderHandlerDecorator(final SoapHeaderHandler decoratee) {
        super(decoratee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setupHeader(final SOAPHeader header) throws SOAPException {
        header.removeContents();

        getDecoratee().setupHeader(header);
    }

}
