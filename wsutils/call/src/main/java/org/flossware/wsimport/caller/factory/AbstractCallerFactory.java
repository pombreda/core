package org.flossware.wsimport.caller.factory;

import java.util.logging.Logger;
import javax.xml.ws.Service;
import org.flossware.wsimport.caller.factory.CallerFactory;

/**
 *
 * Abstract base class for caller factories.
 *
 * @author sfloess
 *
 */
public abstract class AbstractCallerFactory<V> implements CallerFactory<V> {
    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    protected final Logger getLogger() {
        return logger;
    }

    /**
     * Will create the port for service.
     *
     * @param <P> the type of port desired.
     *
     * @param service the web service.
     * @param portType the type of port on the web service.
     *
     * @return return the web service port.
     */
    protected <P> P createPort(final Service service, final Class<P> portType) {
        return service.getPort(portType);
    }

    /**
     * This constructor sets the session manager and decorator.
     */
    public AbstractCallerFactory() {
        this.logger = Logger.getLogger(getClass().getName());
    }
}
