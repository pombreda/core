package org.flossware.reflect.call.layering.processor;

import java.util.logging.Logger;

/**
 *
 * Abstract base class for call processing.
 *
 * @author sfloess
 *
 */
public abstract class AbstractProcessor {
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
     * Set the pause time.
     */
    public AbstractProcessor() {
        this.logger = Logger.getLogger(getClass().getName());
    }
}
