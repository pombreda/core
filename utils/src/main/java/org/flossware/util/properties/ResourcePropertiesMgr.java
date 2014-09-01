package org.flossware.util.properties;

/**
 *
 * Loads properties as a resource.
 *
 * @author sfloess
 *
 */
public final class ResourcePropertiesMgr extends InputStreamPropertiesMgr {

    public ResourcePropertiesMgr(final String resourceName) {
        super(ResourcePropertiesMgr.class.getResourceAsStream(resourceName));
    }
}
