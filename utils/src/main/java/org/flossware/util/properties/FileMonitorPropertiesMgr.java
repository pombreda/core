package org.flossware.util.properties;

import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;
import org.flossware.util.FileMonitor;

/**
 *
 * Monitor a properties file. If the file changes, reload the properties.
 *
 * @author sfloess
 *
 */
public class FileMonitorPropertiesMgr extends AbstractPropertiesMgr {

    private final FileMonitor fileMonitor;

    private Properties properties;

    protected FileMonitor getFileMonitor() {
        return fileMonitor;
    }

    protected static Properties loadProperties(final Logger logger, final FileMonitor fileMonitor) {
        return new FilePropertiesMgr(fileMonitor.getFile()).getProperties();
    }

    @Override
    public Properties getProperties() {
        if (getFileMonitor().isChanged()) {
            properties = loadProperties(getLogger(), getFileMonitor());
        }

        return properties;
    }

    public FileMonitorPropertiesMgr(final File file) {
        this.fileMonitor = new FileMonitor(file);
        this.properties = loadProperties(getLogger(), fileMonitor);
    }

    public FileMonitorPropertiesMgr(final String fileName) {
        this.fileMonitor = new FileMonitor(fileName);
    }
}
