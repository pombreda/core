package org.flossware.wsimport.soap.header;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Service;
import org.flossware.wsimport.service.util.WebServiceUtil;

/**
 *
 * This class handles attributes for soap headers.
 *
 * @author sfloess
 *
 */
public abstract class AbstractSoapHeaderHandler implements SoapHeaderHandler {

    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * The qualified name of the header element.
     */
    private final String qname;

    /**
     * The local name for the header element.
     */
    private final String localName;

    /**
     * Return the logger.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Return the qualified name of the header element.
     *
     * @return the qualified name of the header element.
     */
    protected String getQname() {
        return qname;
    }

    /**
     * Return the local name of the header element.
     *
     * @return the local name of the header element.
     */
    protected String getLocalName() {
        return localName;
    }

    /**
     * Create a qualified header element.
     *
     * @return a qualified header element.
     */
    protected QName createHeaderQname() {
        return new QName(getQname(), getLocalName());
    }

    /**
     * Add an element to the header.
     *
     * @param headerElement the header element to add to.
     * @param key           the name of the header element to add.
     * @param value         value to set the element to.
     *
     * @throws SOAPException if any problems arise added the header element.
     */
    protected void addElement(final SOAPElement headerElement, final String key, final String value) throws SOAPException {
        headerElement.addChildElement(key).addTextNode(value);

        getLogger().log(Level.FINE, "Set [{0}] header to [{1}]", new Object[] {key, value});
    }

    /**
     * Set the qualified name and local name of the header element.
     *
     * @param qname     is the qualified name of the header element.
     * @param localName is the local name of the header element.
     */
    protected AbstractSoapHeaderHandler(final String qname, final String localName) {
        this.logger = Logger.getLogger(getClass().getName());
        this.qname = qname;
        this.localName = localName;
    }

    /**
     * Set the qualified name and local name of the header element.
     *
     * @param qname     is the qualified name of the header element.
     * @param localName is the local name of the header element.
     */
    protected AbstractSoapHeaderHandler(final QName qname, final String localName) {
        this(qname.getNamespaceURI(), localName);
    }

    /**
     * Set the qualified name and local name of the header element using
     * <code>service</code>'s service name as the qualified name of the header
     * element.
     *
     * @param service   using the qualified name of the service, this is the
     *                  header element's qualified name.
     * @param localName is the local name of the header element.
     */
    protected AbstractSoapHeaderHandler(final Service service, final String localName) {
        this(service.getServiceName(), localName);
    }

    protected AbstractSoapHeaderHandler(final Class<? extends Service> serviceClass, final String localName) {
        this(WebServiceUtil.getQName(serviceClass), localName);
    }
}
