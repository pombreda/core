package org.flossware.wsimport.soap.header.decorate;

import org.flossware.wsimport.soap.header.SoapHeaderHandler;

/**
 *
 * Interface for embellishing a soap header handler.
 *
 * @author sfloess
 *
 */
public interface SoapHeaderHandlerDecorator extends SoapHeaderHandler {
    /**
     * Return the decoratee.
     *
     * @return the object for whom we add embellishment.
     */
    SoapHeaderHandler getDecoratee();
}
