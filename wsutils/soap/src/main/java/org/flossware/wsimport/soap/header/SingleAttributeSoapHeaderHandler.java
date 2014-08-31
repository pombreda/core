package org.flossware.wsimport.soap.header;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.Service;

/**
 *
 * This class handles single attributes for soap headers.
 *
 * @author sfloess
 *
 */
public class SingleAttributeSoapHeaderHandler extends AbstractSoapHeaderHandler {

    /**
     * The name of the attribute for the header element.
     */
    private final String attributeName;

    /**
     * The value of the attribute for the header element.
     */
    private final String attributeValue;

    /**
     * Return the name of the attribute for the header element.
     *
     * @return the name of the attribute for the header element.
     */
    protected String getAttributeName() {
        return attributeName;
    }

    /**
     * Return the value of the attribute for the header element.
     *
     * @return the value of the attribute for the header element.
     */
    protected String getAttributeValue() {
        return attributeValue;
    }

    /**
     * Set the qualified name and local name of the header element. Additionally
     * set the name of the attribute that will be in the header and its value.
     *
     * @param qname          is the qualified name of the header element.
     * @param localName      is the local name of the header element.
     * @param attributeName  is the name of the attribute for the header
     *                       element.
     * @param attributeValue is the value of the attribute for the header
     *                       element.
     */
    public SingleAttributeSoapHeaderHandler(final String qname, final String localName, final String attributeName, final String attributeValue) {
        super(qname, localName);

        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    /**
     * Set the qualified name and local name of the header element. Additionally
     * set the name of the attribute that will be in the header and its value.
     *
     * @param qname          is the qualified name of the header element.
     * @param localName      is the local name of the header element.
     * @param attributeName  is the name of the attribute for the header
     *                       element.
     * @param attributeValue is the value of the attribute for the header
     *                       element.
     */
    public SingleAttributeSoapHeaderHandler(final QName qname, final String localName, final String attributeName, final String attributeValue) {
        super(qname, localName);

        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    /**
     * Set the qualified name (using the <code>service</code>'s service name as
     * the qualified name) and local name of the header element. Additionally
     * set the name of the attribute that will be in the header and its value.
     *
     * @param service        using the qualified name of the service, this is
     *                       the header element's qualified name.
     * @param localName      is the local name of the header element.
     * @param attributeName  is the name of the attribute for the header
     *                       element.
     * @param attributeValue is the value of the attribute for the header
     *                       element.
     */
    public SingleAttributeSoapHeaderHandler(final Service service, final String localName, final String attributeName, final String attributeValue) {
        super(service, localName);

        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public SingleAttributeSoapHeaderHandler(final Class<? extends Service> serviceClass, final String localName, final String attributeName, final String attributeValue) {
        super(serviceClass, localName);

        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setupHeader(final SOAPHeader header) throws SOAPException {
        addElement(header.addChildElement(createHeaderQname()), getAttributeName(), getAttributeValue());
    }
}
