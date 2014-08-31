package org.flossware.wsimport.soap.header;

import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.Service;
import org.flossware.common.IntegrityUtil;
import org.flossware.wsimport.service.util.WebServiceUtil;

/**
 *
 * This class handles multi attributes for soap headers.
 *
 * @author sfloess
 *
 */
public class MultiAttributeSoapHeaderHandler extends AbstractSoapHeaderHandler {

    /**
     * The keys represent the name of the header elements and the values are the
     * values for each of the header elements.
     */
    private final Map<String, String> childElementMap;

    /**
     * Return the child element map.
     *
     * @return the child element map.
     */
    protected Map<String, String> getChildElementMap() {
        return childElementMap;
    }

    /**
     * Add child elements to the SOAP header.
     *
     * @param headerElement the SOAP header to add all child element.s
     *
     * @throws SOAPException if any problems arise adding the child elements.
     */
    protected void addElements(final SOAPElement headerElement) throws SOAPException {
        for (final String key : getChildElementMap().keySet()) {
            addElement(headerElement, key, getChildElementMap().get(key));
        }
    }

    /**
     * Set the qualified name and local name of the header element. Additionally
     * set the name of the child element names/values (as a map).
     *
     * @param qname           is the qualified name of the header element.
     * @param localName       is the local name of the header element.
     * @param childElementMap contains the child element names (as keys) and
     *                        values.
     */
    public MultiAttributeSoapHeaderHandler(final String qname, final String localName, final Map<String, String> childElementMap) {
        super(qname, localName);

        IntegrityUtil.ensure(childElementMap);

        this.childElementMap = new HashMap<>();

        this.childElementMap.putAll(childElementMap);
    }

    /**
     * Set the qualified name and local name of the header element. Additionally
     * set the name of the child element names/values (as a map).
     *
     * @param qname           is the qualified name of the header element.
     * @param localName       is the local name of the header element.
     * @param childElementMap contains the child element names (as keys) and
     *                        values.
     */
    public MultiAttributeSoapHeaderHandler(final QName qname, final String localName, final Map<String, String> childElementMap) {
        this(qname.getNamespaceURI(), localName, childElementMap);
    }

    /**
     * Set the qualified name (using the <code>service</code>'s service name as
     * the qualified name) and local name of the header element. Additionally
     * setthe name of the child element names/values (as a map).
     *
     * @param service         using the qualified name of the service, this is
     *                        the header element's qualified name.
     * @param localName       is the local name of the header element.
     * @param childElementMap contains the child element names (as keys) and
     *                        values.
     */
    public MultiAttributeSoapHeaderHandler(final Service service, final String localName, final Map<String, String> childElementMap) {
        this(service.getServiceName(), localName, childElementMap);
    }

    public MultiAttributeSoapHeaderHandler(final Class<? extends Service> serviceClass, final String localName, final Map<String, String> childElementMap) {
        this(WebServiceUtil.getQName(serviceClass), localName, childElementMap);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void setupHeader(final SOAPHeader header) throws SOAPException {
        addElements(header.addChildElement(createHeaderQname()));
    }
}
