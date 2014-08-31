package org.flossware.wsimport.soap.header;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.common.IntegrityUtil;

/**
 *
 * This class handles SOAP requests.
 *
 * @author sfloess
 *
 */
public final class SoapRequestHandler implements SOAPHandler<SOAPMessageContext> {

    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * Object that will process and handle the SOAP header.
     */
    private final SoapHeaderHandler headerHandler;

    /**
     * Return the logger.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Return the SOAP header handler.
     */
    protected SoapHeaderHandler getHeaderHandler() {
        return headerHandler;
    }

    /**
     * Return true if its a request or false if not.
     */
    protected boolean isRequest(final SOAPMessageContext msgContext) {
        return (Boolean) msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    }

    /**
     * Return our SOAP header or add one if its not found.
     *
     * @param msgContext contains the SOAP header desired.
     *
     * @return the SOAP header if it exists or add one if not found.
     *
     * @throws SOAPException any problems arise adding the SOAP header if
     *                       missing.
     */
    protected SOAPHeader getSoapHeader(final SOAPMessageContext msgContext) throws SOAPException {
        if (null != msgContext.getMessage().getSOAPPart().getEnvelope().getHeader()) {
            return msgContext.getMessage().getSOAPPart().getEnvelope().getHeader();
        }

        return msgContext.getMessage().getSOAPPart().getEnvelope().addHeader();
    }

    /**
     * Set the header handler.
     *
     * @param headerHandler is the object that can handle adding SOAP header
     *                      values.
     */
    public SoapRequestHandler(final SoapHeaderHandler headerHandler) {
        IntegrityUtil.ensure(headerHandler, "Must have a header handler!");

        this.logger = Logger.getLogger(SoapRequestHandler.class.getName());
        this.headerHandler = headerHandler;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public boolean handleMessage(final SOAPMessageContext msgContext) {
        //if this is a request, true for outbound messages, false for inbound
        if (!isRequest(msgContext)) {
            return true;
        }

        try {
            getHeaderHandler().setupHeader(getSoapHeader(msgContext));

            msgContext.getMessage().saveChanges();
        } catch (final SOAPException soapException) {
            getLogger().log(Level.SEVERE, "Problem handling msg", soapException);
        }

        return true;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public boolean handleFault(SOAPMessageContext context) {
        getLogger().log(Level.SEVERE, context.getMessage().getContentDescription());

        return true;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void close(MessageContext context) {
    }
}
