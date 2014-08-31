package org.flossware.reflect.call.decorator;

import java.util.logging.Logger;

/**
 *
 * Abstract base class for decorate calls.
 *
 */
public abstract class AbstractDecorateCall<V> implements DecorateCall<V> {

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
     * Default constructor.
     */
    protected AbstractDecorateCall() {
        this.logger = Logger.getLogger(getClass().getName());
    }
}
