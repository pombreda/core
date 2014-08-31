package org.flossware.wsimport.caller.factory;

import javax.xml.ws.Service;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Create a caller capable of processing a service and port.
 *
 * @author sfloess
 *
 */
public interface CallerFactory<V> {

    <P> Caller<V> create(Service service, Class<P> portType) throws Exception;
}
