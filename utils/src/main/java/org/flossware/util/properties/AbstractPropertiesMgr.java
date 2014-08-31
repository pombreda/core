package org.flossware.util.properties;

import java.util.logging.Logger;
import org.flossware.util.properties.PropertiesMgr;

/**
 *
 * Abstract base class for property management.
 *
 * @author sfloess
 *
 */
public abstract class AbstractPropertiesMgr implements PropertiesMgr {
    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * Return the logger.
     */
    protected Logger getLogger() {
        return logger;
    }

    public AbstractPropertiesMgr() {
        logger = Logger.getLogger(getClass().getName());
    }
}
