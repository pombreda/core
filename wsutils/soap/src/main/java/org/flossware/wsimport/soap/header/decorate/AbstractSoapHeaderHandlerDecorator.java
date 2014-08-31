package org.flossware.wsimport.soap.header.decorate;

import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;
import org.flossware.wsimport.soap.header.SoapHeaderHandler;
import org.flossware.wsimport.soap.header.decorate.SoapHeaderHandlerDecorator;

/**
 *
 * Abstract base class for adding behavior through the Decorator Pattern.
 *
 * @author sfloess
 *
 */
public abstract class AbstractSoapHeaderHandlerDecorator implements SoapHeaderHandlerDecorator {
    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * The object for whom we add behavior.
     */
    private final SoapHeaderHandler decoratee;

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    protected final Logger getLogger() {
        return logger;
    }

    /**
     * This constructor sets the decoratee (the object for whom we embellish).
     *
     * @param decoratee is the object for whom we embellish.
     *
     * @throws IllegalArgumentException if <code>decoratee</code> is null.
     */
    protected AbstractSoapHeaderHandlerDecorator(final SoapHeaderHandler decoratee) {
        IntegrityUtil.ensure(decoratee, "Object to decorate cannot be null!");

        this.logger    = Logger.getLogger(getClass().getName());
        this.decoratee = decoratee;
    }

    /**
     * {@inheritDoc}
     */
    public SoapHeaderHandler getDecoratee() {
        return decoratee;
    }
}
