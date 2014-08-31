package org.flossware.util.properties;

import java.util.Properties;

/**
 *
 * Uses system properties.
 *
 * @author sfloess
 *
 */
public class SystemPropertiesMgr extends AbstractPropertiesMgr {
    @Override
    public Properties getProperties() {
        return System.getProperties();
    }
}
